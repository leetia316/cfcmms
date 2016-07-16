package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._AuditingAbstract;

@Entity
@SuppressWarnings("serial")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "销售系统", id = 7010, title = "省份")
public class Province extends _AuditingAbstract implements Serializable {

  @Id
  @FieldDefine(title = "编码", number = 10, fieldGroup = "基本信息")
  @Column(nullable = false, length = 10)
  private String tf_provinceId;

  @FieldDefine(title = "名称", number = 20, nameField = true, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50, unique = true)
  private String tf_name;

  @FieldDefine(title = "简称", number = 30, fieldGroup = "基本信息")
  @Column(nullable = false, length = 10, unique = true)
  private String tf_shortname;

  @FieldDefine(title = "所属区域", number = 40, fieldGroup = "基本信息", allowGroup = true, otherSetting = "formfield:{comboThisField:true},"
      + "field : {searchCondOrder :41 }")
  @Column(length = 50)
  private String tf_district;

  @FieldDefine(title = "面积", number = 50, fieldGroup = "附加信息", unitText = "平方公里", allowSummary = true)
  private Integer tf_area;

  @FieldDefine(title = "人口数量", number = 60, fieldGroup = "附加信息", unitText = "万", allowSummary = true)
  private Integer tf_numberOfPeople;

  @FieldDefine(title = "GPD", number = 70, fieldGroup = "附加信息", unitText = "亿", isMonetary = true, allowSummary = true)
  private Double tf_GDP;

  @Transient
  @FieldDefine(title = "人均GPD", number = 70, fieldGroup = "附加信息", unitText = "万元/人", divisor = "tf_GDP", denominator = "tf_numberOfPeople", tooltipTpl = "GDP：{tf_GDP}亿/人口数量：{tf_numberOfPeople}万人", allowSummary = true, formula = "   tf_GDP /   tf_numberOfPeople")
  @Column(insertable = false, updatable = false)
  private Double tf_averageGDP;

  @FieldDefine(title = "百分比", number = 80, fieldGroup = "附加信息")
  private Double tf_percent;

  @FieldDefine(title = "是否自治区", number = 90, fieldGroup = "附加信息")
  private Boolean tf_isMunicipality;

  @FieldDefine(title = "附加日期", number = 100, fieldGroup = "附加信息")
  private Date tf_createDate;

  @FieldDefine(title = "顺序号", number = 110, fieldGroup = "附加信息")
  private Integer tf_orderId;


  @FieldDefine(title = "地图", number = 110, fieldGroup = "附加信息")
  private Blob tf_map;
  
  @FieldDefine(title = "备注", number = 190, fieldGroup = "附加信息")
  private String tf_remark;

  public Province() {

  }

  public Integer getTf_orderId() {
    return tf_orderId;
  }

  public void setTf_orderId(Integer tf_orderId) {
    this.tf_orderId = tf_orderId;
  }

  public String getTf_provinceId() {
    return tf_provinceId;
  }

  public void setTf_provinceId(String tf_provinceId) {
    this.tf_provinceId = tf_provinceId;
  }

  public String getTf_name() {
    return tf_name;
  }

  public void setTf_name(String tf_name) {
    this.tf_name = tf_name;
  }

  public String getTf_shortname() {
    return tf_shortname;
  }

  public void setTf_shortname(String tf_shortname) {
    this.tf_shortname = tf_shortname;
  }

  public String getTf_district() {
    return tf_district;
  }

  public void setTf_district(String tf_district) {
    this.tf_district = tf_district;
  }

  public Integer getTf_area() {
    return tf_area;
  }

  public void setTf_area(Integer tf_area) {
    this.tf_area = tf_area;
  }

  public Integer getTf_numberOfPeople() {
    return tf_numberOfPeople;
  }

  public void setTf_numberOfPeople(Integer tf_numberOfPeople) {
    this.tf_numberOfPeople = tf_numberOfPeople;
  }

  public Double getTf_GDP() {
    return tf_GDP;
  }

  public void setTf_GDP(Double tf_GDP) {
    this.tf_GDP = tf_GDP;
  }

  public Double getTf_percent() {
    return tf_percent;
  }

  public void setTf_percent(Double tf_percent) {
    this.tf_percent = tf_percent;
  }

  public Boolean getTf_isMunicipality() {
    return tf_isMunicipality;
  }

  public void setTf_isMunicipality(Boolean tf_isMunicipality) {
    this.tf_isMunicipality = tf_isMunicipality;
  }

  public Date getTf_createDate() {
    return tf_createDate;
  }

  public void setTf_createDate(Date tf_createDate) {
    this.tf_createDate = tf_createDate;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public Double getTf_averageGDP() {
    return this.tf_averageGDP;
  }

  public void setTf_averageGDP(Double tf_averageGDP) {
    this.tf_averageGDP = tf_numberOfPeople > 0 ? tf_GDP / tf_numberOfPeople : 0;
    ;
  }

  public Blob getTf_map() {
    return tf_map;
  }

  public void setTf_map(Blob tf_map) {
    this.tf_map = tf_map;
  }

}
