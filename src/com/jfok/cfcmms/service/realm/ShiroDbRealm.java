package com.jfok.cfcmms.service.realm;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	private static final Log log = LogFactory.getLog(ShiroDbRealm.class);

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 角色授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("doGetAuthorizationInfo");

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		return info;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String loginname = token.getPrincipal().toString();
		String password = new String((char[]) token.getCredentials());
		_User user = (_User) systemBaseDAO.findByPropertyFirst(_User.class, "tf_loginName", loginname);
		if (user == null) {
			log.debug(loginname + " 登录名的用户未找到");
			throw new UnknownAccountException();
		}
		// 帐户被锁定
		if (!user.getTf_allowLogin())
			throw new LockedAccountException();
		if (!user.getTf_password().equals(
				UserService.fu_GenPasswordWithId(user.getTf_userId(), password))) {
			log.debug(loginname + " 密码错误");
			throw new UnknownAccountException();
		}
		return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
	}
}
