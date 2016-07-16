Ext
		.define(
				'app.view.treeModule.TreeGrid',
				{
					extend : 'Ext.tree.Panel',
					alias : 'widget.moduletreegrid',

					requires : [ 'app.view.treeModule.TreeGridController',
							'app.view.treeModule.TreeGridModel',
							'app.module.factory.ColumnsFactory',
							'app.view.treeModule.ToolBar','app.view.treeModule.OperateToolBar' ],

					controller : 'treegrid',
					viewModel : 'treegrid',

					module : null,
					columnLines : true,
					bodyCls : 'panel-background',
					multiSelect : false,
					selType : 'rowmodel',// 'checkboxmodel','SINGLE'
					enableLocking : true,

					// config : {
					parentFilter : null,
					gridSettingMenu : null,
					gridType : 'normal',

					// },
					schemeOrder : null,
					// 如果要分组的话，要改三处，这个文件二处，还有一个在 controller/module.js中的gridAfterRender中。
					// features : [{
					// ftype : 'grouping', // 加了这个没有分组，会报错
					// id : 'grouping'
					// }],
					tools : [
							{
								type : 'refresh',
								tooltip : '刷新数据'
							},
							{
								type : 'print',
								listeners : {
									click : function(tool) {
										app.lib.GridPrinter.mainTitle = tool.up('grid').module.tf_title
												+ '<br />　';
										app.lib.GridPrinter.print(tool.up('grid'));
									}
								}
							}, {
								type : 'gear'
							}, {
								type : 'maximize',
								hidden : true,
								tooltip : '当前列表最大化'
							}, {
								type : 'restore',
								tooltip : '显示导航及明细'
							}, {
								type : 'expand',
								handler : 'expandButtonClick'

							}, {
								type : 'collapse',
								handler : 'collapseButtonClick'

							} ],

					rootVisible : false,

					viewConfig : {
						// trackOver : false
						loadMask : true,
						stripeRows : true,
						plugins : {
							ptype : 'treeviewdragdrop',
							ddGroup : 'DD_treeview', // + this.module.tf_moduleName,
							enableDrop : true,
							containerScroll : true
						},
						listeners : {
							beforedrop : 'onNodeBeforeDrop',

							drop : 'onNodeDrop'

						}
					},

					listeners : {
						afterrender : 'onGridRender',
						selectionchange : 'onGridSelectionChanged'
					},
					
					initComponent : function() {

						console.log('init Compnent grid');

						this.module = this.modulePanel.module;

						this.schemeOrder = this.module.tf_gridSchemes[0].tf_schemeOrder;
						this.columns = app.module.factory.ColumnsFactory.getColumns(
								this.module, this.schemeOrder);
						this.columns[0].xtype = 'treecolumn';

						this.setColumnsNameToStoreExtraParams();

						var gridschemecount = this.modulePanel.module.tf_gridSchemes.length;
						this.dockedItems = [ {
							dock : 'top',
							modulePanel : this.modulePanel,
							xtype : 'treemoduletoolbar'
						}, {
							dock : 'top',
							modulePanel : this.modulePanel,
							xtype : 'childtoolbar'
						}, {
							dock : 'left',
							xtype : 'treemoduleoperatetoolbar',
							modulePanel : this.modulePanel
						} ];

						this.callParent(arguments);
					},

					/**
					 * 在执行了操作之后，修改当前title
					 */
					updateTitle : function() {
						if (!this.view)
							return;
						var selected = this.view.getSelectionModel().selected.items[0];
						var title = this.module.tf_title;
						if (selected)
							if (!!selected.getTitleTpl())
								title = title + '　〖<em>' + selected.getTitleTpl() + '</em>〗';

						// 如果有父模块的filter，则加进来
						if (this.parentFilter) {
							title = title + ' 『' + this.parentFilter.fieldtitle + ':'
									+ this.parentFilter.text + '』 ';
						}

						// 如果有导航条件，加到titleLabel中去
						var navigates = this.store['navigates'];
						// console.log(navigates);

						var navigateString = '';
						if (navigates && navigates.length > 0) {
							navigateString = navigateString
									+ '<div style="display :inline-table;">';
							for ( var i in navigates) {
								navigateString = navigateString + '■ '
										+ navigates[i].fieldtitle + "：<em>" + navigates[i].text
										+ '</em><br/>';
							}
							navigateString = navigateString + '</div>';

						}
						if (navigateString)
							navigateString = '导航值：' + navigateString;

						this.setTitle(title + '　　　' + navigateString);

						// this.navigateTitleLabel.setText(navigateString);
					},

					setParentFilter : function(fp) {
						this.parentFilter = fp;
						if (!this.store)
							return;
						this.store.extraParams.parentFilter = Ext.encode(this.parentFilter);
						this.store.setNavigates([]); // 改变了parentFilter后清除navigate的导航选择
						// this.store.loadPage(1);
						this.updateTitle();
					},

					// 在改变了父模块的约束之后，需要刷新数据
					changeParentFilter : function(fp, navigates) {
						this.parentFilter = fp;
						this.store.extraParams.parentFilter = Ext.encode(this.parentFilter);
						// 如果有指定的navigates
						if (typeof (navigates) == 'object')
							this.store.setDefaultNavigates(navigates.defaultNavigateValues);
						else
							this.store.setDefaultNavigates([]);
						this.store.loadPage(1);
						this.updateTitle();
					},

					/**
					 * 取得某个模块的parent 或者 navigate 的导航值
					 * 
					 * @param {}
					 *          moduleName ,当前parent或者导航的模块名称
					 * @return 如果找到，则返回控制此grid的模块的值，如果没有，返回null
					 */
					getParentOrNavigateValue : function(moduleName) {
						var me = this;
						var keyid = null;
						if (me.parentFilter && me.parentFilter.moduleName == moduleName)
							keyid = me.parentFilter.equalsValue;
						else if (me.store.navigates) // 看看有没有_Module的导航设置
							Ext.each(me.store.navigates, function(n) {
								if (n.moduleName == moduleName) {
									keyid = n.equalsValue;
									return false;
								}
							});
						return keyid;
					},

					/**
					 * 取得本模块某个字段的导航值
					 * 
					 * @param {}
					 *          fieldname ,导航模块的字段名称
					 * @return 如果找到，则返回控制此grid的模块，如果没有，返回null
					 */
					getNavigateWithFieldName : function(fn) {
						var me = this;
						var record = null;
						if (me.store.navigates) // 看看有没有_Module的导航设置
							Ext.each(me.store.navigates, function(n) {
								if (n.primarykey == fn) {
									record = n;
									return false;
								}
							});
						return record;
					},

					/**
					 * 重新选择了列表方案之后，替换方案中的字段
					 */
					updateColumnFieldsWithSchemeId : function(schemeOrderId) {
						this.schemeOrder = schemeOrderId;
						this.columns = app.module.factory.ColumnsFactory.getColumns(
								this.module, schemeOrderId);
						this.reconfigure(this.store, this.columns);
						this.setColumnsNameToStoreExtraParams();
						// 如果不是禁止自动列宽，那么在换了方案之后就自动排列一下
						if (app.viewport.getViewModel().get('autoColumnMode') != 'disable')
							this.autoSizeAllColumn();

					},

					/**
					 * 将当前选中的记录值新到propertyGrid中
					 */
					updateRecordDetail : function(selected) {
						return;
						if (selected[0])
							this.up('moduletreepanel').down('recorddetail').updateSource(
									selected[0]);
						else
							this.up('moduletreepanel').down('recorddetail').updateSource(null);
					},

					/**
					 * 设置当前最大化，与恢复的的显示状态
					 */
					setShowMaximize : function(visible) {
						if (this.rendered) {
							this.down('tool[type=maximize]').setVisible(visible);
							this.down('tool[type=restore]').setVisible(!visible);
						}
					},

					/**
					 * 将当前选中的列表方案中的所有字段值写到store的proxy的extraParams中去，如果有筛选值，那就在这些字段里进行筛选
					 */
					setColumnsNameToStoreExtraParams : function() {
						var columnsName = [];
						for ( var i in this.columns) {
							if (this.columns[i].columns)
								for ( var j in this.columns[i].columns)
									columnsName.push(this.columns[i].columns[j].dataIndex);
							else
								columnsName.push(this.columns[i].dataIndex);
						}
						Ext.apply(this.store.extraParams, {
							schemeOrder : this.schemeOrder,
							columns : columnsName.join(',')
						});
					},

					// 检查是不是有超过一个字段是可以小计的，如果是在分类汇总的时候，加入小计列
					getColumnsSummaryCount : function() {
						var c = 0;
						for ( var i in this.columns) {
							if (this.columns[i].columns) {
								for ( var j in this.columns[i].columns)
									if (this.columns[i].columns[j].hasSummary)
										c += 1;
							} else if (this.columns[i].hasSummary)
								c += 1;
						}
						return c;
					},

					// 刷新数据，但是不要刷新已有的form detail,等
					refreshWithSilent : function() {
						var me = this;
						if (me.silent) // 正处在刷新当中
							return;
						this.silent = true;
						this.getStore().reload({
							callback : function() {
								me.silent = false;
							}
						});
					},

					// 数据刷新的时候，重新选择当前的选中行，不然的话，当前的选中行的数据没有改变，这是一个extjs 的bug
					reselectSelection : function() {
						if (this.getSelectionModel().getSelection().length == 0)
							return null;
						var keyfield = this.module.tf_primaryKey;
						var keyid = this.getSelectionModel().getSelection()[0]
								.get(keyfield);
						var me = this;
						this.getSelectionModel().deselectAll();
						this.getStore().each(function(record) {
							if (record.get(keyfield) == keyid) {
								me.getSelectionModel().select(record);
								return false;
							}
						});
					},

					/**
					 * 取得选中的记录条数
					 * 
					 * @return {}
					 */
					getSelectionCount : function() {
						return this.getSelectionModel().getSelection().length;
					},

					/**
					 * 取得选中的记录
					 * 
					 * @return {}
					 */
					getSelection : function(action) {
						return this.getSelectionModel().getSelection();
					},

					/**
					 * 取得选中的记录的titletpls
					 * 
					 * @return {}
					 */
					getSelectionTitleTpl : function(action) {
						var result = [];
						Ext.each(this.getSelectionModel().getSelection(), function(model) {
							result.push(model.getTitleTpl() ? model.getTitleTpl() : model
									.getIdValue());
						});
						return result;
					},

					/**
					 * 取得选中的记录的ids
					 * 
					 * @return {}
					 */
					getSelectionIds : function(action) {
						var result = [];
						Ext.each(this.getSelectionModel().getSelection(), function(model) {
							result.push(model.getIdValue());
						});
						return result;
					},

					/**
					 * 刷新当前选中的那条记录
					 */
					refreshSelectedRecord : function() {
						if (this.getSelectionModel().getSelection().length == 0)
							return null;
						var r = this.getSelectionModel().getSelection()[0];
						var me = this;
						this.module.model.load(r.getIdValue(), {
							success : function(record, operation, success) {
								// success中的record中返回的raw 数据，是字符串，没有经过decode,要自己转成对象
								var returnModel = Ext.create(me.module.model, Ext
										.decode(record.raw));
								r.fields.each(function(field) {
									if (r.get(field.name) != returnModel.get(field.name)) {
										r.set(field.name, returnModel.get(field.name));
									}
								});
								r.commit();
							}
						});
					},

					/**
					 * 刷新store ,导航栏，以及和他相关的模块
					 */
					refreshAll : function() {
						this.store.reload();
						this.up('modulepanel').refreshNavigate();
						if (this.module.tf_linkedModule)
							app.modules.refreshModuleGrid(this.module.tf_linkedModule);

					},

					/**
					 * 返回当前grid的选中的第一条记录，如果没有选择，则显示一个警告信息
					 */
					getFirstSelectedRecord : function(action) {
						if (this.getSelectionModel().getSelection().length == 0) {
							Ext.toastWarn("请先选择一条记录,然后再执行此操作！");
							return null;
						}
						var record = this.getSelectionModel().getSelection()[0];

						return record;
					},

					// 选择下一条记录
					selectNextRecord : function() {
						var sm = this.getSelectionModel();
						if (sm.getCount() == 0) {
							if (this.store.getCount() > 0)
								sm.select(this.store.getAt(0));
							else
								Ext.toastWarn('当前列表中没有可显示的记录!');
						} else {
							var index = this.store.indexOf(sm.getSelection()[0]);

							if (index + ((this.store.currentPage - 1) * this.store.pageSize) == this.store
									.getTotalCount() - 1)
								Ext.toastWarn('已经是当前列表的最后一条记录!');
							else {
								if (this.store.buffered) {
									sm.select(this.store.getAt(index + 1));
								} else {
									// 如果是最后一页以前的最后一条记录，那么就往下翻一页
									if (index
											+ ((this.store.currentPage - 1) * this.store.pageSize) == this.store.currentPage
											* this.store.pageSize - 1) {
										this.store.nextPage({
											scope : this,
											callback : function(records, operation, success) {
												if (records.length > 0)
													// 翻页过后，选中第一条
													sm.select(records[0]);
											}
										});
									} else
										sm.select(this.store.getAt(index + 1));
								}
							}
						}
					},

					getGridSettingMenu : function() {
						if (!this.gridSettingMenu)
							this.gridSettingMenu = Ext.create(
									'app.module.widget.GridSettingMenu', {
										modulegrid : this
									});
						return this.gridSettingMenu;
					},

					// 选择上一条记录
					selectPriorRecord : function() {
						var sm = this.getSelectionModel();
						if (sm.getCount() == 0) {
							if (this.store.getCount() > 0) {
								sm.select(this.store.getAt(0));
								return true;
							} else {
								Ext.toastWarn('当前列表中没有可显示的记录!');
								return false;
							}
						} else {
							var index = this.store.indexOf(sm.getSelection()[0]);

							if (index + ((this.store.currentPage - 1) * this.store.pageSize) == 0) {
								Ext.toastWarn('已经是当前列表的第一条记录!');
								return false;
							} else {
								if (this.store.buffered) {
									sm.select(this.store.getAt(index - 1));
									return true;
								} else {
									// 如果是第二页以后的第一条记录，那么就往上翻一页
									if (index == 0) {
										this.store.previousPage({
											scope : this,
											callback : function(records, operation, success) {
												if (records.length > 0)
													// 翻页过后，选中最后一条
													sm.select(records[records.length - 1]);
											}
										});
									} else
										sm.select(this.store.getAt(index - 1));
								}
							}
						}
					},

					autoSizeAllColumn : function() {
						var me = this;
						Ext.suspendLayouts();
						Ext.Array.forEach(this.columnManager.getColumns(),
								function(column) {
									if (me.isVisible() && column.resizable) {
										column.autoSize();
									}
								});
						Ext.resumeLayouts(true);
					}

				});