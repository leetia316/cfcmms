/**
 * merge level=30
 */
Ext.define('app.module.additionFunction.RoleSetPopedom', {
			extend : 'Ext.window.Window',

			//height : '90%',   //ie8 以下不可以
			width : 900,
			layout : 'fit',
			modal : true,
			buttonText : null,
			selectRecord : null,

			initComponent : function() {
				this.height = document.body.clientHeight * 0.9;
				var me = this;
				this.title = this.buttonText + '『角色：'
						+ this.selectRecord.get('tf_roleName') + '』';
				this.items = [{
					xtype : 'form',
					autoScroll : true,
					bodyPadding : '5 5 5 5',
					buttonAlign : 'center',
					buttons : [{
						text : '保存',
						itemId : 'save',
						icon : 'images/button/save.png',
						scope : this,
						handler : function(button) {

							// 将修改过后的权限设置保存回formdata中，返回给服务器
							var mrs = [];
							var mrads = '';
							for (var i in this.formdata) {
								var moduleGroup = this.formdata[i];
								for (var j in moduleGroup.popedoms) {
									var p = moduleGroup.popedoms[j];
									var id = p.tf_moduleId;
									var mr = {
										tf_moduleId : p.tf_moduleId
									};
									mr.tf_allowBrowse = this.down('[name=_' + id + '_browse]')
											.getValue();
									if (p.tf_allowInsert != null)
										mr.tf_allowInsert = this.down('[name=_' + id + '_insert]')
												.getValue();
									if (p.tf_allowEdit != null)
										mr.tf_allowEdit = this.down('[name=_' + id + '_edit]')
												.getValue();
									if (p.tf_allowDelete != null)
										mr.tf_allowDelete = this.down('[name=_' + id + '_delete]')
												.getValue();
									if (p.tf_allowAuditing != null)
										mr.tf_allowAuditing = this.down('[name=_' + id
												+ '_auditing]').getValue();
									if (p.tf_allowApprove != null)
										mr.tf_allowApprove = this
												.down('[name=_' + id + '_approve]').getValue();

									if (p.tf_allowEditDirect != null)
										mr.tf_allowEditDirect = this.down('[name=_' + id
												+ '_editDirect]').getValue();

									if (p.tf_allowPayment != null)
										mr.tf_allowPayment = this
												.down('[name=_' + id + '_payment]').getValue();
									if (p.tf_attachmentBrowse != null) {
										mr.tf_attachmentBrowse = this.down('[name=_' + id
												+ '_attachmentBrowse]').getValue();
										mr.tf_attachmentInsert = this.down('[name=_' + id
												+ '_attachmentInsert]').getValue();
										mr.tf_attachmentEdit = this.down('[name=_' + id
												+ '_attachmentEdit]').getValue();
										mr.tf_attachmentDelete = this.down('[name=_' + id
												+ '_attachmentDelete]').getValue();
									}
									mrs.push(mr);
									var me = this;
									if (p.additionFunctions) {
										Ext.each(p.additionFunctions, function(addition) {
													if (me.down('[name=_' + id + '_' + addition.id + ']')
															.getValue())
														mrads += addition.id + ","
												})
									}
								}
							}
							Ext.Ajax.request({
										scope : this,
										url : 'role/saverolepopedoms.do',
										params : {
											roleId : this.selectRecord.get('tf_roleId'),
											formdata : Ext.encode(mrs),
											additionids : mrads
										},
										success : function() {
											Ext.toastInfo(this.title + '的修改已保存!');
											this.hide();
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
					}]
				}];

				Ext.Ajax.request({
							async : false,
							url : 'role/getrolepopedoms.do',
							params : {
								roleId : this.selectRecord.get('tf_roleId')
							},
							success : function(response) {
								me.formdata = Ext.decode(response.responseText);
							}
						})

				this.listeners = {
					'render' : function() {
						me.createForm();
					}
				}
				this.callParent(arguments);
			},

			createForm : function() {
				for (var i in this.formdata) {
					var moduleGroup = this.formdata[i];
					var fieldset = new Ext.form.FieldSet({
								title : moduleGroup.tf_title,
								collapsible : true
							});
					for (var j in moduleGroup.popedoms) {
						var popedom = moduleGroup.popedoms[j];

						fieldset.add(this.getFieldContainer(popedom));
					}
					this.down('form').add(fieldset);
				}
			},

			getFieldContainer : function(popedom) {

				var result = Ext.widget('fieldcontainer', {
							layout : 'vbox',
							margin : '0 0 0 0'
						});
				var fc = Ext.widget('fieldcontainer', {
							layout : 'hbox',
							defaults : {
								width : 100
							}
						});
				result.add(fc);
				var browsecheckbox = Ext.widget('checkbox', {
							fieldLabel : popedom.tf_title,
							labelAlign : 'right',
							labelWidth : 120,
							width : 225,
							moduleId : popedom.tf_moduleId,
							name : '_' + popedom.tf_moduleId + '_browse',
							checked : popedom.tf_allowBrowse,
							boxLabel : '可浏览',
							listeners : {
								'change' : function(field, newValue, oldValue) {
									Ext.each(field.up('form').query('[moduleId='
													+ popedom.tf_moduleId + ']'), function(f) {
												if (f != field)
													// 选中整个模块的时候，直接修改不选中
													if ((!newValue)
															|| (newValue && f.name.indexOf('_editDirect') == -1))
														f.setValue(newValue);
											})
								}
							}
						});
				fc.add(browsecheckbox);

				if (popedom.tf_allowInsert != null) {
					var addcheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_insert',
								checked : popedom.tf_allowInsert,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可新增'

							});
					fc.add(addcheckbox);
				} else
					fc.add({
								xtype : 'tbspacer'
							});
				if (popedom.tf_allowEdit != null) {
					var editcheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_edit',
								checked : popedom.tf_allowEdit,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可修改'
							});
					fc.add(editcheckbox);
				}
				if (popedom.tf_allowDelete != null) {
					var deletecheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_delete',
								checked : popedom.tf_allowDelete,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可删除'
							})
					fc.add(deletecheckbox);
				}
				if (popedom.tf_allowAuditing != null) {
					var deletecheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_auditing',
								checked : popedom.tf_allowAuditing,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可审核'
							})
					fc.add(deletecheckbox);
				}

				if (popedom.tf_allowApprove != null) {
					var deletecheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_approve',
								checked : popedom.tf_allowApprove,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可审批'
							})
					fc.add(deletecheckbox);
				}

				if (popedom.tf_allowEditDirect != null) {
					var editdirectcheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_editDirect',
								checked : popedom.tf_allowEditDirect,
								moduleId : popedom.tf_moduleId,
								boxLabel : '直接修改'
							})
					fc.add(editdirectcheckbox);
				}

				if (popedom.tf_allowPayment != null) {
					var deletecheckbox = Ext.widget('checkbox', {
								name : '_' + popedom.tf_moduleId + '_payment',
								checked : popedom.tf_allowPayment,
								moduleId : popedom.tf_moduleId,
								boxLabel : '可支付'
							})
					fc.add(deletecheckbox);
				}

				if (popedom.tf_attachmentBrowse != null) {
					var additionfc = this.getHboxContainer()
					result.add(additionfc);
					additionfc.add({
								name : '_' + popedom.tf_moduleId + '_attachmentBrowse',
								checked : popedom.tf_attachmentBrowse,
								moduleId : popedom.tf_moduleId,
								boxLabel : '附件浏览'
							});
					additionfc.add({
								name : '_' + popedom.tf_moduleId + '_attachmentInsert',
								checked : popedom.tf_attachmentInsert,
								moduleId : popedom.tf_moduleId,
								boxLabel : '附件新增'
							});
					additionfc.add({
								name : '_' + popedom.tf_moduleId + '_attachmentEdit',
								checked : popedom.tf_attachmentEdit,
								moduleId : popedom.tf_moduleId,
								boxLabel : '附件修改'
							});
					additionfc.add({
								name : '_' + popedom.tf_moduleId + '_attachmentDelete',
								checked : popedom.tf_attachmentDelete,
								moduleId : popedom.tf_moduleId,
								boxLabel : '附件删除'
							});
				}

				var me = this;
				if (popedom.additionFunctions) {
					var c = 0, additionfc;
					Ext.each(popedom.additionFunctions,function(addition) {
								if (c == 0) {
									additionfc = me.getHboxContainer()
									result.add(additionfc);
								}
								var af = Ext.widget('checkbox', {
											name : '_' + popedom.tf_moduleId + '_' + addition.id,
											checked : addition.checked,
											moduleId : popedom.tf_moduleId,
											boxLabel : addition.title
										})
								additionfc.add(af);
								c += 1;
								c = (c == 4) ? 0 : c;
							});
					result.add(additionfc);
				}
				return result;
			},

			getHboxContainer : function() {
				return Ext.widget('fieldcontainer', {
							layout : 'hbox',
							margin : '-10 0 0 0',
							defaults : {
								xtype : 'checkbox',
								width : 100
							},
							items : [{
										xtype : 'tbspacer',
										width : 125
									}]
						});
			}
		});