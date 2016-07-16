package com.jfok.cfcmms.logic.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.business.Province;
import com.jfok.cfcmms.logic.BaseOperateLogic;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class ProvinceLogic extends BaseOperateLogic<Province> {


	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Override
	public boolean beforeInsert(Province inserted, Map<String, String> errorMessage,
		HttpServletRequest request) {
	// TODO Auto-generated method stub
	return super.beforeInsert(inserted, errorMessage, request);
	}

	@Override
	public boolean beforeUpdate(ModuleFormOperateType type, Province updatedObject,
		Province oldObject, Map<String, String> errorMessage, HttpServletRequest request) {

		if (updatedObject.getTf_GDP() < oldObject.getTf_GDP())
		{
			errorMessage.put("tf_GPD", "ＧＤＰ只能增加，不能减少,必须大于"+ oldObject.getTf_GDP());
			return false;
		}
		return super.beforeUpdate(type, updatedObject, oldObject, errorMessage, request);
	}

	@Override
	public boolean beforeDelete(Province deleted, List<String> errorMessage,
		HttpServletRequest request) {
	// TODO Auto-generated method stub
	return super.beforeDelete(deleted, errorMessage, request);
	}

	@Override
	public boolean afterInsert(Province inserted, HttpServletRequest request) {
	// TODO Auto-generated method stub
	return super.afterInsert(inserted, request);
	}

	@Override
	public boolean afterUpdate(ModuleFormOperateType type, Province updatedObject,
		Province oldObject, HttpServletRequest request) {
	// TODO Auto-generated method stub
	return super.afterUpdate(type, updatedObject, oldObject, request);
	}

	@Override
	public boolean afterDelete(Province deleted, HttpServletRequest request) {
	// TODO Auto-generated method stub
	return super.afterDelete(deleted, request);
	}

	@Override
	public Map<String, Object> getNewDefultValue(HttpServletRequest request,
		GridFilterData gridFilterData) {

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("tf_GDP", "10000");
		result.put("tf_name", "请修改此名称" );
		
	return result;
	}
	
	
	
	
}
