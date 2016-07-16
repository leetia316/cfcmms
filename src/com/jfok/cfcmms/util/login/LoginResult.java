package com.jfok.cfcmms.util.login;

/**
 * 用户登录后返回的结果
 * @author jfok
 *
 */

public enum LoginResult {
	// 用户登录成功
	LOGINOK("登录成功"),
	// 用户名不正确
	LOGINNAMENOTFOUND("输入的用户名或者密码不正确！"),
	// 用户没有开通登录权限
	USERNOTAVIABLE("此用户尚未开通登录权限，请与管理员联系！"),
	// 用户密码错误
	ERRORPASSWORD("输入的用户名或者密码不正确！"),
	// 验证码不对
	ERRORIDENTIFYINGCODE("验证码错误或已过期，请重新刷新页面!！"),
	// 验证码不对
	SESSIONTIMEOUT("session time out！"),
	// 登录人员超过了最大限制
	LOGINNUMBERGTMAXUSER("登录的用户数已经超过了系统设置的最大值");
	
	private String context;

	private LoginResult(String context) {
		this.context = context;
	}

	public String getContext() {
		return context;
	}

}
