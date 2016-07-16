package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(id = 8091, title = "日期数值分组", group = "编码设置")
public class _NumberGroup implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_numberGroupId;

	@FieldDefine(nameField = true, title = "分组名称", number = 20)
	@Column(nullable = false, length = 50)
	private String tf_name;

	@FieldDefine(title = "备注", number = 30)
	private String tf_remark;

	public _NumberGroup() {

	}

	public Integer getTf_numberGroupId() {
	return tf_numberGroupId;
	}

	public void setTf_numberGroupId(Integer tf_numberGroupId) {
	this.tf_numberGroupId = tf_numberGroupId;
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

}
