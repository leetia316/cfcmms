package com.jfok.cfcmms.hibernate.system.viewSetting;

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
import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)

@TableDefine(group = "系统模块", id = 9908, title = "模块Form字段分组", shortName = "Form字段分组")
public class _ModuleFormSchemeGroup implements _IModuleControlInterface, Serializable {

  public static final String FORMGROUPID = "tf_formGroupId";

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @FieldDefine(title = "ID号", number = 10)
  private Integer tf_formGroupId;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_formSchemeId", nullable = false)
  @FieldDefine(title = "模块Form方案", number = 20)
  private _ModuleFormScheme tf_ModuleFormScheme;

  @FieldDefine(title = "顺序号", number = 30)
  @Column(nullable = false)
  private Integer tf_formGroupOrder;

  @FieldDefine(title = "分组名称", nameField = true, number = 40)
  @Column(nullable = false, length = 50)
  private String tf_formGroupName;

  @FieldDefine(title = "显示方式", number = 50)
  @Column(length = 50)
  private String tf_displayMode;

  @FieldDefine(title = "表格Table", number = 55)
  private Boolean tf_isTable;

  @FieldDefine(title = "每页宽度", number = 56, remark = "设置每列的宽度，可以为像素或者百分比")
  @Column(length = 100)
  private String tf_widths;

  @FieldDefine(title = "分离label", number = 57, remark = "将label和text放在二个单元格里")
  private Boolean tf_isSeparateLabel;

  @FieldDefine(title = "分栏数", number = 60)
  private Integer tf_numCols;

  @FieldDefine(title = "审核组", number = 70)
  private Boolean tf_auditingGroup;

  @FieldDefine(title = "审批组", number = 80)
  private Boolean tf_approveGroup;

  @FieldDefine(title = "可折叠", number = 90)
  private Boolean tf_collapsible;

  @FieldDefine(title = "默认折叠", number = 100)
  private Boolean tf_collapsed;

  @FieldDefine(title = "子模块名称", remark = "设置此项，则在此组中显示该子模块", number = 110)
  @Column(length = 50)
  private String tf_subModuleName;

  @FieldDefine(title = "其他设置", number = 120)
  private String tf_otherSetting;

