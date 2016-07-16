package com.jfok.cfcmms.hibernate.system.authority;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;




@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@Table(name = "Role_operdetail")
public class _UserRoleDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tf_userId;
	@Id
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

	@Transient
	private Integer tf_approveOrder; // 审批级数的序号

	@Transient
	private Integer tf_approveLevel; // 审批级数的级次，可以同相同的级次，并列审批

	@Transient
	List<_UserRoleAddition> userRoleAdditions;

	public _UserRoleDetail() {

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

	public Integer getTf_allowApprove() {
		return tf_allowApprove == null ? 0 : tf_allowApprove;
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

	public Integer getTf_allowAuditing() {
		return tf_allowAuditing == null ? 0 : tf_allowAuditing;
	}

	public void setTf_allowAuditing(Integer tf_allowAuditing) {
		this.tf_allowAuditing = tf_allowAuditing;
	}

	public Integer getTf_userId() {
		return tf_userId;
	}

	public void setTf_userId(Integer tf_userId) {
		this.tf_userId = tf_userId;
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

	public List<_UserRoleAddition> getUserRoleAdditions() {
		return userRoleAdditions;
	}

	public void setUserRoleAdditions(List<_UserRoleAddition> userRoleAdditions) {
		this.userRoleAdditions = userRoleAdditions;
	}

	public Integer getTf_approveOrder() {
		return tf_approveOrder == null ? 0 : tf_approveOrder;
	}

	public void setTf_approveOrder(Integer tf_approveOrder) {
		this.tf_approveOrder = tf_approveOrder;
	}

	public Integer getTf_approveLevel() {
		return tf_approveLevel;
	}

	public void setTf_approveLevel(Integer tf_approveLevel) {
		this.tf_approveLevel = tf_approveLevel;
	}

	public Integer getTf_allowEditDirect() {
		return tf_allowEditDirect;
	}

	public void setTf_allowEditDirect(Integer tf_allowEditDirect) {
		this.tf_allowEditDirect = tf_allowEditDirect;
	}

}
