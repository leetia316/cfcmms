package com.jfok.cfcmms.hibernate.system.setting;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.jfok.cfcmms.hibernate.system.authority._Role;
import com.jfok.cfcmms.hibernate.system.authority._UserAdditionDepartment;
import com.jfok.cfcmms.hibernate.system.authority._UserDataFilterRole;
import com.jfok.cfcmms.util.annotation.FieldDefine;
import com.jfok.cfcmms.util.annotation.TableDefine;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)

@TableDefine(group = "系统设置", id = 9035, title = "用户")
public class _User implements Serializable {

  public static final String LOGINNAME = "tf_loginName";

  private static final long serialVersionUID = -4018384526282246891L;
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @FieldDefine(title = "序号", number = 10)
  private Integer tf_userId;

  @FieldDefine(title = "部门", number = 20)
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "tf_departmentId", nullable = false)
  private _Department tf_Department;

  @FieldDefine(title = "用户姓名", nameField = true, number = 30)
  @Column(length = 20, nullable = false)
  private String tf_userName;

  @FieldDefine(title = "登录名", remark = "用户登录本系统时使用的名称", number = 40)
  @Column(length = 20, nullable = false)
  private String tf_loginName;

  @FieldDefine(title = "职务", number = 50)
  @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
  @JoinColumn(name = "tf_positionId", nullable = false)
  private _Position tf_Position;

  @FieldDefine(title = "允许登录", remark = "是否允许此用户登录系统，如未选中则不许登录", number = 60)
  @Column(nullable = false)
  private Boolean tf_allowLogin;

  // @FieldDefine(title = "用户密码", hidden = true)
  @Column(length = 50, nullable = false)
  private String tf_password;

  @FieldDefine(title = "办公室", number = 70)
  @Column(length = 50)
  private String tf_office;

  @FieldDefine(title = "联系电话", number = 80)
  @Column(length = 20)
  private String tf_telnumber;

  @FieldDefine(title = "手机号码", number = 90)
  @Column(length = 20)
  private String tf_phonenumber;

  @FieldDefine(title = "电子邮件", number = 100)
  @Column(length = 50)
  private String tf_eMail;

  @FieldDefine(title = "QQ号", number = 110)
  @Column(length = 20)
  private String tf_qq;

  @FieldDefine(title = "可发送短信", number = 120, remark = "是否可对此人发送短信提醒")
  private Boolean tf_isSendMessage;

  @FieldDefine(title = "最后登录日期", number = 210)
  private Date tf_lastLoginDate;

  @FieldDefine(title = "登录次数", number = 220)
  private Integer tf_loginTimes;

  private Blob tf_signPhoto;

  @FieldDefine(title = "附加设置", number = 230)
  private String tf_favoriteSet;

  @FieldDefine(title = "备注", number = 240)
  private String tf_remark;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tf_User")
  private Set<_UserAdditionDepartment> userAdditionDepartments;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tf_User")
  private Set<_UserDataFilterRole> userDataFilterRoles;

  @FieldDefine(title = "权限列表", number = 200)
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  // 中间表
  @JoinTable(name = "_UserRole", joinColumns = {
      @JoinColumn(name = "tf_userId", referencedColumnName = "tf_userId") }, inverseJoinColumns = {
          // _UserRole 中的字段名，           _Role 中的关联字段名                    
          @JoinColumn(name = "tf_roleId", referencedColumnName = "tf_roleId") })
  public Set<_Role> tf_Roles;

  public _User() {

  }

  public _User(Integer tf_userId) {
    super();
    this.tf_userId = tf_userId;
  }

  public Integer getTf_userId() {
    return tf_userId;
  }

  public void setTf_userId(Integer tf_userId) {
    this.tf_userId = tf_userId;
  }

  public String getTf_loginName() {
    return tf_loginName;
  }

  public void setTf_loginName(String tf_loginName) {
    this.tf_loginName = tf_loginName;
  }

  public Boolean getTf_allowLogin() {
    return tf_allowLogin;
  }

  public void setTf_allowLogin(Boolean tf_allowLogin) {
    this.tf_allowLogin = tf_allowLogin;
  }

  public String getTf_userName() {
    return tf_userName;
  }

  public void setTf_userName(String tf_userName) {
    this.tf_userName = tf_userName;
  }

  public String getTf_password() {
    return tf_password;
  }

  public void setTf_password(String tf_password) {
    this.tf_password = tf_password;
  }

  public _Department getTf_Department() {
    return tf_Department;
  }

  public void setTf_Department(_Department tf_Department) {
    this.tf_Department = tf_Department;
  }

  public _Position getTf_Position() {
    return tf_Position;
  }

  public void setTf_Position(_Position tf_Position) {
    this.tf_Position = tf_Position;
  }

  public String getTf_office() {
    return tf_office;
  }

  public void setTf_office(String tf_office) {
    this.tf_office = tf_office;
  }

  public String getTf_telnumber() {
    return tf_telnumber;
  }

  public void setTf_telnumber(String tf_telnumber) {
    this.tf_telnumber = tf_telnumber;
  }

  public String getTf_phonenumber() {
    return tf_phonenumber;
  }

  public void setTf_phonenumber(String tf_phonenumber) {
    this.tf_phonenumber = tf_phonenumber;
  }

  public String getTf_eMail() {
    return tf_eMail;
  }

  public void setTf_eMail(String tf_eMail) {
    this.tf_eMail = tf_eMail;
  }

  public String getTf_qq() {
    return tf_qq;
  }

  public void setTf_qq(String tf_qq) {
    this.tf_qq = tf_qq;
  }

  public Date getTf_lastLoginDate() {
    return tf_lastLoginDate;
  }

  public void setTf_lastLoginDate(Date tf_lastLoginDate) {
    this.tf_lastLoginDate = tf_lastLoginDate;
  }

  public Integer getTf_loginTimes() {
    return tf_loginTimes;
  }

  public void setTf_loginTimes(Integer tf_loginTimes) {
    this.tf_loginTimes = tf_loginTimes;
  }

  public Blob getTf_signPhoto() {
    return tf_signPhoto;
  }

  public void setTf_signPhoto(Blob tf_signPhoto) {
    this.tf_signPhoto = tf_signPhoto;
  }

  public String getTf_favoriteSet() {
    return tf_favoriteSet;
  }

  public void setTf_favoriteSet(String tf_favoriteSet) {
    this.tf_favoriteSet = tf_favoriteSet;
  }

  public String getTf_remark() {
    return tf_remark;
  }

  public void setTf_remark(String tf_remark) {
    this.tf_remark = tf_remark;
  }

  public Boolean getTf_isSendMessage() {
    return tf_isSendMessage == null ? false : tf_isSendMessage;
  }

  public void setTf_isSendMessage(Boolean tf_isSendMessage) {
    this.tf_isSendMessage = tf_isSendMessage;
  }

  public Set<_UserAdditionDepartment> getUserAdditionDepartments() {
    return userAdditionDepartments;
  }

  public void setUserAdditionDepartments(Set<_UserAdditionDepartment> userAdditionDepartments) {
    this.userAdditionDepartments = userAdditionDepartments;
  }

  public Set<_UserDataFilterRole> getUserDataFilterRoles() {
    return userDataFilterRoles;
  }

  public void setUserDataFilterRoles(Set<_UserDataFilterRole> userDataFilterRoles) {
    this.userDataFilterRoles = userDataFilterRoles;
  }

  public Set<_Role> getTf_Roles() {
    return tf_Roles;
  }

  public void setTf_Roles(Set<_Role> tf_Roles) {
    this.tf_Roles = tf_Roles;
  }

}
