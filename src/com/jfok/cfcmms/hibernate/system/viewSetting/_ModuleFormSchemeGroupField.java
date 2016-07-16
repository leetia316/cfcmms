package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9909, title = "模块Form字段", shortName = "Form字段")
public class _ModuleFormSchemeGroupField implements _IModuleControlInterface, Serializable {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @FieldDefine(title = "ID号", hidden = true, number = 10)
  private Integer tf_formFieldId;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "tf_formGroupId", nullable = false)
  @FieldDefine(title = "模块Form字段分组", number = 20)
  private _ModuleFormSchemeGroup tf_ModuleFormSchemeGroup;

  @FieldDefine(title = "顺序号", number = 30)
  @Column(nullable = false)
  private Integer tf_formFieldOrder;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_fieldId", nullable = false)
  @FieldDefine(title = "模块字段", nameField = true, number = 40)
  private _ModuleField tf_ModuleField;

  // 这个字段用于将数据转成json传到前台时候，加入moduleField的id
  @Column(insertable = false, updatable = false)
  private Integer tf_fieldId;

  @FieldDefine(title = "聚合类型", number = 45)
  @Column(length = 20)
  private String tf_aggregate;

  @FieldDefine(title = "宽度", number = 50)
  private Integer tf_width;

  @FieldDefine(title = "栏数", number = 60)
  private Integer tf_colspan;

  @FieldDefine(title = "行数", number = 61)
  private Integer tf_rowspan;

  @FieldDefine(title = "结束行", number = 70)
  private Boolean tf_isEndRow;

  @FieldDefine(title = "附加设置", number = 80)
  private String tf_otherSetting;

  public _ModuleFormSchemeGroupField() {

  }

  public _ModuleFormSchemeGroupField(Integer formFieldId) {
    this.tf_formFieldId = formFieldId;
  }

  public Integer getTf_formFieldId() {
    return tf_formFieldId;
  }

  public void setTf_formFieldId(Integer tf_formFieldId) {
    this.tf_formFieldId = tf_formFieldId;
  }

  public Integer getTf_formFieldOrder() {
    return tf_formFieldOrder;
  }

  public void setTf_formFieldOrder(Integer tf_formFieldOrder) {
    this.tf_formFieldOrder = tf_formFieldOrder;
  }

  public Integer getTf_width() {
    return tf_width == null ? 0 : tf_width;
  }

  public void setTf_width(Integer tf_width) {
    this.tf_width = tf_width;
  }

  public Integer getTf_colspan() {
    return tf_colspan == null ? 0 : tf_colspan;
  }

  public void setTf_colspan(Integer tf_colspan) {
    this.tf_colspan = tf_colspan;
  }

  public Boolean getTf_isEndRow() {
    return tf_isEndRow == null ? false : tf_isEndRow;
  }

  public void setTf_isEndRow(Boolean tf_isEndRow) {
    this.tf_isEndRow = tf_isEndRow;
  }

  public String getTf_otherSetting() {
    return tf_otherSetting == null ? "" : tf_otherSetting;
  }

  public void setTf_otherSetting(String tf_otherSetting) {
    this.tf_otherSetting = tf_otherSetting;
  }

  public _ModuleFormSchemeGroup getTf_ModuleFormSchemeGroup() {
    return tf_ModuleFormSchemeGroup;
  }

  public void setTf_ModuleFormSchemeGroup(_ModuleFormSchemeGroup tf_ModuleFormSchemeGroup) {
    this.tf_ModuleFormSchemeGroup = tf_ModuleFormSchemeGroup;
  }

  public _ModuleField getTf_ModuleField() {
    return tf_ModuleField;
  }

  public void setTf_ModuleField(_ModuleField tf_ModuleField) {
    this.tf_ModuleField = tf_ModuleField;
  }

  public Integer getTf_fieldId() {
    return tf_fieldId;
  }

  public void setTf_fieldId(Integer tf_fieldId) {
    this.tf_fieldId = tf_fieldId;
  }

  public String getTf_aggregate() {
    return tf_aggregate;
  }

  public void setTf_aggregate(String tf_aggregate) {
    this.tf_aggregate = tf_aggregate;
  }

  public Integer getTf_rowspan() {
    return tf_rowspan;
  }

  public void setTf_rowspan(Integer tf_rowspan) {
    this.tf_rowspan = tf_rowspan;
  }

}
