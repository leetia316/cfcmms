package com.jfok.cfcmms.share.service;

import java.util.List;

import com.jfok.cfcmms.hibernate.system.module._ModuleGroup;
import com.jfok.cfcmms.share.RolePopedom;


/**
 * 设置用户角色的权限
 * 
 * @author jiangfeng
 * 
 */
public interface IRoleSetPopedomService {

	public List<_ModuleGroup> getRolePopedoms(String roleId);

	public Boolean setRolePopedoms(String roleId, RolePopedom rolePopedoms[] ,String additionids);

}
