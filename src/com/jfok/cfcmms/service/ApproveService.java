package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import ognl.Ognl;
import ognl.OgnlException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.superclass._ApproveAbstract;
import com.jfok.cfcmms.hibernate.system.authority._ModuleApprove;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.ApproveListTypeEnum;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;


@Service
public class ApproveService {

	private static final String AUTOApprove = "批量自动审批";

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModuleDAO moduleDAO;

	@Resource
	private ModuleService moduleService;

	/**
	 * 生成系统首页的审核模块的全局概览， 每个模块－－ 有 m 条待审批，我可以审批 n 条
	 * 
	 * @param request
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecord> genAllModuleApproveInfo(HttpServletRequest request) {

		List<TreeNodeRecord> results = new ArrayList<TreeNodeRecord>();
		TreeNodeRecord canApprove = new TreeNodeRecord();
		canApprove.setText("我可以审批的");
		canApprove.setExpanded(true);

		TreeNodeRecord donotApprove = new TreeNodeRecord();
		donotApprove.setText("我不可以审批的");
		donotApprove.setExpanded(true);

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		List<_Module> modules = SystemAndLoginInfoService.getModules();

		for (_Module module : modules)
			if (module.getTf_isEnable() && module.getTf_hasApprove()) {
				_UserRoleDetail userRoleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());
				if (userRoleDetail != null && userRoleDetail.getTf_attachmentBrowse() > 0) {
					// 所有可视范围即可审核或不能审核，只要区分是否可以审核即可。

					SqlModuleFilter approveFilter = getFilter(module, "tf_shResultDate", "null");

					DataFetchResponseInfo response = moduleService.fetchDataInner(module.getTf_moduleName(),
							0, 0, null, null, null, null, approveFilter, null, request);

					if (userRoleDetail.getTf_allowApprove() > 0 && userRoleDetail.getTf_approveOrder() > 0) {
						// 我可以审批的
						List<SqlModuleFilter> meCanApproveFilters = genSqlModule(module,
								userRoleDetail.getTf_approveOrder(), userSession.getUserName(),
								ApproveListTypeEnum.我可以审批的);

						DataFetchResponseInfo meResponse = moduleService.fetchDataInner(
								module.getTf_moduleName(), 0, 0, null, null, null, null, null, meCanApproveFilters,
								request);

						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						record.setTag(meResponse.getTotalRows()); // 只有大于等于1，才可以单击treeitem进入审批界面
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有未审批的记录", module.getTf_title()));
						else
							record.setText(String.format(
									"<span class='treeitemimportant'>%s 有 %d 条未审批，我可以审批 %d 条</span>",
									module.getTf_title(), record.getCount(), meResponse.getTotalRows()));
						record.setLeaf(true);
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							canApprove.getChildren().add(record);

					} else {
						// 我可以查看，但是不可以审批的
						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有未审批的记录", module.getTf_title()));
						else
							record.setText(String.format("%s 共有 %d 条记录未审批", module.getTf_title(),
									record.getCount()));
						record.setLeaf(true);
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							donotApprove.getChildren().add(record);
					}
				}
			}
		if (canApprove.getChildren().size() > 0)
			results.add(canApprove);
		if (donotApprove.getChildren().size() > 0)
			results.add(donotApprove);
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecord> getApproveTypeTree(String moduleName, String title,
			SqlModuleFilter parentFilter, Boolean isContainNullRecord, HttpServletRequest request) {

		List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		TreeNodeRecord root = new TreeNodeRecord(moduleName, module.getTableAsName(), title, null,
				null, null, null);
		root.setLeaf(false);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		for (ApproveListTypeEnum typeenum : ApproveListTypeEnum.values()) {
			// 第一个审批的没有尚未到我审批这个项目
			if (userSession.getUserRoleDetails(module.getTf_moduleId()).getTf_approveOrder() == 1
					&& typeenum == ApproveListTypeEnum.尚未到我审批的)
				continue;
			TreeNodeRecord record = new TreeNodeRecord(module.getTf_moduleName(),
					module.getTableAsName(), typeenum.getValue(), "approvetype", typeenum.getValue(), null,
					false);
			record.setFieldtitle(title);
			List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();
			if (parentFilter != null)
				filters.add(parentFilter);

			filters.addAll(userSession.getModuleApproveSqlFilters().get(moduleName).get(typeenum));
			record.setCount(moduleDAO.getModuleReccWithFilter(moduleName, filters, request));
			if (isContainNullRecord || record.getCount() > 0)
				root.getChildren().add(record);
		}
		result.add(root);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoApproveAll(String moduleName, String parentFilter,
			HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException,
			OgnlException {
		// 直接取出了当前条件下的所有记录
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_UserRoleDetail roleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());

		List<_ModuleApprove> moduleApproves = SystemAndLoginInfoService.getModuleWithName(
				module.getTf_moduleName()).getTf_moduleApproves();

		// 我可以审批的的条件组
		List<SqlModuleFilter> filters = genSqlModule(module, roleDetail.getTf_approveOrder(),
				userSession.getUserName(), ApproveListTypeEnum.我可以审批的);

		// 加入当前模块的 父模块约束
		SqlModuleFilter pFilter = null;
		if (parentFilter != null && parentFilter.length() > 1) {
			JSONObject jo = JSONObject.fromObject(parentFilter);
			pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
		}

		// 取得所有我可以审批的记录
		DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, -1, -1, null, null,
				null, filters, pFilter, null, request);
		int count = 0;
		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);

		// 一个一个进行审批
		for (Object record : response.getMatchingObjects()) {
			JSONObject canApprove = (JSONObject) record;
			String id = canApprove.getString(module.getTf_primaryKey());
			count += approveRecordWithId(module, beanClass, id, userSession, moduleApproves, roleDetail,
					request);
		}
		ActionResult result = new ActionResult();
		result.setMsg(count + "");
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoApproveThisCondition(String moduleName, String query, String columns,
			String navigates, String parentFilter, HttpServletRequest request)
			throws NoSuchFieldException, IllegalAccessException, OgnlException {
		// 直接取出了当前条件下的所有记录
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_UserRoleDetail roleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());

		List<_ModuleApprove> moduleApproves = SystemAndLoginInfoService.getModuleWithName(
				module.getTf_moduleName()).getTf_moduleApproves();
		// 我可以审批的的条件组
		List<SqlModuleFilter> filters = genSqlModule(module, roleDetail.getTf_approveOrder(),
				userSession.getUserName(), ApproveListTypeEnum.我可以审批的);

		DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, -1, -1, null, query,
				columns, navigates, parentFilter, filters, request);
		int count = 0;
		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		for (Object record : response.getMatchingObjects()) {
			JSONObject canApprove = (JSONObject) record;
			String id = canApprove.getString(module.getTf_primaryKey());
			count += approveRecordWithId(module, beanClass, id, userSession, moduleApproves, roleDetail,
					request);
		}
		ActionResult result = new ActionResult();
		result.setMsg(count + "");
		return result;
	}

	/**
	 * 对某一个可审核模块进行当前页面内数据的自动审核
	 * 
	 * @param moduleName
	 * @param ids
	 * @param request
	 * @return
	 * @throws OgnlException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoApprovePageRecord(String moduleName, String ids,
			HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException,
			OgnlException {
		ActionResult result = new ActionResult();
		String rids[] = ids.split(",");
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		List<_ModuleApprove> moduleApproves = SystemAndLoginInfoService.getModuleWithName(
				module.getTf_moduleName()).getTf_moduleApproves();

		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_UserRoleDetail roleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());
		int count = 0;
		for (String id : rids)
			count += approveRecordWithId(module, beanClass, id, userSession, moduleApproves, roleDetail,
					request);
		result.setMsg(count + "");
		return result;
	}

	/**
	 * 将一条记录进行审批，审批之前要先判断是否是我可以审批的
	 * 
	 * @param module
	 * @param beanClass
	 * @param id
	 * @param userSession
	 * @param request
	 * @return
	 * @throws OgnlException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 */
	private int approveRecordWithId(_Module module, Class<?> beanClass, String id,
			UserSession userSession, List<_ModuleApprove> moduleApproves, _UserRoleDetail roleDetail,
			HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException,
			OgnlException {
		_ApproveAbstract record = (_ApproveAbstract) systemBaseDAO.findById(beanClass, id);
		int order = roleDetail.getTf_approveOrder();
		int allLevel = moduleApproves.size();
		Date now = new Date();
		// 如果这条我可以审批
		if (record.meCanApprove(order, roleDetail.getTf_approveLevel(), moduleApproves)) {
			Ognl.setValue("tf_shname" + order, record, userSession.getUserName());
			Ognl.setValue("tf_shdate" + order, record, now);
			Ognl.setValue("tf_shresult" + order, record, "同意");
			Ognl.setValue("tf_shexplain" + order, record, AUTOApprove);
			record.adjustResultInfo(order, allLevel);

			systemBaseDAO.attachDirty(record, null);
			moduleDAO.saveOperateLog(request, module, id, moduleDAO.getRecordNameValue(module, record),
					"approve", AUTOApprove);
			return 1;
		} else
			return 0;
	}

	/**
	 * 取得一个模块某一级审批的各种情况的sqlFilter
	 * 
	 * @param module
	 * @param moduleApproves
	 * @param thisLevel
	 * @return
	 */
	public Map<ApproveListTypeEnum, List<SqlModuleFilter>> genModuleApproveSqlFilter(_Module module,
			int thisOrder, String userName) {
		Map<ApproveListTypeEnum, List<SqlModuleFilter>> result = new HashMap<ApproveListTypeEnum, List<SqlModuleFilter>>();
		for (ApproveListTypeEnum typeEnum : ApproveListTypeEnum.values())
			result.put(typeEnum, genSqlModule(module, thisOrder, userName, typeEnum));
		return result;
	}

	public static List<SqlModuleFilter> genApproveSqlModule(String moduleName,
			ApproveListTypeEnum typeEnum, HttpServletRequest request) {
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		_UserRoleDetail roleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());
		int thisOrder = roleDetail.getTf_approveOrder();
		String userName = userSession.getUserName();
		return genSqlModule(module, thisOrder, userName, typeEnum);
	}

