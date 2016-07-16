package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.jfok.cfcmms.hibernate.system.module._Module;

/**
 * 模块的一级审批信息的定义
 * 
 * @author jiangfeng
 * 
 */

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9045, title = "模块审批设置")
public class _ModuleApprove implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_approveId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleId", nullable = false, updatable = false)
	@FieldDefine(title = "所属模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "审批部门名称", number = 40, nameField = true)
	@Column(nullable = false, length = 50)
	private String tf_departmentName;

	@FieldDefine(title = "审批级次", number = 50, remark = "审批级次相同的可以多级同时审批。")
	@Column(nullable = false)
	private Integer tf_level;

	@FieldDefine(title = "可以终止审批", number = 60, remark = "可以审批的同时终止审批流程，即最终决定通过或不通过审批。")
	private Boolean tf_allowFinished;

	public _ModuleApprove() {

	}

	public Integer getTf_approveId() {
	return tf_approveId;
	}

	public void setTf_approveId(Integer tf_approveId) {
	this.tf_approveId = tf_approveId;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
	}

	public Integer getTf_order() {
	return tf_order;
	}

	public void setTf_order(Integer tf_order) {
	this.tf_order = tf_order;
	}

	public String getTf_departmentName() {
	return tf_departmentName;
	}

	public void setTf_departmentName(String tf_departmentName) {
	this.tf_departmentName = tf_departmentName;
	}

	public Integer getTf_level() {
	return tf_level;
	}

	public void setTf_level(Integer tf_level) {
	this.tf_level = tf_level;
	}

	public Boolean getTf_allowFinished() {
	return tf_allowFinished == null ? false : tf_allowFinished;
	}

	public void setTf_allowFinished(Boolean tf_allowFinished) {
	this.tf_allowFinished = tf_allowFinished;
	}

}
