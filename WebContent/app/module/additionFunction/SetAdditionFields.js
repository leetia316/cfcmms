/**
 *   merge level=30
 */
Ext.define('app.module.additionFunction.SetAdditionFields', {
			extend : 'Ext.window.Window',

			width : 350,
			layout : 'fit',
			modal : true,
			buttonText : null,
			module : null,
			selectRecord : null,
			
			initComponent : function() {
				this.height = document.body.clientHeight * 0.7;
				this.module = app.modules.getModule(this.selectRecord.get('tf_moduleName'));
				this.title = this.buttonText + '『模块：' + this.module.tf_title + '』';
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
											var view = button.up('treepanel').getView(), all = [];
											view.node.cascadeBy(function(rec) {
														if (rec.data.leaf)
															all.push({
																		moduleName : rec.raw.moduleName,
																		checked : rec.data.checked,
																		tag : rec.raw.tag,
																		fieldvalue : rec.raw.fieldvalue
																	})
													});
											Ext.Ajax.request({
														scope : this,
														url : 'systemframe/saveadditionfields.do',
														params : {
															moduleName : this.module.tf_moduleName,
															noderecords : Ext.encode(all)
														},
														success : function() {
															Ext.toastInfo(this.title + '的设置已保存!');
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
											url : 'systemframe/getalladditionfields.do',
											extraParams : {
												moduleName : this.module.tf_moduleName
											}
										}
									})
						}];
				this.callParent(arguments);
			}
		});