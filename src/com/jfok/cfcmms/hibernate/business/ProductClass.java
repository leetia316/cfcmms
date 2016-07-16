package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
@Entity
@SuppressWarnings("serial")
@TableDefine(group = "销售系统", id = 7018, title = "商品类别")
public class ProductClass implements Serializable {

	@Id
	@FieldDefine(title = "编码", number = 10, fieldGroup = "基本信息")
	@Column(nullable = false, length = 6)
	private String tf_productClassId;

	@FieldDefine(title = "产品类别名称", number = 20, nameField = true, fieldGroup = "基本信息")
	@Column(nullable = false, length = 50, unique = true)
	private String tf_name;

	public ProductClass() {

	}

	public String getTf_productClassId() {
		return tf_productClassId;
	}

	public void setTf_productClassId(String tf_productClassId) {
		this.tf_productClassId = tf_productClassId;
	}

	public String getTf_name() {
		return tf_name;
	}

	public void setTf_name(String tf_name) {
		this.tf_name = tf_name;
	}

}
