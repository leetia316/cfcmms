package com.jfok.cfcmms.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.core.module.SqlField;
import com.jfok.cfcmms.core.module.SqlGenerator;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.DataFetchRequestInfo;
import com.jfok.cfcmms.util.json.JsonDateProcessor;

@Repository
public class TreeModuleDAO {

  public static final String PVALUE = "thisispvaluepleasechangeittoid";
  public static final String CHILDREN = "children";

  @Autowired
  private SystemBaseDAO systemBaseDAO;

  /**
   * 根据前台传进来的参数取得list 数据，然后返回 , 是 level 类型的 tree code ,2,2,2,2,2
   * 
   * @param moduleName
   * @param dsRequest
   * @param gridFilterData
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONObject getTreeModuleDataWithLevel(_Module module, DataFetchRequestInfo dsRequest,
      GridFilterData gridFilterData, HttpServletRequest request) {

    // 所有的导航tree产生的过滤条件
    List<SqlModuleFilter> treeAndParentFilters = new ArrayList<SqlModuleFilter>();

    SqlGenerator generator = new SqlGenerator(module, request);

    generator.setModuleFilters(treeAndParentFilters);
    generator.setGridColumnNames(gridFilterData.getGridColumnNames());
    generator.setSearchText(gridFilterData.getSearchText());
    generator.setSorts(dsRequest.getSorts());
    generator.setIsTreeData(true);

    String orginSql = generator.getSqlStatment();
    JSONObject result = new JSONObject();
    result.put("text", ".");
    String[] levels_1 = module.getTf_codeLevel().split(",");
    int[] levels = new int[levels_1.length];
    for (int i = 0; i < levels.length; i++)
      levels[i] = Integer.parseInt(levels_1[i]);

    JSONArray jsonArray = getDataWithPLevel(generator, orginSql, 0, getLikeBehind(levels[0]),
        module.getTf_primaryKey(), levels);
    result.put(CHILDREN, jsonArray);

    return result;

  }

  /**
   * 递归调用取得数据
   * 
   * @param generator
   * @param orginSql
   * @param plevel
   *          第几级
   * @param primaryKey
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONArray getDataWithPLevel(SqlGenerator generator, String orginSql, int level,
      String parentkey, String primaryKey, int[] levels) {
    String sql;

    sql = orginSql.replaceFirst(PVALUE, " like '" + parentkey + "'");

    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    SQLQuery query = session.createSQLQuery(sql);

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

        String thisid = object.getString(primaryKey);
        JSONArray subArray = getDataWithPLevel(generator, orginSql, level + 1,
            thisid + getLikeBehind(levels[level + 1]), primaryKey, levels);
        if (subArray.size() > 0)
          object.put(CHILDREN, subArray);
        else
          object.put("leaf", true);

        resultArray.add(object);
      }
    return resultArray;
  }

  private String getLikeBehind(int l) {
    String result = "";
    for (int i = 0; i < l; i++)
      result += "_";
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
  public JSONObject getTreeModuleDataWithPid(_Module module, DataFetchRequestInfo dsRequest,
      GridFilterData gridFilterData, HttpServletRequest request) {

    // 所有的导航tree产生的过滤条件
    List<SqlModuleFilter> treeAndParentFilters = new ArrayList<SqlModuleFilter>();

    SqlGenerator generator = new SqlGenerator(module, request);

    generator.setModuleFilters(treeAndParentFilters);
    generator.setGridColumnNames(gridFilterData.getGridColumnNames());
    generator.setSearchText(gridFilterData.getSearchText());
    generator.setSorts(dsRequest.getSorts());
    generator.setIsTreeData(true);

    String orginSql = generator.getSqlStatment();
    JSONObject result = new JSONObject();
    result.put("text", ".");

    JSONArray jsonArray = getDataWithPid(generator, orginSql, null, module.getTf_primaryKey());
    result.put(CHILDREN, jsonArray);

    return result;

  }

  /**
   * 递归调用取得数据
   * 
   * @param generator
   * @param orginSql
   * @param pid
   * @param primaryKey
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONArray getDataWithPid(SqlGenerator generator, String orginSql, String pid,
      String primaryKey) {
    String sql;
    if (pid == null)
      sql = orginSql.replaceFirst(PVALUE, " is null");
    else
      sql = orginSql.replaceFirst(PVALUE, " = '" + pid + "'");

    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    SQLQuery query = session.createSQLQuery(sql);

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

        String thisid = object.getString(primaryKey);
        JSONArray subArray = getDataWithPid(generator, orginSql, thisid, primaryKey);
        if (subArray.size() > 0)
          object.put(CHILDREN, subArray);
        else
          object.put("leaf", true);

        resultArray.add(object);
      }
    return resultArray;
  }
}
