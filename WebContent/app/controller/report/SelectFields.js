/**
 * 
 * merge level=80
 * 
 * 综合查询中选择字段的窗口的事件
 * 
 */

Ext.define('app.controller.report.SelectFields', {
	extend : 'Ext.app.Controller',

	requires : ['app.report.selectfields.CanSelectedFieldsTree',
			'app.report.selectfields.SelectedFieldsTree',
			'app.report.selectfields.FieldConditionForm',
			'app.report.selectfields.GroupAndModulePanel'],

	init : function() {

		this.control({

			'selectfieldswindow' : {
				show : function(window) {

					// 窗口显示的时候，从mainreport中读取当前的字段组和字段，可以是mainreport从后台取得的，也可以是我这里保存的数据，但是
					// 还没有保存到数据库里的
					var source = window.mainReport.getSelectdGroupAndFields();
					var selectedTree = window.down('selectedfieldstree');
					window.setTitle('字段选择及附加条件『' + window.mainReport.reportText + '』');
					selectedTree.fireEvent('groupandfieldschanged', selectedTree, source);
					window.refreshStatusBar();
					if (window.focusNodeId) {
						selectedTree.getRootNode().eachChild(function(group) {
									group.eachChild(function(field) {
												if (field.data.value == window.focusNodeId) {
													window.down('fieldconditionform').expand();
													setTimeout(function() {
																selectedTree.getSelectionModel().select(field);
															}, 500);
												}
											});
								});
					}
				}
			},

			// 确定返回按钮
			'selectfieldswindow toolbar button#saveselectedfields' : {
				click : function(button) {
					var window = button.up('window');
					var tree = window.down('selectedfieldstree');
					var result = getGroupAndFieldsWithTree(tree);
					if (result.length == 0) {
						Ext.toastWarn('请至少选择一个字段！');
						return;
					}
					Ext.Ajax.request({
								url : 'report/validselectedfields.do',
								params : {
									fields : Ext.encode(result)
								},
								success : function(response) {
									var r = Ext.decode(response.responseText, true);
									if (r.success) {
										var mainReport = window.mainReport;
										if (mainReport.canEditorDelete())
											mainReport.down('button#save').enable();
										// 如果当前的基准模块在所有有字段的模块中不存在，则换掉基准模块
										var baseModuleName = mainReport.getBaseModuleName();
										if (r.msg.allModules.indexOf(baseModuleName) == -1)
											baseModuleName = r.msg.baseModuleName;
										mainReport.reportChange(r.msg.allModules, baseModuleName,
												r.msg.groups , null , [] , false);
										window.close();
									} else
										Ext.MessageBox.show({
													title : '选择错误',
													msg : r.msg,
													buttons : Ext.MessageBox.OK,
													animateTarget : button.id,
													icon : Ext.MessageBox.ERROR
												});
								}
							});
				}
			},
			// 有字段值改变时，将其更新到选中的字段中去
			'selectfieldswindow fieldconditionform field' : {
				change : function(field, newValue, oldValue) {
					field.up('form').fieldchange();
				}
			},

			// field 条件form 上面的清除所有条件tool按钮
			'selectfieldswindow fieldconditionform tool[type=refresh]' : {
				click : function(tool) {
					var form = tool.up('form');
					form.getForm().resetToNull();
					form.fieldchange();
				}
			},

			// 清除所有字段，重新选择的按钮
			'selectfieldswindow toolbar button#clearfields' : {

				click : function(button) {
					var me = button.up('window');
					var selected = me.down('selectedfieldstree');
					selected.getRootNode().removeAll(false);
					me.syncCanSelected();

					var canSelected = me.down('canselectedfieldstree');
					canSelected.getRootNode().cascadeBy(function(node) {
								node.set({
											checked : false
										});
							});
					me.refreshStatusBar();
				}
			},

			// 分组和模块选择的panel,在创建的时候需要加入从系统中读取得到的分组和模块
			'groupandmodulepanel' : {
				render : function(panel) {
					// 加入所有的模块分组 ，分组下面是一个tree,里面有所有的模块
					console.log(app.system);
					Ext.Array.forEach(app.system.groupsAndmodules, function(group) {
								panel.add({
											title : group.text,
											xtype : 'treepanel',
											rootVisible : false,
											store : Ext.create('Ext.data.TreeStore', {
														root : {
															expanded : true,
															children : group.children
														}
													})
										});
							});
				}
			},
			// 每一个模块的treeitem被选中的时候，需要转换到相应的模块
			'groupandmodulepanel treepanel' : {
				selectionchange : function(tree, selected) {
					if (!(selected && selected.length > 0))
						return;
					tree.view.panel.up('window').down('canselectedfieldstree')
							.setModuleName(selected[0].raw.value);
				}
			},

			'selectfieldswindow selectedfieldstree' : {

				// 选择了一个字段以后，把左面的 group 放到相应的记录，中间的可选择字段，也换成当前选中字段的module

				selectionchange : function(tree, selected) {
					if (!(selected && selected.length > 0))
						return;
					var mname = selected[0].data.moduleName;
					var window = tree.view.panel.up('window');
					if (mname && selected[0].data.leaf) {
						var canSelected = window.down('canselectedfieldstree');
						if (canSelected.moduleName != mname)
							window.down('panel#tablegroup').anchorToModule(mname);
						else
							window.syncCanSelectedFocusNode();
						// 更新 conditionform
						window.syncConditionForm(selected[0]);
					}
				},

				itemclick : function(tree, record, item, index, e, eOpts) {
					if (e.target.localName == 'img'
							&& e.target.src.indexOf('edit.png') != -1) {
						Ext.MessageBox.prompt('分组名称', '请输入字段分组名称', function(btn, text) {
									if (btn == 'ok') {
										record.raw.title = text;
										record.set({
													text : text
															+ (tree.ownerCt.canEditGroupText
																	? tree.ownerCt.editIcon
																	: '')
												});
										record.save();
									}
								}, this, false, record.raw.title);
					}
				},

				itemdblclick : function(tree, record, item) {
					if (record.parentNode != null && record.hasChildNodes()) {
					} else if (record.data.leaf) {
						var form = tree.up('window').down('fieldconditionform');
						if (!form.collapsed)
							form.collapse();
						else
							form.expand();
					}
				}

			},

			// 可被选择的字段树
			'canselectedfieldstree' : {

				checkchange : function(node, checked) {
					setChildChecked(node, checked);
					setParentChecked(node, checked);
					node.getOwnerTree().up('window').syncSelected();
				},

				load : function(store, node, records) {
					var tree = store.ownerTree;
					tree.expandAll();
					tree.up('window').syncCanSelected();
				}

			}

		});
	}
});
