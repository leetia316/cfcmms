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
@TableDefine(group = "系统设置", id = 9039, title = "用户记录筛选角色分配")
public class _UserDataFilterRole implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10, hidden = true)
	private Integer tf_userDataFilterRoleId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_userId", nullable = false)
	@FieldDefine(title = "用户", number = 20)
	private _User tf_User;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_filterRoleId", nullable = false)
	@FieldDefine(title = "记录筛选角色", number = 30)
	private _DataFilterRole tf_DataFilterRole;

	public _UserDataFilterRole() {

	}

	public Integer getTf_userDataFilterRoleId() {
	return tf_userDataFilterRoleId;
	}

	public void setTf_userDataFilterRoleId(Integer tf_userDataFilterRoleId) {
	this.tf_userDataFilterRoleId = tf_userDataFilterRoleId;
	}

	public _User getTf_User() {
	return tf_User;
	}

	public void setTf_User(_User tf_User) {
	this.tf_User = tf_User;
	}

	public _DataFilterRole getTf_DataFilterRole() {
	return tf_DataFilterRole;
	}

	public void setTf_DataFilterRole(_DataFilterRole tf_DataFilterRole) {
	this.tf_DataFilterRole = tf_DataFilterRole;
	}

}
