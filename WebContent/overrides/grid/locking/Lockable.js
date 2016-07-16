Ext.define('overrides.grid.locking.Lockable', {
	uses : [ 'Ext.grid.locking.Lockable' ],
	override : 'Ext.grid.locking.Lockable',

	unlockText : '解锁列',

	lockText : '锁定列'
})