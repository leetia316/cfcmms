/**
 * 系统主页的顶部区域，主要放置系统名称，菜单，和一些快捷按钮
 */
Ext.define('app.view.main.region.Top', {

	extend : 'Ext.toolbar.Toolbar',

	alias : 'widget.maintop', // 定义了这个组件的xtype类型为maintop

	requires : [ 'app.ux.ButtonTransparent', 'app.view.main.menu.ButtonMainMenu',
			'app.view.main.menu.SettingMenu', 'Ext.toolbar.Spacer',
			'Ext.toolbar.Fill', 'Ext.toolbar.Separator', 'Ext.Img', 'Ext.form.Label',
			'app.ux.ThemeSelect', 'app.view.main.widget.FavoriteButton' ],

	defaults : {
		xtype : 'buttontransparent'
	},

	style : 'border-bottom:1px solid white;'
			+ 'background-color:#CAE5E8;padding-right:30px;',
	border : '0 0 1 0',

	height : 42,

	initComponent : function() {
		console.log('top region init');
		this.items = [ {
			xtype : 'image',
			// 24*24 的系统图标文件
			src : 'images/system/favicon24.png'
		}, {
			xtype : 'label',
			// 系统名称
			bind : {
				text : '{systemInfo.tf_systemName}'
			},
			style : 'font-size:20px;color:blank;'
		}, {
			xtype : 'label',
			style : 'color:grey;',
			// 版本号,MVVM的用法，用bind注入，
			bind : {
				// 传统的用法，这个用法会在显示界面的时候，把数据显示好，用上面的方法在显示界面后，
				// 再进行变更，会看得出延时的过程
				// text :
				// '('+this.up('app-main').getViewModel().get('systemInfo.tf_systemVersion')
				// +')'
				text : '(Ver:{systemInfo.tf_systemVersion})'
			}
		}, '->', {
			xtype : 'buttonmainmenu',
			hidden : true,
			bind : {
				hidden : '{!isButtonMenu}'
			}
		}, ' ', ' ', {
			iconCls : 'pictos pictos-anchor'
		}, {
			text : '首页',
			iconCls : 'fa fa-home',
			handler : 'onHomePageButtonClick'
		}, {
			xtype : 'favoritebutton'
		}, {
			xtype : 'settingmenu'
		}, {
			text : '帮助',
			iconCls : 'fa fa-question',handler : function(button){
				console.log(Ext.getCmp('appviewport'));
				console.log(button.up('app-main'));
			}
		}, {
			text : '关于',
			iconCls : 'fa fa-exclamation-circle',
			handler : function(){
				Ext.create('app.view.main.widget.Gojs').show();
			}
		}, '->', '->', {
			text : '搜索',
			iconCls : 'fa fa-search',
			disabled : true
		}, {
			text : '注销',
			iconCls : 'fa fa-sign-out',
			handler : 'logout'
		}, ' ', {
			xtype : 'themeselectregion'
		} ];
		this.callParent(arguments);
	}
});