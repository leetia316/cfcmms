package com.jfok.cfcmms.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;


public class loginValidFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		//displayURLandParm(httpRequest);

		String uri = httpRequest.getRequestURI();

		if (session.getAttribute(SessionManage.USERSESSION) == null
				|| ((UserSession) session.getAttribute(SessionManage.USERSESSION)).getLoginName() == null)
			if (isNeedFilter(uri)) {
				//System.out.println("filter:" + uri);
				// 对于extjs的ajax请求，加入 session timeout 让其重新登录
				if (httpRequest.getHeader("x-requested-with") != null
						&& httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))
					httpResponse.setHeader("sessionstatus", "timeout");
				else
					httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
				return;
			}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	/**
	 * 是否需要过滤，*.do, *.do/123,这种形式的都要过滤
	 * 
	 * @param uri
	 * @return
	 */
	private final static String[] needfilterExt = { ".jsp", ".do", ".action", ".htm", ".html" };

	private Boolean isNeedFilter(String uri) {
		if (uri.indexOf("/login/") != -1) // 登录模块的不需要过滤
			return false;
		if (uri.indexOf("login.jsp") != -1)
			return false;
		for (String s : needfilterExt)
			if (uri.indexOf(s) != -1)
				return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void displayURLandParm(HttpServletRequest httpRequest) {
		try {
			Map<String, String> param = new HashMap<String, String>();
			java.util.Enumeration<String> e = httpRequest.getParameterNames();
			while (e.hasMoreElements()) {
				String c = (String) e.nextElement();
				param.put(c, httpRequest.getParameter(c));
			}
			if (
			// httpRequest.getRequestURL().indexOf("extjs") > 0 ||
			httpRequest.getRequestURL().indexOf(".png") > 0
					|| httpRequest.getRequestURL().indexOf(".bmp") > 0
					|| httpRequest.getRequestURL().indexOf(".gif") > 0
			// || httpRequest.getRequestURL().indexOf(".js") > 0

			)
				return;
			System.out.print("[url]   " + httpRequest.getRequestURL());
			if (httpRequest.getQueryString() != null)
				System.out.println("?" + httpRequest.getQueryString());
			else
				System.out.println();
			@SuppressWarnings("rawtypes")
			Iterator it = param.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				System.out.println("          " + entry.getKey() + "~~" + entry.getValue());
			}
		} catch (Exception e) {
		}
	}

}
