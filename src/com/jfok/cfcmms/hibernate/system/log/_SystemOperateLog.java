package com.jfok.cfcmms.hibernate.system.log;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9091, title = "操作日志")
public class _SystemOperateLog implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10)
	private Integer tf_systemlogId;

	@FieldDefine(title = "操作时间", number = 20)
	@Column(nullable = false, updatable = false)
	private Date tf_date;

	@FieldDefine(title = "操作ip地址", number = 30)
	@Column(nullable = false, length = 24, updatable = false)
	private String tf_ipaddress; // nvarchar(24) Unchecked

	@FieldDefine(title = "用户", number = 35)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_userId",  insertable = false, updatable = false)
	private _User tf_User;

	@FieldDefine(title = "用户序号", number = 40)
	@Column(nullable = false, updatable = false)
	private Integer tf_userId;

	@FieldDefine(title = "用户名称", number = 50)
	@Column(nullable = false, length = 10, updatable = false)
	private String tf_userName;

	@FieldDefine(title = "操作类型", number = 60)
	@Column(nullable = false, length = 20, updatable = false)
	private String tf_do;

	@FieldDefine(title = "模块序号", number = 70)
	@Column(nullable = false, length = 10, updatable = false)
	private String tf_moduleId;

	@FieldDefine(title = "模块名称", number = 80)
	@Column(nullable = false, length = 50, updatable = false)
	private String tf_moduleTitle;

	@FieldDefine(title = "记录主键值", number = 90)
	@Column(nullable = false, length = 20, updatable = false)
	private String tf_recordkey;

	@FieldDefine(title = "记录名称值", number = 100, nameField = true)
	@Column(length = 50, updatable = false)
	private String tf_recordname;

	@FieldDefine(title = "包含文件", number = 110)
	private Boolean tf_hasfiledata;

	@JsonIgnore
	// @FieldDefine(title = "上传的文件", number = 110)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private Blob tf_filedata;// insert update excel 的文件数据

	@FieldDefine(title = "备注", number = 200)
	@Column(updatable = false)
	private String tf_remark;

	public _SystemOperateLog() {

	}

	public Integer getTf_systemlogId() {
		return tf_systemlogId;
	}

	public void setTf_systemlogId(Integer tf_systemlogId) {
		this.tf_systemlogId = tf_systemlogId;
	}

	public Date getTf_date() {
		return tf_date;
	}

	public void setTf_date(Date tf_date) {
		this.tf_date = tf_date;
	}

	public String getTf_ipaddress() {
		return tf_ipaddress;
	}

	public void setTf_ipaddress(String tf_ipaddress) {
		this.tf_ipaddress = tf_ipaddress;
	}

	public Integer getTf_userId() {
		return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
		this.tf_userId = tf_userId;
	}

	public String getTf_userName() {
		return tf_userName;
	}

	public void setTf_userName(String tf_userName) {
		this.tf_userName = tf_userName;
	}

	public String getTf_do() {
		return tf_do;
	}

	public void setTf_do(String tf_do) {
		this.tf_do = tf_do;
	}

	public String getTf_moduleId() {
		return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
		this.tf_moduleId = tf_moduleId;
	}

	public String getTf_recordkey() {
		return tf_recordkey;
	}

	public void setTf_recordkey(String tf_recordkey) {
		this.tf_recordkey = tf_recordkey;
	}

	public String getTf_recordname() {
		return tf_recordname;
	}

	public void setTf_recordname(String tf_recordname) {
		this.tf_recordname = tf_recordname;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

	public String getTf_moduleTitle() {
		return tf_moduleTitle;
	}

	public void setTf_moduleTitle(String tf_moduleTitle) {
		this.tf_moduleTitle = tf_moduleTitle;
	}

	public Blob getTf_filedata() {
		return tf_filedata;
	}

	public void setTf_filedata(Blob tf_filedata) {
		this.tf_filedata = tf_filedata;
	}

	public Boolean getTf_hasfiledata() {
		return tf_hasfiledata;
	}

	public void setTf_hasfiledata(Boolean tf_hasfiledata) {
		this.tf_hasfiledata = tf_hasfiledata;
	}

	public _User getTf_User() {
		return tf_User;
	}

	public void setTf_User(_User tf_User) {
		this.tf_User = tf_User;
	}

}
