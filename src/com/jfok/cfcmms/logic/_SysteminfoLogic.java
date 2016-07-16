package com.jfok.cfcmms.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;


@Service
public class _SysteminfoLogic extends BaseOperateLogic<_Systeminfo> {

	@Override
	public boolean afterUpdate(ModuleFormOperateType type, _Systeminfo updatedObject,
			_Systeminfo oldObject, HttpServletRequest request) {
		SystemInfoService.refreshAll();
		return super.afterUpdate(type, updatedObject, oldObject, request);
	}

}
