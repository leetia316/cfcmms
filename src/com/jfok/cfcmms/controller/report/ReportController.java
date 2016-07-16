package com.jfok.cfcmms.controller.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.service.report.ReportConditionService;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.TreeValueText;
import com.jfok.cfcmms.util.ActionResult;


@Controller
@RequestMapping("/report")
public class ReportController {

	@Resource
	private ReportConditionService reportConditionService;

	/**
	 * 前台发送取得报表数据的请求
	 * 
	 * @param reportId
	 * @param start
	 * @param limit
	 * @param sort
	 *          排序字段
	 * @param query
	 *          筛选条件
	 * @param fields
	 *          当前选中的字段
	 * @param moduleConditions
	 *          模块选择的条件
	 * @param groups
	 *          分组
	 * @param groupShowDetail
	 *          分组显示明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/fetchdata.do")
	@ResponseBody
	public Map<String, Object> fetchData(String reportGroupId, Integer reportId, Integer start,
			Integer limit, String sort, String query, String selectedFields, String baseModuleName,
			String moduleConditions, String filter, String groupFields, Boolean groupShowDetail,
			Boolean isShowTotal, HttpServletRequest request) {

		return reportConditionService.fetchData(reportGroupId, reportId, start, limit, sort, query,
				baseModuleName, selectedFields, moduleConditions, filter, isShowTotal, groupFields,
				groupShowDetail, request);

	}

	/**
	 * 下载当前报表excel
	 * 
	 * @param reportGroupId
	 * @param reportId
	 * @param start
	 * @param limit
	 * @param sort
	 * @param query
	 * @param selectedFields
	 * @param baseModuleName
	 * @param moduleConditions
	 * @param filter
	 * @param groupFields
	 * @param groupShowDetail
	 * @param isShowTotal
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/downloadresult.do")
	@ResponseBody
	public void downloadResult(String reportGroupId, Integer reportId, Integer start, Integer limit,
			String sort, String query, String selectedFields, String baseModuleName,
			String moduleConditions, String filter, String groupFields, Boolean groupShowDetail,
			Boolean isShowTotal, Boolean iswanyuan, String hiddenColumns, Boolean ispdf, Boolean print,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		reportConditionService.downloadResult(reportGroupId, reportId, start, limit, sort, query,
				baseModuleName, selectedFields, moduleConditions, filter, isShowTotal, groupFields,
				groupShowDetail, iswanyuan, hiddenColumns, ispdf, print, request, response);

	}

	/**
	 * 根据传进去的基准模块和 选中的分组以及字段，返回实际的 分组 和字段
	 * 
	 * @param fields
	 * @param moduleConditions
	 * @return
	 */
	@RequestMapping("/getfactgroupandfields.do")
	@ResponseBody
	public Object getFactGroupAndFields(String baseModuleName, String fields, String groupFields,
			Boolean groupShowDetail, HttpServletRequest request) {
		return reportConditionService.getFactGroupAndFields(baseModuleName, fields, groupFields,
				groupShowDetail, request);
	}

	/**
	 * 取得每个模块筛选记录的 当前基准模块的 记录数，如果没有用到那个条件，则也返回一个值
	 * 
	 * @param fields
	 * @param moduleConditions
	 * @return
	 */
	@RequestMapping("/getconditionsrecordcount.do")
	@ResponseBody
	public Map<String, Object> getConditionsRecordCount(String baseModuleName, String fields,
			String moduleConditions, HttpServletRequest request) {
		return reportConditionService.getConditionsRecordCount(baseModuleName, fields,
				moduleConditions, request);
	}

	/**
	 * 验证当前选择的各个字段的模块是否有二个不相关的模块，如果有不相干的模块，则会产生迪卡尔积
	 * 
	 * @param fields
	 * @param request
	 * @return
	 */
	@RequestMapping("/validselectedfields.do")
	@ResponseBody
	public ActionResult validSelectedFields(String fields, HttpServletRequest request) {

		return reportConditionService.validSelectedFields(fields, request);

	}

