package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;
import java.util.Date;

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
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9941, title = "记录打印方案")
public class _PrintScheme implements _IModuleControlInterface , Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_printSchemeId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId")
	@FieldDefine(title = "模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_schemeOrder;

	@FieldDefine(title = "方案名称", nameField = true, number = 40)
	@Column(length = 50, nullable = false)
	private String tf_schemeName;

	@FieldDefine(title = "系统方案", number = 50)
	private Boolean tf_isSystemScheme;

	@FieldDefine(title = "子方案", number = 60)
	private Boolean tf_isSubScheme;

	@FieldDefine(title = "创建人员", number = 70)
	@Column(length = 20)
	private String tf_createMen;

	@FieldDefine(title = "创建日期", number = 80)
	private Date tf_createDate;

	@FieldDefine(title = "附加设置", number = 90)
	private String tf_otherSetting;

	public _PrintScheme() {

	}

	public Integer getTf_printSchemeId() {
		return tf_printSchemeId;
	}

	public void setTf_printSchemeId(Integer tf_printSchemeId) {
		this.tf_printSchemeId = tf_printSchemeId;
	}

	public _Module getTf_Module() {
		return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
		this.tf_Module = tf_Module;
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
		return tf_isSystemScheme;
	}

	public void setTf_isSystemScheme(Boolean tf_isSystemScheme) {
		this.tf_isSystemScheme = tf_isSystemScheme;
	}

	public Boolean getTf_isSubScheme() {
		return tf_isSubScheme;
	}

	public void setTf_isSubScheme(Boolean tf_isSubScheme) {
		this.tf_isSubScheme = tf_isSubScheme;
	}

	public String getTf_createMen() {
		return tf_createMen;
	}

	public void setTf_createMen(String tf_createMen) {
		this.tf_createMen = tf_createMen;
	}

	public Date getTf_createDate() {
		return tf_createDate;
	}

	public void setTf_createDate(Date tf_createDate) {
		this.tf_createDate = tf_createDate;
	}

	public String getTf_otherSetting() {
		return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
		this.tf_otherSetting = tf_otherSetting;
	}

}
