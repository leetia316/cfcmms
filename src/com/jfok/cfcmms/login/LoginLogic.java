package com.jfok.cfcmms.login;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.UserDataFilterInfo;
import com.jfok.cfcmms.hibernate.system.authority._DataFilterRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserAdditionDepartment;
import com.jfok.cfcmms.hibernate.system.authority._UserDataFilterRole;
import com.jfok.cfcmms.hibernate.system.log._SystemLoginLog;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.setting._Department;
import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.service.UserService;
import com.jfok.cfcmms.share.login.ILoginConstant;
import com.jfok.cfcmms.util.Utils;
import com.jfok.cfcmms.util.login.LoginResult;



@Service
public class LoginLogic implements ILoginLogic, ILoginConstant {

	private static final Log log = LogFactory.getLog(LoginLogic.class);

	@Resource
	private SystemBaseDAO systemBaseDAO;

	
	// 已经不用了
	@Override
	public LoginResult login(LoginType loginType, String loginName, String password,
			String identifyingCode, HttpServletRequest request) {
		log.debug("用户登录:" + loginName + "," + password);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_Systeminfo systeminfo = SystemInfoService.getSysteminfo();
		if (userSession == null)
			return LoginResult.SESSIONTIMEOUT;
		userSession.setLoginTimes(userSession.getLoginTimes() + 1);
		// 对密码解码
		if (password != null)
			try {
				password = base64decode(password.replaceAll("%3D", "="));
			} catch (Exception e) {
				log.error("密码解码错误!");
			}

		// 看看是不是超出了系统设置的最大登录人数
		Integer maxuser = SystemInfoService.getSysteminfo().getTf_maxusers();
		if (maxuser > 0) {
			if (SessionManage.getInstance().getLoginUserNumber() >= maxuser) {
				log.debug("超出了最大登录人数: " + maxuser + " ！");
				return LoginResult.LOGINNUMBERGTMAXUSER;
			}
		}

		// 最先验证验证码，只有用户手工登录，才需要验证码
		if (loginType == LoginType.USERLOGIN && systeminfo.getTf_needIdentifingCode())
			if ((systeminfo.getTf_alwaysNeedIdentifingCode() || userSession.getLoginTimes() > 1)
					&& !identifyingCode.equals(userSession.getIdentifyingCode())) {
				log.debug("验证码输入错误!");
				return LoginResult.ERRORIDENTIFYINGCODE;
			}
		@SuppressWarnings("unchecked")
		List<_User> users = (List<_User>) systemBaseDAO.findByProperty(_User.class, _User.LOGINNAME,
				loginName);
		if (users.size() == 0) {
			log.debug("系统中未找到登录名为: " + loginName + " 的用户！");
			return LoginResult.LOGINNAMENOTFOUND;
		}
		_User user = users.get(0);
		user.getUserAdditionDepartments().size();
		if (!user.getTf_allowLogin()) {
			log.debug("用户: " + loginName + " 尚未开通登录权限，请与管理员联系！");
			return LoginResult.USERNOTAVIABLE;
		}

		// 单点登录,不需要验证密码
		if (loginType != LoginType.DIRECTLOGIN) {
			if (user.getTf_password().length() < 11) {
				user.setTf_password(UserService.fu_GenPasswordWithId(user.getTf_userId(),
						user.getTf_password()));
				systemBaseDAO.attachDirty(user, null);
			}
			if (!user.getTf_password().equals(
					UserService.fu_GenPasswordWithId(user.getTf_userId(), password))) {
				log.debug("用户: " + loginName + " 密码错误！");
				return LoginResult.ERRORPASSWORD;
			}
		}
		log.info("登录成功:" + loginName + "," + loginType.getContext());
		return LoginResult.LOGINOK;
	}

