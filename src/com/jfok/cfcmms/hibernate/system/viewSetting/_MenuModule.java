package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.report._Report;
import com.jfok.cfcmms.hibernate.system.report._ReportGroup;
import com.jfok.cfcmms.share.info.MenuTypeEnum;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 系统可使用的菜单项,包括系统模块，综合查询，查询方案，函数名称，窗口名称，执行的语句
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9921, title = "系统菜单")
public class _MenuModule implements _IModuleControlInterface, Serializable {

	@Id
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonIgnore
	private Integer tf_menuModuleId;

	@FieldDefine(title = "顺序号", remark = "按顺序号显示在菜单中", number = 20)
	@Column(nullable = false)
	@JsonIgnore
	private Integer tf_orderId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_menuGroupId", nullable = false)
	@FieldDefine(title = "菜单分组", nameField = true, number = 30)
	private _MenuGroup tf_MenuGroup;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleId")
	@FieldDefine(title = "系统模块", number = 40)
	private _Module tf_Module;

	@FieldDefine(title = "分隔下一条", number = 60)
	private Boolean tf_addSeparator;

	@FieldDefine(title = "菜单标题", number = 70)
	@Column(length = 50)
	private String tf_title;

	@FieldDefine(title = "父模块约束设置", number = 80)
	private String tf_parentFilter;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reportGroupId")
	@FieldDefine(title = "综合查询", number = 110)
	private _ReportGroup tf_ReportGroup;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reportId")
	@FieldDefine(title = "综合查询", number = 120)
	private _Report tf_Report;
	
	@FieldDefine(title = "函数名称", number = 130)
	@Column(length = 90)
	private String tf_functionName;
	
	@FieldDefine(title = "窗口名称", number = 140)
	@Column(length = 90)
	private String tf_windowName;
	
	@FieldDefine(title = "执行语句", number = 140)
	@Column(length = 90)
	private String tf_execStatument;

	@Transient
	private MenuTypeEnum menuType;
	
	@Transient
	private String menuTypeId;
	
	
	public _MenuModule() {

	}

	public Integer getTf_menuModuleId() {
		return tf_menuModuleId;
	}

	public void setTf_menuModuleId(Integer tf_menuModuleId) {
		this.tf_menuModuleId = tf_menuModuleId;
	}

	public Integer getTf_orderId() {
		return tf_orderId;
	}

	public void setTf_orderId(Integer tf_orderId) {
		this.tf_orderId = tf_orderId;
	}

	public Boolean getTf_addSeparator() {
		return tf_addSeparator == null ? false : tf_addSeparator;
	}

	public void setTf_addSeparator(Boolean tf_addSeparator) {
		this.tf_addSeparator = tf_addSeparator;
	}

	public _MenuGroup getTf_MenuGroup() {
		return tf_MenuGroup;
	}

	public void setTf_MenuGroup(_MenuGroup tf_MenuGroup) {
		this.tf_MenuGroup = tf_MenuGroup;
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

	public String getTf_parentFilter() {
		return tf_parentFilter;
	}

	public void setTf_parentFilter(String tf_parentFilter) {
		this.tf_parentFilter = tf_parentFilter;
	}

	public _ReportGroup getTf_ReportGroup() {
		return tf_ReportGroup;
	}

	public void setTf_ReportGroup(_ReportGroup tf_ReportGroup) {
		this.tf_ReportGroup = tf_ReportGroup;
	}

	public _Report getTf_Report() {
		return tf_Report;
	}

	public void setTf_Report(_Report tf_Report) {
		this.tf_Report = tf_Report;
	}

	public String getTf_functionName() {
		return tf_functionName;
	}

	public void setTf_functionName(String tf_functionName) {
		this.tf_functionName = tf_functionName;
	}

	public String getTf_windowName() {
		return tf_windowName;
	}

	public void setTf_windowName(String tf_windowName) {
		this.tf_windowName = tf_windowName;
	}

	public String getTf_execStatument() {
		return tf_execStatument;
	}

	public void setTf_execStatument(String tf_execStatument) {
		this.tf_execStatument = tf_execStatument;
	}

	public MenuTypeEnum getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuTypeEnum menuType) {
		this.menuType = menuType;
	}

	public String getMenuTypeId() {
		return menuTypeId;
	}

	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}

}
