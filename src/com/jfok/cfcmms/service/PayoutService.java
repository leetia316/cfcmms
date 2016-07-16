package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;

@Service
public class PayoutService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModuleDAO moduleDAO;

	@Resource
	private ModuleService moduleService;

	/**
	 * 生成系统首页的 可支付 模块的全局概览， 每个模块－－ 有 m 条待审批，我可以审批 n 条
	 * 
	 * @param request
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecord> genAllModulePayoutInfo(HttpServletRequest request) {

		List<TreeNodeRecord> results = new ArrayList<TreeNodeRecord>();
		TreeNodeRecord canPayout = new TreeNodeRecord();
		canPayout.setText("我可以支付的");
		canPayout.setExpanded(true);

		TreeNodeRecord donotPayout = new TreeNodeRecord();
		donotPayout.setText("我不可以支付的");
		donotPayout.setExpanded(true);

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		List<_Module> modules = SystemAndLoginInfoService.getModules();

		for (_Module module : modules)
			if (module.getTf_isEnable() && module.getTf_hasPayment()) {
				_UserRoleDetail userRoleDetail = userSession.getUserRoleDetails(module.getTf_moduleId());
				if (userRoleDetail != null && userRoleDetail.getTf_attachmentBrowse() > 0) {
					// 所有可视范围即可支付或不能支付，只要区分是否可以审核即可。
					SqlModuleFilter notAudtiing = getNotPayoutFilter(module);
					DataFetchResponseInfo response = moduleService.fetchDataInner(module.getTf_moduleName(),
							0, 0, null, null, null, null, (SqlModuleFilter) null, notAudtiing, request);

					if (userRoleDetail.getTf_allowPayment() > 0) {
						// 我可以审核的
						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有可支付的记录", module.getTf_title()));
						else
							record.setText(String.format(
									"<span class='treeitemimportant'>%s 有 %d 条未支付，我可以支付 %d 条</span>",
									module.getTf_title(), record.getCount(), record.getCount()));
						record.setLeaf(true);
						record.setTag(record.getCount()); // 只有大于等于1，才可以单击treeitem进入审核界面
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							canPayout.getChildren().add(record);
					} else {
						// 我可以查看，但是不可以审核的
						TreeNodeRecord record = new TreeNodeRecord();
						record.setModuleName(module.getTf_moduleName());
						record.setCount(response.getTotalRows());
						if (record.getCount() == 0)
							record.setText(String.format("%s 没有可支付的记录", module.getTf_title()));
						else
							record.setText(String.format("%s 共有 %d 条记录未支付", module.getTf_title(),
									record.getCount()));
						record.setLeaf(true);
						// 没有的模块不加进去了，太多了
						if (record.getCount() > 0)
							donotPayout.getChildren().add(record);
					}
				}
			}
		if (canPayout.getChildren().size() > 0)
			results.add(canPayout);
		if (donotPayout.getChildren().size() > 0)
			results.add(donotPayout);
		return results;
	}

	private SqlModuleFilter getNotPayoutFilter(_Module module) {
		SqlModuleFilter notAudtiing = new SqlModuleFilter();
		notAudtiing.setEqualsValue("可支付");
		notAudtiing.setModuleName(module.getTf_moduleName());
		notAudtiing.setModuleId(module.getTf_moduleId());
		notAudtiing.setTableAsName(module.getTableAsName());
		notAudtiing.setPrimarykey("tf_PayoutStatus");
		return notAudtiing;
	}
}
