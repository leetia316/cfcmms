package com.jfok.cfcmms.logic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.viewSetting._MenuGroup;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

/**
 * 模块 _MenuGroup 的业务逻辑处理
 * 
 * @author jfok
 * 
 */

@Service
public class _MenuGroupLogic extends BaseOperateLogic<_MenuGroup> {

	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(_MenuGroupLogic.class);

	@Override
	public boolean beforeUpdate(ModuleFormOperateType type, _MenuGroup updatedObject,
			_MenuGroup oldObject, Map<String, String> errorMessage, HttpServletRequest request) {

		return true;
	}

}
