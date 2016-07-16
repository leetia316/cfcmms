package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9042, title = "只读字段明细")
public class _FieldReadonlyRoleDetail implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10, hidden = true)
	private Integer tf_fieldRoleDetailId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fieldRoleId", nullable = false)
	@FieldDefine(title = "只读字段角色", nameField = true, number = 20)
	private _FieldReadonlyRole tf_FieldReadonlyRole;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fieldId", nullable = false)
	@FieldDefine(title = "模块字段", nameField = true, number = 30)
	private _ModuleField tf_ModuleField;

	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;

	public _FieldReadonlyRoleDetail() {

	}

	public Integer getTf_fieldRoleDetailId() {
		return tf_fieldRoleDetailId;
	}

	public void setTf_fieldRoleDetailId(Integer tf_fieldRoleDetailId) {
		this.tf_fieldRoleDetailId = tf_fieldRoleDetailId;
	}

	public _FieldReadonlyRole getTf_FieldReadonlyRole() {
		return tf_FieldReadonlyRole;
	}

	public void setTf_FieldReadonlyRole(_FieldReadonlyRole tf_FieldReadonlyRole) {
		this.tf_FieldReadonlyRole = tf_FieldReadonlyRole;
	}

	public _ModuleField getTf_ModuleField() {
		return tf_ModuleField;
	}

	public void setTf_ModuleField(_ModuleField tf_ModuleField) {
		this.tf_ModuleField = tf_ModuleField;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
