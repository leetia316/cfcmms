package com.jfok.cfcmms.login;

public enum LoginType {
	USERLOGIN("正常登录"), TWOWEEKSAUTOLOGIN("二周内自动登录方式登录"), DIRECTLOGIN("外部调用登录");

	private String context;

	LoginType(String context) {
	this.context = context;
	}

	public String getContext() {
	return context;
	}
}
