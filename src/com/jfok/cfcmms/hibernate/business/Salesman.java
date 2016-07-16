package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.setting._Department;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 6020, title = "业务员")
public class Salesman implements Serializable {

	@Id
	// 使用的hibernate 的自动增量，没有用数据库的自动增量型
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10, fieldGroup = "用户基本信息", hidden = true)
	@Column(nullable = false)
	private Integer tf_salesmanId;

	@FieldDefine(title = "部门", number = 20, fieldGroup = "用户基本信息")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_departmentId", nullable = false)
	private _Department tf_Department;

	@FieldDefine(title = "姓名", nameField = true, number = 30, fieldGroup = "用户基本信息")
	@Column(length = 10, nullable = false)
	private String tf_name;

	@FieldDefine(title = "性别", number = 40, fieldGroup = "用户附加信息")
	@Column(length = 2, nullable = false)
	private String tf_sex;

	@FieldDefine(title = "出生日期", number = 50, fieldGroup = "用户附加信息")
	@Column(nullable = false)
	private Date tf_birthday;

	// 年龄是根据出生日期自动计算的。在sql server里是一个计算字段。
	@FieldDefine(title = "年龄", number = 60, fieldGroup = "用户附加信息")
	@Column(updatable = false, insertable = false)
	private Integer tf_age;

	@FieldDefine(title = "联系电话", number = 70, fieldGroup = "用户附加信息")
	@Column(length = 20)
	private String tf_telnumber;

	@FieldDefine(title = "手机号码", number = 80, fieldGroup = "用户附加信息")
	@Column(length = 20)
	private String tf_phonenumber;

	@FieldDefine(title = "电子邮件", number = 90, fieldGroup = "用户附加信息")
	@Column(length = 50)
	private String tf_eMail;

	@FieldDefine(title = "备注", number = 190, fieldGroup = "用户附加信息")
	private String tf_remark;

	public Salesman() {

	}

	public Integer getTf_salesmanId() {
		return tf_salesmanId;
	}

	public void setTf_salesmanId(Integer tf_salesmanId) {
		this.tf_salesmanId = tf_salesmanId;
	}

	public _Department getTf_Department() {
		return tf_Department;
	}

	public void setTf_Department(_Department tf_Department) {
		this.tf_Department = tf_Department;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public String getTf_sex() {
		return tf_sex;
	}

	public void setTf_sex(String tf_sex) {
		this.tf_sex = tf_sex;
	}

	public Date getTf_birthday() {
		return tf_birthday;
	}

	public void setTf_birthday(Date tf_birthday) {
		this.tf_birthday = tf_birthday;
	}

	public Integer getTf_age() {
		return tf_age;
	}

	public void setTf_age(Integer tf_age) {
		this.tf_age = tf_age;
	}

	public String getTf_telnumber() {
		return tf_telnumber;
	}

	public void setTf_telnumber(String tf_telnumber) {
		this.tf_telnumber = tf_telnumber;
	}

	public String getTf_phonenumber() {
		return tf_phonenumber;
	}

	public void setTf_phonenumber(String tf_phonenumber) {
		this.tf_phonenumber = tf_phonenumber;
	}

	public String getTf_eMail() {
		return tf_eMail;
	}

	public void setTf_eMail(String tf_eMail) {
		this.tf_eMail = tf_eMail;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
