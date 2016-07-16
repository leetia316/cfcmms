/**
 * merge level=50
 * 
 * 在附件模块中，用来上传一个新的附件文件，将会覆盖掉原来的记录
 * 
 */

Ext.define('app.module.window.UploadNewAttachment', {

	extend : 'Ext.window.Window',
	layout : 'border',
	icon : 'images/button/new.png',
	height : 200,
	width : 600,
	shadowOffset : 20,
	modal : true,
	module : null,

	initComponent : function() {
		var me = this;
		this.title = '上传附件 ' + this.module.tf_title + '『'
				+ this.model.getTitleTpl() + '』';
		this.items = [{
			xtype : 'form',
			region : 'center',
			bodyPadding : 20,
			defaults : {
				anchor : '100%'
			},
			items : [ {
						xtype : 'hidden',
						name : 'tf_attachmentId',
						value : this.model.getIdValue()
					}, {
						padding : '10 0 0 0',
						fieldLabel : '上传新文件',
						msgTarget : 'side',
						width : 300,
						name : 'file',
						xtype : 'filefield',
						allowBlank : false,
						emptyText : '请选择一个小于10M的文件...',
						buttonText : '选择文件'
					}],
			buttons : ['->', {
						text : '上传',
						icon : 'images/button/save.png',
						handler : function(button) {
							var form = button.up('form');
							if (form.isValid()) {
								form.submit({
											url : 'attachment/uploadnewattachment.do',
											waitMsg : '正在处理中，请稍候...',
											timeout : 120,
											success : function(form, action) {
												var success = action.result.success;
												if (success) {
													Ext.toastInfo('所选的文件已成功上传!');
													me.hide();
												}
											},
											failure : function(form, action) {
												if (action.response.responseText
														.indexOf('MaxUploadSize'))
													Ext.MessageBox.show({
																title : '上传文件失败',
																msg : '失败原因:上传文件的大小超过了10M,请重新上传...',
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
												else
													Ext.Msg.alert('上传文件失败', action.response.responseText);
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
