Ext.define('app.view.treeModule.OperateToolBar', {
	extend : 'Ext.toolbar.Toolbar',
	alias : 'widget.treemoduleoperatetoolbar',

	initComponent : function() {

		this.items = [ Ext.create('app.module.widget.GridSchemeSegmented', {
			modulePanel : this.modulePanel
		}), {
			tooltip : '自动调整列宽',
			itemId : 'autocolumnwidth',
			iconCls : 'fa fa-magic',
			handler : 'autoColumnWidthButtonClick'
		}, {
			tooltip : '刷新数据',
			itemId : 'refresh',
			iconCls : 'fa fa-refresh',
			handler : 'refreshButtonClick'
		}, ' ', {
			tooltip : '全部折叠',
			itemId : 'collapse',
			iconCls : 'x-tool-img x-tool-collapse',
			handler : 'collapseButtonClick',
			ui : "default-toolbar-small"
		}, {
			tooltip : '全部展开',
			itemId : 'expand',
			iconCls : 'x-tool-img x-tool-expand',
			handler : 'expandButtonClick',
			ui : "default-toolbar-small"
		}, '-', {
			tooltip : '设置当前节点为父节点',
			itemId : 'setnotleaf',
			iconCls : 'fa fa-folder-open-o',
			handler : 'onSetNotLeafButtonClick'

		}, '->', {

			tooltip : '改变工具条的位置',
			itemId : 'changeregion',
			iconCls : 'fa fa-arrows',
			listeners : {
				click : 'onChangeRegionButtonClick',
				render : 'onChangeRegionButtonRender'
			}
		} ];
		this.callParent(arguments);
	}
})