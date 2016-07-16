Ext.define('overrides.selection.Model', {
	uses : [ 'Ext.selection.Model' ],
	override : 'Ext.selection.Model',
	onStoreRefresh : function() {
		this.updateSelectedInstances(this.selected);
		if (this.view && this.view.xtype == 'tableview') {
			if (this.getStore().count() > 0 && this.selected.length == 0) {
				if (app && app.viewport
						&& app.viewport.getViewModel().get('_autoselectrecord')) {
					switch (app.viewport.getViewModel().get('_autoselectrecord')) {
					case 'everyload':
						Ext.log('auto select first');
						this.select(this.getStore().first());
						break;
					case 'onlyone':
						if (this.getStore().count() == 1) {
							Ext.log('auto select first');
							this.select(this.getStore().first());
						}
						break;
					}
				}
			}
		}
	}
})