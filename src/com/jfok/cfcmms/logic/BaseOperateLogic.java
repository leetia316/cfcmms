package com.jfok.cfcmms.logic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;


public class BaseOperateLogic<T> implements IModuleOperateLogic<T> {

	@Override
	public boolean beforeInsert(T inserted, Map<String, String> errorMessage,HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean beforeUpdate(ModuleFormOperateType type, T updatedObject, T oldObject,
			Map<String, String> errorMessage,HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean beforeDelete(T deleted, List<String> errorMessage,HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterInsert(T inserted,HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean afterUpdate(ModuleFormOperateType type,T updatedObject,T oldObject,HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterDelete(T deleted , HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Map<String, Object> getNewDefultValue(HttpServletRequest request , GridFilterData gridFilterData) {
		// TODO Auto-generated method stub
		return null;
	}

}

