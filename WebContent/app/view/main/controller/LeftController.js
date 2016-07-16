/**
 * 
 * 用来管理left界面菜单样式的转换等功能
 * 
 */
Ext.define('app.view.main.controller.LeftController', {
	extend : 'Ext.Mixin',

	//requires : [ 'app.view.main.menu.AccordionMainMenu' ],

	init : function() {
		console.log('LeftController init.....');
	},

	expandTreeMenu : function() {
		this.lookupReference('mainmenutree').expandAll();
	},

	collapseTreeMenu : function() {
		this.lookupReference('mainmenutree').collapseAll();
	},

	setAccordionMenu : function(tool) {
		console.log('以层叠方式显示菜单');
		
		var panel = this.lookupReference('mainmenuregion');
		panel.down('mainmenuaccordion').show();
		panel.down('mainmenutree').hide();
		tool.hide();
		tool.nextSibling().show();
		
		var expandtool = this.lookupReference('expandtool');
		expandtool.hide();
		expandtool.nextSibling().hide();
		
	},

	setTreeMenu : function(tool) {
		console.log('以树形方式显示菜单');

		var panel = this.lookupReference('mainmenuregion');
		panel.down('mainmenutree').show();
		panel.down('mainmenuaccordion').hide();
		tool.hide();
		tool.previousSibling().show();
		
		var expandtool = this.lookupReference('expandtool');
		expandtool.show();
		expandtool.nextSibling().show();
	}

});
