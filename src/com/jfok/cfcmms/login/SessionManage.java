package com.jfok.cfcmms.login;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.jfok.cfcmms.service.SystemInfoService;

/**
 * 用于管理系统中各个session
 * 
 * @author jfok 2012.10.17
 */

public class SessionManage implements HttpSessionListener, ServletContextListener {

	private static final Log log = LogFactory.getLog(SessionManage.class);

	public static final String USERSESSION = "userSession";

	private static SessionManage sessionManage;

	public static Integer loginUserNumber = 0;

	private Map<String, HttpSession> userSessions;

	/**
	 * 此类是放在web.xml中的监听器对象，会由tomcat自动在启动时创建一个实例
	 */
	public SessionManage() {
		log.debug("SessionManage created");
		userSessions = new ConcurrentHashMap<String, HttpSession>();
		SessionManage.sessionManage = this;
	}

	public UserSession getUserSession(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(USERSESSION);
		if (userSession == null)
			session.invalidate();
		return userSession;
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.debug("Session created:" + event.getSession().getId());
		UserSession userSession = new UserSession(event.getSession());
		event.getSession().setAttribute(USERSESSION, userSession);
		userSessions.put(event.getSession().getId(), event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.debug("Session Destroyed:" + event.getSession().getId());
		String sessionId = event.getSession().getId();
		UserSession userSession = (UserSession) event.getSession().getAttribute(USERSESSION);
		if (userSession != null && userSession.getLoginState() == LoginState.LOGIN) {
			try {
				ApplicationContext context = SystemInfoService.getApplicationContext();
				LoginLogic loginLogic = (LoginLogic) context.getBean(LoginLogic.class);
				if (loginLogic != null)
					loginLogic.logout(event.getSession());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		userSessions.remove(sessionId);
	}

	public static SessionManage getInstance() {
		return SessionManage.sessionManage;
	}

	public synchronized void userLogined() {
		loginUserNumber++;
		log.debug("当前登录人数:" + loginUserNumber);
	}

	public synchronized void userLogouted() {
		if (loginUserNumber > 0)
			loginUserNumber--;
		log.debug("当前登录人数:" + loginUserNumber);
	}

	
	//也可以不用变量，直接取session 中 登录的记录数
	public synchronized Integer getLoginUserNumber() {
		return loginUserNumber;
	}

	// tomcat 启动的时候，会把关闭的时候的session序列化，在启动的时候又加入，但是只能保存可以序列化的属性
	// tomcat 加载的时候，会把原来序列化的session生成，然后去执行一下 web.xml中指定的主页

	// tomcat 服务停时候，会先把session destory,所以下面想要手工destory的时候，就不可以了

	// 服务器停止的时候
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//System.out.println("contextDestroyed");
		//System.out.println(userSessions.size());
		//

		// for (UserSession session : userSessions.values())
		// session.getSession().invalidate();
	}

	
	// 服务器启动时候
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//SystemAndLoginInfoService.getModules();
	}

}
