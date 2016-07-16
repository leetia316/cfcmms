package com.jfok.cfcmms.share.service;

import javax.servlet.http.HttpServletRequest;

import com.jfok.cfcmms.share.info.RoleInfo;


public interface ISystemAndLoginInfoService {

	public RoleInfo getRoleInfo(HttpServletRequest request);

}
