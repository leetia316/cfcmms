/**
 * merge level=50
 * 
 * 用来上传excel表，新增记录
 * 
 */

Ext.define('app.module.window.UploadInsertExcelWindow', {

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
		this.fnstart = this.module.tf_title + '(新增导入表)';
		this.errorMessage = Ext.String.format('注意：请选择文件名为 『{0}』 开头的Excel文件。',
				this.fnstart)
		this.title = this.module.tf_title + '（上传Excel文件导入新增）';
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
						xtype : 'displayfield',
						value : '此窗口用于将下载的新增Excel表格填制好新增的记录后，上传导入新增。'
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
						value : this.errorMessage,
						fieldCls : 'errorMessage'
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
						if (lastname !== '.xls' || fn.indexOf('\\' + me.fnstart) == -1) {
							Ext.MessageBox.show({
										title : '选择文件错误',
										msg : me.errorMessage,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
							return;
						}
						//console.log(lastname);
						//console.log(fn);
						form.submit({
							url : 'module/uploadexcelinsert.do',
							waitMsg : '正在处理中，请稍候...',
							timeout : 120,
							success : function(form, action) {
								var success = action.result.success;
								if (success) {
									var cs = '<br/><br/>你可以在系统操作日志中下载新增导入处理过后的Excel文件。<br/><br/>'
											+ '<a onclick="javascript:window.location.href=\'systemoperatelog/download.do?id='
											+ action.result.tag
											+ '\';return false;" href="#">点击下载已处理过的新增文件，在最后一列可以看到导入情况</a><br/>'

									Ext.MessageBox.show({
												title : '保存成功',
												msg : action.result.msg + cs,
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
