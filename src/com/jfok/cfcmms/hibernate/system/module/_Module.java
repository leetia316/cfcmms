package com.jfok.cfcmms.hibernate.system.module;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.util.FileOperate;
import com.jfok.cfcmms.core.report.GroupFieldDefine;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.authority._ModuleApprove;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleDetailScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleExcelRecordAdd;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleExcelReport;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridNavigate;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleSubToolbar;
import com.jfok.cfcmms.hibernate.system.viewSetting._PrintScheme;
import com.jfok.cfcmms.share.module.ModuleConstants;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 系统中每个模块的字义
 * 
 * @author jfok 2012.11.7
 * 
 */
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9902, title = "系统模块")
public class _Module implements _IModuleControlInterface, Serializable {

  public static final String MODULEID = "tf_moduleId";
  public static final String MODULENAME = "tf_moduleName";

  @Id
  @FieldDefine(title = "模块ID号", number = 10, fieldGroup = "基本信息")
  @Column(nullable = false, length = 10)
  private String tf_moduleId; // 模块ＩＤ，模块序号

  @JsonIgnore
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  @JoinColumn(name = "tf_moduleGroupId", nullable = false)
  @FieldDefine(title = "模块分组", number = 20, fieldGroup = "基本信息")
  private _ModuleGroup tf_ModuleGroup;

  @FieldDefine(title = "模块标识", number = 30, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50, updatable = false)
  private String tf_moduleName; // 模块英文名称

  @FieldDefine(title = "模块名称", nameField = true, number = 40, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50)
  private String tf_title; // 模块中文名称

  @FieldDefine(title = "模块简称", number = 50, fieldGroup = "基本信息")
  @Column(length = 20)
  private String tf_shortname; // 简称

  @FieldDefine(title = "英文简称", number = 60, fieldGroup = "基本信息")
  @Column(length = 20)
  private String tf_englishname; // 英文简称，在新增序号的时候，可以把这字母加进去

  @FieldDefine(title = "表名", number = 70, fieldGroup = "基本信息")
  @Column(length = 50)
  private String tf_tableName;

  @FieldDefine(title = "主键", number = 80, fieldGroup = "字段设置")
  @Column(nullable = false, length = 50)
  private String tf_primaryKey;// 模块主键

  @FieldDefine(title = "显示标志字段", number = 81, fieldGroup = "字段设置")
  @Column(nullable = false, length = 50)
  private String tf_nameFields;// 模块主要关键内容的字段，如合同的是合同名称，部门的是部门名称

  @FieldDefine(title = "记录标题tpl", number = 82, remark = "如果显示标志字段不能描述记录,需要设置此内容", fieldGroup = "字段设置")
  @Column(length = 200)
  private String tf_titleTpl;// 模块主要关键内容的字段，如合同的是合同名称，部门的是部门名称

