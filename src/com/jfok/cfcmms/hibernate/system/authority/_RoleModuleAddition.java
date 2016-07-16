package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.module._ModuleAdditionFunction;
import com.jfok.cfcmms.util.annotation.FieldDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


public class _RoleModuleAddition implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号")
	private Integer tf_roleModuleAdditionId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_roleId", nullable = false)
	@FieldDefine(title = "用户角色")
	private _Role tf_Role;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleAdditionFunctionId", nullable = false)
	@FieldDefine(title = "模块附加功能")
	private _ModuleAdditionFunction tf_ModuleAdditionFunction;

	public _RoleModuleAddition() {

	}

	public Integer getTf_roleModuleAdditionId() {
	return tf_roleModuleAdditionId;
	}

	public void setTf_roleModuleAdditionId(Integer tf_roleModuleAdditionId) {
	this.tf_roleModuleAdditionId = tf_roleModuleAdditionId;
	}

	public _Role getTf_Role() {
	return tf_Role;
	}

	public void setTf_Role(_Role tf_Role) {
	this.tf_Role = tf_Role;
	}

	public _ModuleAdditionFunction getTf_ModuleAdditionFunction() {
	return tf_ModuleAdditionFunction;
	}

	public void setTf_ModuleAdditionFunction(_ModuleAdditionFunction tf_ModuleAdditionFunction) {
	this.tf_ModuleAdditionFunction = tf_ModuleAdditionFunction;
	}

}
