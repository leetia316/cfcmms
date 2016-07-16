package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@Table(name = "role_fieldhiddendetail")
/**
 * 某个用户的隐藏字段
 * 
 * @author jiangfeng
 *
 */
public class _UserFieldHiddenRoleDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tf_userId;
	@Id
	private Integer tf_fieldId;

	private String tf_moduleId;

	public _UserFieldHiddenRoleDetail() {

	}

	public Integer getTf_userId() {
	return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
	this.tf_userId = tf_userId;
	}

	public Integer getTf_fieldId() {
	return tf_fieldId;
	}

	public void setTf_fieldId(Integer tf_fieldId) {
	this.tf_fieldId = tf_fieldId;
	}

	public String getTf_moduleId() {
	return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
	this.tf_moduleId = tf_moduleId;
	}

}
