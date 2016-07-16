package com.jfok.cfcmms.hibernate.system.viewSetting;

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

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.setting._NumberGroup;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9910, title = "模块导航字段", shortName = "导航字段")
public class _ModuleGridNavigate implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10)
	private Integer tf_navigatesetId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId", updatable = false, nullable = false)
	@FieldDefine(title = "模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "导航字段名", number = 40, remark = "各字段之间用“;”号分隔")
	@Column(nullable = false, length = 200)
	private String tf_fields;

	@FieldDefine(title = "导航字段说明", number = 50, nameField = true)
	@Column(nullable = false, length = 200)
	private String tf_text;

	// @FieldDefine(title = "导航模式", number = 60)
	// // 比如说是 日期，ＹＭ ，ＹＳ ， Ｙ ，数值 区间
	// @Column(length = 20)
	// private String tf_mode;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_numberGroupId")
	@FieldDefine(title = "日期数值分组", number = 60)
	private _NumberGroup tf_NumberGroup;

	@FieldDefine(title = "附加类型", number = 70)
	@Column(length = 200)
	private String tf_type;

	@FieldDefine(title = "层级", number = 80, remark = "默认树是层级显示还是并排显示")
	private Boolean tf_cascading;

	// 只对基本字段有效,对于manytoone 字段无效，主要是对日期的作用
	@FieldDefine(title = "倒序", number = 90, remark = "显示的树是否倒序排列")
	private Boolean tf_reverseOrder;

	@FieldDefine(title = "可用", number = 100)
	private Boolean tf_enabled;

	public _ModuleGridNavigate() {

	}

	public _ModuleGridNavigate(String tf_fields, String tf_text) {
	super();
	this.tf_fields = tf_fields;
	this.tf_text = tf_text;
	this.tf_cascading = true;
	}

	public _ModuleGridNavigate(String tf_fields, String tf_text, _NumberGroup tf_NumberGroup,
		String tf_type, Boolean cascading) {
	super();
	this.tf_fields = tf_fields;
	this.tf_text = tf_text;
	this.tf_NumberGroup = tf_NumberGroup;
	this.tf_type = tf_type;
	this.tf_cascading = cascading;
	}

	public Integer getTf_navigatesetId() {
	return tf_navigatesetId;
	}

	public void setTf_navigatesetId(Integer tf_navigatesetId) {
	this.tf_navigatesetId = tf_navigatesetId;
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

	public String getTf_fields() {
	return tf_fields;
	}

	public void setTf_fields(String tf_fields) {
	this.tf_fields = tf_fields;
	}

	public String getTf_text() {
	return tf_text;
	}

	public void setTf_text(String tf_text) {
	this.tf_text = tf_text;
	}

	public _NumberGroup getTf_NumberGroup() {
	return tf_NumberGroup;
	}

	public void setTf_NumberGroup(_NumberGroup tf_NumberGroup) {
	this.tf_NumberGroup = tf_NumberGroup;
	}

	public String getTf_type() {
	return tf_type;
	}

	public void setTf_type(String tf_type) {
	this.tf_type = tf_type;
	}

	public Boolean getTf_cascading() {
	return tf_cascading == null ? false : tf_cascading;
	}

	public void setTf_cascading(Boolean tf_cascading) {
	this.tf_cascading = tf_cascading;
	}

	public Boolean getTf_enabled() {
	return tf_enabled == null ? false : tf_enabled;
	}

	public void setTf_enabled(Boolean tf_enabled) {
	this.tf_enabled = tf_enabled;
	}

	public Boolean getTf_reverseOrder() {
	return tf_reverseOrder == null ? false : tf_reverseOrder;
	}

	public void setTf_reverseOrder(Boolean tf_reverseOrder) {
	this.tf_reverseOrder = tf_reverseOrder;
	}

}
