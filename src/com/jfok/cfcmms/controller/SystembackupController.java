package com.jfok.cfcmms.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.hibernate.system.log._Systembackup;
import com.jfok.cfcmms.service.SystembackupService;
import com.jfok.cfcmms.util.CommonFunction;

@Controller
@RequestMapping("/systembackup")
public class SystembackupController {

	@Resource
	private SystembackupService systembackupService;

	/**
	 * 下载备份文件到本地
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/download.do")
	public @ResponseBody
	void downloadBackupFile(Integer id, HttpServletResponse response) throws IOException {
		_Systembackup systembackup = systembackupService.getSystemBackupBean(id);
		String fn = systembackup.getTf_backupfilename();
		int pos = fn.lastIndexOf("\\");
		fn = fn.substring(pos + 1);
		CommonFunction.download(systembackup.getTf_backupfilename(), fn, response, false);
	}

}
