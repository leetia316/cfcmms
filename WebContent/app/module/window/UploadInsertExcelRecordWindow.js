/**
 * merge level=50
 * 
 * 用来上传excel表，新增记录
 * 
 */

Ext.define('app.module.window.UploadInsertExcelRecordWindow', {

	extend : 'Ext.window.Window',
	layout : 'border',
	icon : 'images/button/new.png',
	height : 300,
	width : 600,
	shadowOffset : 20,
	modal : true,
	module : null,

	initComponent : function() {
		var me = this;
		this.title = this.module.tf_title + '（上传Excel文件导入新增一条记录）';
		this.items = [{
			xtype : 'form',
			region : 'center',
			bodyPadding : 20,
			defaults : {
				anchor : '100%'
			},
			items : [{
						xtype : 'hidden',
						name : 'moduleId',
						value : this.module.tf_moduleId
					}, {
						xtype : 'hidden',
						name : 'id',
						value : this.methodId
					}, {
						xtype : 'displayfield',
						value : this.remark
					}, {
						padding : '20 0 20 0',
						fieldLabel : '新增上传文件',
						msgTarget : 'side',
						width : 300,
						name : 'file',
						xtype : 'filefield',
						allowBlank : false,
						emptyText : '请选择一个小于10M的文件...',
						buttonText : '选择文件'
					}, {
						xtype : 'displayfield',
						value : Ext.String.format(
								'<a href="module/downloadinsertexcelrecord.do?moduleId={0}&methodId={1}'
										+ ' " >单击此处下载用于新增记录的Excel文件</a>', this.module.tf_moduleId,
								this.methodId)
					}],
			buttons : ['->', {
				text : '上传',
				icon : 'images/button/save.png',
				handler : function(button) {
					var form = button.up('form');
					if (form.isValid()) {
						var fn = form.getForm().findField('file').getValue();
						var pos = fn.lastIndexOf(".");
						var lastname = fn.substring(pos, fn.length);
						// 上传的文件名统一为c:\fadepath\文件名.xls
						if (lastname !== '.xls') {
							Ext.MessageBox.show({
										title : '选择文件错误',
										msg : '请选择一个Excel文件来上传新增记录！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
							return;
						}
						// console.log(lastname);
						// console.log(fn);
						form.submit({
							url : 'module/uploadexcelinsertrecord.do',
							waitMsg : '正在处理中，请稍候...',
							timeout : 120,
							success : function(form, action) {
								var success = action.result.success;
								if (success) {
									me.grid.refreshAll();
									Ext.MessageBox.show({
												title : '保存成功',
												msg : "上传的Excel已成功新增记录，请找到相应记录，查看是否正确。",
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.INFO
											});
								}
							},
							failure : function(form, action) {
								Ext.MessageBox.show({
											title : '错误',
											msg : action.result.msg,
											buttons : Ext.MessageBox.OK,
											icon : Ext.MessageBox.ERROR
										});
							}
						})
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
