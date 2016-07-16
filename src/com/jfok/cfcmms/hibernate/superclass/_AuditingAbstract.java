package com.jfok.cfcmms.hibernate.superclass;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import com.jfok.cfcmms.util.annotation.FieldDefine;

//此标注语言表明这是一个非实体类，但是其属性都可以映射到子类中
@MappedSuperclass
// 具有审核权限的类继承此类
public class _AuditingAbstract extends _InputInfoAbstract {

  @FieldDefine(title = "审核人员", number = 2010)
  @Column(length = 10)
  protected String tf_auditingName;// 审核人员

  @FieldDefine(title = "审核日期", number = 2020)
  protected Date tf_auditingDate;// 审核日期

  @FieldDefine(title = "审核备注", number = 2030)
  @Column(length = 100)
  protected String tf_auditingRemark;// 审核备注

  @FieldDefine(title = "审核", number = 2040)
  public Boolean tf_auditinged = false;

  public Boolean getTf_auditinged() { // 是否审核过了
    return tf_auditinged;
  }

  public void setTf_auditinged(Boolean tf_auditinged) {
    this.tf_auditinged = tf_auditinged;
  }

  public String getTf_auditingName() {
    return tf_auditingName;
  }

  public void setTf_auditingName(String tf_auditingName) {
    this.tf_auditingName = tf_auditingName;
  }

  public Date getTf_auditingDate() {
    return tf_auditingDate;
  }

  public void setTf_auditingDate(Date tf_auditingDate) {
    this.tf_auditingDate = tf_auditingDate;
    setTf_auditinged(this.tf_auditingDate != null);
  }

  public String getTf_auditingRemark() {
    return tf_auditingRemark;
  }

  public void setTf_auditingRemark(String tf_auditingRemark) {
    this.tf_auditingRemark = tf_auditingRemark;
  }

}
