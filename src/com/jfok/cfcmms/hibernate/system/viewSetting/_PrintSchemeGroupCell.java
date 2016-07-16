package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;

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

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9943, title = "记录打印方案分组字段")
public class _PrintSchemeGroupCell implements _IModuleControlInterface , Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_cellId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_printSchemeGroupId")
	@FieldDefine(title = "记录打印方案分组", number = 20)
	private _PrintSchemeGroup tf_PrintSchemeGroup;

	@FieldDefine(title = "顺序号", number = 30)
	private Integer tf_order;

	@FieldDefine(title = "单元格名称", nameField = true, number = 40)
	@Column(length = 50, nullable = false)
	private String tf_name;

	@FieldDefine(title = "打印内容", number = 50)
	private String tf_printText;

	@FieldDefine(title = "行高", number = 60)
	private Integer tf_height;

	@FieldDefine(title = "列宽", number = 70)
	@Column(length = 10)
	private String tf_width;

	@FieldDefine(title = "合并列数", number = 80)
	private Integer tf_colspan;

	@FieldDefine(title = "合并行数", number = 90)
	private Integer tf_rowspan;

	@FieldDefine(title = "左右对齐方式", number = 100)
	private String tf_align;

	@FieldDefine(title = "上下对齐方式", number = 110)
	private String tf_valign;

	@FieldDefine(title = "结束行", number = 120)
	private Boolean tf_isEndrow;

	@FieldDefine(title = "css设置", number = 130)
	@Column(length = 50)
	private String tf_cssStyle;

	@FieldDefine(title = "禁用", number = 140)
	private Boolean tf_disabled;
	
	@FieldDefine(title = "附加设置", number = 190)
	private String tf_otherSetting;

	// 生成excel时，需要知道这个格子的行与列
	@Transient
	private int colnumber;

	@Transient
	private int rownumber;

	public _PrintSchemeGroupCell() {

	}

	// 取得td 的 值

	public String genHtml() {
		String result = "";
		String attr = "";
		if (tf_colspan != null && tf_colspan > 0)
			attr = attr + " colspan=\"" + tf_colspan + "\"";
		if (tf_rowspan != null && tf_rowspan > 0)
			attr = attr + " rowspan=\"" + tf_rowspan + "\"";

	//  这个取消掉了，因为第一个隐藏行里面加入了，每一列的宽度。
		if (tf_width != null && tf_width.length() > 0)
			attr = attr + " width=\"" + tf_width + "\"";
		if (tf_height != null && tf_height > 0)
			attr = attr + " height=\"" + tf_height + "\"";

		if (tf_align != null && tf_align.length() > 0)
			attr = attr + " align=\"" + tf_align + "\"";
		if (tf_valign != null && tf_valign.length() > 0)
			attr = attr + " valign=\"" + tf_valign + "\"";

		if (tf_cssStyle != null && tf_cssStyle.length() > 0)
			attr = attr + " class=\"" + tf_cssStyle + "\"";
		if (tf_otherSetting != null && tf_otherSetting.length() > 0)
			attr = attr + " " + tf_otherSetting;
		result = (containFormula() ? "<td " : "<th ") + attr + ">" + cellText()
				+ (containFormula() ? " </td>" : " </th>");
		return result;

	}

	public Integer getTf_cellId() {
		return tf_cellId;
	}

	public void setTf_cellId(Integer tf_cellId) {
		this.tf_cellId = tf_cellId;
	}

	public _PrintSchemeGroup getTf_PrintSchemeGroup() {
		return tf_PrintSchemeGroup;
	}

	public void setTf_PrintSchemeGroup(_PrintSchemeGroup tf_PrintSchemeGroup) {
		this.tf_PrintSchemeGroup = tf_PrintSchemeGroup;
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

	public String getTf_printText() {
		return tf_printText;
	}

	public void setTf_printText(String tf_printText) {
		this.tf_printText = tf_printText;
	}

	public String cellText() {
		return (tf_printText == null ? tf_name : tf_printText);
	}

	/**
	 * 此单元格是否包含公式
	 * 
	 * @return
	 */
	public boolean containFormula() {
		return cellText().indexOf("{") >= 0;
	}

	public Integer getTf_height() {
		return tf_height;
	}

	public void setTf_height(Integer tf_height) {
		this.tf_height = tf_height;
	}

	public Integer getTf_colspan() {
		return tf_colspan;
	}

	// 取得行数，如果为0,则为1
	public int colspan() {
		return (tf_colspan == null || tf_colspan == 0) ? 1 : tf_colspan;
	}

	public void setTf_colspan(Integer tf_colspan) {
		this.tf_colspan = tf_colspan;
	}

	public Integer getTf_rowspan() {
		return tf_rowspan;
	}

	// 取得列数，如果为0,则为1
	public int rowspan() {
		return (tf_rowspan == null || tf_rowspan == 0) ? 1 : tf_rowspan;
	}

	public void setTf_rowspan(Integer tf_rowspan) {
		this.tf_rowspan = tf_rowspan;
	}

	public String getTf_align() {
		return tf_align;
	}

	public void setTf_align(String tf_align) {
		this.tf_align = tf_align;
	}

	public String getTf_valign() {
		return tf_valign;
	}

	public void setTf_valign(String tf_valign) {
		this.tf_valign = tf_valign;
	}

	public Boolean getTf_isEndrow() {
		return tf_isEndrow == null ? false : tf_isEndrow;
	}

	public void setTf_isEndrow(Boolean tf_isEndrow) {
		this.tf_isEndrow = tf_isEndrow;
	}

	public String getTf_cssStyle() {
		return tf_cssStyle;
	}

	public void setTf_cssStyle(String tf_cssStyle) {
		this.tf_cssStyle = tf_cssStyle;
	}

	public String getTf_otherSetting() {
		return tf_otherSetting;
	}

	public void setTf_otherSetting(String tf_otherSetting) {
		this.tf_otherSetting = tf_otherSetting;
	}

	public String getTf_width() {
		return tf_width;
	}

	public void setTf_width(String tf_width) {
		this.tf_width = tf_width;
	}

	public int getColnumber() {
		return colnumber;
	}

	public void setColnumber(int colnumber) {
		this.colnumber = colnumber;
	}

	public int getRownumber() {
		return rownumber;
	}

	public void setRownumber(int rownumber) {
		this.rownumber = rownumber;
	}

	public Boolean getTf_disabled() {
		return tf_disabled == null ? false : tf_disabled;
	}

	public void setTf_disabled(Boolean tf_disabled) {
		this.tf_disabled = tf_disabled;
	}

}
