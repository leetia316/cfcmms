/**
 * 
 * 系统首页的字义
 * 
 */

Ext.define('app.view.main.region.HomePage', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.homepage',
	layout : 'border',

	requires : [ 'app.view.main.widget.OpenRecentTree' ],

	title : '首页',
	iconCls : 'fa fa-home',

	frame : false,
	border : false,
	items : [ {
		title : '相关事项',
		region : 'west',
		collapsible : true,
		split : true,
		width : 300,
		layout : 'accordion',
		header : {
		// hidden : true
		// style : 'background-color : #f6f5ec'
		},
		items : [ {
			title : '待办事项'
		}, {
			xtype : 'openrecenttree',
			reference : 'openrecenttree',
			title : '最近访问过的模块'
		}, {
			title : '最近修改过的数据'
		} ]
	}, {
		//title : '主显示区',
		region : 'center',
		layout : 'fit',
		items : [ Ext.create('app.view.main.widget.Gojs') ]
	} ]

});