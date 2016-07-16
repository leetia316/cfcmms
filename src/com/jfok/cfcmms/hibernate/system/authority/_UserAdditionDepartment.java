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

import com.jfok.cfcmms.hibernate.system.setting._User;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@SuppressWarnings("serial")
@TableDefine(group = "系统设置", id = 9043, title = "用户附加部门")
public class _UserAdditionDepartment implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(updatable = false, nullable = false, unique = true)
	@FieldDefine(title = "序号", number = 10, hidden = true)
	private Integer tf_additionDepaId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_allDepartmentId", nullable = false)
	@FieldDefine(title = "可视部门", number = 20)
	private _AllDepartment tf_AllDepartment;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_userId", nullable = false)
	@FieldDefine(title = "用户", number = 30)
	private _User tf_User;

	public _UserAdditionDepartment() {

	}

	public Integer getTf_additionDepaId() {
		return tf_additionDepaId;
	}

	public void setTf_additionDepaId(Integer tf_additionDepaId) {
		this.tf_additionDepaId = tf_additionDepaId;
	}

	public _AllDepartment getTf_AllDepartment() {
		return tf_AllDepartment;
	}

	public void setTf_AllDepartment(_AllDepartment tf_AllDepartment) {
		this.tf_AllDepartment = tf_AllDepartment;
	}

	public _User getTf_User() {
		return tf_User;
	}

	public void setTf_User(_User tf_User) {
		this.tf_User = tf_User;
	}

}
