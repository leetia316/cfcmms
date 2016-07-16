package com.jfok.cfcmms.hibernate.system.report;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.codehaus.jackson.annotate.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;

/**
 * 一个报表或综合查询的定义
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9052, title = "综合查询")
public class _Report extends _InputInfoAbstract implements _IModuleControlInterface, Serializable {

  @Id
  @FieldDefine(title = "ID号", hidden = true, number = 10)
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  private Integer tf_reportId;

  @FieldDefine(title = "顺序号", remark = "按顺序号显示在报表菜单中", number = 20)
  @Column(nullable = false)
  private Integer tf_orderId;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_reportGroupId", nullable = false)
  @FieldDefine(title = "综合查询分组", number = 30)
  private _ReportGroup tf_ReportGroup;

  @FieldDefine(title = "综合查询名称", number = 40, nameField = true)
  @Column(length = 50)
  private String tf_title;

  @FieldDefine(title = "类型", number = 50)
  @Column(length = 50)
  private String tf_type;

  @FieldDefine(title = "图标文件名", remark = "图标放置于/images/module/目录下", number = 60)
  @Column(length = 50)
  private String tf_iconURL;

  @FieldDefine(title = "系统方案", number = 70)
  private Boolean tf_isSystem;

  @FieldDefine(title = "分组定义", number = 80)
  @Column(length = 255)
  private String tf_groupDefine;

  @FieldDefine(title = "分组显示明细", number = 90)
  private Boolean tf_isShowDetail;

  @FieldDefine(title = "基准模块", number = 100)
  @Column(length = 50, nullable = false)
  private String tf_baseModuleName;

  @FieldDefine(title = "显示总计", number = 90)
  private Boolean tf_isShowTotal;

  @FieldDefine(title = "备注", number = 800)
  private String tf_remark;

  @OneToMany(mappedBy = "tf_Report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  // @JoinColumn(name = "tf_reportId")
  @OrderBy("tf_orderId")
  private List<_ReportFieldGroup> reportFieldGroups;

  public _Report() {

  }

  public Integer getTf_reportId() {
    return tf_reportId;
  }

  public void setTf_reportId(Integer tf_reportId) {
    this.tf_reportId = tf_reportId;
  }

  public Integer getTf_orderId() {
    return tf_orderId;
  }

  public void setTf_orderId(Integer tf_orderId) {
    this.tf_orderId = tf_orderId;
  }

  public _ReportGroup getTf_ReportGroup() {
    return tf_ReportGroup;
  }

  public void setTf_ReportGroup(_ReportGroup tf_ReportGroup) {
    this.tf_ReportGroup = tf_ReportGroup;
  }

  public String getTf_title() {
    return tf_title;
  }

  public void setTf_title(String tf_title) {
    this.tf_title = tf_title;
  }

  public String getTf_type() {
    return tf_type;
  }

  public void setTf_type(String tf_type) {
    this.tf_type = tf_type;
  }

  public String getTf_iconURL() {
    return tf_iconURL;
  }

  public void setTf_iconURL(String tf_iconURL) {
    this.tf_iconURL = tf_iconURL;
  }

  public Boolean getTf_isSystem() {
    return tf_isSystem == null ? false : tf_isSystem;
  }

  public void setTf_isSystem(Boolean tf_isSystem) {
    this.tf_isSystem = tf_isSystem;
  }

  public String getTf_groupDefine() {
    return tf_groupDefine;
  }

  public void setTf_groupDefine(String tf_groupDefine) {
    this.tf_groupDefine = tf_groupDefine;
  }

  public Boolean getTf_isShowDetail() {
    return tf_isShowDetail == null ? false : tf_isShowDetail;
  }

  public void setTf_isShowDetail(Boolean tf_isShowDetail) {
    this.tf_isShowDetail = tf_isShowDetail;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public List<_ReportFieldGroup> getReportFieldGroups() {
    return reportFieldGroups;
  }

  public void setReportFieldGroups(List<_ReportFieldGroup> reportFieldGroups) {
    this.reportFieldGroups = reportFieldGroups;
  }

  public String getTf_baseModuleName() {
    return tf_baseModuleName;
  }

  public void setTf_baseModuleName(String tf_baseModuleName) {
    this.tf_baseModuleName = tf_baseModuleName;
  }

  public Boolean getTf_isShowTotal() {
    return tf_isShowTotal == null ? false : tf_isShowTotal;
  }

  public void setTf_isShowTotal(Boolean tf_isShowTotal) {
    this.tf_isShowTotal = tf_isShowTotal;
  }

}
