package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;



import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 菜单分组
 * 
 * @author jfok
 * 
 */
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9920, title = "菜单分组")
public class _MenuGroup implements _IModuleControlInterface, Serializable {

	@Id
	@JsonIgnore
	@FieldDefine(title = "顺序号", remark = "菜单分组按顺序号显示在菜单条上", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_menuGroupId;

	@FieldDefine(title = "分组名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_title;

	@FieldDefine(title = "展开", remark = "在树状菜单下默认是否展开", number = 30)
	private Boolean tf_expand;

	@FieldDefine(title = "图标文件名", remark = "图标放置于/images/module/目录下", number = 40)
	@Column(length = 50)
	private String tf_iconURL;

	@FieldDefine(title = "分组描述", number = 50)
	@Column(length = 50)
	private String tf_description;

	@FieldDefine(title = "备注", number = 60)
	private String tf_remark;

	@JsonIgnore
	@OneToMany(targetEntity = _MenuModule.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "tf_menuGroupId")
	@OrderBy("tf_orderId")
	private List<_MenuModule> tf_menuModules;

	// 用来生成传往前台的该组下的模块菜单或者子组
	@Transient
	private List<Object> tf_submenus;
	
	public _MenuGroup() {
	}

	@Override
	public String toString() {
		return "_MenuGroup [tf_menuGroupId=" + tf_menuGroupId + ", tf_title=" + tf_title
				+ ", tf_description=" + tf_description + ", tf_iconURL=" + tf_iconURL + ", tf_remark="
				+ tf_remark + "]";
	}

	public String getTf_menuGroupId() {
		return tf_menuGroupId;
	}

	public void setTf_menuGroupId(String tf_menuGroupId) {
		this.tf_menuGroupId = tf_menuGroupId;
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

	public String getTf_iconURL() {
		return tf_iconURL;
	}

	public void setTf_iconURL(String tf_iconURL) {
		this.tf_iconURL = tf_iconURL;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

	public List<_MenuModule> getTf_menuModules() {
		return tf_menuModules;
	}

	public void setTf_menuModules(List<_MenuModule> tf_menuModules) {
		this.tf_menuModules = tf_menuModules;
	}

	public Boolean getTf_expand() {
		return tf_expand;
	}

	public void setTf_expand(Boolean tf_expand) {
		this.tf_expand = tf_expand;
	}

	public List<Object> getTf_submenus() {
		return tf_submenus;
	}

	public void setTf_submenus(List<Object> tf_submenus) {
		this.tf_submenus = tf_submenus;
	}

}
