package com.jfok.cfcmms.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.setting._SystemsetAddition;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class _SystemsetAdditionLogic extends BaseOperateLogic<_SystemsetAddition> {

	@Override
	public boolean afterUpdate(ModuleFormOperateType type, _SystemsetAddition updatedObject,
			_SystemsetAddition oldObject, HttpServletRequest request) {
		SystemInfoService.refreshAll();
		return super.afterUpdate(type, updatedObject, oldObject, request);
	}

}
