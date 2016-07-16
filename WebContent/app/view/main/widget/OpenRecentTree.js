/**
 * 最近打开过的模块
 */
Ext.define('app.view.main.widget.OpenRecentTree', {
	extend : 'Ext.tree.Panel',

	alias : 'widget.openrecenttree',

	openRecentCount : 10,

	store : Ext.create('Ext.data.TreeStore', {
		root : {
			text : 'Ext JS',
			expanded : true
		}
	}),

	rootVisible : true,

	lines : false,

	initComponent : function() {
		console.log('initComponent OpenRecentTree');
		var me = this;

		this.callParent(arguments);
	},

	listeners : {
		render : function(tree) {
			tree.localStore = Ext.data.StoreManager.lookup('OpenRecentStore');
			tree.localStore.load({
				scope : this,
				callback : function(records, operation, success) {

					//console.log(records);
					//console.log(tree.getStore().getRoot());
					for (i in records) {
						//console.log(records[i]);
						tree.getStore().getRoot().insertChild(0, {
							text : records[i].data.text,
							leaf : true
						});
					}
				}
			});
		}
	},

	/**
	 * 打开了一个模块以后，将其加入到OpenRecent的第一个,并更新各个菜单里的数据
	 */
	addItem : function(item) {
		this.getStore().getRoot().insertChild(0, item);
		this.localStore.add({
			text : item.text
		});
		this.localStore.sync();
	}

})
