package com.jfok.cfcmms.logic;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.setting._Systemset;
import com.jfok.cfcmms.service.SystemInfoService;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class _SystemsetLogic extends BaseOperateLogic<_Systemset> {

	@Override
	public boolean afterUpdate(ModuleFormOperateType type, _Systemset updatedObject,
			_Systemset oldObject, HttpServletRequest request) {
		SystemInfoService.refreshAll();
		return super.afterUpdate(type, updatedObject, oldObject, request);
	}

}
