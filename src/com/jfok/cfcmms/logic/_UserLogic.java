package com.jfok.cfcmms.logic;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.service.UserService;

@Service
public class _UserLogic extends BaseOperateLogic<_User> {

	@Resource
	private UserService userService;
	
	@Override
	public boolean beforeInsert(_User inserted, Map<String, String> errorMessage,
			HttpServletRequest request) {
		inserted.setTf_password("123456");
		return super.beforeInsert(inserted, errorMessage, request);
	}

	@Override
	public boolean afterInsert(_User inserted, HttpServletRequest request) {
		userService.resetPassword(inserted.getTf_userId());
		return super.afterInsert(inserted, request);
	}
	
	
}
