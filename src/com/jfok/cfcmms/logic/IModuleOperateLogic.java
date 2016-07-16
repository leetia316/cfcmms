package com.jfok.cfcmms.logic;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;


/**
 * 每一个模块的新增，修改，删队的处理逻辑
 * 
 * @author jfok 2012.11.14
 * 
 */
public interface IModuleOperateLogic<T> {

	/**
	 * 记录插入之前的操作
	 * 
	 * @param inserted
	 *          要被插入的 hibernate bean
	 * @param errorMessage
	 *          如果不能插入，存放发生错误的字段和错误原因
	 * @param request
	 * @return true 表示可以插入，false 表示不可以插入
	 */
	public boolean beforeInsert(T inserted, Map<String, String> errorMessage,
			HttpServletRequest request);

	public boolean afterInsert(T inserted, HttpServletRequest request);

	/**
	 * 记录修改之前的操作
	 * 
	 * @param type
	 *          修改的类型，有修改，或者审批，审核等
	 * @param updatedObject
	 *          记录修改后的bean
	 * @param oldObject
	 *          记录修改前的bean
	 * @param errorMessage
	 *          如果不能修改，存放发生错误的字段和错误原因
	 * @param request
	 * @return 表示可以修改，false 表示不可以修改
	 */
	public boolean beforeUpdate(ModuleFormOperateType type, T updatedObject, T oldObject,
			Map<String, String> errorMessage, HttpServletRequest request);

	public boolean afterUpdate(ModuleFormOperateType type, T updatedObject, T oldObject,
			HttpServletRequest request);

	public boolean beforeDelete(T deleted, List<String> errorMessage, HttpServletRequest request);

	public boolean afterDelete(T deleted, HttpServletRequest request);

	/**
	 * 取得记录的缺省值
	 * @param request
	 * @param gridFilterData 前台传过来的参数
	 * @return 缺省值字段名和字段值的集合
	 */
	public Map<String, Object> getNewDefultValue(HttpServletRequest request,
			GridFilterData gridFilterData);

}
