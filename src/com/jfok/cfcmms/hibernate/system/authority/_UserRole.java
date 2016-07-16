package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@SuppressWarnings("serial")
@TableDefine(group = "系统设置", id = 9042, title = "用户操作角色分配")
public class _UserRole implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10)
	private Integer tf_userRoleId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_roleId", nullable = false)
	@FieldDefine(title = "用户角色", number = 20)
	private _Role tf_Role;

	// private String tf_roleId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_userId", nullable = false)
	@FieldDefine(title = "用户", number = 30)
	private _User tf_User;

	// private Integer tf_userId;

	public _UserRole() {

	}

	public _UserRole(_Role tf_Role, _User tf_User) {

		this.tf_Role = tf_Role;
		this.tf_User = tf_User;
	}

	public Integer getTf_userRoleId() {
		return tf_userRoleId;
	}

	public void setTf_userRoleId(Integer tf_userRoleId) {
		this.tf_userRoleId = tf_userRoleId;
	}

	public _Role getTf_Role() {
		return tf_Role;
	}

	public void setTf_Role(_Role tf_Role) {
		this.tf_Role = tf_Role;
	}

	public _User getTf_User() {
		return tf_User;
	}

	public void setTf_User(_User tf_User) {
		this.tf_User = tf_User;
	}

}
