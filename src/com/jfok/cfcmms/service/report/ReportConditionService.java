package com.jfok.cfcmms.service.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import ognl.Ognl;
import ognl.OgnlException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.ReportDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.core.report.ColumnFilter;
import com.jfok.cfcmms.core.report.GroupFieldDefine;
import com.jfok.cfcmms.core.report.ModuleCondition;
import com.jfok.cfcmms.core.report.ReportExcelExport;
import com.jfok.cfcmms.core.report.ReportField;
import com.jfok.cfcmms.core.report.ReportFieldGroup;
import com.jfok.cfcmms.core.report.ReportParam;
import com.jfok.cfcmms.core.report.SqlGenerator;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.Office2PDF;
import com.jfok.cfcmms.share.ValueText;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.report._Report;
import com.jfok.cfcmms.hibernate.system.report._ReportField;
import com.jfok.cfcmms.hibernate.system.report._ReportFieldGroup;
import com.jfok.cfcmms.hibernate.system.report._ReportGroup;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.SortParameter;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.TreeValueText;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;


/**
 * 报表，综合查询 条件选择 以及 取得条件数据 Service
 * 
 * @author jiangfeng
 * 
 */

@Service
public class ReportConditionService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModuleDAO moduleDAO;

	@Resource
	private ReportDAO reportDAO;

	/**
	 * 取得一个模块的所有的id,name 记录，用于选择查 询字段的时候，打勾
	 * 
	 * @param moduleName
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<TreeNodeRecordChecked> getModuleTreeData(String conditionId,
			HttpServletRequest request) {

		// 如果是 模块，则 conditionId 是 modulename ,如果 中间有个减号，表示是某个字段
		String c[] = conditionId.split("-");
		String moduleName = c[0];

		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		List<TreeNodeRecordChecked> results = new ArrayList<TreeNodeRecordChecked>();
		if (c.length == 1) {
			if (module.getTf_codeLevel() == null || module.getTf_codeLevel().length() == 0) {
				List<ValueText> values = moduleDAO.getModuleWithComboData(moduleName, null, request);
				for (ValueText value : values) {
					TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
							module.getTableAsName(), value.getText(), module.getTf_primaryKey(),
							value.getValue(), null, module.isCodeLevel());
					record.setLeaf(true);
					results.add(record);
				}
			} else {
				List<TreeValueText> values = moduleDAO.getModuleWithTreeData(moduleName, true, null,
						request);
				for (TreeValueText value : values) {
					TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
							module.getTableAsName(), value.getText(), module.getTf_primaryKey(),
							value.getValue(), null, module.isCodeLevel());
					record.setLeaf(true);
					results.add(record);
					addSub(value.getChildren(), record, module);
				}
			}
		} else {
			// 取得这个模块的字段值的唯一列表
			_ModuleField field = module.getModuleFieldByFieldId(Integer.parseInt(c[1]));

			Map<String, Integer> records = moduleDAO.getParentModuleGroupByRecord(moduleName,
					module.getTableAsName() + "." + field.getTf_fieldName(), null, null, false, request);
			for (String f : records.keySet()) {

				TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
						module.getTableAsName(), f + " <font color=blue>(" + records.get(f).toString()
								+ ")</font>", field.getTf_fieldName(), f, null, null);
				record.setLeaf(true);
				results.add(record);

			}
		}

		return results;
	}

	// 将子级item加入
	private void addSub(List<TreeValueText> subvalues, TreeNodeRecordChecked p, _Module module) {
		if (subvalues != null && subvalues.size() > 0) {
			p.setLeaf(false);
			for (TreeValueText value : subvalues) {
				TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
						module.getTableAsName(), value.getText(), module.getTf_primaryKey(), value.getValue(),
						null, module.isCodeLevel());
				p.getChildren().add(record);
				addSub(value.getChildren(), record, module);
			}
		}
	}

	/**
	 * 取得所有的模块分组和模块，加入树状结构，前台用来选择相应的模块来取得模块的字段，供选择
	 * 
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<TreeValueText> getModuleGroupAndModule(HttpServletRequest request) {

		List<TreeValueText> results = new ArrayList<TreeValueText>();
		_Module groupModule = SystemAndLoginInfoService.getModuleWithName("_ModuleGroup");
		List<ValueText> values = moduleDAO.getModuleWithComboData("_ModuleGroup", null, request);
		for (ValueText vt : values) {
			TreeValueText p = new TreeValueText(vt.getValue(), vt.getText());
			results.add(p);

			SqlModuleFilter filter = new SqlModuleFilter();
			filter.setModuleName("_ModuleGroup");
			filter.setEqualsValue(vt.getValue());
			filter.setPrimarykey("tf_moduleGroupId");
			filter.setTableAsName(groupModule.getTableAsName());
			List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();
			filters.add(filter);
			List<ValueText> ms = moduleDAO.getModuleWithComboData("_Module", filters, request);
			for (ValueText vt1 : ms) {
				_Module m = SystemAndLoginInfoService.getModuleWithId(vt1.getValue());
				TreeValueText p1 = new TreeValueText(m.getTf_moduleName(), vt1.getText());
				p.getChildren().add(p1);
			}
		}
		return results;
	}

	// 返回 字段分组＋字段，的树状结构
	public List<TreeValueText> getModuleFields(String moduleName, HttpServletRequest request) {
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		if (module == null)
			return null;
		List<_ModuleField> fields = module.getTf_fields();
		List<TreeValueText> results = new ArrayList<TreeValueText>();
		for (_ModuleField field : fields) {
			// 隐藏的字段和禁用的字段，不要出现在综合查询的字段选择里面
			if (!field.getTf_isHidden() && !field.getTf_isDisable()) {
				TreeValueText f = new TreeValueText(field.getTf_fieldId().toString(), field.getTf_title());
				f.setTooltip(field.getTf_fieldType());
				TreeValueText nowGroup = null;
				for (TreeValueText vt : results)
					if (vt.getText().equals(field.getTf_fieldGroup())) {
						nowGroup = vt;
						break;
					}
				if (nowGroup == null) {
					nowGroup = new TreeValueText(field.getTf_fieldGroup(), field.getTf_fieldGroup());
					nowGroup.setLeaf(false);
					nowGroup.setExpanded(true);
					results.add(nowGroup);
				}
				nowGroup.getChildren().add(f);
			}
		}
		return results;
	}

	/**
	 * 根据报表分组，取得该分组下的所有报表
	 * 
	 * @param groupName
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<TreeValueText> getGroupReports(String reportGroupId, HttpServletRequest request) {
		List<TreeValueText> valueTexts = new ArrayList<TreeValueText>();

		TreeValueText me = new TreeValueText("-1", "我的查询方案", true, true, false);
		TreeValueText system = new TreeValueText("-2", "系统查询方案", true, true, false);
		TreeValueText other = new TreeValueText("-3", "其他查询方案", true, true, false);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());

		@SuppressWarnings("unchecked")
		List<_Report> _Reports = (List<_Report>) systemBaseDAO.findByPropertyAllSort(_Report.class,
				"tf_orderId", "asc", "tf_ReportGroup.tf_reportGroupId", reportGroupId, null, null);

		for (_Report report : _Reports) {
			TreeValueText aReport = new TreeValueText(report.getTf_reportId().toString(),
					report.getTf_title());
			if (report.getTf_isSystem()) {
				if (userSession.getLoginName().equals("admin"))
					aReport.setTag(1); // 表示可以修改或删除
				system.getChildren().add(aReport);
			} else if (report.getTf_inputmen().equals(userSession.getUserName())) {
				aReport.setTag(1);
				me.getChildren().add(aReport);
			} else
				other.getChildren().add(aReport);
		}
		if (me.getChildren().size() > 0)
			valueTexts.add(me);
		if (system.getChildren().size() > 0)
			valueTexts.add(system);
		if (other.getChildren().size() > 0)
			valueTexts.add(other);
		return valueTexts;
	}

	/**
	 * 根据报表号，取得分组名称和下面的字段信息，取得 base module ，用于选择字段，以及生成 grid
	 * 
	 * @param reportId
	 *          报表号
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Object getReportInfo(Integer reportId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ReportFieldGroup> gs = new ArrayList<ReportFieldGroup>();
		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);
		for (_ReportFieldGroup group : report.getReportFieldGroups()) {
			ReportFieldGroup rgroup = new ReportFieldGroup(group.getTf_orderId(), group.getTf_groupName());
			rgroup.setFields(new ArrayList<ReportField>());
			for (_ReportField field : group.getReportFields()) {
				ReportField rfield = new ReportField(field.getTf_ModuleField().getTf_Module()
						.getTf_moduleName(), field.getTf_ModuleField().getTf_fieldId(),
						field.getTf_condition(), field.getTf_aggregate());
				rgroup.getFields().add(rfield);
			}
			gs.add(rgroup);
		}
		ReportParam rp = new ReportParam();
		rp.setBaseModule(SystemAndLoginInfoService.getModuleWithName(report.getTf_baseModuleName()));
		rp.setReportFieldGroups(gs);

		result.put("groups", rp.getReportFieldGroups());
		result.put("baseModuleName", rp.getBaseModule().getTf_moduleName());
		List<String> allmodules = new ArrayList<String>();
		for (_Module module : rp.getAllModules())
			allmodules.add(module.getTf_moduleName());
		result.put("allModules", allmodules);
		result.put("isShowTotal", report.getTf_isShowTotal());
		result.put("groupFields", report.getTf_groupDefine());
		result.put("groupShowDetail", report.getTf_isShowDetail());

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateReportInfo(Integer reportId, String baseModuleName, Boolean isShowTotal,
			String groupFields, Boolean groupShowDetail) {
		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);
		report.setTf_baseModuleName(baseModuleName);
		report.setTf_isShowTotal(isShowTotal);
		report.setTf_groupDefine(groupFields);
		report.setTf_isShowDetail(groupShowDetail);
		systemBaseDAO.attachDirty(report, null);
	}

	/**
	 * 根据报表号，以及传来的json字符串，把当前用户选中的字段保存到数据库里
	 * 
	 * @param reportId
	 *          报表号
	 * @param groupfields
	 *          定义
	 * @param request
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult setReportGroupAndField(Integer reportId, String selectedFields,
			HttpServletRequest request) {
		JSONArray groups = JSONArray.fromObject(selectedFields);

		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);

		int orderId = 10;
		for (int i = 0; i < groups.size(); i++) {
			_ReportFieldGroup group = new _ReportFieldGroup();
			group.setTf_Report(report);
			group.setTf_orderId(orderId);
			group.setTf_groupName(((JSONObject) groups.get(i)).getString(GROUPTITLE));
			orderId += 10;
			systemBaseDAO.save(group);

			JSONArray fields = (JSONArray) ((JSONObject) groups.get(i)).get("fields");
			int oid = 10;
			for (int j = 0; j < fields.size(); j++) {
				JSONObject field = (JSONObject) fields.get(j);
				_ReportField rfield = new _ReportField();
				rfield.setTf_ReportFieldGroup(group);
				rfield.setTf_ModuleField(new _ModuleField(Integer.parseInt(field.getString("fieldId"))));
				rfield.setTf_orderId(oid);
				if (field.containsKey("condition") && !field.getString("condition").equals("null"))
					rfield.setTf_condition(field.getString("condition"));
				if (field.containsKey("aggregate") && !field.getString("aggregate").equals("null"))
					rfield.setTf_aggregate(field.getString("aggregate"));
				oid += 10;
				systemBaseDAO.save(rfield);

			}

		}
		return new ActionResult();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteFieldGroupWithReportId(Integer reportId) {
		String hql = "delete _ReportFieldGroup as p where p.tf_Report.tf_reportId=?";

		Session session = systemBaseDAO.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, reportId);
			query.executeUpdate();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	/**
	 * 新增一个综合查询方案
	 * 
	 * @param reportGroupId
	 * @param text
	 * @param groupfields
	 * @param request
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult saveAsReport(String reportGroupId, String text, String selectedFields,
			String baseModuleName, Boolean isShowTotal, String groupFields, Boolean groupShowDetail,
			HttpServletRequest request) {
		_ReportGroup rg = (_ReportGroup) systemBaseDAO.findById(_ReportGroup.class, reportGroupId);
		_Report rp = new _Report();
		rp.setTf_ReportGroup(rg);
		rp.setTf_title(text);
		rp.setTf_inputmen(SessionManage.getInstance().getUserSession(request.getSession())
				.getUserName());
		rp.setTf_baseModuleName(baseModuleName);
		rp.setTf_inputdate(new Date());
		rp.setTf_isShowTotal(isShowTotal);
		rp.setTf_groupDefine(groupFields);
		rp.setTf_isShowDetail(groupShowDetail);

		Session session = systemBaseDAO.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(_Report.class);
		Criteria moduleCriteria = criteria.createCriteria("tf_ReportGroup");
		moduleCriteria.add(Restrictions.eq("tf_reportGroupId", reportGroupId));
		criteria.setProjection(Projections.max("tf_orderId"));
		List<?> results = criteria.list();
		if (results.get(0) == null)
			rp.setTf_orderId(10);
		else
			rp.setTf_orderId((Integer) results.get(0) + 10);

		systemBaseDAO.save(rp);
		systemBaseDAO.getHibernateTemplate().flush();
		setReportGroupAndField(rp.getTf_reportId(), selectedFields, request);
		ActionResult result = new ActionResult();
		result.setTag(rp.getTf_reportId().toString());
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult deleteReport(Integer reportId, HttpServletRequest request) {
		ActionResult result = new ActionResult();

		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);
		if (report.getTf_isSystem()) {
			result.setMsg(report.getTf_title() + " 是系统查询方案，不能被删除！");
			result.setSuccess(false);
			return result;
		}

		String hql = "delete _Report as p where p.tf_reportId=?";
		Session session = systemBaseDAO.getSessionFactory().openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, reportId);
			query.executeUpdate();
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("删除失败:" + e.getMessage());
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return result;
	}

	public static final String GROUPTITLE = "groupTitle";
	public static final String GROUPORDER = "groupOrder";

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public boolean isSystemReport(Integer reportId) {
		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);
		return report.getTf_isSystem();
	}

	// 根据选择的字段和 baseModule 来返回 factGroupAndFields ;

	// 检查选中的字段是否包含二个无关联的模块 ,如果有 条件字段，判断每一个条件字段是否正确，
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ActionResult validSelectedFields(String groupfields, HttpServletRequest request) {
		ReportParam rp = new ReportParam();
		rp.setReportFieldGroups(changeStringToReportFieldGroup(groupfields));
		// rp.applyParentAndChildModules();
		// rp.applyFactReportFieldGroups();
		ActionResult result = new ActionResult();
		String s = rp._setBaseModule(); // 这里要改
		if (s != null) {
			result.setSuccess(false);
			result.setMsg(s);
		} else {
			// 如果模块字段间的关系正常，判断有条件的所有值是否都正确
			for (ReportFieldGroup group : rp.getReportFieldGroups())
				for (ReportField field : group.getFields()) {
					// 判断每个字段是否有附加条件，如果有的话，更新到 加到 sql 中去看看会不会报错
					String sqlwhere = field.getWhereSql();
					if (sqlwhere != null && sqlwhere.length() > 0) {
						ReportParam newReportParam = new ReportParam();
						newReportParam.setBaseModule(SystemAndLoginInfoService.getModuleWithName(field
								.getModuleName()));
						newReportParam.setFieldConditions(new ArrayList<ReportField>());
						newReportParam.getFieldConditions().add(field);
						newReportParam.setReportFieldGroups(rp.getReportFieldGroups());
						newReportParam.applyParentAndChildModules();
						newReportParam.applyFactReportFieldGroups();

						SqlGenerator generator = new SqlGenerator(newReportParam, request);
						Integer totalRow = reportDAO.getRecordCount(generator);
						// System.out.println("记录数：" + totalRow);
						if (totalRow == -2) {
							// sql 语句出错了
							result.setSuccess(false);
							result.setMsg(field.getModule().getTf_title() + "中的字段『"
									+ field.getModuleField().getTf_title() + "』设置的条件出错。");
							return result;
						}
					}
				}

			// 返回参数中加入，共有几个模块，基准模块，以及各个字段组和字段

			Map<String, Object> returnData = new HashMap<String, Object>();
			returnData.put("groups", rp.getReportFieldGroups());
			returnData.put("baseModuleName", rp.getBaseModule().getTf_moduleName());
			List<String> allmodules = new ArrayList<String>();
			for (_Module module : rp.getAllModules())
				allmodules.add(module.getTf_moduleName());
			returnData.put("allModules", allmodules);
			result.setMsg(returnData);
		}
		return result;
	}

	/**
	 * 根据reportId,取得查询数据
	 * 
	 * @param reportId
	 * @param request
	 * @return
	 */

	public static final String REPORTPARAMS = "REPORT_PARAMS_";

	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> fetchData(String reportGroupId, Integer reportId, Integer start,
			Integer limit, String sort, String query, String baseModuleName, String fields,
			String moduleConditions, String columnFilter, Boolean isShowTotal, String groupFields,
			Boolean groupShowDetail, HttpServletRequest request) {

		// 查找这个报表组有没有缓存的东西存在reques 中
		// 在分类汇总中， sort 无关
		String cacheId = reportId.toString() + baseModuleName + sort + query + fields
				+ moduleConditions + columnFilter + groupFields + groupShowDetail + isShowTotal;
		ReportParam cacherp = (ReportParam) request.getSession().getAttribute(
				REPORTPARAMS + reportGroupId);
		ReportParam newrp = null;
		if (cacherp != null && cacherp.getCatchId().equals(cacheId)) {
			// System.out.println("一样的，没变");
			newrp = cacherp;
		} else {
			// System.out.println("改变了");
			newrp = new ReportParam();
			newrp.setReportGroupId(reportGroupId);
			newrp.setReportId(reportId);
			newrp.setSort(SortParameter.changeToSortParameters(sort));
			newrp.setQuery(query);
			newrp.setBaseModule(SystemAndLoginInfoService.getModuleWithName(baseModuleName));
			newrp.setReportFieldGroups(changeStringToReportFieldGroup(fields));
			newrp.setModuleConditions(changeStringToModuleCondition(moduleConditions));
			newrp.setColumnFilters(ColumnFilter.changeToColumnFilters(columnFilter));

			newrp.setGroups(changeStringToGroupField(groupFields, newrp.getBaseModule()));

			newrp.setGroupShowDetail(groupShowDetail);
			newrp.setCatchId(cacheId);

			newrp.setTempTableName("__tempReport"
					+ CommonFunction.encodeByMD5(request.getSession().getId() + baseModuleName + query
							+ fields + moduleConditions + columnFilter));

			// System.out.println(newrp.getTempTableName());

			newrp.applyParentAndChildModules();
			newrp.applyFactReportFieldGroups();
			newrp.setIsShowTotal(isShowTotal);
			request.getSession().removeAttribute(REPORTPARAMS + reportGroupId);
			request.getSession().setAttribute(REPORTPARAMS + reportGroupId, newrp);
		}

		// DataFetchResponseInfo response = reportDAO.getReportData(newrp, start,
		// start + limit - 1,
		// request);
		DataFetchResponseInfo response;
		if (newrp.hasSubTotal())
			response = reportDAO.getReportDataWithSubTotal(newrp, start, start + limit - 1, request);
		else if (isShowTotal)
			response = reportDAO.getReportDataWithTotal(newrp, start, start + limit - 1, request);
		else
			response = reportDAO.getReportData(newrp, start, start + limit - 1, request);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", response.getMatchingObjects());
		result.put("totalCount", response.getTotalRows());
		return result;

	}

	/**
	 * 根据reportId,取得查询数据
	 * 
	 * @param reportId
	 * @param request
	 * @return
	 * @throws IOException
	 */

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void downloadResult(String reportGroupId, Integer reportId, Integer start, Integer limit,
			String sort, String query, String baseModuleName, String fields, String moduleConditions,
			String columnFilter, Boolean isShowTotal, String groupFields, Boolean groupShowDetail,
			Boolean iswanyuan, String hiddenColumns, Boolean ispdf, Boolean PDFPrint,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 查找这个报表组有没有缓存的东西存在reques 中
		// 在分类汇总中， sort 无关
		String cacheId = reportId.toString() + baseModuleName + sort + query + fields
				+ moduleConditions + columnFilter + groupFields + groupShowDetail + isShowTotal;
		ReportParam cacherp = (ReportParam) request.getSession().getAttribute(
				REPORTPARAMS + reportGroupId);
		ReportParam newrp = null;
		if (cacherp != null && cacherp.getCatchId().equals(cacheId)) {
			// System.out.println("一样的，没变");
			newrp = cacherp;
		} else {
			// System.out.println("改变了");
			newrp = new ReportParam();
			newrp.setReportGroupId(reportGroupId);
			newrp.setReportId(reportId);
			newrp.setSort(SortParameter.changeToSortParameters(sort));
			newrp.setQuery(query);
			newrp.setBaseModule(SystemAndLoginInfoService.getModuleWithName(baseModuleName));
			newrp.setReportFieldGroups(changeStringToReportFieldGroup(fields));
			newrp.setModuleConditions(changeStringToModuleCondition(moduleConditions));

			newrp.setColumnFilters(ColumnFilter.changeToColumnFilters(columnFilter));

			newrp.setGroups(changeStringToGroupField(groupFields, newrp.getBaseModule()));

			newrp.setGroupShowDetail(groupShowDetail);
			newrp.setCatchId(cacheId);

			newrp.setTempTableName("__tempReport"
					+ CommonFunction.encodeByMD5(request.getSession().getId() + baseModuleName + query
							+ fields + moduleConditions + columnFilter));

			// System.out.println(newrp.getTempTableName());

			newrp.applyParentAndChildModules();
			newrp.applyFactReportFieldGroups();
			newrp.setIsShowTotal(isShowTotal);
			request.getSession().removeAttribute(REPORTPARAMS + reportGroupId);
			request.getSession().setAttribute(REPORTPARAMS + reportGroupId, newrp);
		}

		// 将打印显示在excel 表头的显示属性全部准备好
		for (ModuleCondition mc : newrp.getModuleConditions()) {
			if (mc.getType().equals("module")) {
				String value = mc.getModule().getTf_title() + "：";
				for (String id : mc.getValue().split(",")) {
					Object record = systemBaseDAO.findById(
							ModuleServiceFunction.getModuleBeanClass(mc.getModuleName()), id);
					try {
						value = value + Ognl.getValue(mc.getModule().getTf_nameFields(), record).toString()
								+ "； ";
					} catch (OgnlException e) {
					}
				}
				mc.setExcelCellText(value);
			} else if (mc.getType().equals("modulefield")) {
				String value = mc.getModule().getTf_title() + mc.getModuleField().getTf_title() + "："
						+ mc.getValue();
				mc.setExcelCellText(value);
			} else if (mc.getType().equals("selectfield")) {
				String value = mc.getModule().getTf_title() + mc.getModuleField().getTf_title() + "："
						+ mc.getDisplayText();
				mc.setExcelCellText(value);
			} else if (mc.getType().equals("basemoduledate")) {
				String value = mc.getModule().getTf_title() + mc.getModuleField().getTf_title() + "："
						+ mc.getDisplayText();
				mc.setExcelCellText(value);
			}
		}

		// DataFetchResponseInfo response = reportDAO.getReportData(newrp, start,
		// start + limit - 1,
		// request);
		DataFetchResponseInfo responseInfo;
		if (newrp.hasSubTotal())
			responseInfo = reportDAO.getReportDataWithSubTotal(newrp, 0, 60000, request);
		else if (isShowTotal)
			responseInfo = reportDAO.getReportDataWithTotal(newrp, 0, 60000, request);
		else
			responseInfo = reportDAO.getReportData(newrp, 0, 60000, request);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("records", responseInfo.getMatchingObjects());
		result.put("totalCount", responseInfo.getTotalRows());

		_Report report = (_Report) systemBaseDAO.findById(_Report.class, reportId);

		OutputStream os = new ReportExcelExport(PDFPrint || ispdf).GenExcel(request, newrp, report.getTf_title(),
				iswanyuan, responseInfo.getMatchingObjects(), hiddenColumns);
		if (ispdf) {
			if (PDFPrint)
				CommonFunction.downloadAndOpenPDF(
						Office2PDF.office2PDF("xls",
								new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray())),
						report.getTf_title() + "--" + CommonFunction.fu_GenDownLoadXH() + ".pdf", response);
			else
				CommonFunction.download(
						Office2PDF.office2PDF("xls",
								new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray())),
						report.getTf_title() + "--" + CommonFunction.fu_GenDownLoadXH() + ".pdf", response);
		} else
			CommonFunction.download(os, report.getTf_title() + "--" + CommonFunction.fu_GenDownLoadXH()
					+ ".xls", response);

	}

	// 返回实际的 分组 和 字段信息
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Object getFactGroupAndFields(String baseModuleName, String fields, String groupFields,
			Boolean groupShowDetail, HttpServletRequest request) {
		ReportParam newrp = new ReportParam();

		newrp.setBaseModule(SystemAndLoginInfoService.getModuleWithName(baseModuleName));
		newrp.setReportFieldGroups(changeStringToReportFieldGroup(fields));

		newrp.setGroups(changeStringToGroupField(groupFields, newrp.getBaseModule()));

		newrp.setGroupShowDetail(groupShowDetail);

		newrp.applyParentAndChildModules();
		newrp.applyFactReportFieldGroups();

		return newrp.getFactReportFieldGroups();
	}

	/**
	 * 取得每个模块筛选记录的 当前基准模块的 记录数，如果没有用到那个条件，则也返回一个值
	 * 
	 * @param fields
	 * @param moduleConditions
	 * @return
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Map<String, Object> getConditionsRecordCount(String baseModuleName, String groupfields,
			String moduleConditions, HttpServletRequest request) {
		ReportParam newrp = new ReportParam();

		newrp.setBaseModule(SystemAndLoginInfoService.getModuleWithName(baseModuleName));
		newrp.setReportFieldGroups(changeStringToReportFieldGroup(groupfields));
		newrp.setModuleConditions(new ArrayList<ModuleCondition>());

		newrp.applyParentAndChildModules();
		newrp.applyFactReportFieldGroups();

		// newrp._setBaseModule();

		List<ModuleCondition> mcs = changeStringToModuleCondition(moduleConditions);

		Map<String, Object> result = new HashMap<String, Object>();
		for (ModuleCondition mc : mcs) {
			// 加入一个条件，然后去求记录数，再加入一个条件，再求记录数
			newrp.getModuleConditions().add(mc);
			SqlGenerator generator = new SqlGenerator(newrp, request);
			Integer totalRow = reportDAO.getRecordCount(generator);
			Map<String, Object> fieldResult = new HashMap<String, Object>();
			if (mc.getUnused()) {
				// 对于基准模块没有用到的条件，可能是求聚合函数的时候子模块用到的条件，查找每一个求和的子模块，看看是不是可以用这个条件
				if (isAggreagateCondition(newrp, mc.getFactModuleName()))
					fieldResult.put("count", -99);
				else
					fieldResult.put("count", -1);
			} else
				fieldResult.put("count", totalRow);
			fieldResult.put("displayText", mc.getDisplayText());
			result.put(mc.getModuleName(), fieldResult);
		}
		return result;
	}

	// 对于基准模块没有用到的条件，可能是求聚合函数的时候子模块用到的条件，查找每一个求和的子模块，看看是不是可以用这个条件
	private boolean isAggreagateCondition(ReportParam rp, String moduleName) {
		for (ReportFieldGroup group : rp.getFactReportFieldGroups()) {
			for (ReportField field : group.getFields()) {
				if (field.getAggregateType() != null) {
					_Module af = SystemAndLoginInfoService.getModuleWithName(field.getModuleName());
					// 如果是聚合模块或者在聚合模块的父模块中出现，那么这个条件就是作用于 聚合查询上的
					if (af.getTf_moduleName().equals(moduleName)
							|| af.getAllParentsMap().containsKey(moduleName))
						return true;
				}
			}
		}
		return false;
	}

	private List<GroupFieldDefine> changeStringToGroupField(String str, _Module baseModule) {
		List<GroupFieldDefine> result = new ArrayList<GroupFieldDefine>();
		// System.out.println(str);
		JSONArray groups = JSONArray.fromObject(str);
		for (int i = 0; i < groups.size(); i++) {
			GroupFieldDefine mc = new GroupFieldDefine();
			mc.setModuleName(((JSONObject) groups.get(i)).getString("moduleName"));
			mc.setFieldId(Integer.parseInt(((JSONObject) groups.get(i)).getString("fieldId")));
			mc.applyModuleAndField(baseModule);
			result.add(mc);
		}
		return result;
	}

	private List<ModuleCondition> changeStringToModuleCondition(String moduleConditions) {
		List<ModuleCondition> result = new ArrayList<ModuleCondition>();
		// System.out.println(moduleConditions);
		JSONArray groups = JSONArray.fromObject(moduleConditions);
		for (int i = 0; i < groups.size(); i++) {
			ModuleCondition mc = new ModuleCondition();
			mc.setType(((JSONObject) groups.get(i)).getString("type"));
			mc.setModuleName(((JSONObject) groups.get(i)).getString("conditionId"));
			mc.setValue(((JSONObject) groups.get(i)).getString("values"));
			result.add(mc);
		}
		return result;
	}

	/**
	 * 把字符串转换成字段分组和字段
	 * [{"groupOrder":10,"groupTitle":"\u5b57\u6bb5","fields":[{"fieldId"
	 * :20100030,"fieldTitl.....
	 * 
	 * @param groupfields
	 * @return
	 */
	private List<ReportFieldGroup> changeStringToReportFieldGroup(String groupfields) {
		List<ReportFieldGroup> result = new ArrayList<ReportFieldGroup>();
		JSONArray groups = JSONArray.fromObject(groupfields);
		for (int i = 0; i < groups.size(); i++) {
			ReportFieldGroup group = new ReportFieldGroup();
			group.setGroupTitle(((JSONObject) groups.get(i)).getString(GROUPTITLE));
			group.setFields(new ArrayList<ReportField>());
			JSONArray fields = (JSONArray) ((JSONObject) groups.get(i)).get("fields");
			for (int j = 0; j < fields.size(); j++) {
				JSONObject field = (JSONObject) fields.get(j);
				ReportField rfield = new ReportField(field.getString("moduleName"), Integer.parseInt(field
						.getString("fieldId")), field.containsKey("condition")
						&& !field.getString("condition").equals("null") ? field.getString("condition") : null,
						field.containsKey("aggregate") && !field.getString("aggregate").equals("null") ? field
								.getString("aggregate") : null);
				//
				// rfield.setFieldId(Integer.parseInt(field.getString("fieldId")));
				// rfield.setModuleName(field.getString("moduleName"));
				// rfield.setFieldTitle(field.getString("fieldTitle"));
				// if (field.containsKey("condition") &&
				// !field.getString("condition").equals("null"))
				// rfield.setCondition(field.getString("condition"));
				group.getFields().add(rfield);
			}
			result.add(group);
		}
		return result;
	}

}
