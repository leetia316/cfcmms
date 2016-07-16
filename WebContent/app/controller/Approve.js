/**
 * 
 * merge level=80
 * 
 * 几种批量审批的控制器
 */
Ext.define('app.controller.Approve', {
	extend : 'Ext.app.Controller',

	views : [],

	init : function() {

		this.control({

					// 审批记录
					'approveform button#saveapprove' : {
						click : this.saveOrCancelApprove

					},
					// 取消审批记录
					'approveform button#cancelapprove' : {
						click : this.saveOrCancelApprove
					},

					// 批量审批当前页中选中的未审批的记录
					'modulepanel menuitem#approve_thisselection' : {
						click : this.approveThisSelection
					},

					// 批量审批当前页中所有未审批的记录
					'modulepanel menuitem#approve_thispage' : {
						click : this.approveThisPage
					},

					// 批量审批当前导航及筛选条件下的未审批的记录
					'modulepanel menuitem#approve_thiscondition' : {
						click : this.approve_thiscondition
					},

					// 批量审批所有的未审批的记录
					'modulepanel menuitem#approve_all' : {
						click : this.approve_all
					}

				})
	},

	approve_all : function(item) {
		var grid = item.up('modulegrid'), store = grid.getStore();

		var params = {
			moduleName : grid.module.tf_moduleName,
			parentFilter : store.extraParams.parentFilter
		};

		Ext.MessageBox.confirm('确定审批', '确定要自动审批我能审批的所有未审批的' + grid.module.tf_title
						+ '记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : 'moduleapprove/allapprove.do',
									params : params,
									success : function(response) {
										var info = Ext.decode(response.responseText, true);
										grid.store.reload();
										grid.up('modulepanel').refreshNavigate();
										Ext.toastInfo('批量自动审批完成,审批了 ' + info.msg + '条记录!');
									}
								})
					}
				})

	},
	approve_thiscondition : function(item) {
		var grid = item.up('modulegrid'), store = grid.getStore();

		var params = {
			moduleName : grid.module.tf_moduleName
		};
		for (var i in store.extraParams)
			params[i] = store.extraParams[i];
		Ext.each(store.filters.items, function(filter) {
					params[filter.property] = filter.value;
				})

		Ext.MessageBox.confirm('确定审批', '确定要自动审批当前导航值和筛选条件下的我能审批的'
						+ grid.module.tf_title + '记录吗？', function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : 'moduleapprove/thiscondition.do',
									params : params,
									success : function(response) {
										var info = Ext.decode(response.responseText, true);
										grid.store.reload();
										grid.up('modulepanel').refreshNavigate();
										Ext.toastInfo('批量自动审批完成,审批了 ' + info.msg + '条记录!');
									}
								})
					}
				})

	},

	approveThisSelection : function(item) {
		var grid = item.up('modulegrid'), store = grid.getStore(), ids = [], names = [];
		Ext.each(grid.getSelection(), function(record) {
					if (record.meCanApprove()) {
						ids.push(record.getIdValue());
						names.push(record.getNameValue());
					}
				});
		if (ids.length == 0) {
			Ext.toastWarn('当前选中的记录中没有可以审批的记录！');
			return;
		}
		Ext.MessageBox.confirm('确定审批', '确定要自动审批' + grid.module.tf_title
						+ '的以下记录吗？<br/><br/>' + names.join(', '), function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : 'moduleapprove/pagerecord.do',
									params : {
										moduleName : grid.module.tf_moduleName,
										ids : ids.join(",")
									},
									success : function(response) {
										var info = Ext.decode(response.responseText, true);
										grid.store.reload();
										grid.up('modulepanel').refreshNavigate();
										Ext.toastInfo('批量自动审批完成,审批了 ' + info.msg + '条记录!');
									}
								})
					}
				})
	},

	approveThisPage : function(item) {
		var grid = item.up('modulegrid'), store = grid.getStore(), ids = [], names = [];
		store.each(function(record) {
					if (record.meCanApprove()) {
						ids.push(record.getIdValue());
						names.push(record.getNameValue());
					}
				});
		if (ids.length == 0) {
			Ext.toastWarn('当前页面中没有未审批的记录！');
			return;
		}
		Ext.MessageBox.confirm('确定审批', '确定要自动审批' + grid.module.tf_title
						+ '的以下记录吗？<br/><br/>' + names.join(', '), function(btn) {
					if (btn == 'yes') {
						Ext.Ajax.request({
									url : 'moduleapprove/pagerecord.do',
									params : {
										moduleName : grid.module.tf_moduleName,
										ids : ids.join(",")
									},
									success : function(response) {
										var info = Ext.decode(response.responseText, true);
										grid.store.reload();
										grid.up('modulepanel').refreshNavigate();
										Ext.toastInfo('批量自动审批完成,审批了 ' + info.msg + '条记录!');
									}
								})
					}
				})
	},

	resetField : function(form, fn) {
		var f = form.getForm().findField(fn);
		if (f)
			f.setValue(null);
	},

	saveOrCancelApprove : function(button) {
		var me = this;
		var form = button.up('form');
		var operText = '已审批成功！', operType = 'approve';
		if (button.itemId == 'cancelapprove') {
			operText = '已取消审批！';
			operType = 'cancelapprove';
			var af = form.approveFields;
			this.resetField(form, af[0] + form.order);
			this.resetField(form, af[1] + form.order);
			this.resetField(form, af[2] + form.order);
			this.resetField(form, af[3] + form.order);
			// 审批的最终结果由 服务器端去保存
		}
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
			fields.each(function(field) {
						if (model.get(field.name) != field.getValue()) {
							model.set(field.name, field.getValue())
						}
					});
			model.phantom = false; // 服务器上已有
			var text = form.module.tf_title + ":【" + model.getTitleTpl() + '】';
			model.getProxy().extraParams.operType = operType;
			model.save({
				success : function(record, operation, success) {
					myMask.hide();
					form.moduleGrid.up('modulepanel').refreshNavigate();
					delete model.proxy.extraParams.operType;
					delete model.proxy.extraParams.oldid;
					var result = Ext.decode(operation.getResponse().responseText);
					if (result.resultCode == 0) {
						Ext.toastInfo(text + operText);
						// 从服务器返回的增加过后的数据，要重新load一次，会加入主键和一些其他做过的字段的改变
						var returnModel = Ext.create(form.module.model, Ext
										.decode(result.records[0]));
						var gridModel = form.moduleGrid.getSelectionModel().getSelection()[0];;
						// store增加了一个记录后，store.getTotalCount()的值不会改变，重新刷新才会，而且新增加的值的recno是undefined
						Ext.each(form.module.tablefields, function(field) {
									if (gridModel.get(field.name) != returnModel.get(field.name)) {
										gridModel.set(field.name, returnModel.get(field.name))
									}
								});
						gridModel.commit(); // commit表示数据已经在服务器上更新过了
						form.initForm();
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

	getResponseError : function(form, errors) {
		var errorMessage = '';
		for (var fieldname in errors) {
			var field = form.getForm().findField(fieldname);
			errorMessage = errorMessage + (field ? field.getFieldLabel() : field)
					+ " : " + errors[fieldname] + '</br>';
		}
		return errorMessage;
	}

});
