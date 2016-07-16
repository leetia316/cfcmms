package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9030, title = "职务")
public class _Position implements Serializable {

	private static final long serialVersionUID = -5683575494747758863L;

	@Id
	@FieldDefine(title = "编号", number = 10)
	@Column(length = 10, nullable = false, unique = true)
	private String tf_positionId;

	@FieldDefine(title = "职务名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false, unique = true)
	private String tf_positionName;

	@FieldDefine(title = "主要职能", number = 30)
	@Column(length = 100)
	private String tf_effect;

	@FieldDefine(title = "附加设置", number = 40)
	private String tf_preview;

	@FieldDefine(title = "备注", number = 50)
	private String tf_remark;

	public _Position() {

	}

	@Override
	public String toString() {
	return "Position [tf_positionId=" + tf_positionId + ", tf_positionName=" + tf_positionName
		+ ", tf_effect=" + tf_effect + ", tf_preview=" + tf_preview + ", tf_remark=" + tf_remark
		+ "]";
	}

	public String getTf_positionId() {
	return tf_positionId;
	}

	public void setTf_positionId(String tf_positionId) {
	this.tf_positionId = tf_positionId;
	}

	public String getTf_positionName() {
	return tf_positionName;
	}

	public void setTf_positionName(String tf_positionName) {
	this.tf_positionName = tf_positionName;
	}

	public String getTf_effect() {
	return tf_effect;
	}

	public void setTf_effect(String tf_effect) {
	this.tf_effect = tf_effect;
	}

	public String getTf_preview() {
	return tf_preview;
	}

	public void setTf_preview(String tf_preview) {
	this.tf_preview = tf_preview;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

}
