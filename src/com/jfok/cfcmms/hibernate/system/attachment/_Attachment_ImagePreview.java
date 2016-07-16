package com.jfok.cfcmms.hibernate.system.attachment;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




import com.jfok.cfcmms.util.annotation.FieldDefine;

/**
 * 读取图像的缩略图时候的类，只读取图像缩略图
 * 
 * @author jiangfeng
 * 
 */
@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@Table(name = "_Attachment")
public class _Attachment_ImagePreview implements Serializable {

	@Id
	@Column(updatable = false)
	private Integer tf_attachmentId; // 附件序号

	@Column(updatable = false)
	private String tf_filename;// 文件名

	private Blob tf_imagePreview;// image 的缩略图

	@FieldDefine(title = "图像宽度")
	@Column(updatable = false)
	private Integer tf_imgwidth;// 图像宽

	@FieldDefine(title = "图像高度")
	@Column(updatable = false)
	private Integer tf_imgheight;// 图像高

	public _Attachment_ImagePreview() {

	}

	public Integer getTf_attachmentId() {
	return tf_attachmentId;
	}

	public void setTf_attachmentId(Integer tf_attachmentId) {
	this.tf_attachmentId = tf_attachmentId;
	}

	public Blob getTf_imagePreview() {
	return tf_imagePreview;
	}

	public void setTf_imagePreview(Blob tf_imagePreview) {
	this.tf_imagePreview = tf_imagePreview;
	}

	public String getTf_filename() {
	return tf_filename;
	}

	public void setTf_filename(String tf_filename) {
	this.tf_filename = tf_filename;
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

}
