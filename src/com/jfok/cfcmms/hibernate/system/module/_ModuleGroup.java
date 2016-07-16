package com.jfok.cfcmms.hibernate.system.module;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.jfok.cfcmms.hibernate.system._IModuleControlInterface;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;
import com.jfok.cfcmms.share.RolePopedom;

/**
 * 模块分组，每一个module都被分配到了一个组中，组是分级的，每个模块必须放在最末级的分组上。
 * 
 * 暂定分级为2,2,2即最大可分成3级，每级2位代码
 * 
 * @author jfok 2012.11.7
 */
@SuppressWarnings("serial")
@Entity
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@TableDefine(group = "系统模块", id = 9901, title = "模块分组", codeLevel = "2,2,2", 
    isSystem = true, defaultOrderField = "tf_moduleGroupId")
public class _ModuleGroup implements _IModuleControlInterface, Serializable {

  public static final String TITLE = "tf_title";

  @Id
  @FieldDefine(title = "模块分组编码", number = 10)
  @Column(nullable = false, length = 10)
  private String tf_moduleGroupId;

  @FieldDefine(title = "模块分组名称", nameField = true, number = 20)
  @Column(nullable = false, length = 50, unique = true)
  private String tf_title;

  @FieldDefine(title = "系统组", number = 30)
  private Boolean tf_isSystemGroup;

  @FieldDefine(title = "描述", number = 40)
  @Column(length = 50)
  private String tf_description;

  @FieldDefine(title = "图标Cls", number = 50)
  @Column(length = 50)
  private String tf_iconCls;

  @FieldDefine(title = "图标地址", number = 60)
  @Column(length = 50)
  private String tf_iconUrl;

  @JsonIgnore
  @FieldDefine(title = "图标文件", number = 70)
  private Blob tf_iconFile;

  @FieldDefine(title = "备注", number = 190)
  private String tf_remark;

  // 每一个此分组下的模块的权限定义
  @Transient
  private List<RolePopedom> popedoms;

  public _ModuleGroup() {

  }

  @Override
  public String toString() {
    return "_ModuleGroup [tf_moduleGroupId=" + tf_moduleGroupId + ", tf_title=" + tf_title
        + ", tf_description=" + tf_description + ", tf_iconURL=" + tf_iconUrl + ", tf_remark="
        + tf_remark + "]";
  }

  public String getTf_moduleGroupId() {
    return tf_moduleGroupId;
  }

  public void setTf_moduleGroupId(String tf_moduleGroupId) {
    this.tf_moduleGroupId = tf_moduleGroupId;
  }

  public String getTf_title() {
    return tf_title;
  }

  public void setTf_title(String tf_title) {
    this.tf_title = tf_title;
  }

  public String getTf_description() {
    return tf_description;
  }

  public void setTf_description(String tf_description) {
    this.tf_description = tf_description;
  }

  public Boolean getTf_isSystemGroup() {
    return tf_isSystemGroup;
  }

  public void setTf_isSystemGroup(Boolean tf_isSystemGroup) {
    this.tf_isSystemGroup = tf_isSystemGroup;
  }

  public String getTf_iconCls() {
    return tf_iconCls;
  }

  public void setTf_iconCls(String tf_iconCls) {
    this.tf_iconCls = tf_iconCls;
  }

  public String getTf_iconUrl() {
    return tf_iconUrl;
  }

  public void setTf_iconUrl(String tf_iconUrl) {
    this.tf_iconUrl = tf_iconUrl;
  }

  public Blob getTf_iconFile() {
    return tf_iconFile;
  }

  public void setTf_iconFile(Blob tf_iconFile) {
    this.tf_iconFile = tf_iconFile;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public List<RolePopedom> getPopedoms() {
    return popedoms;
  }

  public void setPopedoms(List<RolePopedom> popedoms) {
    this.popedoms = popedoms;
  }

}
