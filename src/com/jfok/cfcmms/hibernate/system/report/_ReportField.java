package com.jfok.cfcmms.hibernate.system.report;

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

import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 一个报表或综合查询的定义的字段
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9054, title = "综合查询字段")
public class _ReportField implements Serializable {

	@Id
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer tf_reportFieldId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_reportFieldGroupId", nullable = false)
	@FieldDefine(title = "综合查询字段分组", number = 20)
	private _ReportFieldGroup tf_ReportFieldGroup;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_fieldId", nullable = false)
	@FieldDefine(title = "模块字段", nameField = true, number = 30)
	private _ModuleField tf_ModuleField;

	@FieldDefine(title = "顺序号", number = 40)
	@Column(nullable = false)
	private Integer tf_orderId;

	@FieldDefine(title = "字段查询条件", number = 50)
	private String tf_condition;

	@FieldDefine(title = "隐藏", number = 60)
	private Boolean tf_isHidden;
	
	// sum,max,avg,count,min,  这样的
	@FieldDefine(title = "聚合定义", number = 70)
	@Column(length = 50)
	private String tf_aggregate;

	@FieldDefine(title = "其他设置", number = 100)
	private String tf_otherSetting;

	public _ReportField() {

	}

	public Integer getTf_reportFieldId() {
		return tf_reportFieldId;
	}

	public void setTf_reportFieldId(Integer tf_reportFieldId) {
		this.tf_reportFieldId = tf_reportFieldId;
	}

	public _ReportFieldGroup getTf_ReportFieldGroup() {
		return tf_ReportFieldGroup;
	}

	public void setTf_ReportFieldGroup(_ReportFieldGroup tf_ReportFieldGroup) {
		this.tf_ReportFieldGroup = tf_ReportFieldGroup;
	}

	public _ModuleField getTf_ModuleField() {
		return tf_ModuleField;
	}

	public void setTf_ModuleField(_ModuleField tf_ModuleField) {
		this.tf_ModuleField = tf_ModuleField;
	}

	public Integer getTf_orderId() {
		return tf_orderId;
	}

	public void setTf_orderId(Integer tf_orderId) {
		this.tf_orderId = tf_orderId;
	}

	public String getTf_condition() {
		return tf_condition;
	}

	public void setTf_condition(String tf_condition) {
		this.tf_condition = tf_condition;
	}

	public Boolean getTf_isHidden() {
		return tf_isHidden == null ? false : tf_isHidden;
	}

	public void setTf_isHidden(Boolean tf_isHidden) {
		this.tf_isHidden = tf_isHidden;
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
