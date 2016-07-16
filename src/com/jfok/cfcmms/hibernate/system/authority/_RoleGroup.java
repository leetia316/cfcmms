package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 权限的分组
 * 
 * @author jiangfeng
 * 
 */

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "系统设置", id = 9040, title = "用户操作角色分组")
public class _RoleGroup implements Serializable {

	@Id
	@FieldDefine(title = "分组编码", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_roleGroupId;

	@FieldDefine(title = "角色分组名称", nameField = true, number = 20)
	@Column(length = 20, nullable = false)
	private String tf_title;

	@FieldDefine(title = "备注", number = 30)
	private String tf_remark;

	public _RoleGroup() {

	}

	public String getTf_roleGroupId() {
	return tf_roleGroupId;
	}

	public void setTf_roleGroupId(String tf_roleGroupId) {
	this.tf_roleGroupId = tf_roleGroupId;
	}

	public String getTf_title() {
	return tf_title;
	}

	public void setTf_title(String tf_title) {
	this.tf_title = tf_title;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
