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
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 6060, title = "收款明细")
public class IncomeDetail implements Serializable {

	@Id
	// 使用的hibernate 的自动增量，没有用数据库的自动增量型
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10, fieldGroup = "收款明细信息", hidden = true)
	@Column(nullable = false)
	private Integer tf_incomeDetailId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_ordersId", nullable = false)
	@FieldDefine(title = "订单", number = 20, fieldGroup = "收款明细信息")
	private Orders tf_Orders;

	@FieldDefine(title = "收款明细描述", number = 30, nameField = true, fieldGroup = "收款明细信息")
	@Column(nullable = false, length = 50)
	private String tf_name;

	@FieldDefine(title = "收款日期", number = 40, fieldGroup = "收款明细信息")
	@Column(nullable = false)
	private Date tf_date;

	@FieldDefine(title = "收款金额", number = 50, fieldGroup = "收款明细信息")
	@Column(nullable = false)
	private Double tf_imcomePrice = 0.0;

	public IncomeDetail() {

	}

	public Integer getTf_incomeDetailId() {
		return tf_incomeDetailId;
	}

	public void setTf_incomeDetailId(Integer tf_incomeDetailId) {
		this.tf_incomeDetailId = tf_incomeDetailId;
	}

	public Orders getTf_Orders() {
		return tf_Orders;
	}

	public void setTf_Orders(Orders tf_Orders) {
		this.tf_Orders = tf_Orders;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public Date getTf_date() {
		return tf_date;
	}

	public void setTf_date(Date tf_date) {
		this.tf_date = tf_date;
	}

	public Double getTf_imcomePrice() {
		return tf_imcomePrice;
	}

	public void setTf_imcomePrice(Double tf_imcomePrice) {
		this.tf_imcomePrice = tf_imcomePrice;
	}

}
