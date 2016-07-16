package com.jfok.cfcmms.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.UserService;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 取得该用户的签名图片，是上传到user中的png图片
	 * 
	 * @param usernaem
	 * @throws IOException 
	 */
	@RequestMapping("/getsignphoto.do")
	public void getUserSignPhoto(String userid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		userService.getUserSignPhoto(userid, request, response);
	}

	@RequestMapping("/getuserroles.do")
	public @ResponseBody
	List<TreeNodeRecordChecked> getRolesWithEmployeeId(Integer userId, HttpServletRequest request) {
		return userService.getRolesWithEmployeeId(userId, request);
	}

	@RequestMapping("/saveuserroles.do")
	public @ResponseBody
	Boolean setRolesWithEmployeeId(Integer userId, String noderecords, HttpServletRequest request) {
		return userService.setRolesWithEmployeeId(userId, noderecords);
	}

	@RequestMapping("/getuserfieldroles.do")
	public @ResponseBody
	List<TreeNodeRecordChecked> getFieldRolesWithEmployeeId(Integer userId) {
		return userService.getFieldRolesWithEmployeeId(userId);
	}

	@RequestMapping("/saveuserfieldroles.do")
	public @ResponseBody
	Boolean setFieldRolesWithEmployeeId(Integer userId, String noderecords) {
		return userService.setFieldRolesWithEmployeeId(userId, noderecords);
	}

	@RequestMapping("/resetpassword.do")
	public @ResponseBody
	Boolean resetPassword(Integer userId) {
		return userService.resetPassword(userId);
	}

	@RequestMapping("/changepassword.do")
	public @ResponseBody
	Boolean changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
		return userService.changePassword(oldPassword, newPassword, request);
	}

}
