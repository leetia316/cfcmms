package com.jfok.cfcmms.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.authority._FieldRole;
import com.jfok.cfcmms.hibernate.system.authority._Role;
import com.jfok.cfcmms.hibernate.system.authority._RoleGroup;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldRole;
import com.jfok.cfcmms.hibernate.system.authority._UserRole;
import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.service.IUserService;

import sun.misc.BASE64Encoder;

@Service
public class UserService implements IUserService {

	private static final Log log = LogFactory.getLog(UserService.class);

	@Resource
	private SystemBaseDAO systemBaseDAO;

	public void userLogined(UserSession userSession, HttpServletRequest request) {
		// 用户登录后的初始化工作

		log.debug("用户登录成功，即将进行登录后的初始化操作！");

	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecordChecked> getFieldRolesWithEmployeeId(Integer userId) {

		List<_UserFieldRole> userFieldRoles = (List<_UserFieldRole>) systemBaseDAO.findByProperty(
				_UserFieldRole.class, "tf_userId", (userId));
		TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有隐藏字段角色", null, null,
				null, null);
		root.setLeaf(false);
		List<_FieldRole> groups = (List<_FieldRole>) systemBaseDAO.findAll(_FieldRole.class);
		Boolean rootChecked = true;

		for (_FieldRole role : groups) {
			TreeNodeRecordChecked roleRecord = new TreeNodeRecordChecked(null, null, role.getTf_name(),
					null, role.getTf_fieldRoleId(), null, null);
			for (_UserFieldRole userRole : userFieldRoles) {
				if (userRole.getTf_fieldRoleId().equals(role.getTf_fieldRoleId())) {
					roleRecord.setChecked(true);
					break;
				}
			}
			roleRecord.setTag(1);
			rootChecked = rootChecked && roleRecord.getChecked();
			root.getChildren().add(roleRecord);
		}
		root.setChecked(rootChecked);
		List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
		result.add(root);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean setFieldRolesWithEmployeeId(Integer userId, String noderecords) {
		List<_UserFieldRole> userRoles = (List<_UserFieldRole>) systemBaseDAO.findByProperty(
				_UserFieldRole.class, "tf_userId", userId);
		for (int i = userRoles.size() - 1; i >= 0; i--) {
			systemBaseDAO.delete(userRoles.get(i));
		}
		if (noderecords != null && noderecords.length() > 0) {
			String roles[] = noderecords.split(",");
			for (String role : roles)
				systemBaseDAO.save(new _UserFieldRole(role, userId));
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecordChecked> getRolesWithEmployeeId(Integer userId,
			HttpServletRequest request) {

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());

		List<_UserRole> userRoles = (List<_UserRole>) systemBaseDAO.findByProperty(_UserRole.class,
				"tf_User.tf_userId", (userId));

		TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有角色", null, null, null,
				null);
		root.setLeaf(false);
		List<_RoleGroup> groups = (List<_RoleGroup>) systemBaseDAO.findAll(_RoleGroup.class);
		Boolean rootChecked = true;
		for (_RoleGroup group : groups) {

			if (group.getTf_roleGroupId().equals("00"))
				if (!userSession.getLoginName().equals("admin"))
					continue;

			List<_Role> roles1 = (List<_Role>) systemBaseDAO.findByPropertyAllSort(_Role.class,
					"tf_roleId", "asc", "tf_RoleGroup.tf_roleGroupId", group.getTf_roleGroupId(), null, null);
			if (roles1.size() > 0) {
				TreeNodeRecordChecked groupRecord = new TreeNodeRecordChecked(null, null,
						group.getTf_title(), null, null, null, null);
				groupRecord.setLeaf(false);
				root.getChildren().add(groupRecord);
				boolean groupChecked = true;
				for (_Role role : roles1) {
					TreeNodeRecordChecked roleRecord = new TreeNodeRecordChecked(null, null,
							role.getTf_roleName(), null, role.getTf_roleId(), null, null);
					for (_UserRole userRole : userRoles) {
						if (userRole.getTf_Role().getTf_roleId().equals(role.getTf_roleId())) {
							roleRecord.setChecked(true);
							break;
						}
					}
					roleRecord.setTag(1);
					groupChecked = groupChecked && roleRecord.getChecked();
					groupRecord.getChildren().add(roleRecord);
				}
				groupRecord.setChecked(groupChecked);
				rootChecked = rootChecked && groupRecord.getChecked();
			}
		}
		root.setChecked(rootChecked);
		List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
		result.add(root);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean setRolesWithEmployeeId(Integer userId, String noderecords) {
		List<_UserRole> userRoles = (List<_UserRole>) systemBaseDAO.findByProperty(_UserRole.class,
				"tf_User.tf_userId", userId);
		for (int i = userRoles.size() - 1; i >= 0; i--) {
			systemBaseDAO.delete(userRoles.get(i));
		}
		if (noderecords != null && noderecords.length() > 0) {
			String roles[] = noderecords.split(",");
			for (String role : roles)
				systemBaseDAO.save(new _UserRole(new _Role(role), new _User(userId)));
		}
		return true;
	}

	/**
	 * 初始化用户的密码为123456
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean resetPassword(Integer userId) {
		_User user = (_User) systemBaseDAO.findById(_User.class, userId);
		user.setTf_password(fu_GenPasswordWithId(userId, "123456"));
		systemBaseDAO.attachDirty(user, null);
		return true;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_User user = (_User) systemBaseDAO.findById(_User.class, userSession.getUserId());
		if (user.getTf_password().equals(fu_GenPasswordWithId(user.getTf_userId(), oldPassword))) {
			user.setTf_password(fu_GenPasswordWithId(user.getTf_userId(), newPassword));
			systemBaseDAO.attachDirty(user, null);
			return true;
		} else
			return false;
	}

	/**
	 * 利用MD5进行加密
	 */
	public static String EncoderByMd5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr = null;
		try {
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}

	/**
	 * 根据序号和密码，生成一个MD5的密码，放在数据库中
	 */
	public static String fu_GenPasswordWithId(Integer id, String p) {
		return EncoderByMd5(id.toString() + " " + p);
	}

	/**
	 * 这个人是否有签名图片，如果有的话，返回包含userid的链接,没有的话返回name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSignPhotoSrc(String name) {
		List<_User> users = (List<_User>) systemBaseDAO
				.findByProperty(_User.class, "tf_userName", name);
		if (users.size() > 0) {
			_User user = users.get(0);
			try {
				if (user.getTf_signPhoto() != null && user.getTf_signPhoto().length() > 10)
					return "<span><img class=\"signphoto\" src=\"user/getsignphoto.do?userid="
							+ user.getTf_userId() + "\"/><span>";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return name;
	}

	/**
	 * 取得该用户的签名图片，是上传到user中的png图片
	 * 
	 * @param usernaem
	 * @throws IOException
	 */
	public void getUserSignPhoto(String userid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		_User user = (_User) systemBaseDAO.findById(_User.class, userid);
		if (user.getTf_signPhoto() != null) {
			response.setContentType("image/png");
			response.setHeader("Cache-Control", "max-age=" + 60);
			try {
				response.addHeader("Content-Length", "" + user.getTf_signPhoto().length());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Blob blob = user.getTf_signPhoto();
			writeBlobToResponse(blob, response);
		}
	}

	private void writeBlobToResponse(Blob blob, HttpServletResponse response) throws IOException {
		InputStream br = null;
		try {
			br = blob.getBinaryStream();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		OutputStream out = response.getOutputStream();
		try {
			byte[] buffer = new byte[1024 * 10];
			int len = 0;
			while ((len = br.read(buffer)) > 0) {
				out.write(buffer, 0, len);
				out.flush();
			}
		} catch (Exception e) {
		} finally {
			br.close();
			out.close();
		}
	}
}
