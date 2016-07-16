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
@TableDefine(group = "销售系统", id = 7020, title = "商品仓库")
public class Storage implements Serializable {

  @Id
  // 使用的hibernate 的自动增量，没有用数据库的自动增量型
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  // 这是客户单位的序号主键，隐藏不要显示
  @FieldDefine(title = "序号", number = 10, fieldGroup = "基本信息", hidden = true)
  @Column(nullable = false)
  private Integer tf_storageId;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "tf_cityId", nullable = false)
  @FieldDefine(title = "市", number = 20, fieldGroup = "基本信息")
  private City tf_City;

  @FieldDefine(title = "仓库名称", number = 30, nameField = true, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50)
  private String tf_name;

  @FieldDefine(title = "地址", number = 40, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_address;

  @FieldDefine(title = "联系人", number = 50, fieldGroup = "附加信息")
  @Column(length = 10)
  private String tf_linkman;

  @FieldDefine(title = "联系电话", number = 60, fieldGroup = "附加信息")
  @Column(length = 20)
  private String tf_linkmanTel;

  @FieldDefine(title = "备注", number = 190, fieldGroup = "附加信息")
  @Column(length = 20)
  private String tf_remark;

  public Storage() {

  }

  public Integer getTf_storageId() {
    return tf_storageId;
  }

  public void setTf_storageId(Integer tf_storageId) {
    this.tf_storageId = tf_storageId;
  }

  public City getTf_City() {
    return tf_City;
  }

  public void setTf_City(City tf_City) {
    this.tf_City = tf_City;
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

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

}
