package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentReduceMode;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@TableDefine(group = "系统设置", id = 9005, title = "运行参数设置")
public class _Systeminfo implements Serializable, _IModuleControlInterface {

	private static final long serialVersionUID = 651774393220912601L;

	@Id
	@Column(insertable = false, updatable = false)
	@FieldDefine(title = "序号", number = 10, hidden = true)
	private Integer tf_systeminfoId;

	@FieldDefine(title = "系统名称", nameField = true, number = 20)
	@Column(length = 50, nullable = false)
	private String tf_systemName;

	@FieldDefine(title = "版本号", number = 30)
	@Column(length = 50, nullable = false)
	private String tf_systemVersion;

	@FieldDefine(title = "附加信息", number = 40)
	@Column(length = 50)
	private String tf_systemAddition;

	@FieldDefine(title = "版权所有", number = 50)
	@Column(length = 50, nullable = false)
	private String tf_copyrightOwner;

	@FieldDefine(title = "版权信息", number = 60)
	@Column(length = 50, nullable = false)
	private String tf_copyrightInfo;

	@FieldDefine(title = "单点登录", number = 100)
	@Column(nullable = false)
	private Boolean tf_allowExternalLogin; // 是否可以外部单点登录

	@FieldDefine(title = "2周内自动登录", number = 110)
	@Column(nullable = false)
	private Boolean tf_allowautoLoginInTwoWeeks; // 是否允许2周内自动登录

	@FieldDefine(title = "更改初始密码", remark = "是否需要在第一次登录的时候修改初始密码", number = 120)
	@Column(nullable = false)
	private Boolean tf_needReplaceInitialPassword; // 是否需要修改初始密码，在第一次登录的时候

	@FieldDefine(title = "需要验证码", number = 130)
	@Column(nullable = false)
	private Boolean tf_needIdentifingCode; // 是否需要验证码

	@FieldDefine(title = "总是需要验证码", remark = "是否总是需要验证码，如果是false,则登录时输错一次密码后才显示验证码", number = 140)
	@Column(nullable = false)
	private Boolean tf_alwaysNeedIdentifingCode; // 是否总是需要验证码，如果是false,登录密码错过一次后才显示

	@JsonIgnore
	@FieldDefine(title = "最大登录用户数", number = 150)
	@Column(nullable = false)
	private Integer tf_maxusers;

	@FieldDefine(title = "超时时间分种", number = 160)
	@Column(nullable = false)
	private Integer tf_sessionTimeoutMinute;

	@FieldDefine(title = "附件最大兆数", number = 170, remark = "在这里设置无用，必须在spring mvc的配置文件中进行设置")
	@Column(nullable = false)
	private Integer tf_additionFileMaxMB;

	@FieldDefine(title = "数据备份文件名", number = 180)
	@Column(length = 50, nullable = false)
	private String tf_backupfilename;

	@FieldDefine(title = "附件图像压缩方式", number = 190, remark = "上传图像附件的压缩方式", fieldGroup = "附件设置")
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_reduceModeId")
	private _AttachmentReduceMode tf_AttachmentReduceMode;

	@FieldDefine(title = "可预览文件类型", number = 210, remark = "附件可以在网页中进行预览的类型，以逗号分隔", fieldGroup = "附件设置")
	private String tf_previewExts;

	@FieldDefine(title = "可转换成pdf文件类型", number = 220, remark = "上传文件后会生成可用于预览的pdf文件的后缀名，以逗号分隔", fieldGroup = "附件设置")
	private String tf_allowToPDF;

	@FieldDefine(title = "附件保存在文件系统中", number = 230, remark = "选中后上传的附件将会保存在文件中，否则保存在数据库里", fieldGroup = "附件设置")
	private Boolean tf_attachmentSaveToFile;

	@FieldDefine(title = "附件保存路径", number = 240, remark = "如果附件保存在文件系统中，需要设置附件保存的路径", fieldGroup = "附件设置")
	private String tf_attachmentSaveDir;

	@FieldDefine(title = "附件数据库名", number = 250, remark = "如果设置了此项，可以将附件保存在不同的数据库中，防止主数据库过于庞大", fieldGroup = "附件设置")
	private String tf_attachmentDataBaseName;

	@FieldDefine(title = "备注", number = 300)
	private String tf_remark;

	public _Systeminfo() {

	}

	public Integer getTf_sessionTimeoutMinute() {
	return tf_sessionTimeoutMinute;
	}

