package com.jfok.cfcmms.share.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.util.ActionResult;

public interface ILoginService {

	/**
	 * 到服务器注册登录
	 * 
	 * @param 用户名
	 * @param 密码
	 * @param 验证码
	 * @return UserLoginResult
	 */
	@Transactional(propagation = Propagation.REQUIRED)

	public ActionResult login(String loginName, String password, String identifyingCode,
		HttpServletRequest request);

	/**
	 * 注销登录
	 */
	@Transactional(propagation = Propagation.REQUIRED)

	public void logout(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 忘记密码了，显示系统设置中的语言
	 */
	public String forgetPassword();

}
