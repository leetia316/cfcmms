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
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 每一个模块列表字段分组下面显示的字段
 * 
 * @author jfok
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9906, title = "模块列表字段", shortName = "列表字段")
public class _ModuleGridSchemeGroupField implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	private Integer tf_gridFieldId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_gridGroupId", nullable = false)
	@FieldDefine(title = "模块列表字段分组", number = 20)
	private _ModuleGridSchemeGroup tf_ModuleGridSchemeGroup;

	@JsonIgnore
	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_gridFieldOrder;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fieldId", nullable = false)
	@FieldDefine(title = "模块字段", nameField = true, number = 40)
	private _ModuleField tf_ModuleField;

	@FieldDefine(title = "聚合类型", number = 45)
	@Column(length = 20)
	private String tf_aggregate;

	@Column(insertable = false, updatable = false)
	private Integer tf_fieldId;

	@FieldDefine(title = "列宽度", number = 50)
	private Integer tf_columnWidth;

	@FieldDefine(title = "锁定列", number = 60)
	private Boolean tf_isLocked;

	@FieldDefine(title = "附加类型", number = 70)
	private String tf_additionType;

	@FieldDefine(title = "附加设置", number = 80)
	private String tf_otherSetting;

	@FieldDefine(title = "隐藏列", number = 90)
	private Boolean tf_ishidden;

	@JsonIgnore
	@FieldDefine(title = "Excel不导出", number = 100)
	private Boolean tf_notExportExcel;

	public _ModuleGridSchemeGroupField() {

	}

	public _ModuleGridSchemeGroupField(Integer gridFieldId) {
		this.tf_gridFieldId = gridFieldId;
	}

	public Integer getTf_gridFieldId() {
		return tf_gridFieldId;
	}

	public void setTf_gridFieldId(Integer tf_gridFieldId) {
		this.tf_gridFieldId = tf_gridFieldId;
	}

	public Integer getTf_gridFieldOrder() {
		return tf_gridFieldOrder;
	}

	public void setTf_gridFieldOrder(Integer tf_gridFieldOrder) {
		this.tf_gridFieldOrder = tf_gridFieldOrder;
	}

	public Integer getTf_columnWidth() {
		return tf_columnWidth;
	}

	public void setTf_columnWidth(Integer tf_columnWidth) {
		this.tf_columnWidth = tf_columnWidth;
	}

	public Boolean getTf_isLocked() {
		return tf_isLocked == null ? false : tf_isLocked;
	}

	public void setTf_isLocked(Boolean tf_isLocked) {
		this.tf_isLocked = tf_isLocked;
	}

	public String getTf_additionType() {
		return tf_additionType;
	}

	public void setTf_additionType(String tf_additionType) {
		this.tf_additionType = tf_additionType;
	}

	public String getTf_otherSetting() {
		return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
		this.tf_otherSetting = tf_otherSetting;
	}

	public _ModuleGridSchemeGroup getTf_ModuleGridSchemeGroup() {
		return tf_ModuleGridSchemeGroup;
	}

	public void setTf_ModuleGridSchemeGroup(_ModuleGridSchemeGroup tf_ModuleGridSchemeGroup) {
		this.tf_ModuleGridSchemeGroup = tf_ModuleGridSchemeGroup;
	}

	public _ModuleField getTf_ModuleField() {
		return tf_ModuleField;
	}

	public void setTf_ModuleField(_ModuleField tf_ModuleField) {
		this.tf_ModuleField = tf_ModuleField;
	}

	public String getTf_aggregate() {
		return tf_aggregate;
	}

	public void setTf_aggregate(String tf_aggregate) {
		this.tf_aggregate = tf_aggregate;
	}

	public Integer getTf_fieldId() {
		return tf_fieldId;
	}

	public void setTf_fieldId(Integer tf_fieldId) {
		this.tf_fieldId = tf_fieldId;
	}

	public Boolean getTf_ishidden() {
		return tf_ishidden;
	}

	public void setTf_ishidden(Boolean tf_ishidden) {
		this.tf_ishidden = tf_ishidden;
	}

	public Boolean getTf_notExportExcel() {
		return tf_notExportExcel == null ? false : tf_notExportExcel;
	}

	public void setTf_notExportExcel(Boolean tf_notExportExcel) {
		this.tf_notExportExcel = tf_notExportExcel;
	}

}
