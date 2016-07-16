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
@TableDefine(group = "销售系统", id = 6050, title = "订单明细")
public class OrdersDetail implements Serializable {

	@Id
	// 使用的hibernate 的自动增量，没有用数据库的自动增量型
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号", number = 10, fieldGroup = "订单明细信息", hidden = true)
	@Column(nullable = false)
	private Integer tf_ordersDetailId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_ordersId", nullable = false)
	@FieldDefine(title = "订单", number = 20, fieldGroup = "订单明细信息")
	private Orders tf_Orders;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_productId", nullable = false)
	@FieldDefine(title = "商品", number = 30, fieldGroup = "订单明细信息")
	private Product tf_Product;

	@FieldDefine(title = "明细描述", number = 40, nameField = true, fieldGroup = "订单明细信息")
	@Column(nullable = false, length = 50)
	private String tf_name;

	@FieldDefine(title = "数量", number = 50, fieldGroup = "订单明细信息")
	@Column(nullable = false)
	private Integer tf_number;

	@FieldDefine(title = "单价", number = 60, fieldGroup = "订单明细信息")
	@Column(nullable = false)
	private Double tf_unitPrice;

	@FieldDefine(title = "金额", number = 70, fieldGroup = "订单明细信息")
	@Column(updatable = false, insertable = false)
	private Double tf_subtotalPrice;

	@FieldDefine(title = "备注", number = 190, fieldGroup = "订单明细信息")
	private String tf_remark;

	public OrdersDetail() {

	}

	public Integer getTf_ordersDetailId() {
		return tf_ordersDetailId;
	}

	public void setTf_ordersDetailId(Integer tf_ordersDetailId) {
		this.tf_ordersDetailId = tf_ordersDetailId;
	}

	public Orders getTf_Orders() {
		return tf_Orders;
	}

	public void setTf_Orders(Orders tf_Orders) {
		this.tf_Orders = tf_Orders;
	}

	public Product getTf_Product() {
		return tf_Product;
	}

	public void setTf_Product(Product tf_Product) {
		this.tf_Product = tf_Product;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

	public Integer getTf_number() {
		return tf_number;
	}

	public void setTf_number(Integer tf_number) {
		this.tf_number = tf_number;
	}

	public Double getTf_unitPrice() {
		return tf_unitPrice;
	}

	public void setTf_unitPrice(Double tf_unitPrice) {
		this.tf_unitPrice = tf_unitPrice;
	}

	public Double getTf_subtotalPrice() {
		return tf_subtotalPrice;
	}

	public void setTf_subtotalPrice(Double tf_subtotalPrice) {
		this.tf_subtotalPrice = tf_subtotalPrice;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
