package com.jfok.cfcmms.hibernate.system.module;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.share.module.FieldAggregationType;

/**
 * 一个模块的附加字段，包括其父模块的字段，以及子模块的聚合字段，要加在dataSource 里，一起传到前台
 * 
 * @author jiangfeng
 * 
 */

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "基础模块", id = 9935, title = "模块附加字段")
public class _ModuleAdditionField implements _IModuleControlInterface, Serializable {
	@Id
	@FieldDefine(title = "序号")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(length = 10, unique = true, nullable = false)
	private Integer tf_moduleadditionfieldId;

	@FieldDefine(title = "模块编号")
	@Column(nullable = false, length = 10)
	private String tf_moduleId;

	@FieldDefine(title = "附加模块字段序号")
	@Column(nullable = false)
	private Integer tf_fieldId;

	@FieldDefine(title = "聚合类型")
	// normal count sum 暂时这三种
	@Column(nullable = false, length = 20)
	private String tf_aggregate;

	@Transient
	private String targetModuleName; // 目标的模块名称

	@Transient
	private String tf_title;

	@Transient
	private String tf_fieldName; // C__ S__moduleName等

	@Transient
	private String tf_fieldType;

	@Transient
	private Boolean tf_allowSummary;

	public _ModuleAdditionField() {

	}

	@JsonIgnore
	public FieldAggregationType getAggregationType() {
	for (FieldAggregationType type : FieldAggregationType.values())
		if (tf_aggregate.equals(type.getValue()))
		return type;
	return null;
	}

	public Integer getTf_moduleadditionfieldId() {
	return tf_moduleadditionfieldId;
	}

	public void setTf_moduleadditionfieldId(Integer tf_moduleadditionfieldId) {
	this.tf_moduleadditionfieldId = tf_moduleadditionfieldId;
	}

	public String getTf_moduleId() {
	return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
	this.tf_moduleId = tf_moduleId;
	}

	public Integer getTf_fieldId() {
	return tf_fieldId;
	}

	public void setTf_fieldId(Integer tf_fieldId) {
	this.tf_fieldId = tf_fieldId;
	}

	public String getTf_aggregate() {
	return tf_aggregate;
	}

	public void setTf_aggregate(String tf_aggregate) {
	this.tf_aggregate = tf_aggregate;
	}

	public String getTargetModuleName() {
	return targetModuleName;
	}

	public void setTargetModuleName(String targetModuleName) {
	this.targetModuleName = targetModuleName;
	}

	public String getTf_title() {
	return tf_title;
	}

	public void setTf_title(String tf_title) {
	this.tf_title = tf_title;
	}

	public String getTf_fieldName() {
	return tf_fieldName;
	}

	public void setTf_fieldName(String tf_fieldName) {
	this.tf_fieldName = tf_fieldName;
	}

	public String getTf_fieldType() {
	return tf_fieldType;
	}

	public void setTf_fieldType(String tf_fieldType) {
	this.tf_fieldType = tf_fieldType;
	}

	public Boolean getTf_allowSummary() {
	return tf_allowSummary;
	}

	public void setTf_allowSummary(Boolean tf_allowSummary) {
	this.tf_allowSummary = tf_allowSummary;
	}

}
