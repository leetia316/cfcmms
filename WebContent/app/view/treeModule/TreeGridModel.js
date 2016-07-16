
Ext.define('app.view.treeModule.TreeGridModel', {

	extend : 'Ext.app.ViewModel',

	alias : 'viewmodel.treegrid',

	data : {
		toolbarDock : 'left'
	},

	constructor : function() {
		console.log('treegrid viewmodel constructor......')
		this.callParent(arguments);
	}

})
