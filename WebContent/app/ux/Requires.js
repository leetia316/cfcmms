/**
 * 这是一个专门用于加Requires的文件
 */

Ext.define('app.ux.Requires', {
	requires : [ 'Ext.window.Toast',
			'app.module.additionFunction.RoleSetPopedom',
			'app.module.additionFunction.SetAdditionFields',
			'app.module.additionFunction.SetDataFilterRoleDetail',
			'app.module.additionFunction.SetDetailGroupFields',
			'app.module.additionFunction.SetFormGroupFields',
			'app.module.additionFunction.SetGridGroupFields',
			'app.module.additionFunction.UserPopedom',
			'app.module.additionFunction.UserSetFieldHiddenRole',
			'app.module.additionFunction.UserSetFieldReadonlyRole',
			'app.module.additionFunction.UserSetRole',

			'overrides.selection.Model', 'overrides.form.Basic',
			'overrides.tree.Column', 'overrides.util.LocalStorage',
			'overrides.grid.locking.Lockable'
	]
})
