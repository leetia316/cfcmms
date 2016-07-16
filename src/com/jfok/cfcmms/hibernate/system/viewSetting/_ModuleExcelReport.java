package com.jfok.cfcmms.hibernate.system.viewSetting;

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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.hibernate.system.module._Module;

/**
 * 用来存放模块的用于导出的excel的模板，导入后，可以根据选择的条件和导航值来生成报表
 * 
 * @author jiangfeng
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9935, title = "模块Excel报表")
public class _ModuleExcelReport extends _InputInfoAbstract
	implements Serializable, _IModuleControlInterface {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_moduleId", nullable = false)
	@FieldDefine(title = "所属模块", number = 20)
	private _Module tf_Module;

	@FieldDefine(title = "顺序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(title = "报表名称", number = 40, nameField = true)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "报表类型", number = 50)
	@Column(length = 50)
	private String tf_type;

	@FieldDefine(title = "可用", number = 60)
	private Boolean tf_isEnable;

	@FieldDefine(title = "选择年度", number = 70)
	private Boolean tf_isSelectYear;

	@FieldDefine(title = "选择季度", number = 80)
	private Boolean tf_isSelectSeason;

	@FieldDefine(title = "选择月份", number = 90)
	private Boolean tf_isSelectMonth;

	@FieldDefine(title = "选择记录", number = 100)
	private Boolean tf_isSelectRecord;

	@FieldDefine(title = "文件名", number = 180)
	@Column(length = 99)
	private String tf_filename;

	@FieldDefine(title = "文件大小", number = 190)
	private Integer tf_filesize;

	@FieldDefine(title = "文件上传者", number = 200)
	@Column(length = 10)
	private String tf_author;

	@FieldDefine(title = "上传时间", number = 210)
	private Date tf_uploadDate;

	@FieldDefine(title = "其他设置", number = 220)
	private String tf_otherSetting;

	@FieldDefine(title = "备注", number = 800)
	private String tf_remark;

	@JsonIgnore
	private Blob tf_filedata;// 文件数据

	public _ModuleExcelReport() {

	}

	public Integer getTf_id() {
	return tf_id;
	}

	public void setTf_id(Integer tf_id) {
	this.tf_id = tf_id;
	}

	public _Module getTf_Module() {
	return tf_Module;
	}

	public void setTf_Module(_Module tf_Module) {
	this.tf_Module = tf_Module;
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

	public String getTf_type() {
	return tf_type;
	}

	public void setTf_type(String tf_type) {
	this.tf_type = tf_type;
	}

	public Boolean getTf_isEnable() {
	return tf_isEnable;
	}

	public void setTf_isEnable(Boolean tf_isEnable) {
	this.tf_isEnable = tf_isEnable;
	}

	public Boolean getTf_isSelectYear() {
	return tf_isSelectYear;
	}

	public void setTf_isSelectYear(Boolean tf_isSelectYear) {
	this.tf_isSelectYear = tf_isSelectYear;
	}

	public Boolean getTf_isSelectSeason() {
	return tf_isSelectSeason;
	}

	public void setTf_isSelectSeason(Boolean tf_isSelectSeason) {
	this.tf_isSelectSeason = tf_isSelectSeason;
	}

	public Boolean getTf_isSelectMonth() {
	return tf_isSelectMonth;
	}

	public void setTf_isSelectMonth(Boolean tf_isSelectMonth) {
	this.tf_isSelectMonth = tf_isSelectMonth;
	}

	public Boolean getTf_isSelectRecord() {
	return tf_isSelectRecord;
	}

	public void setTf_isSelectRecord(Boolean tf_isSelectRecord) {
	this.tf_isSelectRecord = tf_isSelectRecord;
	}

	public String getTf_author() {
	return tf_author;
	}

	public void setTf_author(String tf_author) {
	this.tf_author = tf_author;
	}

	public Date getTf_uploadDate() {
	return tf_uploadDate;
	}

	public void setTf_uploadDate(Date tf_uploadDate) {
	this.tf_uploadDate = tf_uploadDate;
	}

	public Blob getTf_filedata() {
	return tf_filedata;
	}

	public void setTf_filedata(Blob tf_filedata) {
	this.tf_filedata = tf_filedata;
	}

	public String getTf_otherSetting() {
	return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
	this.tf_otherSetting = tf_otherSetting;
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

}
