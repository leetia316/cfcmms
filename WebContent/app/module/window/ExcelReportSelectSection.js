/**
 * merge level=50
 * 
 * 用来选择excel report 下载时选择年度和月度，或季度
 * 
 */

Ext.define('app.module.window.ExcelReportSelectSection', {

	extend : 'Ext.window.Window',
	layout : 'border',
	icon : 'images/button/report.png',
	// height : 150,
	layout : 'fit',
	width : 400,
	shadowOffset : 20,
	modal : true,
	module : null,

	initComponent : function() {
		var me = this;
		this.title = '下载报表 ' + '『' + this.excelReport.tf_name + '』';
		var y = new Date().getFullYear(), m = (new Date().getMonth()) + 1;
		if (m == 1) {
			y--;
			m = 12;
		} else
			m--;
		this.items = [{
			xtype : 'form',
			region : 'center',
			bodyPadding : '5 5 5 5',
			items : [{
						xtype : 'fieldset',
						title : '请选择年度和月份',
						defaults : {
							labelWidth : 60,
							width : 150,
							labelAlign : 'right'
						},
						items : [{
									xtype : 'numberfield',
									name : 'year',
									fieldLabel : '年度',
									value : y,
									maxValue : 2050,
									minValue : 2000
								}, {
									xtype : 'numberfield',
									name : 'month',
									fieldLabel : '月份',
									value : m,
									maxValue : 12,
									minValue : 1
								}]
					}

			],
			buttons : ['->', {
				text : '确定',
				icon : 'images/button/download.png',
				handler : function(button) {
					var form = button.up('form');
					if (form.isValid()) {
						if (!me.excelReport.tf_filename) {
							Ext.toastErrorInfo('当前选中的报表方案没有上传模板文件！');
							return;
						}
						Ext.String.endsWith()
						if (! Ext.String.endsWith(me.excelReport.tf_filename, '.xls')) {
							Ext.toastErrorInfo('当前选中的报表方案的上传文件不是后缀名为.xls的Excel文件！');
							return;
						}
						window.location.href = Ext.String
								.format(
										'rest/module/downloadexcelreportmonth.do?moduleId={0}&excelReportId={1}&year={2}&month={3}',
										me.module.tf_moduleId, me.excelReport.tf_id, form.getForm()
												.findField('year').getValue(), form.getForm()
												.findField('month').getValue());
					}
				}
			}, {
				text : '关闭',
				itemId : 'close',
				icon : 'images/button/return.png',
				handler : function(button) {
					button.up('window').close();
				}
			}, '->']
		}]
		this.callParent(arguments);
	}
})
