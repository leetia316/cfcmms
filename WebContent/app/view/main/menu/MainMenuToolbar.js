/**
 * 系统的主菜单条，根据MainModel中的数据来生成，可以切换至按钮菜单，菜单树
 */
Ext.define('app.view.main.menu.MainMenuToolbar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.mainmenutoolbar',

	requires : [ 'app.view.main.widget.MenuChangeToolbar',
			'app.ux.ButtonTransparent' ],

	defaults : {
		xtype : 'buttontransparent'
	},

	items : [ {
		xtype : 'menuchangetoolbar'
	}, ' ' ],

	initComponent : function() {

		// 把ViewModel中生成的菜单items加到此toolbar的items中
		this.items = this.items.concat(this.up('app-main').getViewModel()
				.getMenus());

		this.callParent();
	}
});