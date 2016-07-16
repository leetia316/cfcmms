package com.jfok.cfcmms.service;

import java.util.List;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.setting._PropertyType;
import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.hibernate.system.setting._Systemset;
import com.jfok.cfcmms.hibernate.system.setting._SystemsetAddition;


public class SystemInfoService {
	private static final Log log = LogFactory.getLog(SystemInfoService.class);

	private static _Systeminfo systeminfo = null;
	private static _Systemset systemset = null;
	private static _SystemsetAddition systemsetaddition = null;
	
	// 用来存放数据库中的各个property 的属性值

	private static List<_PropertyType> _propertyTypes = null;

	public synchronized static void refreshAll() {
		systeminfo = null;
		systemset = null;
		systemsetaddition = null;
		_propertyTypes = null;
		getSysteminfo();
		getSystemset();
		getSystemsetAddition();
	}

	@SuppressWarnings("unchecked")
	public synchronized static _PropertyType getPropertyType(String name) {
		if (_propertyTypes == null)
			_propertyTypes = (List<_PropertyType>) SystemBaseDAO.systemBaseDAO
					.findAll(_PropertyType.class);
		for (_PropertyType pt : _propertyTypes)
			if (pt.getTf_name().equals(name))
				return pt;
		return null;
	}

	public synchronized static _Systeminfo getSysteminfo() {
		if (systeminfo == null)
			systeminfo = (_Systeminfo) SystemBaseDAO.systemBaseDAO.findById(_Systeminfo.class, new Integer(
					1));
		return systeminfo;
	}

	public synchronized static _Systemset getSystemset() {
		if (systemset == null)
			systemset = (_Systemset) SystemBaseDAO.systemBaseDAO.findById(_Systemset.class, new Integer(1));
		return systemset;
	}

	public synchronized static _SystemsetAddition getSystemsetAddition() {
		if (systemsetaddition == null)
			systemsetaddition = (_SystemsetAddition) SystemBaseDAO.systemBaseDAO.findById(
					_SystemsetAddition.class, new Integer(1));
		return systemsetaddition;
	}

	private static ApplicationContext applicationContext;

	/**
	 * 取得主applicationContext
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			applicationContext = ContextLoader.getCurrentWebApplicationContext();
		}
		return applicationContext;
	}

	/**
	 * 取得mvc的applicationContext的方法
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext_dispatcherServlet() {
		if (applicationContext == null) {
			WebApplicationContext web = ContextLoader.getCurrentWebApplicationContext();
			ServletContext ctx = web.getServletContext();
			applicationContext = (ApplicationContext) ctx
					.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcherServlet");
		}
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		Object result = null;
		try {
			// for (String s :getApplicationContext().getBeanDefinitionNames())
			// System.out.println(s);
			// bean 的首字母是小写 ，有下划线的则不变
			beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
			result = getApplicationContext().getBean(beanName);
		} catch (Exception e) {
			log.debug("未找到spring中bean：" + beanName);
			// e.printStackTrace();
		}
		return result;
	}

	public static Object getBean(Class<?> bean) {
		Object result = null;
		try {
			result = getApplicationContext().getBean(bean);
		} catch (Exception e) {
			log.debug("未找到spring中bean：" + bean.getName());
		}
		return result;
	}
}