  @FieldDefine(title = "编码字段", number = 91, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_codeField;// 模块编码字段，如果有的话

  @FieldDefine(title = "顺序号字段", number = 97, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_orderField;// 模块编码字段，如果有的话

  @FieldDefine(title = "顺序字段限定模块", number = 98, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_orderFieldControlModule;

  @FieldDefine(title = "日期字段", number = 92, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_dateField;

  @FieldDefine(title = "年度字段", number = 93, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_yearField;

  @FieldDefine(title = "月度字段", number = 94, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_monthField;

  @FieldDefine(title = "季度字段", number = 95, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_seasonField;

  @FieldDefine(title = "文件字段", number = 96, fieldGroup = "字段设置")
  @Column(length = 50)
  private String tf_fileField;

  @FieldDefine(title = "模块描述", number = 100, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_description;

  @FieldDefine(title = "请求地址", number = 110)
  @Column(nullable = false, length = 50)
  private String tf_requestMapping;// 系统中后台服务的调用接入点 user.do,employee.do 等

  @FieldDefine(title = "图标地址", number = 120, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_iconUrl;

  @FieldDefine(title = "图标iconCls", number = 121, fieldGroup = "附加信息")
  @Column(length = 50)
  private String tf_iconCls;

  @FieldDefine(title = "图标文件", number = 122, fieldGroup = "附加信息")
  @Column(length = 50)
  private Blob tf_iconFile;

  @FieldDefine(title = "图标字体值", number = 125, fieldGroup = "附加信息")
  @Column(length = 10)
  private String tf_glyph;

  @FieldDefine(title = "默认排序字段", number = 130, fieldGroup = "附加信息")
  private String tf_defaultOrderField;

  @FieldDefine(title = "联动模块", remark = "在本模块的数据增删改后，打开的联动模块都要刷新数据。", number = 155, fieldGroup = "附加信息")
  @Column(length = 200)
  private String tf_linkedModule;

  @FieldDefine(title = "主页上顺序", number = 99, fieldGroup = "其他设置")
  @Column(length = 50)
  private String tf_homePageTag;

  @FieldDefine(title = "行操作", remark = "新增及修改操作都行内完成", number = 140)
  @Column(nullable = false)
  private Boolean tf_isInlineOper;

  ////////////////////////
  // 如部门编码为 2,2,2,2,2
  @FieldDefine(title = "编码级次", remark = "如果设置了编码级次，则主键的长度必须是此级次中的，并且必须有父级编码存在", number = 150, fieldGroup = "层次结构")
  @Column(length = 50)
  String tf_codeLevel;

  @FieldDefine(title = "树形表", number = 157, fieldGroup = "层次结构")
  private Boolean tf_isTreeModel;

  /**
   * 如果是一个树形的模块，那么这里记录父键的字段名,如果此模块设置了编码级次，那么就要自动调整tf_parentKey中的值
   */
  @FieldDefine(title = "父键字段", number = 85, fieldGroup = "层次结构")
  @Column(length = 50)
  private String tf_parentKey;

  //////////////////////

  @FieldDefine(title = "可用", number = 160)
  @Column(nullable = false)
  private Boolean tf_isEnable = false;

  @FieldDefine(title = "可浏览", number = 170)
  @Column(nullable = false)
  private Boolean tf_hasBrowse = false;

  @FieldDefine(title = "可增加", number = 180)
  @Column(nullable = false)
  private Boolean tf_hasInsert = false;

  @FieldDefine(title = "可修改", number = 190)
  @Column(nullable = false)
  private Boolean tf_hasEdit = false;

  @FieldDefine(title = "可删除", number = 200)
  @Column(nullable = false)
  private Boolean tf_hasDelete = false;

  @FieldDefine(title = "可执行", number = 210)
  @Column(nullable = false)
  private Boolean tf_hasExec = false;

  @FieldDefine(title = "可审核", number = 220)
  @Column(nullable = false)
  private Boolean tf_hasAuditing = false;

  @FieldDefine(title = "可审批", number = 230)
  @Column(nullable = false)
  private Boolean tf_hasApprove = false;

  @FieldDefine(title = "可支付", number = 240)
  @Column(nullable = false)
  private Boolean tf_hasPayment = false;

  @FieldDefine(title = "有附件", number = 250)
  @Column(nullable = false)
  private Boolean tf_hasAttachment = false; // 是否需要附件

  @FieldDefine(title = "记录有图标", number = 252, fieldGroup = "附加信息")
  private Boolean tf_hasRecordIcon;

  @FieldDefine(title = "可权限设置", remark = "用户可对此模块设置权限，选定的才可以浏览与操作", number = 260)
  @Column(nullable = false)
  private Boolean tf_canLimit = false; // 此模块是否能进行权限设置

  @FieldDefine(title = "Excel导入", remark = "是否可以通过Excel导入新增记录", number = 270)
  @Column(nullable = false)
  private Boolean tf_allowInsertExcel = false;

  @FieldDefine(title = "Excel修改", remark = "是否可以导出的Excel修改后再导入", number = 280)
  @Column(nullable = false)
  private Boolean tf_allowEditExcel = false;

  @FieldDefine(title = "可图表", number = 290)
  @Column(nullable = false)
  private Boolean tf_hasChart = false;

  @FieldDefine(title = "只用于查询", number = 292)
  private Boolean tf_isOnlyUsedSearch;

  @FieldDefine(title = "无实体bean", number = 294)
  private Boolean tf_isNotNean;

  @FieldDefine(title = "系统模块", number = 300, remark = "如果是系统模块，用户没有浏览权限，就不把模块定义发送到前端")
  @Column(nullable = false)
  private Boolean tf_isSystem = false;

  /**
   * 这个要改掉，专门加一个综合查询设置条件的模块，可以包括模块，以及字段
   */
  @FieldDefine(title = "查询条件顺序号", number = 310, remark = "此模块放在综合查询的条件选择栏中的顺序")
  private Integer tf_searchCondOrder;

  @FieldDefine(title = "备注", number = 800)
  private String tf_remark;
  // 本模块定义的附加字段，包括manytoOne的父模块，以及child模块的聚合字段

  @Transient
  private List<_ModuleGridNavigate> moduleGridNavigates;

  // 所有的本模块可以进行分组设置的定义
  @Transient
  private List<GroupFieldDefine> groupFieldDefines;

  // 保存此模块的当前的idNameMaps,如果有manyToOne模块，可以放在其下拉框里,
  @JsonIgnore
  @Transient
  private Map<String, String> idNameMap;

  @JsonIgnore
  @Transient
  private List<_Module> parents;

  @JsonIgnore
  @Transient
  private List<_Module> parentOneToOnes;

  @JsonIgnore
  @Transient
  private List<_Module> childOneToOnes;

  // 可以加入导航的本层的manytoone模块
  @JsonIgnore
  @Transient
  private List<_Module> navigateParents;

  @JsonIgnore
  @Transient
  private List<_Module> childs;

  // 包括所有的manytoone和onetoone
  @Transient
  private List<String> childNames;

  // @Transient
  // private ApproveModuleInfo approveModuleInfo;

  // 只在后台作比较，不传到前台
  @JsonIgnore
  @Transient
  private List<_ModuleFieldConstraint> moduleFieldConstraints = null;

  @OneToMany(targetEntity = _ModuleField.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_fieldOrder")
  private List<_ModuleField> tf_fields;

  @OneToMany(targetEntity = _ModuleFormScheme.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_schemeOrder")
  private List<_ModuleFormScheme> tf_formSchemes;

  @OneToMany(targetEntity = _ModuleGridScheme.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_schemeOrder")
  private List<_ModuleGridScheme> tf_gridSchemes;

  // 附加字段
  @OneToMany(targetEntity = _ModuleAdditionField.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_fieldId")
  private List<_ModuleAdditionField> tf_moduleAdditionFields;

  // 模块单条记录的打印的设置
  @OneToMany(targetEntity = _PrintScheme.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_schemeOrder")
  private List<_PrintScheme> tf_recordPrintSchemes;

  // 明细显示方案
  @OneToMany(targetEntity = _ModuleDetailScheme.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_order")
  private List<_ModuleDetailScheme> tf_moduleDetailSchemes;

  // 加入 module excel report 的定义
  @OneToMany(targetEntity = _ModuleExcelReport.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_order")
  private List<_ModuleExcelReport> tf_moduleExcelReports;

  // 加入 module 单条记录 excel 导入的设置

  @OneToMany(targetEntity = _ModuleExcelRecordAdd.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_order")
  private List<_ModuleExcelRecordAdd> tf_moduleExcelRecordAdds;

  // 附加的功能设置
  @OneToMany(targetEntity = _ModuleAdditionFunction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_moduleAdditionFunctionId")
  private List<_ModuleAdditionFunction> tf_moduleAdditions;

  // 加在子模块上的toolBar
  @OneToMany(targetEntity = _ModuleSubToolbar.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_order")
  private List<_ModuleSubToolbar> tf_moduleSubToolbar;

  // 本模块的审批信息的字义
  @OneToMany(targetEntity = _ModuleApprove.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "tf_moduleId")
  @OrderBy("tf_order")
  private List<_ModuleApprove> tf_moduleApproves;

  public _Module() {

  }

  // 把所有的lazy都加载进来
  public void loadAllOneToMany() {

    // 以下全部都是提前加载，防止session关闭
    this.getTf_fields().size();
    for (_ModuleGridScheme scheme : this.getTf_gridSchemes()) {
      for (_ModuleGridSchemeGroup group : scheme.getTf_schemeGroups()) {
        group.getTf_groupFields().size();
      }
    }
    for (_ModuleFormScheme scheme : this.getTf_formSchemes()) {
      for (_ModuleFormSchemeGroup group : scheme.getTf_schemeGroups()) {
        group.getTf_groupFields().size();
      }
    }
    this.getTf_moduleAdditionFields().size();
    // record 单条打印方案
    this.getTf_recordPrintSchemes().size();
    // 记录detail名细方案
    for (_ModuleDetailScheme scheme : this.getTf_moduleDetailSchemes())
      scheme.getTf_moduleDetailSchemeFields().size();
    // 加入 module excel report 的定义
    this.getTf_moduleExcelReports().size();
    // 加入 module 单条记录 excel 导入的设置
    this.getTf_moduleExcelRecordAdds().size();

    this.getTf_moduleAdditions().size();
    this.getTf_moduleSubToolbar().size();
    this.getTf_moduleApproves().size();

  }

  // private String tf_parentModuleId;
  // private Integer tf_parentDisplayOrder; // 已模块在父模块中的按钮的显示顺序号
  // private String tf_childModuleIds; // 本模块的子模块，有些模块有二个父，需要在此设置
  // private Boolean tf_departmentLimit; // 是否要加入全局部门限制
  // private String tf_leftTree; // 是否有显示在左边的树形导航栏，如部门， 部门＋工程，部门＋ 工程＋标段
  // private Boolean tf_hasAddition; // 是否需要附件
  // private String tf_otherSetting;
  // private String tf_printDefine; // 定义的直接在系统中打印模板
  @Override
  public String toString() {
    return "_Module [tf_moduleId=" + tf_moduleId + ", tf_moduleGroup=" + tf_ModuleGroup
        + ", tf_moduleName=" + tf_moduleName + ", tf_title=" + tf_title + ", tf_shortname="
        + tf_shortname + ", tf_englishname=" + tf_englishname + "]";
  }

  public void setAllDisable() {
    tf_isEnable = false;
    tf_hasBrowse = false;
    tf_hasInsert = false;
    tf_hasEdit = false;
    tf_hasDelete = false;
    tf_hasExec = false;
    tf_hasAuditing = false;
    tf_hasApprove = false;
    tf_hasPayment = false;
  }

  // 根据id 返回modulefiles 中的字段
  public _ModuleField getModuleFieldByFieldId(Integer fieldId) {
    for (_ModuleField field : tf_fields)
      if (field.getTf_fieldId().equals(fieldId)) {
        return field;
      }
    return null;
  }

  // 根据id 返回moduleaddition 中的字段
  public _ModuleAdditionField getModuleAdditionFieldByFieldId(Integer fieldId) {
    for (_ModuleAdditionField field : tf_moduleAdditionFields)
      if (field.getTf_fieldId().equals(fieldId)) {
        return field;
      }
    return null;
  }

  // 根据id 返回modulefiles 中的字段
  public _ModuleField getModuleFieldByFieldName(String fieldName) {
    for (_ModuleField field : tf_fields)
      if (field.getTf_fieldName().equals(fieldName)) {
        return field;
      }
    return null;
  }

  // 返回modulefiles 的primarykdy字段
  @JsonIgnore
  public _ModuleField getPrimaryKeyField() {
    for (_ModuleField field : tf_fields)
      if (field.getTf_fieldName().equals(tf_primaryKey)) {
        return field;
      }
    System.out.println("模块：" + this.getTf_moduleName() + ":未找到主键");
    return null;
  }

  // 返回modulefiles 的primarykdy字段
  @JsonIgnore
  public _ModuleField getNameField() {
    for (_ModuleField field : tf_fields)
      if (field.getTf_fieldName().equals(tf_nameFields)) {
        return field;
      }
    return null;
  }

  /**
   * 只要在模块的字段前打勾要导航的才加入 取得所有的navigate 的导航的树的名称，包括manytoone 以及单个字段
   * 
   * @param allPath
   * @param parent
   */
  @JsonIgnore
  public List<_ModuleGridNavigate> getAllNavigatePaths() {
    List<_ModuleGridNavigate> result = new ArrayList<_ModuleGridNavigate>();
    getNavigateParentPaths(result, null);
    result.addAll(getNavigateFields());
    return result;
  }

  /**
   * 返回本模块的所有可以参与导航的普通字段，不包括manyToOne中已经有的，
   * 
   * @return
   */
  @JsonIgnore
  public List<_ModuleGridNavigate> getNavigateFields() {
    List<_ModuleGridNavigate> result = new ArrayList<_ModuleGridNavigate>();
    if (tf_fields != null)
      for (_ModuleField field : tf_fields) {
        if (field.getTf_showNavigatorTree() && field.isBaseField())
          result.add(new _ModuleGridNavigate(field.getTf_fieldName(), field.getTf_title())); // true
      }
    return result;
  }

  @JsonIgnore
  public _ModuleField getFieldWithAsAndFieldName(String fieldName) {
    for (_ModuleField field : tf_fields) {
      if ((getTableAsName() + "." + field.getTf_fieldName()).equals(fieldName))
        return field;
    }
    return null;
  }

  /**
   * 所有的模块都加入，不管是不是有导航值的 取得所有的navigate 的导航的树的名称，包括manytoone 以及单个字段
   * 
   * @param allPath
   * @param parent
   */
  @JsonIgnore
  public List<_ModuleGridNavigate> getCanNavigatePaths() {
    List<_ModuleGridNavigate> result = new ArrayList<_ModuleGridNavigate>();
    getCanNavigateParentPaths(result, null);
    return result;
  }

  /**
   * 所有的模块都加入，不管是不是有导航值的 根据manytoone的字义，返回所有可能的 navigate parent 的path 祖父1--父1 ,
   * 祖父2--父1 , 父2
   * 
   * @return
   */
  @JsonIgnore
  public void getCanNavigateParentPaths(List<_ModuleGridNavigate> allPath,
      _ModuleGridNavigate parent) {
    // List<List<String>> result = new ArrayList<List<String>>();
    if (getParents().size() > 0) {
      // 如果这个节点有父接点，那么把这个删了，要加入的父接点－－祖父接点
      allPath.remove(parent);
      for (_Module pmodule : getParents()) {
        _ModuleGridNavigate p = new _ModuleGridNavigate(
            pmodule.getTf_moduleName() + (parent == null ? "" : "--" + parent.getTf_fields()),
            pmodule.getTf_title() + (parent == null ? "" : "-" + parent.getTf_text()));
        allPath.add(p);
        pmodule.getCanNavigateParentPaths(allPath, p);
      }
    }
  }

  /**
   * 只有选中了可以导航的模块才有 根据manytoone的字义，返回所有可能的 navigate parent 的path 祖父1--父1 ,
   * 祖父2--父1 , 父2
   * 
   * @return
   */
  @JsonIgnore
  public void getNavigateParentPaths(List<_ModuleGridNavigate> allPath,
      _ModuleGridNavigate parent) {
    // List<List<String>> result = new ArrayList<List<String>>();
    if (getNavigateParents().size() > 0) {
      // 如果这个节点有父接点，那么把这个删了，要加入的父接点－－祖父接点
      allPath.remove(parent);
      for (_Module pmodule : getNavigateParents()) {
        _ModuleGridNavigate p = new _ModuleGridNavigate(
            pmodule.getTf_moduleName() + (parent == null ? "" : "--" + parent.getTf_fields()),
            pmodule.getTf_title() + (parent == null ? "" : "-" + parent.getTf_text()));
        allPath.add(p);
        pmodule.getNavigateParentPaths(allPath, p);
      }
    }
  }

  /**
   * 根据manytoone的字义，返回所有可能的parent 的path 祖父1--父1 , 祖父2--父1 , 父2
   * 
   * @return
   */
  @JsonIgnore
  public void getParentPaths(List<String> allPath, String parent) {
    // List<List<String>> result = new ArrayList<List<String>>();
    if (getParents().size() > 0) {
      // 如果这个节点有父接点，那么把这个删了，要加入的父接点－－祖父接点
      allPath.remove(parent);
      for (_Module pmodule : getParents()) {
        String p = pmodule.getTf_moduleName() + (parent == null ? "" : "--" + parent);
        allPath.add(p);
        pmodule.getParentPaths(allPath, p);
      }
    }
  }

  /**
   * 返回可以控制这个模块的所有父类及祖先类，在选择grid 字段的时候，可以应用此来生成所有可以选择的字段
   * 
   * @return
   */
  @JsonIgnore
  public Map<String, _Module> getAllParentsMap() {
    Map<String, _Module> result = new HashMap<String, _Module>();
    for (_Module m : getParents()) {
      result.put(m.getTf_moduleName(), m);
      result.putAll(m.getAllParentsMap());
    }
    return result;
  }

  /**
   * 返回可以控制这个模块的所有父类及祖先类，在选择grid 字段的时候，可以应用此来生成所有可以选择的字段
   * 
   * @return
   */
  @JsonIgnore
  public List<_Module> getAllParentsList() {
    List<_Module> result = new ArrayList<_Module>();
    for (_Module m : getParents()) {
      result.add(m);
    }
    for (_Module m : getParents()) {
      List<_Module> ms = m.getAllParentsList();
      for (_Module mm : ms)
        if (!result.contains(mm))
          result.add(mm);
    }
    return result;
  }

  /**
   * 返回本模块可以控制的所有子模块，在选择grid 字段的时候，可以应用此来生成所有可以选择的聚合字段，以及count
   * 
   * @return
   */
  @JsonIgnore
  public List<_Module> getAllChildsList() {
    List<_Module> result = new ArrayList<_Module>();
    for (_Module m : getChilds()) {
      result.add(m);
    }
    for (_Module m : getChilds()) {
      List<_Module> ms = m.getAllChildsList();
      for (_Module mm : ms)
        if (!result.contains(mm))
          result.add(mm);
    }
    return result;
  }

  // 根据模块 as name 号取得模块定义
  public _Module getModuleWithAsName(String name) {
    for (_Module module : getParents())
      if (module.getTableAsName().equals(name))
        return module;
    for (_Module module : getParentOneToOnes())
      if (module.getTableAsName().equals(name))
        return module;

    return null;
  }

  // 根据模块名称，返回父模块定义
  public _Module getParentModuleWithName(String name) {
    for (_Module pm : getParents())
      if (pm.getTf_moduleName().equals(name))
        return pm;
    return null;
  }

  public _ModuleGridScheme getGridFieldsSchemeWithName(String schemaName) {
    for (int i = 0; i < tf_gridSchemes.size(); i++) {
      if (tf_gridSchemes.get(i).getTf_schemeName().equals(schemaName))
        return tf_gridSchemes.get(i);
    }
    return null;
  }

  public _ModuleGridScheme getGridFieldsSchemeWithOrder(Integer id) {
    for (int i = 0; i < tf_gridSchemes.size(); i++) {
      if (tf_gridSchemes.get(i).getTf_schemeOrder().equals(id))
        return tf_gridSchemes.get(i);
    }
    return null;
  }

  public String getTableAsName() {
    return "_t" + tf_moduleId;
  }

  public static String IMGPATH = null;

  public String getIconURL() {
    if (IMGPATH == null) {
      IMGPATH = _Module.class.getResource("/").getPath().replace("%20", " ");
      IMGPATH = IMGPATH.substring(0, IMGPATH.indexOf("WEB-INF")) + "images" + File.separator
          + "module" + File.separator;
    }
    if (FileOperate.fileExists(IMGPATH + tf_moduleName + ".png"))
      return ModuleConstants.MENUICONPATH + tf_moduleName + ".png";
    else
      return null;
  }

  public String getTf_moduleId() {
    return tf_moduleId;
  }

  public void setTf_moduleId(String tf_moduleId) {
    this.tf_moduleId = tf_moduleId;
  }

  public String getTf_moduleName() {
    return tf_moduleName;
  }

  // 如果modulename没有前缀_,那就就加一个用于生成 tf_ （modulename）
  public String getTf_moduleNameWithPre() {
    if (tf_moduleName.startsWith("_"))
      return tf_moduleName;
    else
      return "_" + tf_moduleName;
  }

  public void setTf_moduleName(String tf_moduleName) {
    this.tf_moduleName = tf_moduleName;
  }

  public String getTf_title() {
    return tf_title;
  }

  public String shortnameOrTitle() {
    if (tf_shortname == null || tf_shortname.length() == 0)
      return tf_title;
    else
      return tf_shortname;
  }

  public void setTf_title(String tf_title) {
    this.tf_title = tf_title;
  }

  public String getTf_shortname() {
    return tf_shortname;
  }

  public void setTf_shortname(String tf_shortname) {
    this.tf_shortname = tf_shortname;
  }

  public String getTf_englishname() {
    return tf_englishname;
  }

  public void setTf_englishname(String tf_englishname) {
    this.tf_englishname = tf_englishname;
  }

  public String getTf_primaryKey() {
    return tf_primaryKey;
  }

  public void setTf_primaryKey(String tf_primaryKey) {
    this.tf_primaryKey = tf_primaryKey;
  }

  public String getTf_nameFields() {
    return tf_nameFields;
  }

  public void setTf_nameFields(String tf_nameFields) {
    this.tf_nameFields = tf_nameFields;
  }

  public String getTf_parentKey() {
    return tf_parentKey;
  }

  public void setTf_parentKey(String tf_parentKey) {
    this.tf_parentKey = tf_parentKey;
  }

  public String getTf_description() {
    return tf_description;
  }

  public void setTf_description(String tf_description) {
    this.tf_description = tf_description;
  }

  public String getTf_requestMapping() {
    return tf_requestMapping;
  }

  public void setTf_requestMapping(String tf_requestMapping) {
    this.tf_requestMapping = tf_requestMapping;
  }

  public String getTf_iconUrl() {
    return tf_iconUrl;
  }

  public void setTf_iconUrl(String tf_iconUrl) {
    this.tf_iconUrl = tf_iconUrl;
  }

  public String getTf_glyph() {
    return tf_glyph;
  }

  public void setTf_glyph(String tf_glyph) {
    this.tf_glyph = tf_glyph;
  }

  public Boolean getTf_isTreeModel() {
    return tf_isTreeModel;
  }

  public void setTf_isTreeModel(Boolean tf_isTreeModel) {
    this.tf_isTreeModel = tf_isTreeModel;
  }

  public Boolean getTf_isInlineOper() {
    return tf_isInlineOper;
  }

  public void setTf_isInlineOper(Boolean tf_isInlineOper) {
    this.tf_isInlineOper = tf_isInlineOper;
  }

  public Boolean getTf_isEnable() {
    return tf_isEnable == null ? false : tf_isEnable;
  }

  public void setTf_isEnable(Boolean tf_isEnable) {
    this.tf_isEnable = tf_isEnable;
  }

  public Boolean getTf_hasBrowse() {
    return tf_hasBrowse == null ? false : tf_hasBrowse;
  }

  public void setTf_hasBrowse(Boolean tf_hasBrowse) {
    this.tf_hasBrowse = tf_hasBrowse;
  }

  public Boolean getTf_hasInsert() {
    return tf_hasInsert == null ? false : tf_hasInsert;
  }

  public void setTf_hasInsert(Boolean tf_hasInsert) {
    this.tf_hasInsert = tf_hasInsert;
  }

  public Boolean getTf_hasEdit() {
    return tf_hasEdit == null ? false : tf_hasEdit;
  }

  public void setTf_hasEdit(Boolean tf_hasEdit) {
    this.tf_hasEdit = tf_hasEdit;
  }

  public Boolean getTf_hasDelete() {
    return tf_hasDelete == null ? false : tf_hasDelete;
  }

  public void setTf_hasDelete(Boolean tf_hasDelete) {
    this.tf_hasDelete = tf_hasDelete;
  }

  public Boolean getTf_hasExec() {
    return tf_hasExec == null ? false : tf_hasExec;
  }

  public void setTf_hasExec(Boolean tf_hasExec) {
    this.tf_hasExec = tf_hasExec;
  }

  public Boolean getTf_hasAuditing() {
    return tf_hasAuditing == null ? false : tf_hasAuditing;
  }

  public void setTf_hasAuditing(Boolean tf_hasAuditing) {
    this.tf_hasAuditing = tf_hasAuditing;
  }

  public Boolean getTf_hasApprove() {
    return tf_hasApprove == null ? false : tf_hasApprove;
  }

  public void setTf_hasApprove(Boolean tf_hasApprove) {
    this.tf_hasApprove = tf_hasApprove;
  }

  public Boolean getTf_hasPayment() {
    return tf_hasPayment == null ? false : tf_hasPayment;
  }

  public void setTf_hasPayment(Boolean tf_hasPayment) {
    this.tf_hasPayment = tf_hasPayment;
  }

  public Boolean getTf_hasAttachment() {
    return tf_hasAttachment;
  }

  public void setTf_hasAttachment(Boolean tf_hasAttachment) {
    this.tf_hasAttachment = tf_hasAttachment;
  }

  public Blob getTf_iconFile() {
    return tf_iconFile;
  }

  public void setTf_iconFile(Blob tf_iconFile) {
    this.tf_iconFile = tf_iconFile;
  }

  public Boolean getTf_hasRecordIcon() {
    return tf_hasRecordIcon;
  }

  public void setTf_hasRecordIcon(Boolean tf_hasRecordIcon) {
    this.tf_hasRecordIcon = tf_hasRecordIcon;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public _ModuleGroup getTf_ModuleGroup() {
    return tf_ModuleGroup;
  }

  public void setTf_ModuleGroup(_ModuleGroup tf_ModuleGroup) {
    this.tf_ModuleGroup = tf_ModuleGroup;
  }

  public List<_ModuleDetailScheme> getTf_moduleDetailSchemes() {
    return tf_moduleDetailSchemes;
  }

  public void setTf_moduleDetailSchemes(List<_ModuleDetailScheme> tf_moduleDetailSchemes) {
    this.tf_moduleDetailSchemes = tf_moduleDetailSchemes;
  }

  public List<_ModuleAdditionField> getTf_moduleAdditionFields() {
    return tf_moduleAdditionFields;
  }

  public void setTf_moduleAdditionFields(List<_ModuleAdditionField> tf_moduleAdditionFields) {
    this.tf_moduleAdditionFields = tf_moduleAdditionFields;
  }

  public List<_Module> _getParents() {
    return parents;
  }

  public List<_Module> getParents() {
    if (parents == null)
      parents = new ArrayList<_Module>();
    return parents;
  }

  public String getTf_orderFieldControlModule() {
    return tf_orderFieldControlModule;
  }

  public void setTf_orderFieldControlModule(String tf_orderFieldControlModule) {
    this.tf_orderFieldControlModule = tf_orderFieldControlModule;
  }

  public void setParents(List<_Module> parents) {
    this.parents = parents;
  }

  public List<_Module> getNavigateParents() {
    if (navigateParents == null)
      navigateParents = new ArrayList<_Module>();
    return navigateParents;
  }

  public void setNavigateParents(List<_Module> navigateParents) {
    this.navigateParents = navigateParents;
  }

  public List<_Module> getChilds() {
    if (childs == null)
      childs = new ArrayList<_Module>();
    return childs;
  }

  public List<String> getChildNames() {
    if (childNames == null)
      childNames = new ArrayList<String>();
    return childNames;
  }

  public void setChildNames(List<String> childNames) {
    this.childNames = childNames;
  }

  public void setChilds(List<_Module> childs) {
    this.childs = childs;
  }

  public Map<String, String> getIdNameMap() {
    return idNameMap;
  }

  public void setIdNameMap(Map<String, String> idNameMap) {
    this.idNameMap = idNameMap;
  }

  public String getTf_defaultOrderField() {
    return tf_defaultOrderField;
  }

  public void setTf_defaultOrderField(String tf_defaultOrderField) {
    this.tf_defaultOrderField = tf_defaultOrderField;
  }

  public String getTf_codeLevel() {
    return tf_codeLevel;
  }

  public void setTf_codeLevel(String tf_codeLevel) {
    this.tf_codeLevel = tf_codeLevel;
  }

  public Boolean isCodeLevel() {
    return (tf_codeLevel != null && tf_codeLevel.length() > 0);
  }

  public Boolean getTf_canLimit() {
    return tf_canLimit == null ? false : tf_canLimit;
  }

  public void setTf_canLimit(Boolean tf_canLimit) {
    this.tf_canLimit = tf_canLimit;
  }

  public List<_ModuleGridNavigate> getModuleGridNavigates() {
    return moduleGridNavigates;
  }

  public void setModuleGridNavigates(List<_ModuleGridNavigate> moduleGridNavigates) {
    this.moduleGridNavigates = moduleGridNavigates;
  }

  public List<_ModuleExcelReport> getTf_moduleExcelReports() {
    return tf_moduleExcelReports;
  }

  public void setTf_moduleExcelReports(List<_ModuleExcelReport> tf_moduleExcelReports) {
    this.tf_moduleExcelReports = tf_moduleExcelReports;
  }

  public List<_ModuleExcelRecordAdd> getTf_moduleExcelRecordAdds() {
    return tf_moduleExcelRecordAdds;
  }

  public void setTf_moduleExcelRecordAdds(List<_ModuleExcelRecordAdd> tf_moduleExcelRecordAdds) {
    this.tf_moduleExcelRecordAdds = tf_moduleExcelRecordAdds;
  }

  public List<_PrintScheme> getTf_recordPrintSchemes() {
    return tf_recordPrintSchemes;
  }

  public void setTf_recordPrintSchemes(List<_PrintScheme> tf_recordPrintSchemes) {
    this.tf_recordPrintSchemes = tf_recordPrintSchemes;
  }

  public List<_ModuleApprove> getTf_moduleApproves() {
    return tf_moduleApproves;
  }

  public void setTf_moduleApproves(List<_ModuleApprove> tf_moduleApproves) {
    this.tf_moduleApproves = tf_moduleApproves;
  }

  public Boolean isModuleChildOneToOne(String moduleName) {
    if (childOneToOnes == null)
      return false;
    for (_Module m : childOneToOnes)
      if (m.getTf_moduleName().equals(moduleName))
        return true;
    return false;
  }

  public Boolean isModuleParentOneToOne(String moduleName) {
    if (parentOneToOnes == null)
      return false;
    for (_Module m : parentOneToOnes)
      if (m.getTf_moduleName().equals(moduleName))
        return true;
    return false;
  }

  public List<_Module> _getParentOneToOnes() {
    return parentOneToOnes;
  }

  public List<_Module> getParentOneToOnes() {
    if (parentOneToOnes == null)
      parentOneToOnes = new ArrayList<_Module>();
    return parentOneToOnes;
  }

  public void setParentOneToOnes(List<_Module> parentOneToOnes) {
    this.parentOneToOnes = parentOneToOnes;
  }

  public List<_Module> _getChildOneToOnes() {
    return childOneToOnes;
  }

  public List<_Module> getChildOneToOnes() {
    if (childOneToOnes == null)
      childOneToOnes = new ArrayList<_Module>();
    return childOneToOnes;
  }

  public void setChildOneToOnes(List<_Module> childOneToOnes) {
    this.childOneToOnes = childOneToOnes;
  }

  public String getTf_tableName() {
    return tf_tableName == null || tf_tableName.length() == 0 ? tf_moduleName : tf_tableName;
  }

  public void setTf_tableName(String tf_tableName) {
    this.tf_tableName = tf_tableName;
  }

  public String getTf_linkedModule() {
    return tf_linkedModule;
  }

  public void setTf_linkedModule(String tf_linkedModule) {
    this.tf_linkedModule = tf_linkedModule;
  }

  public Boolean getTf_allowInsertExcel() {
    return tf_allowInsertExcel;
  }

  public void setTf_allowInsertExcel(Boolean tf_allowInsertExcel) {
    this.tf_allowInsertExcel = tf_allowInsertExcel;
  }

  public Boolean getTf_allowEditExcel() {
    return tf_allowEditExcel;
  }

  public void setTf_allowEditExcel(Boolean tf_allowEditExcel) {
    this.tf_allowEditExcel = tf_allowEditExcel;
  }

  public Boolean getTf_hasChart() {
    return tf_hasChart;
  }

  public void setTf_hasChart(Boolean tf_hasChart) {
    this.tf_hasChart = tf_hasChart;
  }

  public String getTf_codeField() {
    return tf_codeField;
  }

  public void setTf_codeField(String tf_codeField) {
    this.tf_codeField = tf_codeField;
  }

  public String getTf_orderField() {
    return tf_orderField;
  }

  public void setTf_orderField(String tf_orderField) {
    this.tf_orderField = tf_orderField;
  }

  public String getTf_dateField() {
    return tf_dateField;
  }

  public void setTf_dateField(String tf_dateField) {
    this.tf_dateField = tf_dateField;
  }

  public String getTf_yearField() {
    return tf_yearField;
  }

  public void setTf_yearField(String tf_yearField) {
    this.tf_yearField = tf_yearField;
  }

  public String getTf_monthField() {
    return tf_monthField;
  }

  public void setTf_monthField(String tf_monthField) {
    this.tf_monthField = tf_monthField;
  }

  public String getTf_seasonField() {
    return tf_seasonField;
  }

  public void setTf_seasonField(String tf_seasonField) {
    this.tf_seasonField = tf_seasonField;
  }

  public String getTf_homePageTag() {
    return tf_homePageTag;
  }

  public void setTf_homePageTag(String tf_homePageTag) {
    this.tf_homePageTag = tf_homePageTag;
  }

  public String getTf_titleTpl() {
    return tf_titleTpl;
  }

  public void setTf_titleTpl(String tf_titleTpl) {
    this.tf_titleTpl = tf_titleTpl;
  }

  public String getTf_fileField() {
    return tf_fileField;
  }

  public void setTf_fileField(String tf_fileField) {
    this.tf_fileField = tf_fileField;
  }

  public Boolean getTf_isSystem() {
    return tf_isSystem;
  }

  public void setTf_isSystem(Boolean tf_isSystem) {
    this.tf_isSystem = tf_isSystem;
  }

  public Boolean getTf_isOnlyUsedSearch() {
    return tf_isOnlyUsedSearch;
  }

  public void setTf_isOnlyUsedSearch(Boolean tf_isOnlyUsedSearch) {
    this.tf_isOnlyUsedSearch = tf_isOnlyUsedSearch;
  }

  public Boolean getTf_isNotNean() {
    return tf_isNotNean;
  }

  public void setTf_isNotNean(Boolean tf_isNotNean) {
    this.tf_isNotNean = tf_isNotNean;
  }

  public List<_ModuleFieldConstraint> getModuleFieldConstraints() {
    return moduleFieldConstraints;
  }

  public void setModuleFieldConstraints(List<_ModuleFieldConstraint> moduleFieldConstraints) {
    this.moduleFieldConstraints = moduleFieldConstraints;
  }

  public Integer getTf_searchCondOrder() {
    return tf_searchCondOrder;
  }

  public void setTf_searchCondOrder(Integer tf_searchCondOrder) {
    this.tf_searchCondOrder = tf_searchCondOrder;
  }

  public List<GroupFieldDefine> getGroupFieldDefines() {
    return groupFieldDefines;
  }

  public void setGroupFieldDefines(List<GroupFieldDefine> groupFieldDefines) {
    this.groupFieldDefines = groupFieldDefines;
  }

  public List<_ModuleAdditionFunction> getTf_moduleAdditions() {
    return tf_moduleAdditions;
  }

  public void setTf_moduleAdditions(List<_ModuleAdditionFunction> tf_moduleAdditions) {
    this.tf_moduleAdditions = tf_moduleAdditions;
  }

  public List<_ModuleSubToolbar> getTf_moduleSubToolbar() {
    return tf_moduleSubToolbar;
  }

  public void setTf_moduleSubToolbar(List<_ModuleSubToolbar> tf_moduleSubToolbar) {
    this.tf_moduleSubToolbar = tf_moduleSubToolbar;
  }

  public List<_ModuleField> getTf_fields() {
    return tf_fields;
  }

  public void setTf_fields(List<_ModuleField> tf_fields) {
    this.tf_fields = tf_fields;
  }

  public List<_ModuleFormScheme> getTf_formSchemes() {
    return tf_formSchemes;
  }

  public void setTf_formSchemes(List<_ModuleFormScheme> tf_formSchemes) {
    this.tf_formSchemes = tf_formSchemes;
  }

  public List<_ModuleGridScheme> getTf_gridSchemes() {
    return tf_gridSchemes;
  }

  public void setTf_gridSchemes(List<_ModuleGridScheme> tf_gridSchemes) {
    this.tf_gridSchemes = tf_gridSchemes;
  }

  public String getTf_iconCls() {
    return tf_iconCls;
  }

  public void setTf_iconCls(String tf_iconCls) {
    this.tf_iconCls = tf_iconCls;
  }

}
