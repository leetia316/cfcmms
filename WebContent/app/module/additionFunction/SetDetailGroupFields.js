/**
 *   merge level=30
 */
Ext.define('app.module.additionFunction.SetDetailGroupFields', {
			extend : 'Ext.window.Window',

			//height : '70%',
			width : 350,
			layout : 'fit',
			modal : true,
			buttonText : null,
			selectRecord : null,
			detailId : null,
			
			initComponent : function() {
				this.height = document.body.clientHeight * 0.7;
				this.detailId = this.selectRecord.get('tf_detailId');
				this.title = this.buttonText + '『分组名称：' + this.selectRecord.get('tf_schemeName') + '』';
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
														url : 'systemframe/savedetailgroupfields.do',
														params : {
															detailId : this.detailId,
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
											url : 'systemframe/getdetailgroupfields.do',
											extraParams : {
												detailId : this.detailId
											}
										}
									})
						}];
				this.callParent(arguments);
			}
		});