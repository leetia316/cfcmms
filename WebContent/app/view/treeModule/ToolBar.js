/**
 * merge level=45
 */
Ext
		.define(
				'app.view.treeModule.ToolBar',
				{
					extend : 'Ext.toolbar.Toolbar',
					alias : 'widget.treemoduletoolbar',
					requires : [ 'app.module.widget.GridSchemeCombo',
							'app.module.widget.GridSearchField',
							'app.module.widget.DetailSchemeCombo',
							'app.module.widget.GridGroupCombo' ],
					layout : {
						overflowHandler : 'Menu'
					},

					initComponent : function() {
						var me = this;
						this.items = [];
						this.items.push({
							text : '显示',
							iconCls : 'fa fa-newspaper-o',
							itemId : 'display',
							listeners : {
								click : function(button) {
									var grid = button.up('moduletreegrid');
									var selected = grid.getFirstSelectedRecord();
									if (selected) {
										var window = grid.module.getDisplayWindow();
										window.show();
										window.form.setLinkedGrid(grid);
									}
								}
							}
						});
						var userrole = this.modulePanel.module.tf_userRole;
						// 每一个模块的附件都有自己的权限

						if (userrole.tf_allowInsert
								&& this.modulePanel.gridType == 'normal') {
							var newmenuitems = [ {
								text : '复制新增',
								tooltip : '新增时先将当前选中的记录值复制到新记录中',
								itemId : 'newwithcopy',
								iconCls : 'fa fa-files-o'
							} ];

							// 是否有单条记录导入新增
							var adds = this.modulePanel.module.moduleExcelRecordAdds;
							if (adds && adds.length > 0) {
								// console.log(adds[0]);
								newmenuitems.push('-');
								newmenuitems.push({
									text : '上传Excel表单条新增',
									tooltip : '根据指定的excel表添好数据后，上传新增一条记录',
									itemId : 'uploadinsertexcelrecord',
									methodId : adds[0].tf_id,
									remark : adds[0].tf_remark
								})
							}

							if (this.modulePanel.module.tf_allowInsertExcel) {
								newmenuitems.push('-');
								newmenuitems.push({
									text : '上传Excel表批量新增',
									tooltip : '根据下载的Excel表中的要求添加数据后，上传批量新增数据',
									itemId : 'uploadinsertexcel'
								})
							}
							this.items.push({
								text : '新增',
								// icon : 'images/button/new.png',
								iconCls : 'fa fa-plus',
								itemId : 'new',
								xtype : 'splitbutton',
								menu : {
									items : newmenuitems
								},
								listeners : {
									click : function(button) {
										var grid = button.up('moduletreegrid');
										var window = grid.module.getNewWindow();
										window.form.setLinkedGrid(grid);
										window.show();
									}
								}

							});
						}
						if (userrole.tf_allowEdit && this.modulePanel.gridType == 'normal') {
							this.items.push({
								text : '修改',
								iconCls : 'fa fa-pencil-square-o',
								itemId : 'edit',
								listeners : {
									click : function(button) {
										var grid = button.up('moduletreegrid');
										var selected = grid.getFirstSelectedRecord('edit');
										if (selected) {

											var window = grid.module.getEditWindow();
											window.form.setLinkedGrid(grid);
											window.show();
										}
									}
								}
							});
							if (this.modulePanel.module.tf_fileField) {
								this.items.push({
									text : '上传',
									icon : 'images/button/upload.png',
									tooltip : '上传此条记录包含的文件',
									itemId : 'uploadfile'
								});
							}
						}

						if (userrole.tf_allowDelete
								&& this.modulePanel.gridType == 'normal')
							this.items.push({
								text : '删除',
								// icon : 'images/button/delete.png',
								iconCls : 'fa fa-trash-o',
								itemId : 'delete',
								listeners : {
									click : function(button){

										var grid = button.up('moduletreegrid');

										if (grid.getSelectionCount() > 1) {
											this.deleteRecords(button);
											return;
										}
										var selected = grid.getFirstSelectedRecord('delete');
										if (selected) {
											var canDelete = selected.canDelete();
											if (typeof canDelete == 'object') {
												Ext.toastWarn(canDelete.message);
												return false;
											}
											var text = grid.module.tf_title + ":【" + selected.getTitleTpl()
													+ '】';
											Ext.MessageBox
													.confirm('确定删除', '确定要删除当前选中的' + text + '吗?',
															function(btn) {
																if (btn == 'yes') {
																	// 重新创建一个新的model用于删除
																	var deleted = Ext.create(grid.store.model,
																			selected.data);
																	deleted
																			.erase({
																				success : function(proxy, operation) {
																					var result = Ext.decode(operation
																							.getResponse().responseText);
																					if (result.resultCode == 0) {
																						// 下面设置phantom=true,表示服务器上这条记录已经没有了，drop后就不会sync()了
																						selected.phantom = true;
																						selected.drop();
																						Ext.toastInfo(text + ' 已被成功删除！');
																					} else
																						// 删除失败
																						Ext.MessageBox.show({
																							title : '记录删除失败',
																							msg : text + '删除失败<br/><br/>'
																									+ result.message,
																							buttons : Ext.MessageBox.OK,
																							animateTarget : button.id,
																							icon : Ext.MessageBox.ERROR
																						});
																				},
																				failure : function() {
																				}
																			});
																}
															});
										}
									}
								}
							});

						/**
						 * 加入附加按钮
						 */
						if (this.modulePanel.module.tf_moduleAdditions) {
							Ext
									.each(
											this.modulePanel.module.tf_moduleAdditions,
											function(addition) {
												if (userrole.userRoleAdditions) {
													Ext
															.each(
																	userrole.userRoleAdditions,
																	function(roleaddition) {
																		if (roleaddition.tf_moduleAdditionFunctionId == addition.tf_moduleAdditionFunctionId) {
																			var button = {
																				tooltip : addition.tf_description,
																				text : addition.tf_title,
																				icon : addition.iconURL,
																				additionName : addition.tf_additionName,
																				needRecord : addition.tf_needRecord,
																				showWindow : addition.tf_showWindow
																			};
																			// 如果此功能要建立在某个菜单之上
																			if (addition.tf_menuName) {
																				var amenu = null;
																				// 查找此菜单是否已经存在了
																				Ext
																						.each(
																								me.items,
																								function(m) {
																									if (m.menuText == addition.tf_menuName) {
																										amenu = m;
																										return false;
																									}
																								})
																				if (!amenu) {
																					amenu = {
																						text : addition.tf_menuName,
																						// icon : addition.iconURL,
																						menuText : addition.tf_menuName,
																						xtype : 'splitbutton',
																						menu : []
																					}
																					me.items.push(amenu);
																				}
																				amenu.menu.push(button)
																				if (Ext.String.endsWith(
																						addition.tf_title, ' '))
																					amenu.menu.push('-')
																			} else
																				me.items.push(button)
																			return false;
																		}
																	});
												}
											})
						}

						if (this.modulePanel.module.tf_hasAttachment
								&& userrole.tf_attachmentBrowse) {

							var attachmentMenu = [];
							if (userrole.tf_attachmentInsert) {
								attachmentMenu.push({
									text : '新增附件',
									icon : 'images/button/additionadd.png',
									itemId : 'additionviewandinsert'
								});
								attachmentMenu.push('-');
							}
							attachmentMenu.push({
								text : '预览所有附件',
								itemId : 'additionview'
							}, '-', {
								text : '下载所有附件',
								itemId : 'downloadall',
								icon : 'images/button/download.png'
							});
							this.items.push({
								xtype : 'splitbutton',
								tooltip : '显示当前记录的所有附件',
								icon : 'images/button/addition.png',
								itemId : 'additiongrid',
								menu : attachmentMenu
							});
						}
						var printSchemes = this.modulePanel.module.recordPrintSchemes;

						var excelmenu = [ {
							text : '列表导出至excel',
							icon : 'images/button/excel.png',
							itemId : 'exportgrid'
						}, '-' ];
						// 有没有 excel report
						var excelReports = this.modulePanel.module.moduleExcelReports;
						if (excelReports && excelReports.length > 0) {
							Ext.each(excelReports, function(excelreport) {
								excelmenu.push({
									reportId : excelreport.tf_id,
									text : excelreport.tf_name,
									action : 'excelreport',
									icon : 'images/button/report.png'
								})
							})
						} else {
							excelmenu.push({
								text : '选中记录导出至excel',
								icon : 'images/button/excelone.png',
								schemeId : null,
								action : 'exportrecord'
							})
						}

						// 有没有 print scheme 可以打印的表，敢放在导出的里面
						if (printSchemes && printSchemes.length > 1) {
							excelmenu.push('-')
							Ext.each(printSchemes, function(scheme) {
								excelmenu.push({
									text : scheme.tf_schemeName,
									schemeId : scheme.tf_printSchemeId,
									action : 'exportrecord'
								})
							})
						}
						// 所有excel下载的菜单
						this.items.push({
							xtype : 'splitbutton',
							icon : 'images/button/excel.png',
							// iconCls : 'fa fa-file-excel-o',
							menu : excelmenu,
							handler : function(button) {
								var menuitem = button.down('#exportgrid');
								menuitem.fireEvent('click', menuitem);
							}
						});

						// 所有打印的
						var printmenu = [
								{
									text : '打印当前页',
									icon : 'images/button/print.png',
									itemId : 'printgrid',
									handler : function(button) {
										app.lib.GridPrinter.mainTitle = button.up('modulepanel')
												.down('modulegrid').module.tf_title
												+ '<br />　';
										app.lib.GridPrinter.print(button.up('modulepanel').down(
												'modulegrid'));
									}
								}, {
									text : '打印所有记录',
									icon : 'images/button/print.png',
									itemId : 'printgridall'
								} ];

						if (printSchemes && printSchemes.length > 0) {
							printmenu.push('-');
							Ext.each(printSchemes, function(scheme) {
								printmenu.push({
									text : scheme.tf_schemeName,
									schemeId : scheme.tf_printSchemeId,
									action : 'printrecord'
								})
							})
						}
						;
						// 所有的 Excel 报表也可以打印，先生成excel , 然后再转成pdf
						if (excelReports && excelReports.length > 0) {
							printmenu.push('-');
							Ext.each(excelReports, function(excelreport) {
								printmenu.push({
									reportId : excelreport.tf_id,
									text : excelreport.tf_name,
									action : 'showpdfreport', // 在新网页里 显示pdf report
									icon : 'images/button/report.png'
								})
							})
						}

						// 所有 print 下载的菜单
						this.items.push({
							xtype : 'splitbutton',
							icon : 'images/button/print.png',
							menu : printmenu,
							handler : function(button) {
								console.log('aa');
								var menuitem = button.down('#printgrid');
								menuitem.handler(menuitem);
							}
						});

						if (this.modulePanel.module.tf_hasChart) {
							this.items.push({
								xtype : 'button',
								icon : 'images/button/chart_bar.png',
								action : 'chart',
								tooltip : '图表分析'
							});
						}

						// 如果只有2个以下的子模块，就放在此处,原来的方案，后来改了
						// var childs = this.modulePanel.module.childNames;
						// if (childs && childs.length > 0 && childs.length <= 2) {
						// this.items.push("-");
						// for (var i in childs) {
						// var cm = app.modules.getModule(childs[i]);
						// this.items.push(app.module.ChildToolBar.getModuleButton(childs[i]))
						// }
						// }
						// 加入 moduleSubToolbar 中字义的子模块
						var subtoolbars = this.modulePanel.module.tf_moduleSubToolbar;
						if (subtoolbars && subtoolbars.length > 0
								&& subtoolbars.length <= 2) {
							this.items.push("-");
							for ( var i in subtoolbars) {
								this.items.push(app.module.ChildToolBar
										.getModuleButtonWithSingle(subtoolbars[i].tf_subMoudleName,
												subtoolbars[i].tf_openInWindow))
							}
						}

						this.items.push('-', '筛选', {
							width : 60,
							xtype : 'gridsearchfield',
							store : this.modulePanel.store
						});

						// this.items.push('->', {
						// xtype : 'gridgroupcombo',
						// modulePanel : this.modulePanel
						// });

						this.callParent(arguments);
					}

				})

/**
 * tf_allowApprove: 0 tf_allowAuditing: 0 tf_allowBrowse: 1 tf_allowDelete: 1
 * tf_allowEdit: 1 tf_allowExec: 0 tf_allowInsert: 1 tf_moduleId: "1010"
 * tf_userId: 1
 */
