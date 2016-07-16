package com.jfok.cfcmms.hibernate.system.report;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 一个报表或综合查询的定义的字段的分组定义
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9053, title = "综合查询字段分组")
public class _ReportFieldGroup implements Serializable {

	@Id
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer tf_reportFieldGroupId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_reportId", nullable = false)
	@FieldDefine(title = "综合查询", number = 20)
	private _Report tf_Report;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_orderId;

	@FieldDefine(title = "分组名称", number = 40, nameField = true)
	@Column(nullable = false, length = 50)
	private String tf_groupName;

	@FieldDefine(title = "表头分组", number = 50)
	private Boolean tf_isShowHeaderSpans;

	@FieldDefine(title = "其他设置", number = 100)
	private String tf_otherSetting;

	// @JsonIgnore
	@OneToMany( mappedBy = "tf_ReportFieldGroup" , cascade = CascadeType.ALL)
//	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//			org.hibernate.annotations.CascadeType.DELETE})
	//@JoinColumn(name = "tf_reportFieldGroupId")
	@OrderBy("tf_orderId")
	private List<_ReportField> reportFields;

	public _ReportFieldGroup() {

	}

	public Integer getTf_reportFieldGroupId() {
		return tf_reportFieldGroupId;
	}

	public void setTf_reportFieldGroupId(Integer tf_reportFieldGroupId) {
		this.tf_reportFieldGroupId = tf_reportFieldGroupId;
	}

	public _Report getTf_Report() {
		return tf_Report;
	}

	public void setTf_Report(_Report tf_Report) {
		this.tf_Report = tf_Report;
	}

	public Integer getTf_orderId() {
		return tf_orderId;
	}

	public void setTf_orderId(Integer tf_orderId) {
		this.tf_orderId = tf_orderId;
	}

	public String getTf_groupName() {
		return tf_groupName;
	}

	public void setTf_groupName(String tf_groupName) {
		this.tf_groupName = tf_groupName;
	}

	public Boolean getTf_isShowHeaderSpans() {
		return tf_isShowHeaderSpans == null ? false : tf_isShowHeaderSpans;
	}

	public void setTf_isShowHeaderSpans(Boolean tf_isShowHeaderSpans) {
		this.tf_isShowHeaderSpans = tf_isShowHeaderSpans;
	}

	public String getTf_otherSetting() {
		return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
		this.tf_otherSetting = tf_otherSetting;
	}

	public List<_ReportField> getReportFields() {
		return reportFields;
	}

	public void setReportFields(List<_ReportField> reportFields) {
		this.reportFields = reportFields;
	}

}
