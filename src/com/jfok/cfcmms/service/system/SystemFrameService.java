package com.jfok.cfcmms.service.system;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.Formula;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.DAO.SystemFrameDAO;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._ApproveAbstract;
import com.jfok.cfcmms.hibernate.superclass._AuditingAbstract;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.module._ModuleGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleDetailScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleFormSchemeGroupField;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridNavigate;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridScheme;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroupField;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.FieldType;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.module.FieldAggregationType;
import com.jfok.cfcmms.share.service.ISystemFrameService;

@Service
public class SystemFrameService implements ISystemFrameService {

  private static final Log log = LogFactory.getLog(SystemFrameService.class);

  @Resource
  private SystemBaseDAO systemBaseDAO;

  @Resource
  private SystemFrameDAO systemFrameDAO;

  @Resource
  private ModuleDAO moduleDAO;

  /**
   * 刷新当前模块的所有导航模块，会递归加入所有未在导航字段列表中的值，已经有的就不加入了
   * 
   * @param moduleId
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Integer refreshNavigateModule(String moduleId) {

    _Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
    @SuppressWarnings("unchecked")
    List<_ModuleGridNavigate> navigates = (List<_ModuleGridNavigate>) systemBaseDAO
        .findByProperty(_ModuleGridNavigate.class, "tf_Module.tf_moduleId", moduleId);
    int maxorder = 0;
    int count = 0;
    for (_ModuleGridNavigate na : navigates)
      if (maxorder < na.getTf_order())
        maxorder = na.getTf_order();
    maxorder++;
    for (_ModuleGridNavigate info : module.getCanNavigatePaths()) {
      boolean found = false;
      for (_ModuleGridNavigate na : navigates)
        if (na.getTf_fields().equals(info.getTf_fields())) {
          found = true;
          break;
        }
      if (!found) {
        _ModuleGridNavigate newNavigate = new _ModuleGridNavigate();
        newNavigate.setTf_Module(module);
        newNavigate.setTf_fields(info.getTf_fields());
        newNavigate.setTf_enabled(true);
        newNavigate.setTf_cascading(true);
        newNavigate.setTf_text(info.getTf_text());
        newNavigate.setTf_order(maxorder++);
        count++;
        systemBaseDAO.save(newNavigate);
      }
    }
    return count;
  }

  /**
   * 根据系统类的定义刷新当前模块的字段,在新增一个module 以后也可执行此过程
   * 
   * @param moduleId
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public Integer refreshModuleField(String moduleId) {
    _Module module = (_Module) systemBaseDAO.findByPropertyFirst(_Module.class, _Module.MODULEID,
        moduleId);
    String moduleName = module.getTf_moduleName();
    Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
    if (beanClass == null)
      return -1;
    TableDefine tableDefine = (TableDefine) beanClass.getAnnotation(TableDefine.class);
    if (tableDefine == null)
      return -1;
    // 取得本模块的字段的最大ＩＤ号
    Integer maxId = systemFrameDAO.getMaxModuleFieldId(module.getTf_moduleId());
    int result = refreshModuleField(beanClass, module, maxId);
    return result;
  }

  //
  private Integer refreshModuleField(Class<?> beanClass, _Module module, Integer maxId) {

    // 每一个数据库里没有的字段写入数据库
    int i = 0;
    Field[] fs = beanClass.getDeclaredFields();
    for (Field f : fs) {
      // if (!f.getName().startsWith("tf_"))
      // continue;
      // if (f.getAnnotation(Transient.class) != null)
      // continue;
      if (f.getAnnotation(FieldDefine.class) == null)
        continue;

      // module的idkeyfield
      Id id = f.getAnnotation(Id.class);
      if (id != null && !f.getName().equals(module.getTf_primaryKey())) {
        module.setTf_primaryKey(f.getName());
        systemBaseDAO.attachDirty(module, null);
      }
      // module的namefield
      FieldDefine fieldDefine = f.getAnnotation(FieldDefine.class);
      if (fieldDefine != null && fieldDefine.nameField()
          && !f.getName().equals(module.getTf_nameFields())) {
        module.setTf_nameFields(f.getName());
        systemBaseDAO.attachDirty(module, null);
      }

      _ModuleField moduleField = (_ModuleField) systemBaseDAO.findByPropertyFirstWithOtherCondition(
          _ModuleField.class, _ModuleField.FIELDNAME, f.getName(),
          " tf_moduleId = '" + module.getTf_moduleId() + "'");
      boolean isnew = false;
      // 如果不是新加的字段，那么就不修改东西，如果要修改，自己去界面中修改
      if (moduleField == null) {
        isnew = true;
        moduleField = new _ModuleField();
        moduleField.setTf_Module(module);
        moduleField.setTf_fieldId(maxId);
        maxId += 10;
        moduleField.setTf_fieldName(f.getName());
        moduleField.setTf_allowNew(true);
        moduleField.setTf_allowEdit(true);
        i++;

        if (fieldDefine != null) {
          moduleField.setTf_title(fieldDefine.title());
          moduleField.setTf_fieldGroup(fieldDefine.fieldGroup());
          moduleField.setTf_isHidden(fieldDefine.hidden());
          moduleField
              .setTf_remark(fieldDefine.remark().length() == 0 ? null : fieldDefine.remark());
          moduleField.setTf_fieldOrder(fieldDefine.number());

          moduleField.setTf_isMonetary(zeroToNull(fieldDefine.isMonetary()));
          moduleField.setTf_divisor(zeroToNull(fieldDefine.divisor()));
          moduleField.setTf_denominator(zeroToNull(fieldDefine.denominator()));
          moduleField.setTf_tooltipTpl(zeroToNull(fieldDefine.tooltipTpl()));
          moduleField.setTf_unitText(zeroToNull(fieldDefine.unitText()));

          moduleField.setTf_allowSummary(zeroToNull(fieldDefine.allowSummary()));
          moduleField.setTf_allowGroup(zeroToNull(fieldDefine.allowGroup()));
          moduleField.setTf_DBformula(zeroToNull(fieldDefine.formula()));

          moduleField.setTf_otherSetting(zeroToNull(fieldDefine.otherSetting()));

        } else
          moduleField.setTf_title(f.getName());

        Column column = f.getAnnotation(Column.class);
        if (column != null) {
          moduleField.setTf_isRequired(!column.nullable());
          moduleField.setTf_fieldLen(column.length() == 255 ? null : column.length());
          moduleField.setTf_DBfieldName(column.name());
          moduleField.setTf_allowNew(column.insertable());
          moduleField.setTf_allowEdit(column.updatable());
        }

        Formula formula = f.getAnnotation(Formula.class);
        if (formula != null) {
          moduleField.setTf_DBformula(formula.value());
          moduleField.setTf_allowNew(false);
          moduleField.setTf_allowEdit(false);
        }

        moduleField.setTf_fieldType(f.getType().getSimpleName());

        if (f.getAnnotation(ManyToOne.class) != null) {
          moduleField.setTf_fieldRelation("ManyToOne");
          JoinColumn joinColumn = f.getAnnotation(JoinColumn.class);
          if (joinColumn != null) {
            moduleField.setTf_allowNew(joinColumn.insertable());
            moduleField.setTf_allowEdit(joinColumn.updatable());
            moduleField.setTf_isRequired(!joinColumn.nullable());
            moduleField.setTf_showNavigatorTree(true);
          }
        }

        if (moduleField.getTf_fieldName().equals(_InputInfoAbstract.INPUTMEN)
            || moduleField.getTf_fieldName().equals(_InputInfoAbstract.INPUTDATE)) {
          moduleField.setTf_allowNew(false);
          moduleField.setTf_allowEdit(false);
          moduleField.setTf_isRequired(false);
        }

        if (f.getAnnotation(OneToOne.class) != null)
          moduleField.setTf_fieldRelation("OneToOne");
        if (f.getAnnotation(OneToMany.class) != null)
          moduleField.setTf_fieldRelation("OneToMany");
        
        if (f.getAnnotation(ManyToMany.class) != null)
          moduleField.setTf_fieldRelation("ManyToMany");
      }

      if (isnew)
        systemBaseDAO.save(moduleField);
      else
        systemBaseDAO.attachDirty(moduleField, null);
    }
    if (beanClass.getSuperclass() != null)
      return i + refreshModuleField(beanClass.getSuperclass(), module, maxId);
    else
      return i;
  }

  public String zeroToNull(String s) {
    if (s == null || s.length() == 0)
      return null;
    else
      return s;
  }

  public Boolean zeroToNull(Boolean s) {
    if (s == null || s == false)
      return null;
    else
      return s;
  }

  /**
   * 根据类名加入module 定义以及字段定义，生成grid form 的缺省
   * 
   * @param moduleName
   * @return
   */

  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public String addModuleWithName(String moduleName, Class<?> moduleClass,
      TableDefine tableDefine) {

    // 生成 模块文件
    _Module module = (_Module) systemBaseDAO.findByPropertyFirst(_Module.class, _Module.MODULENAME,
        moduleName);

    if (module == null) {
      module = new _Module();
      // 设置分组信息，先根据groupid找，再根据group找，如果groupid没找到，那么用group的名字新建一个分组
      module.setTf_ModuleGroup(get_ModuleGroup(tableDefine.groupId(), tableDefine.group()));
      module.setTf_moduleId(String.valueOf(tableDefine.id()));
      module.setTf_tableName(zeroToNull(tableDefine.tableName()));
      module.setTf_moduleName(moduleName);
      module.setTf_title(tableDefine.title());
      module.setTf_shortname(zeroToNull(tableDefine.shortName()));
      module.setTf_englishname(zeroToNull(tableDefine.englishName()));

      module.setTf_nameFields("undefined");
      module.setTf_primaryKey("undefined");
      module.setTf_titleTpl(zeroToNull(tableDefine.titleTpl()));
      module.setTf_codeLevel(zeroToNull(tableDefine.codeLevel()));

      module.setTf_orderField(zeroToNull(tableDefine.orderField()));
      module.setTf_orderFieldControlModule(zeroToNull(tableDefine.orderFieldControlModule()));
      module.setTf_defaultOrderField(zeroToNull(tableDefine.defaultOrderField()));
      module.setTf_dateField(zeroToNull(tableDefine.dateField()));
      module.setTf_yearField(zeroToNull(tableDefine.yearField()));
      module.setTf_monthField(zeroToNull(tableDefine.monthField()));
      module.setTf_seasonField(zeroToNull(tableDefine.seasonField()));
      module.setTf_fileField(zeroToNull(tableDefine.fileField()));

      module.setTf_isTreeModel(zeroToNull(tableDefine.isTreeModel()));
      module.setTf_parentKey(zeroToNull(tableDefine.parentKey()));

      module.setTf_linkedModule(zeroToNull(tableDefine.linkModule()));

      module.setTf_hasAttachment(tableDefine.attachment());

      // 模块的权限的一些设置
      module.setTf_isEnable(tableDefine.isEnable());
      module.setTf_hasBrowse(tableDefine.hasBrowse());
      module.setTf_hasInsert(tableDefine.hasInsert());
      module.setTf_hasEdit(tableDefine.hasEdit());
      module.setTf_hasDelete(tableDefine.hasDelete());
      module.setTf_hasExec(tableDefine.hasExec());
      module.setTf_hasChart(tableDefine.hasChart());
      module.setTf_hasAuditing(tableDefine.hasAuditing());
      module.setTf_hasApprove(tableDefine.hasApprove());
      module.setTf_hasPayment(tableDefine.hasPayment());

      module.setTf_isSystem(tableDefine.isSystem());
      module.setTf_canLimit(tableDefine.canLimit());

      module.setTf_isOnlyUsedSearch(zeroToNull(tableDefine.isOnlyUsedSearch()));
      module.setTf_isNotNean(tableDefine.isNotBean());

      module.setTf_isInlineOper(false);
      module.setTf_requestMapping("undefined");

      module.setTf_description(zeroToNull(tableDefine.description()));
      module.setTf_remark(zeroToNull(tableDefine.remark()));

      systemBaseDAO.save(module);
    } else
      return "指定的类记录已经生成过了！";

    return null;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public Boolean createNewGridScheme(String moduleId, Class<?> moduleClass) {
    _Module module = (_Module) systemBaseDAO.findById(_Module.class, moduleId);
    if (module == null)
      return false;
    _ModuleGridScheme scheme = new _ModuleGridScheme();
    scheme.setTf_schemeName(module.getTf_title() + "列表");
    scheme.setTf_Module(module);
    scheme.setTf_schemeOrder(systemFrameDAO.getNextGridSchemeOrder(moduleId));
    systemBaseDAO.save(scheme);

    _ModuleGridSchemeGroup schemeGroup = new _ModuleGridSchemeGroup();
    schemeGroup.setTf_gridGroupOrder(1);
    schemeGroup.setTf_ModuleGridScheme(scheme);
    schemeGroup.setTf_gridGroupName(module.getTf_title());
    systemBaseDAO.save(schemeGroup);

    List<_ModuleField> fields = systemFrameDAO.get_ModuleFields(moduleId);

    int order = 10;
    for (_ModuleField field : fields) {
      if (field.getTf_isHidden())
        continue;
      // 审批的不加入，加入另外的组
      if (!(field.getTf_fieldOrder() >= 1000 && (field.getTf_fieldName().startsWith("tf_sh")
          || field.getTf_fieldName().startsWith("tf_auditing")))) {
        _ModuleGridSchemeGroupField groupField = new _ModuleGridSchemeGroupField();
        groupField.setTf_ModuleField(field);
        if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
          groupField.setTf_gridFieldOrder(field.getTf_fieldOrder());
        else {
          groupField.setTf_gridFieldOrder(order);
          order += 10;
        }
        groupField.setTf_ModuleGridSchemeGroup(schemeGroup);
        systemBaseDAO.save(groupField);
      }
    }

    // if (_AuditingAbstract.class.isAssignableFrom(moduleClass)) {
    // // 审核组
    // schemeGroup = new _ModuleGridSchemeGroup();
    // schemeGroup.setTf_gridGroupOrder(8);
    // schemeGroup.setTf_ModuleGridScheme(scheme);
    // schemeGroup.setTf_isShowHeaderSpans(true);
    // schemeGroup.setTf_gridGroupName("审核情况");
    // systemBaseDAO.save(schemeGroup);
    // order = 10;
    // for (_ModuleField field : fields) {
    // if ((field.getTf_fieldOrder() >= 2010 && field.getTf_fieldOrder() <=
    // 2040)) {
    // _ModuleGridSchemeGroupField groupField = new
    // _ModuleGridSchemeGroupField();
    // groupField.setTf_ModuleField(field);
    // groupField.setTf_ModuleGridSchemeGroup(schemeGroup);
    // if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
    // groupField.setTf_gridFieldOrder(field.getTf_fieldOrder());
    // else {
    // groupField.setTf_gridFieldOrder(order);
    // order += 10;
    // }
    // systemBaseDAO.save(groupField);
    // }
    // }
    // }

    // if (_ApproveAbstract.class.isAssignableFrom(moduleClass))
    //
    // // 审批组
    // for (int i = 1; i <= 6; i++) {
    // schemeGroup = new _ModuleGridSchemeGroup();
    // schemeGroup.setTf_gridGroupOrder(10 + i);
    // schemeGroup.setTf_ModuleGridScheme(scheme);
    // schemeGroup.setTf_isShowHeaderSpans(true);
    // schemeGroup.setTf_gridGroupName("第" + i + "级审批");
    // systemBaseDAO.save(schemeGroup);
    // order = 10;
    // for (_ModuleField field : fields) {
    // if ((field.getTf_fieldOrder() >= 1000 + i * 100
    // && field.getTf_fieldOrder() < 1100 + i * 100)) {
    // _ModuleGridSchemeGroupField groupField = new
    // _ModuleGridSchemeGroupField();
    // groupField.setTf_ModuleField(field);
    // groupField.setTf_ModuleGridSchemeGroup(schemeGroup);
    // if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
    // groupField.setTf_gridFieldOrder(field.getTf_fieldOrder());
    // else {
    // groupField.setTf_gridFieldOrder(order);
    // order += 10;
    // }
    // systemBaseDAO.save(groupField);
    // }
    // }
    // }
    return true;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public Boolean createNewFormScheme(String moduleId, Class<?> moduleClass) {
    _Module module = (_Module) systemBaseDAO.findById(_Module.class, moduleId);
    if (module == null)
      return false;
    _ModuleFormScheme scheme = new _ModuleFormScheme();
    scheme.setTf_schemeName(module.getTf_title() + "Form");
    scheme.setTf_Module(module);
    scheme.setTf_windowHeight(-1);
    scheme.setTf_windowWidth(600);
    scheme.setTf_numCols(2);
    scheme.setTf_schemeOrder(systemFrameDAO.getNextFormSchemeOrder(moduleId));
    systemBaseDAO.save(scheme);

    _ModuleFormSchemeGroup schemeGroup = new _ModuleFormSchemeGroup();
    schemeGroup.setTf_formGroupOrder(1);
    schemeGroup.setTf_ModuleFormScheme(scheme);
    schemeGroup.setTf_formGroupName(module.getTf_title());
    systemBaseDAO.save(schemeGroup);

    List<_ModuleField> fields = systemFrameDAO.get_ModuleFields(moduleId);

    int order = 10;
    for (_ModuleField field : fields) {
      if (!(field.getTf_fieldOrder() >= 1000 && (field.getTf_fieldName().startsWith("tf_sh")
          || field.getTf_fieldName().startsWith("tf_auditing")))) {
        _ModuleFormSchemeGroupField groupField = new _ModuleFormSchemeGroupField();
        groupField.setTf_ModuleField(field);
        groupField.setTf_ModuleFormSchemeGroup(schemeGroup);
        if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
          groupField.setTf_formFieldOrder(field.getTf_fieldOrder());
        else {
          groupField.setTf_formFieldOrder(order);
          order += 10;
        }
        systemBaseDAO.save(groupField);
      }
    }

    // 审核组
    if (_AuditingAbstract.class.isAssignableFrom(moduleClass)) {
      schemeGroup = new _ModuleFormSchemeGroup();
      schemeGroup.setTf_formGroupOrder(8);
      schemeGroup.setTf_ModuleFormScheme(scheme);
      schemeGroup.setTf_auditingGroup(true);
      schemeGroup.setTf_numCols(2);
      schemeGroup.setTf_collapsible(true);
      schemeGroup.setTf_formGroupName("审核情况");
      systemBaseDAO.save(schemeGroup);
      order = 10;
      for (_ModuleField field : fields) {
        if ((field.getTf_fieldOrder() >= 2010 && field.getTf_fieldOrder() <= 2040)) {
          _ModuleFormSchemeGroupField groupField = new _ModuleFormSchemeGroupField();
          groupField.setTf_ModuleField(field);
          groupField.setTf_ModuleFormSchemeGroup(schemeGroup);
          if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
            groupField.setTf_formFieldOrder(field.getTf_fieldOrder());
          else {
            groupField.setTf_formFieldOrder(order);
            order += 10;
          }
          systemBaseDAO.save(groupField);
        }
      }
    }

    // 审批组
    if (_ApproveAbstract.class.isAssignableFrom(moduleClass))
      for (int i = 1; i <= 6; i++) {
        schemeGroup = new _ModuleFormSchemeGroup();
        schemeGroup.setTf_formGroupOrder(10 + i);
        schemeGroup.setTf_ModuleFormScheme(scheme);
        schemeGroup.setTf_approveGroup(true);
        schemeGroup.setTf_numCols(3);
        schemeGroup.setTf_collapsible(true);
        schemeGroup.setTf_formGroupName("第" + i + "级审批");
        systemBaseDAO.save(schemeGroup);
        order = 10;
        for (_ModuleField field : fields) {
          if ((field.getTf_fieldOrder() >= 1000 + i * 100
              && field.getTf_fieldOrder() < 1100 + i * 100)) {
            _ModuleFormSchemeGroupField groupField = new _ModuleFormSchemeGroupField();
            groupField.setTf_ModuleField(field);
            groupField.setTf_ModuleFormSchemeGroup(schemeGroup);
            if (field.getTf_fieldOrder() != null && field.getTf_fieldOrder() > 0)
              groupField.setTf_formFieldOrder(field.getTf_fieldOrder());
            else {
              groupField.setTf_formFieldOrder(order);
              order += 10;
            }
            systemBaseDAO.save(groupField);
          }
        }
      }
    return true;
  }

  /**
   * 取得一个模块分组，如果没有，则创建一个分组
   * 
   * @param groupTitle
   * @return
   */
  private _ModuleGroup get_ModuleGroup(String groupId, String groupTitle) {

    // 先根据groupid找模块分组
    _ModuleGroup moduleGroup = (_ModuleGroup) systemBaseDAO.findById(_ModuleGroup.class, groupId);
    if (moduleGroup == null) {
      // 如果没找到，那么根据分组名称找到第一个匹配的
      moduleGroup = (_ModuleGroup) systemBaseDAO.findByPropertyFirst(_ModuleGroup.class,
          _ModuleGroup.TITLE, groupTitle);
      // 如果没有找到的话，就新建一个分组
      if (moduleGroup == null) {
        moduleGroup = new _ModuleGroup();
        // 如果没有设置groupid,就用 groupTitle 去代替
        moduleGroup.setTf_moduleGroupId(groupId.length() > 0 ? groupId
            : (groupTitle.length() > 10 ? groupTitle.substring(0, 10) : groupTitle));
        moduleGroup.setTf_title(groupTitle);
        systemBaseDAO.save(moduleGroup);
      }
    }
    return moduleGroup;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public Boolean saveAdditionFields(String moduleName, String noderecords) {
    if (noderecords != null && noderecords.length() > 10) {
      JsonConfig config = new JsonConfig();
      config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
      config.setRootClass(TreeNodeRecordChecked.class);
      TreeNodeRecordChecked[] records = (TreeNodeRecordChecked[]) JSONSerializer
          .toJava(JSONArray.fromObject(noderecords), config);

      _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
      for (TreeNodeRecordChecked record : records) {
        if (record.getTag() >= 0)
          systemFrameDAO.addorDeleteAdditionField(module.getTf_moduleId(),
              Integer.parseInt(record.getFieldvalue()),
              FieldAggregationType.values()[record.getTag()], record.getChecked());
      }
    }
    return true;
  }

  /**
   * 根据模块名称，生成模块－－字段，的一棵树，可以让用户选择当前的grid的字段的列表方案， 只加入当前模块的父模块的所有字段
   * manytoOne,oneToone 以及 oneTomany 中的聚合字段
   * 
   * @param moduleName
   * @return 可选择字段的列表
   */
  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public List<TreeNodeRecordChecked> getAllAdditionFields(String moduleName) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    String moduleId = module.getTf_moduleId();
    TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有可选择字段", null, null, null,
        null);
    root.setLeaf(false);
    // addModuleNameAndFieldtoTree(result, module);
    List<_Module> pModules = module.getAllParentsList();
    for (int i = 0; i < pModules.size(); i++) {
      root.getChildren().add(addModuleNameAndFieldtoTree(pModules.get(i), moduleId));
    }
    // 加入所有的childOneToOne 模块的字段
    for (_Module m : module.getChildOneToOnes())
      root.getChildren().add(addModuleNameAndFieldtoTree(m, moduleId));
    // 加入所有的parentOneToOne 模块的字段
    for (_Module m : module.getParentOneToOnes())
      root.getChildren().add(addModuleNameAndFieldtoTree(m, moduleId));
    // 加入所有可以统计个数的子模块
    pModules = module.getAllChildsList();
    TreeNodeRecordChecked countNode = new TreeNodeRecordChecked(null,
        FieldAggregationType.COUNT.getValue(), "可计数的模块", null, null, null, null);
    countNode.setLeaf(false);
    int count = 0;
    for (int i = 0; i < pModules.size(); i++) {
      TreeNodeRecordChecked node = new TreeNodeRecordChecked(pModules.get(i).getTf_moduleName(),
          null, pModules.get(i).getTf_title(), "count",
          pModules.get(i).getPrimaryKeyField().getTf_fieldId().toString(), null, null);
      node.setTag(FieldAggregationType.COUNT.ordinal()); // 表示是计数的

      if (systemFrameDAO.isModuleHasAdditionField(moduleId,
          pModules.get(i).getPrimaryKeyField().getTf_fieldId(), FieldAggregationType.COUNT))
        node.setChecked(true);

      count++;
      countNode.getChildren().add(node);
    }
    if (count > 0)
      root.getChildren().add(countNode);

    for (FieldAggregationType type : FieldAggregationType.AGGREGATION.keySet()) {
      // 加入可以求合的所有子模块的字段
      TreeNodeRecordChecked sumNode = new TreeNodeRecordChecked(type.getValue(), null,
          "可" + FieldAggregationType.AGGREGATION.get(type) + "的字段", null, null, null, null);
      sumNode.setLeaf(false);

      count = 0;
      for (int i = 0; i < pModules.size(); i++) {
        TreeNodeRecordChecked r = addModuleNameAndsumFieldtoTree(pModules.get(i), moduleId, type);
        if (r != null)
          sumNode.getChildren().add(r);
      }
      // if (sumNode.getChildren().size() > 0)
      root.getChildren().add(sumNode);
    }
    List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
    autoAdjustParentChecked(root);
    result.add(root);
    return result;
  }

  /**
   * 将一个模块的所有可以求和的字称，加入到树形菜单中
   * 
   * @param tree
   * @param module
   * @param moduleId
   *          返回加入了多少个
   */
  private TreeNodeRecordChecked addModuleNameAndsumFieldtoTree(_Module module, String moduleId,
      FieldAggregationType type) {
    TreeNodeRecordChecked moduleRoot = new TreeNodeRecordChecked(module.getTf_moduleName(),
        module.getTableAsName(), module.getTf_title(), type.getValue(), null, null, null);
    moduleRoot.setLeaf(false);
    for (_ModuleField field : module.getTf_fields()) {
      if (field.getTf_allowAggregate()) {
        TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
            module.getTableAsName(), field.getTf_title(), module.getTf_primaryKey(),
            field.getTf_fieldId().toString(), null, null);
        record.setTag(type.ordinal()); // 表示是求和的
        if (systemFrameDAO.isModuleHasAdditionField(moduleId, field.getTf_fieldId(), type))
          record.setChecked(true);
        moduleRoot.getChildren().add(record);
      }
    }
    if (moduleRoot.getChildren().size() > 0)
      return moduleRoot;
    else
      return null;
  }

