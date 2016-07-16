package com.jfok.cfcmms.controller;


import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.AuditingService;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.util.ActionResult;


@Controller
@RequestMapping("/moduleauditing")
public class AuditingController {

	@Resource
	private AuditingService auditingService;

	/**
	 * 生成系统首页的审核模块的全局概览， 每个模块－－ 有 m 条待审批，我可以审批 n 条
	 * 
	 * @param request
	 */

	@RequestMapping("/getmoduleauditinginfo.do")
	public @ResponseBody
	List<TreeNodeRecord> genAllModuleAuditingInfo(HttpServletRequest request) {
		return auditingService.genAllModuleAuditingInfo(request);
	}

	@RequestMapping("/allauditing.do")
	public @ResponseBody
	ActionResult autoAuditingAll(String moduleName, String parentFilter, HttpServletRequest request) {
		return auditingService.autoAuditingAll(moduleName, parentFilter, request);
	}

	@RequestMapping("/thiscondition.do")
	public @ResponseBody
	ActionResult autoAuditingThisCondition(String moduleName, String query, String columns,
			String navigates, String parentFilter, HttpServletRequest request) {
		return auditingService.autoAuditingThisCondition(moduleName, query, columns, navigates,
				parentFilter, request);
	}

	/**
	 * 对某一个可审核模块进行当前页面内数据的自动审核
	 * 
	 * @param moduleName
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("/pagerecord.do")
	public @ResponseBody
	ActionResult autoAuditingPageRecord(String moduleName, String ids, HttpServletRequest request) {
		return auditingService.autoAuditingPageRecord(moduleName, ids, request);
	}

}
