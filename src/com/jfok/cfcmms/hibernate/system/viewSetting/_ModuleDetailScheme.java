package com.jfok.cfcmms.hibernate.system.viewSetting;

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

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9911, title = "模块明细显示分组", shortName = "明细显示分组")
public class _ModuleDetailScheme implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_detailId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId")
	@FieldDefine(title = "模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "明细方案名称", nameField = true, number = 40)
	@Column(length = 50, nullable = false)
	private String tf_schemeName;

	@FieldDefine(title = "系统方案", number = 50)
	private Boolean tf_isSystemScheme;

	@OneToMany(targetEntity = _ModuleDetailSchemeField.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tf_detailId")
	@OrderBy("tf_order")
	private List<_ModuleDetailSchemeField> tf_moduleDetailSchemeFields;

	public _ModuleDetailScheme() {

	}

	public _ModuleDetailScheme(Integer tf_detailId) {
		this.tf_detailId = tf_detailId;
	}

	public Integer getTf_detailId() {
		return tf_detailId;
	}

	public void setTf_detailId(Integer tf_detailId) {
		this.tf_detailId = tf_detailId;
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

	public String getTf_schemeName() {
		return tf_schemeName;
	}

	public void setTf_schemeName(String tf_schemeName) {
		this.tf_schemeName = tf_schemeName;
	}

	public Boolean getTf_isSystemScheme() {
		return tf_isSystemScheme;
	}

	public void setTf_isSystemScheme(Boolean tf_isSystemScheme) {
		this.tf_isSystemScheme = tf_isSystemScheme;
	}

	public List<_ModuleDetailSchemeField> getTf_moduleDetailSchemeFields() {
		return tf_moduleDetailSchemeFields;
	}

	public void setTf_moduleDetailSchemeFields(
			List<_ModuleDetailSchemeField> tf_moduleDetailSchemeFields) {
		this.tf_moduleDetailSchemeFields = tf_moduleDetailSchemeFields;
	}

}
