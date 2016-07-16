package com.jfok.cfcmms.hibernate.system.log;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9092, title = "登录日志")
public class _SystemLoginLog implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10)
	private Integer tf_systemLogiglogId;

	@FieldDefine(title = "用户Id号", number = 20)
	private Integer tf_userId;

	@FieldDefine(title = "用户登录名", number = 30)
	private String tf_loginName;

	@FieldDefine(title = "用户名", number = 40, nameField = true)
	private String tf_userName;

	@FieldDefine(title = "登录时间", number = 50)
	private Date tf_loginDate;

	@FieldDefine(title = "注销时间", number = 60)
	private Date tf_logoutDate;

	@FieldDefine(title = "登录ip地址", number = 70)
	private String tf_ipaddress;

	@FieldDefine(title = "备注", number = 80)
	private String tf_remark;

	public _SystemLoginLog() {

	}

	public Integer getTf_systemLogiglogId() {
	return tf_systemLogiglogId;
	}

	public void setTf_systemLogiglogId(Integer tf_systemLogiglogId) {
	this.tf_systemLogiglogId = tf_systemLogiglogId;
	}

	public Integer getTf_userId() {
	return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
	this.tf_userId = tf_userId;
	}

	public String getTf_loginName() {
	return tf_loginName;
	}

	public void setTf_loginName(String tf_loginName) {
	this.tf_loginName = tf_loginName;
	}

	public String getTf_userName() {
	return tf_userName;
	}

	public void setTf_userName(String tf_userName) {
	this.tf_userName = tf_userName;
	}

	public Date getTf_loginDate() {
	return tf_loginDate;
	}

	public void setTf_loginDate(Date tf_loginDate) {
	this.tf_loginDate = tf_loginDate;
	}

	public Date getTf_logoutDate() {
	return tf_logoutDate;
	}

	public void setTf_logoutDate(Date tf_logoutDate) {
	this.tf_logoutDate = tf_logoutDate;
	}

	public String getTf_ipaddress() {
	return tf_ipaddress;
	}

	public void setTf_ipaddress(String tf_ipaddress) {
	this.tf_ipaddress = tf_ipaddress;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