  /**
   * 将一个模块的名称及其所有字段，加入到树形菜单中 ,请注意要将manytoOne的字段全部取消，因为此manytoone
   * 也将在下面的列表中出现，可以选择id 和 name 字段
   * 
   * @param tree
   * @param module
   * @param moduleId
   */
  private TreeNodeRecordChecked addModuleNameAndFieldtoTree(_Module module, String moduleId) {

    TreeNodeRecordChecked moduleRoot = new TreeNodeRecordChecked(module.getTf_moduleName(),
        module.getTableAsName(), module.getTf_title(), null, null, null, null);
    moduleRoot.setLeaf(false);
    for (_ModuleField field : module.getTf_fields()) {
      if (!field.isManyToOne()) {
        TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
            module.getTableAsName(), field.getTf_title(), field.getTf_fieldName(),
            field.getTf_fieldId().toString(), null, null);
        record.setTag(FieldAggregationType.NORMAL.ordinal()); // 表示是正常的字段
        if (systemFrameDAO.isModuleHasAdditionField(moduleId, field.getTf_fieldId(),
            FieldAggregationType.NORMAL))
          record.setChecked(true);
        moduleRoot.getChildren().add(record);
      }
    }
    return moduleRoot;
  }

  /**
   * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
   * 
   * @param detailId
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<TreeNodeRecordChecked> getDetailGroupFields(String detailId) {
    log.debug("需要取得 detail group 的字段");
    Session session = systemBaseDAO.getSessionFactory().openSession();
    session.beginTransaction();
    TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有可选择字段", null, null, null,
        null);
    root.setLeaf(false);
    try {
      _ModuleDetailScheme group = (_ModuleDetailScheme) session.get(_ModuleDetailScheme.class,
          Integer.parseInt(detailId));
      _Module module = SystemAndLoginInfoService
          .getModuleWithName(group.getTf_Module().getTf_moduleName());
      // 加入本模块所有可以使用的字段
      TreeNodeRecordChecked r = addModuleNameAndGroupFieldtoTree(module, null,
          Integer.parseInt(detailId), true, "detail", group);
      if (r != null)
        root.getChildren().add(r);
      // 加入所有父模块的字段
      List<_Module> pModules = module.getAllParentsList();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(detailId), false, "detail", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有childOneToone字段
      pModules = module.getChildOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(detailId), false, "detail", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有parentOneToone字段
      pModules = module.getParentOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(detailId), false, "detail", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入子模块的count 字段
      pModules = module.getAllChildsList();
      TreeNodeRecordChecked countNode = new TreeNodeRecordChecked(
          FieldAggregationType.COUNT.getValue(), null, "可计数的模块", null, null, null, null);
      countNode.setLeaf(false);
      for (int j = 0; j < pModules.size(); j++)
        if (systemFrameDAO.isModuleHasAdditionField(module.getTf_moduleId(),
            pModules.get(j).getPrimaryKeyField().getTf_fieldId(), FieldAggregationType.COUNT)) {
          TreeNodeRecordChecked node = new TreeNodeRecordChecked(pModules.get(j).getTf_moduleName(),
              null, pModules.get(j).getTf_title(), null,
              pModules.get(j).getPrimaryKeyField().getTf_fieldId().toString(), null, null);
          node.setTag(FieldAggregationType.COUNT.ordinal()); // 表示是计数的
          // 查找上count 字段是不是已经被选中了
          if (systemFrameDAO.isDetailGroupHasField(Integer.parseInt(detailId),
              pModules.get(j).getPrimaryKeyField().getTf_fieldId()))
            node.setChecked(true);
          countNode.getChildren().add(node);
        }
      if (countNode.getChildren().size() > 0)
        root.getChildren().add(countNode);

      // ////
      // 加入子模块的已经选中可以被此模块使用的 sum 字段
      String moduleId = module.getTf_moduleId();

      TreeNodeRecordChecked sumNode = new TreeNodeRecordChecked(FieldAggregationType.SUM.getValue(),
          null, "可求和的字段", null, null, null, null);
      sumNode.setLeaf(false);
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndSelectedSumFieldtoTree(pModules.get(i), moduleId, group,
            FieldAggregationType.SUM, true);
        if (r != null)
          sumNode.getChildren().add(r);
      }
      if (sumNode.getChildren().size() > 0)
        root.getChildren().add(sumNode);
    } finally {
      session.getTransaction().commit();
      session.close();
    }
    List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
    autoAdjustParentChecked(root);
    result.add(root);
    return result;
  }

  /**
   * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
   * 
   * @param GridGroupId
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  @Override
  public List<TreeNodeRecordChecked> getGridGroupFields(String gridGroupId) {
    log.debug("需要取得grid group 的字段");
    Session session = systemBaseDAO.getSessionFactory().openSession();
    session.beginTransaction();
    TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有可选择字段", null, null, null,
        null);
    root.setLeaf(false);
    try {
      _ModuleGridSchemeGroup group = (_ModuleGridSchemeGroup) session
          .get(_ModuleGridSchemeGroup.class, Integer.parseInt(gridGroupId));
      _Module module = SystemAndLoginInfoService
          .getModuleWithName(group.getTf_ModuleGridScheme().getTf_Module().getTf_moduleName());
      // 加入本模块所有可以使用的字段
      TreeNodeRecordChecked r = addModuleNameAndGroupFieldtoTree(module, null,
          Integer.parseInt(gridGroupId), true, "grid", group);
      if (r != null)
        root.getChildren().add(r);
      // 加入所有父模块的字段
      List<_Module> pModules = module.getAllParentsList();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(gridGroupId), false, "grid", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有childOneToone字段
      pModules = module.getChildOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(gridGroupId), false, "grid", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有parentOneToone字段
      pModules = module.getParentOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(gridGroupId), false, "grid", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入子模块的count 字段
      pModules = module.getAllChildsList();
      TreeNodeRecordChecked countNode = new TreeNodeRecordChecked(
          FieldAggregationType.COUNT.getValue(), null, "可计数的模块", null, null, null, null);
      countNode.setLeaf(false);
      for (int j = 0; j < pModules.size(); j++)
        if (systemFrameDAO.isModuleHasAdditionField(module.getTf_moduleId(),
            pModules.get(j).getPrimaryKeyField().getTf_fieldId(), FieldAggregationType.COUNT)) {
          TreeNodeRecordChecked node = new TreeNodeRecordChecked(pModules.get(j).getTf_moduleName(),
              null, pModules.get(j).getTf_title(), null,
              pModules.get(j).getPrimaryKeyField().getTf_fieldId().toString(), null, null);
          node.setAggregationType(FieldAggregationType.COUNT.getValue());
          node.setTag(FieldAggregationType.COUNT.ordinal()); // 表示是计数的
          // 查找上count 字段是不是已经被选中了
          if (systemFrameDAO.isGridGroupHasField(group,
              pModules.get(j).getPrimaryKeyField().getTf_fieldId(), node,
              FieldAggregationType.COUNT))
            node.setChecked(true);
          countNode.getChildren().add(node);
        }
      if (countNode.getChildren().size() > 0)
        root.getChildren().add(countNode);

      // ////
      // 加入子模块的已经选中可以被此模块使用的 sum 字段
      String moduleId = module.getTf_moduleId();

      for (FieldAggregationType type : FieldAggregationType.AGGREGATION.keySet()) {

        TreeNodeRecordChecked sumNode = new TreeNodeRecordChecked(type.getValue(), null,
            "可" + FieldAggregationType.AGGREGATION.get(type) + "的字段", null, null, null, null);
        sumNode.setLeaf(false);
        for (int i = 0; i < pModules.size(); i++) {
          r = addModuleNameAndSelectedSumFieldtoTree(pModules.get(i), moduleId, group, type, true);
          if (r != null)
            sumNode.getChildren().add(r);
        }
        if (sumNode.getChildren().size() > 0)
          root.getChildren().add(sumNode);

      }

      // TreeNodeRecordChecked sumNode = new TreeNodeRecordChecked(
      // FieldAggregationType.SUM.getValue(), null, "可求和的字段", null, null,
      // null,
      // null);
      // sumNode.setLeaf(false);
      // for (int i = 0; i < pModules.size(); i++) {
      // r = addModuleNameAndSelectedSumFieldtoTree(pModules.get(i), moduleId,
      // group, true);
      // if (r != null)
      // sumNode.getChildren().add(r);
      // }
      // if (sumNode.getChildren().size() > 0)
      // root.getChildren().add(sumNode);

    } finally {
      session.getTransaction().commit();
      session.close();
    }
    List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
    autoAdjustParentChecked(root);
    result.add(root);
    return result;
  }

  /**
   * 将一个模块的所有的被加到父模块的 sum 字段加到tree中，并且根据gridSchemeGroupid
   * 的值为判断此sum是否被选中到gridSchemeGroup下面
   * 
   * @param tree
   * @param module
   * @param moduleId
   *          返回加入了多少个
   */
  private TreeNodeRecordChecked addModuleNameAndSelectedSumFieldtoTree(_Module module,
      String moduleId, Object group, FieldAggregationType type, Boolean isgrid) {
    TreeNodeRecordChecked moduleRoot = new TreeNodeRecordChecked(module.getTf_moduleName(),
        module.getTableAsName(), module.getTf_title(), type.getValue(), null, null, null);
    moduleRoot.setLeaf(false);
    for (_ModuleField field : module.getTf_fields()) {
      if (field.getTf_fieldType().equals(FieldType.Double) || field.getTf_allowSummary()) {
        if (systemFrameDAO.isModuleHasAdditionField(moduleId, field.getTf_fieldId(), type)) {
          TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
              module.getTableAsName(), field.getTf_title(), module.getTf_primaryKey(),
              field.getTf_fieldId().toString(), null, null);
          record.setTag(type.ordinal()); // 表示是求和的
          record.setAggregationType(type.getValue());
          if (isgrid) {
            systemFrameDAO.isGridGroupHasField((_ModuleGridSchemeGroup) (group),
                field.getTf_fieldId(), record, type);
          } else {
            systemFrameDAO.isFormGroupHasField((_ModuleFormSchemeGroup) (group),
                field.getTf_fieldId(), record);
          }
          moduleRoot.getChildren().add(record);
        }
      }
    }
    if (moduleRoot.getChildren().size() > 0)
      return moduleRoot;
    else
      return null;
  }

  /**
   * 将一个父模块的名称及其所有被选中的可加入到child 中的字段，加入到树形菜单中
   * 
   * @param tree
   * @param module
   * @param moduleId
   */
  private TreeNodeRecordChecked addModuleNameAndGroupFieldtoTree(_Module module, String moduleId,
      Integer gridGroupId, boolean isThisModule, String what, Object group) {
    TreeNodeRecordChecked moduleRoot = new TreeNodeRecordChecked(module.getTf_moduleName(),
        module.getTableAsName(), module.getTf_title(), null, null, null, null);
    moduleRoot.setLeaf(false);
    for (_ModuleField field : module.getTf_fields()) {
      if (isThisModule || systemFrameDAO.isModuleHasAdditionField(moduleId, field.getTf_fieldId(),
          FieldAggregationType.NORMAL)) {
        TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
            module.getTableAsName(), field.getTf_title(), module.getTf_primaryKey(),
            field.getTf_fieldId().toString(), null, null);
        record.setTag(FieldAggregationType.NORMAL.ordinal()); // 表示是正常的字段
        if (what.equals("grid")) {
          systemFrameDAO.isGridGroupHasField((_ModuleGridSchemeGroup) group, field.getTf_fieldId(),
              record, null);
        } else if (what.equals("form")) {
          systemFrameDAO.isFormGroupHasField((_ModuleFormSchemeGroup) group, field.getTf_fieldId(),
              record);
        } else if (what.equals("detail")) {
          if (systemFrameDAO.isDetailGroupHasField(gridGroupId, field.getTf_fieldId()))
            record.setChecked(true);
        }
        moduleRoot.getChildren().add(record);
      }
    }
    if (moduleRoot.getChildren().size() > 0)
      return moduleRoot;
    else
      return null;
  }

  /**
   * 
   */
  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public Boolean saveGridGroupFields(String gridGroupId, String noderecords) {
    if (noderecords != null && noderecords.length() > 10) {
      JsonConfig config = new JsonConfig();
      config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
      config.setRootClass(TreeNodeRecordChecked.class);
      TreeNodeRecordChecked[] records = (TreeNodeRecordChecked[]) JSONSerializer
          .toJava(JSONArray.fromObject(noderecords), config);

      for (TreeNodeRecordChecked record : records) {
        if (record.getFieldvalue() != null)
          systemFrameDAO.addorDeleteGridGroupFields(Integer.parseInt(gridGroupId),
              Integer.parseInt(record.getFieldvalue()), record.getAggregationType(),
              record.getChecked());
      }
    }
    SystemAndLoginInfoService.setRefreshAll(true);
    return true;
  }

  /**
   * 
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public Boolean saveDetailGroupFields(String detailId, String noderecords) {
    if (noderecords != null && noderecords.length() > 10) {
      JsonConfig config = new JsonConfig();
      config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
      config.setRootClass(TreeNodeRecordChecked.class);
      TreeNodeRecordChecked[] records = (TreeNodeRecordChecked[]) JSONSerializer
          .toJava(JSONArray.fromObject(noderecords), config);

      for (TreeNodeRecordChecked record : records) {
        if (record.getFieldvalue() != null)
          systemFrameDAO.addorDeleteDetailGroupFields(Integer.parseInt(detailId),
              Integer.parseInt(record.getFieldvalue()), record.getChecked());
      }
    }
    SystemAndLoginInfoService.setRefreshAll(true);
    return true;
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  @Override
  public List<TreeNodeRecordChecked> getFormGroupFields(String formGroupId) {

    log.debug("需要取得grid group 的字段");
    Session session = systemBaseDAO.getSessionFactory().openSession();
    session.beginTransaction();
    TreeNodeRecordChecked root = new TreeNodeRecordChecked(null, null, "所有可选择字段", null, null, null,
        null);
    root.setLeaf(false);
    try {
      _ModuleFormSchemeGroup group = (_ModuleFormSchemeGroup) session
          .get(_ModuleFormSchemeGroup.class, Integer.parseInt(formGroupId));
      _Module module = SystemAndLoginInfoService
          .getModuleWithName(group.getTf_ModuleFormScheme().getTf_Module().getTf_moduleName());
      // 加入本模块所有可以使用的字段
      TreeNodeRecordChecked r = addModuleNameAndGroupFieldtoTree(module, null,
          Integer.parseInt(formGroupId), true, "form", group);
      if (r != null)
        root.getChildren().add(r);

      // 加入所有父模块的字段
      List<_Module> pModules = module.getAllParentsList();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(formGroupId), false, "form", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有childOneToone字段
      pModules = module.getChildOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(formGroupId), false, "form", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入所有parentOneToone字段
      pModules = module.getParentOneToOnes();
      for (int i = 0; i < pModules.size(); i++) {
        r = addModuleNameAndGroupFieldtoTree(pModules.get(i), module.getTf_moduleId(),
            Integer.parseInt(formGroupId), false, "form", group);
        if (r != null)
          root.getChildren().add(r);
      }

      // 加入子模块的count 字段
      pModules = module.getAllChildsList();
      TreeNodeRecordChecked countNode = new TreeNodeRecordChecked(
          FieldAggregationType.COUNT.getValue(), null, "可计数的模块", null, null, null, null);
      countNode.setLeaf(false);
      for (int j = 0; j < pModules.size(); j++) {
        System.out.println(pModules.get(j).getTf_moduleName());
        if (systemFrameDAO.isModuleHasAdditionField(module.getTf_moduleId(),
            pModules.get(j).getPrimaryKeyField().getTf_fieldId(), FieldAggregationType.COUNT)) {
          TreeNodeRecordChecked node = new TreeNodeRecordChecked(pModules.get(j).getTf_moduleName(),
              null, pModules.get(j).getTf_title(), null,
              pModules.get(j).getPrimaryKeyField().getTf_fieldId().toString(), null, null);
          node.setTag(FieldAggregationType.COUNT.ordinal()); // 表示是计数的
          // 查找上count 字段是不是已经被选中了
          systemFrameDAO.isFormGroupHasField((_ModuleFormSchemeGroup) group,
              pModules.get(j).getPrimaryKeyField().getTf_fieldId(), node);

          countNode.getChildren().add(node);
        }
      }
      if (countNode.getChildren().size() > 0)
        root.getChildren().add(countNode);
      // ////
      // 加入子模块的已经选中可以被此模块使用的 sum 字段
      String moduleId = module.getTf_moduleId();

      for (FieldAggregationType type : FieldAggregationType.AGGREGATION.keySet()) {

        TreeNodeRecordChecked sumNode = new TreeNodeRecordChecked(
            FieldAggregationType.SUM.getValue(), null,
            "可" + FieldAggregationType.AGGREGATION.get(type) + "的字段", null, null, null, null);
        for (int i = 0; i < pModules.size(); i++) {
          r = addModuleNameAndSelectedSumFieldtoTree(pModules.get(i), moduleId, group, type, false);
          if (r != null)
            sumNode.getChildren().add(r);
        }
        if (sumNode.getChildren().size() > 0)
          root.getChildren().add(sumNode);
      }
    } finally {
      session.getTransaction().commit();
      session.close();
    }
    List<TreeNodeRecordChecked> result = new ArrayList<TreeNodeRecordChecked>();
    autoAdjustParentChecked(root);
    result.add(root);
    return result;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  @Override
  public Boolean saveFormGroupFields(String formGroupId, String noderecords) {
    if (noderecords != null && noderecords.length() > 10) {
      JsonConfig config = new JsonConfig();
      config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
      config.setRootClass(TreeNodeRecordChecked.class);
      TreeNodeRecordChecked[] records = (TreeNodeRecordChecked[]) JSONSerializer
          .toJava(JSONArray.fromObject(noderecords), config);

      for (TreeNodeRecordChecked record : records) {
        if (record.getFieldvalue() != null)
          systemFrameDAO.addorDeleteFormGroupFields(Integer.parseInt(formGroupId),
              Integer.parseInt(record.getFieldvalue()), record.getChecked());
      }
    }
    SystemAndLoginInfoService.setRefreshAll(true);
    return true;

  }

  /**
   * 根据子node 的checkd 来确定本级是否是checked ,先递归到最深度，然后再返回
   * 
   * @param node
   * @return
   */
  private boolean autoAdjustParentChecked(TreeNodeRecordChecked node) {
    boolean checked = true;
    if (node.hasChildren()) {
      for (TreeNodeRecord c : node.getChildren())
        checked = checked && autoAdjustParentChecked((TreeNodeRecordChecked) c);
      node.setChecked(checked);
    }
    return node.getChecked();
  }

}
