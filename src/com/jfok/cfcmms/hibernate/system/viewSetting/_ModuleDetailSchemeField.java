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
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9912, title = "模块明细显示字段")
public class _ModuleDetailSchemeField implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_detailFieldId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_detailId")
	@FieldDefine(title = "模块显示方案", number = 20)
	private _ModuleDetailScheme tf_ModuleDetailScheme;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fieldId")
	@FieldDefine(title = "模块字段", nameField = true, number = 50)
	private _ModuleField tf_ModuleField;

	// 用于传到js时使用
	@Column(insertable = false, updatable = false)
	private Integer tf_fieldId;

	@FieldDefine(title = "聚合类型", number = 45)
	@Column(length = 20)
	private String tf_aggregate;

	@FieldDefine(title = "操作类型", number = 60)
	@Column(length = 50)
	private String tf_action;

	@FieldDefine(title = "附加设置", number = 70)
	@Column(length = 50)
	private String tf_otherSetting;

	public _ModuleDetailSchemeField() {

	}

	public _ModuleDetailSchemeField(Integer tf_detailFieldId) {
		this.tf_detailFieldId = tf_detailFieldId;
		this.tf_order = 0;
	}

	public Integer getTf_detailFieldId() {
		return tf_detailFieldId;
	}

	public void setTf_detailFieldId(Integer tf_detailFieldId) {
		this.tf_detailFieldId = tf_detailFieldId;
	}

	public _ModuleDetailScheme getTf_ModuleDetailScheme() {
		return tf_ModuleDetailScheme;
	}

	public void setTf_ModuleDetailScheme(_ModuleDetailScheme tf_ModuleDetailScheme) {
		this.tf_ModuleDetailScheme = tf_ModuleDetailScheme;
	}

	public Integer getTf_order() {
		return tf_order;
	}

	public void setTf_order(Integer tf_order) {
		this.tf_order = tf_order;
	}

	public _ModuleField getTf_ModuleField() {
		return tf_ModuleField;
	}

	public void setTf_ModuleField(_ModuleField tf_ModuleField) {
		this.tf_ModuleField = tf_ModuleField;
	}

	public Integer getTf_fieldId() {
		return tf_fieldId;
	}

	public void setTf_fieldId(Integer tf_fieldId) {
		this.tf_fieldId = tf_fieldId;
	}

	public String getTf_action() {
		return tf_action;
	}

	public void setTf_action(String tf_action) {
		this.tf_action = tf_action;
	}

	public String getTf_otherSetting() {
		return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
		this.tf_otherSetting = tf_otherSetting;
	}

	public String getTf_aggregate() {
		return tf_aggregate;
	}

	public void setTf_aggregate(String tf_aggregate) {
		this.tf_aggregate = tf_aggregate;
	}

}
