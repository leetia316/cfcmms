package com.jfok.cfcmms.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.core.module.UserDataFilterInfo;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldHiddenRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldReadonlyRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.log._SystemLoginLog;
import com.jfok.cfcmms.share.ApproveListTypeEnum;



/**
 * 单个用户的session数据以及登录数据
 * 
 * @author jfok
 * 
 */

public class UserSession implements Serializable {

	private static final long serialVersionUID = -8672982011518767571L;
	private String sessionId;
	private LoginState loginState = LoginState.LOGOUT;
	private _SystemLoginLog systemLoginlog = null;
	private int loginTimes = 0;
	private String identifyingCode = null;
	private String loginName = null;
	private Integer userId = null;
	private String userName = null;
	private String departmentId = null;
	private String department = null;
	private List<UserDataFilterInfo> userDataFilterInfos; // 每个模块的权限设置
	// 每一个可以审批模块的各个审批的ＳＱＬ filters
	private Map<String, Map<ApproveListTypeEnum, List<SqlModuleFilter>>> moduleApproveSqlFilters;
	private List<_UserRoleDetail> tf_userRoleDetails; // 用户的权限信息
	private List<_UserFieldHiddenRoleDetail> tf_userFieldHiddenRoleDetails; // 字段角色中加入的不隐藏的字段
	private List<_UserFieldReadonlyRoleDetail> tf_userFieldReadonlyRoleDetails; // 字段角色中加入的不隐藏的字段
	// 综合查询中建立的临时查询，在session 关闭时全部去删除
	private Set<String> tempReportView;

	public UserSession(HttpSession session) {
		super();
		this.setSessionId(session.getId());
		this.tempReportView = new HashSet<String>();
		this.userDataFilterInfos = new ArrayList<UserDataFilterInfo>();
	}

	public void addUserDataFilterInfo(UserDataFilterInfo userDataFilterInfo) {
		if (userDataFilterInfo != null)
			userDataFilterInfos.add(userDataFilterInfo);
	}

	public void addUserDataFilterInfo(List<UserDataFilterInfo> userDataFilterInfos) {
		if (userDataFilterInfos != null)
			this.userDataFilterInfos.addAll(userDataFilterInfos);
	}

	public boolean isFieldHidden(Integer fieldId) {
		if (tf_userFieldHiddenRoleDetails != null) {
			for (_UserFieldHiddenRoleDetail detail : tf_userFieldHiddenRoleDetails)
				// 此字段不允许显示，隐藏权限设置为true
				if (detail.getTf_fieldId().equals(fieldId))
					return true;
		}
		return false;
	}

	/**
	 * 根据模块名称，取得当前用户是否有此模块的权限
	 * 
	 * @param moduleName
	 * @return
	 */
	public UserDataFilterInfo getUserDataFilterInfoWithModuleName(String moduleName) {
		for (UserDataFilterInfo userDataFilterInfo : userDataFilterInfos) {
			if (userDataFilterInfo.getModule().getTf_moduleName().equals(moduleName))
				return userDataFilterInfo;
			// 类名和数据表名不一样的，是用tf_tableName 来放moduleName的
			if (userDataFilterInfo.getModule().getTf_tableName() != null
					&& userDataFilterInfo.getModule().getTf_tableName().equals(moduleName))
				return userDataFilterInfo;

		}
		return null;
	}

	public _UserRoleDetail getUserRoleDetails(String moduleId) {
		for (_UserRoleDetail detail : tf_userRoleDetails)
			if (detail.getTf_moduleId().equals(moduleId))
				return detail;
		return null;
	}

	public LoginState getLoginState() {
		return loginState;
	}

	public void setLoginState(LoginState loginState) {
		this.loginState = loginState;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public _SystemLoginLog getSystemLoginlog() {
		return systemLoginlog;
	}

	public void setSystemLoginlog(_SystemLoginLog systemLoginlog) {
		this.systemLoginlog = systemLoginlog;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<UserDataFilterInfo> getUserDataFilterInfos() {
		return userDataFilterInfos;
	}

	public void setUserDataFilterInfos(List<UserDataFilterInfo> userDataFilterInfos) {
		this.userDataFilterInfos = userDataFilterInfos;
	}

	public Map<String, Map<ApproveListTypeEnum, List<SqlModuleFilter>>> getModuleApproveSqlFilters() {
		return moduleApproveSqlFilters;
	}

	public void setModuleApproveSqlFilters(
			Map<String, Map<ApproveListTypeEnum, List<SqlModuleFilter>>> moduleApproveSqlFilters) {
		this.moduleApproveSqlFilters = moduleApproveSqlFilters;
	}

	public List<_UserRoleDetail> getTf_userRoleDetails() {
		return tf_userRoleDetails;
	}

	public void setTf_userRoleDetails(List<_UserRoleDetail> tf_userRoleDetails) {
		this.tf_userRoleDetails = tf_userRoleDetails;
	}

	public List<_UserFieldHiddenRoleDetail> getTf_userFieldHiddenRoleDetails() {
		return tf_userFieldHiddenRoleDetails;
	}

	public void setTf_userFieldHiddenRoleDetails(
			List<_UserFieldHiddenRoleDetail> tf_userFieldHiddenRoleDetails) {
		this.tf_userFieldHiddenRoleDetails = tf_userFieldHiddenRoleDetails;
	}

	public List<_UserFieldReadonlyRoleDetail> getTf_userFieldReadonlyRoleDetails() {
		return tf_userFieldReadonlyRoleDetails;
	}

	public void setTf_userFieldReadonlyRoleDetails(
			List<_UserFieldReadonlyRoleDetail> tf_userFieldReadonlyRoleDetails) {
		this.tf_userFieldReadonlyRoleDetails = tf_userFieldReadonlyRoleDetails;
	}

	public Set<String> getTempReportView() {
		return tempReportView;
	}

	public void setTempReportView(Set<String> tempReportView) {
		this.tempReportView = tempReportView;
	}

}
