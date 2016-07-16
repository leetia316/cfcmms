package com.jfok.cfcmms.hibernate.system.attachment;

import java.io.Serializable;
import java.sql.Blob;

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


@TableDefine(group = "附件管理", id = 9503, title = "附件文件类型")
public class _AttachmentFileType implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", hidden = true)
	private Integer tf_fileTypeId;

	@FieldDefine(title = "附件文件类型名称", nameField = true)
	@Column(length = 50, nullable = false, unique = true)
	private String tf_name;

	@FieldDefine(title = "备注")
	private String tf_remark;

	private Blob blobfield;
	
	public Blob getBlobfield() {
		return blobfield;
	}

	public void setBlobfield(Blob blobfield) {
		this.blobfield = blobfield;
	}

	public _AttachmentFileType() {

	}

	@Override
	public String toString() {
	return "_AdditionFileType [tf_additionFileTypeId=" + tf_fileTypeId + ", tf_name="
		+ tf_name + ", tf_remark=" + tf_remark + "]";
	}

	public Integer getTf_fileTypeId() {
		return tf_fileTypeId;
	}

	public void setTf_fileTypeId(Integer tf_fileTypeId) {
		this.tf_fileTypeId = tf_fileTypeId;
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