	/**
	 * 根据报表号，取得分组名称和下面的字段信息，用于选择字段，以及生成 grid
	 * 
	 * @param reportId
	 *          报表号
	 * @param request
	 * @return
	 */

	@RequestMapping("/getreportinfo.do")
	@ResponseBody
	public Object getReportInfo(Integer reportId, HttpServletRequest request) {

		return reportConditionService.getReportInfo(reportId, request);

	}

	/**
	 * 根据报表号，以及传来的json字符串，把当前用户选中的字段保存到数据库里
	 * 
	 * @param reportId
	 *          报表号
	 * @param request
	 * @return
	 */

	@RequestMapping("/savereport.do")
	@ResponseBody
	public ActionResult SaveReport(Integer reportId, String selectedFields, String baseModuleName,
			Boolean isShowTotal, String groupFields, Boolean groupShowDetail, HttpServletRequest request) {
		if (reportConditionService.isSystemReport(reportId)) {
			if (!SessionManage.getInstance().getUserSession(request.getSession()).getLoginName()
					.equals("admin"))
				return new ActionResult(false, "系统内置的综合查询方案只能由“系统管理员”进行修改。");
		}

		reportConditionService.deleteFieldGroupWithReportId(reportId);
		reportConditionService.updateReportInfo(reportId, baseModuleName, isShowTotal, groupFields,
				groupShowDetail);
		return reportConditionService.setReportGroupAndField(reportId, selectedFields, request);
	}

	/**
	 * 新增一个 报表 ，
	 * 
	 * @param reportId
	 *          报表号
	 * @param request
	 * @return
	 */

	@RequestMapping("/saveasreport.do")
	@ResponseBody
	public Object saveAsReport(String reportGroupId, String text, String selectedFields,
			String baseModuleName, Boolean isShowTotal, String groupFields, Boolean groupShowDetail,
			HttpServletRequest request) {

		return reportConditionService.saveAsReport(reportGroupId, text, selectedFields, baseModuleName,
				isShowTotal, groupFields, groupShowDetail, request);
	}

	@RequestMapping("/deletereport.do")
	@ResponseBody
	public ActionResult deleteReport(Integer reportId, HttpServletRequest request) {

		return reportConditionService.deleteReport(reportId, request);
	}

	/**
	 * 根据报表分组号取得该分组号下面的所有报表
	 * 
	 * @param reportGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getgroupreports.do")
	@ResponseBody
	public List<TreeValueText> getGroupReports(String reportGroupId, HttpServletRequest request) {

		return reportConditionService.getGroupReports(reportGroupId, request);

	}

	@RequestMapping("/fetchmoduleconitiontree.do")
	/**
	 * 根据模块名称，取得该模块的数据 tree ,用于综合查询选择该模块的条件值
	 * @param moduleName
	 * @param request
	 * @return
	 */
	public @ResponseBody
	List<TreeNodeRecordChecked> getModuleTreeData(String conditionId, HttpServletRequest request) {
		return reportConditionService.getModuleTreeData(conditionId, request);

	}

	@RequestMapping("/fetchgroupandmodule.do")
	/**
	 * 取得所有的模块分组名称，用于选择字段窗口
	 * @param moduleName
	 * @param request
	 * @return
	 */
	public @ResponseBody
	List<TreeValueText> getModuleGroupAndModule(HttpServletRequest request) {
		return reportConditionService.getModuleGroupAndModule(request);

	}

	@RequestMapping("/fetchmodulefields.do")
	/**
	 * 取得所有的模块分组名称，用于选择字段窗口
	 * @param moduleName
	 * @param request
	 * @return
	 */
	public @ResponseBody
	List<TreeValueText> getModuleFields(String moduleName, HttpServletRequest request) {
		return reportConditionService.getModuleFields(moduleName, request);

	}

}
