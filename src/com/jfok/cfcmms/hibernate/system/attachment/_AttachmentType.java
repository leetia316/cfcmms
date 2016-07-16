package com.jfok.cfcmms.hibernate.system.attachment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "附件管理", id = 9502, title = "附件类型")
public class _AttachmentType implements Serializable {

	@Id
	// @GeneratedValue(generator = "increment")
	// @GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "编码", number = 10)
	@Column(length = 10, nullable = false)
	private String tf_typeId;

	@FieldDefine(title = "附件类型名称", nameField = true)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "备注")
	private String tf_remark;

	public _AttachmentType() {

	}

	public String getTf_typeId() {
	return tf_typeId;
	}

	public void setTf_typeId(String tf_typeId) {
	this.tf_typeId = tf_typeId;
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
