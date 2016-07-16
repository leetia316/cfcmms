package com.jfok.cfcmms.share.service;

import java.util.List;

import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridNavigate;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.TreeModeEnum;



/**
 * 用于获取一个模块的控制tree
 * 
 * @author jfok
 * 
 */
public interface INavigateTreeService {

	/**
	 * 根据parentsPath 的path值，来生成module--值的树图，如 menuGroup--menu 生成 menugroup
	 * --menugroup1 menu1 menu2...
	 * 
	 * @param moduleName
	 * @param parentModuleName
	 * @param isContainNullRecord 是否包括没有记录的tree record
	 * @return
	 */
	public List<TreeNodeRecord> getTreeRecords(String moduleName, _ModuleGridNavigate parentsPath,
			TreeModeEnum treeModeEnum, Boolean isContainNullRecord,GridFilterData gridFilterData);

		
}
