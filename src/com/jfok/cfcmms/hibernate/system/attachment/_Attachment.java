package com.jfok.cfcmms.hibernate.system.attachment;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(group = "附件管理", id = 9505, title = "附件")
public class _Attachment extends _InputInfoAbstract implements Serializable {

	public static final String ATTACHMENTCOUNT = "tf_attachmentCount";
	public static final String _ATTACHMENT = "_Attachment";
	public static final String MODULEID = "tf_moduleId";
	public static final String MODULEKEYID = "tf_moduleIdvalue";
	public static final String ATTACHMENTFILENAME = "tf_filename";
	public static final String ATTACHMENTID = "tf_attachmentId";
	public static final String ATTACHMENTORDER = "tf_order";
	public static final String ATTACHMENTNAME = "tf_name";
	public static final String ATTACHMENTREMARK = "tf_remark";
	public static final String WIDTH = "tf_imgwidth";
	public static final String HEIGHT = "tf_imgheight";

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", hidden = true, number = 10)
	private Integer tf_attachmentId; // 附件序号

	@Column(nullable = false, updatable = false)
	@FieldDefine(title = "模块Id", number = 20)
	private String tf_moduleId;// 模块ＩＤ

	@Column(nullable = false, updatable = false)
	@FieldDefine(title = "记录主键值", number = 30)
	private Integer tf_moduleIdvalue;// 模块Idkey

	@FieldDefine(title = "序号", number = 40)
	private Integer tf_order;// 显示顺序号

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_typeId", nullable = false)
	@FieldDefine(title = "附件类型", number = 50)
	private _AttachmentType tf_AttachmentType;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fieldId")
	@FieldDefine(title = "附件对应字段", number = 55)
	private _AttachmentOnField tf_AttachmentOnField;

	@FieldDefine(title = "附件名称", nameField = true, number = 60)
	@Column(nullable = false, length = 100)
	private String tf_name;// 名称

	@FieldDefine(title = "保管人", number = 70)
	@Column(length = 50)
	private String tf_keeper;// 保管人

	@FieldDefine(title = "档案号", number = 80)
	@Column(length = 50)
	private String tf_archiveNumber;// 档案号

	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;// 描述

	@Column(length = 250)
	@FieldDefine(title = "文件名", number = 100)
	private String tf_filename;// 文件名

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_fileTypeId")
	@FieldDefine(title = "附件文件类型", number = 110)
	private _AttachmentFileType tf_AttachmentFileType;// 附件文件类型

	@FieldDefine(title = "附件文件大小", number = 120)
	private Integer tf_filesize;// 文件大小

	@FieldDefine(title = "附件上传日期", number = 130)
	private Date tf_filelastupdate;// 最后上传日期

	@FieldDefine(title = "文件压缩保存", number = 140)
	private Boolean tf_fileCompressed;

	@FieldDefine(title = "图像压缩模式", number = 150)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reduceModeId")
	private _AttachmentReduceMode tf_AttachmentReduceMode;

	@FieldDefine(title = "图像宽度", number = 160)
	private Integer tf_imgwidth;// 图像宽

	@FieldDefine(title = "图像高度", number = 170)
	private Integer tf_imgheight;// 图像高

	@JsonIgnore
	@Transient
	private Blob tf_imagePreview;// image 的缩略图

	@JsonIgnore
	@Transient
	private Blob tf_filedata;// 文件数据，这里的没有用，放在另外一个类中

	public _Attachment() {

	}

	public Integer getTf_attachmentId() {
	return tf_attachmentId;
	}

	public void setTf_attachmentId(Integer tf_attachmentId) {
	this.tf_attachmentId = tf_attachmentId;
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

	public Integer getTf_order() {
	return tf_order;
	}

	public void setTf_order(Integer tf_order) {
	this.tf_order = tf_order;
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

	public String getTf_filename() {
	return tf_filename;
	}

	public void setTf_filename(String tf_filename) {
	this.tf_filename = tf_filename;
	}


	public Integer getTf_filesize() {
	return tf_filesize;
	}

	public void setTf_filesize(Integer tf_filesize) {
	this.tf_filesize = tf_filesize;
	}

	public Date getTf_filelastupdate() {
	return tf_filelastupdate;
	}

	public void setTf_filelastupdate(Date tf_filelastupdate) {
	this.tf_filelastupdate = tf_filelastupdate;
	}

	public Integer getTf_imgwidth() {
	return tf_imgwidth;
	}

	public void setTf_imgwidth(Integer tf_imgwidth) {
	this.tf_imgwidth = tf_imgwidth;
	}

	public Integer getTf_imgheight() {
	return tf_imgheight;
	}

	public void setTf_imgheight(Integer tf_imgheight) {
	this.tf_imgheight = tf_imgheight;
	}

	public Blob getTf_imagePreview() {
	return tf_imagePreview;
	}

	public String getTf_keeper() {
	return tf_keeper;
	}

	public void setTf_keeper(String tf_keeper) {
	this.tf_keeper = tf_keeper;
	}

	public String getTf_archiveNumber() {
	return tf_archiveNumber;
	}

	public void setTf_archiveNumber(String tf_archiveNumber) {
	this.tf_archiveNumber = tf_archiveNumber;
	}

	public Blob getTf_filedata() {
	return tf_filedata;
	}

	public Boolean getTf_fileCompressed() {
	return tf_fileCompressed == null ? false : tf_fileCompressed;
	}

	public void setTf_fileCompressed(Boolean tf_fileCompressed) {
	this.tf_fileCompressed = tf_fileCompressed;
	}

	public _AttachmentType getTf_AttachmentType() {
		return tf_AttachmentType;
	}

	public void setTf_AttachmentType(_AttachmentType tf_AttachmentType) {
		this.tf_AttachmentType = tf_AttachmentType;
	}

	public _AttachmentOnField getTf_AttachmentOnField() {
		return tf_AttachmentOnField;
	}

	public void setTf_AttachmentOnField(_AttachmentOnField tf_AttachmentOnField) {
		this.tf_AttachmentOnField = tf_AttachmentOnField;
	}

	public _AttachmentFileType getTf_AttachmentFileType() {
		return tf_AttachmentFileType;
	}

	public void setTf_AttachmentFileType(_AttachmentFileType tf_AttachmentFileType) {
		this.tf_AttachmentFileType = tf_AttachmentFileType;
	}

	public _AttachmentReduceMode getTf_AttachmentReduceMode() {
		return tf_AttachmentReduceMode;
	}

	public void setTf_AttachmentReduceMode(_AttachmentReduceMode tf_AttachmentReduceMode) {
		this.tf_AttachmentReduceMode = tf_AttachmentReduceMode;
	}




}
