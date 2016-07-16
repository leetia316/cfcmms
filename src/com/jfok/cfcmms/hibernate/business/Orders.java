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
@TableDefine(group = "销售系统", id = 6040, title = "订单")
public class Orders implements Serializable {

  @Id
  // 使用的hibernate 的自动增量，没有用数据库的自动增量型
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @FieldDefine(title = "序号", number = 10, fieldGroup = "订单信息", hidden = true)
  @Column(nullable = false)
  private Integer tf_ordersId;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_customerId", nullable = false)
  @FieldDefine(title = "客户单位", number = 20, fieldGroup = "订单信息")
  private Customer tf_Customer;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_salesmanId", nullable = false)
  @FieldDefine(title = "业务员", number = 30, fieldGroup = "订单信息")
  private Salesman tf_Salesman;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_storageId", nullable = true)
  @FieldDefine(title = "商品仓库", number = 35, fieldGroup = "订单信息")
  private Storage tf_Storage;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_fromCityId", nullable = true)
  @FieldDefine(title = "始发地市", number = 35, fieldGroup = "订单信息")
  private City tf_FromCity;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_toCityId", nullable = true)
  @FieldDefine(title = "目的地市", number = 35, fieldGroup = "订单信息")
  private City tf_ToCity;

  @FieldDefine(title = "订单号", number = 40, nameField = true, fieldGroup = "订单信息")
  @Column(nullable = false, length = 20)
  private String tf_ordersNumber;

  @FieldDefine(title = "订单日期", number = 50, fieldGroup = "订单信息")
  @Column(nullable = false)
  private Date tf_date;

  @FieldDefine(title = "订单年份", number = 55, fieldGroup = "订单信息")
  @Column(updatable = false, insertable = false, length = 5)
  private String tf_year;

  @FieldDefine(title = "是否完成", number = 60, fieldGroup = "订单信息")
  @Column(nullable = false)
  private Boolean tf_finished = false;

  @FieldDefine(title = "备注", number = 190, fieldGroup = "商品附加信息")
  private String tf_remark;

  public Orders() {

  }

  public Integer getTf_ordersId() {
    return tf_ordersId;
  }

  public void setTf_ordersId(Integer tf_ordersId) {
    this.tf_ordersId = tf_ordersId;
  }

  public Customer getTf_Customer() {
    return tf_Customer;
  }

  public void setTf_Customer(Customer tf_Customer) {
    this.tf_Customer = tf_Customer;
  }

  public Salesman getTf_Salesman() {
    return tf_Salesman;
  }

  public void setTf_Salesman(Salesman tf_Salesman) {
    this.tf_Salesman = tf_Salesman;
  }

  public String getTf_ordersNumber() {
    return tf_ordersNumber;
  }

  public void setTf_ordersNumber(String tf_ordersNumber) {
    this.tf_ordersNumber = tf_ordersNumber;
  }

  public Date getTf_date() {
    return tf_date;
  }

  public void setTf_date(Date tf_date) {
    this.tf_date = tf_date;
  }

  public String getTf_year() {
    return tf_year;
  }

  public void setTf_year(String tf_year) {
    this.tf_year = tf_year;
  }

  public Boolean getTf_finished() {
    return tf_finished;
  }

  public void setTf_finished(Boolean tf_finished) {
    this.tf_finished = tf_finished;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public Storage getTf_Storage() {
    return tf_Storage;
  }

  public void setTf_Storage(Storage tf_Storage) {
    this.tf_Storage = tf_Storage;
  }

  public City getTf_FromCity() {
    return tf_FromCity;
  }

  public void setTf_FromCity(City tf_FromCity) {
    this.tf_FromCity = tf_FromCity;
  }

  public City getTf_ToCity() {
    return tf_ToCity;
  }

  public void setTf_ToCity(City tf_ToCity) {
    this.tf_ToCity = tf_ToCity;
  }

}
