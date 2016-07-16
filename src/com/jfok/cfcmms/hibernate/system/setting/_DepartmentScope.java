package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9010, title = "部门权限")
public class _DepartmentScope implements Serializable {

	private static final long serialVersionUID = 7460840981190881743L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "编号", number = 10)
	private Integer tf_scopeId;

	@FieldDefine(title = "权限名称", number = 20)
	@Column(nullable = false, length = 50)
	private String tf_scopeName;

	@FieldDefine(title = "备注", number = 30)
	private String tf_remark;

	public _DepartmentScope() {

	}

	public _DepartmentScope(Integer tf_scopeId, String tf_scopeName, String tf_remark) {
	super();
	this.tf_scopeId = tf_scopeId;
	this.tf_scopeName = tf_scopeName;
	this.tf_remark = tf_remark;
	}

	@Override
	public String toString() {
	return "Scope [tf_scopeId=" + tf_scopeId + ", tf_scopeName=" + tf_scopeName + ", tf_remark="
		+ tf_remark + "]";
	}

	public Integer getTf_scopeId() {
	return tf_scopeId;
	}

	public void setTf_scopeId(Integer tf_scopeId) {
	this.tf_scopeId = tf_scopeId;
	}

	public String getTf_scopeName() {
	return tf_scopeName;
	}

	public void setTf_scopeName(String tf_scopeName) {
	this.tf_scopeName = tf_scopeName;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