  @OneToMany(targetEntity = _ModuleFormSchemeGroupField.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_formGroupId")
  @OrderBy("tf_formFieldOrder")
  private List<_ModuleFormSchemeGroupField> tf_groupFields;

  public _ModuleFormSchemeGroup() {

  }

  public _ModuleFormSchemeGroup(Integer tf_formGroupId) {
    this.tf_formGroupId = tf_formGroupId;
  }

  public Integer getTf_formGroupId() {
    return tf_formGroupId;
  }

  public void setTf_formGroupId(Integer tf_formGroupId) {
    this.tf_formGroupId = tf_formGroupId;
  }

  public Integer getTf_formGroupOrder() {
    return tf_formGroupOrder;
  }

  public void setTf_formGroupOrder(Integer tf_formGroupOrder) {
    this.tf_formGroupOrder = tf_formGroupOrder;
  }

  public String getTf_formGroupName() {
    return tf_formGroupName;
  }

  public void setTf_formGroupName(String tf_formGroupName) {
    this.tf_formGroupName = tf_formGroupName;
  }

  public Integer getTf_numCols() {
    return tf_numCols;
  }

  public void setTf_numCols(Integer tf_numCols) {
    this.tf_numCols = tf_numCols;
  }

  // public Boolean getTf_hiddenDisplay() {
  // return tf_hiddenDisplay == null ? false : tf_hiddenDisplay;
  // }
  //
  // public void setTf_hiddenDisplay(Boolean tf_hiddenDisplay) {
  // this.tf_hiddenDisplay = tf_hiddenDisplay;
  // }
  //
  // public Boolean getTf_hiddenOnNew() {
  // return tf_hiddenOnNew == null ? false : tf_hiddenOnNew;
  // }
  //
  // public void setTf_hiddenOnNew(Boolean tf_hiddenOnNew) {
  // this.tf_hiddenOnNew = tf_hiddenOnNew;
  // }
  //
  // public Boolean getTf_hiddenOnEdit() {
  // return tf_hiddenOnEdit == null ? false : tf_hiddenOnEdit;
  // }
  //
  // public void setTf_hiddenOnEdit(Boolean tf_hiddenOnEdit) {
  // this.tf_hiddenOnEdit = tf_hiddenOnEdit;
  // }
  //
  // public Boolean getTf_readonlyOnNew() {
  // return tf_readonlyOnNew == null ? false : tf_readonlyOnNew;
  // }
  //
  // public void setTf_readonlyOnNew(Boolean tf_readonlyOnNew) {
  // this.tf_readonlyOnNew = tf_readonlyOnNew;
  // }
  //
  // public Boolean getTf_readonlyOnEdit() {
  // return tf_readonlyOnEdit == null ? false : tf_readonlyOnEdit;
  // }
  //
  // public void setTf_readonlyOnEdit(Boolean tf_readonlyOnEdit) {
  // this.tf_readonlyOnEdit = tf_readonlyOnEdit;
  // }

  public Boolean getTf_auditingGroup() {
    return tf_auditingGroup == null ? false : tf_auditingGroup;
  }

  public void setTf_auditingGroup(Boolean tf_auditingGroup) {
    this.tf_auditingGroup = tf_auditingGroup;
  }

  public Boolean getTf_approveGroup() {
    return tf_approveGroup == null ? false : tf_approveGroup;
  }

  public void setTf_approveGroup(Boolean tf_approveGroup) {
    this.tf_approveGroup = tf_approveGroup;
  }

  public String getTf_otherSetting() {
    return tf_otherSetting;
  }

  public void setTf_otherSetting(String tf_otherSetting) {
    this.tf_otherSetting = tf_otherSetting;
  }

  public String getTf_displayMode() {
    return tf_displayMode == null ? "" : tf_displayMode;
  }

  public void setTf_displayMode(String tf_displayMode) {
    this.tf_displayMode = tf_displayMode;
  }

  public _ModuleFormScheme getTf_ModuleFormScheme() {
    return tf_ModuleFormScheme;
  }

  public void setTf_ModuleFormScheme(_ModuleFormScheme tf_ModuleFormScheme) {
    this.tf_ModuleFormScheme = tf_ModuleFormScheme;
  }

  public Boolean getTf_collapsible() {
    return tf_collapsible == null ? false : tf_collapsible;
  }

  public void setTf_collapsible(Boolean tf_collapsible) {
    this.tf_collapsible = tf_collapsible;
  }

  public Boolean getTf_collapsed() {
    return tf_collapsed == null ? false : tf_collapsed;
  }

  public void setTf_collapsed(Boolean tf_collapsed) {
    this.tf_collapsed = tf_collapsed;
  }

  public String getTf_subModuleName() {
    return tf_subModuleName;
  }

  public void setTf_subModuleName(String tf_subModuleName) {
    this.tf_subModuleName = tf_subModuleName;
  }

  public List<_ModuleFormSchemeGroupField> getTf_groupFields() {
    return tf_groupFields;
  }

  public void setTf_groupFields(List<_ModuleFormSchemeGroupField> tf_groupFields) {
    this.tf_groupFields = tf_groupFields;
  }

  public Boolean getTf_isTable() {
    return tf_isTable;
  }

  public void setTf_isTable(Boolean tf_isTable) {
    this.tf_isTable = tf_isTable;
  }

  public String getTf_widths() {
    return tf_widths;
  }

  public void setTf_widths(String tf_widths) {
    this.tf_widths = tf_widths;
  }

  public Boolean getTf_isSeparateLabel() {
    return tf_isSeparateLabel;
  }

  public void setTf_isSeparateLabel(Boolean tf_isSeparateLabel) {
    this.tf_isSeparateLabel = tf_isSeparateLabel;
  }

}
