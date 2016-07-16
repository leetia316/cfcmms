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
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9055, title = "综合查询图表方案", shortName = "查询图表")

public class _ReportChart extends _InputInfoAbstract implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_chartId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reportId", nullable = false)
	@FieldDefine(title = "综合查询", number = 20)
	private _Report tf_Report;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "图表方案名称", nameField = true, number = 40)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "系统方案", number = 50)
	private Boolean tf_isSystemScheme;

	@FieldDefine(title = "图表类型", number = 60)
	@Column(length = 50, nullable = false)
	private String tf_chartType;

	@FieldDefine(title = "配色方案", number = 70)
	@Column(length = 50, nullable = false)
	private String tf_colorScheme;

	@FieldDefine(title = "项目内容", number = 80)
	@Column(length = 50, nullable = false)
	private String tf_categoryField;

	@FieldDefine(title = "数值内容", number = 90)
	@Column(nullable = false)
	private String tf_numericFields;

	@FieldDefine(title = "显示数值", number = 100)
	private Boolean tf_isShowDetailNumber;

	@FieldDefine(title = "显示提示信息", number = 110)
	private Boolean tf_isShowTips;

	@FieldDefine(title = "动画展示", number = 120)
	private Boolean tf_isAnimate;

	@FieldDefine(title = "定位线", number = 130)
	private Boolean tf_isGridLine;

	@FieldDefine(title = "标题", number = 140)
	@Column(length = 50)
	private String tf_title;

	@FieldDefine(title = "附加设置", number = 190)
	private String tf_otherSetting;

	@FieldDefine(title = "备注", number = 200)
	private String tf_remark;

	public _ReportChart() {

	}

	public Integer getTf_chartId() {
	return tf_chartId;
	}

	public void setTf_chartId(Integer tf_chartId) {
	this.tf_chartId = tf_chartId;
	}

	public _Report getTf_Report() {
	return tf_Report;
	}

	public void setTf_Report(_Report tf_Report) {
	this.tf_Report = tf_Report;
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

	public Boolean getTf_isSystemScheme() {
	return tf_isSystemScheme;
	}

	public void setTf_isSystemScheme(Boolean tf_isSystemScheme) {
	this.tf_isSystemScheme = tf_isSystemScheme;
	}

	public String getTf_chartType() {
	return tf_chartType;
	}

	public void setTf_chartType(String tf_chartType) {
	this.tf_chartType = tf_chartType;
	}

	public String getTf_colorScheme() {
	return tf_colorScheme;
	}

	public void setTf_colorScheme(String tf_colorScheme) {
	this.tf_colorScheme = tf_colorScheme;
	}

	public String getTf_categoryField() {
	return tf_categoryField;
	}

	public void setTf_categoryField(String tf_categoryField) {
	this.tf_categoryField = tf_categoryField;
	}

	public String getTf_numericFields() {
	return tf_numericFields;
	}

	public void setTf_numericFields(String tf_numericFields) {
	this.tf_numericFields = tf_numericFields;
	}

	public Boolean getTf_isShowDetailNumber() {
	return tf_isShowDetailNumber;
	}

	public void setTf_isShowDetailNumber(Boolean tf_isShowDetailNumber) {
	this.tf_isShowDetailNumber = tf_isShowDetailNumber;
	}

	public Boolean getTf_isShowTips() {
	return tf_isShowTips;
	}

	public void setTf_isShowTips(Boolean tf_isShowTips) {
	this.tf_isShowTips = tf_isShowTips;
	}

	public Boolean getTf_isAnimate() {
	return tf_isAnimate;
	}

	public void setTf_isAnimate(Boolean tf_isAnimate) {
	this.tf_isAnimate = tf_isAnimate;
	}

	public Boolean getTf_isGridLine() {
	return tf_isGridLine;
	}

	public void setTf_isGridLine(Boolean tf_isGridLine) {
	this.tf_isGridLine = tf_isGridLine;
	}

	public String getTf_title() {
	return tf_title;
	}

	public void setTf_title(String tf_title) {
	this.tf_title = tf_title;
	}

	public String getTf_otherSetting() {
	return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
	this.tf_otherSetting = tf_otherSetting;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
