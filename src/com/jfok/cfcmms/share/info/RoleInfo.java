package com.jfok.cfcmms.share.info;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.hibernate.system.authority._UserFieldHiddenRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldReadonlyRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.report._ReportGroup;



/**
 * 用于向客户端返回权限信息等
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RoleInfo implements Serializable {

	private List<_UserRoleDetail> tf_userRoleDetails; // 用户的权限信息
	private List<_UserFieldHiddenRoleDetail> tf_userFieldHiddenRoleDetails; // 字段角色中加入的不隐藏的字段
	private List<_UserFieldReadonlyRoleDetail> tf_userFieldReadonlyRoleDetails; // 字段角色中加入的不隐藏的字段
	private List<_ReportGroup> tf_ReportGroups; // 综合查询的分组


	public RoleInfo() {
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


	public List<_ReportGroup> getTf_ReportGroups() {
		return tf_ReportGroups;
	}


	public void setTf_ReportGroups(List<_ReportGroup> tf_ReportGroups) {
		this.tf_ReportGroups = tf_ReportGroups;
	}

}
