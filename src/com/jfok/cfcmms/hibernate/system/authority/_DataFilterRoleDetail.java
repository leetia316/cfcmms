package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 
 * 用于给模块的记录加上筛选条件的分组,这个是每个分组里面的筛选记录的设置
 * 
 * @author jiangfeng
 * 
 */

@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@SuppressWarnings("serial")
@TableDefine(group = "系统设置", id = 903810, title = "用户记录筛选设置明细")
public class _DataFilterRoleDetail implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10, hidden = false)
	private Integer tf_filtRoleDetailId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_filterRoleId", nullable = false)
	@FieldDefine(title = "用户记录筛选角色", number = 20)
	private _DataFilterRole tf_DataFilterRole;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_moduleId", nullable = false)
	@FieldDefine(title = "所属模块", number = 30)
	private _Module tf_Module;

	@FieldDefine(title = "筛选设置说明", nameField = true, number = 40)
	@Column(length = 50, nullable = false, unique = true)
	private String tf_name;

	@FieldDefine(title = "记录主键集合", number = 50)
	private String tf_recordIds;

	@FieldDefine(title = "记录名称集合", number = 60)
	private String tf_recordNames;

	public _DataFilterRoleDetail() {

	}

	public Integer getTf_filtRoleDetailId() {
	return tf_filtRoleDetailId;
	}

	public void setTf_filtRoleDetailId(Integer tf_filtRoleDetailId) {
	this.tf_filtRoleDetailId = tf_filtRoleDetailId;
	}

	public _DataFilterRole getTf_DataFilterRole() {
	return tf_DataFilterRole;
	}

	public void setTf_DataFilterRole(_DataFilterRole tf_DataFilterRole) {
	this.tf_DataFilterRole = tf_DataFilterRole;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public String getTf_recordIds() {
	return tf_recordIds;
	}

	public void setTf_recordIds(String tf_recordIds) {
	this.tf_recordIds = tf_recordIds;
	}

	public String getTf_recordNames() {
	return tf_recordNames;
	}

	public void setTf_recordNames(String tf_recordNames) {
	this.tf_recordNames = tf_recordNames;
	}

}
