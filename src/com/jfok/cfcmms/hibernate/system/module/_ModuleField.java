package com.jfok.cfcmms.hibernate.system.module;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.setting._PropertyType;
import com.jfok.cfcmms.share.FieldType;
import com.jfok.cfcmms.share.module.ModuleConstants;

/**
 * 每个模块的各个字段
 * 
 * @author jfok
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9903, title = "模块字段")
@NamedQuery(name = "FindModuleFieldWithModuleId", query = "from _ModuleField where tf_moduleId = :tf_moduleId order by tf_fieldId")
public class _ModuleField implements Serializable, _IModuleControlInterface {

  public static final String FIELDID = "tf_fieldId";
  public static final String FIELDNAME = "tf_fieldName";
  public static final String MANYTOONE = "ManyToOne";
  public static final String ONETOONE = "OneToOne";
  public static final String ONETOMANY = "OneToMany";
  public static final String MANYTOMANY = "ManyToMany";

  /////// 字段的基本信息
  @Id
  @FieldDefine(title = "ID号", number = 10, fieldGroup = "基本信息")
  @Column(nullable = false)
  private Integer tf_fieldId;

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_moduleId", nullable = false)
  @FieldDefine(title = "所属模块", number = 20, fieldGroup = "基本信息")
  private _Module tf_Module;

  @JsonIgnore
  @FieldDefine(title = "顺序号", number = 30, fieldGroup = "基本信息")
  private Integer tf_fieldOrder;

  @FieldDefine(title = "字段内容", number = 40, nameField = true, fieldGroup = "基本信息")
  @Column(length = 50, nullable = false)
  private String tf_title;

  @FieldDefine(title = "字段名", number = 50, fieldGroup = "基本信息")
  @Column(length = 50, nullable = false)
  private String tf_fieldName;

  @FieldDefine(title = "类型", number = 60, fieldGroup = "基本信息")
  @Column(length = 50, nullable = false)
  private String tf_fieldType;

  @JsonProperty("l")
  @FieldDefine(title = "长度", number = 70, fieldGroup = "基本信息", minValue = 0)
  private Integer tf_fieldLen;

  @FieldDefine(title = "小数位数", number = 80, fieldGroup = "基本信息", minValue = 0, maxValue = 6)
  private Integer tf_digitsLen;

  @FieldDefine(title = "字段分组", number = 90, fieldGroup = "基本信息")
  @Column(length = 50, nullable = false)
  private String tf_fieldGroup;

  // 字段的关联类型 ，ManyToOne，OneToOne，OneToMany, ManyToMany
  @JsonIgnore
  @FieldDefine(title = "关联类型", number = 100, fieldGroup = "基本信息")
  @Column(length = 20)
  private String tf_fieldRelation;

  @FieldDefine(title = "关联表名", number = 102, fieldGroup = "基本信息", remark = "ManyToMany中的关联表名")
  @Column(length = 50)
  private String tf_joinTable;

  /**
   * 如果是普通字段，设置此项，表示此字段在数据库中的实际字段名称。
   * 
   * 如果是ManyToOne字段，设置此字段表示，与父模块关联的实际字段名。默认是和父模块的primary key 相同的字段名
   * 
   */
  @JsonIgnore
  @FieldDefine(title = "表字段实名", remark = "数据表中的实际字段名", number = 110, fieldGroup = "基本信息")
  @Column(length = 50)
  private String tf_DBfieldName;

  /**
   * 字段公式中引用到的本模块字段前面请加三个空格。会替换成 tableas 。 如果这个字段只存在于字段列表中，在java
   * bean中并没有生成，那么可以加上别名，如： _tXXXX.tf_name
   * 
   * 其实字段公式中也能存放表字段实名
   * 
   */
  @JsonIgnore
  @FieldDefine(title = "字段公式", remark = "公式字段的具体内容", number = 120, fieldGroup = "基本信息")
  private String tf_DBformula;

  ///////////////////// 字段权限

  @FieldDefine(title = "禁用", number = 130, fieldGroup = "字段权限")
  private Boolean tf_isDisable;

  @FieldDefine(title = "隐藏", number = 140, fieldGroup = "字段权限")
  private Boolean tf_isHidden;

  @FieldDefine(title = "可新增", number = 150, fieldGroup = "字段权限")
  private Boolean tf_allowNew;

  @FieldDefine(title = "可修改", number = 160, fieldGroup = "字段权限")
  private Boolean tf_allowEdit;

  ////////////////////////////// 设置信息

  @JsonProperty("g")
  @FieldDefine(title = "可分组", number = 190, fieldGroup = "设置信息")
  private Boolean tf_allowGroup; // 是否允许分组

  @JsonProperty("s")
  @FieldDefine(title = "可小计", number = 200, fieldGroup = "设置信息")
  private Boolean tf_allowSummary; // 是否可以小计及总计

  @FieldDefine(title = "聚合", number = 210)
  private Boolean tf_allowAggregate; // 是否可以被父模块进行聚合统计

  @JsonIgnore
  @FieldDefine(title = "可导航", remark = "选中才可以在导航树中显示", number = 220, fieldGroup = "设置信息")
  private Boolean tf_showNavigatorTree;

  @JsonProperty("n")
  @FieldDefine(title = "新增选中", remark = "在新增一条记录时，是否必须在导航树中选择此字段的值", number = 230, fieldGroup = "设置信息")
  private Boolean tf_newNeedSelected;

  @JsonIgnore
  @FieldDefine(title = "Excel导入", remark = "Excel导入新增时加入此字段可新增", number = 240, fieldGroup = "设置信息")
  private Boolean tf_allowInsertExcel;

  @JsonIgnore
  @FieldDefine(title = "Excel修改", remark = "Excel修改后再导入时此字段可更新", number = 250, fieldGroup = "设置信息")
  private Boolean tf_allowEditExcel;

  @JsonProperty("cc")
  @FieldDefine(title = "图表项目", remark = "此字段可以作为图表分析中的一个项目", number = 260, fieldGroup = "设置信息")
  private Boolean tf_isChartCategory;

  @JsonProperty("cn")
  @FieldDefine(title = "图表数据", remark = "此字段可以作为图表分析中的一个数据", number = 270, fieldGroup = "设置信息")
  private Boolean tf_isChartNumeric;

  @FieldDefine(title = "字段附件", remark = "可以增加针对此字段的附件", number = 280, fieldGroup = "设置信息")
  private Boolean tf_haveAttachment;

  @FieldDefine(title = "选择数值单位", remark = "可以选择千,万,百万,亿为单位", number = 290, fieldGroup = "设置信息")
  private Boolean tf_isMonetary;

  //////////////////// 单字段验证

  @FieldDefine(title = "必填", number = 300, fieldGroup = "单字段验证")
  private Boolean tf_isRequired;

  @FieldDefine(title = "最大值", number = 310, fieldGroup = "单字段验证")
  private Integer tf_maxValue;

  @FieldDefine(title = "最小值", number = 320, fieldGroup = "单字段验证")
  private Integer tf_minValue;

  @FieldDefine(title = "正则验正表达式", number = 330, fieldGroup = "单字段验证")
  private String tf_regexValue;

  @FieldDefine(title = "vtype类型", number = 340, fieldGroup = "单字段验证")
  @Column(length = 50)
  private String tf_vtype;

  @FieldDefine(title = "js验证代码", number = 350, fieldGroup = "单字段验证")
  private String tf_jsValue;

  ///////////////////////////

  @FieldDefine(title = "计量单位", number = 400, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_unitText;

  @FieldDefine(title = "缺省值", number = 410, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_defaultValue;

  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_propertyTypeId")
  @FieldDefine(title = "字段列表属性", number = 420, fieldGroup = "附加信息")
  private _PropertyType tf_PropertyType;

  @FieldDefine(title = "字段列表值", number = 430, fieldGroup = "附加信息")
  private String tf_propertyValue;

  @JsonIgnore
  @FieldDefine(title = "百分比分子", number = 440, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_divisor;

  @JsonIgnore
  @FieldDefine(title = "百分比分母", number = 450, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_denominator;

  @FieldDefine(title = "提示信息定义", remark = "column中显示在此字段值上的提示信息,是tpl表达式", number = 460)
  private String tf_tooltipTpl;

  @FieldDefine(title = "其他设置", number = 470, fieldGroup = "附加信息")
  private String tf_otherSetting;

  @FieldDefine(title = "备注", number = 990, fieldGroup = "附加信息")
  private String tf_remark;

  /////////

  @FieldDefine(title = "model设置语句", number = 800, fieldGroup = "关联设置")
  private String tf_modelSet;

  @FieldDefine(title = "grid字段设置", number = 810, fieldGroup = "关联设置")
  private String tf_gridColumnSet;

  @FieldDefine(title = "form字段设置", number = 820, fieldGroup = "关联设置")
  private String tf_formFieldSet;

  @FieldDefine(title = "report字段设置", number = 830, fieldGroup = "关联设置")
  private String tf_reportSet;

  // 如果是一个manytoone的字段，那么显示实际ID的字段 _
  @Transient
  private String manytoone_IdName;

  // 如果是一个manytoone的字段，那么显示实际title的字段 _
  @Transient
  private String manytoone_TitleName;

  public _ModuleField() {

  }

  public _ModuleField(Integer tf_fieldId) {
    this.tf_fieldId = tf_fieldId;
  }

  @Override
  public String toString() {
    return "_ModuleField [tf_fieldId=" + tf_fieldId + ", tf_title=" + tf_title + ", tf_fieldName="
        + tf_fieldName + ", tf_fieldType=" + tf_fieldType + ", tf_fieldLen=" + tf_fieldLen + "]";
  }

  public Boolean hasDivisior_Denominator() {
    return (tf_divisor != null) && (tf_denominator != null) && (tf_divisor.length() > 0)
        && (tf_denominator.length() > 0);
  }

  // 如果这字段是从一个模块中选择一个，取得模块的ＩＤ
  // 从另外一个模块中选择一个字段，返回 moduieId , moduleName

  // public String getSelectModuleId() {
  // if (tf_comboSource != null && tf_comboSource.startsWith("Select:"))
  // return tf_comboSource == null ? null :
  // tf_comboSource.replaceAll("Select:", "");
  // else
  // return null;
  // }

  // 如果这字段是从一个模块中选择一个，取得模块的ＩＤ
  // 从另外一个模块中选择一个字段，返回 moduieId , moduleName

  // public String getMultSelectModuleId() {
  // if (tf_comboSource != null && tf_comboSource.startsWith("MultSelect:"))
  // return tf_comboSource == null ? null :
  // tf_comboSource.replaceAll("MultSelect:", "");
  // else
  // return null;
  // }

  // 取得字段是整型还是ＤＯＵＢＬＥ
  public String fieldtypeDoubleOrInteger() {
    if (tf_fieldType.equals(FieldType.Double.getValue())
        || tf_fieldType.equals(FieldType.Float.getValue()))
      return FieldType.Double.getValue();
    else if (tf_fieldType.equals(FieldType.Integer.getValue()))
      return FieldType.Integer.getValue();
    else {
      return null;
    }
  }

  // 取得是否字段可以小计
  @JsonIgnore
  public Boolean isFieldAllowSubTotal() {
    return tf_fieldType.equals("money") || getTf_allowSummary();
  }

  // 返回combo 是否可以多选
  public Boolean isComboMultiple() {
    if (tf_otherSetting != null && tf_otherSetting.indexOf("comboMultiple:true") != -1)
      return true;
    else
      return false;
  }

  // 返回是否有正则表达式的约束
  public Boolean haveRegExpValidator() {
    if (tf_otherSetting != null && tf_otherSetting.indexOf("regex") != -1)
      return true;
    else
      return false;
  }

  // 返回是否这个字段是一个select字段
  public Boolean isSelectField() {
    return tf_PropertyType != null;
  }

  // 返回是否这个字段是一个combo字段,既可以选又 可以录入
  public Boolean isComboField() {
    return tf_PropertyType != null && tf_PropertyType.getTf_canInput();
  }

  // 返回combo 是否可以自己录入数据
  public Boolean isComboAllowInput() {
    if (tf_otherSetting != null && tf_otherSetting.indexOf("comboAllowInput:true") != -1)
      return true;
    else
      return false;
  }

  // 返回是否有id key的combo box，有些combo 是 id 和key 是一样的
  // public Boolean haveIdKeyComboSource() {
  // if (tf_comboSource != null && tf_comboSource.length() > 1
  // && tf_fieldName.endsWith("Id"))
  // return true;
  // else
  // return false;
  // }

  // 如果有combo box value,返回combobox 的 name 字段
  // public String comboSourceValueField() {
  // if (haveIdKeyComboSource())
  // return tf_fieldName.replaceFirst("Id", "Name");
  // else
  // return tf_fieldName;
  // }

  // 返回正则表达式
  public String _getRegex() {
    return _getOtherSettingValue("regex");

  }

  // 返回出错提示
  public String _getRegexText() {

    return _getOtherSettingValue("regexText");
  }

  public String _getOtherSettingValue(String key) {
    String result = null;
    String[] strings = tf_otherSetting.split("\\s");
    for (String string : strings)
      if (string.startsWith(key + ":")) {
        result = string.substring(string.indexOf(":") + 1, string.length());
        break;
      }
    if (result.startsWith("\""))
      result = result.replaceAll("\"", "");
    if (result.startsWith("'"))
      result = result.replaceAll("'", "");
    return result;

  }

  public Integer getTf_fieldId() {
    return tf_fieldId;
  }

  public void setTf_fieldId(Integer tf_fieldId) {
    this.tf_fieldId = tf_fieldId;
  }

  public String getTf_title() {
    return tf_title;
  }

  public void setTf_title(String tf_title) {
    this.tf_title = tf_title;
  }

  public String getTf_fieldName() {
    return tf_fieldName;
  }

  public String getFactfieldName() {
    return tf_fieldName;
  }

  public void setTf_fieldName(String tf_fieldName) {
    this.tf_fieldName = tf_fieldName;
  }

  public String getTf_fieldType() {
    return tf_fieldType;
  }

  public void setTf_fieldType(String tf_fieldType) {
    this.tf_fieldType = tf_fieldType;
  }

  public Integer getTf_fieldLen() {
    return tf_fieldLen == null ? 0 : tf_fieldLen;
  }

  public void setTf_fieldLen(Integer tf_fieldLen) {
    this.tf_fieldLen = tf_fieldLen;
  }

  public Boolean getTf_isHidden() {
    return tf_isHidden == null ? false : tf_isHidden;
  }

  public void setTf_isHidden(Boolean tf_isHidden) {
    this.tf_isHidden = tf_isHidden;
  }

  @JsonIgnore(false)
  public Boolean getTf_isRequired() {
    return tf_isRequired == null ? false : tf_isRequired;
  }

  public void setTf_isRequired(Boolean tf_isRequired) {
    this.tf_isRequired = tf_isRequired;
  }

  public Boolean getTf_isDisable() {
    return tf_isDisable == null ? false : tf_isDisable;
  }

  public void setTf_isDisable(Boolean tf_isDisable) {
    this.tf_isDisable = tf_isDisable;
  }

  public _PropertyType getTf_PropertyType() {
    return tf_PropertyType;
  }

  public void setTf_PropertyType(_PropertyType tf_PropertyType) {
    this.tf_PropertyType = tf_PropertyType;
  }

  public String getTf_otherSetting() {
    return tf_otherSetting;
  }

  public void setTf_otherSetting(String tf_otherSetting) {
    this.tf_otherSetting = tf_otherSetting;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public String getTf_defaultValue() {
    return tf_defaultValue;
  }

  public void setTf_defaultValue(String tf_defaultValue) {
    this.tf_defaultValue = tf_defaultValue;
  }

  public Boolean getTf_allowGroup() {
    return tf_allowGroup == null ? false : tf_allowGroup;
  }

  public void setTf_allowGroup(Boolean tf_allowGroup) {
    this.tf_allowGroup = tf_allowGroup;
  }

  public Boolean getTf_allowSummary() {

    return tf_allowSummary == null ? false : tf_allowSummary;
  }

  public void setTf_allowSummary(Boolean tf_allowSummary) {
    this.tf_allowSummary = tf_allowSummary;
  }

  public Boolean getTf_allowAggregate() {
    return tf_allowAggregate == null ? false : tf_allowAggregate;
  }

  public void setTf_allowAggregate(Boolean tf_allowAggregate) {
    this.tf_allowAggregate = tf_allowAggregate;
  }

  public _Module getTf_Module() {
    return tf_Module;
  }

  public void setTf_Module(_Module tf_Module) {
    this.tf_Module = tf_Module;
  }

  public String getTf_fieldRelation() {
    return tf_fieldRelation;
  }

  public void setTf_fieldRelation(String tf_fieldRelation) {
    this.tf_fieldRelation = tf_fieldRelation;
  }

  /**
   * 是否是基本类型字段
   * 
   * @return
   */
  public boolean isBaseField() {
    return (!(isManyToOne() || isOneToOne() || isOneToMany() || isManyToMany()));
  }

  /**
   * 是否是多对一的字段
   * 
   * @return
   */
  public Boolean isManyToOne() {
    return tf_fieldRelation == null ? false : tf_fieldRelation.equalsIgnoreCase(MANYTOONE);
  }

  /**
   * 是否是一对一的字段
   * 
   * @return
   */
  public Boolean isOneToOne() {
    return tf_fieldRelation == null ? false : tf_fieldRelation.equalsIgnoreCase(ONETOONE);

  }

  /**
   * 是否是多对一的字段
   * 
   * @return
   */
  public Boolean isManyToMany() {
    return tf_fieldRelation == null ? false : tf_fieldRelation.equalsIgnoreCase(MANYTOMANY);
  }

  public String getTf_joinTable() {
    return tf_joinTable;
  }

  public void setTf_joinTable(String tf_joinTable) {
    this.tf_joinTable = tf_joinTable;
  }

  @JsonIgnore
  public Boolean isDateField() {
    return tf_fieldType != null && (tf_fieldType.equalsIgnoreCase(FieldType.Date.toString())
        || tf_fieldType.equalsIgnoreCase(FieldType.DateTime.toString()));
  }

  public Boolean isNumberField() {
    return tf_fieldType != null && (tf_fieldType.equalsIgnoreCase(FieldType.Integer.toString())
        || tf_fieldType.equalsIgnoreCase(FieldType.Double.toString())
        || tf_fieldType.equalsIgnoreCase(FieldType.Float.toString()));
  }

  /**
   * 是否是一对多的字段
   * 
   * @return
   */
  public Boolean isOneToMany() {
    return tf_fieldRelation == null ? false : tf_fieldRelation.equalsIgnoreCase(ONETOMANY);
  }

  public Boolean getTf_showNavigatorTree() {
    return tf_showNavigatorTree == null ? false : tf_showNavigatorTree;
  }

  public void setTf_showNavigatorTree(Boolean tf_showNavigatorTree) {
    this.tf_showNavigatorTree = tf_showNavigatorTree;
  }

  public Boolean getTf_newNeedSelected() {
    return tf_newNeedSelected == null ? false : tf_newNeedSelected;
  }

  public void setTf_newNeedSelected(Boolean tf_newNeedSelected) {
    this.tf_newNeedSelected = tf_newNeedSelected;
  }

  public String getManytoone_IdName() {
    if (!isBaseField()) {
      _Module module = SystemAndLoginInfoService.getModuleWithName(tf_fieldType);
      if (module != null)
        return module.getTableAsName() + ModuleConstants.SEPARATOR
            + module.getPrimaryKeyField().getTf_fieldName();
      else
        System.out.println("getManytoone_IdName  tf_fieldType:" + tf_fieldType + "没找到");
    }

    return null;
  }

  public void setManytoone_IdName(String manytoone_IdName) {
    this.manytoone_IdName = manytoone_IdName;
  }

  public String getManytoone_TitleName() {
    if (!isBaseField() && (isManyToOne() || isOneToOne())) {
      _Module module = SystemAndLoginInfoService.getModuleWithName(tf_fieldType);
      if (module != null)
        return module.getTableAsName() + ModuleConstants.SEPARATOR
            + module.getNameField().getTf_fieldName();
      else
        System.out.println("getManytoone_TitleName  tf_fieldType:" + tf_fieldType + "没找到");
    }
    return null;
  }

  public void setManytoone_TitleName(String manytoone_TitleName) {
    this.manytoone_TitleName = manytoone_TitleName;
  }

  public String getTf_DBfieldName() {
    return tf_DBfieldName;
  }

  public void setTf_DBfieldName(String tf_DBfieldName) {
    this.tf_DBfieldName = tf_DBfieldName;
  }

  public String getTf_DBformula() {
    return tf_DBformula;
  }

  public void setTf_DBformula(String tf_DBformula) {
    this.tf_DBformula = tf_DBformula;
  }

  public String getTf_divisor() {
    return tf_divisor;
  }

  public void setTf_divisor(String tf_divisor) {
    this.tf_divisor = tf_divisor;
  }

  public String getTf_denominator() {
    return tf_denominator;
  }

  public void setTf_denominator(String tf_denominator) {
    this.tf_denominator = tf_denominator;
  }

  public Integer getTf_fieldOrder() {
    return tf_fieldOrder;
  }

  public void setTf_fieldOrder(Integer tf_fieldOrder) {
    this.tf_fieldOrder = tf_fieldOrder;
  }

  public Boolean getTf_allowNew() {
    return tf_allowNew == null ? false : tf_allowNew;
  }

  public void setTf_allowNew(Boolean tf_allowNew) {
    this.tf_allowNew = tf_allowNew;
  }

  public Boolean getTf_allowEdit() {
    return tf_allowEdit == null ? false : tf_allowEdit;
  }

  public void setTf_allowEdit(Boolean tf_allowEdit) {
    this.tf_allowEdit = tf_allowEdit;
  }

  public Boolean getTf_allowInsertExcel() {
    return tf_allowInsertExcel == null ? false : tf_allowInsertExcel;
  }

  public void setTf_allowInsertExcel(Boolean tf_allowInsertExcel) {
    this.tf_allowInsertExcel = tf_allowInsertExcel;
  }

  public Boolean getTf_allowEditExcel() {
    return tf_allowEditExcel == null ? false : tf_allowEditExcel;
  }

  public void setTf_allowEditExcel(Boolean tf_allowEditExcel) {
    this.tf_allowEditExcel = tf_allowEditExcel;
  }

  public Boolean getTf_haveAttachment() {
    return tf_haveAttachment == null ? false : tf_haveAttachment;
  }

  public void setTf_haveAttachment(Boolean tf_haveAttachment) {
    this.tf_haveAttachment = tf_haveAttachment;
  }

  public Boolean getTf_isChartCategory() {
    return tf_isChartCategory == null ? false : tf_isChartCategory;
  }

  public void setTf_isChartCategory(Boolean tf_isChartCategory) {
    this.tf_isChartCategory = tf_isChartCategory;
  }

  public Boolean getTf_isChartNumeric() {
    return tf_isChartNumeric == null ? false : tf_isChartNumeric;
  }

  public void setTf_isChartNumeric(Boolean tf_isChartNumeric) {
    this.tf_isChartNumeric = tf_isChartNumeric;
  }

  public String getTf_fieldGroup() {
    return tf_fieldGroup;
  }

  public void setTf_fieldGroup(String tf_fieldGroup) {
    this.tf_fieldGroup = tf_fieldGroup;
  }

  public String getTf_unitText() {
    return tf_unitText;
  }

  public void setTf_unitText(String tf_unitText) {
    this.tf_unitText = tf_unitText;
  }

  public Boolean getTf_isMonetary() {
    return tf_isMonetary;
  }

  public String getTf_tooltipTpl() {
    return tf_tooltipTpl;
  }

  public void setTf_tooltipTpl(String tf_tooltipTpl) {
    this.tf_tooltipTpl = tf_tooltipTpl;
  }

  public void setTf_isMonetary(Boolean tf_isMonetary) {
    this.tf_isMonetary = tf_isMonetary;
  }

}
