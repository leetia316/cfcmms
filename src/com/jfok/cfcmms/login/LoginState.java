package com.jfok.cfcmms.login;

/**
 * 用户的登录状态
 * 
 * @author jfok
 *
 */

public enum LoginState {
	// 系统打开网页login.jsp时的状态
	LOGOUT("LOGOUT"),
	// 系统在取得了当前session中的数据后的状态
	LOGING("LOGING"),
	// 用户成功登录后的状态
	LOGIN("LOGIN");

	private String context;

	LoginState(String context) {
	this.context = context;
	}

	public String getContext() {
	return context;
	}

}
