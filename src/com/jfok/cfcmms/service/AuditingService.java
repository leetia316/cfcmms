package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.logic.IModuleOperateLogic;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class AuditingService {

	private static final String AUTOAUDITING = "批量自动审核";

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
	public List<TreeNodeRecord> genAllModuleAuditingInfo(HttpServletRequest request) {

		List<TreeNodeRecord> results = new ArrayList<TreeNodeRecord>();
		TreeNodeRecord canAuditing = new TreeNodeRecord();
		canAuditing.setText("我可以审核的");
		canAuditing.setExpanded(true);

		TreeNodeRecord donotAuditing = new TreeNodeRecord();
		donotAuditing.setText("我不可以审核的");
		donotAuditing.setExpanded(true);

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		List<_Module> modules = SystemAndLoginInfoService.getModules();

		for (_Module module : modules)
			if (module.getTf_isEnable() && module.getTf_hasAuditing()) {
				_UserRoleDetail userRoleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());
				if (userRoleDetail != null && userRoleDetail.getTf_attachmentBrowse() > 0) {
					// 所有可视范围即可审核或不能审核，只要区分是否可以审核即可。
					SqlModuleFilter notAudtiing = getNotAuditingFilter(module);
					DataFetchResponseInfo response = moduleService.fetchDataInner(module.getTf_moduleName(),
							0, 0, null, null, null, null, (SqlModuleFilter) null, notAudtiing, request);

					if (userRoleDetail.getTf_allowAuditing() > 0) {
						// 我可以审核的
						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有未审核的记录", module.getTf_title()));
						else
							record.setText(String.format(
									"<span class='treeitemimportant'>%s 有 %d 条未审核，我可以审核 %d 条</span>",
									module.getTf_title(), record.getCount(), record.getCount()));
						record.setLeaf(true);
						record.setTag(record.getCount()); // 只有大于等于1，才可以单击treeitem进入审核界面
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							canAuditing.getChildren().add(record);
					} else {
						// 我可以查看，但是不可以审核的
						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有未审核的记录", module.getTf_title()));
						else
							record.setText(String.format("%s 共有 %d 条记录未审核", module.getTf_title(),
									record.getCount()));
						record.setLeaf(true);
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							donotAuditing.getChildren().add(record);
					}
				}
			}
		if (canAuditing.getChildren().size() > 0)
			results.add(canAuditing);
		if (donotAuditing.getChildren().size() > 0)
			results.add(donotAuditing);
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoAuditingAll(String moduleName, String parentFilter,
			HttpServletRequest request) {
		// 直接取出了当前条件下的所有记录
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		SqlModuleFilter notAudtiing = getNotAuditingFilter(module);
		// 加入当前模块的 父模块约束
		SqlModuleFilter pFilter = null;
		if (parentFilter != null && parentFilter.length() > 1) {
			JSONObject jo = JSONObject.fromObject(parentFilter);
			pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
		}

		DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, -1, -1, null, null,
				null, null, pFilter, notAudtiing, request);
		int count = 0;
		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		for (Object record : response.getMatchingObjects()) {
			JSONObject canAuditing = (JSONObject) record;
			String id = canAuditing.getString(module.getTf_primaryKey());
			count += auditingRecordWithId(module, beanClass, id, userSession, request);
		}
		ActionResult result = new ActionResult();
		result.setMsg(count + "");
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoAuditingThisCondition(String moduleName, String query, String columns,
			String navigates, String parentFilter, HttpServletRequest request) {
		// 直接取出了当前条件下的所有记录
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		SqlModuleFilter notAudtiing = getNotAuditingFilter(module);
		DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, -1, -1, null, query,
				columns, navigates, parentFilter, notAudtiing, request);
		int count = 0;
		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		for (Object record : response.getMatchingObjects()) {
			JSONObject canAuditing = (JSONObject) record;
			String id = canAuditing.getString(module.getTf_primaryKey());
			count += auditingRecordWithId(module, beanClass, id, userSession, request);
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
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult autoAuditingPageRecord(String moduleName, String ids,
			HttpServletRequest request) {
		ActionResult result = new ActionResult();
		String rids[] = ids.split(",");

		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		int count = 0;
		for (String id : rids)
			count += auditingRecordWithId(module, beanClass, id, userSession, request);
		result.setMsg(count + "");
		return result;
	}

	@SuppressWarnings("unchecked")
	private int auditingRecordWithId(_Module module, Class<?> beanClass, String id,
			UserSession userSession, HttpServletRequest request) {
		Object oldRecord = systemBaseDAO.findById(beanClass, id);
		systemBaseDAO.getHibernateTemplate().evict(oldRecord);

		Object record = systemBaseDAO.findById(beanClass, id);

		// 使oldRecord 处于游离状态
		try {
			if (Ognl.getValue("tf_auditingDate", record) == null) {
				Ognl.setValue("tf_auditingDate", record, new Date());
				Ognl.setValue("tf_auditingName", record, userSession.getUserName());
				Object r = Ognl.getValue("tf_auditingRemark", record);
				Ognl.setValue("tf_auditingRemark", record, (r == null ? "" : r.toString() + ";")
						+ AUTOAUDITING);
				systemBaseDAO.attachDirty(record, null);
				moduleDAO.saveOperateLog(request, module, id, moduleDAO.getRecordNameValue(module, record),
						"auditing", AUTOAUDITING);

				// 修改数据之前去检查逻辑性
				IModuleOperateLogic<Object> moduleOperateLogic = (IModuleOperateLogic<Object>) SystemInfoService
						.getBean(module.getTf_moduleName() + "Logic");
				try {
					if (moduleOperateLogic != null)

						moduleOperateLogic.afterUpdate(ModuleFormOperateType.OperateTypeGen("auditing"),
								record, oldRecord, request);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 1;
			} else
				return 0;
		} catch (OgnlException e) {
			e.printStackTrace();
			return 0;
		}
	}

	private SqlModuleFilter getNotAuditingFilter(_Module module) {
		SqlModuleFilter notAudtiing = new SqlModuleFilter();
		notAudtiing.setEqualsValue("null");
		notAudtiing.setModuleName(module.getTf_moduleName());
		notAudtiing.setModuleId(module.getTf_moduleId());
		notAudtiing.setTableAsName(module.getTableAsName());
		notAudtiing.setPrimarykey("tf_auditingDate");
		return notAudtiing;
	}
}
