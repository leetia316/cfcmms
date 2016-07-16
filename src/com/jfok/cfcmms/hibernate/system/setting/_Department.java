package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9011, title = "部门", codeLevel = "2,2,2,2,2")
public class _Department implements Serializable {

	@Id
	@FieldDefine(title = "部门编号", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_departmentId;

	@FieldDefine(title = "部门名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "部门简称", number = 30)
	@Column(length = 50)
	private String tf_shortname;

	//@FieldDefine(title = "部门权限", number = 40)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_scopeId")
	private _DepartmentScope tf_DepartmentScope;

	//@FieldDefine(title = "部门类型", number = 45)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_departmentClassId")
	private _DepartmentClass tf_DepartmentClass;

	@FieldDefine(title = "操作所有", number = 50)
	@Column(nullable = false)
	private Boolean tf_isOperAll;

	@FieldDefine(title = "本级", number = 60)
	@Column(nullable = false)
	private Boolean tf_isOperThisLevel;

	@FieldDefine(title = "付款部门", number = 70)
	@Column(nullable = false)
	private Boolean tf_isPayDepa;

	@FieldDefine(title = "建设", number = 80)
	@Column(nullable = false)
	private Boolean tf_isjs;

	@FieldDefine(title = "财务", number = 90)
	@Column(nullable = false)
	private Boolean tf_iscw;

	@FieldDefine(title = "审计", number = 100)
	@Column(nullable = false)
	private Boolean tf_issj;

	@FieldDefine(title = "职责范围", number = 110)
	private String tf_effect;

	@FieldDefine(title = "管理权限", number = 120)
	private String tf_preview;

	@FieldDefine(title = "负责人", number = 130)
	@Column(length = 10)
	private String tf_fzr;


	@FieldDefine(title = "是否管理部门", number = 140)
	@Column(nullable = false)
	private Boolean tf_isManage = false;
	
	@FieldDefine(title = "备注", number = 200)
	private String tf_remark;

	public _Department() {

	}

	@Override
	public String toString() {
		return "Department [tf_departmentId=" + tf_departmentId + ", tf_name=" + tf_name
				+ ", tf_shortname=" + tf_shortname + ", tf_scope=" + tf_DepartmentScope + ", tf_isOperAll="
				+ tf_isOperAll + ", tf_isOperThisLevel=" + tf_isOperThisLevel + ", tf_isPayDepa="
				+ tf_isPayDepa + ", tf_isjs=" + tf_isjs + ", tf_iscw=" + tf_iscw + ", tf_issj=" + tf_issj
				+ ", tf_effect=" + tf_effect + ", tf_preview=" + tf_preview + ", tf_remark=" + tf_remark;
	}

	public String getTf_departmentId() {
		return tf_departmentId;
	}

	public void setTf_departmentId(String tf_departmentId) {
		this.tf_departmentId = tf_departmentId;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public String getTf_shortname() {
		return tf_shortname;
	}

	public void setTf_shortname(String tf_shortname) {
		this.tf_shortname = tf_shortname;
	}

	public _DepartmentScope getTf_DepartmentScope() {
		return tf_DepartmentScope;
	}

	public void setTf_DepartmentScope(_DepartmentScope tf_DepartmentScope) {
		this.tf_DepartmentScope = tf_DepartmentScope;
	}

	public _DepartmentClass getTf_DepartmentClass() {
		return tf_DepartmentClass;
	}

	public void setTf_DepartmentClass(_DepartmentClass tf_DepartmentClass) {
		this.tf_DepartmentClass = tf_DepartmentClass;
	}

	public Boolean getTf_isOperAll() {
		return tf_isOperAll;
	}

	public void setTf_isOperAll(Boolean tf_isOperAll) {
		this.tf_isOperAll = tf_isOperAll;
	}

	public Boolean getTf_isOperThisLevel() {
		return tf_isOperThisLevel;
	}

	public void setTf_isOperThisLevel(Boolean tf_isOperThisLevel) {
		this.tf_isOperThisLevel = tf_isOperThisLevel;
	}

	public Boolean getTf_isPayDepa() {
		return tf_isPayDepa;
	}

	public void setTf_isPayDepa(Boolean tf_isPayDepa) {
		this.tf_isPayDepa = tf_isPayDepa;
	}

	public Boolean getTf_isjs() {
		return tf_isjs;
	}

	public void setTf_isjs(Boolean tf_isjs) {
		this.tf_isjs = tf_isjs;
	}

	public Boolean getTf_iscw() {
		return tf_iscw;
	}

	public void setTf_iscw(Boolean tf_iscw) {
		this.tf_iscw = tf_iscw;
	}

	public Boolean getTf_issj() {
		return tf_issj;
	}

	public void setTf_issj(Boolean tf_issj) {
		this.tf_issj = tf_issj;
	}

	public String getTf_effect() {
		return tf_effect;
	}

	public void setTf_effect(String tf_effect) {
		this.tf_effect = tf_effect;
	}

	public String getTf_preview() {
		return tf_preview;
	}

	public void setTf_preview(String tf_preview) {
		this.tf_preview = tf_preview;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

	public String getTf_fzr() {
		return tf_fzr;
	}

	public void setTf_fzr(String tf_fzr) {
		this.tf_fzr = tf_fzr;
	}

	public Boolean getTf_isManage() {
		return tf_isManage;
	}

	public void setTf_isManage(Boolean tf_isManage) {
		this.tf_isManage = tf_isManage;
	}

}
