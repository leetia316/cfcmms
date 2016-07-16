package com.jfok.cfcmms.share.module;

import java.io.Serializable;
import java.util.List;

import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.share.SortParameter;

/**
 * 前台listgrid fetch 数据时，传入后台的数据
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
public class DataFetchRequestInfo implements Serializable {

	private String moduleId; // moduleID
	private String moduleName; // moduleName
	private Integer startRow; // 起始行
	private Integer endRow; // 终止行
	private SortParameter[] sorts; // 当前grid 的排序字段
	private Boolean isExport; // 是否是执行的导出
	
	private List<SqlModuleFilter> navigateFilters ;    //导航树的值
	
	private ModuleOperateType moduleOperateType; // grid 的操作类型

	public DataFetchRequestInfo() {
		super();
	}

	public DataFetchRequestInfo(String moduleId, String moduleName, Integer startRow,
			Integer endRow, ModuleOperateType moduleOperateType) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.startRow = startRow;
		this.endRow = endRow;
		this.moduleOperateType = moduleOperateType;

	}

	@Override
	public String toString() {
		return "DataFetchRequestInfo [moduleId=" + moduleId + ", moduleName=" + moduleName
				+ ", startRow=" + startRow + ", endRow=" + endRow + ", sorts=" + sorts
				+ ", isExport=" + isExport + ", moduleOperateType=" + moduleOperateType + "]";
	}


	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}


	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Boolean getIsExport() {
		return isExport == null ? false : isExport;
	}

	public void setIsExport(Boolean isExport) {
		this.isExport = isExport;
	}

	public ModuleOperateType getModuleOperateType() {
		return moduleOperateType;
	}

	public SortParameter[] getSorts() {
		return sorts;
	}

	public void setSorts(SortParameter[] sorts) {
		this.sorts = sorts;
	}

	public void setModuleOperateType(ModuleOperateType moduleOperateType) {
		this.moduleOperateType = moduleOperateType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public List<SqlModuleFilter> getNavigateFilters() {
		return navigateFilters;
	}

	public void setNavigateFilters(List<SqlModuleFilter> navigateFilters) {
		this.navigateFilters = navigateFilters;
	}



}
