/**
 * 
 * merge level=80
 * 
 * 综合查询中选择字段的窗口的事件
 * 
 */

Ext.define('app.controller.report.NavigateSelectFieldsTree', {
	extend : 'Ext.app.Controller',
	requires : ['app.report.selectfields.FieldConditionWindow'],

	init : function() {

		this.control({

					'mainreport selectedfieldstree' : {

						itemclick : function(tree, record, item, index, e, eOpts) {
							// console.log(tree);
							// console.log(record);
							// console.log(e);
							// console.log(eOpts);

							if (e.target.localName == 'img') {
								if (e.target.src.indexOf('setting.png') != -1) {
									var module = app.modules.getModule(record.data.moduleName);
									var field = module.getFieldDefine(record.data.id);
									var mainreport = tree.ownerCt.up('mainreport');
									if (field.tf_fieldType == 'Date') {
										var baseModuleDateField = mainreport
												.getBaseModuleDateField();

										// 如果是当前基准模块的日期字段，弹出 查询期间的年度菜单
										if (baseModuleDateField
												&& baseModuleDateField.fieldId == record.data.id)
											mainreport.down('dateselectbutton').showMenu();
										else {
											// 如果是 dateSelectButton 则， menu.dateField
											// 为空，如果是从
											// navigateSelectedfieldsTree中传过来的，则
											// 要去执行 mainreport 的 dataSectionchanged 事件，而 dataField
											// 会作为参数传进去。表示不是基准模块的的定义。
											var menu = Ext.createWidget('yearmonthselectmenu', {
														target : tree.ownerCt,
														dateField : record
													});
											menu.show();
											menu.setXY([
													Ext.Array.min([e.browserEvent.clientX,
															document.body.clientWidth - 200]),
													Ext.Array.min([e.browserEvent.clientY,
															document.body.clientHeight - 250])]);
										}
									} else { // 不是日期字段，显示字段设置窗口
										var win = Ext.widget('fieldconditionwindow', {
													tree : mainreport.down('selectedfieldstree'),
													treeNode : record
												});
										win.show();
										win.setXY([
												Ext.Array.min([e.browserEvent.clientX,
														document.body.clientWidth - 200]),
												Ext.Array.min([e.browserEvent.clientY,
														document.body.clientHeight - 250])]);
									}
								} else if (e.target.src.indexOf('edit.png') != -1) {
									Ext.MessageBox.prompt('分组名称', '请输入字段分组名称',
											function(btn, text) {
												if (btn == 'ok') {
													record.raw.title = text;
													record.set({
																text : text + tree.ownerCt.editIcon
															});
													record.save();
												}
											}, this, false, record.raw.title);
								}
							}
						},

						// navigateseldctfieldstree 中选择了一个日期，返回结果后要去处理
						dateSectionChanged : function(tree, dateSection, field) {
							if (dateSection.sectiontype == 'all')
								field.raw.condition = null;
							else
								field.raw.condition = Ext.encode(dateSection);
							var mainReport = tree.up('mainreport');
							Ext.Array.each(mainReport.selectdGroupAndFields, function(group) {
										Ext.Array.each(group.fields, function(gf) {
													if (gf.fieldId == field.raw.id) {
														gf.condition = field.raw.condition;
														return false;
													}
												});
									});
							mainReport.refreshConditionAndRecreateResult();
						}
					},

					'selectedfieldstree' : {

						groupandfieldschanged : function(tree, groupandfields) {

							if (tree.itemId == 'navigatefields') {
								if (tree.up('reportnavigate').collapsed) {
									// 如果是折叠的，那么就把改变后的group and fields 存下来
									tree.selectedGroupAndFields = groupandfields;
									return;
								} else {
									tree.selectedGroupAndFields = null;
								}
							}
							tree.getRootNode().eachChild(function(group) {
										group.removeAll(false);
									});
							tree.getRootNode().removeAll(false);

							Ext.Array.forEach(groupandfields, function(group) {
										groupnode = tree.getRootNode().appendChild({
											title : group.groupTitle,
											text : group.groupTitle
													+ (tree.canEditGroupText ? tree.editIcon : ''),
											leaf : false,
											expanded : true
										});
										Ext.Array.forEach(group.fields, function(field) {
													var node = groupnode.appendChild({
																moduleName : field.moduleName,
																id : '' + field.fieldId,
																value : '' + field.fieldId,
																cls : getTypeClass(field.fieldType),
																title : field.fieldTitle,
																text : field.fieldTitle
																		+ ((field.condition || field.aggregate)
																				? ' <span class="hascondition">✽</span>'
																				: '') + tree.setIcon1,
																condition : field.condition,
																aggregate : field.aggregate,
																fieldType : field.fieldType,
																leaf : true
															});
												});
									});
						}
					},

					'selectedfieldstree treeview' : {
						nodedragover : function(targetNode, position, dragData, e, eOpts) {
							if (dragData.records.length == 1) {
								var node = dragData.records[0];
								if (node.data.leaf) {
									// 如果是叶结点，不能放在根结点下
									if (targetNode.internalId == 'root')
										return false;
									// 不可以放在分组结点的前或后
									if ((position == 'before' || position == 'after')
											&& targetNode.hasChildNodes())
										return false;
								} else {
									// 如果是分组节点，只能放在平级的前或后
									if (position == 'append' || targetNode.data.leaf)
										return false;
								}
							}
						}
					}
				});
	}
});
