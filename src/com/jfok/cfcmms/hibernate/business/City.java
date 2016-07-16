package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.hibernate.superclass._ApproveAbstract;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 7012, title = "市")
public class City extends _ApproveAbstract implements Serializable {

	@Id
	@FieldDefine(title = "编码", number = 10, fieldGroup = "基本信息")
	@Column(nullable = false, length = 4)
	// 所有的数据表字段，全部用tf_开头，只是为了好分别表字段还是普通bean字段而己
	private String tf_cityId;

	// manyToOne 定义了此模块的一个父模块。不用在Province定义oneToMany了,这里的父子关系是双向的。
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_provinceId", nullable = false)
	@FieldDefine(title = "省份", number = 20, fieldGroup = "基本信息")
	private Province tf_Province;

	@FieldDefine(title = "名称", number = 30, nameField = true, fieldGroup = "基本信息")
	@Column(nullable = false, length = 50, unique = true)
	private String tf_name;

	@FieldDefine(title = "邮政编码", number = 40, fieldGroup = "附加信息")
	@Column(length = 6)
	private String tf_postNumber;

	@FieldDefine(title = "电话区号", number = 50, fieldGroup = "附加信息")
	@Column(length = 6)
	private String tf_telHead;

	// 此字段无实际意义，只是为了某些功能的展示所用
	@FieldDefine(title = "金额属性", number = 70, fieldGroup = "附加信息", allowSummary = true, isMonetary = true)
	private Double tf_money = 0.0;

	@FieldDefine(title = "备注", number = 190, fieldGroup = "附加信息")
	private String tf_remark;

	public City() {

	}

	public String getTf_cityId() {
	return tf_cityId;
	}

	public void setTf_cityId(String tf_cityId) {
	this.tf_cityId = tf_cityId;
	}

	public Province getTf_Province() {
	return tf_Province;
	}

	public void setTf_Province(Province tf_Province) {
	this.tf_Province = tf_Province;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public String getTf_postNumber() {
	return tf_postNumber;
	}

	public void setTf_postNumber(String tf_postNumber) {
	this.tf_postNumber = tf_postNumber;
	}

	public String getTf_telHead() {
	return tf_telHead;
	}

	public void setTf_telHead(String tf_telHead) {
	this.tf_telHead = tf_telHead;
	}

	public Double getTf_money() {
	return tf_money;
	}

	public void setTf_money(Double tf_money) {
	this.tf_money = tf_money;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
