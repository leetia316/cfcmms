/**
 * merge level=80
 * 
 */
Ext
		.define(
				'app.controller.ModuleForm',
				{
					extend : 'Ext.app.Controller',

					init : function(moduleName) {
						this
								.control({

									// 创建当前记录的module 的 child module 菜单，选中了，可以显示子模块。
									'basewindow > header > tool[type=gear]' : {
										click : function(tool, e) {
											var window = tool.up('basewindow');
											var menu = Ext.create(
													'app.module.widget.FormSubModuleMenu', {
														module : window.module,
														window : window
													});
											menu.show();
											menu.setXY([
													Ext.Array.min([ e.browserEvent.clientX,
															document.body.clientWidth - 200 ]),
													e.browserEvent.clientY ]);

										}
									},

									'menuitem[formChildButton=true]' : {
										click : function(menuitem) {

											var form = menuitem.window.down('baseform');
											var idkey, title;

											if (form.data)
												idkey = form.data.data[form.module.tf_primaryKey];
											else if (form.model)
												idkey = form.model.getIdValue();
											if (!idkey) {
												Ext.toastWarn('此记录尚未保存，不能显示其子模块记录。');
												return;
											}
											title = form.data.data[form.module.tf_nameFields];

											var win = Ext.create('app.module.window.SubModuleWindow',
													{
														childModuleName : menuitem.moduleName,
														pModuleName : menuitem.window.module.tf_moduleName,
														pId : idkey,
														pName : title,
														param : null,
														y : 50,
														x : 10,
														height : document.body.clientHeight - 100,
														width : document.body.clientWidth - 20

													});
											win.show();

										}
									},

									// 下载当前记录的excel
									'basewindow tool[type=collapse]' : {
										click : function(tool) {
											var form = tool.up('basewindow').down('baseform');
											var idkey;
											if (form.data)
												idkey = form.data.data[form.module.tf_primaryKey];
											else if (form.model)
												idkey = form.model.getIdValue();
											if (idkey)
												window.location.href = Ext.String
														.format(
																'rest/module/exportrecord.do?moduleName={0}&id={1}',
																form.module.tf_moduleName, idkey);
											else
												Ext.toastWarn('此记录尚未保存，不能导出Excel。');
										}
									},

									// 打印当前的window form 页面，如果有滚动，还有问题，字段类容还没有
									'basewindow tool[type=print]' : {
										click : function(tool) {
											var targetElement = tool.up('window');
											var myWindow = window.open();
											myWindow.document.write('<html><head>');
											myWindow.document.write('<title>' + 'Title' + '</title>');
											myWindow.document
													.write('<link rel="Stylesheet" type="text/css" href="extjs4/resources/css/ext-all.css" />');
											myWindow.document
													.write('<script type="text/javascript" src="extjs4/ext-all.js"></script>');
											myWindow.document.write('</head><body>');
											myWindow.document.write(targetElement.body.dom.innerHTML);
											myWindow.document.write('</body></html>');
											myWindow.print();
										}
									},

									// 当前模块有附件的话，显示附件窗口
									'basewindow tool[type=search]' : {
										click : function(tool) {
											var thiswin = tool.up('window');
											var form = thiswin.down('form');

											if (!form.data || !form.data.getIdValue()) {
												Ext.toastWarn('此记录尚未保存，不可以查看附件!');
												return;
											}

											var aid = form.data.getIdValue();
											var aname = form.data.getTitleTpl();
											// 有权限关系，所以要重建
											if (window.attachmentWin)
												if (window.attachmentWin.pModuleName !== thiswin.module.tf_moduleName) {
													window.attachmentWin.destroy();
													window.attachmentWin = null;
												}
											if (!window.attachmentWin) {
												window.attachmentWin = Ext.widget('attahcmentwindow', {
													pModuleName : thiswin.module.tf_moduleName,
													pModuleTitle : thiswin.module.tf_title,
													aid : aid,
													aname : aname,
													// maximized : true,
													x : document.body.clientWidth * 0.1,
													y : document.body.clientHeight * .1,
													width : document.body.clientWidth * 0.8,
													height : document.body.clientHeight * 0.8,
													param : {}
												});
											} else {
												window.attachmentWin.changeParentFilter(
														thiswin.module.tf_moduleName,
														thiswin.module.tf_title, aid, aname);
											}
											window.attachmentWin.show();
										}
									},

									/**
									 * 记录修改窗口的事件
									 */
									// 有字段值改变时，将保存按钮激活
									'basewindow111 baseform[formtype=edit] field' : {
										change : function(field, newValue, oldValue, opts) {
											// console.log(field, newValue, oldValue);
											// console.log('触发了edit 事件：');
											console.log(field);
											if (field.up('modulepanel')
													|| field.xtype == 'imagefield'
													|| field.xtype == 'fileuploadfield') // 如果是modulepanel上面的padding
											// 的页码field
											// 也会激发此事件
											{
											} else {
												field.up('form').down('button#saveedit').enable();
											}
										}
									},
									'baseform button#editprior' : {
										click : this.editpriorornext
									},
									'baseform button#editnext' : {
										click : this.editpriorornext
									},
									// 保存修改的记录
									'baseform button#saveedit' : {
										click : function(button) {
											var me = this;
											var form = button.up('form');
											// 如果具有直接修改的权限，那么在保存的时候再问问，是否要保存已审核或审批的记录
											if (form.data.canEdit())
												this.saveedit(button);
											else
												Ext.MessageBox.show({
													title : '保存修改',
													msg : '当前' + form.module.tf_title
															+ '记录已经审核或审批过了，你确定要保存吗?',
													buttons : Ext.MessageBox.YESNO,
													fn : function(msg) {
														if (msg == 'yes')
															me.saveedit(button);
													},
													icon : Ext.MessageBox.QUESTION
												});
										}
									},

									'basewindow' : {
										show : function(window) {
											window.form.submitSuccessed = false;
											window.down('form').initForm();
										},

										beforeclose : function(window) {
											var me = this, form = window.down('form');
											if (form.formtype == 'edit') {
												if (!form.down('button#saveedit').disabled
														&& !window.afterquery) {
													Ext.MessageBox.show({
														title : '保存修改',
														msg : '当前' + form.module.tf_title
																+ '记录已经被修改过，需要保存吗?',
														buttons : Ext.MessageBox.YESNO,
														fn : function(msg) {
															if (msg == 'yes')
																me.saveedit(form.down('button#saveedit'));
															window.afterquery = true;
															window.close();
														},
														icon : Ext.MessageBox.QUESTION
													});
													return false;
												}
											}
										},

										close : function(window) {
											delete window.afterquery;
											// 如果当前模块有联动模块，并且该模块在显示，则需要刷新这些模块的数据
											if (window.form.formtype == 'new'
													|| window.form.formtype == 'edit'
													|| window.form.formtype == 'auditing'
													|| window.form.formtype == 'approve')
												if (window.form.module.tf_linkedModule)
													app.modules
															.refreshModuleGrid(window.form.module.tf_linkedModule);
											// 如果没有modulegrid,说明是其他模块调用的新增或修改，需要刷新此模块的grid,如果是显示，不用刷新
											if ((!window.form.moduleGrid)
													&& (window.form.formtype != 'display'))
												app.modules
														.refreshModuleGrid(window.form.module.tf_moduleName);
										}
									},

									/**
									 * 在加载了新的记录后，或者是namefields改变过后，都要改变window的title
									 */
									'baseform field[namefield=true]' : {
										change : function(field, newValue, oldValue, eOpts) {
											field.up('form').setWindowTitle();
										}

									},

									'baseform button#close' : {
										click : function(button) {
											button.up('window').close();
										}
									},

									/**
									 * form中的各个按钮的操作事件
									 */
									// 显示上一条
									'baseform button#prior' : {
										click : function(button) {
											console.log(button);
											console.log(button.ownerCt.ownerCt.xtype);
											button.up('form').moduleGrid.selectPriorRecord();
										}
									},
									// 显示下一条
									'baseform  toolbar  button#next' : {
										click : function(button) {
											button.up('form').moduleGrid.selectNextRecord();
										}
									},
									// 保存新增的记录
									'baseform button#savenew' : {
										click : this.savenew
									},
									// 保存新增附件的记录,并上传文件
									'baseform button#upload' : {
										click : this.upload
									},

									// 新增下一条记录
									'baseform button#newnext' : {
										click : function(button) {
											button.up('form').initForm();
										}
									},

									// 根据刚刚加进去的记录，新增下一条
									'baseform menuitem#newwithcopy' : {
										click : function(button) {
											var form = button.up('form');
											form.copyrecord = form.data;
											button.up('form').initForm();
										}
									}
								});
					},

					/**
					 * 修改窗口按下 下一条 和 上一条 按钮，先检查当前记录是否已经修改，如果修改提示是否保存。
					 * 
					 * @param {}
					 *          当前按下的键
					 */
					editpriorornext : function(button) {
						var me = this;
						var form = button.up('form');
						if (!form.down('button#saveedit').disabled) {
							Ext.MessageBox.show({
								title : '保存修改',
								msg : '当前' + form.module.tf_title + '记录已经被修改过，需要保存吗?',
								buttons : Ext.MessageBox.YESNOCANCEL,
								fn : function(msg) {
									if (msg == 'yes')
										me.saveedit(form.down('button#saveedit'));
									else if (msg == 'no') {
										if (button.itemId == 'editprior')
											form.moduleGrid.selectPriorRecord();
										else
											form.moduleGrid.selectNextRecord();
									}
								},
								icon : Ext.MessageBox.QUESTION
							});
						} else {
							if (button.itemId == 'editprior')
								form.moduleGrid.selectPriorRecord();
							else
								form.moduleGrid.selectNextRecord();
						}

					},

					saveedit : function(button) {
						var me = this;
						var form = button.up('form');
						if (form.getForm().isValid()) {
							var myMask = new Ext.LoadMask({
								msg : "正在保存结果，请稍候......",
								target : form
							});
							myMask.show();
							// 未修改之前的数据
							var model = Ext.create(form.module.model, form.data.getData());
							// 将修改过的数据更新到model中
							var fields = form.getForm().getFields();
							// 检查主键值有没有被修改过了
							var key = model.idProperty;
							var oldid = model.get(key);
							fields
									.each(function(field) {
										if (model.get(field.name) != field.getValue()) {
											// console.log('change value :' + field.xtype + ' '
											// + field.name + ',' + model.get(field.name) + ',to:'
											// + field.getValue());

											if (field.readOnly
													|| field.xtype == 'fileuploadfield'
													|| (field.xtype == 'imagefield' && (model
															.get(field.name) == null && field.getValue() == '')))
												// 对于imagefield来说有时候是空，有时候是''
												;
											else
												model.set(field.name, field.getValue());
										}
									});
							model.phantom = false; // 服务器上已有
							var text = form.module.tf_title + ":【" + model.getTitleTpl()
									+ '】';

							if (form.getForm().getValues()[key])
								if (oldid != form.getForm().getValues()[key]) {
									// console.log('change key:' + key + ',' + model.get(key) +
									// ',to:'
									// + form.getForm().getValues()[key]);
									model.getProxy().extraParams.oldid = oldid;
								}

							// console.log(model);

							model.getProxy().extraParams.operType = 'edit';
							model
									.save({
										success : function(record, operation, success) {
											myMask.hide();
											delete model.proxy.extraParams.operType;
											delete model.proxy.extraParams.oldid;
											var result = Ext
													.decode(operation.getResponse().responseText);
											if (result.resultCode == 0) {
												Ext.toastInfo(text + '已被成功修改！');
												// 从服务器返回的增加过后的数据，要重新load一次，会加入主键和一些其他做过的字段的改变
												var returnModel = Ext.create(form.module.model, Ext
														.decode(result.records[0]));
												var gridModel = form.moduleGrid.getSelectionModel()
														.getSelection()[0];

												// store增加了一个记录后，store.getTotalCount()的值不会改变，重新刷新才会，而且新增加的值的recno是undefined
												Ext.each(form.module.tablefields, function(field) {
													if (gridModel.get(field.name) != returnModel
															.get(field.name)) {
														gridModel.set(field.name, returnModel
																.get(field.name));
													}
												});
												gridModel.commit(); // commit表示数据已经在服务器上更新过了
												Ext.each(form.query('fileuploadfield'),
														function(field) {
															field.reset();
														});
												form.getForm().loadRecord(returnModel);
												form.submitSuccessed = true;
											} else {
												// 新增失败,将失败的内容写到各个字段的错误中去
												form.getForm().markInvalid(result.errorMessage);
												Ext.MessageBox.show({
													title : '记录修改失败',
													msg : text + '修改失败<br/><br/>'
															+ me.getResponseError(form, result.errorMessage),
													buttons : Ext.MessageBox.OK,
													icon : Ext.MessageBox.ERROR
												});
											}
										},
										failure : function() {
											myMask.hide();
											delete model.proxy.extraParams.operType;
											delete model.proxy.extraParams.oldid;
										}
									});
						} else {
							Ext.toastErrorInfo(this.getFormError(form));
						}
					},

					upload : function(button) {
						var me = this;
						var form = button.up('form');
						if (form.getForm().isValid()) {
							form.getForm()
									.submit(
											{
												url : 'attachment/uploadnew.do',
												waitMsg : '正在上传文件,请稍候...',
												timeout : 60,
												success : function(baseform, action) {
													Ext.toastInfo('附件文件已上传成功!');
													button.setVisible(false);
													button.ownerCt.down('#newnext').setVisible(true);
													Ext.each(form.query('fieldset'), function(f) {
														// f.setDisabled(true);
													});
													var grid = form.moduleGrid;
													grid.store.reload();

													// 如果模块panel有一个父模块的panel,例如附件肯定有一个像模块的panel
													// ,那么在新增以后，刷新那个模块的grid
													if (grid.up('modulepanel').param.parentModuleGrid)
														grid.up('modulepanel').param.parentModuleGrid
																.refreshSelectedRecord();

												},
												failure : function(baseform, action) {
													if (action.response.responseText
															.indexOf('MaxUploadSize'))
														Ext.MessageBox.show({
															title : '上传文件失败',
															msg : '失败原因:上传文件的大小超过了10M,请重新上传...',
															buttons : Ext.MessageBox.OK,
															icon : Ext.MessageBox.ERROR
														});
													else
														Ext.Msg.alert('上传文件失败',
																action.response.responseText);
												}
											});
						}
					},

					savenew : function(button) {
						var me = this;
						var form = button.up('form');
						if (form.getForm().isValid()) {
							var myMask = new Ext.LoadMask({
								msg : "正在保存结果，请稍候......",
								target : form
							});
							myMask.show();
							var model = Ext.create(form.module.model, form.getForm()
									.getValues());
							model.phantom = true; // 服务器上还没有此条记录
							var text = form.module.tf_title;
							model
									.save({
										success : function(record, operation, success) {
											myMask.hide();
											var result = Ext
													.decode(operation.getResponse().responseText);
											if (result.resultCode == 0) {
												// 从服务器返回的增加过后的数据，要重新load一次，会加入主键和一些其他做过的字段的改变
												var returnModel = Ext.create('app.model.'
														+ form.module.tf_moduleName, Ext
														.decode(result.records[0]));
												text += ":【" + returnModel.getTitleTpl() + '】';
												Ext.toastInfo(text + '已被成功添加！');
												var grid = form.moduleGrid;
												if (grid) {
													if (grid.xtype == 'modulegrid') {
														// 这里不能用 grid.getStore().add(returnModel)
														// ,用了会报一个错误，这是
														// extjs5里面的一个bug,还是我有些配置的东西错了呢
														grid.getStore().loadData([ returnModel ], true);
														grid.getStore().totalCount++;
														grid.getSelectionModel().select(returnModel);
														grid.up('modulepanel').refreshNavigate();
													} else { // 如果是treegrid
														returnModel.set('children', []);
														grid.getStore().getRoot().appendChild(returnModel);
													}
												}
												form.setData(returnModel);
												// form.getForm().loadRecord();
												// form.data = returnModel;
												button.setVisible(false);
												if (button.ownerCt.down('#newnext'))
													button.ownerCt.down('#newnext').setVisible(true);
												// Ext.each(form.query('fieldset'), function(f) {
												// if (!f.down('modulepanel'))
												// f.disable(); //setDisabled(true);
												// });
												form.submitSuccessed = true;
											} else {
												// 新增失败,将失败的内容写到各个字段的错误中去
												form.getForm().markInvalid(result.errorMessage);
												Ext.MessageBox.show({
													title : '记录新增失败',
													msg : text + '新增失败<br/><br/>'
															+ me.getResponseError(form, result.errorMessage),
													buttons : Ext.MessageBox.OK,
													icon : Ext.MessageBox.ERROR
												});
											}
										}
									});
						} else {
							Ext.toastErrorInfo(this.getFormError(form));
						}
					},

					getResponseError : function(form, errors) {
						var errorMessage = '';
						for ( var fieldname in errors) {
							var fs = fieldname;
							var field = form.getForm().findField(fieldname);
							if (field) {
								if (field.getFieldLabel()) {
									fs = field.getFieldLabel();
								} else {
									var fd = form.module.getFieldDefineWithName(fieldname);
									if (fd)
										fs = fd.tf_title;
								}
							}
							errorMessage = errorMessage + fs + " : " + errors[fieldname]
									+ '</br>';
						}
						return errorMessage;
					},

					getFormError : function(form) {
						var fields = form.getForm().getFields();
						var errorMessage = '';
						fields.each(function(field) {
							Ext.each(field.getErrors(), function(error) {
								errorMessage = errorMessage + field.getFieldLabel() + " : "
										+ error + '</br>';
							});
						});
						return errorMessage;
					}

				});