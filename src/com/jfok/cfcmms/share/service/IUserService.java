package com.jfok.cfcmms.share.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.jfok.cfcmms.share.TreeNodeRecordChecked;

public interface IUserService {

	public List<TreeNodeRecordChecked> getRolesWithEmployeeId(Integer userId , HttpServletRequest request);

	public Boolean setRolesWithEmployeeId(Integer employeeId, String noderecords);

	public Boolean resetPassword(Integer employeeId);

	public Boolean changePassword(String oldPassword, String newPassword, HttpServletRequest request);

}
