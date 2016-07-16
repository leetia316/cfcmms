package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)



/**
 * 用户和隐藏字段角色
 * 
 * @author jiangfeng
 *
 */

public class _UserFieldHiddenRole implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	private Integer tf_userFieldRoleId;

	private String tf_fieldRoleId;

	private Integer tf_userId;

	public _UserFieldHiddenRole() {

	}

	public _UserFieldHiddenRole(String tf_fieldRoleId, Integer userid) {
	this.tf_fieldRoleId = tf_fieldRoleId;
	this.tf_userId = userid;
	}

	public Integer getTf_userFieldRoleId() {
	return tf_userFieldRoleId;
	}

	public void setTf_userFieldRoleId(Integer tf_userFieldRoleId) {
	this.tf_userFieldRoleId = tf_userFieldRoleId;
	}

	public String getTf_fieldRoleId() {
	return tf_fieldRoleId;
	}

	public void setTf_fieldRoleId(String tf_fieldRoleId) {
	this.tf_fieldRoleId = tf_fieldRoleId;
	}

	public Integer getTf_userId() {
	return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
	this.tf_userId = tf_userId;
	}

}
