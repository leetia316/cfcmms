package com.jfok.cfcmms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.setting._NumberGroup;
import com.jfok.cfcmms.service.NavigateTreeService;
import com.jfok.cfcmms.share.TreeNodeRecord;

@Controller
@RequestMapping("/navigatetree")
/**
 * 取得模块的控制树的值
 * @author jfok
 *
 */
public class NavigateTreeController {

	@Resource
	private NavigateTreeService navigateTreeService;

	/**
	 * gridFilterData 是传进来当前模块的约束，有的话 ，也加在tree 里面
	 */
	@RequestMapping(value = "/fetchdata.do", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNodeRecord> getTreeRecords(String moduleName, String title, String navigatePath,
			Boolean isBaseField, Boolean cascading, Boolean isContainNullRecord, String parentFilter,
			String mode, String type, Boolean reverseOrder, HttpServletRequest request) {
		
		_NumberGroup numberGroup = null;
		if (mode != null)
			numberGroup = (_NumberGroup) JSONObject.toBean(JSONObject.fromObject(mode),
					_NumberGroup.class);
		
		
		SqlModuleFilter pFilter = null;
		if (parentFilter != null && parentFilter.length() > 10) {
			JSONObject jo = JSONObject.fromObject(parentFilter);
			pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
		}
		
		return navigateTreeService.getTreeRecords(moduleName, title, navigatePath, isBaseField,
				cascading, isContainNullRecord, pFilter, numberGroup, type, reverseOrder, request);
	}

}