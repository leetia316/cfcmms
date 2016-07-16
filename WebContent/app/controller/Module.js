/**
 * merge level=80
 */

Ext.define('app.controller.Module', {
	extend : 'Ext.app.Controller',
	models : [],
	stores : [],
	views : [],
	requires : [],

	init : function(moduleName) {

		this
				.control({

					'numbercolumn' : {
						render : function(column) {
							column.getEl().removeCls('x-column-header-align-right');
							column.getEl().addCls('x-column-header-align-center');
						}
					},

					'gridschemecombo' : {
						change : this.gridSchemeChange
					},

					'gridschemesegmented' : {
						toggle : function(segmented, button) {
							segmented.up('modulegrid').updateColumnFieldsWithSchemeId(
									button.value, true);
						}
					},

					'detailschemecombo' : {
						change : this.detailSchemeChange
					},

					'gridgroupcombo' : {
						change : function(combo, selectId) {
							var grid = combo.up('modulepanel').down('modulegrid');
							grid.store.clearGrouping();
							grid.lockedGrid.getView().getFeature('grouping').disable();
							if (selectId != 'none') {
								grid.store.group(selectId);
								grid.lockedGrid.getView().getFeature('grouping').enable();
							}
						}
					},

					'recorddetail' : {
						beforeedit : function() {
							return false;
						},
						expand : function(p) {
							var grid = p.up('modulepanel').down('modulegrid');
							grid.updateRecordDetail(grid.getSelectionModel().getSelection());
						}
					},

					'modulegrid tableview' : {
						render : function(view) {
							// 创建这个列的tooltip
							view.tip = Ext.create('Ext.tip.ToolTip', {
								showDelay : 1000,
								dismissDelay : 20000,
								hideDelay : 0,
								target : view.el,
								delegate : '.childCountColumn', // 这个属性是上面renderer里面的class的名称
								trackMouse : false,
								listeners : {
									beforeshow : function updateTipBody(tip) {
										var record = view.getRecord(tip.triggerElement);
										var id = record.getIdValue();
										var childModule = tip.triggerElement
												.getAttribute('childModuleName');
										var tooltip = '';
										// 在这里通过一个同步ajax来取得当前记录的子模块的所有名称
										Ext.Ajax.request({
											async : false, // 同步
											method : 'get',
											url : 'module/getChildModuleDetail.do',
											params : {
												moduleName : record.module.tf_moduleName,
												id : id,
												childModuleName : childModule
											},
											success : function(response) {
												var records = eval(response.responseText);
												for ( var i in records)
													tooltip += '<li>' + records[i].name + "</li>";
											}
										})
										// 更新tooltip
										tip.update('<ol class="gridcelltooltip">' + tooltip
												+ '</ol>');
									}
								}
							});

							// 创建这个列的tooltip
							view.tipAggregate = Ext.create('Ext.tip.ToolTip', {
								showDelay : 1000,
								dismissDelay : 20000,								
								hideDelay : 0,
								target : view.el,
								delegate : '.childAggregateColumn', // 这个属性是上面renderer里面的class的名称
								trackMouse : false,
								listeners : {
									beforeshow : function updateTipBody(tip) {
										var record = view.getRecord(tip.triggerElement);
										var id = record.getIdValue();
										var childModule = tip.triggerElement
												.getAttribute('childModuleName');
										var childFieldName = tip.triggerElement
												.getAttribute('childFieldName');
										var tooltip = '';
										// 在这里通过一个同步ajax来取得当前记录的子模块的所有名称
										Ext.Ajax.request({
											async : false, // 同步
											method : 'get',
											url : 'module/getChildModuleDetail.do',
											params : {
												moduleName : record.module.tf_moduleName,
												id : id,
												childModuleName : childModule,
												childFieldName : childFieldName
											},
											success : function(response) {
												var records = eval(response.responseText);
												for ( var i in records)
													tooltip += '<tr><td><li>'
															+ records[i].name
															+ '</li></td><td style="text-align:right;"> '
															+ Ext.util.Format.number(records[i].value,
																	'0,000.00') + "</td></tr>";
											}
										})
										// 更新tooltip
										tip.update('<ol class="gridcelltooltip"><table>' + tooltip
												+ '</table></ol>');
									}
								}
							});

						}
					},

					'modulegrid' : {

						afterrender : this.gridAfterRender,

						selectionchange : function(model, selected) {
							var grid = model.view.up('modulegrid');
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

						cellclick : function(grid, td, cellIndex, record, tr, rowIndex, e,
								eOpts) {

						},
						/**
						 * 双击弹出显示窗口
						 */
						itemdblclick : function(grid, record, item, index, e, eOpts) {
							// grid.ownerCt.down('button#display').fireEvent('click',
							// grid.ownerCt.down('button#display'));
							var detail = grid.up('modulepanel').down('recorddetail');
							if (detail.collapsed)
								detail.expand();
							else
								detail.collapse();
						}
					},

					'modulegrid tool[type=maximize]' : {
						click : function(tool) {
							tool.up('modulepanel').down('recorddetail').collapse();
							if (tool.up('modulepanel').down('modulenavigate'))
								tool.up('modulepanel').down('modulenavigate').collapse();
							tool.up('modulegrid').setShowMaximize(false);
						}
					},

					'modulegrid tool[type=restore]' : {
						click : function(tool) {
							tool.up('modulepanel').down('recorddetail').expand();
							if (tool.up('modulepanel').down('modulenavigate'))
								tool.up('modulepanel').down('modulenavigate').expand();
							tool.up('modulegrid').setShowMaximize(true);
						}
					},

					'modulegrid tool[type=refresh]' : {
						click : function(tool) {
							tool.up('modulegrid').store.reload();
						}
					},

					// 字段列的顺序改变了
					'modulegrid headercontainer' : {
						columnmove : function(ct, column, fromIdx, toIdx) {
							column.up('modulegrid').getGridSettingMenu().down(
									'#savecolumnorder').setDisabled(false);
						}

					},

					'modulegrid gridcolumn' : {
						resize : function(column, width, height, oldWidth, oldHeight) {
							if (typeof (column.flex) == 'undefined'
									&& typeof (oldWidth) != 'undefined' && oldWidth != width) {
								column.up('modulegrid').getGridSettingMenu().down(
										'#savecolumnwidth').setDisabled(false);
								column.resized = true;
								width = width + 4;
								width = width - width % 5;
								column.setWidth(width);
							}
						}
					},

					// 显示 setting menu
					'modulegrid tool[type=gear]' : {
						click : function(tool, e, opts) {
							var menu = tool.up('modulegrid').getGridSettingMenu();
							menu.show();
							menu.setXY([
									Ext.Array.min([ e.browserEvent.clientX,
											document.body.clientWidth - 200 ]),
									e.browserEvent.clientY ]);
						}
					},

					// 显示 在paging上面的自动调整列宽被单击
					'modulegrid ownpagingtoolbar button#autocolumnwidth' : {
						click : function(button, e, opts) {
							button.up('modulegrid').autoSizeAllColumn();
						}
					},

					// 在按下gear显示菜单时，设置好菜单的状态
					'gridsettingmenu' : {
						beforeshow : function(menu) {

						}
					},

					// 保存改变过的列的顺序
					'gridsettingmenu #saverecordorder' : {
						click : function(item) {
							var grid = item.ownerCt.modulegrid;
							var ids = [];
							grid.getStore().each(function(record) {
								ids.push(record.getIdValue());
							});
							Ext.Ajax.request({
								url : 'moduleoperation/saverecordorder.do',
								params : {
									moduleName : grid.module.tf_moduleName,
									param : ids.join(',')
								},
								success : function() {
									Ext.toastInfo('当前记录顺序号已保存成功!');
									item.setDisabled(true);
									grid.getStore().reload();
								}
							});
						}
					},
					// 保存改变过的列位置到服务器
					'gridsettingmenu #savecolumnorder' : {
						click : function(item) {
							var grid = item.ownerCt.modulegrid;
							// 有了locked字段，grid view 分成二块，一块是 lockedGrid 一个是 normalGrid
							var items = grid.lockedGrid.getView().headerCt.items.items;
							items = items
									.concat(grid.normalGrid.getView().headerCt.items.items);
							var fieldids = [];
							Ext.each(items, function(column) {
								if (column.gridFieldId)
									fieldids.push(column.gridFieldId);
								else {
									// 审批，审核二个字段没有gridFieldId,但是也不要加进去
									if (column.items.length > 0 && column.items.items) {
										Ext.each(column.items.items, function(c) {
											if (c.gridFieldId)
												fieldids.push(c.gridFieldId);
										});
									}
								}
							});
							Ext.Ajax.request({
								url : 'moduleoperation/savegridcolumnorder.do',
								params : {
									param : fieldids.join(',')
								},
								success : function() {
									Ext.toastInfo('列表表头的顺序已保存成功!');
									item.setDisabled(true);
								}
							});

						}
					},

					// 自动调整列宽
					'gridsettingmenu #autocolumnwidth' : {
						click : function(item) {
							var grid = item.ownerCt.modulegrid;
							grid.autoSizeAllColumn();
							grid.getGridSettingMenu().down('#savecolumnwidth').setDisabled(
									false);
						}
					},

					// 保存改变过的列宽到服务器
					'gridsettingmenu #savecolumnwidth' : {
						click : function(item) {
							var grid = item.ownerCt.modulegrid;
							var items = grid.lockedGrid.getView().headerCt.items.items;

							var c = [];
							Ext.each(items, function(column) {
								if (column.isGroupHeader)
									Ext.each(column.items.items, function(cl) {
										if (cl.resized)
											c.push({
												gridFieldId : cl.gridFieldId,
												width : cl.width
											});
									});
								else if (column.resized)
									c.push({
										gridFieldId : column.gridFieldId,
										width : column.width
									});
							});

							items = grid.normalGrid.getView().headerCt.items.items;
							Ext.each(items, function(column) {
								if (column.isGroupHeader)
									Ext.each(column.items.items, function(cl) {
										if (cl.resized)
											c.push({
												gridFieldId : cl.gridFieldId,
												width : cl.width
											});
									});
								else if (column.resized)
									c.push({
										gridFieldId : column.gridFieldId,
										width : column.width
									});
							});

							var s = '';
							Ext.each(c, function(o) {
								s += o.gridFieldId + ':' + o.width + ',';
							});
							Ext.Ajax.request({
								url : 'moduleoperation/savegridcolumnwidth.do',
								params : {
									param : s
								},
								success : function() {
									Ext.toastInfo('列表表头的宽度已保存成功!');
									item.setDisabled(true);
									Ext.each(items.items, function(column) {
										if (column.isGroupHeader)
											Ext.each(column.items.items, function(cl) {
												delete cl.resized;
											});
										else
											delete column.resized;
									});
								}
							});
						}
					},

					// 保存改变过的列宽到服务器
					'gridsettingmenu #downloadinsertexcel' : {
						click : function(item) {
							// console.log(item);
							var grid = item.ownerCt.modulegrid;
							window.location.href = 'module/downloadinsertexcel.do?moduleId='
									+ item.ownerCt.modulegrid.module.tf_moduleId;
						}
					}
				});
	},

	gridAfterRender : function(grid) {

		// grid.lockedGrid.getView().getFeature('grouping').disable();
		grid.getStore().load();
		grid.updateTitle();
	},

	/**
	 * 用户选择了一个不同的grid方案
	 */
	gridSchemeChange : function(combo, schemeId) {
		combo.up('modulepanel').down('modulegrid').updateColumnFieldsWithSchemeId(
				schemeId, true);
	},

	/**
	 * 用户选择了一个不同的detail方案
	 */
	detailSchemeChange : function(combo, schemeId) {
		combo.up('modulepanel').down('recorddetail').selectScheme(schemeId);
		var grid = combo.up('modulepanel').down('modulegrid');
		grid.updateRecordDetail(grid.getSelectionModel().getSelection());
	}

});