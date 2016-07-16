package com.jfok.cfcmms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import ognl.OgnlException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.ApproveService;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.util.ActionResult;


@Controller
@RequestMapping("/moduleapprove")
public class ApproveController {

	@Resource
	private ApproveService approveService;

	/**
	 * 生成系统首页的审核模块的全局概览， 每个模块－－ 有 m 条待审批，我可以审批 n 条
	 * 
	 * @param request
	 */

	@RequestMapping("/getmoduleapproveinfo.do")
	public @ResponseBody
	List<TreeNodeRecord> genAllModuleApproveInfo(HttpServletRequest request) {
		return approveService.genAllModuleApproveInfo(request);
	}

	@RequestMapping("/allapprove.do")
	public @ResponseBody
	ActionResult autoApproveAll(String moduleName, String parentFilter, HttpServletRequest request)
			throws NoSuchFieldException, IllegalAccessException, OgnlException {
		return approveService.autoApproveAll(moduleName, parentFilter, request);
	}

	@RequestMapping("/thiscondition.do")
	public @ResponseBody
	ActionResult autoApproveThisCondition(String moduleName, String query, String columns,
			String navigates, String parentFilter, HttpServletRequest request)
			throws NoSuchFieldException, IllegalAccessException, OgnlException {
		return approveService.autoApproveThisCondition(moduleName, query, columns, navigates,
				parentFilter, request);
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
	@RequestMapping("/pagerecord.do")
	public @ResponseBody
	ActionResult autoApprovePageRecord(String moduleName, String ids, HttpServletRequest request)
			throws NoSuchFieldException, IllegalAccessException, OgnlException {
		return approveService.autoApprovePageRecord(moduleName, ids, request);
	}

}
