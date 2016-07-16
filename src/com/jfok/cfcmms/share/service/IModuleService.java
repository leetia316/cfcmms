package com.jfok.cfcmms.share.service;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfok.cfcmms.share.module.DataDeleteResponseInfo;
import com.jfok.cfcmms.share.module.DataInsertResponseInfo;
import com.jfok.cfcmms.share.module.DataUpdateResponseInfo;


/**
 * 用于模块的基本操作的service ,包括fetch add update remove find
 * 
 * @author jfok
 * @version 1.0
 */
public interface IModuleService {
	/**
	 * grid 在需要数据的时候，调用此函数取得数据
	 * 
	 * @param dsRequest
	 *          ，调用传参数，包括起始行，终止行，排序，及筛选
	 * @return 返回总行数以及起始行与终止行的json数据
	 */
	public Map<String, Object> fetchData(String moduleName, Integer start, Integer limit,
			String sort, String query, String columns, String navigates, String parentFilter,
			HttpServletRequest request);

	/**
	 * 新增记录时去后台取得默认值
	 * 
	 * @param record
	 * @return 返回包括默认值的记录，也可以加一些上条记录的数据
	 */
	public Object getRecordNewDefault(String moduleName, String parentFilter, String navigates,
			HttpServletRequest request);

	// 根据ＩＤ，module id 取得记录
	public Object getRecordById(String moduleName, String id, HttpServletRequest request);

	public DataInsertResponseInfo add(String moduleName, String inserted, HttpServletRequest request);

	public DataUpdateResponseInfo update(String moduleName, String id, String oldid, String formtype,
			String updated, HttpServletRequest request);

	public DataDeleteResponseInfo remove(String moduleName, String id, HttpServletRequest request);

	public void exportDataExecl(String moduleName, Integer schemeOrder, String sort, String group,
			String query, String columns, String navigates, String parentFilter,
			HttpServletRequest request, HttpServletResponse response) throws IOException;

	public void exportRecordExcel(String moduleName, String id, String title, String schemeId,
			HttpServletRequest request, HttpServletResponse response) throws IOException;

	public String printRecordExcel(String moduleName, String id, String schemeId,
			HttpServletRequest request, HttpServletResponse response) throws IOException;
}
