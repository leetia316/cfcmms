package com.jfok.cfcmms.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.hibernate.system.module._ModuleGroup;
import com.jfok.cfcmms.service.RoleService;
import com.jfok.cfcmms.share.RolePopedom;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;

@Controller
@RequestMapping("/role")
/**
 * 角色的读取和设置的保存控制
 * @author jiangfeng
 *
 */
public class RoleController {

	
	@Resource
	private RoleService roleService;

	@RequestMapping("/getrolepopedoms.do")
	public @ResponseBody
	List<_ModuleGroup> getRolePopedoms(String roleId , HttpServletRequest request) {
		return roleService.getRolePopedoms(roleId);
	}


	@RequestMapping("/getuserpopedoms.do")
	public @ResponseBody
	List<_ModuleGroup> getUserPopedoms(Integer userId , HttpServletRequest request) {
		return roleService.getUserPopedoms(userId);
	}

	
	@RequestMapping("/saverolepopedoms.do")
	public @ResponseBody
	Boolean setRolePopedoms(String roleId, String formdata, String additionids , HttpServletRequest request) {
		JsonConfig config = new JsonConfig();
		config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		config.setRootClass(RolePopedom.class);
		RolePopedom rolePopedoms[] = (RolePopedom[]) JSONSerializer.toJava(
				JSONArray.fromObject(formdata), config);
		return roleService.setRolePopedoms(roleId, rolePopedoms, additionids);

	}

	/**
	 * 
	 */
	@RequestMapping("/fetchmodulerecordtree.do")
	/**
	 * 根据模块名称,以及模块筛选条件的角色id号，取得该模块的数据 tree ,用于设置用户的筛选条件
	 * @param moduleName
	 * @param request
	 * @return
	 */
	public @ResponseBody
	List<TreeNodeRecordChecked> getModuleTreeData(String moduleId , Integer roleId, HttpServletRequest request) {
		return roleService.getModuleTreeData(moduleId , roleId, request);

	}
	
}
