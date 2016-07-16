/**
 * merge level=80
 * 
 * 一个模块的toolbar上面的按钮的执行控制
 */

Ext
		.define(
				'app.controller.ModuleToolbar',
				{
					extend : 'Ext.app.Controller',

					requires : [ 'app.module.widget.GridSearchField' ],

					init : function(moduleName) {

						this
								.control({

									/**
									 * 模块的excel 报表，生成pdf 然后在网页里打开
									 */
									'moduletoolbar menuitem[action=showpdfreport]' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var excelReport = Ext.Array.findBy(
													grid.module.moduleExcelReports, function(item) {
														return item.tf_id == button.reportId
													})
											// console.log(excelReport);
											var selected;
											// 如果是要选择单条记录的，那就是单条excel 的下载
											if (excelReport.tf_isSelectRecord) {
												selected = grid.getFirstSelectedRecord();
												if (selected) {
													window
															.open(
																	Ext.String
																			.format(
																					'rest/module/downloadrecordexcelreportPDF.do?moduleId={0}&excelReportId={1}&id={2}',
																					grid.module.tf_moduleId,
																					excelReport.tf_id, selected
																							.getIdValue()),
																	'printexcelrecord');

												}
											}
										}
									},

									/**
									 * 模块报表的生成的导出
									 */
									'moduletoolbar menuitem[action=excelreport]' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var excelReport = Ext.Array.findBy(
													grid.module.moduleExcelReports, function(item) {
														return item.tf_id == button.reportId
													});
											// console.log(excelReport);
											var selected;
											// 如果是要选择单条记录的，那就是单条excel 的下载
											if (excelReport.tf_isSelectRecord) {
												selected = grid.getFirstSelectedRecord();
												if (selected) {
													window.location.href = Ext.String
															.format(
																	'rest/module/downloadrecordexcelreport.do?moduleId={0}&excelReportId={1}&id={2}',
																	grid.module.tf_moduleId, excelReport.tf_id,
																	selected.getIdValue());
												}
											} else
											// 是不是要选择一个月度
											if (excelReport.tf_isSelectMonth) {
												var win = Ext.create(
														'app.module.window.ExcelReportSelectSection', {
															module : grid.module,
															excelReport : excelReport
														});
												win.show();
											}
										}
									},

									/*
									 * ＊ 下载当前选中记录的所有附件
									 */
									'moduletoolbar menuitem#downloadall' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												window.location.href = 'attachment/downloadall.do?moduleId='
														+ grid.module.tf_moduleId
														+ '&id='
														+ selected.getIdValue()
														+ '&text='
														+ selected.getTitleTpl();
											}
										}
									},

									/**
									 * 显示子模块的按钮，childButton=true ,包括button和menuitem
									 */
									'toolbar [childButton=true]' : {

										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var posbutton = button;
												if (button.isMenu)
													posbutton = button.up('[text=更多]');
												if (button.openInWindow) { // 在window 中打开，是一个frame
																										// 的window
													var win = Ext
															.create(
																	'app.module.window.SubModuleWindow',
																	{
																		childModuleName : button.moduleName,
																		pModuleName : grid.module.tf_moduleName,
																		pId : selected.getIdValue(),
																		pName : selected.getTitleTpl(),
																		param : null,
																		animateTarget : posbutton.id,
																		y : posbutton.getY()
																				+ posbutton.el.dom.offsetHeight + 2,
																		x : 10,
																		height : document.body.clientHeight
																				- (posbutton.getY()
																						+ posbutton.el.dom.offsetHeight + 2 + 2),
																		width : document.body.clientWidth - 20

																	});
													win.show();
												} else
													// 在主tab中打开
													app.mainRegion
															.addParentFilterModule(button.moduleName,
																	grid.module.tf_moduleName, selected
																			.getIdValue(), selected.getTitleTpl(), {});
											}
										}
									},

									'moduletoolbar [action=chart]' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											Ext.widget('chartwindow', {
												grid : grid,
												x : grid.getX(),
												y : grid.getY(),
												height : grid.getHeight(),
												width : grid.getWidth()
											}).show();
										}
									},

									/**
									 * 在网页里用table生成后 打印当前记录
									 */
									'moduletoolbar [action=printrecord]' : {
										click : function(menuitem) {
											var grid = menuitem.ownerCt.up('grid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var html;
												Ext.Ajax
														.request({
															// 在异步下不能打开弹出式菜单
															async : false,
															url : 'rest/module/printrecord.do',
															params : {
																moduleName : grid.module.tf_moduleName,
																id : selected.getIdValue(),
																schemeId : menuitem.schemeId
															},
															success : function(response) {
																var htmlMarkup = [
																		'<html>',
																		'<head>',
																		'<link rel="Shortcut Icon" href="favicon.png" type="image/png" />',
																		'<link href="styles/printrecord.css" rel="stylesheet" type="text/css" />',
																		'<title>' + '打印' + grid.module.tf_title
																				+ '</title>',
																		'</head>',
																		'<body>',
																		'<div class="printer-noprint">',
																		'<div class="buttons">',
																		'<a class="button-print" href="javascript:void(0);" onclick="window.print();return false;">打印</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
																		'<a class="button-exit" href="javascript:void(0);" onclick="window.close();return false;">关闭</a>',
																		'<hr/>', '</div>', '</div>',
																		response.responseText, '</body>', '</html>' ];
																var html = Ext.create('Ext.XTemplate',
																		htmlMarkup).apply();
																var win = window.open('', 'printrecord');
																win.document.open();
																win.document.write(html);
																win.document.close();
															}
														});

											}
										}
									},

									/**
									 * 导出当前记录
									 */
									'moduletoolbar [action=exportrecord]' : {
										click : function(menuitem) {
											var grid = menuitem.ownerCt.up('grid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												window.location.href = Ext.String
														.format(
																'rest/module/exportrecord.do?moduleName={0}&id={1}&title={2}&schemeId={3}',
																grid.module.tf_moduleName, selected
																		.getIdValue(), selected.getTitleTpl(),
																menuitem.schemeId);
											}
										}
									},

									/**
									 * 导出当条条件下的grid记录
									 */
									'moduletoolbar menuitem#exportgrid' : {
										click : function(menuitem) {
											var grid = menuitem.ownerCt.up('grid');
											this.exportOrprintGrid(grid, true);
										}
									},

									/**
									 * 当条条件下的grid生成excel后转换成pdf ,然后新开一个页面打开
									 */
									'moduletoolbar menuitem#printgridall' : {
										click : function(menuitem) {
											var grid = menuitem.ownerCt.up('grid');
											this.exportOrprintGrid(grid, false);
										}
									},

									'moduletoolbar button#display' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var window = grid.module.getDisplayWindow();
												window.show();
												window.form.setLinkedGrid(grid);
											}
										}
									},

									'moduletoolbar button#new' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											// 检查是否有新增时必须选中的字段没有选择
											var allowNew = true;
											Ext
													.each(
															grid.module.tf_fields,
															function(field) {
																if (field.n) { // tf_newNeedSelected
																	if (grid
																			.getParentOrNavigateValue(field.tf_fieldType) == null) {
																		Ext.toastWarn('请在导航列表中选择一个『'
																				+ field.tf_title + '』再新增。');
																		allowNew = false;
																		return false;
																	}
																}
															})
											if (!allowNew)
												return;
											var window = grid.module.getNewWindow();
											window.form.setLinkedGrid(grid);
											window.show();
										}
									},

									'moduletoolbar menuitem#newwithcopy' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var window = grid.module.getNewWindow();
												window.form.copyrecord = selected;
												window.form.setLinkedGrid(grid);
												window.show();
											}
										}
									},

									'moduletoolbar menuitem#uploadinsertexcel' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var win = Ext.create(
													'app.module.window.UploadInsertExcelWindow', {
														module : grid.module
													});
											win.show();
										}
									},

									// 上传单条记录新增
									'moduletoolbar menuitem#uploadinsertexcelrecord' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var win = Ext.create(
													'app.module.window.UploadInsertExcelRecordWindow', {
														module : grid.module,
														methodId : button.methodId,
														remark : button.remark,
														grid : grid
													});
											win.show();
										}
									},

									// 对此条记录支付 ，或者可以在此模块中对其他模块的记录进行增加
									'moduletoolbar button#payout' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();

											if (selected) {
												var pstatus = selected.get('tf_payoutStatus');
												if (pstatus != '可支付') {
													Ext
															.toastWarn('此记录的支付状态为：『' + pstatus
																	+ "』，不能进行支付操作。");
													return;
												}

												var module = grid.module;
												var paymentdetail = app.modules
														.getModule(module.tf_moduleName + 'Detail');
												// console.log(paymentdetail);
												var window = paymentdetail.getPaymentWindow();
												var pf = {
													moduleId : module.tf_moduleId,
													moduleName : module.tf_moduleName,
													tableAsName : module.tableAsName,
													primarykey : module.tf_primaryKey,
													fieldtitle : module.tf_title,
													equalsValue : selected.getIdValue(),
													equalsMethod : null,
													text : selected.getTitleTpl(),
													isCodeLevel : module.codeLevel
												};
												window.form.setParentFilter(pf);
												window.show();
												window.on('hide', function() {
													grid.refreshAll();
												})
											}
										}
									},

									'moduletoolbar button#delete' : {
										click : this.deleteRecord
									},

									'moduletoolbar button#edit' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord('edit');
											if (selected) {
												var window = grid.module.getEditWindow();
												window.form.setLinkedGrid(grid);
												window.show();
											}
										}
									},

									'moduletoolbar button#uploadfile' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord('edit');
											if (selected) {
												var win = Ext.create(
														'app.module.window.UploadModuleFile', {
															module : grid.module,
															model : selected
														});
												win.show();
											}
										}
									},

									'moduletoolbar button#auditing' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var window = grid.module.getAuditingWindow();
												window.form.setLinkedGrid(grid);
												window.show();
											}
										}
									},

									'moduletoolbar button#approve' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												var window = grid.module.getApproveWindow();
												window.form.setLinkedGrid(grid);
												window.show();
											}
										}
									},

									// 显示附件列表,以供新增，修改，上传操作
									'moduletoolbar #additiongrid' : {
										click : function(button) {
											var grid = button.up('modulegrid');
											var selected = grid.getFirstSelectedRecord();
											if (selected) {
												app.mainRegion.addParentFilterModule('_Attachment',
														grid.module.tf_moduleName, selected.getIdValue(),
														selected.getTitleTpl(), {})

											}
										}
									},
									// 显示附件view，以供预览
									'moduletoolbar #additionview' : {
										click : this.additionview
									},
									// 显示附件view，以供预览 ,并立即启动新增界面
									'moduletoolbar #additionviewandinsert' : {
										click : this.additionview
									}
								})
					},

					additionview : function(button) {
						var grid = button.up('modulegrid');
						var selected = grid.getFirstSelectedRecord();
						if (selected) {
							var p = app.mainRegion.addParentFilterModule('_Attachment',
									grid.module.tf_moduleName, selected.getIdValue(), selected
											.getTitleTpl(), {
										showAdditionView : true,
										// 如果是直接新增的，那么在tab 中增加了此panel 以后， activetab不变
										notFocus : button.itemId == 'additionviewandinsert',
										parentModuleGrid : grid
									})
							if (button.itemId == 'additionviewandinsert') {
								var newbutton = p.down('button#new');
								if (newbutton)
									newbutton.fireEvent('click', newbutton);
							}
						}
					},

					/**
					 * 导出当前的grid 或者生成 pdf 然后新开一个网页显示 grid
					 */
					exportOrprintGrid : function(grid, toexcel) {
						var query;
						if (grid.store.filters)
							Ext.each(grid.store.filters.items, function(filter) {
								if (filter.property = 'query') {
									query = filter.value;
									return false;
								}
							});
						var params = Ext.apply(grid.store.extraParams);
						params.moduleName = grid.module.tf_moduleName;
						if (query)
							params.query = query;
						var sort = [];
						Ext.each(grid.store.sorters.items, function(s) {
							sort.push({
								property : s.property,
								direction : s.direction
							})
						});
						if (sort.length > 0)
							params.sort = Ext.encode(sort);

						var group = [];
						if (grid.store.groupers)
							Ext.each(grid.store.groupers.items, function(s) {
								group.push({
									property : s.property,
									direction : s.direction
								})
							});
						if (group.length > 0)
							params.group = Ext.encode(group);
						// console.log(Ext.Object.toQueryString(params));
						if (toexcel)
							window.location.href = Ext.String.format(
									'rest/module/exportgrid.do?{0}', Ext.Object
											.toQueryString(params));
						else {
							// 在网页里预览打印

							var printWindow = window.open();
							printWindow.document.write('<html><head>');
							printWindow.document.write('<title>' + '' + grid.module.tf_title
									+ '打印</title>');
							printWindow.document.write('</head><body>');
							printWindow.document
									.write('<iframe src="'
											+ Ext.String.format(
													'rest/module/exportgridtoprint.do?{0}', Ext.Object
															.toQueryString(params))
											+ '" style="width:100% ; height: 100%" frameborder="0"></iframe>');
							printWindow.document.write('</body></html>');
							win.document.close();

							// window.open(Ext.String.format('rest/module/exportgridtoprint.do?{0}',
							// Ext.Object.toQueryString(params)), 'printgridrecord');
						}

					},

					/**
					 * 删除grid的当前选中的记录，如果是多选，那删除第一条
					 */
					deleteRecord : function(button) {
						var grid = button.up('modulegrid');

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
													//
													// grid.store.remove(selected);
													// grid.store.sync({
													// callback : function(a, b, c, d) {
													// console.log(a);
													// console.log(b);
													// console.log(c);
													// console.log(d);
													// grid.getStore().add(grid.getStore().getRemovedRecords());
													// }
													// });
													//
													// return;

													// 重新创建一个新的model用于删除
													var deleted = Ext.create(grid.store.model,
															selected.data);
													deleted
															.erase({
																success : function(proxy, operation) {
																	var result = Ext.decode(operation
																			.getResponse().responseText);
																	if (result.resultCode == 0) {
																		// 下面设置phantom=true,表示服务器上这条记录已经没有了，remove后就不会sync()了
																		// selected.phantom = true;
																		// grid.store.remove(selected);
																		// grid.store.sync();
																		Ext.toastInfo(text + ' 已被成功删除！');
																		grid.refreshAll();
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
					},

					/**
					 * 删除grid的当前选中的多条记录，
					 */
					deleteRecords : function(button) {
						var grid = button.up('modulegrid');
						var selection = grid.getSelection('delete');
						var errormessage = [];

						Ext.each(selection, function(model) {
							var canDelete = model.canDelete();
							if (typeof canDelete == 'object') {
								errormessage.push(canDelete.message);
							}
						});
						if (errormessage.length != 0) {
							var s = Ext.String.format('以下 {0} 条不能删除，请重新选择后再删除。<br/>{1}',
									errormessage.length, '<ol><li>'
											+ errormessage.join('</li><li>') + '</li></ol>');
							Ext.toastWarn(s);
							return false;
						}
						var text = '<ol><li>'
								+ grid.getSelectionTitleTpl().join('</li><li>') + '</li></ol>';
						Ext.MessageBox
								.confirm(
										'确定删除',
										Ext.String.format('确定要删除' + grid.module.tf_title
												+ '当前选中的 {0} 条记录吗?<br/>{1}', selection.length, text),
										function(btn) {
											if (btn == 'yes') {

												Ext.Ajax
														.request({
															url : 'rest/module/removerecords.do',
															params : {
																moduleName : grid.module.tf_moduleName,
																ids : grid.getSelectionIds().join(","),
																titles : grid.getSelectionTitleTpl().join("~~")
															},
															success : function(response) {
																var info = Ext.decode(response.responseText,
																		true);
																if (info.resultCode == 0) {
																	Ext.toastInfo(text + ' 已成功被删除。');
																} else {
																	// 删除失败
																	Ext.MessageBox
																			.show({
																				title : '删除结果',
																				msg : (info.okMessageList.length > 0 ? ('已被删除记录：<br/>'
																						+ '<ol><li>'
																						+ info.okMessageList
																								.join('</li><li>') + '</li></ol><br/>')
																						: '')
																						+ '删除失败记录：<br/>'
																						+ '<ol><li>'
																						+ info.errorMessageList
																								.join('</li><li>')
																						+ '</li></ol>',
																				buttons : Ext.MessageBox.OK,
																				animateTarget : button.id,
																				icon : Ext.MessageBox.ERROR
																			});
																}
																// 有记录被删除，才刷新
																if (info.okMessageList.length > 0)
																	grid.refreshAll();
															},
															failure : function() {
																window.alert('删除时，服务器返回返回错误');
															}
														})
											}
										})
					}

				});