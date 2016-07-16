package com.jfok.cfcmms.controller.system;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.system.SystemFrameService;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.annotation.TableDefine;


@Controller
@RequestMapping("/systemframe")
public class SystemFrameController {

	@Resource
	private SystemFrameService systemFrameService;

	/**
	 * 刷新当前模块的所有导航模块，会递归加入所有未在导航字段列表中的值，已经有的就不加入了
	 * 
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("/refreshnavigatemodule.do")
	public @ResponseBody
	Integer refreshNavigateModule(String moduleId) {
		return systemFrameService.refreshNavigateModule(moduleId);
	}

	/**
	 * 根据系统类的定义刷新当前模块的字段,在新增一个module 以后也可执行此过程
	 * 
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("/refreshfields.do")
	// @Override
	public @ResponseBody
	Integer refreshModuleField(String moduleId) {
		return systemFrameService.refreshModuleField(moduleId);
	}

	/**
	 * 根据类名加入module 定义以及字段定义，生成grid form 的缺省
	 * 
	 * @param moduleName
	 * @return
	 */

	@RequestMapping(value = "/addmodule", produces = "application/json;text/plain;charset=UTF-8")
	// @Override
	public @ResponseBody
	String addModuleWithName(String moduleName) {
		Class<?> moduleClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		if (moduleClass == null)
			return "未在指定的包中找到类:" + moduleName + "!";

		TableDefine tableDefine = (TableDefine) moduleClass.getAnnotation(TableDefine.class);
		if (tableDefine == null)
			return "未在指定的类中找到tableDefine的标注定义";

		String result = systemFrameService.addModuleWithName(moduleName, moduleClass, tableDefine);
		if (result == null) {
			// 生成字段
			systemFrameService.refreshModuleField(String.valueOf(tableDefine.id()));
			systemFrameService.createNewGridScheme(String.valueOf(tableDefine.id()), moduleClass);
			systemFrameService.createNewFormScheme(String.valueOf(tableDefine.id()), moduleClass);
		}
		return null;
	}

	@RequestMapping("/saveadditionfields.do")
	// @Override
	public @ResponseBody
	Boolean saveAdditionFields(String moduleName, String noderecords) {
		return systemFrameService.saveAdditionFields(moduleName, noderecords);
	}

	/**
	 * 根据模块名称，生成模块－－字段，的一棵树，可以让用户选择当前的grid的字段的列表方案， 只加入当前模块的父模块的所有字段
	 * manytoOne,oneToone 以及 oneTomany 中的聚合字段
	 * 
	 * @param moduleName
	 * @return 可选择字段的列表
	 */
	@RequestMapping("/getalladditionfields.do")
	// @Override
	public @ResponseBody
	List<TreeNodeRecordChecked> getAllAdditionFields(String moduleName) {
		return systemFrameService.getAllAdditionFields(moduleName);
	}

	/**
	 * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
	 * 
	 * @param GridGroupId
	 * @return
	 */
	@RequestMapping(value = "/getgridgroupfields.do")
	// @Override
	public @ResponseBody
	List<TreeNodeRecordChecked> getGridGroupFields(String gridGroupId) {
		return systemFrameService.getGridGroupFields(gridGroupId);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/savegridgroupfields.do")
	// @Override
	public @ResponseBody
	Boolean saveGridGroupFields(String gridGroupId, String noderecords) {
		return systemFrameService.saveGridGroupFields(gridGroupId, noderecords);
	}

	/**
	 * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
	 * 
	 * @param Detail
	 *          Id
	 * @return
	 */
	@RequestMapping(value = "/getdetailgroupfields.do")
	// @Override
	public @ResponseBody
	List<TreeNodeRecordChecked> getDetailGroupFields(String detailId) {
		return systemFrameService.getDetailGroupFields(detailId);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/savedetailgroupfields.do")
	// @Override
	public @ResponseBody
	Boolean saveDetailGroupFields(String detailId, String noderecords) {
		return systemFrameService.saveDetailGroupFields(detailId, noderecords);
	}

	@RequestMapping(value = "/getformgroupfields.do")
	// @Override
	public @ResponseBody
	List<TreeNodeRecordChecked> getFormGroupFields(String formGroupId) {
		return systemFrameService.getFormGroupFields(formGroupId);
	}

	@RequestMapping(value = "/saveformgroupfields.do")
	// @Override
	public @ResponseBody
	Boolean saveFormGroupFields(String formGroupId, String noderecords) {
		return systemFrameService.saveFormGroupFields(formGroupId, noderecords);
	}

}