	@Transactional
	public void saveLoginInfo( HttpServletRequest request) {
		
		Subject subject = SecurityUtils.getSubject();
		String loginname = (String)subject.getPrincipal();
		log.debug("写入登录日志，用户名：" + loginname);
		_User user = (_User) systemBaseDAO.findByPropertyFirst(_User.class, "tf_loginName", loginname);

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		SessionManage.getInstance().userLogined();
		user.setTf_lastLoginDate(new Date());
		user.setTf_loginTimes((user.getTf_loginTimes() == null ? 0 : user.getTf_loginTimes()) + 1);
		systemBaseDAO.attachDirty(user, null);

		userSession.setLoginState(LoginState.LOGIN);
		userSession.setLoginName(loginname);
		userSession.setUserId(user.getTf_userId());
		userSession.setUserName(user.getTf_userName());
		userSession.setDepartmentId(user.getTf_Department().getTf_departmentId());
		userSession.setDepartment(user.getTf_Department().getTf_name());
		userSession.addUserDataFilterInfo(getUserDepartmentRole(user, request));
		userSession.addUserDataFilterInfo(getUserDataFilterRole(user, request));

		_Systeminfo systeminfo = SystemInfoService.getSysteminfo();
		request.getSession().setMaxInactiveInterval(systeminfo.sessionTimeoutSecond());
		_SystemLoginLog systemLoginlog = new _SystemLoginLog();
		systemLoginlog.setTf_userId(userSession.getUserId());
		systemLoginlog.setTf_loginName(loginname);
		systemLoginlog.setTf_userName(userSession.getUserName());
		systemLoginlog.setTf_loginDate(new Date());
		systemLoginlog.setTf_ipaddress(Utils.getIpAddr(request));
		if (subject.isAuthenticated())
			systemLoginlog.setTf_remark(LoginType.USERLOGIN.getContext());
		else
			// 二周内自动登录
			systemLoginlog.setTf_remark(LoginType.TWOWEEKSAUTOLOGIN.getContext());

		systemBaseDAO.save(systemLoginlog);
		userSession.setSystemLoginlog(systemLoginlog);

	}

	// 加入该用户的模块筛选记录角色的所有筛选值
	public List<UserDataFilterInfo> getUserDataFilterRole(_User user, HttpServletRequest request) {
		List<UserDataFilterInfo> results = new ArrayList<UserDataFilterInfo>();
		for (_UserDataFilterRole dataFilterRole : user.getUserDataFilterRoles()) {
			for (_DataFilterRoleDetail detail : dataFilterRole.getTf_DataFilterRole()
					.getDataFilterRoleDetails()) {
				if (detail.getTf_recordIds() != null && detail.getTf_recordIds().length() > 0) {
					_Module module = SystemAndLoginInfoService.getModuleWithId(detail.getTf_Module()
							.getTf_moduleId());
					UserDataFilterInfo dataFilterInfo = new UserDataFilterInfo(module);
					String[] ids = detail.getTf_recordIds().split(",");
					String[] names = detail.getTf_recordNames().split("<br/>");
					for (String id : ids)
						dataFilterInfo.getKeys().add(id);
					for (String name : names)
						dataFilterInfo.getTitles().add(name);
					results.add(dataFilterInfo);
				}
			}
		}
		return results;
	}

	// 计算该用户能访问的部门权限,只是单一的部门，如果是复合权限，在另外的选择地方设置，并且覆盖掉这个
	public UserDataFilterInfo getUserDepartmentRole(_User user, HttpServletRequest request) {
		// 可视的部门范围
		String visibledDepartment;
		_Department department = (_Department) systemBaseDAO.findById(_Department.class, user
				.getTf_Department().getTf_departmentId());
		UserDataFilterInfo dataFilterInfo = new UserDataFilterInfo(
				SystemAndLoginInfoService.getModuleWithName(_Department.class.getSimpleName()));
		// 如果有部门的附加权限设置
		if (user.getUserAdditionDepartments().size() != 0) {
			for (_UserAdditionDepartment uad : user.getUserAdditionDepartments()) {
				dataFilterInfo.getKeys().add(uad.getTf_AllDepartment().getTf_allDepartmentId());
				dataFilterInfo.getTitles().add(uad.getTf_AllDepartment().getTf_name());
			}
		} else {
			// 如果能查看所有
			if (department.getTf_isOperAll())
				return null;
			else if (department.getTf_isOperThisLevel()) {
				// 可以查看本级的所有
				if (department.getTf_departmentId().length() > 2)
					visibledDepartment = department.getTf_departmentId().substring(0,
							department.getTf_departmentId().length() - 2);
				else
					return null;
			} else {
				visibledDepartment = department.getTf_departmentId();
			}

			department = (_Department) systemBaseDAO.findById(_Department.class, visibledDepartment);
			dataFilterInfo.getKeys().add(visibledDepartment);
			dataFilterInfo.getTitles().add(department.getTf_name());
		}
		return dataFilterInfo;
	}

