package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;
import com.jfok.cfcmms.util.annotation.FieldDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

public class _Popedom implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "序号")
	private Integer tf_popedomId;

	private String tf_roleId;

	private String tf_moduleId;

	private Integer tf_allowBrowse;
	private Integer tf_allowInsert;
	private Integer tf_allowEdit;
	private Integer tf_allowDelete;
	private Integer tf_allowExec;
	private Integer tf_allowAuditing;
	private Integer tf_allowApprove;
	private Integer tf_allowEditDirect;
	private Integer tf_allowPayment;

	private Integer tf_attachmentBrowse;
	private Integer tf_attachmentInsert;
	private Integer tf_attachmentEdit;
	private Integer tf_attachmentDelete;

	public _Popedom() {

	}

	public Integer getTf_popedomId() {
	return tf_popedomId;
	}

	public void setTf_popedomId(Integer tf_popedomId) {
	this.tf_popedomId = tf_popedomId;
	}

	public String getTf_roleId() {
	return tf_roleId;
	}

	public void setTf_roleId(String tf_roleId) {
	this.tf_roleId = tf_roleId;
	}

	public String getTf_moduleId() {
	return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
	this.tf_moduleId = tf_moduleId;
	}

	public Integer getTf_allowBrowse() {
	return tf_allowBrowse;
	}

	public void setTf_allowBrowse(Integer tf_allowBrowse) {
	this.tf_allowBrowse = tf_allowBrowse;
	}

	public Integer getTf_allowInsert() {
	return tf_allowInsert;
	}

	public void setTf_allowInsert(Integer tf_allowInsert) {
	this.tf_allowInsert = tf_allowInsert;
	}

	public Integer getTf_allowEdit() {
	return tf_allowEdit;
	}

	public void setTf_allowEdit(Integer tf_allowEdit) {
	this.tf_allowEdit = tf_allowEdit;
	}

	public Integer getTf_allowDelete() {
	return tf_allowDelete;
	}

	public void setTf_allowDelete(Integer tf_allowDelete) {
	this.tf_allowDelete = tf_allowDelete;
	}

	public Integer getTf_allowExec() {
	return tf_allowExec;
	}

	public void setTf_allowExec(Integer tf_allowExec) {
	this.tf_allowExec = tf_allowExec;
	}

	public Integer getTf_allowAuditing() {
	return tf_allowAuditing;
	}

	public void setTf_allowAuditing(Integer tf_allowAuditing) {
	this.tf_allowAuditing = tf_allowAuditing;
	}

	public Integer getTf_allowApprove() {
	return tf_allowApprove;
	}

	public void setTf_allowApprove(Integer tf_allowApprove) {
	this.tf_allowApprove = tf_allowApprove;
	}

	public Integer getTf_allowPayment() {
	return tf_allowPayment;
	}

	public void setTf_allowPayment(Integer tf_allowPayment) {
	this.tf_allowPayment = tf_allowPayment;
	}

	public Integer getTf_attachmentBrowse() {
	return tf_attachmentBrowse;
	}

	public void setTf_attachmentBrowse(Integer tf_attachmentBrowse) {
	this.tf_attachmentBrowse = tf_attachmentBrowse;
	}

	public Integer getTf_attachmentInsert() {
	return tf_attachmentInsert;
	}

	public void setTf_attachmentInsert(Integer tf_attachmentInsert) {
	this.tf_attachmentInsert = tf_attachmentInsert;
	}

	public Integer getTf_attachmentEdit() {
	return tf_attachmentEdit;
	}

	public void setTf_attachmentEdit(Integer tf_attachmentEdit) {
	this.tf_attachmentEdit = tf_attachmentEdit;
	}

	public Integer getTf_attachmentDelete() {
	return tf_attachmentDelete;
	}

	public void setTf_attachmentDelete(Integer tf_attachmentDelete) {
	this.tf_attachmentDelete = tf_attachmentDelete;
	}

	public Integer getTf_allowEditDirect() {
	return tf_allowEditDirect;
	}

	public void setTf_allowEditDirect(Integer tf_allowEditDirect) {
	this.tf_allowEditDirect = tf_allowEditDirect;
	}

}
