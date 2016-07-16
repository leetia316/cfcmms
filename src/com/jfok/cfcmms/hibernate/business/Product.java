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
import com.jfok.cfcmms.util.annotation.TableDefine;;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 6030, title = "商品")
public class Product implements Serializable {

  @Id
  // 使用的hibernate 的自动增量，没有用数据库的自动增量型
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @FieldDefine(title = "序号", number = 10, fieldGroup = "商品基本信息", hidden = true)
  @Column(nullable = false)
  private Integer tf_productId;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_productClassId", nullable = false)
  @FieldDefine(title = "商品类别", number = 20, fieldGroup = "商品基本信息")
  private ProductClass tf_ProductClass;

  @FieldDefine(title = "商品名称", number = 30, nameField = true, fieldGroup = "商品基本信息")
  @Column(nullable = false, length = 50)
  private String tf_name;

  @FieldDefine(title = "产地", number = 40, fieldGroup = "商品基本信息")
  @Column(length = 20)
  private String tf_origin;

  @FieldDefine(title = "销售单价", number = 50, fieldGroup = "商品附加信息")
  private Double tf_unitPrice;

  @FieldDefine(title = "成本单价", number = 60, fieldGroup = "商品附加信息")
  private Double tf_costPrice;

  @FieldDefine(title = "备注", number = 190, fieldGroup = "商品附加信息")
  private String tf_remark;

  public Product() {

  }

  public Integer getTf_productId() {
    return tf_productId;
  }

  public void setTf_productId(Integer tf_productId) {
    this.tf_productId = tf_productId;
  }

  public ProductClass getTf_ProductClass() {
    return tf_ProductClass;
  }

  public void setTf_ProductClass(ProductClass tf_ProductClass) {
    this.tf_ProductClass = tf_ProductClass;
  }

  public String getTf_name() {
    return tf_name;
  }

  public void setTf_name(String tf_name) {
    this.tf_name = tf_name;
  }

  public String getTf_origin() {
    return tf_origin;
  }

  public void setTf_origin(String tf_origin) {
    this.tf_origin = tf_origin;
  }

  public Double getTf_unitPrice() {
    return tf_unitPrice;
  }

  public void setTf_unitPrice(Double tf_unitPrice) {
    this.tf_unitPrice = tf_unitPrice;
  }

  public Double getTf_costPrice() {
    return tf_costPrice;
  }

  public void setTf_costPrice(Double tf_costPrice) {
    this.tf_costPrice = tf_costPrice;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

}
