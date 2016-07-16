package com.jfok.cfcmms.hibernate.system.module;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 模块的附加操作，可以根据权限设置放在toolstrip上
 * 
 * @author jiangfeng 2012.12.13
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9930, title = "模块附加功能")
public class _ModuleAdditionFunction implements Serializable, _IModuleControlInterface {

	@Id
	@FieldDefine(title = "序号")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(length = 10, unique = true, nullable = false)
	private Integer tf_moduleAdditionFunctionId;

	// private String tf_moduleId;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId")
	@FieldDefine(title = "模块")
	private _Module tf_Module;

	@FieldDefine(title = "功能名称")
	@Column(length = 20, nullable = false)
	private String tf_title;

	// 此号用于和应用程序中的相匹配
	@FieldDefine(title = "功能Id号")
	@Column(length = 50)
	private String tf_additionName;

	@FieldDefine(title = "功能说明")
	@Column(length = 50)
	private String tf_description;

	@FieldDefine(title = "可用")
	private Boolean tf_hasEnable;

	// 如果有菜单名称，则将菜单放在toolstrip上，此功能放在此菜单之下，如无则此功能按钮放于toolStrip之上
	@FieldDefine(title = "菜单名称")
	@Column(length = 20)
	private String tf_menuName;

	@FieldDefine(title = "图标名称")
	@Column(length = 50)
	private String tf_icon;

	@FieldDefine(title = "备注")
	private String tf_remark;

	@FieldDefine(title = "需选中记录")
	private Boolean tf_needRecord;

	@FieldDefine(title = "有窗口操作")
	private Boolean tf_showWindow;

	public _ModuleAdditionFunction() {

	}

	public _ModuleAdditionFunction(Integer tf_moduleAdditionFunctionId) {
	super();
	this.tf_moduleAdditionFunctionId = tf_moduleAdditionFunctionId;
	}

	public String getIconURL() {
	if (tf_icon == null)
		return "images/button/systemset.png";
	else
		return "images/button/" + tf_icon;
	}

	public Integer getTf_moduleAdditionFunctionId() {
	return tf_moduleAdditionFunctionId;
	}

	public void setTf_moduleAdditionFunctionId(Integer tf_moduleAdditionFunctionId) {
	this.tf_moduleAdditionFunctionId = tf_moduleAdditionFunctionId;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
	}

	public String getTf_title() {
	return tf_title;
	}

	public void setTf_title(String tf_title) {
	this.tf_title = tf_title;
	}

	public String getTf_description() {
	return tf_description;
	}

	public void setTf_description(String tf_description) {
	this.tf_description = tf_description;
	}

	public Boolean getTf_hasEnable() {
	return tf_hasEnable;
	}

	public void setTf_hasEnable(Boolean tf_hasEnable) {
	this.tf_hasEnable = tf_hasEnable;
	}

	public String getTf_menuName() {
	return tf_menuName;
	}

	public void setTf_menuName(String tf_menuName) {
	this.tf_menuName = tf_menuName;
	}

	public String getTf_icon() {
	return tf_icon;
	}

	public void setTf_icon(String tf_icon) {
	this.tf_icon = tf_icon;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

	public String getTf_additionName() {
	return tf_additionName;
	}

	public void setTf_additionName(String tf_additionName) {
	this.tf_additionName = tf_additionName;
	}

	public Boolean getTf_needRecord() {
	return tf_needRecord;
	}

	public void setTf_needRecord(Boolean tf_needRecord) {
	this.tf_needRecord = tf_needRecord;
	}

	public Boolean getTf_showWindow() {
	return tf_showWindow;
	}

	public void setTf_showWindow(Boolean tf_showWindow) {
	this.tf_showWindow = tf_showWindow;
	}

}
