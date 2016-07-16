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

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.setting._User;

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9046, title = "模块审批人员")
public class _ModuleApproveUser implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_approveUserId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_approveId", nullable = false, updatable = false)
	@FieldDefine(title = "所属审批部门", number = 20)
	private _ModuleApprove tf_ModuleApprove;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_userId", nullable = false, updatable = true)
	@FieldDefine(title = "用户", number = 30, nameField = true)
	private _User tf_User;

	public _ModuleApproveUser() {

	}

	public Integer getTf_approveUserId() {
	return tf_approveUserId;
	}

	public void setTf_approveUserId(Integer tf_approveUserId) {
	this.tf_approveUserId = tf_approveUserId;
	}

	public _ModuleApprove getTf_ModuleApprove() {
	return tf_ModuleApprove;
	}

	public void setTf_ModuleApprove(_ModuleApprove tf_ModuleApprove) {
	this.tf_ModuleApprove = tf_ModuleApprove;
	}

	public _User getTf_User() {
	return tf_User;
	}

	public void setTf_User(_User tf_User) {
	this.tf_User = tf_User;
	}

}
