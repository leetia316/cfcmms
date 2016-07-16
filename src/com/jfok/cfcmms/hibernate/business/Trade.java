package com.jfok.cfcmms.hibernate.business;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@SuppressWarnings("serial")
@TableDefine(group = "销售系统", id = 7016, title = "行业", codeLevel = "2,2")
public class Trade implements Serializable {

  @Id
  // 此处行业编码是分级的，类似于财务里面的科目，每级长为2,共分为2级。
  @FieldDefine(title = "行业编码", number = 10, fieldGroup = "基本信息")
  @Column(nullable = false, length = 6)
  private String tf_tradeId;

  @FieldDefine(title = "行业名称", number = 20, nameField = true, fieldGroup = "基本信息")
  @Column(nullable = false, length = 50, unique = true)
  private String tf_name;

  public Trade() {

  }

  public String getTf_tradeId() {
    return tf_tradeId;
  }

  public void setTf_tradeId(String tf_tradeId) {
    this.tf_tradeId = tf_tradeId;
  }

  public String getTf_name() {
    return tf_name;
  }

  public void setTf_name(String tf_name) {
    this.tf_name = tf_name;
  }

}
