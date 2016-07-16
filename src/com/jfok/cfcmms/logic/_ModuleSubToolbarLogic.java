package com.jfok.cfcmms.logic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleSubToolbar;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class _ModuleSubToolbarLogic extends BaseOperateLogic<_ModuleSubToolbar> {

	@Override
	public boolean beforeInsert(_ModuleSubToolbar inserted, Map<String, String> errorMessage,
			HttpServletRequest request) {
		_Module sub = SystemAndLoginInfoService.getModuleWithName(inserted.getTf_subMoudleName());
		if (sub == null) {
			errorMessage.put("tf_subMoudleName", "子模块名称未在系统中找到");
			return false;
		} else
			inserted.setTf_subModuleTitle(sub.getTf_title());
		return super.beforeInsert(inserted, errorMessage, request);
	}

	@Override
	public boolean beforeUpdate(ModuleFormOperateType type, _ModuleSubToolbar updatedObject,
			_ModuleSubToolbar oldObject, Map<String, String> errorMessage, HttpServletRequest request) {
		_Module sub = SystemAndLoginInfoService.getModuleWithName(updatedObject.getTf_subMoudleName());
		if (sub == null) {
			errorMessage.put("tf_subMoudleName", "子模块名称未在系统中找到");
			return false;
		} else
			updatedObject.setTf_subModuleTitle(sub.getTf_title());
		return super.beforeUpdate(type, updatedObject, oldObject, errorMessage, request);
	}

}
