package com.jfok.cfcmms.controller.system;

import javax.annotation.Resource;

import ognl.OgnlException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.system.ModuleOperationService;
import com.jfok.cfcmms.util.ActionResult;


@Controller
@RequestMapping("/moduleoperation")
public class ModuleOperationController {

	@Resource
	private ModuleOperationService moduleOperationService;

	@RequestMapping("/savegridcolumnwidth.do")
	public @ResponseBody
	ActionResult saveGridColumnWidth(String param) {
		return moduleOperationService.saveGridColumnWidth(param);
	}

	@RequestMapping("/savegridcolumnorder.do")
	public @ResponseBody
	ActionResult saveGridColumnOrder(String param) {
		return moduleOperationService.saveGridColumnOrder(param);
	}


	@RequestMapping("/saverecordorder.do")
	public @ResponseBody
	ActionResult saverecordorder(String moduleName , String param) throws OgnlException {
		return moduleOperationService.saveRecordOrder(moduleName ,param);
	}
	
}
