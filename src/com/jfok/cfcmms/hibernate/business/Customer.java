package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
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
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 6010, title = "客户单位")
public class Customer implements Serializable {

	@Id
	// 使用的hibernate 的自动增量，没有用数据库的自动增量型
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	// 这是客户单位的序号主键，隐藏不要显示
	@FieldDefine(title = "序号", number = 10, fieldGroup = "基本信息", hidden = true)
	@Column(nullable = false)
	private Integer tf_customerId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_cityId", nullable = false)
	@FieldDefine(title = "市", number = 20, fieldGroup = "基本信息")
	private City tf_City;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_tradeId", nullable = false)
	@FieldDefine(title = "行业", number = 30, fieldGroup = "基本信息")
	private Trade tf_Trade;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_rateId", nullable = false)
	@FieldDefine(title = "等级", number = 40, fieldGroup = "基本信息")
	private Rate tf_Rate;

	@FieldDefine(title = "客户名称", number = 50, nameField = true, fieldGroup = "基本信息", remark = "请输入该单位公章上的全称")
	@Column(nullable = false, length = 50)
	private String tf_name;

	@FieldDefine(title = "单位地址", number = 60, fieldGroup = "附加信息")
	@Column(length = 50)
	private String tf_address;

	@FieldDefine(title = "联系人", number = 70, fieldGroup = "附加信息")
	@Column(length = 10)
	private String tf_linkman;

	@FieldDefine(title = "联系电话", number = 80, fieldGroup = "附加信息")
	@Column(length = 20)
	private String tf_linkmanTel;

	@FieldDefine(title = "税号", number = 90, fieldGroup = "附加信息")
	@Column(length = 20)
	private String tf_taxId;

	@FieldDefine(title = "备注", number = 190, fieldGroup = "附加信息")
	@Column(length = 20)
	private String tf_remark;

	public Customer() {

	}

	public Integer getTf_customerId() {
		return tf_customerId;
	}

	public void setTf_customerId(Integer tf_customerId) {
		this.tf_customerId = tf_customerId;
	}

	public City getTf_City() {
		return tf_City;
	}

	public void setTf_City(City tf_City) {
		this.tf_City = tf_City;
	}

	public Trade getTf_Trade() {
		return tf_Trade;
	}

	public void setTf_Trade(Trade tf_Trade) {
		this.tf_Trade = tf_Trade;
	}

	public Rate getTf_Rate() {
		return tf_Rate;
	}

	public void setTf_Rate(Rate tf_Rate) {
		this.tf_Rate = tf_Rate;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public String getTf_address() {
		return tf_address;
	}

	public void setTf_address(String tf_address) {
		this.tf_address = tf_address;
	}

	public String getTf_linkman() {
		return tf_linkman;
	}

	public void setTf_linkman(String tf_linkman) {
		this.tf_linkman = tf_linkman;
	}

	public String getTf_linkmanTel() {
		return tf_linkmanTel;
	}

	public void setTf_linkmanTel(String tf_linkmanTel) {
		this.tf_linkmanTel = tf_linkmanTel;
	}

	public String getTf_taxId() {
		return tf_taxId;
	}

	public void setTf_taxId(String tf_taxId) {
		this.tf_taxId = tf_taxId;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
