package com.jfok.cfcmms.logic;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.setting._PropertyType;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;


@Service
public class _ModuleFieldLogic extends BaseOperateLogic<_ModuleField> {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Override
	public boolean beforeUpdate(ModuleFormOperateType type,
			_ModuleField updatedObject, _ModuleField oldObject,
			Map<String, String> errorMessage, HttpServletRequest request) {

		// 如果是第“空值”，则置为空
		if (updatedObject.getTf_PropertyType() != null
				&& updatedObject.getTf_PropertyType().getTf_propertyTypeId() != null) {
			_PropertyType ptype = (_PropertyType) systemBaseDAO.findById(
					_PropertyType.class, updatedObject.getTf_PropertyType()
							.getTf_propertyTypeId());
			if (ptype.getTf_name().equals("空值"))
				updatedObject.setTf_PropertyType(null);
		}
		return super.beforeUpdate(type, updatedObject, oldObject, errorMessage,
				request);
	}

}
