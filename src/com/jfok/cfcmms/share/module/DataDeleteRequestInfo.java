package com.jfok.cfcmms.share.module;


import java.io.Serializable;

/**
 * 数据删除时，传到后台的数据
 * 
 * @author jfok
 * 
 */
public class DataDeleteRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String moduleId; // 传入后台service的模块的id,用于确定是哪个类
	private String moduleName; // 传入后台service的模块的id,用于确定是哪个类
	private String idFieldName; // id 字段名称
	private String keyId; // 如果是取得单条记录，则用此作为主键

	public DataDeleteRequestInfo() {

	}

	@Override
	public String toString() {
		return "DataDeleteRequestInfo [moduleId=" + moduleId + ", moduleName="
				+ moduleName + ", idFieldName=" + idFieldName + ", keyId=" + keyId
				+ "]";
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

}
