/**
 * 
 * merge level=80
 * 
 * 附件的控制器
 */
Ext.define('app.controller.report.Report', {
	extend : 'Ext.app.Controller',

	requires : [ 'app.report.SelectConditionWindow', 'app.lib.TreeSearchField',
			'app.report.ConditionTree', 'app.report.ConditionListGrid',
			'app.report.resultgrid.ResultListGrid',
			'app.report.widget.ConditionSelectButton',
			'app.report.widget.BaseModuleSelectMenu',
			'app.report.widget.YearMonthSelectMenu',
			'app.module.widget.column.AttachmentNumberColumn',
			'app.module.widget.column.AuditingActionColumn',
			'app.module.widget.column.PayoutActionColumn',
			'app.module.widget.column.ApproveActionColumn', 'app.lib.ToggleSlide',
			'app.report.selectfields.SelectedFieldsTree',
			'app.report.navigate.ConditionNavigateTree'

	],

	init : function() {

		this.control({

			'mainreport' : {

				reportChange : function(mainreport, reportId, reportText) {
					mainreport.setReportId(reportId);
					mainreport.setReportText(reportText);
					mainreport.down('button#save').disable();

					if (mainreport.canEditorDelete())
						mainreport.down('button#delete').enable();
					else
						mainreport.down('button#delete').disable();

					// 报表换掉了，去取得 selectfields 中要用到的数据
					Ext.Ajax.request({
						url : 'report/getreportinfo.do',
						params : {
							reportId : mainreport.getReportId()
						},
						success : function(response) {
							// {"baseModuleName":"Agreement","allModules":["Global","Agreement"],
							// "groups":[{"groupOrder":10,"groupTitle":"默认组",
							// "fields":[{"moduleName":"Agreement","fieldId":20100020,"fieldTitle":"标段","condition":"null"}
							var value = Ext.decode(response.responseText, true);
							mainreport.reportChange(value.allModules, value.baseModuleName,
									value.groups, value.isShowTotal, Ext.decode(
											value.groupFields, true), value.groupShowDetail);
						}
					});
				},

				dateSectionChanged : function(mainreport, dateSection, dateField) {
					if (dateField)
						mainreport.setDateSection(dateSection, dateField);
					else
						mainreport.setBaseModuleDateSection(dateSection);
				}

			},

			'mainreport reportselectcombo' : {
				change : function(combo, newValue, oldValue) {
					if (newValue)
						combo.up('mainreport').fireEvent('reportChange',
								combo.up('mainreport'), combo.getValue(), combo.getRawValue());
				}
			},

			// 选择基础模块后，改变报表主控中的 baseModuleName,去刷新页面
			'mainreport basemoduleselectmenu menuitem' : {
				click : function(item) {

					var mainReport = item.up('mainreport');
					if (mainReport.canEditorDelete())
						mainReport.down('button#save').enable();

					mainReport.setBaseModuleName(item.moduleName);
					mainReport.setGroupFields([]);
					mainReport.setGroupShowDetail(false);
					mainReport.refreshAll();
				}
			},

			// 选择了新增分组的 ＋ 按钮
			'mainreport resultlistgridtoolbar button#newGroup' : {
				click : function(button, e) {
					var toolbar = button.up('toolbar');
					var mainReport = toolbar.up('mainreport');
					var menu = Ext.createWidget('menu', {
						mainReport : mainReport, // 将 mainReport 加进去，不然执了找不到
						// mainreport
						items : toolbar.getCanSelectedMenu(
								mainReport.getBaseModule().groupFieldDefines, mainReport
										.getGroupFields()).menu
					});
					menu.show();
					menu.setXY([
							Ext.Array.min([ e.browserEvent.clientX,
									document.body.clientWidth - 200 ]),
							Ext.Array.min([ e.browserEvent.clientY,
									document.body.clientHeight - 250 ]) ]);
				}
			},

			// 选择了分组菜单里面的菜单项，可以是取消
			'menuitem[groupMenuItem]' : {
				click : function(menuitem) {
					var mainReport = menuitem.up('mainreport');
					if (mainReport == null)
						mainReport = menuitem.ownerCt.mainReport; // 新增的时候的弹出菜单，没有
					// up('mainreport')
					if (mainReport.canEditorDelete())
						mainReport.down('button#save').enable();
					var level = menuitem.level;
					if (menuitem.itemId == 'cancelGroup') {
						mainReport.getGroupFields().splice(level - 1, 1); // 取消某一级的分组

					} else if (menuitem.addGroupLevel == true) {
						// 新增加一级分组
						mainReport.getGroupFields().push({
							moduleName : menuitem.moduleName,
							fieldId : menuitem.fieldId
						});
					} else {
						// 改变当前级的分组设置,删除当前的分组，加入新的分组
						mainReport.getGroupFields().splice(level - 1, 1, {
							moduleName : menuitem.moduleName,
							fieldId : menuitem.fieldId
						});
					}
					mainReport.reCreateResultGrid();
				}
			},

			'mainreport toggleslide#isShowDetail' : {
				change : function(slide, value) {
					var mainReport = slide.up('mainreport');
					if (mainReport.canEditorDelete())
						mainReport.down('button#save').enable();
					mainReport.setGroupShowDetail(value);
					mainReport.reCreateResultGrid();
				}
			},

			'mainreport toggleslide#isshowtotal' : {
				change : function(slide, value) {
					var mainReport = slide.up('mainreport').setIsShowTotal(value);
					if (mainReport.canEditorDelete())
						mainReport.down('button#save').enable();
					if (mainReport.getGroupFields().length == 0) {
						mainReport.down('resultlistgrid').getStore().removeAll(true);
						mainReport.down('resultlistgrid').getStore().loadPage(1);
					}
				}
			},

			// 是否是live grid
			'mainreport toggleslide#islivegrid' : {
				change : function(slide, value) {
					var mainReport = slide.up('mainreport').setIsLiveGrid(value);
					mainReport.reCreateResultGrid();
				}
			},

			'mainreport > toolbar > button#save' : {
				click : this.saveReportDefine
			},

			'mainreport > toolbar > button#saveas' : {
				click : this.saveReportDefineAs
			},

			'mainreport > toolbar > button#delete' : {
				click : this.deleteReport
			},

			'mainreport > toolbar > button#selectfields' : {
				// 定位到某个节点id，focusNodeId
				click : function(button, focusNodeId) {
					this.getController('report.SelectFields');
					var win = Ext.create('app.report.selectfields.SelectFieldsWindow', {
						mainReport : button.up('mainreport'),
						focusNodeId : focusNodeId
					});
					win.show();
				}

			},

			'button[searchConditionButton]' : {
				click : function(button) {
					var selectedValues = [];
					var thiscond = null;
					// store.findRecord 有bug ,Agreement 会等于 AgreementCode
					button.up('mainreport').down('conditionlistgrid').getStore().each(
							function(record) {
								if (record.get('conditionId') == button.condition.conditionId) {
									thiscond = record;
									return false;
								}
							});
					if (thiscond)
						selectedValues = thiscond.get('se_values');
					var win = Ext.create('app.report.SelectConditionWindow', {
						enterButton : button,
						title : button.condition.fulltext,
						icon : button.condition.icon,
						condition : button.condition,
						mainReport : button.up('mainreport'),
						selectedValues : selectedValues
					});
					win.show();
				}
			}
		});
	},

	saveReportDefine : function(button) {
		var mainReport = button.up('mainreport');
		var combo = mainReport.down('reportselectcombo');
		if (mainReport.canEditorDelete() == false) {
			Ext.toastErrorInfo('你无权修改查询方案『' + combo.getRawValue() + '』。');
			return;
		}
		Ext.Ajax.request({
			url : 'report/savereport.do',
			params : {
				reportId : mainReport.getReportId(),
				selectedFields : Ext.encode(mainReport.getSelectdGroupAndFields()),
				isShowTotal : mainReport.getIsShowTotal(),
				baseModuleName : mainReport.getBaseModuleName(),
				groupFields : Ext.encode(mainReport.getGroupFields()),
				groupShowDetail : mainReport.getGroupShowDetail()
			},
			success : function(response) {
				var result = Ext.decode(response.responseText, true);
				if (result.success) {
					Ext.toastInfo('综合查询方案『' + combo.getRawValue() + '』已保存。');
					button.disable();
				} else {
					// 删除失败
					Ext.MessageBox.show({
						title : '保存失败',
						msg : '保存失败<br/><br/>' + result.msg,
						buttons : Ext.MessageBox.OK,
						animateTarget : button.id,
						icon : Ext.MessageBox.ERROR
					});
				}
			}
		});
	},

	saveReportDefineAs : function(button) {
		var mainReport = button.up('mainreport');

		Ext.MessageBox.prompt('新增方案', '请输入新方案名称', function(btn, text) {
			if (btn == 'ok')
				Ext.Ajax.request({
					url : 'report/saveasreport.do',
					params : {
						reportId : null,
						reportGroupId : mainReport.reportGroup.reportGroupId,
						text : text,
						selectedFields : Ext.encode(mainReport.getSelectdGroupAndFields()),
						baseModuleName : mainReport.getBaseModuleName(),
						isShowTotal : mainReport.getIsShowTotal(),
						groupFields : Ext.encode(mainReport.getGroupFields()),
						groupShowDetail : mainReport.getGroupShowDetail()
					},
					success : function(response) {
						var result = Ext.decode(response.responseText, true);
						var combo = mainReport.down('reportselectcombo');

						var menode = combo.store.getRootNode().findChildBy(function(node) {
							if (node.data.text == '我的查询方案')
								return true;
						}, this, true);
						if (!menode)
							menode = combo.store.getRootNode().insertChild(0, {
								text : '我的查询方案',
								value : -1,
								disabled : true,
								expanded : true,
								leaf : false
							});
						menode.appendChild({
							value : result.tag,
							text : text,
							leaf : true,
							tag : 1
						});
						combo.setValue(result.tag);
						Ext.toastInfo('综合查询方案『' + text + '』已保存。');
					}
				});
		});
	},

	deleteReport : function(button) {
		var mainReport = button.up('mainreport');
		var combo = mainReport.down('reportselectcombo');
		if (mainReport.canEditorDelete() == false) {
			Ext.toastErrorInfo('你无权删除查询方案『' + combo.getRawValue() + '』。');
			return;
		}
		var t = '综合查询方案『' + combo.getRawValue() + '』';
		Ext.MessageBox.confirm('确定删除', '确定要删除' + t + '吗?', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : 'report/deletereport.do',
					params : {
						reportId : mainReport.getReportId()
					},
					success : function(response) {
						var result = Ext.decode(response.responseText, true);
						if (result.success) {
							Ext.toastInfo(t + '已被删除。');

							var node = combo.store.getRootNode().findChildBy(function(node) {
								if (node.data.value == combo.getValue())
									return true;
							}, this, true);

							node.remove(true);
							// 转换到第一个子节点的方案
							node = combo.store.getRootNode().findChildBy(function(node) {
								if (node.data.leaf == true)
									return true;
							}, this, true);

							combo.setValue(node.data.value);

						} else
							// 删除失败
							Ext.MessageBox.show({
								title : '删除失败',
								msg : '删除失败<br/><br/>' + result.msg,
								buttons : Ext.MessageBox.OK,
								animateTarget : button.id,
								icon : Ext.MessageBox.ERROR
							});
					}
				});
			}
		});
	}

});