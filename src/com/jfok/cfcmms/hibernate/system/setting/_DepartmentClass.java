package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 权限的分组
 * 
 * @author jiangfeng
 * 
 */

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9009, title = "部门类型")
public class _DepartmentClass implements Serializable {

	@Id
	@FieldDefine(title = "部门类型编码", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_departmentClassId;

	@FieldDefine(title = "部门类型名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "备注", number = 30)
	private String tf_remark;

	public _DepartmentClass() {

	}

	public String getTf_departmentClassId() {
	return tf_departmentClassId;
	}

	public void setTf_departmentClassId(String tf_departmentClassId) {
	this.tf_departmentClassId = tf_departmentClassId;
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
