/**
 * merge level=30
 * 
 */
Ext.define('app.module.additionFunction.UserSetFieldReadonlyRole', {
			extend : 'Ext.window.Window',

			//height : '70%',
			width : 350,
			layout : 'fit',
			modal : true,
			buttonText : null,
			selectRecord : null,

			initComponent : function() {
				this.height = document.body.clientHeight * 0.5;
				this.title = this.buttonText + '『用户：'
						+ this.selectRecord.get('tf_userName') + '』';
				this.items = [{
							xtype : 'checktreepanel',
							rootVisible : false,
							buttonAlign : 'center',
							buttons : [{
										text : '保存',
										disabled : true,
										itemId : 'save',
										icon : 'images/button/save.png',
										scope : this,
										handler : function(button) {
											var view = button.up('treepanel').getView(), all = '';
											view.node.cascadeBy(function(rec) {
														if (rec.data.leaf && rec.raw.tag
																&& rec.data.checked)
															all += rec.raw.fieldvalue + ','
													});
											Ext.Ajax.request({
														scope : this,
														url : 'user/saveuserfieldreadonlyroles.do',
														params : {
															userId : this.selectRecord.get('tf_userId'),
															noderecords : all
														},
														success : function() {
															Ext.toastInfo(this.title + '的修改已保存!');
															button.disable();
														},
														failure : function() {
															window.alert(this.title + '保存失败!')
														}
													})
										}
									}, {
										text : '关闭',
										icon : 'images/button/return.png',
										scope : this,
										handler : function() {
											this.hide();
										}
									}],
							store : new Ext.data.TreeStore({
										autoLoad : true,
										proxy : {
											type : 'ajax',
											url : 'user/getuserfieldreadonlyroles.do',
											extraParams : {
												userId : this.selectRecord.get('tf_userId')
											}
										}
									})
						}];
				this.callParent(arguments);
			}
		});