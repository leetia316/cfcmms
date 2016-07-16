/**
 * merge level=30
 */
Ext.define('app.module.additionFunction.SetGridGroupFields', {
			extend : 'Ext.window.Window',

			// height : '70%',
			width : 350,
			layout : 'fit',
			modal : true,
			buttonText : null,
			selectRecord : null,
			gridGroupId : null,

			initComponent : function() {
				this.height = document.body.clientHeight * 0.7;
				this.gridGroupId = this.selectRecord.get('tf_gridGroupId');
				this.title = this.buttonText + '『分组名称：'
						+ this.selectRecord.get('tf_gridGroupName') + '』';
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
																		aggregationType : rec.raw.aggregationType,
																		fieldvalue : rec.raw.fieldvalue
																	});
													});
											Ext.Ajax.request({
														scope : this,
														url : 'systemframe/savegridgroupfields.do',
														params : {
															gridGroupId : this.gridGroupId,
															noderecords : Ext.encode(all)
														},
														success : function() {
															Ext.toastInfo(this.title + '的设置已保存!');
															button.disable();
														},
														failure : function() {
															window.alert(this.title + '保存失败!');
														}
													});
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
											url : 'systemframe/getgridgroupfields.do',
											extraParams : {
												gridGroupId : this.gridGroupId
											}
										}
									})
						}];
				this.callParent(arguments);
			}
		});