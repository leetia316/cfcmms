package com.jfok.cfcmms.hibernate.system.viewSetting;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统模块", id = 9942, title = "记录打印方案分组")
public class _PrintSchemeGroup implements _IModuleControlInterface , Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_printSchemeGroupId;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "tf_printSchemeId")
	@FieldDefine(title = "记录打印方案", number = 20)
	private _PrintScheme tf_PrintScheme;

	@FieldDefine(title = "分组顺序号", number = 30)
	private Integer tf_printGroupOrder;

	@FieldDefine(title = "分组名称", nameField = true, number = 40)
	@Column(length = 50)
	private String tf_printGroupName;

	@FieldDefine(title = "本组宽度", number = 50)
	private Integer tf_groupWidth;

	@FieldDefine(title = "列数", number = 60)
	private Integer tf_numCols;

	@FieldDefine(title = "列宽", number = 70, remark = "以逗号分隔各个列宽")
	@Column(length = 80)
	private String tf_widths;

	@FieldDefine(title = "边线宽度", number = 80)
	private Integer tf_border;

	@FieldDefine(title = "cellpadding", number = 90)
	private Integer tf_cellpadding;

	@FieldDefine(title = "css设置", number = 100)
	@Column(length = 50)
	private String tf_cssStyle;

	@FieldDefine(title = "附加设置", number = 190)
	private String tf_otherSetting;

	// @OneToMany( fetch = FetchType.LAZY)
	// @JoinColumn(name = "tf_printSchemeGroupId")
	// private Collection<_PrintSchemeGroupCell> groupCells;

	public _PrintSchemeGroup() {

	}

	// 根据分组定义，来确定每个格子的行与列,,返回一共有多少行
	public int genCellRowAndCol(List<_PrintSchemeGroupCell> cells, int firstrow) {
		// 一张表格的二维数组，已经用到过的格子填充为1

		int coord[][] = new int[100][tf_numCols];
		for (int i = 0; i < coord.length; i++)
			for (int j = 0; j < tf_numCols; j++)
				coord[i][j] = 0;

		int nowRow = 0; // 当前行
		int nowCol = 0; // 当前列
		int cellCount = 0;
		for (_PrintSchemeGroupCell cell : cells) {
			
			// 如果当前列加了以后大于总数了，那么就要到下一行去，要判断下一行的第一个，是不是被占用了
			//			if (nowCol + cell.colspan() > tf_numCols) {
			//				nowRow += 1;
			//				nowCol = getFirstEmptyCol(coord[nowRow]);
			//			}
			cellCount ++;
			cell.setColnumber(nowCol);
			cell.setRownumber(nowRow);
			updateCoord(coord, cell , cellCount);

			nowCol = getFirstEmptyCol(coord[nowRow]);
			if (nowCol == -1 || cell.getTf_isEndrow()){
				nowRow += 1;
				nowCol = getFirstEmptyCol(coord[nowRow]);
			}

		}
		
		return nowRow;
	}

	// 把某一个格子的使用情况更新一坐标系中
	public void updateCoord(int coord[][], _PrintSchemeGroupCell cell , int cellCount) {
		for (int i = 0; i < cell.rowspan(); i++)
			for (int j = 0; j < cell.colspan(); j++)
				coord[cell.getRownumber() + i][cell.getColnumber() + j] = cellCount;
	}

	// 找到某一行里面第一个可以用的格子
	public int getFirstEmptyCol(int coord[]) {
		for (int i = 0; i < coord.length; i++)
			if (coord[i] == 0)
				return i;
		return -1;
	}

	public String genHtml(_PrintSchemeGroup group ,List<_PrintSchemeGroupCell> cells) {
		String result = "";
		String attr = " ";

		if (tf_groupWidth != null && tf_groupWidth > 0)
			attr = attr + " width=\"" + tf_groupWidth + "\"";
		if (tf_border != null && tf_border > 0)
			attr = attr + " border=\"" + tf_border + "\"";
		if (tf_cellpadding != null && tf_cellpadding > 0)
			attr = attr + " cellpadding=\"" + tf_cellpadding + "\"";
		if (tf_cssStyle != null && tf_cssStyle.length() > 0)
			attr = attr + " class=\"" + tf_cssStyle + "\"";
		if (tf_otherSetting != null && tf_otherSetting.length() > 0)
			attr = attr + " " + tf_otherSetting;
		result = "<printtable><table " + attr + ">";
		
		//加入宽度，第一行是一个0行高的宽度
		String tr = "";
		String starttr = "<tr>";
		String endtr = "</tr>";
		
//		String widths[] = group.getTf_widths().split(",");
//		tr = "<tr class=\"trhidden\">" ;
//		for (int i = 0; i < widths.length; i++){
//			tr += "<td width=\"" + widths[i] + "\"> </td>";
//		}
//		tr+= endtr;
		//result += tr;
		
		int nowCol = 0;
		tr = "";

		for (_PrintSchemeGroupCell cell : cells) {
			if (nowCol + cell.colspan() > tf_numCols) {
				result = result + starttr + tr + endtr;
				tr = "";
				nowCol = 0;
			}
			nowCol += cell.colspan();
			tr += cell.genHtml();

			if (cell.getTf_isEndrow()) {
				result = result + starttr + tr + endtr;
				tr = "";
				nowCol = 0;
			}

		}
		if (tr.length() > 0)
			result = result + starttr + tr + endtr;
		result += " </table></printtable>";

		return result;

	}

	public Integer getTf_printSchemeGroupId() {
		return tf_printSchemeGroupId;
	}

	public void setTf_printSchemeGroupId(Integer tf_printSchemeGroupId) {
		this.tf_printSchemeGroupId = tf_printSchemeGroupId;
	}

	public _PrintScheme getTf_PrintScheme() {
		return tf_PrintScheme;
	}

	public void setTf_PrintScheme(_PrintScheme tf_PrintScheme) {
		this.tf_PrintScheme = tf_PrintScheme;
	}

	public Integer getTf_printGroupOrder() {
		return tf_printGroupOrder;
	}

	public void setTf_printGroupOrder(Integer tf_printGroupOrder) {
		this.tf_printGroupOrder = tf_printGroupOrder;
	}

	public String getTf_printGroupName() {
		return tf_printGroupName;
	}

	public void setTf_printGroupName(String tf_printGroupName) {
		this.tf_printGroupName = tf_printGroupName;
	}

	public Integer getTf_groupWidth() {
		return tf_groupWidth;
	}

	public void setTf_groupWidth(Integer tf_groupWidth) {
		this.tf_groupWidth = tf_groupWidth;
	}

	public Integer getTf_numCols() {
		return tf_numCols;
	}

	public void setTf_numCols(Integer tf_numCols) {
		this.tf_numCols = tf_numCols;
	}

	public String getTf_widths() {
		return tf_widths;
	}

	public void setTf_widths(String tf_widths) {
		this.tf_widths = tf_widths;
	}

	public Integer getTf_border() {
		return tf_border == null ? 0 : tf_border ;
	}

	public void setTf_border(Integer tf_border) {
		this.tf_border = tf_border;
	}

	public Integer getTf_cellpadding() {
		return tf_cellpadding;
	}

	public void setTf_cellpadding(Integer tf_cellpadding) {
		this.tf_cellpadding = tf_cellpadding;
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

}
