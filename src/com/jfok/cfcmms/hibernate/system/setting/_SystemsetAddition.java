package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.codehaus.jackson.map.annotate.JsonSerialize;



import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)


@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@TableDefine(group = "系统设置", id = 9001, title = "附加参数设置", remark = "系统中的其他参数和业务系统中的参数的设置")
public class _SystemsetAddition implements Serializable, _IModuleControlInterface {

	@Id
	@Column(insertable = false, updatable = false, nullable = false)
	@FieldDefine(title = "序号", number = 10, hidden = true)
	private Integer tf_systemsetAdditionId;

	@FieldDefine(number = 20, title = "审批后才能继续")
	private Boolean tf_continueWithAudit;

	@FieldDefine(title = "忘记密码提示", number = 100)
	private String tf_userforgetPassword;

	@FieldDefine(title = "备份路径", remark = "系统自动备份的数据路径，可以设置多个用;号隔开", number = 110)
	@Column(length = 250)
	private String tf_backupfiledirs; // 备份文件的保存路径 ，可以有多个

	public _SystemsetAddition() {

	}

	public Integer getTf_systemsetAdditionId() {
	return tf_systemsetAdditionId;
	}

	public void setTf_systemsetAdditionId(Integer tf_systemsetAdditionId) {
	this.tf_systemsetAdditionId = tf_systemsetAdditionId;
	}

	public String getTf_userforgetPassword() {
	return tf_userforgetPassword;
	}

	public void setTf_userforgetPassword(String tf_userforgetPassword) {
	this.tf_userforgetPassword = tf_userforgetPassword;
	}

	public String getTf_backupfiledirs() {
	return tf_backupfiledirs;
	}

	public void setTf_backupfiledirs(String tf_backupfiledirs) {
	this.tf_backupfiledirs = tf_backupfiledirs;
	}

	public Boolean getTf_continueWithAudit() {
	return tf_continueWithAudit == null ? false : tf_continueWithAudit;
	}

	public void setTf_continueWithAudit(Boolean tf_continueWithAudit) {
	this.tf_continueWithAudit = tf_continueWithAudit;
	}

	// 取得第一个备份路径
	public String firstBackupFileDir() {
	if (tf_backupfiledirs == null || tf_backupfiledirs.length() == 0)
		return "c:\\backup\\";
	else {
		String dir = tf_backupfiledirs.split(";")[0];
		if (!dir.endsWith("\\"))
		dir = dir + "\\";
		return dir;
	}

	}

	// 取得余下的所有的备份路径
	public String[] allBackupFileDirs() {
	if (tf_backupfiledirs == null || tf_backupfiledirs.length() == 0)
		return null;
	else {
		String all[] = tf_backupfiledirs.split(";");
		if (all.length <= 1)
		return null;
		String result[] = new String[all.length - 1];
		for (int i = 0; i < result.length; i++) {
		result[i] = all[i + 1] + (all[i + 1].endsWith("\\") ? "" : "\\");
		}
		return result;
	}
	}
}
