/**
 * 系统主页的底部区域，主要放置用户单位信息，服务单位和服务单位和人员信息
 */
Ext.define('app.view.main.region.Bottom', {

	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.mainbottom',
	requires : [ 'app.ux.ButtonTransparent',
			'app.view.main.region.ButtomController','Ext.toolbar.Spacer','Ext.toolbar.Fill' ],

	defaults : {
		xtype : 'buttontransparent',
		// 默认都不显示，在渲染以后使用MVVM的特性来根据项目是否有值来确定hidden的属性
		hidden : true
	},
	// 此处指定了此控件的控制器。MainController将在此控件中不可用，
	// 但是Main的ViewModel是可以继承下来
	controller : 'buttom',

	style : 'background-color : #CAE5E8;',

	// items中的值都使用bind来设置值，同时显示，视觉效果还不错
	items : [
			{
				bind : {
					text : '{userInfo.tf_userdwmc}',
					hidden : '{!userInfo.tf_userdwmc}'
				},
				handler : 'onUserDwmcClick',
				iconCls : 'fa fa-building'
			},
			{
				bind : {
					text : '{userInfo.tf_departmentName}',
					hidden : '{!userInfo.tf_departmentName}'
				},
				handler : 'onUserDepartmentClick',
				iconCls : 'fa fa-university'
			},
			{
				bind : {
					text : '{userInfo.tf_userName}',
					hidden : '{!userInfo.tf_userName}'
				},
				iconCls : 'fa fa-user',
				menu : [ {
					text : '我的信息',
					handler : 'onUserInfoClick',
					iconCls : 'fa fa-info-circle'
				}, {
					text : '我的角色设置',
					handler : 'onUserRolesClick',
					iconCls : 'fa fa-users'
				}, {
					text : '我的操作权限',
					handler : 'onUserPopedomClick',
					iconCls : 'fa fa-bell-o'
				}, '-', {
					text : '我的登录日志',
					handler : 'onLoginInfoClick'
				}, {
					text : '我的操作日志',
					handler : 'onOperateInfoClick'
				}, '-', {
					text : '修改登录密码',
					handler : 'onChangePasswordClick',
					iconCls : 'fa fa-user-secret'
				}, '-', {
					text : '注销登录',
					handler : 'onLogoutClick',
					iconCls : 'fa fa-sign-out'
				} ]
			},
			'->',
			{
				bind : {
					text : '{serviceInfo.tf_serviceDepartment} '
							+ '{serviceInfo.tf_serviceMen}',
					hidden : '{!serviceInfo.tf_serviceDepartment}'
				},
				iconCls : 'fa fa-smile-o'
			}, {
				bind : {
					text : '{serviceInfo.tf_serviceTelnumber}',
					hidden : '{!serviceInfo.tf_serviceTelnumber}'
				},
				iconCls : 'fa fa-phone'
			}, {
				bind : {
					hidden : '{!serviceInfo.tf_serviceQQ}',
					text : '{serviceInfo.tf_serviceQQ}',
					handler : 'onQQClick'
				},
				iconCls : 'fa fa-qq'
			}, {
				bind : {
					// 绑定值前面加！表示取反，如果有email则不隐藏，如果email未设置，则隐藏
					hidden : '{!serviceInfo.tf_serviceEmail}'
				},
				iconCls : 'fa fa-envelope',
				handler : 'onEmailClick'
			}, {
				bind : {
					text : '{serviceInfo.tf_copyrightOwner}',
					hidden : '{!serviceInfo.tf_copyrightOwner}'
				},
				iconCls : 'fa fa-copyright'
			} ],

	initComponent : function() {
		console.log('bottom region init......');
		this.callParent(arguments);
	}
});