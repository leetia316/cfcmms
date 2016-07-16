/**
 * 
 * merge level=80
 * 
 * 一个导航树navigatetree的所有事件控制
 */

Ext.define('app.controller.GridNavigateTree', {
	extend : 'Ext.app.Controller',

	init : function() {

		this.control({

					'navigatetree treeview' : {
						beforedrop : this.gridDropToTree,
						nodedragover : this.gridnodedragover
					},

					'navigatetree' : {
						selectionchange : this.navigateSelectionChange
					},

					// 设置多层的属性层叠显示
					'navigatetree tool[type=pin]' : {
						click : function(tool) {
							tool.setVisible(false);
							tool.ownerCt.down('tool[type=unpin]').setVisible(true);
							var tree = tool.up('navigatetree');
							tree.setCascading(false);
						}
					},

					// 设置多层的属性并排显示
					'navigatetree tool[type=unpin]' : {
						click : function(tool) {
							tool.setVisible(false);
							tool.ownerCt.down('tool[type=pin]').setVisible(true);
							var tree = tool.up('navigatetree');
							tree.setCascading(true);
						}
					},

					// 全部折叠
					'navigatetree tool[type=collapse]' : {
						click : function(tool) {
							tool.up('navigatetree').collapseAll();
							tool.up('navigatetree').setLevel(1);
						}
					},

					// 展开至下一级
					'navigatetree tool[type=expand]' : {
						click : function(tool) {
							tool.up('navigatetree').expandToNextLevel();
						}
					}

				});
	},

	/**
	 * 将grid中的记录拖动到navigate中的项目上时，用来改变记录的某个导航字段的值
	 * 
	 * @param {}
	 *          targetNode
	 * @param {}
	 *          data
	 * @param {}
	 *          overModel tree item 的 model
	 * @param {}
	 *          dropPosition
	 */
	gridDropToTree : function(targetNode, data, overModel, dropPosition,
			dropHandlers) {
				
		var treeModuleName = overModel.raw.moduleName, gridModule = data.view.ownerCt.ownerCt.module;
			
		var record = data.records[0].data;

		var field;
		var sameModel = treeModuleName == gridModule.tf_moduleName;

		// 由于extjs5的改动，在将grid拖到tree以后，会将model要新增到tree中，因此需要在此处将drop
		// cancel掉，然后再询问是否要确认拖动。如无此条语句，则会报错
		dropHandlers.cancelDrop();
    // 由于text 中有<span> 记录数 </span> 在显示信息的时候把记录数的显示去掉
		var changetotext = overModel.raw.text, pos = changetotext.indexOf('<span');
		if (pos != -1)
			changetotext = changetotext.substr(0, changetotext.indexOf('<span'));

		Ext.MessageBox.confirm('确认修改', '确定要将' + gridModule.tf_title + '『'
						+ record[gridModule.tf_nameFields] + '』的'
						+ overModel.raw.fieldtitle + '改为“<span style="color:blue;">' + changetotext + '</span>”吗？',
				function(btn) {
					if (btn === 'yes') {
						// 仿照saveedit 写如下提交过程
						var model = Ext.create(gridModule.model, {});
						// 写入主键值
						model.set(gridModule.tf_primaryKey,
								record[gridModule.tf_primaryKey]);
						// 写入修改过的字段值
						if (sameModel) {
							model.set(overModel.raw.fieldname, overModel.raw.fieldvalue);
						} else {
							Ext.each(gridModule.tf_fields, function(f) {
										if (f.tf_fieldType == treeModuleName) {
											field = f;
											return false;
										}
									});
							model.set(field.manytoone_IdName, overModel.raw.fieldvalue);
						}
						model.phantom = false; // 服务器上已有
						var text = gridModule.tf_title + ":【"
								+ record[gridModule.tf_nameFields] + '】';
						model.save({
									success : function(record, operation, success) {
										delete model.proxy.extraParams.oldid;
										var result = Ext
												.decode(operation.getResponse().responseText);
										if (result.resultCode == 0) {
											Ext.toastInfo(text + '已被成功修改！');
											// 从服务器返回的增加过后的数据，要重新load一次，会加入主键和一些其他做过的字段的改变
											var returnModel = Ext.create(gridModule.model, Ext
															.decode(result.records[0]));
											var gridModel = data.view.ownerCt.getSelectionModel()
													.getSelection()[0];

											Ext.each(gridModel.getFields(), function(field) {
														if (gridModel.get(field.name) != returnModel
																.get(field.name)) {
															gridModel.set(field.name, returnModel
																			.get(field.name));
														}
													});

											gridModel.commit(); // commit表示数据已经在服务器上更新过了
											data.view.ownerCt.up('modulepanel')
													.down('modulenavigate').refreshNavigateTree();
										} else {
											// 新增失败,将失败的内容写到各个字段的错误中去
											Ext.MessageBox.show({
														title : '记录修改失败',
														msg : text + '修改失败<br/><br/>' + result.errorMessage,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.ERROR
													});
										}
									}
								});
					}
				});
	},

	gridnodedragover : function(targetNode, position, dragData, e, eOpts) {

		var modulepanel = dragData.view.up('modulepanel');
		// 此人不具有直接修改的权限
		if (!modulepanel.module.tf_userRole.tf_allowEditDirect) {
			if (!modulepanel.down('modulegrid').getFirstSelectedRecord().canEdit())
				return false;
		}

		// 拖动进的treenode的moduleName
		var treeModuleName = targetNode.raw.moduleName, gridModule = modulepanel.module;
		var sameModel = treeModuleName == gridModule.tf_moduleName;
		// 如果是相同的model表示，当前导航这段是本模块的自有字段，检查此字段，如果是可修改，并且是字符串的才可以拖放
		var field;
		if (sameModel) {
			Ext.each(gridModule.tf_fields, function(f) {
						if (f.tf_fieldName == targetNode.raw.fieldname) {
							field = f;
							return false;
						}
					});
			return (field ? true : false)
					&& (field.tf_fieldType == 'String' || field.tf_fieldType == 'Integer')
					&& field.tf_allowEdit == true;
		} else {
			// 判断 treeModuleName 是不是 拖动来的grid记录的直接父模块，如果是并且允许修改，才可以修改
			Ext.each(gridModule.tf_fields, function(f) {
						if (f.tf_fieldType == treeModuleName) {
							field = f;
							return false;
						}
					});
			if (field && field.tf_allowEdit == true) {
				if (field.allowParentValue) // 如果此字段可以选择层级模块的非末级科目
					return true;
				else
					return targetNode.data.leaf;
			} else
				return false;
		}

	},

	/**
	 * grid导航树单击以后，将条件加到store的extraParams中去，然后刷新数据
	 */
	navigateSelectionChange : function(selectionModel, model) {
		if (!(model && model.length > 0))
			return;
		var tree = selectionModel.view.ownerCt, record = model[0];
		var navigate = tree.up('modulenavigate');
		var grid = tree.up('modulepanel').down('modulegrid');
		if (record.raw.fieldvalue) {
			var navigateValue = {
				moduleName : record.raw.moduleName,
				tableAsName : record.raw.tableAsName,
				primarykey : record.raw.fieldname,
				fieldtitle : record.raw.fieldtitle,
				equalsValue : record.raw.fieldvalue,
				equalsMethod : record.raw.equalsMethod,
				text : record.raw.text,
				isCodeLevel : record.raw.isCodeLevel
			};
			// console.log(tree.path);
			// console.log(navigateValue);

			navigate.addNavigateValue(tree.path, navigateValue);
		} else
			navigate.addNavigateValue(tree.path, null);
	}

});