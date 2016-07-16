package com.jfok.cfcmms.controller;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.login.LoginLogic;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.info.ApplicationInfo;

@Controller
public class SystemAndLoginInfoController {

	@Resource
	private SystemAndLoginInfoService systemAndLoginInfoService;

	@Resource
	private LoginLogic loginLogic;

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 根据moduleId取得该模块的所有定义，基本信息，字段，grid,form信息，还有各种权限设置
	 * 
	 * @param request
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("/moduleinfo.do")
	@Transactional
	public @ResponseBody
	_Module getModuleInfo(HttpServletRequest request, String moduleName) {
		_Module module = systemAndLoginInfoService.getModuleInfo(request,moduleName);
//		
//		JSONObject aa = JSONObject.fromObject(module);
//		System.out.println(aa.toString());
//		
		return module;

	}

	@RequestMapping("/applicationinfo.do")
	public @ResponseBody
	ApplicationInfo getApplicationInfo(HttpServletRequest request) {

		// index.jsp 调用js 用，需要用此函数来取得系统的信息，在此之前断是否将登录信息写入了数据库
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		if (userSession.getLoginName() == null)
			loginLogic.saveLoginInfo(request);

		return systemAndLoginInfoService.getApplicationInfo(request);

		
	}

	@RequestMapping("/restarttomcat.do")
	public synchronized @ResponseBody
	void restartTomcat(HttpServletRequest request) {
		System.out.println("--------restarttomcat---------");
		Runtime run = Runtime.getRuntime();
		try {
			String s = SystemAndLoginInfoController.class.getResource("").getPath().replace("%20", " ");
			s = s.substring(0, s.indexOf("WEB-INF") + 8) + "restarttomcat.exe";
			@SuppressWarnings("unused")
			Process pro = run.exec(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从客户端设置服务器的日期和时间
	 * 
	 * @return
	 */
	@RequestMapping("/updateserverdate.do")
	public @ResponseBody
	String restartServerDate(String d, String t) {

		System.out.println(d);
		System.out.println(t);
		String result = "";
		try {
			java.lang.Runtime.getRuntime().exec("cmd /c date " + d);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			result += e.getCause();
		}

		try {
			java.lang.Runtime.getRuntime().exec("cmd /c time " + t);
		} catch (java.io.IOException e) {
			e.printStackTrace();
			result += "<br/>" + e.getCause();
		}
		return result;
	}

}
