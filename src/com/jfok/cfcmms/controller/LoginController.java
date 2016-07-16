package com.jfok.cfcmms.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.login.IdentifyingCodeServlet;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.ValidateCode;
import com.jfok.cfcmms.util.login.LoginResult;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	public static final String VALIDATECODE = "validateCode";
	public static final String LOGINTIMES = "logintimes";
	public static String LOGOUTMODE = "userLogoutMethod";
	public static String LOGOUTMODESTAND = "正常退出";
	public static String LOGOUTMODETIMEOUT = "超时退出";

	@RequestMapping(value = "/getusername.do")
	@ResponseBody
	public String getusername() {
		Subject user = SecurityUtils.getSubject();
		return user.getPrincipal().toString() + user.isRemembered() + user.isAuthenticated();
	}

	@RequestMapping(value = "/login.do")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult login(String loginname, String password, Boolean saveloginname,
			Boolean twoweek, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

		log.debug(loginname + " 开始登录");

		ActionResult result = new ActionResult();
		_Systeminfo si = SystemInfoService.getSysteminfo();

		// 是不是超出了系统设置的最大登录人数
		Integer maxuser = si.getTf_maxusers();
		if (maxuser > 0) {
			if (SessionManage.getInstance().getLoginUserNumber() >= maxuser) {
				log.debug("超出了最大登录人数: " + maxuser + " ！");
				result.setSuccess(false);
				result.setMsg(LoginResult.LOGINNUMBERGTMAXUSER.getContext());
				return result;
			}
		}

		// 判断是否要检测验证码
		if (BooleanUtils.isTrue(si.getTf_alwaysNeedIdentifingCode())
				|| (BooleanUtils.isTrue(si.getTf_needIdentifingCode() && getLoginTimes(session) >= 1))) {
			// 存在session中的验证码
			String code = ((String) session.getAttribute(VALIDATECODE)).toLowerCase();
			// request 中传入的验证码
			String submitCode = WebUtils.getCleanParam(request, VALIDATECODE);
			if (StringUtils.isEmpty(submitCode) || StringUtils.isEmpty(code)
					|| !StringUtils.equals(code, submitCode)) {
				result.setSuccess(false);
				result.setMsg(LoginResult.ERRORIDENTIFYINGCODE.getContext());
				log.debug(loginname + " 登录失败：," + LoginResult.ERRORIDENTIFYINGCODE.getContext());
				return result;
			}
		}
		// 生成一个登录主体，并且生成一个token
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);

		saveloginname = BooleanUtils.isTrue(saveloginname);
		twoweek = BooleanUtils.isTrue(twoweek);

		// 设置是否二周内自动登录,如果原来设置了true,但是通过login.jsp登录的时候密码不对，又把此设为false ,
		// 那就会将remember信息删除。
		token.setRememberMe(twoweek);

		// 登录次数加 1 ,
		session.setAttribute(LOGINTIMES, getLoginTimes(session) + 1);
		log.debug(loginname + " 准备第" + getLoginTimes(session) + "次登录");

		try {
			// 登录，身份认证
			user.login(token);

			// 如果正常登录，则将登录次数删除。如果有登录次数，并且要输入验证码的话，则第二次登录需要录入验证码
			session.removeAttribute(LOGINTIMES);

			Cookie loginnamecookie = new Cookie("loginname", loginname);
			Cookie savelogincookie = new Cookie("savename", saveloginname.toString());
			Cookie twoweekcookie = new Cookie("twoweek", saveloginname.toString());

			String contextPath = request.getContextPath();
			loginnamecookie.setPath(contextPath);
			savelogincookie.setPath(contextPath);
			twoweekcookie.setPath(contextPath);

			savelogincookie.setMaxAge(24 * 360 * 60 * 60);
			if (saveloginname)
				loginnamecookie.setMaxAge(24 * 360 * 60 * 60);
			else
				loginnamecookie.setMaxAge(0);
			if (twoweek)
				twoweekcookie.setMaxAge(24 * 14 * 60 * 60);
			else
				twoweekcookie.setMaxAge(0);

			response.addCookie(loginnamecookie);
			response.addCookie(savelogincookie);
			response.addCookie(twoweekcookie);

			log.debug(loginname + " 登录成功");
			// 用户名或密码错
		} catch (UnknownAccountException e) {
			result.setSuccess(false);
			result.setMsg(LoginResult.LOGINNAMENOTFOUND.getContext());
		} catch (IncorrectCredentialsException e) {
			result.setSuccess(false);
			result.setMsg("帐户异常，请咨询本单位管理员!");
			// 锁定的帐号
		} catch (LockedAccountException e) {
			result.setSuccess(false);
			result.setMsg(LoginResult.USERNOTAVIABLE.getContext());
			// 禁用的帐号
		} catch (DisabledAccountException e) {
			result.setSuccess(false);
			result.setMsg(LoginResult.USERNOTAVIABLE.getContext());
			// 上述各种异常的父类
		} catch (AuthenticationException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getCause());
		}
		if (result.getSuccess() == false) {
			token.clear();
			log.debug(loginname + " 登录失败：" + ", 原因：" + result.getMsg());
		}
		return result;
	}

	/**
	 * 用户发出的注销事件
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.do")
	@Transactional(propagation = Propagation.REQUIRED)
	public String logout(HttpSession session, HttpServletRequest request) {
		request.getSession().setAttribute(LOGOUTMODE, LOGOUTMODESTAND);
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

	/**
	 * 取得已经登录了几次
	 */
	public static Integer getLoginTimes(HttpSession session) {
		if (session.getAttribute(LOGINTIMES) != null)
			return (Integer) session.getAttribute(LOGINTIMES);
		else
			return 0;
	}

	/**
	 * 生成验证码
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode.do")
	public void generateValidateCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null)
				.toLowerCase();
		request.getSession().setAttribute(VALIDATECODE, verifyCode);
		response.setContentType("image/jpeg");
//		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE,
//				Color.BLACK, null);
		
		BufferedImage bim = IdentifyingCodeServlet.generateIdentifyingCode(verifyCode);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

	/**
	 * 检查前台发送的验证码是否正确
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkValidateCode.do")
	@ResponseBody
	public Integer checkValidateCode(String code, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (((String) request.getSession().getAttribute(VALIDATECODE)).equals(code.toLowerCase()))
			return 1;
		else
			return 0;
	}
}
