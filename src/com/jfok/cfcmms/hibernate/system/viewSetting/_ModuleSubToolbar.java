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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 990320, title = "子模块按钮方案")
public class _ModuleSubToolbar implements _IModuleControlInterface, Serializable {

	@Id
	@JsonIgnore
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_Id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId", nullable = false)
	@FieldDefine(title = "所属模块", number = 20)
	private _Module tf_Module;

	@JsonIgnore
	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "子模块标识", number = 40)
	@Column(nullable = false, length = 50)
	private String tf_subMoudleName;

	@JsonIgnore
	@FieldDefine(title = "子模块名称", number = 50, nameField = true)
	@Column(nullable = false, length = 50)
	private String tf_subModuleTitle;

	@FieldDefine(title = "窗口中打开", number = 60)
	private Boolean tf_openInWindow;

	@FieldDefine(title = "更多菜单下", number = 70)
	private Boolean tf_inSubMenu;
	
	public _ModuleSubToolbar() {

	}

	public Integer getTf_Id() {
		return tf_Id;
	}

	public void setTf_Id(Integer tf_Id) {
		this.tf_Id = tf_Id;
	}

	public _Module getTf_Module() {
		return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
		this.tf_Module = tf_Module;
	}

	public Integer getTf_order() {
		return tf_order;
	}

	public void setTf_order(Integer tf_order) {
		this.tf_order = tf_order;
	}

	public String getTf_subMoudleName() {
		return tf_subMoudleName;
	}

	public void setTf_subMoudleName(String tf_subMoudleName) {
		this.tf_subMoudleName = tf_subMoudleName;
	}

	public Boolean getTf_openInWindow() {
		return tf_openInWindow;
	}

	public void setTf_openInWindow(Boolean tf_openInWindow) {
		this.tf_openInWindow = tf_openInWindow;
	}

	public String getTf_subModuleTitle() {
		return tf_subModuleTitle;
	}

	public void setTf_subModuleTitle(String tf_subModuleTitle) {
		this.tf_subModuleTitle = tf_subModuleTitle;
	}

	public Boolean getTf_inSubMenu() {
		return tf_inSubMenu;
	}

	public void setTf_inSubMenu(Boolean tf_inSubMenu) {
		this.tf_inSubMenu = tf_inSubMenu;
	}

}
