package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@TableDefine(group = "销售系统", id = 7014, title = "客户等级")
public class Rate implements Serializable {

	@Id
	@FieldDefine(title = "编码", number = 10, fieldGroup = "基本信息")
	@Column(nullable = false, length = 2)
	private String tf_rateId;

	@FieldDefine(title = "等级名称", number = 20, nameField = true, fieldGroup = "基本信息")
	@Column(nullable = false, length = 50, unique = true)
	private String tf_name;

	public Rate() {

	}

	public String getTf_rateId() {
		return tf_rateId;
	}

	public void setTf_rateId(String tf_rateId) {
		this.tf_rateId = tf_rateId;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

}
