package com.jfok.cfcmms.hibernate.system.attachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)



@TableDefine(group = "附件管理", id = 9504, title = "图片压缩模式")

public class _AttachmentReduceMode {

	@Id
	@FieldDefine(title = "序号", number = 10)
	@Column(nullable = false, length = 2)
	private Integer tf_reduceModeId;

	@FieldDefine(title = "图片压缩模式", number = 20, nameField = true)
	@Column(nullable = false, unique = true, length = 50)
	private String tf_name;

	@FieldDefine(title = "长宽最大像素", number = 30)
	private Integer tf_maxValue;

	@FieldDefine(title = "缩小比例", number = 40)
	private Integer tf_recudeTo;

	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;

	public _AttachmentReduceMode() {

	}

	@Override
	public String toString() {
	return "_AttachmentReduceMode [tf_reduceModeId=" + tf_reduceModeId + ", tf_name=" + tf_name
		+ ", tf_maxValue=" + tf_maxValue + ", tf_recudeTo=" + tf_recudeTo + ", tf_remark="
		+ tf_remark + "]";
	}

	public Integer getTf_reduceModeId() {
	return tf_reduceModeId;
	}

	public void setTf_reduceModeId(Integer tf_reduceModeId) {
	this.tf_reduceModeId = tf_reduceModeId;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public Integer getTf_maxValue() {
	return tf_maxValue;
	}

	public void setTf_maxValue(Integer tf_maxValue) {
	this.tf_maxValue = tf_maxValue;
	}

	public Integer getTf_recudeTo() {
	return tf_recudeTo;
	}

	public void setTf_recudeTo(Integer tf_recudeTo) {
	this.tf_recudeTo = tf_recudeTo;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
