package com.jfok.cfcmms.share.service;

import java.util.List;

import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.util.annotation.TableDefine;


/**
 * 用于系统和模块架构的服务
 * 
 * @author jfok
 * 
 */
public interface ISystemFrameService {

	/**
	 * 根据模块名称，生成模块－－字段，的一棵树，可以让用户选择当前的grid的字段的列表方案， 只加入当前模块的父模块的所有字段
	 * manytoOne,oneToone 以及 oneTomany 中的聚合字段
	 * 
	 * @param moduleName
	 * @return
	 */
	public List<TreeNodeRecordChecked> getAllAdditionFields(String moduleName);

	/**
	 * 保存用户对于当前模块附加字段的选择
	 * 
	 * @param moduleName
	 * @param records
	 * @return
	 */
	public Boolean saveAdditionFields(String moduleName, String noderecords);

	/**
	 * 根据模块名称生成map ,包括id和name ,用于在manytoOne字段中显示在下拉框中的
	 * 
	 * @param moduleName
	 * @return
	 */

	/**
	 * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
	 * 
	 * @param GridGroupId
	 * @return
	 */
	public List<TreeNodeRecordChecked> getGridGroupFields(String gridGroupId);

	/**
	 * 保存grid Group 所选择的字段，如果该字段已经在其他组里了，则不保存
	 * 
	 * @param gridGroupId
	 * @return
	 */
	public Boolean saveGridGroupFields(String gridGroupId, String noderecords);

	/**
	 * 用于取得当前模块和所有选中的父模块和子模块的聚合字段，可以用来选择，保存在groupfield之下
	 * 
	 * @param GridGroupId
	 * @return
	 */
	public List<TreeNodeRecordChecked> getFormGroupFields(String formGroupId);

	/**
	 * 保存grid Group 所选择的字段，如果该字段已经在其他组里了，则不保存
	 * 
	 * @param gridGroupId
	 * @return
	 */
	public Boolean saveFormGroupFields(String formGroupId, String noderecords);

	/**
	 * 根据系统类的定义刷新当前模块的字段
	 * 
	 * @param moduleId
	 * @return
	 */
	public Integer refreshModuleField(String moduleId);

	/**
	 * 根据类名加入module 定义以及字段定义，生成grid form 的缺省
	 * 
	 * @param moduleName
	 * @return
	 */
	public String addModuleWithName(String moduleName , Class<?> moduleClass , TableDefine tableDefine);
}
