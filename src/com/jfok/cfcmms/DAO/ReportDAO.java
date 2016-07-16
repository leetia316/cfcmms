package com.jfok.cfcmms.DAO;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ognl.Ognl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.core.report.ReportParam;
import com.jfok.cfcmms.core.report.SqlField;
import com.jfok.cfcmms.core.report.SqlGenerator;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.TypeChange;
import com.jfok.cfcmms.util.json.JsonDateProcessor;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;

@Repository
public class ReportDAO {

	private static final Log log = LogFactory.getLog(ReportDAO.class);

	@Autowired
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 根据前台传进来的参数取得list 数据，然后返回 ,加入汇总和分类汇总
	 * 
	 * @param moduleName
	 * @param dsRequest
	 * @param gridFilterData
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DataFetchResponseInfo getReportDataWithSubTotal(ReportParam reportParam,
		Integer startRow, Integer endRow, HttpServletRequest request) {

	// 先根据 sql generator 的 sql 语句生成一个 视图
	// create view _1 as .....
	// 生成一个总计的sql ,然后用 union 联合查询在一起
	// select '' as xxx,'' as xxx , sum(f1) as f1 , sum (f2) as f2 ,.... union
	// select * from ...
	// ....key._level_...._count_....sum(je)
	// ... ....10...........5.........100......总计
	// ....a...20...........3..........80......小计
	// ....a...100..........1..........30
	// ....a...100..........1..........20
	// ....a...100..........1..........30
	// ....b...20...........2..........60......小计
	// ....b...100..........1..........20
	// ....b...100..........1..........40
	// order by key , _level_ , other
	//
	// ....k1....k2...._level_...._count_....sum(je)
	// ... .............10...........5.........100......总计
	// ....a.....20..........3..........80.....小计 a
	// ....a.b...30..........2..........50.....小计 a , b
	// ....a.b..100..........1..........20
	// ....a.b..100..........1..........30
	// ....a.c.. 40..........1..........30.....小计 a , c
	// ....a.c..100..........1..........30
	// ....b...20...........2..........60......小计
	// ....b...100..........1..........20
	// ....b...100..........1..........40
	// order by key , _level_ , other

	if (reportParam.getSqlGenerator() == null)
		reportParam.setSqlGenerator(new SqlGenerator(reportParam, request));

	Session session = systemBaseDAO.getSessionFactory().getCurrentSession();

	// SQLQuery query = session.createSQLQuery("drop view _1");
	// try {
	// query.executeUpdate();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }

	String sql = reportParam.getSqlGenerator().getSqlStatment();

	reportParam.setTempTableName(
		"__tempReportView" + CommonFunction.encodeByMD5(request.getSession().getId() + sql));

	UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
	// 如果已经在，则不去创建了
	if (!userSession.getTempReportView().contains(reportParam.getTempTableName())) {
		SQLQuery query = session
			.createSQLQuery("create view " + reportParam.getTempTableName() + " as " + sql);
		try {
		query.executeUpdate();
		userSession.getTempReportView().add(reportParam.getTempTableName());
		} catch (Exception e) {
		// System.out.println(reportParam.getTempTableName() + "已存在");
		}
	}
	// 总计的 sql
	String sumSql = "select " + reportParam.getSqlGenerator().getSumSqlStatment() + " from "
		+ reportParam.getTempTableName();

	// 如果要显示明细，加上明细的选择
	if (reportParam.getGroupShowDetail())
		sumSql += " union select * from " + reportParam.getTempTableName();

	// 每一级分组的sql 语句
	for (int level = 0; level < reportParam.getGroups().size(); level++)
		sumSql = sumSql + " union select "
			+ reportParam.getSqlGenerator().getSubTotalSqlStatment(level) + " from "
			+ reportParam.getTempTableName() + " group by "
			+ reportParam.getSqlGenerator().getGroupFieldname(level);

	sumSql = sumSql + " order by " + reportParam.getSqlGenerator().getGroup_order_by();
	String sortby = reportParam.getSqlGenerator().getSortByString();
	if (sortby.length() > 0) {
		sumSql += " ," + sortby;
	}

	Integer totalRow = 0;
	// 如果要显示明细，才加入明细的记录数
	if (reportParam.getGroupShowDetail())
		totalRow = getRecordCountWithSql("select count(*) from " + reportParam.getTempTableName());
	log.debug("统计计录个数:" + totalRow);
	totalRow++;
	// 计算subtotal 的记录数
	for (int level = 0; level < reportParam.getGroups().size(); level++) {
		SQLQuery query = session
			.createSQLQuery("select " + reportParam.getSqlGenerator().getGroupFieldname(level)
				+ " , count(*) from " + reportParam.getTempTableName() + " group by "
				+ reportParam.getSqlGenerator().getGroupFieldname(level));
		totalRow += query.list().size();
	}
	endRow = Math.min(endRow, totalRow - 1);

	JSONArray jsonArray = getDataWithSubTotal(reportParam.getSqlGenerator(), sumSql, startRow,
		endRow);
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray getDataWithSubTotal(SqlGenerator generator, String sql, Integer startRow,
		Integer endRow) {

	// String sql = generator.getSqlStatment();
	Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
	SQLQuery query = session.createSQLQuery(sql);
	if (startRow != -1) {
		query.setFirstResult(startRow);
		query.setMaxResults(endRow - startRow + 1);
	}
	query.addScalar("_total_");
	query.addScalar("_level_");
	query.addScalar("_count_");
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
		objMap.put("_total_", rowObjects[i++]);
		objMap.put("_level_", rowObjects[i++]);
		objMap.put("_count_", rowObjects[i++]);
		for (SqlField field : generator.getFieldList())
			objMap.put(field.getFieldasScalar(), rowObjects[i++]);
		for (SqlField field : generator.getJoinField())
			objMap.put(field.getFieldasScalar(), rowObjects[i++]);
		object.putAll(objMap, JsonDateProcessor.us_jsonConfig);
		resultArray.add(object);
		}
	return resultArray;
	}

