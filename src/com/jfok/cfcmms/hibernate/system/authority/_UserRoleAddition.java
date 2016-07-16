package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@SuppressWarnings("serial")
@Table(name = "role_additionfunction")
/**
 * 每个用户的可以操作的附加功能，有记录表示可以操作
 * @author jiangfeng 2013.06.25
 *
 */
public class _UserRoleAddition implements Serializable {

	private Integer tf_userId;
	private String tf_moduleId;
	@Id
	private String tf_moduleAdditionFunctionId;

	public _UserRoleAddition() {
	}

	public Integer getTf_userId() {
		return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
		this.tf_userId = tf_userId;
	}

	public String getTf_moduleId() {
		return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
		this.tf_moduleId = tf_moduleId;
	}

	public String getTf_moduleAdditionFunctionId() {
		return tf_moduleAdditionFunctionId;
	}

	public void setTf_moduleAdditionFunctionId(String tf_moduleAdditionFunctionId) {
		this.tf_moduleAdditionFunctionId = tf_moduleAdditionFunctionId;
	}

}
