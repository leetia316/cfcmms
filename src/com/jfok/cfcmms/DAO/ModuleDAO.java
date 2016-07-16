package com.jfok.cfcmms.DAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import ognl.Ognl;
import ognl.OgnlException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LobHelper;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.core.module.SqlField;
import com.jfok.cfcmms.core.module.SqlGenerator;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment;
import com.jfok.cfcmms.hibernate.system.log._SystemOperateLog;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.TreeValueText;
import com.jfok.cfcmms.share.ValueText;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.DataFetchRequestInfo;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;
import com.jfok.cfcmms.share.module.ModuleConstants;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.TypeChange;
import com.jfok.cfcmms.util.json.JsonDateProcessor;

@Repository
public class ModuleDAO {

  private static final Log log = LogFactory.getLog(ModuleDAO.class);

  @Autowired
  private SystemBaseDAO systemBaseDAO;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)

  public Object getChildModuleDetail(String moduleName, String id, String childModuleName,
      String childFieldName, HttpServletRequest request) {

    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    _Module childModule = SystemAndLoginInfoService.getModuleWithName(childModuleName);

    SqlGenerator generator = new SqlGenerator(childModule, request);
    generator.setFieldsOnlyIdAndNameAndField(childFieldName);

    SqlModuleFilter pFilter = new SqlModuleFilter();
    pFilter.setModuleName(moduleName);
    pFilter.setPrimarykey(module.getTf_primaryKey());
    pFilter.setEqualsValue(id);
    pFilter.setTableAsName(module.getTableAsName());
    generator.addModuleFilter(pFilter);

    JSONArray jsonArray = getData(generator, -1, 0);

    List<JSONObject> result = new ArrayList<JSONObject>();
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject object = new JSONObject();
      object.put("id", jsonArray.getJSONObject(i).getString(childModule.getTf_primaryKey()));
      object.put("name", jsonArray.getJSONObject(i).getString(childModule.getTf_nameFields()));
      if (childFieldName != null)
        object.put("value", jsonArray.getJSONObject(i).get(childFieldName));

      result.add(object);
    }
    return result;
  }

  /**
   * 根据模块的名称和 一个 name 的值来判断是否此模块已经有这个值了
   * 
   * @param moduleName
   * @param name
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Object getModuleDataWithName(String moduleName, String name) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    if (module == null)
      return null;
    List<?> records = systemBaseDAO.findByProperty(moduleName, module.getTf_nameFields(), name);
    if (records.size() >= 1)
      return records.get(0);
    else
      return null;
  }

  /**
   * 根据module 和一个传进来的值，找到相应的记录,返回主键
   * 
   * @param moduleName
   * @param idOrName
   * @return
   */
  public Object getBeanIdWithIdOrName(_Module module, Object idOrName) {
    Object bean = getBeanWithIdOrName(module, idOrName);
    if (bean == null)
      return null;
    else
      try {
        return Ognl.getValue(module.getTf_primaryKey(), bean);
      } catch (OgnlException e) {
        e.printStackTrace();
        return null;
      }
  }

  /**
   * 根据module 和一个传进来的值，找到相应的记录
   * 如果这是一个父模块的字段，1.先检查是否是主键，2.检查是否是fieldnames,3.检查是否只有一个符合条件的like,
   * 
   * @param module
   * @param idOrName
   * @return
   */
  public Object getBeanWithIdOrName(_Module module, Object idOrName) {
    Class<?> BeanClass = ModuleServiceFunction.getModuleBeanClass(module.getTf_moduleName());
    Object bean = null;
    try {
      bean = systemBaseDAO.findById(BeanClass, idOrName);
    } catch (Exception e) {
    }
    if (bean == null) {
      try {
        List<?> beans = systemBaseDAO.findByProperty(BeanClass, module.getTf_nameFields(),
            idOrName);
        if (beans.size() == 1)
          bean = beans.get(0);
        else if ((beans.size() > 1))
          return null;
      } catch (Exception e) {
      }
    }
    if (bean == null) {
      try {
        @SuppressWarnings("unchecked")
        List<Object> beans = systemBaseDAO.findByLikeProperty(BeanClass.getSimpleName(),
            module.getTf_nameFields(), "%" + idOrName + "%");
        if (beans.size() == 1)
          bean = beans.get(0);
      } catch (Exception e) {
      }
    }
    return bean;
  }

  /**
   * 用户修改了数据之后，将修改过的值updae到bean中去
   * 
   * @param moduleName
   * @param object
   * @param keyValue
   * @throws OgnlException
   */

  @SuppressWarnings("unchecked")
  public void updateValueToBean(String moduleName, Object record, JSONObject keyValue)
      throws OgnlException {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    Iterator<String> keyIterator = keyValue.keys();
    while (keyIterator.hasNext()) {
      String key = keyIterator.next();
      Object value = keyValue.get(key);

      // 判断是不是图像文件，如果是的话，用以下的方法更新。这个是和记录一起更新的方式，其他的更新方法为上传文件的更新
      _ModuleField field = module.getModuleFieldByFieldName(key);
      if (field != null && field.getTf_fieldType().toLowerCase().equals("image")) {
        if (value != null && value.toString().length() > 1 && !value.equals("null")) {
          String[] s = value.toString().split(",");
          byte[] b = new byte[s.length];
          for (int i = 0; i < b.length; i++)
            b[i] = (byte) Integer.parseInt(s[i]);
          InputStream sbs = new ByteArrayInputStream(b);
          LobHelper lobHelper = systemBaseDAO.getSessionFactory().getCurrentSession()
              .getLobHelper();
          Blob blob = null;
          try {
            blob = lobHelper.createBlob(sbs, sbs.available());
          } catch (IOException e) {
            e.printStackTrace();
          }
          Ognl.setValue(key, record, blob);
        } else
          // 清除图像
          Ognl.setValue(key, record, null);

        continue;
      }

      // 是不是manytoone 的值进行了修改
      if (key.indexOf(ModuleConstants.SEPARATOR) != -1) {

        String[] table_fieldname = key.split(ModuleConstants.SEPARATOR);
        _Module parentModule = module.getModuleWithAsName(table_fieldname[0]);
        if (value instanceof JSONNull) { // 如果是父模块的manytoone字段没有选择，则只更新到前面
          key = "tf" + parentModule.getTf_moduleNameWithPre();
        } else {
          key = "tf" + parentModule.getTf_moduleNameWithPre() + "." + table_fieldname[1];
          // 生成一个空记录以后，要对所有的有初始化值的manyToOne进行初始化
          Field[] fs = record.getClass().getDeclaredFields();
          for (Field f : fs) {
            if ((f.getAnnotation(ManyToOne.class) != null
                || f.getAnnotation(OneToOne.class) != null)
                && f.getName().equals("tf" + parentModule.getTf_moduleNameWithPre()))
              try {
                CommonFunction.setPropertyToSuperClass(record, f.getName(),
                    Class.forName(f.getType().getName()).newInstance());
              } catch (Exception e) {
                e.printStackTrace();
              }
          }
        }
      }
      log.debug("更新字段:" + key + ",value:" + value);
      ModuleServiceFunction.setValueToRecord(key, record, value);
    }
  }

  /**
   * 取得一个模块的某个字段的distinct 的值，用于combo 下拉框里进行选择。
   * 
   * @param moduleName
   * @param filters
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<ValueText> getDistinctFieldWithComboData(String moduleName, String fieldName,
      List<SqlModuleFilter> filters, HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    SqlGenerator generator = new SqlGenerator(module, request);
    generator.setFieldsOnlyThisField(fieldName);
    generator.setModuleFilters(filters);
    JSONArray dataArray = getData(generator, -1, 0);
    List<ValueText> result = new ArrayList<ValueText>();
    for (int i = 0; i < dataArray.size(); i++)
      if (dataArray.getJSONObject(i) != null) {
        String v = null;
        try {
          v = dataArray.getJSONObject(i).getString(fieldName);
        } catch (Exception e) {
        }
        if (v != null && !v.toLowerCase().equals("null"))
          result.add(new ValueText(v, v));
      }
    return result;
  }

  /**
   * 取得一个模块的所有用于combo 的数据，包括id和name ,filters 是其上级模块的一个条件值
   * 
   * @param moduleName
   * @param filters
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<ValueText> getModuleWithComboData(String moduleName, List<SqlModuleFilter> filters,
      HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    SqlGenerator generator = new SqlGenerator(module, request);
    generator.setFieldsOnlyIdAndName();
    generator.setModuleFilters(filters);
    JSONArray dataArray = getData(generator, -1, 0);
    String idField = module.getTf_primaryKey();
    String nameField = module.getTf_nameFields();
    List<ValueText> result = new ArrayList<ValueText>();
    for (int i = 0; i < dataArray.size(); i++)
      result.add(new ValueText(dataArray.getJSONObject(i).getString(idField),
          dataArray.getJSONObject(i).getString(nameField)));
    return result;
  }

  /**
   * 取得一个模块的所有用于combo 的数据，包括id和name ,filters 是其上级模块的一个条件值
   * 
   * @param moduleName
   * @param filters
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<ValueText> getModuleWithComboDataWithQuery(String moduleName,
      List<SqlModuleFilter> filters, HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    SqlGenerator generator = new SqlGenerator(module, request);
    generator.setFieldsOnlyIdAndName();
    generator.setModuleFilters(filters);
    JSONArray dataArray = getData(generator, -1, 0);
    String idField = module.getTf_primaryKey();
    String nameField = module.getTf_nameFields();
    List<ValueText> result = new ArrayList<ValueText>();
    for (int i = 0; i < dataArray.size(); i++)
      result.add(new ValueText(dataArray.getJSONObject(i).getString(idField),
          dataArray.getJSONObject(i).getString(nameField)));
    return result;
  }

  /**
   * 取得一个模块的所有用于Tree 的数据，包括id和name ,filters 是其上级模块的一个条件值
   * 
   * @param moduleName
   * @param filters
   * @param allowParentValue
   *          是否可以选择非末级值
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<TreeValueText> getModuleWithTreeData(String moduleName, Boolean allowParentValue,
      List<SqlModuleFilter> filters, HttpServletRequest request) {
    List<ValueText> vts = getModuleWithComboData(moduleName, filters, request);

    TreeValueText root = new TreeValueText("", "root");
    root.setLeaf(false);
    root.setExpanded(true);
    for (ValueText vt : vts) {
      root.getChildren().add(new TreeValueText(vt.getValue(), vt.getText()));
    }

    List<TreeValueText> deleted = new ArrayList<TreeValueText>();
    for (int i = root.getChildren().size() - 1; i > 0; i--) {
      // 对每一个编码，找到离其最近的上一级编码，把自己放在他的下面
      TreeValueText record = root.getChildren().get(i);
      for (int j = i - 1; j >= 0; j--) {
        TreeValueText p = root.getChildren().get(j);
        if (record.getValue().startsWith(p.getValue())) {
          p.getChildren().add(record);
          p.setExpanded(true);

          p.setDisabled(!allowParentValue && true);
          p.setLeaf(false);
          deleted.add(record);
          break;
        }
      }
    }
    root.getChildren().removeAll(deleted);
    List<TreeValueText> result = new ArrayList<TreeValueText>();
    result.addAll(root.getChildren());
    return result;
  }

  /**
   * 根据前台传进来的参数取得list 数据，然后返回
   * 
   * @param moduleName
   * @param dsRequest
   * @param gridFilterData
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public DataFetchResponseInfo getModuleData(String moduleName, DataFetchRequestInfo dsRequest,
      GridFilterData gridFilterData, HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

    // 所有的导航tree产生的过滤条件
    List<SqlModuleFilter> treeAndParentFilters = new ArrayList<SqlModuleFilter>();

    if (dsRequest.getNavigateFilters() != null)
      for (SqlModuleFilter filter : dsRequest.getNavigateFilters()) {
        // 要处理一下，如果里面有的 formula 字段
        if (filter.getModuleName() != null) {
          _Module modu = SystemAndLoginInfoService.getModuleWithName(filter.getModuleName());
          _ModuleField field = modu
              .getFieldWithAsAndFieldName(modu.getTableAsName() + "." + filter.getPrimarykey());
          if (field != null) {
            SqlField sqlField = new SqlField(filter.getModuleName(), filter.getTableAsName(),
                filter.getPrimarykey(), field.getTf_DBfieldName(), field.getTf_DBformula(),
                field.getTf_fieldType());
            filter.setPrimarykey(sqlField.getFieldFullName());
          }
        }

        treeAndParentFilters.add(filter);

      }
    // Iterator<String> key =
    // gridFilterData.getNavigateTreeSelected().keySet().iterator();
    // while (key.hasNext()) {
    // String s = (String) key.next();
    // TreeNodeRecord record = gridFilterData.getNavigateTreeSelected().get(s);
    // SqlModuleFilter moduleFilter = new SqlModuleFilter();
    // moduleFilter.setModuleName(record.getModuleName());
    // moduleFilter.setTableAsName(SystemAndLoginInfoService.getModuleWithName(
    // record.getModuleName()).getTableAsName());
    // moduleFilter.setPrimarykey(record.getIdkey());
    // moduleFilter.setEqualsValue(record.getIdvalue());
    // moduleFilter.setIsCodeLevel(record.getIsCodeLevel());
    // treeAndParentFilters.add(moduleFilter);
    // }

    // 如果有父模块约束，加入父模块约束
    addParentModuleFiltToSQLFilters(module, gridFilterData.getParentModuleFilter(),
        treeAndParentFilters);

    SqlGenerator generator = new SqlGenerator(module, request);

    generator.setModuleFilters(treeAndParentFilters);
    generator.setGridColumnNames(gridFilterData.getGridColumnNames());
    generator.setSearchText(gridFilterData.getSearchText());
    generator.setSorts(dsRequest.getSorts());
    generator.setGroupFieldname(gridFilterData.getGroupFieldName());
    Integer totalRow = getRecordCount(generator);
    log.debug("统计计录个数:" + totalRow);

    Integer startRow = dsRequest.getStartRow();
    Integer endRow = dsRequest.getEndRow();
    endRow = Math.min(endRow, totalRow - 1);

    JSONArray jsonArray = getData(generator, startRow, endRow);
    DataFetchResponseInfo response = new DataFetchResponseInfo();
    response.setStartRow(startRow);
    response.setEndRow(endRow);
    response.setTotalRows(totalRow);
    // if (dsRequest.getIsExport())
    response.setMatchingObjects(jsonArray);
    // else
    // response.setJsonMatchingItems(jsonArray.toString());

    return response;

  }

  /**
   * // 如果有父模块约束，加入父模块约束
   * 
   * @param moduleName
   * @param module
   * @param parentModuleFilter
   * @param treeAndParentFilters
   */
  private void addParentModuleFiltToSQLFilters(_Module module, SqlModuleFilter parentModuleFilter,
      List<SqlModuleFilter> treeAndParentFilters) {
    // 如果有父模块约束，加入父模块约束
    if (parentModuleFilter != null) {
      // 如果是附件的父模块约束，则要加入另外二个条件
      if (module.getTf_moduleName().equals(_Attachment._ATTACHMENT)) {
        SqlModuleFilter additionModuleIdFilter = new SqlModuleFilter();
        additionModuleIdFilter.setModuleName(module.getTf_moduleName());
        additionModuleIdFilter.setTableAsName(module.getTableAsName());
        additionModuleIdFilter.setPrimarykey(_Attachment.MODULEID);
        additionModuleIdFilter.setEqualsValue(parentModuleFilter.getModuleId());
        treeAndParentFilters.add(additionModuleIdFilter);

        SqlModuleFilter additionModuleKeyIdFilter = new SqlModuleFilter();
        additionModuleKeyIdFilter.setModuleName(module.getTf_moduleName());
        additionModuleKeyIdFilter.setTableAsName(module.getTableAsName());
        additionModuleKeyIdFilter.setPrimarykey(_Attachment.MODULEKEYID);
        additionModuleKeyIdFilter.setEqualsValue(parentModuleFilter.getEqualsValue());
        treeAndParentFilters.add(additionModuleKeyIdFilter);

      } else {
        treeAndParentFilters.add(parentModuleFilter);
      }
    }
  }

  /**
   * 根据一个父模块的ＩＤ，产生 id ,count(*)，返回的值放在map中
   * 
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Map<String, Integer> getParentModuleGroupByRecord(String moduleName, String groupField,
      SqlModuleFilter sqlModuleFilter, GridFilterData gridFilterData, Boolean reverseOrder,
      HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    Map<String, Integer> result = new LinkedHashMap<String, Integer>();
    SqlGenerator generator = new SqlGenerator(moduleName, request);
    List<SqlModuleFilter> treeAndParentFilters = new ArrayList<SqlModuleFilter>();
    if (sqlModuleFilter != null)
      treeAndParentFilters.add(sqlModuleFilter);
    if (gridFilterData != null)
      addParentModuleFiltToSQLFilters(module, gridFilterData.getParentModuleFilter(),
          treeAndParentFilters);
    generator.setModuleFilters(treeAndParentFilters);
    String f = groupField;
    // groupField 是带 as 的 如 _t9901.tf_fieldname
    _ModuleField gf = module.getFieldWithAsAndFieldName(groupField);
    if (gf != null) {
      SqlField sqlField = new SqlField(moduleName, module.getTableAsName(),
          groupField.split("\\.")[1], gf.getTf_DBfieldName(), gf.getTf_DBformula(),
          gf.getTf_fieldType());
      f = sqlField.getFieldFullName();
    }
    String sql = generator.getGroupByIdAndCount(f, reverseOrder);
    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    SQLQuery query = session.createSQLQuery(sql);
    List<?> results = null;
    try {
      results = query.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (results != null)
      for (Object row : results) {
        Object[] rowObjects = (Object[]) row;
        if (rowObjects[0] != null)
          result.put(rowObjects[0].toString(), TypeChange.toInt(rowObjects[1]));
        else
          result.put(null, TypeChange.toInt(rowObjects[1]));
      }
    return result;
  }

  /**
   * 根据模块名称和约束条件以及最大最小值，对一个数值字段，生成 大于等于 和小于 条件的约束，返回个数
   * 
   * @param moduleName
   *          模块名称
   * @param sqlModuleFilters
   *          限定的条件
   * @param gridFilterData
   *          父模块的限定信息
   * @param request
   * @return 当前所有限定条件下的个数
   */
  // @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  // public int getModuleCountWithCondition(String moduleName,
  // List<SqlModuleFilter> sqlModuleFilters,
  // GridFilterData gridFilterData, HttpServletRequest request) {
  // _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
  // SqlGenerator generator = new SqlGenerator(moduleName, request);
  // List<SqlModuleFilter> treeAndParentFilters = new
  // ArrayList<SqlModuleFilter>();
  // if (sqlModuleFilters != null)
  // treeAndParentFilters.addAll(sqlModuleFilters);
  // addParentModuleFiltToSQLFilters(module,
  // gridFilterData.getParentModuleFilter(),
  // treeAndParentFilters);
  // generator.setModuleFilters(treeAndParentFilters);
  // return getRecordCount(generator);
  //
  // }

  /**
   * 根据一个父模块的ＩＤ，以及条件，返回记录数
   * 
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public int getModuleReccWithFilter(String moduleName, List<SqlModuleFilter> sqlModuleFilter,
      HttpServletRequest request) {
    SqlGenerator generator = new SqlGenerator(moduleName, request);
    generator.setModuleFilters(sqlModuleFilter);
    int result = getRecordCount(generator);
    return result;
  }

  /**
   * 根据前台传进来的参数取一个模块的 record 数据，然后返回 用在用户增加，修改了数据之后，将修改新增的数据，通过这里取得数据后返回
   * 
   * @param moduleName
   * @param keyValue
   * @return JSONObject
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONObject getModuleRecord(String moduleName, String keyValue,
      HttpServletRequest request) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    SqlGenerator generator = new SqlGenerator(module, request);
    generator.setKeyValue(keyValue);
    JSONArray jsonArray = getData(generator, -1, 0);
    if (jsonArray.size() > 0)
      return jsonArray.getJSONObject(0);
    else
      return null;
  }

  /**
   * 取得记录的个数
   * 
   * @param generator
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Integer getRecordCount(SqlGenerator generator) {

    String sql = generator.getCountSqlStatement();
    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    SQLQuery query = session.createSQLQuery(sql);
    Integer countInteger = 0;
    try {
      countInteger = TypeChange.toInt(query.uniqueResult());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return countInteger;
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONArray getData(SqlGenerator generator, Integer startRow, Integer endRow) {

    String sql = generator.getSqlStatment();
    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    SQLQuery query = session.createSQLQuery(sql);
    if (startRow != -1) {
      query.setFirstResult(startRow);
      query.setMaxResults(endRow - startRow + 1);
    }
    generator.addScalar(query);

    List<?> results = null;
    try {
      results = query.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    JSONArray resultArray = new JSONArray();
    if (results != null)
      for (Object row : results) {
        Object[] rowObjects = (Object[]) row;
        Map<String, Object> objMap = new LinkedHashMap<String, Object>();
        JSONObject object = new JSONObject();
        int i = 0;
        for (SqlField field : generator.getFieldList())
          objMap.put(field.getFieldasScalar(), rowObjects[i++]);
        for (SqlField field : generator.getJoinField())
          objMap.put(field.getFieldasScalar(), rowObjects[i++]);
        object.putAll(objMap, JsonDateProcessor.us_jsonConfig);
        resultArray.add(object);
      }
    return resultArray;
  }

  public String getRecordNameValue(_Module module, Object record) {
    String result = "";
    try {
      result = (module.getTf_nameFields() != null && module.getTf_nameFields().length() > 0)
          ? Ognl.getValue(module.getTf_nameFields(), record).toString() : "未定义";
    } catch (Exception e) {
    }
    return result;
  }

  /**
   * 用户新增删除修改了某个模块的记录，写在操作日志中
   * 
   * @param request
   * @param moduleId
   * @param id
   * @param name
   * @param doword
   * @param remark
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public _SystemOperateLog saveOperateLog(HttpServletRequest request, _Module module, String id,
      String name, String doword, String remark) {

    if (doword != null) {
      if (doword.equals("edit"))
        doword = "修改";
      else if (doword.equals("auditing"))
        doword = "审核";
      else if (doword.equals("cancelauditing"))
        doword = "取消审核";
      else if (doword.equals("approve"))
        doword = "审批";
      else if (doword.equals("cancelapprove"))
        doword = "取消审批";
    }

    _SystemOperateLog operateLog = new _SystemOperateLog();
    try {
      operateLog.setTf_date(new Date());
      operateLog.setTf_do(doword);
      operateLog.setTf_ipaddress(CommonFunction.getIpAddr(request));
      operateLog.setTf_moduleId(module.getTf_moduleId());
      operateLog.setTf_moduleTitle(module.getTf_title());
      operateLog.setTf_recordkey(id);
      if (name != null && name.length() > 50)
        name = name.substring(0, 50);
      operateLog.setTf_recordname(name);
      operateLog.setTf_remark(remark == null ? null : "-" + remark);
      UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
      operateLog.setTf_userId(userSession.getUserId());
      operateLog.setTf_userName(userSession.getUserName());
      systemBaseDAO.save(operateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return operateLog;
  }

  /**
   * 用户上传了excel 文件以新增或修改后，保存修改过的文件
   * 
   * @param request
   * @param moduleId
   * @param id
   * @param name
   * @param doword
   * @param remark
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public _SystemOperateLog saveUploadExcelOperateLog(_Module module, String doword, String filename,
      OutputStream os, String remark, HttpServletRequest request) {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos = (ByteArrayOutputStream) os;
    ByteArrayInputStream is = new ByteArrayInputStream(baos.toByteArray());
    return saveUploadExcelOperateLog(module, doword, filename, is, remark, request);

  }

  @Transactional(propagation = Propagation.REQUIRED)
  public _SystemOperateLog saveUploadExcelOperateLog(_Module module, String doword, String filename,
      InputStream is, String remark, HttpServletRequest request) {

    _SystemOperateLog operateLog = new _SystemOperateLog();
    try {
      operateLog.setTf_date(new Date());
      operateLog.setTf_do(doword);
      operateLog.setTf_ipaddress(CommonFunction.getIpAddr(request));
      operateLog.setTf_moduleId(module.getTf_moduleId());
      operateLog.setTf_moduleTitle(module.getTf_title());
      operateLog.setTf_recordkey("未定义");
      if (filename != null && filename.length() > 50)
        filename = filename.substring(0, 50);
      operateLog.setTf_recordname(filename);
      operateLog.setTf_remark(remark == null ? null : "-" + remark);
      UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
      operateLog.setTf_userId(userSession.getUserId());
      operateLog.setTf_userName(userSession.getUserName());

      // ByteArrayOutputStream baos = new ByteArrayOutputStream();
      // baos = (ByteArrayOutputStream) os;
      // ByteArrayInputStream is = new
      // ByteArrayInputStream(baos.toByteArray());
      operateLog.setTf_hasfiledata(true);
      try {
        operateLog.setTf_filedata(systemBaseDAO.getSessionFactory().getCurrentSession()
            .getLobHelper().createBlob(is, is.available()));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // hibernate3的方法
      // operateLog.setTf_filedata(Hibernate..createBlob(is));
      systemBaseDAO.save(operateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return operateLog;
  }

}
