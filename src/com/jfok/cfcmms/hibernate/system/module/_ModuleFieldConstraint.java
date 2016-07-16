package com.jfok.cfcmms.hibernate.system.module;

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
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 990310, title = "模块字段平衡关系")
public class _ModuleFieldConstraint extends _InputInfoAbstract
	implements Serializable, _IModuleControlInterface {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId", nullable = false)
	@FieldDefine(title = "所属模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "平衡关系描述", number = 40, nameField = true)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "是否可用", number = 50)
	private Boolean tf_isEnable;

	@FieldDefine(title = "平衡关系级别", number = 60)
	@Column(length = 10)
	private String tf_Level;

	@FieldDefine(title = "平衡关系表达式", number = 70)
	@Column(length = 250, nullable = false)
	private String tf_express;

	@FieldDefine(title = "错误提示信息", number = 80)
	@Column(length = 250)
	private String tf_errorMessage;

	@FieldDefine(title = "显示信息字段", number = 90, remark = "错误信息绑定的字段")
	@Column(length = 50)
	private String tf_errorMessageField;

	@FieldDefine(title = "备注", number = 200)
	private String tf_remark;

	public _ModuleFieldConstraint() {

	}

	public Integer getTf_id() {
	return tf_id;
	}

	public void setTf_id(Integer tf_id) {
	this.tf_id = tf_id;
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

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public Boolean getTf_isEnable() {
	return tf_isEnable == null ? false : tf_isEnable;
	}

	public void setTf_isEnable(Boolean tf_isEnable) {
	this.tf_isEnable = tf_isEnable;
	}

	public String getTf_Level() {
	return tf_Level;
	}

	public void setTf_Level(String tf_Level) {
	this.tf_Level = tf_Level;
	}

	public String getTf_express() {
	return tf_express;
	}

	public void setTf_express(String tf_express) {
	this.tf_express = tf_express;
	}

	public String getTf_errorMessage() {
	return tf_errorMessage;
	}

	public void setTf_errorMessage(String tf_errorMessage) {
	this.tf_errorMessage = tf_errorMessage;
	}

	public String getTf_errorMessageField() {
	return tf_errorMessageField;
	}

	public void setTf_errorMessageField(String tf_errorMessageField) {
	this.tf_errorMessageField = tf_errorMessageField;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
