/**
 * 
 * merge level=80
 * 
 * 控制查询结果 result list grid 中的导出
 * 
 */

Ext.define('app.controller.report.ResultListGridToolbar', {
			extend : 'Ext.app.Controller',

			views : [],

			init : function() {

				this.control({

							// 打印
							'resultlistgridtoolbar splitbutton#printExcel' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, false, true, 'print');
								}
							},
							'resultlistgridtoolbar menuitem#printexcel' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, false, true, 'print');
								}
							},
							'resultlistgridtoolbar menuitem#printexcelwanyuan' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, true, true, 'print');
								}
							},

							'resultlistgridtoolbar menuitem#exportexcelwanyuan' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, true, false);
								}
							},

							'resultlistgridtoolbar splitbutton#exportExcel' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, false, false);
								}
							},
							'resultlistgridtoolbar menuitem#exportexcel' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, false, false);
								}
							},

							'resultlistgridtoolbar menuitem#exportpdf' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, false, true);
								}
							},

							'resultlistgridtoolbar menuitem#exportpdfwanyuan' : {
								click : function(menuitem) {
									this.exportExcel(menuitem, true, true);
								}
							},

							'resultlistgridtoolbar button#chart' : {
								click : function(menuitem) {

									this.getController('report.Chart');

									var report = menuitem.ownerCt.up('mainreport');
									var win = Ext.widget('reportchartwindow', {
												mainReport : report
											});
									win.show();
								}
							}
						});
			},

			exportExcel : function(menuitem, wanyuan, ispdf, print) {

				var report = menuitem.ownerCt.up('mainreport');
				var conditionList = report.down('conditionlistgrid');
				var resultGrid = report.down('resultlistgrid');
				var store = resultGrid.getStore();
				var param = {
					iswanyuan : wanyuan,
					ispdf : !!ispdf,
					print : !!print
				};

				// 查找在 grid 中 隐藏的字段
				var hiddenColumns = [];
				var items = resultGrid.lockedGrid.getView().headerCt.items.items;
				items = items
						.concat(resultGrid.normalGrid.getView().headerCt.items.items);
				Ext.Array.each(items, function(group) {
							if (group.items.length > 0)
								Ext.Array.each(group.items.items, function(column) {
											if (column && column.isHidden())
												hiddenColumns.push(column.dataIndex);
										});

						});
				param.hiddenColumns = hiddenColumns.join(',');
				Ext.apply(param, store.proxy.extraParams);
				if (store.getFilters().length > 0)
					param.query = store.filters.items[0].value;
				if (store.sorters.length > 0) {
					param.sort = Ext.encode([{
								direction : store.sorters.items[0].direction,
								property : store.sorters.items[0].property
							}]);
				}
				var url = Ext.String.format('report/downloadresult.do?{0}', Ext.Object
								.toQueryString(param));
				if (print) {
					var printWindow = window.open();
					printWindow.document.write('<html><head>');
					printWindow.document.write('<title>' + ''
							+ report.down('reportselectcombo').getRawValue() + '打印</title>');
					printWindow.document.write('</head><body>');
					printWindow.document.write('<iframe src="' + url
							+ '" style="width:100% ; height: 100%" frameborder="0"></iframe>');
					printWindow.document.write('</body></html>');
					printWindow.document.close();
				}
				else
					window.location.href = url;
			}
		});
