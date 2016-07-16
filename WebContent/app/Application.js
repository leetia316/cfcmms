Ext.define('app.Application', {
	extend : 'Ext.app.Application',

	name : 'app',

	controllers : [ 'MainRegion', 'Module', 'ModuleForm', 'ModuleToolbar',
			'ModuleAdditionFunction', 'GridNavigate', 'GridNavigateTree',
			'Attachment', 'Auditing', 'Approve', 'report.Report',
			'report.DateSectionSelect', 'report.NavigateSelectFieldsTree',
			'report.ResultListGridToolbar', 'userDefine.UserController' ],

	stores : [ 'OpenRecentStore', 'IconclsStore' ],

	requires : [ 'app.ux.Requires' ],

	init : function() {

		console.log('Application init......');
		// 设置button menu 的时候在console中显示错误，加了这句就好了。
		// 参阅此处
		// https://docs.sencha.com/extjs/6.0/whats_new/6.0.0/extjs_upgrade_guide.html#Button
		Ext.enableAriaButtons = false;

		// 如果一个 panel 没有设置title，会在console里面显示一个警告信息，加上这个就没了
		Ext.enableAriaPanels = false;
	},

	launch : function() {

		console.log('Application launch......');

		Ext.QuickTips.init();
		delete Ext.tip.Tip.prototype.minWidth; // 防止tooltip的宽度很小，不好看

		app.mainRegion = this.getController('MainRegion');

		// 需要执行的语句可以加在此处
		Ext.defer(function() {
			Ext.get("loading").remove();
		}, 500);

	}
});

/**
 * 这里的shiro的整合还是没有完成，暂时用下面的这个来判断，请不要修改index.jsp中的这一个注释行。
 */
Ext.Ajax.on('requestcomplete', function(conn, response, options) {
	// 暂时使用这样的机制，如果返回的ajax是<!-- index.jsp -->开头的话，谫是index.jsp，即要返回到登录界面
	if (Ext.String.startsWith(response.responseText, '<!-- index.jsp -->')) {
		Ext.MessageBox.show({
			title : '服务器超时',
			msg : '服务器登录超时，您需要重新登录系统。',
			buttons : Ext.MessageBox.OK,
			width : 350,
			icon : Ext.MessageBox.WARNING,
			fn : function() {
				var redirect = 'login.jsp';
				window.location = redirect;
			}
		});

	}
});

Ext.Ajax.on('requestexception', function(conn, response, options) {

});
