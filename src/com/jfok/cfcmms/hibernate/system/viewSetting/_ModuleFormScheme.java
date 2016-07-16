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
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)

@TableDefine(group = "系统模块", id = 9907, title = "模块Form方案", shortName = "Form方案")
public class _ModuleFormScheme implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10)
	private Integer tf_formSchemeId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleId", nullable = false)
	@FieldDefine(title = "模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_schemeOrder;

	@FieldDefine(title = "方案名称", nameField = true, number = 40)
	@Column(nullable = false, length = 50)
	private String tf_schemeName;

	@FieldDefine(title = "系统方案", number = 50)
	private Boolean tf_isSystemScheme;

	@FieldDefine(title = "窗口高", number = 60)
	private Integer tf_windowHeight;

	@FieldDefine(title = "窗口宽", number = 70)
	private Integer tf_windowWidth;

	@FieldDefine(title = "分栏数", number = 80)
	private Integer tf_numCols;

	@FieldDefine(title = "Form类型", number = 90)
	private String tf_displayMode;

	@FieldDefine(title = "附加设置", number = 100)
	private String tf_otherSetting;

	@OneToMany(targetEntity = _ModuleFormSchemeGroup.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tf_formSchemeId")
	@OrderBy("tf_formGroupOrder")
	private List<_ModuleFormSchemeGroup> tf_schemeGroups;

	public _ModuleFormScheme() {

	}

	public Integer getTf_formSchemeId() {
	return tf_formSchemeId;
	}

	public void setTf_formSchemeId(Integer tf_formSchemeId) {
	this.tf_formSchemeId = tf_formSchemeId;
	}

	public Integer getTf_schemeOrder() {
	return tf_schemeOrder;
	}

	public void setTf_schemeOrder(Integer tf_schemeOrder) {
	this.tf_schemeOrder = tf_schemeOrder;
	}

	public String getTf_schemeName() {
	return tf_schemeName;
	}

	public void setTf_schemeName(String tf_schemeName) {
	this.tf_schemeName = tf_schemeName;
	}

	public Boolean getTf_isSystemScheme() {
	return tf_isSystemScheme == null ? false : tf_isSystemScheme;
	}

	public void setTf_isSystemScheme(Boolean tf_isSystemScheme) {
	this.tf_isSystemScheme = tf_isSystemScheme;
	}

	public Integer getTf_windowHeight() {
	return tf_windowHeight;
	}

	public void setTf_windowHeight(Integer tf_windowHeight) {
	this.tf_windowHeight = tf_windowHeight;
	}

	public Integer getTf_windowWidth() {
	return tf_windowWidth;
	}

	public void setTf_windowWidth(Integer tf_windowWidth) {
	this.tf_windowWidth = tf_windowWidth;
	}

	public Integer getTf_numCols() {
	return tf_numCols == null ? 2 : tf_numCols;
	}

	public void setTf_numCols(Integer tf_numCols) {
	this.tf_numCols = tf_numCols;
	}

	public String getTf_displayMode() {
	return tf_displayMode == null ? "" : tf_displayMode;
	}

	public void setTf_displayMode(String tf_displayMode) {
	this.tf_displayMode = tf_displayMode;
	}

	public String getTf_otherSetting() {
	return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
	this.tf_otherSetting = tf_otherSetting;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
	}

	public List<_ModuleFormSchemeGroup> getTf_schemeGroups() {
	return tf_schemeGroups;
	}

	public void setTf_schemeGroups(List<_ModuleFormSchemeGroup> tf_schemeGroups) {
	this.tf_schemeGroups = tf_schemeGroups;
	}

}
