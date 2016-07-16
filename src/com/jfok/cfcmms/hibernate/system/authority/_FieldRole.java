package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9043, title = "隐藏字段角色")
public class _FieldRole implements Serializable {

	@Id
	@FieldDefine(title = "字段角色编码", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_fieldRoleId;

	@FieldDefine(title = "字段角色名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "角色可用", number = 30)
	@Column(nullable = false)
	private Boolean tf_isEnable;

	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;

	public _FieldRole() {

	}

	public String getTf_fieldRoleId() {
		return tf_fieldRoleId;
	}

	public void setTf_fieldRoleId(String tf_fieldRoleId) {
		this.tf_fieldRoleId = tf_fieldRoleId;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public Boolean getTf_isEnable() {
		return tf_isEnable;
	}

	public void setTf_isEnable(Boolean tf_isEnable) {
		this.tf_isEnable = tf_isEnable;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