	public static List<SqlModuleFilter> genSqlModule(_Module module, int thisOrder, String userName,
			ApproveListTypeEnum typeEnum) {
		// int thisOrder = user.getTf_ModuleApprove().getTf_order();
		List<_ModuleApprove> moduleApproves = module.getTf_moduleApproves();
		int thisLevel = 0; // 审批的次序，可以2，3级同时审批 ， 1,2,3,3,4等，最后一级只能一个审批
		for (_ModuleApprove a : moduleApproves)
			if (a.getTf_order().equals(thisOrder)) {
				thisLevel = a.getTf_level();
				break;
			}
		List<SqlModuleFilter> result = new ArrayList<SqlModuleFilter>();

		switch (typeEnum) {
		case 我可以审批的:
			// 还没审批结束
			result.add(getFilter(module, "tf_shResultDate", "null"));
			// 我还没批
			result.add(getFilter(module, "tf_shdate" + thisOrder, "null"));
			// 所有在我前面的都已经审批了
			for (_ModuleApprove a : moduleApproves) {
				if (a.getTf_level() < thisLevel) {
					result.add(getFilter(module, "tf_shdate" + a.getTf_order(), "not null"));
				}
			}
			break;
		case 我已经审批过的:
			// 还没审批结束
			result.add(getFilter(module, "tf_shResultDate", "null"));
			// 我批过了
			result.add(getFilter(module, "tf_shdate" + thisOrder, "not null"));
			break;
		case 能修改审批的:
			// 还没审批结束
			result.add(getFilter(module, "tf_shResultDate", "null"));
			// 我批过了
			result.add(getFilter(module, "tf_shdate" + thisOrder, "not null"));
			// 所有在我后面的都没有审批
			for (_ModuleApprove a : moduleApproves) {
				if (a.getTf_level() > thisLevel) {
					result.add(getFilter(module, "tf_shdate" + a.getTf_order(), "null"));
				}
			}
			break;

		case 能取消终止审批的:
			// 终止审批的
			result.add(getFilter(module, "tf_shResult", _ApproveAbstract.APPROVE_CANCEL));
			// 我终止审批的
			result.add(getFilter(module, "tf_shResultName", userName));

			break;

		case 尚未到我审批的:
			// 还没审批结束
			result.add(getFilter(module, "tf_shResultDate", "null"));
			// 我还没批
			result.add(getFilter(module, "tf_shdate" + thisOrder, "null"));
			// 所有在我前面的至少有一个未审批
			SqlModuleFilter pfilter = new SqlModuleFilter();
			pfilter.setModuleName(module.getTf_moduleName());
			pfilter.setTableAsName(module.getTableAsName());
			for (_ModuleApprove a : moduleApproves) {
				if (a.getTf_level() < thisLevel) {
					pfilter.addToOrFilter(getFilter(module, "tf_shdate" + a.getTf_order(), "null"));
				}
			}
			if (pfilter.getOrFilter() != null)
				result.add(pfilter);
			break;
		case 已通过审批的:
			result.add(getFilter(module, "tf_shResult", _ApproveAbstract.APPROVE_OK));
			break;
		case 尚未通过审批的:
			result.add(getFilter(module, "tf_shResult", _ApproveAbstract.APPROVE_EXEC));
			break;
		case 已终止审批的:
			result.add(getFilter(module, "tf_shResult", _ApproveAbstract.APPROVE_CANCEL));
			break;
		default:
			break;
		}
		return result;
	}

	private static SqlModuleFilter getFilter(_Module module, String fieldname, String fieldvalue) {
		SqlModuleFilter filter = new SqlModuleFilter();
		filter.setModuleId(module.getTf_moduleId());
		filter.setModuleName(module.getTf_moduleName());
		filter.setTableAsName(module.getTableAsName());
		filter.setPrimarykey(fieldname);
		filter.setEqualsValue(fieldvalue);
		return filter;
	}

}
