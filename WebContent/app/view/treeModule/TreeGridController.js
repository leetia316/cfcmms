Ext
		.define(
				'app.view.treeModule.TreeGridController',
				{
					extend : 'Ext.app.ViewController',
					alias : 'controller.treegrid',

					init : function() {
						console.log('tree grid controller init......');

					},

					onGridRender : function(grid) {
						grid.updateTitle();
					} ,
					
					onGridSelectionChanged : function(model, selected) {
						
						var grid = model.view.up('moduletreegrid');

						var selectedString = '';
						Ext.each(selected, function(obj) {
							for ( var i in obj.data) {
								selectedString += ',' + obj.data[i];
							}
						});
						if (grid.selectedString == selectedString)
							return;
						grid.selectedString = selectedString;
						console.log('selection change ');
						console.log(selected);

						if (grid.silent) { // 如果是沉默的，form的 subgrid 中修改过数据以后产生的事件，不需要刷新数据
							return;
						}

						grid.updateRecordDetail(selected);
						grid.module.updateActiveForm();
						// 如果显示的窗口正在显示，则更新
						if (grid.module.displayWindow
								&& !grid.module.getDisplayWindow().isHidden())
							grid.module.getDisplayWindow().form.setLinkedGrid(grid);
						grid.updateTitle();
					},
					
					
					onSetNotLeafButtonClick : function(button) {

						var grid = button.up('moduletreegrid');
						var selected = grid.getFirstSelectedRecord();
						if (selected && selected.get('leaf') == true) {
							selected.set('children', []);
							selected.set('leaf', false);
						}

					},

					onChangeRegionButtonClick : function(button, e, eOpts) {

						var np = button.getPosition(false);
						var numX = event.pageX - np[0] - button.getWidth() / 2, numY = 0 - (event.pageY
								- np[1] - button.getHeight() / 2);

						var d = getCursorFromXY(numX, numY);
						var toolbar = button.up('toolbar');

						toolbar.ownerCt.addDocked({
							dock : d == 'e' ? 'right' : d == 'n' ? 'top' : d == 'w' ? 'left'
									: 'bottom',
							xtype : 'treemoduleoperatetoolbar',
							modulePanel : toolbar.up('moduletreepanel')
						});
						toolbar.ownerCt.remove(toolbar, true);

					},

					onChangeRegionButtonRender : function(button) {
						button
								.getEl()
								.on(
										'mousemove',
										function(event) {
											var np = button.getPosition(false);
											var numX = event.pageX - np[0] - button.getWidth() / 2, numY = 0 - (event.pageY
													- np[1] - button.getHeight() / 2);
											button.getEl().setStyle({
												cursor : getCursorFromXY(numX, numY) + '-resize'
											});
										})
					},

					expandButtonClick : function() {
						this.getView().expandAll();
					},

					collapseButtonClick : function() {
						this.getView().collapseAll();
					},

					autoColumnWidthButtonClick : function() {
						this.getView().autoSizeAllColumn();
					},

					refreshButtonClick : function() {
						this.getView().getStore().reload();
					},

					collapseButtonClick : function() {
						this.getView().collapseAll();
					},

					expandButtonClick : function() {
						this.getView().expandAll();
					},

					onNodeBeforeDrop : function(node, data, overModel, dropPosition,
							dropHandlers) {

						var treegrid = data.view.ownerGrid;
						var sourceRecord = data.records[0]; // 当前拖动的记录

						dropHandlers.wait = true;

						if (dropPosition != 'append'
								&& sourceRecord.get(treegrid.module.tf_parentKey) === overModel
										.get(treegrid.module.tf_parentKey)) {
							console.log('在相同节点下移动');
							dropHandlers.processDrop();
						} else {
							var pnode = overModel;
							if (dropPosition != 'append') // before,after
								pnode = pnode.parentNode;
							var pid = pnode.isRoot() ? null : pnode.getIdValue();
							var mess = Ext.String.format('确定要将当前节点移动到『{0}』之下吗？', pnode
									.getTitleTpl());
							if (pnode.isRoot()) {
								mess = '确定要将当前节点设置成最顶层节点吗？';
							}
							Ext.MessageBox.confirm('确定移动', mess, function(btn) {
								if (btn === 'yes') {
									sourceRecord.set(treegrid.module.tf_parentKey, pid);
									sourceRecord.save({
										success : function(record, operation, success) {
											var result = Ext
													.decode(operation.getResponse().responseText);
											if (result.resultCode == 0) {
												dropHandlers.processDrop();
											} else
												dropHandlers.cancelDrop();
										}
									});
								} else {
									dropHandlers.cancelDrop();
								}
							});
						}
					},

					onNodeDrop : function(node, data, overModel, dropPosition,
							dropHandlers) {
						console.log('drop');
					}

				});

function getCursorFromXY(numX, numY) {
	var cursor = 'default';
	if (numY > 0) {
		if (numX < 0) {
			if (numX + numY > 0)
				cursor = 'n';
			else
				cursor = 'w';
		} else {
			if (numX - numY > 0)
				cursor = 'e';
			else
				cursor = 'n';
		}
	} else {
		if (numX < 0) {
			if (numX - numY > 0)
				cursor = 's';
			else
				cursor = 'w';
		} else {
			if (numX + numY > 0)
				cursor = 'e';
			else
				cursor = 's';
		}
	}
	return cursor;
}