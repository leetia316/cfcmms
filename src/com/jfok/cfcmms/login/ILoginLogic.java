package com.jfok.cfcmms.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfok.cfcmms.util.login.LoginResult;


/**
 * 
 * @author jfok   2012.10.26
 * 
 */
public interface ILoginLogic {

	/**
	 * 根据用户名，密码，验证码判断是否可以登录
	 * 
	 * @param loginName
	 * @param password
	 * @param identifyingCode
	 * @return Boolean 登录是否成功
	 */
	public LoginResult login(LoginType loginType, String loginName, String password,
			String identifyingCode, HttpServletRequest request);

//	private Boolean allowTwoWeeksAutoLogin(HttpServletRequest request);

	/**
	 * 用户在网页中点击 注销
	 * 
	 * @param request
	 */
	public void userLogout(HttpServletRequest request, HttpServletResponse response);

}
