package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@SuppressWarnings("serial")
@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(id = 8090, title = "字段列表属性", group = "编码设置")
public class _PropertyType implements _IModuleControlInterface, Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@FieldDefine(title = "ID号")
	private Integer tf_propertyTypeId;

	@FieldDefine(title = "属性名称", nameField = true)
	@Column(nullable = false, length = 50)
	private String tf_name; // varchar(50)

	@FieldDefine(title = "可录入", remark = "选中表示，此combo可以输入列表中没有的值")
	private Boolean tf_canInput;

	@FieldDefine(title = "可多选", remark = "选中表示，此combo可以多选")
	private Boolean tf_canmultSelected;

	@FieldDefine(title = "属性值")
	@Column(nullable = false)
	private String tf_value;

	@FieldDefine(title = "备注")
	private String tf_remark;

	public _PropertyType() {

	}

	public Integer getTf_propertyTypeId() {
	return tf_propertyTypeId;
	}

	public void setTf_propertyTypeId(Integer tf_propertyTypeId) {
	this.tf_propertyTypeId = tf_propertyTypeId;
	}

	public String getTf_name() {
	return tf_name;
	}

	public void setTf_name(String tf_name) {
	this.tf_name = tf_name;
	}

	public Boolean getTf_canInput() {
	return tf_canInput == null ? false : tf_canInput;
	}

	public void setTf_canInput(Boolean tf_canInput) {
	this.tf_canInput = tf_canInput;
	}

	public Boolean getTf_canmultSelected() {
	return tf_canmultSelected == null ? false : tf_canmultSelected;
	}

	public void setTf_canmultSelected(Boolean tf_canmultSelected) {
	this.tf_canmultSelected = tf_canmultSelected;
	}

	public String getTf_value() {
	return tf_value;
	}

	public void setTf_value(String tf_value) {
	this.tf_value = tf_value;
	}

	public String getTf_remark() {
	return tf_remark;
	}

	public void setTf_remark(String tf_remark) {
	this.tf_remark = tf_remark;
	}

	public int getValueOrder(String value) {
	String[] values = this.tf_value.split(",");
	for (int i = 0; i < values.length; i++) {
		if (values[i].equals(value))
		return i;
	}
	return -1;
	}

}