	/**
	 * 根据前台传进来的参数取得list 数据，然后返回 ,加入汇总
	 * 
	 * @param moduleName
	 * @param dsRequest
	 * @param gridFilterData
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public DataFetchResponseInfo getReportDataWithTotal(ReportParam reportParam, Integer startRow,
		Integer endRow, HttpServletRequest request) {
	if (reportParam.getSqlGenerator() == null)
		reportParam.setSqlGenerator(new SqlGenerator(reportParam, request));
	Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
	String sql = reportParam.getSqlGenerator().getSqlStatment();
	UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
	reportParam.setTempTableName(
		"__tempReportView" + CommonFunction.encodeByMD5(request.getSession().getId() + sql));

	if (!userSession.getTempReportView().contains(reportParam.getTempTableName())) {
		SQLQuery query = session
			.createSQLQuery("create view " + reportParam.getTempTableName() + " as " + sql);
		try {
		query.executeUpdate();
		userSession.getTempReportView().add(reportParam.getTempTableName());
		} catch (Exception e) {
		// System.out.println(reportParam.getTempTableName() + "已存在");
		}
	}

	String sumSql = "select " + reportParam.getSqlGenerator().getSumSqlStatment() + " from "
		+ reportParam.getTempTableName() + " union " + "select * from "
		+ reportParam.getTempTableName();
	String sortby = reportParam.getSqlGenerator().getSortByString();
	if (sortby.length() > 0)
		sumSql += " order by " + sortby;

	Integer totalRow = getRecordCountWithSql(
		"select count(*) from " + reportParam.getTempTableName());

	log.debug("统计计录个数:" + totalRow);
	totalRow++;
	endRow = Math.min(endRow, totalRow - 1);

	JSONArray jsonArray = getDataWithTotal(reportParam.getSqlGenerator(), sumSql, startRow, endRow);
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public JSONArray getDataWithTotal(SqlGenerator generator, String sql, Integer startRow,
		Integer endRow) {

	// String sql = generator.getSqlStatment();
	Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
	SQLQuery query = session.createSQLQuery(sql);
	if (startRow != -1) {
		query.setFirstResult(startRow);
		query.setMaxResults(endRow - startRow + 1);
	}
	query.addScalar("_total_");
	query.addScalar("_level_");
	query.addScalar("_count_");
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
		objMap.put("_total_", rowObjects[i++]);
		objMap.put("_level_", rowObjects[i++]);
		objMap.put("_count_", rowObjects[i++]);
		for (SqlField field : generator.getFieldList())
			objMap.put(field.getFieldasScalar(), rowObjects[i++]);
		for (SqlField field : generator.getJoinField())
			objMap.put(field.getFieldasScalar(), rowObjects[i++]);

		int _level = 0;
		if (objMap.get("_level_") instanceof Integer)
			_level = (Integer) objMap.get("_level_");
		else if ((objMap.get("_level_") instanceof BigInteger))
			_level = ((BigInteger) objMap.get("_level_")).intValue();
		if (_level == 10) {
			// 总计
			objMap.put(generator.getReportParam().getBaseModule().getTf_nameFields(),
				"〖总 计〗 (" + objMap.get("_count_") + "条)");

		}
		object.putAll(objMap, JsonDateProcessor.us_jsonConfig);

		resultArray.add(object);
		}
	return resultArray;
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
	public DataFetchResponseInfo getReportData(ReportParam reportParam, Integer startRow,
		Integer endRow, HttpServletRequest request) {
	if (reportParam.getSqlGenerator() == null)
		reportParam.setSqlGenerator(new SqlGenerator(reportParam, request));

	Integer totalRow = getRecordCount(reportParam.getSqlGenerator());
	log.debug("统计计录个数:" + totalRow);

	endRow = Math.min(endRow, totalRow - 1);

	JSONArray jsonArray = getData(reportParam.getSqlGenerator(), startRow, endRow);
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
	 * 取得记录的个数
	 * 
	 * @param generator
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Integer getRecordCount(SqlGenerator generator) {

	return getRecordCountWithSql(generator.getCountSqlStatement());

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Integer getRecordCountWithSql(String sql) {

	Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
	Integer countInteger = 0;
	try {
		SQLQuery query = session.createSQLQuery(sql);
		countInteger = TypeChange.toInt(query.uniqueResult());
	} catch (Exception e) {
		countInteger = -2;
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

}
