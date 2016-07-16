package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 992130, title = "菜单")
public class _Menu implements _IModuleControlInterface, Serializable {

	@Id
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@JsonIgnore
	private Integer tf_id;

	@JsonIgnore
	@FieldDefine(title = "父节点号", hidden = true, number = 20)
	private Integer tf_pid;

	@FieldDefine(title = "菜单内容", number = 30)
	@Column(length = 50)
	@JsonIgnore
	private String tf_title;

	/**
	 * 对于模块和查询分组来说，如果设置了显示内容，则按显示内容显示在菜单上
	 */
	@FieldDefine(title = "显示内容", number = 40)
	@Column(length = 50)
	@JsonIgnore
	private String tf_displayTitle;

	@FieldDefine(title = "图标文件名", remark = "图标放置于/images/module/目录下", number = 50)
	@Column(length = 50)
	private String tf_iconUrl;

	@FieldDefine(title = "图标样式", remark = "图标的iconCls值", number = 60)
	@Column(length = 50)
	private String tf_iconCls;

	@FieldDefine(title = "顺序号", remark = "按顺序号显示在菜单中", number = 70)
	@Column()
	@JsonIgnore
	private Integer tf_orderId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleId")
	@FieldDefine(title = "系统模块", number = 80)
	private _Module tf_Module;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reportGroupId")
	@FieldDefine(title = "查询分组", number = 90)
	private _ReportGroup tf_ReportGroup;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reportId")
	@FieldDefine(title = "查询", number = 100)
	private _Report tf_Report;

	@FieldDefine(title = "函数名称", number = 110)
	@Column(length = 90)
	private String tf_functionName;

	@FieldDefine(title = "窗口名称", number = 120)
	@Column(length = 90)
	private String tf_windowName;

	@FieldDefine(title = "执行语句", number = 130)
	@Column(length = 90)
	private String tf_execStatument;

	@FieldDefine(title = "附加参数", number = 140)
	private String tf_parameter;

	@FieldDefine(title = "父模块约束值", number = 150)
	private String tf_parentFilter;

	@FieldDefine(title = "默认展开", number = 160)
	private Boolean tf_expand;

	@OneToMany(targetEntity = _Menu.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tf_pid")
	@OrderBy("tf_orderId")
	private List<_Menu> tf_Menus;

	public _Menu() {

	}

	public MenuTypeEnum getMenuType() {
	if (tf_Menus != null && tf_Menus.size() != 0)
		return MenuTypeEnum.group;
	else if (tf_Module != null)
		return MenuTypeEnum.module;
	else if (tf_ReportGroup != null)
		return MenuTypeEnum.reportGroup;
	else if (tf_Report != null)
		return MenuTypeEnum.report;
	else if (tf_functionName != null && tf_functionName.length() > 1)
		return MenuTypeEnum.function;
	else if (tf_windowName != null && tf_windowName.length() > 1)
		return MenuTypeEnum.window;
	else if (tf_execStatument != null && tf_execStatument.length() > 1)
		return MenuTypeEnum.executestatement;
	return MenuTypeEnum.separate;
	}

	public String getMenuTypeId() {
	if (tf_Menus.size() != 0)
		return null;
	else if (tf_Module != null)
		return tf_Module.getTf_moduleId();
	else if (tf_ReportGroup != null)
		return tf_ReportGroup.getTf_reportGroupId();
	else if (tf_Report != null)
		return tf_Report.getTf_reportId().toString();
	else
		return null;
	}

	public String getModuleName() {
	if (tf_Menus.size() != 0)
		return null;
	else if (tf_Module != null)
		return tf_Module.getTf_moduleName();
	else
		return null;
	}

	/**
	 * 
	 * 取得实际的iconUrl,如果设置了iconUrl则用，否则如果是module, 那么判断是否在images/module/下有该模块的url
	 * 
	 * @return
	 */
	public String getModuleIconUrl() {

	if (tf_iconUrl != null && tf_iconUrl.length() > 1)
		return tf_iconUrl;
	else if (tf_Module != null)
		return tf_Module.getIconURL();
	else
		return null;

	}

	/**
	 * 如果这里没有glyph,那么如果module设置了，则用moduleGlyph
	 * 
	 * @return
	 */
	public String getModuleGlyph() {
	if (tf_Menus.size() != 0)
		return null;
	else if (tf_Module != null)
		return tf_Module.getTf_glyph();
	else
		return null;
	}

	/**
	 * 取得菜单组或菜单条的显示值
	 * 
	 * @return
	 */
	public String getTitle() {
	if (tf_title != null)
		return tf_title;
	if (tf_Menus.size() != 0)
		return tf_title;
	else if (tf_Module != null)
		return tf_Module.getTf_title();
	else if (tf_ReportGroup != null)
		return tf_ReportGroup.getTf_title();
	else if (tf_Report != null)
		return tf_Report.getTf_title();
	else
		return null;
	}

	public String getIconCls(){
		if (tf_Module!= null)
			return tf_Module.getTf_iconCls();
		return tf_iconCls;
	}
	
	
	@Override
	public String toString() {
	return "_Menu [tf_id=" + tf_id + ", tf_pid=" + tf_pid + ", tf_title=" + tf_title + ", tf_type="
		+ getMenuType() + ", tf_Menus=" + tf_Menus + "]";
	}

	public Integer getTf_id() {
	return tf_id;
	}

	public void setTf_id(Integer tf_id) {
	this.tf_id = tf_id;
	}

	public Integer getTf_pid() {
	return tf_pid;
	}

	public void setTf_pid(Integer tf_pid) {
	this.tf_pid = tf_pid;
	}

	public String getTf_title() {
	return tf_title;
	}

	public void setTf_title(String tf_title) {
	this.tf_title = tf_title;
	}

	public String getTf_iconUrl() {
	return tf_iconUrl;
	}

	public void setTf_iconUrl(String tf_iconUrl) {
	this.tf_iconUrl = tf_iconUrl;
	}

	public String getTf_iconCls() {
	return tf_iconCls;
	}

	public void setTf_iconCls(String tf_iconCls) {
	this.tf_iconCls = tf_iconCls;
	}

	public Integer getTf_orderId() {
	return tf_orderId;
	}

	public void setTf_orderId(Integer tf_orderId) {
	this.tf_orderId = tf_orderId;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
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

	public String getTf_parameter() {
	return tf_parameter;
	}

	public void setTf_parameter(String tf_parameter) {
	this.tf_parameter = tf_parameter;
	}

	public String getTf_parentFilter() {
	return tf_parentFilter;
	}

	public void setTf_parentFilter(String tf_parentFilter) {
	this.tf_parentFilter = tf_parentFilter;
	}

	public List<_Menu> getTf_Menus() {
	return tf_Menus;
	}

	public void setTf_Menus(List<_Menu> tf_Menus) {
	this.tf_Menus = tf_Menus;
	}

	public String getTf_displayTitle() {
	return tf_displayTitle;
	}

	public void setTf_displayTitle(String tf_displayTitle) {
	this.tf_displayTitle = tf_displayTitle;
	}

	public Boolean getTf_expand() {
	return tf_expand;
	}

	public void setTf_expand(Boolean tf_expand) {
	this.tf_expand = tf_expand;
	}

}
