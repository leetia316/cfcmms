package com.jfok.cfcmms.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.log._Systembackup;

@Service
public class SystembackupService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 下载备份文件到本地
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public _Systembackup getSystemBackupBean(Integer id) {
		_Systembackup systembackup = (_Systembackup) systemBaseDAO.findById(_Systembackup.class, id);
		systembackup.setTf_isupload(true);
		systemBaseDAO.attachDirty(systembackup, null);
		return systembackup;
	}

}
