package com.jfok.cfcmms.share;

import java.io.Serializable;
import java.util.List;

import com.jfok.cfcmms.hibernate.system.authority._Popedom;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;



/**
 * 用于将角色权限去显示与修改的class
 * 
 * @author jfok
 * 
 */

public class RolePopedom implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tf_moduleId;
	private String tf_title;
	private Boolean tf_allowBrowse = null; // true选中，false没，null无此选项
	private Boolean tf_allowInsert = null;
	private Boolean tf_allowEdit = null;
	private Boolean tf_allowDelete = null;
	private Boolean tf_allowExec = null;
	private Boolean tf_allowAuditing = null;
	private Boolean tf_allowApprove = null;
	private Boolean tf_allowEditDirect = null;
	private Boolean tf_allowPayment = null;

	private Boolean tf_attachmentBrowse;
	private Boolean tf_attachmentInsert;
	private Boolean tf_attachmentEdit;
	private Boolean tf_attachmentDelete;

	private List<RoleAdditionFunction> additionFunctions; // 本模块的所有可以附加的功能

	// private Boolean tf_allowApproveedit = null;

	public RolePopedom() {
	}

	public RolePopedom(_Module module) {
		super();
		tf_moduleId = module.getTf_moduleId();
		tf_title = module.getTf_title();
		tf_allowBrowse = module.getTf_hasBrowse() ? false : null;
		tf_allowInsert = module.getTf_hasInsert() ? false : null;
		tf_allowEdit = module.getTf_hasEdit() ? false : null;
		tf_allowDelete = module.getTf_hasDelete() ? false : null;
		tf_allowExec = module.getTf_hasExec() ? false : null;
		tf_allowAuditing = module.getTf_hasAuditing() ? false : null;
		tf_allowApprove = module.getTf_hasApprove() ? false : null;
		tf_allowEditDirect = module.getTf_hasApprove() || module.getTf_hasAuditing() ? false : null;
		tf_allowPayment = module.getTf_hasPayment() ? false : null;

		tf_attachmentBrowse = module.getTf_hasAttachment() ? false : null;
		tf_attachmentInsert = module.getTf_hasAttachment() ? false : null;
		tf_attachmentEdit = module.getTf_hasAttachment() ? false : null;
		tf_attachmentDelete = module.getTf_hasAttachment() ? false : null;

	}

	public void SetSavedPopedom(_Popedom popedom) {
		if (popedom != null) {
			tf_allowBrowse = tf_allowBrowse != null ? popedom.getTf_allowBrowse() == 1 : null;
			tf_allowInsert = tf_allowInsert != null ? popedom.getTf_allowInsert() == 1 : null;
			tf_allowEdit = tf_allowEdit != null ? popedom.getTf_allowEdit() == 1 : null;
			tf_allowDelete = tf_allowDelete != null ? popedom.getTf_allowDelete() == 1 : null;
			tf_allowExec = tf_allowExec != null ? popedom.getTf_allowExec() == 1 : null;
			tf_allowAuditing = tf_allowAuditing != null ? popedom.getTf_allowAuditing() == 1 : null;
			tf_allowApprove = tf_allowApprove != null ? popedom.getTf_allowApprove() == 1 : null;
			tf_allowEditDirect = tf_allowEditDirect != null ? popedom.getTf_allowEditDirect() == 1 : null;
			tf_allowPayment = tf_allowPayment != null ? popedom.getTf_allowPayment() == 1 : null;

			tf_attachmentBrowse = tf_attachmentBrowse != null ? popedom.getTf_attachmentBrowse() == 1 : null;
			tf_attachmentInsert = tf_attachmentInsert != null ? popedom.getTf_attachmentInsert() == 1 : null;
			tf_attachmentEdit = tf_attachmentEdit != null ? popedom.getTf_attachmentEdit() == 1 : null;
			tf_attachmentDelete = tf_attachmentDelete != null ? popedom.getTf_attachmentDelete() == 1 : null;

		}
	}

	public void SetSavedPopedom(_UserRoleDetail popedom) {
		if (popedom != null) {
			tf_allowBrowse = tf_allowBrowse != null ? popedom.getTf_allowBrowse() >= 1 : null;
			tf_allowInsert = tf_allowInsert != null ? popedom.getTf_allowInsert() >= 1 : null;
			tf_allowEdit = tf_allowEdit != null ? popedom.getTf_allowEdit() >= 1 : null;
			tf_allowDelete = tf_allowDelete != null ? popedom.getTf_allowDelete() >= 1 : null;
			tf_allowExec = tf_allowExec != null ? popedom.getTf_allowExec() >= 1 : null;
			tf_allowAuditing = tf_allowAuditing != null ? popedom.getTf_allowAuditing() >= 1 : null;
			tf_allowApprove = tf_allowApprove != null ? popedom.getTf_allowApprove() >= 1 : null;
			tf_allowEditDirect = tf_allowEditDirect != null ? popedom.getTf_allowEditDirect() >= 1 : null;
			tf_allowPayment = tf_allowPayment != null ? popedom.getTf_allowPayment() >= 1 : null;
			tf_attachmentBrowse = tf_attachmentBrowse != null ? popedom.getTf_attachmentBrowse() >= 1 : null;
			tf_attachmentInsert = tf_attachmentInsert != null ? popedom.getTf_attachmentInsert() >= 1 : null;
			tf_attachmentEdit = tf_attachmentEdit != null ? popedom.getTf_attachmentEdit() >= 1 : null;
			tf_attachmentDelete = tf_attachmentDelete != null ? popedom.getTf_attachmentDelete() >= 1 : null;
		}
	}
	
	
	@Override
	public String toString() {
		return "RolePopedom [tf_moduleId=" + tf_moduleId + ", tf_title=" + tf_title
				+ ", tf_allowBrowse=" + tf_allowBrowse + ", tf_allowInsert=" + tf_allowInsert
				+ ", tf_allowEdit=" + tf_allowEdit + ", tf_allowDelete=" + tf_allowDelete
				+ ", tf_allowExec=" + tf_allowExec + ", tf_allowApprove=" + tf_allowApprove + "]";
	}

	public String getTf_moduleId() {
		return tf_moduleId;
	}

	public void setTf_moduleId(String tf_moduleId) {
		this.tf_moduleId = tf_moduleId;
	}

	public String getTf_title() {
		return tf_title;
	}

	public void setTf_title(String tf_title) {
		this.tf_title = tf_title;
	}

	public Boolean getTf_allowBrowse() {
		return tf_allowBrowse;
	}

	public void setTf_allowBrowse(Boolean tf_allowBrowse) {
		this.tf_allowBrowse = tf_allowBrowse;
	}

	public Boolean getTf_allowInsert() {
		return tf_allowInsert;
	}

	public void setTf_allowInsert(Boolean tf_allowInsert) {
		this.tf_allowInsert = tf_allowInsert;
	}

	public Boolean getTf_allowEdit() {
		return tf_allowEdit;
	}

	public void setTf_allowEdit(Boolean tf_allowEdit) {
		this.tf_allowEdit = tf_allowEdit;
	}

	public Boolean getTf_allowDelete() {
		return tf_allowDelete;
	}

	public void setTf_allowDelete(Boolean tf_allowDelete) {
		this.tf_allowDelete = tf_allowDelete;
	}

	public Boolean getTf_allowExec() {
		return tf_allowExec;
	}

	public void setTf_allowExec(Boolean tf_allowExec) {
		this.tf_allowExec = tf_allowExec;
	}

	public Boolean getTf_allowApprove() {
		return tf_allowApprove;
	}

	public void setTf_allowApprove(Boolean tf_allowApprove) {
		this.tf_allowApprove = tf_allowApprove;
	}

	public Boolean getTf_allowPayment() {
		return tf_allowPayment;
	}

	public void setTf_allowPayment(Boolean tf_allowPayment) {
		this.tf_allowPayment = tf_allowPayment;
	}

	public Boolean getTf_allowAuditing() {
		return tf_allowAuditing;
	}

	public void setTf_allowAuditing(Boolean tf_allowAuditing) {
		this.tf_allowAuditing = tf_allowAuditing;
	}

	public Boolean getTf_attachmentBrowse() {
		return tf_attachmentBrowse;
	}

	public void setTf_attachmentBrowse(Boolean tf_attachmentBrowse) {
		this.tf_attachmentBrowse = tf_attachmentBrowse;
	}

	public Boolean getTf_attachmentInsert() {
		return tf_attachmentInsert;
	}

	public void setTf_attachmentInsert(Boolean tf_attachmentInsert) {
		this.tf_attachmentInsert = tf_attachmentInsert;
	}

	public Boolean getTf_attachmentEdit() {
		return tf_attachmentEdit;
	}

	public void setTf_attachmentEdit(Boolean tf_attachmentEdit) {
		this.tf_attachmentEdit = tf_attachmentEdit;
	}

	public Boolean getTf_attachmentDelete() {
		return tf_attachmentDelete;
	}

	public void setTf_attachmentDelete(Boolean tf_attachmentDelete) {
		this.tf_attachmentDelete = tf_attachmentDelete;
	}

	public List<RoleAdditionFunction> getAdditionFunctions() {
		return additionFunctions;
	}

	public void setAdditionFunctions(List<RoleAdditionFunction> additionFunctions) {
		this.additionFunctions = additionFunctions;
	}

	public Boolean getTf_allowEditDirect() {
		return tf_allowEditDirect;
	}

	public void setTf_allowEditDirect(Boolean tf_allowEditDirect) {
		this.tf_allowEditDirect = tf_allowEditDirect;
	}

	// public Boolean getTf_allowApproveedit() {
	// return tf_allowApproveedit;
	// }
	//
	// public void setTf_allowApproveedit(Boolean tf_allowApproveedit) {
	// this.tf_allowApproveedit = tf_allowApproveedit;
	// }

}
