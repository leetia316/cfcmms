package com.jfok.cfcmms.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jfok.cfcmms.hibernate.system.attachment._Attachment;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentFileType;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentOnField;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentType;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentReduceMode;


public class FileUploadBean extends _Attachment {

	private static final long serialVersionUID = 7647897778265424030L;
	private CommonsMultipartFile file;

	private String _t9502___tf_typeId;
	private String _t9503___tf_fileTypeId;
	private String _t9506___tf_fieldId;
	private String _t9504___tf_reduceModeId;

	public FileUploadBean() {
		super();

	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public String get_t9502___tf_typeId() {
		return _t9502___tf_typeId;
	}

	public void set_t9502___tf_typeId(String _t9502___tf_typeId) {
		this._t9502___tf_typeId = _t9502___tf_typeId;
		if (this._t9502___tf_typeId != null && this._t9502___tf_typeId.length() > 0) {
			_AttachmentType type = new _AttachmentType();
			type.setTf_typeId(this._t9502___tf_typeId);
			setTf_AttachmentType(type);
		}
	}

	public String get_t9503___tf_fileTypeId() {
		return _t9503___tf_fileTypeId;
	}

	public void set_t9503___tf_fileTypeId(String _t9503___tf_fileTypeId) {
		this._t9503___tf_fileTypeId = _t9503___tf_fileTypeId;
		if (this._t9503___tf_fileTypeId != null
				&& this._t9503___tf_fileTypeId.length() > 0) {
			_AttachmentFileType type = new _AttachmentFileType();
			type.setTf_fileTypeId(Integer.parseInt(this._t9503___tf_fileTypeId));
			setTf_AttachmentFileType(type);
		}
	}

	public String get_t9504___tf_reduceModeId() {
		return _t9504___tf_reduceModeId;
	}

	public void set_t9504___tf_reduceModeId(String _t9504___tf_reduceModeId) {
		this._t9504___tf_reduceModeId = _t9504___tf_reduceModeId;
		if (this._t9504___tf_reduceModeId != null && this._t9504___tf_reduceModeId.length() > 0) {
			_AttachmentReduceMode mode = new _AttachmentReduceMode();
			mode.setTf_reduceModeId(Integer.parseInt(this._t9504___tf_reduceModeId));
			setTf_AttachmentReduceMode(mode);
		}
	}
	

	public String get_t9506___tf_fieldId() {
		return _t9506___tf_fieldId;
	}

	public void set_t9506___tf_fieldId(String _t9506___tf_fieldId) {
		this._t9506___tf_fieldId = _t9506___tf_fieldId;

		if (this._t9506___tf_fieldId != null && this._t9506___tf_fieldId.length() > 0) {
			_AttachmentOnField field = new _AttachmentOnField();
			field.setTf_fieldId(Integer.parseInt(this._t9506___tf_fieldId));
			setTf_AttachmentOnField(field);
		}

	}	
	
}
