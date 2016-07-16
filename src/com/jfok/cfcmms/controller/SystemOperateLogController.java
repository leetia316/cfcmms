package com.jfok.cfcmms.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.log._SystemOperateLog;
import com.jfok.cfcmms.util.CommonFunction;

@Controller
@RequestMapping("/systemoperatelog")
public class SystemOperateLogController {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 下载备份文件到本地
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	@RequestMapping("/download.do")
	public @ResponseBody
	void downloadBackupFile(Integer id, HttpServletResponse response) throws IOException {
		_SystemOperateLog systemOperateLog = (_SystemOperateLog) systemBaseDAO.findById(
				_SystemOperateLog.class, id);
		String fn = "(已处理的" + systemOperateLog.getTf_do() + ")" + systemOperateLog.getTf_recordname();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		InputStream br = null;
		try {
			br = systemOperateLog.getTf_filedata().getBinaryStream();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		while ((ch = br.read()) != -1)
			out.write(ch);
		CommonFunction.download(out, fn, response);
	}

}
