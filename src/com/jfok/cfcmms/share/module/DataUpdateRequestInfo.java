package com.jfok.cfcmms.share.module;

import java.io.Serializable;

/**
 * 数据保存或新增后，传到后台的数据
 * 
 * @author jfok
 * 
 */
public class DataUpdateRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String moduleId; // 传入后台service的模块的id,用于确定是哪个类
	private String moduleName; // 传入后台service的模块的id,用于确定是哪个类
	private ModuleFormOperateType formOperateType; // 保存的类型 ，修改，审核，审批，等等
	private String idFieldName; // id 字段名称
	private String keyId; // 如果是取得单条记录，则用此作为主键
	private String oldKeyId; // 如果主键被修改过了，那么要传入原来的主键
	private String jsonRecord; // 传入后台的新增，修改过删除的数据

	public DataUpdateRequestInfo() {

	}

	@Override
	public String toString() {
		return "DataUpdateRequestInfo [moduleId=" + moduleId + ", moduleName=" + moduleName
				+ ", formOperateType=" + formOperateType + ", idFieldName=" + idFieldName + ", keyId="
				+ keyId + ", oldKeyId=" + oldKeyId + ", jsonRecord=" + jsonRecord + "]";
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

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public ModuleFormOperateType getFormOperateType() {
		return formOperateType;
	}

	public void setFormOperateType(ModuleFormOperateType formOperateType) {
		this.formOperateType = formOperateType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOldKeyId() {
		return oldKeyId;
	}

	public void setOldKeyId(String oldKeyId) {
		this.oldKeyId = oldKeyId;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}

}
