package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9041, title = "用户操作角色")
public class _Role implements Serializable {

	@Id
	@FieldDefine(title = "角色编码", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_roleId;

	@FieldDefine(title = "角色名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_roleName;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_roleGroupId", nullable = false)
	@FieldDefine(title = "用户角色分组", number = 30)
	private _RoleGroup tf_RoleGroup;

	@FieldDefine(title = "角色可用", number = 40)
	@Column(nullable = false)
	private Boolean tf_isEnable;

	@FieldDefine(title = "备注", number = 50)
	private String tf_remark;

	@Transient
	private Boolean isSelected; // 用于保存 是否操作员选择了些角色

	public _Role() {

	}

	public _Role(String tf_roleId) {
		super();
		this.tf_roleId = tf_roleId;
	}

	public String getTf_roleId() {
		return tf_roleId;
	}

	public void setTf_roleId(String tf_roleId) {
		this.tf_roleId = tf_roleId;
	}

	public String getTf_roleName() {
		return tf_roleName;
	}

	public void setTf_roleName(String tf_roleName) {
		this.tf_roleName = tf_roleName;
	}

	public _RoleGroup getTf_RoleGroup() {
		return tf_RoleGroup;
	}

	public void setTf_RoleGroup(_RoleGroup tf_RoleGroup) {
		this.tf_RoleGroup = tf_RoleGroup;
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

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

}