	@Override
	public void userLogout(HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(LOGOUTMODE, LOGOUTMODESTAND);
	}

	// session 注销后执行的事
	@Transactional(propagation = Propagation.REQUIRED)
	public void logout(HttpSession session) {
		UserSession userSession = SessionManage.getInstance().getUserSession(session);
		log.debug(userSession.getLoginName() + " 正在注销");
		SessionManage.getInstance().userLogouted();
		_SystemLoginLog systemLoginlog = userSession.getSystemLoginlog();
		systemLoginlog.setTf_logoutDate(new Date());
		systemLoginlog.setTf_remark(systemLoginlog.getTf_remark() + ","
				+ (session.getAttribute(LOGOUTMODE) != null ? LOGOUTMODESTAND : LOGOUTMODETIMEOUT));
		systemBaseDAO.attachDirty(systemLoginlog, null);
		Thread thread = new RemoveTempReportView(userSession.getTempReportView());
		thread.start();
		log.debug(userSession.getLoginName() + " 已注销");

	}

	// 删除查询临时视图，放在线程里执行，防止阻塞用户logout
	class RemoveTempReportView extends Thread {
		private Set<String> tempReportViews;

		public RemoveTempReportView(Set<String> tempReportViews) {
			this.tempReportViews = tempReportViews;
		}

		public void run() {
			// 删除所有该用户的查询临时表
			Session sqlsession = systemBaseDAO.getSessionFactory().openSession();
			Transaction tx = sqlsession.beginTransaction();
			for (String tempQuery : tempReportViews) {
				SQLQuery query = sqlsession.createSQLQuery("drop view " + tempQuery);
				try {
					query.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			tx.commit();
			sqlsession.close();
		}
	}



	private int base64DecodeChars[] = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1,
			-1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
			22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
			39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };

	public String base64decode(String str) {
		char c1, c2, c3, c4;
		int i, len;
		String out;

		len = str.length();
		i = 0;
		out = "";
		while (i < len) {
			/* c1 */
			do {
				c1 = (char) base64DecodeChars[str.charAt(i++) & 0xff];
			} while (i < len && c1 == -1);
			if (c1 == -1)
				break;

			/* c2 */
			do {
				c2 = (char) base64DecodeChars[str.charAt(i++) & 0xff];
			} while (i < len && c2 == -1);
			if (c2 == -1)
				break;

			out += (char) ((c1 << 2) | ((c2 & 0x30) >> 4));

			/* c3 */
			do {
				c3 = (char) (str.charAt(i++) & 0xff);
				if (c3 == 61)
					return out;
				c3 = (char) base64DecodeChars[c3];
			} while (i < len && c3 == -1);
			if (c3 == -1)
				break;

			out += (char) (((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

			/* c4 */
			do {
				c4 = (char) (str.charAt(i++) & 0xff);
				if (c4 == 61)
					return out;
				c4 = (char) base64DecodeChars[c4];
			} while (i < len && c4 == -1);
			if (c4 == -1)
				break;
			out += (char) (((c3 & 0x03) << 6) | c4);
		}
		return out;
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *          cookie名字
	 * @return
	 */
	public static String getCookieValueByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = (Cookie[]) request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}


}
