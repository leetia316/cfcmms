package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonSerialize;



import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@TableDefine(group = "系统设置", id = 9000, title = "用户及服务单位")
public class _Systemset implements Serializable, _IModuleControlInterface {

	private static final long serialVersionUID = 6313704211807898718L;

	@Id
	@Column(insertable = false, updatable = false, nullable = false)
	@FieldDefine(title = "序号", number = 10, hidden = true, remark = "此模块记录只有一条", fieldGroup = "用户单位信息")
	private Integer tf_systemsetId;

	@FieldDefine(title = "单位名称", nameField = true, number = 20, fieldGroup = "用户单位信息")
	@Column(length = 50, nullable = false)
	private String tf_userdwmc;

	@FieldDefine(title = "单位地址", number = 30, fieldGroup = "用户单位信息")
	@Column(length = 50)
	private String tf_userAddress;

	@FieldDefine(title = "单位性质", number = 40, fieldGroup = "用户单位信息")
	@Column(length = 20)
	private String tf_userType;

	@FieldDefine(title = "联系人", number = 50, fieldGroup = "用户单位信息")
	@Column(length = 20)
	private String tf_userLinkmen;

	@FieldDefine(title = "联系电话", number = 60, fieldGroup = "用户单位信息")
	@Column(length = 20)
	private String tf_userTelnumber;

	@FieldDefine(title = "开始使用日期", number = 70, fieldGroup = "用户单位信息")
	@Column(length = 50)
	private Date tf_userStartdate;

	@FieldDefine(title = "用户备注", number = 90, fieldGroup = "用户单位信息")
	private String tf_userRemark;

	@FieldDefine(title = "服务单位名称", number = 210, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceDepartment;

	@FieldDefine(title = "服务人员", number = 220, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceMen;

	@FieldDefine(title = "服务电话", number = 230, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceTelnumber;

	@FieldDefine(title = "服务传真", number = 240, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceFaxnumber;

	@FieldDefine(title = "服务电子邮件", number = 250, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceEmail;

	@FieldDefine(title = "服务主页", number = 260, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceHomepage;

	@FieldDefine(title = "服务人员QQ", number = 270, fieldGroup = "服务单位信息")
	@Column(length = 50)
	private String tf_serviceQQ;

	@FieldDefine(title = "服务单位备注", number = 280, fieldGroup = "服务单位信息")
	private String tf_serviceRemark;

	public _Systemset() {

	}

	public Integer getTf_systemsetId() {
	return tf_systemsetId;
	}

	public void setTf_systemsetId(Integer tf_systemsetId) {
	this.tf_systemsetId = tf_systemsetId;
	}

	public String getTf_userdwmc() {
	return tf_userdwmc;
	}

	public void setTf_userdwmc(String tf_userdwmc) {
	this.tf_userdwmc = tf_userdwmc;
	}

	public String getTf_userAddress() {
	return tf_userAddress;
	}

	public void setTf_userAddress(String tf_userAddress) {
	this.tf_userAddress = tf_userAddress;
	}

	public String getTf_userType() {
	return tf_userType;
	}

	public void setTf_userType(String tf_userType) {
	this.tf_userType = tf_userType;
	}

	public String getTf_userLinkmen() {
	return tf_userLinkmen;
	}

	public void setTf_userLinkmen(String tf_userLinkmen) {
	this.tf_userLinkmen = tf_userLinkmen;
	}

	public String getTf_userTelnumber() {
	return tf_userTelnumber;
	}

	public void setTf_userTelnumber(String tf_userTelnumber) {
	this.tf_userTelnumber = tf_userTelnumber;
	}

	public Date getTf_userStartdate() {
	return tf_userStartdate;
	}

	public void setTf_userStartdate(Date tf_userStartdate) {
	this.tf_userStartdate = tf_userStartdate;
	}

	public String getTf_userRemark() {
	return tf_userRemark;
	}

	public void setTf_userRemark(String tf_userRemark) {
	this.tf_userRemark = tf_userRemark;
	}

	public String getTf_serviceDepartment() {
	return tf_serviceDepartment;
	}

	public void setTf_serviceDepartment(String tf_serviceDepartment) {
	this.tf_serviceDepartment = tf_serviceDepartment;
	}

	public String getTf_serviceMen() {
	return tf_serviceMen;
	}

	public void setTf_serviceMen(String tf_serviceMen) {
	this.tf_serviceMen = tf_serviceMen;
	}

	public String getTf_serviceTelnumber() {
	return tf_serviceTelnumber;
	}

	public void setTf_serviceTelnumber(String tf_serviceTelnumber) {
	this.tf_serviceTelnumber = tf_serviceTelnumber;
	}

	public String getTf_serviceFaxnumber() {
	return tf_serviceFaxnumber;
	}

	public void setTf_serviceFaxnumber(String tf_serviceFaxnumber) {
	this.tf_serviceFaxnumber = tf_serviceFaxnumber;
	}

	public String getTf_serviceEmail() {
	return tf_serviceEmail;
	}

	public void setTf_serviceEmail(String tf_serviceEmail) {
	this.tf_serviceEmail = tf_serviceEmail;
	}

	public String getTf_serviceHomepage() {
	return tf_serviceHomepage;
	}

	public void setTf_serviceHomepage(String tf_serviceHomepage) {
	this.tf_serviceHomepage = tf_serviceHomepage;
	}

	public String getTf_serviceQQ() {
	return tf_serviceQQ;
	}

	public void setTf_serviceQQ(String tf_serviceQQ) {
	this.tf_serviceQQ = tf_serviceQQ;
	}

	public String getTf_serviceRemark() {
	return tf_serviceRemark;
	}

	public void setTf_serviceRemark(String tf_serviceRemark) {
	this.tf_serviceRemark = tf_serviceRemark;
	}

}