	public Integer sessionTimeoutSecond() {
	return tf_sessionTimeoutMinute > 0 ? tf_sessionTimeoutMinute * 60 : -1;
	}

	public void setTf_sessionTimeoutMinute(Integer tf_sessionTimeoutMinute) {
	this.tf_sessionTimeoutMinute = tf_sessionTimeoutMinute;
	}

	public Integer getTf_systeminfoId() {
	return tf_systeminfoId;
	}

	public void setTf_systeminfoId(Integer tf_systeminfoId) {
	this.tf_systeminfoId = tf_systeminfoId;
	}

	public Boolean getTf_allowExternalLogin() {
	return tf_allowExternalLogin;
	}

	public void setTf_allowExternalLogin(Boolean tf_allowExternalLogin) {
	this.tf_allowExternalLogin = tf_allowExternalLogin;
	}

	public Integer getTf_maxusers() {
	return tf_maxusers;
	}

	public void setTf_maxusers(Integer tf_maxusers) {
	this.tf_maxusers = tf_maxusers;
	}

	public String getTf_systemName() {
	return tf_systemName;
	}

	public void setTf_systemName(String tf_systemName) {
	this.tf_systemName = tf_systemName;
	}

	public String getTf_systemVersion() {
	return tf_systemVersion;
	}

	public void setTf_systemVersion(String tf_systemVersion) {
	this.tf_systemVersion = tf_systemVersion;
	}

	public String getTf_systemAddition() {
	return tf_systemAddition;
	}

	public void setTf_systemAddition(String tf_systemAddition) {
	this.tf_systemAddition = tf_systemAddition;
	}

	public String getTf_copyrightOwner() {
	return tf_copyrightOwner;
	}

	public void setTf_copyrightOwner(String tf_copyrightOwner) {
	this.tf_copyrightOwner = tf_copyrightOwner;
	}

	public String getTf_copyrightInfo() {
	return tf_copyrightInfo;
	}

	public void setTf_copyrightInfo(String tf_copyrightInfo) {
	this.tf_copyrightInfo = tf_copyrightInfo;
	}

	public Boolean getTf_alwaysNeedIdentifingCode() {
	return tf_alwaysNeedIdentifingCode;
	}

	public void setTf_alwaysNeedIdentifingCode(Boolean tf_alwaysNeedIdentifingCode) {
	this.tf_alwaysNeedIdentifingCode = tf_alwaysNeedIdentifingCode;
	}

	public Boolean getTf_allowautoLoginInTwoWeeks() {
	return tf_allowautoLoginInTwoWeeks;
	}

	public void setTf_allowautoLoginInTwoWeeks(Boolean tf_allowautoLoginInTwoWeeks) {
	this.tf_allowautoLoginInTwoWeeks = tf_allowautoLoginInTwoWeeks;
	}

	public Boolean getTf_needReplaceInitialPassword() {
	return tf_needReplaceInitialPassword;
	}

	public void setTf_needReplaceInitialPassword(Boolean tf_needReplaceInitialPassword) {
	this.tf_needReplaceInitialPassword = tf_needReplaceInitialPassword;
	}

	public Boolean getTf_needIdentifingCode() {
	return tf_needIdentifingCode;
	}

	public void setTf_needIdentifingCode(Boolean tf_needIdentifingCode) {
	this.tf_needIdentifingCode = tf_needIdentifingCode;
	}

	public Integer getTf_additionFileMaxMB() {
	return tf_additionFileMaxMB;
	}

	public void setTf_additionFileMaxMB(Integer tf_additionFileMaxMB) {
	this.tf_additionFileMaxMB = tf_additionFileMaxMB;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

	public String getTf_backupfilename() {
	return tf_backupfilename;
	}

	public void setTf_backupfilename(String tf_backupfilename) {
	this.tf_backupfilename = tf_backupfilename;
	}

	public _AttachmentReduceMode getTf_AttachmentReduceMode() {
	return tf_AttachmentReduceMode;
	}

	public void setTf_AttachmentReduceMode(_AttachmentReduceMode tf_AttachmentReduceMode) {
	this.tf_AttachmentReduceMode = tf_AttachmentReduceMode;
	}

	public String getTf_previewExts() {
	return tf_previewExts;
	}

	public void setTf_previewExts(String tf_previewExts) {
	this.tf_previewExts = tf_previewExts;
	}

	public String getTf_allowToPDF() {
	return tf_allowToPDF;
	}

	public void setTf_allowToPDF(String tf_allowToPDF) {
	this.tf_allowToPDF = tf_allowToPDF;
	}

}
