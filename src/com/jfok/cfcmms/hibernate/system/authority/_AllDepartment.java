package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@TableDefine(group = "系统设置", id = 901110, title = "可视部门", codeLevel = "2,2,2,2,2")
public class _AllDepartment implements Serializable {

	@Id
	@FieldDefine(title = "部门编号", number = 10)
	@Column(length = 10, insertable = false, updatable = false)
	private String tf_allDepartmentId;

	@FieldDefine(title = "部门名称", nameField = true, number = 20)
	@Column(length = 50, insertable = false, updatable = false)
	private String tf_name;

	@FieldDefine(title = "备注", number = 200)
	@Column(insertable = false, updatable = false)
	private String tf_remark;

	public _AllDepartment() {

	}

	public String getTf_allDepartmentId() {
		return tf_allDepartmentId;
	}

	public void setTf_allDepartmentId(String tf_allDepartmentId) {
		this.tf_allDepartmentId = tf_allDepartmentId;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
