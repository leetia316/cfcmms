Ext.define('app.store.OpenRecentStore', {
	extend : 'Ext.data.Store',

	alias : 'store.openrecentstore',

	requires : [ 'app.model.OpenRecentModel' ],

	autoLoad : false,

	model : 'app.model.OpenRecentModel',

	proxy : {
		type : 'localstorage',
		id : getContextPath() + '/openRecentStore'
	},

	listeners11 : {
		load : function(records) {
			console.log(records);
		}
	}
})
