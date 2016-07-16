package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

/**
 * 用于给模块的记录加上筛选条件的分组
 * 
 * @author jiangfeng
 * 
 */

@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@SuppressWarnings("serial")
@TableDefine(group = "系统设置", id = 9038, title = "用户记录筛选角色")
public class _DataFilterRole implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10, hidden = false)
	private Integer tf_filterRoleId;

	@FieldDefine(title = "记录筛选角色名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false, unique = true)
	private String tf_name;

	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tf_DataFilterRole")
	private Set<_DataFilterRoleDetail> dataFilterRoleDetails;

	public _DataFilterRole() {

	}

	public Integer getTf_filterRoleId() {
	return tf_filterRoleId;
	}

	public void setTf_filterRoleId(Integer tf_filterRoleId) {
	this.tf_filterRoleId = tf_filterRoleId;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

	public Set<_DataFilterRoleDetail> getDataFilterRoleDetails() {
	return dataFilterRoleDetails;
	}

	public void setDataFilterRoleDetails(Set<_DataFilterRoleDetail> dataFilterRoleDetails) {
	this.dataFilterRoleDetails = dataFilterRoleDetails;
	}

}
