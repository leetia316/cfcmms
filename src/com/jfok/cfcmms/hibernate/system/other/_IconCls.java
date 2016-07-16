package com.jfok.cfcmms.hibernate.system.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity

@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "辅助模块", id = 992101, title = "系统图标")
public class _IconCls {

  @Id
  @FieldDefine(title = "图标序号", number = 10, fieldGroup = "基本信息")
  @Column(nullable = false)
  private Integer tf_id;

  @FieldDefine(title = "图标名称", number = 20, nameField = true, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50)
  private String tf_name;

}
