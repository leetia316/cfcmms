package com.jfok.cfcmms.hibernate.system.log;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;


@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9090, title = "数据备份")
public class _Systembackup extends _InputInfoAbstract implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10)
	private Integer tf_systembackupId;

	@FieldDefine(title = "备份日期", number = 20, nameField = true)
	@Column(nullable = false, updatable = false)
	private Date tf_backupdate;

	@FieldDefine(title = "发起备份IP地址", number = 30)
	@Column(nullable = false, updatable = false, length = 24)
	private String tf_ipaddress;

	@FieldDefine(title = "备份人员", number = 40)
	@Column(updatable = false, length = 10)
	private String tf_userName;

	@FieldDefine(title = "备份文件名", number = 50)
	@Column(updatable = false, length = 250)
	private String tf_backupfilename;

	@FieldDefine(title = "已下载", number = 60)
	private Boolean tf_isupload;

	@FieldDefine(title = "备份结果", number = 70)
	private String tf_result;

	@FieldDefine(title = "备份保存文件", number = 70)
	private String tf_otherfiles;

	@FieldDefine(title = "备注", number = 80)
	private String tf_remark;

	public _Systembackup() {

	}

	public Integer getTf_systembackupId() {
		return tf_systembackupId;
	}

	public void setTf_systembackupId(Integer tf_systembackupId) {
		this.tf_systembackupId = tf_systembackupId;
	}

	public Date getTf_backupdate() {
		return tf_backupdate;
	}

	public void setTf_backupdate(Date tf_backupdate) {
		this.tf_backupdate = tf_backupdate;
	}

	public String getTf_ipaddress() {
		return tf_ipaddress;
	}

	public void setTf_ipaddress(String tf_ipaddress) {
		this.tf_ipaddress = tf_ipaddress;
	}

	public String getTf_userName() {
		return tf_userName;
	}

	public void setTf_userName(String tf_userName) {
		this.tf_userName = tf_userName;
	}

	public String getTf_backupfilename() {
		return tf_backupfilename;
	}

	public void setTf_backupfilename(String tf_backupfilename) {
		this.tf_backupfilename = tf_backupfilename;
	}

	public Boolean getTf_isupload() {
		return tf_isupload;
	}

	public void setTf_isupload(Boolean tf_isupload) {
		this.tf_isupload = tf_isupload;
	}

	public String getTf_result() {
		return tf_result;
	}

	public void setTf_result(String tf_result) {
		this.tf_result = tf_result;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

	public String getTf_otherfiles() {
		return tf_otherfiles;
	}

	public void setTf_otherfiles(String tf_otherfiles) {
		this.tf_otherfiles = tf_otherfiles;
	}

}
