/**
 * 
 * merge level=48
 * 
 * 一个模块的总panel,包括导航，toolbar,listgrid,detail,etc.
 */

Ext.define('app.view.treeModule.ModuleTreePanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.moduletreepanel',

	requires : [ 'app.view.treeModule.TreeGrid' ],

	frame : false,
	border : false,
	layout : 'border',

	module : null,
	// 是否显示导航栏
	collapseNavigate : false,
	// 是否允许有导航栏
	enableNavigate : true,
	param : {},
	parentFilter : null,
	config : {
		gridType : 'normal'
	},
	initComponent : function() {

		Ext.apply(this, this.param);
		console.log(this.module);
		this.store = Ext.create('app.view.treeModule.TreeStore', {
			module : this.module,
			modulePanel : this,
			model : this.module.model
		});

		this.moduleName = this.module.tf_moduleName;
		var grid_detail = [ {
			region : 'center',
			xtype : 'moduletreegrid',
			modulePanel : this,
			parentFilter : this.parentFilter,
			gridType : this.gridType,
			store : this.store
		},{
			region : 'east',
			xtype : 'recorddetail',
			modulePanel : this,
			width : 400,
			title : '记录明细',
			split : true,
			collapsible : true,
			collapsed : true,
			collapseMode : 'mini'
		} ];

		this.items = grid_detail;

		this.callParent(arguments);
	}

});
