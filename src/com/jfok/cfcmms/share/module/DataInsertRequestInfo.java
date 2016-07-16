package com.jfok.cfcmms.share.module;

import java.io.Serializable;

/**
 * 数据保存或新增后，传到后台的数据
 * 
 * @author jfok
 * 
 */
public class DataInsertRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String moduleId; // 传入后台service的模块的id,用于确定是哪个类
	private String moduleName; // 传入后台service的模块的id,用于确定是哪个类
	private String idFieldName; // id 字段名称

	private String jsonRecord; // 传入后台的新增的数据

	private String parentModuleId; // 如果有父模块，传入父模块的moduleId
	private String parentModuleIdkey; // 如果有父模块，则加入此父模块的ＩＤ，为了取得一些缺省值

	private ModuleGridNavigateTreeControl navigateTreeNodeRecord; // 如果模块有树的导航，加入导航的键值

	public DataInsertRequestInfo() {

	}

	@Override
	public String toString() {
		return "DataInsertRequestInfo [moduleId=" + moduleId + ", moduleName=" + moduleName
				+ ", idFieldName=" + idFieldName + ", jsonRecord=" + jsonRecord + ", parentModuleId="
				+ parentModuleId + ", parentModuleIdkey=" + parentModuleIdkey + ", navigateTreeNodeRecord="
				+ navigateTreeNodeRecord + "]";
	}

	public String getJsonRecord() {
		return jsonRecord;
	}

	public void setJsonRecord(String jsonRecord) {
		this.jsonRecord = jsonRecord;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getParentModuleIdkey() {
		return parentModuleIdkey;
	}

	public void setParentModuleIdkey(String parentModuleIdkey) {
		this.parentModuleIdkey = parentModuleIdkey;
	}

	public ModuleGridNavigateTreeControl getNavigateTreeNodeRecord() {
		return navigateTreeNodeRecord;
	}

	public void setNavigateTreeNodeRecord(ModuleGridNavigateTreeControl navigateTreeNodeRecord) {
		this.navigateTreeNodeRecord = navigateTreeNodeRecord;
	}

	public String getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}

}
