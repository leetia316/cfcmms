package com.jfok.cfcmms.hibernate.system.attachment;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




import com.jfok.cfcmms.util.annotation.FieldDefine;

/**
 * 读取附件文件时的类，只读取文件的数据
 * 
 * @author jiangfeng
 * 
 */
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@Table(name = "_Attachment")
public class _Attachment_FileData implements Serializable {

	@Id
	@Column(updatable = false)
	private Integer tf_attachmentId; // 附件序号

	@Column(updatable = false)
	private String tf_moduleId;// 模块ＩＤ

	@Column(updatable = false)
	private Integer tf_moduleIdvalue;// 模块Idkey

	@Column(updatable = false)
	private String tf_name;// 名称

	@Column(updatable = false)
	private String tf_filename;// 文件名

	@FieldDefine(title = "文件压缩保存")
	private Boolean tf_fileCompressed;

	private Blob tf_filedata;// 文件数据

	private Blob tf_pdfdata; // 如果是word 文件，那么就存放转换好的 pdf文件，可以直接预览

	public _Attachment_FileData() {

	}

	public Integer getTf_attachmentId() {
	return tf_attachmentId;
	}

	public void setTf_attachmentId(Integer tf_attachmentId) {
	this.tf_attachmentId = tf_attachmentId;
	}

	public Blob getTf_filedata() {
	return tf_filedata;
	}

	public void setTf_filedata(Blob tf_filedata) {
	this.tf_filedata = tf_filedata;
	}

	public String getTf_filename() {
	return tf_filename;
	}

	public void setTf_filename(String tf_filename) {
	this.tf_filename = tf_filename;
	}

	public String getTf_moduleId() {
	return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
	this.tf_moduleId = tf_moduleId;
	}

	public Integer getTf_moduleIdvalue() {
	return tf_moduleIdvalue;
	}

	public void setTf_moduleIdvalue(Integer tf_moduleIdvalue) {
	this.tf_moduleIdvalue = tf_moduleIdvalue;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public Boolean getTf_fileCompressed() {
	return tf_fileCompressed;
	}

	public void setTf_fileCompressed(Boolean tf_fileCompressed) {
	this.tf_fileCompressed = tf_fileCompressed;
	}

	public Blob getTf_pdfdata() {
	return tf_pdfdata;
	}

	public void setTf_pdfdata(Blob tf_pdfdata) {
	this.tf_pdfdata = tf_pdfdata;
	}

}
