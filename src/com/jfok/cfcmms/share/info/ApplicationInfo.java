package com.jfok.cfcmms.share.info;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._Menu;

/**
 * 用于向客户端返回系统的模块信息和登录人员的信息的类
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ApplicationInfo implements Serializable {

	// 这是系统总体情况的设置，也是放在数据库里的，可以进行修改
	private SystemInfo systemInfo;

	// 这是用户单位和登录用户的信息
	private UserInfo userInfo;

	// 这是服务单位情况的设置，也是放在数据库里的，可以进行修改
	private ServiceInfo serviceInfo;

	// 所有模块的权限信息，一次加入
	private RoleInfo roleInfo;

	private List<_Menu> menus; // 系统菜单

	private Set<_Module> modules;

	// 系统中各种权限的定义

	// 其他一些附加信息需要传送到前台的
	private Integer tf_additionFileMaxMB; // 上传文件的最大大小
	private String tf_previewExts; // 可预览的文件的后缀名 ，用逗号分开

	public ApplicationInfo() {
	}

	public SystemInfo getSystemInfo() {
	return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
	this.systemInfo = systemInfo;
	}

	public UserInfo getUserInfo() {
	return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
	this.userInfo = userInfo;
	}

	public ServiceInfo getServiceInfo() {
	return serviceInfo;
	}

	public void setServiceInfo(ServiceInfo serviceInfo) {
	this.serviceInfo = serviceInfo;
	}

	public List<_Menu> getMenus() {
	return menus;
	}

	public void setMenus(List<_Menu> menus) {
	this.menus = menus;
	}

	public Integer getTf_additionFileMaxMB() {
	return tf_additionFileMaxMB;
	}

	public void setTf_additionFileMaxMB(Integer tf_additionFileMaxMB) {
	this.tf_additionFileMaxMB = tf_additionFileMaxMB;
	}

	public String getTf_previewExts() {
	return tf_previewExts;
	}

	public void setTf_previewExts(String tf_previewExts) {
	this.tf_previewExts = tf_previewExts;
	}

	public RoleInfo getRoleInfo() {
	return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
	this.roleInfo = roleInfo;
	}

	public Set<_Module> getModules() {
	return modules;
	}

	public void setModules(Set<_Module> modules) {
	this.modules = modules;
	}

}
