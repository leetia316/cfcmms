package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)


@TableDefine(id = 8092, title = "日期数值分组明细", group = "编码设置")
public class _NumberGroupDetail implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号", number = 10, hidden = true)
	private Integer tf_numberGroupDetailId;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "tf_numberGroupId", nullable = false, updatable = false)
	@FieldDefine(title = "数值分组", number = 20)
	private _NumberGroup tf_NumberGroup;

	@FieldDefine(title = "序号", number = 30)
	@Column(nullable = false)
	private Integer tf_order;

	@FieldDefine(nameField = true, title = "条件描述", number = 40)
	@Column(nullable = false, length = 50)
	private String tf_name;

	@FieldDefine(title = "字段条件A", number = 50, remark = "thisfield 表示此字段")
	@Column(length = 50)
	private String tf_condition1;

	@FieldDefine(title = "字段条件B", number = 60, remark = "thisfield 表示此字段")
	@Column(length = 50)
	private String tf_condition2;
	
	@FieldDefine(title = "备注", number = 90)
	private String tf_remark;

	public _NumberGroupDetail() {

	}

	public Integer getTf_numberGroupDetailId() {
		return tf_numberGroupDetailId;
	}

	public void setTf_numberGroupDetailId(Integer tf_numberGroupDetailId) {
		this.tf_numberGroupDetailId = tf_numberGroupDetailId;
	}

	public _NumberGroup getTf_NumberGroup() {
		return tf_NumberGroup;
	}

	public void setTf_NumberGroup(_NumberGroup tf_NumberGroup) {
		this.tf_NumberGroup = tf_NumberGroup;
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

	public String getTf_condition1() {
		return tf_condition1;
	}

	public void setTf_condition1(String tf_condition1) {
		this.tf_condition1 = tf_condition1;
	}

	public String getTf_condition2() {
		return tf_condition2;
	}

	public void setTf_condition2(String tf_condition2) {
		this.tf_condition2 = tf_condition2;
	}

	public String getTf_remark() {
		return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
		this.tf_remark = tf_remark;
	}

}
