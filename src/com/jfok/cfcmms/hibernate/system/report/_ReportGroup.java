package com.jfok.cfcmms.hibernate.system.report;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




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

@TableDefine(group = "系统设置", id = 9050, title = "综合查询分组")
public class _ReportGroup implements _IModuleControlInterface, Serializable {

	@Id
	@FieldDefine(title = "顺序号", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_reportGroupId;

	@FieldDefine(title = "分组名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_title;

	@FieldDefine(title = "分隔下一条", number = 30)
	private Boolean tf_addSeparator;

	@FieldDefine(title = "图标文件名", remark = "图标放置于/images/module/目录下", number = 40)
	@Column(length = 50)
	private String tf_iconURL;

	@FieldDefine(title = "分组描述", number = 50)
	@Column(length = 50)
	private String tf_description;

	@FieldDefine(title = "备注", number = 60)
	private String tf_remark;

	// @Transient
	// private List<_MenuModule> tf_menuModules;

	public _ReportGroup() {
		
	}

	public String getTf_reportGroupId() {
		return tf_reportGroupId;
	}

	public void setTf_reportGroupId(String tf_reportGroupId) {
		this.tf_reportGroupId = tf_reportGroupId;
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

	public Boolean getTf_addSeparator() {
		return tf_addSeparator;
	}

	public void setTf_addSeparator(Boolean tf_addSeparator) {
		this.tf_addSeparator = tf_addSeparator;
	}

}
