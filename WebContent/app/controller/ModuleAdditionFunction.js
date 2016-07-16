/**
 * 
 * merge level=80
 * 
 * 一个模块的toolbar上面的模块附加按钮的执行
 */

Ext.define('app.controller.ModuleAdditionFunction', {
	extend : 'Ext.app.Controller',
	requires : ['app.lib.CheckTreePanel'],

	init : function() {

		this.control({

					'toolbar button[additionName=SetProvinceArea]' : {
						click : function(button) {
							
							Ext.toastWarn('给省份设置地图');
							
						}
					},
			
			
					// 选中要选择一条记录，并且要执行一个窗口操作的附加功能
					'toolbar button[needRecord=true][showWindow=true]' : {
						click : function(button) {
							this.executeWithSelectAndWindow(button);
						}
					},

					// 输入java bean名字，加入一个模块
					'toolbar button[additionName=AddModule]' : {
						click : function(button) {
							this.addModule(button);
						}
					},

					// 选择一个模块，根据bean中的字段定义，刷新字段
					'toolbar button[additionName=RefreshFields]' : {
						click : function(button) {
							this.refreshFields(button);
						}
					},

					// 刷新当前模块的所有导航模块，会递归加入所有未在导航字段列表中的值，已经有的就不加入了
					'toolbar button[additionName=RefreshNavigateModule]' : {
						click : function(button) {

							var grid = button.up('modulegrid');
							var moduleId = grid.getParentOrNavigateValue('_Module');
							if (!moduleId)
								Ext.toastWarn('请在导航列表中选择一个模块，再执行此操作!');
							else {
								Ext.Ajax.request({
											scope : this,
											url : 'systemframe/refreshnavigatemodule.do',
											params : {
												moduleId : moduleId
											},
											success : function(response) {
												grid.refreshAll();
												Ext.toastInfo('模块导航字段刷新,加入了 '
														+ response.responseText + '个记录!');
											},
											failure : function() {
												window.alert(text + '保存失败!');
											}
										});

							}
						}
					},

					// 重置密码
					'toolbar button[additionName=ResetPassword]' : {
						click : function(button) {
							this.resetPassword(button);
						}
					},

					// 重置密码
					'toolbar button[additionName=DownloadAdditionFile]' : {
						click : function(button) {
							this.downloadAdditionFile(button);
						}
					},
					// 下载备份的数据文件
					'toolbar button[additionName=DownloadBackupFile]' : {
						click : function(button) {
							this.downloadBackupFile(button);
						}
					},

					// 下载以前上传过的新增和修改的Excel文件
					'toolbar button[additionName=DownloadUploadedFile]' : {
						click : function(button) {
							this.downloadUploadedFile(button);
						}
					},

					// 服务器重启
					'toolbar menuitem[additionName=RestartTomcat]' : {
						click : function(button) {
							this.restartTomcat(button);
						}
					},

					// 用当前客户端的时间去更新服务器时间

					'toolbar menuitem[additionName=UpdateServerDate]' : {
						click : function(button) {
							var d = new Date();
							Ext.Ajax.request({
										url : 'updateserverdate.do',
										params : {
											d : d.getFullYear() + '-' + (d.getMonth() + 1) + '-'
													+ d.getDate(),
											t : d.getHours() + ":" + d.getMinutes() + ":"
													+ d.getSeconds()
										},
										success : function(response) {
											var message = response.responseText;
											if (message)
												Ext.toastWarn(message);
											else
												Ext.toastInfo("服务器时间已根据当前客户端时间更新完成。");
										}
									});
						}
					},

					// 合同付款计划的操作,^=表示AgreementPlan开头的值
					'menuitem[additionName^=AgreementPlan]' : {
						click : function(button) {
							this.AgreementPlanAction(button);
						}
					}

				});
	},

	AgreementPlanAutoCreate : function(agreementId, grid) {
		Ext.Ajax.request({
					url : 'agreementplan/autocreate.do',
					params : {
						agreementId : agreementId
					},
					success : function(response) {
						var message = Ext.decode(response.responseText);
						if (message.success == false)
							Ext.toastWarn(message.msg);
						else
							Ext.toastInfo('此合同的付款计划已自动生成!');
						grid.refreshAll();
					},
					failure : function(response) {
						window.alert('重新生成合同付款计划失败!');
					}
				});

	},

	AgreementPlanAction : function(button) {
		var me = this;
		var grid = button.up('modulegrid');
		var agreementId = grid.getParentOrNavigateValue('Agreement');
		if (!agreementId)
			Ext.toastWarn('请在导航列表中选择一个项目合同，再执行此操作!');
		else {
			if (button.additionName == 'AgreementPlanAutoCreate') {
				if (grid.store.count() > 0) {
					Ext.MessageBox.confirm('确定', '确定要重新自动生成此合同的付款计划吗？(原已制定的计划会被全部删除)',
							function(btn) {
								if (btn == 'yes')
									me.AgreementPlanAutoCreate(agreementId, grid);
							});
				} else
					me.AgreementPlanAutoCreate(agreementId, grid);
			} else if (button.additionName == 'AgreementPlanAutoBalance') {
				Ext.Ajax.request({
							url : 'agreementplan/autobalance.do',
							params : {
								agreementId : agreementId
							},
							success : function(response) {
								var message = Ext.decode(response.responseText);
								if (message.success == false)
									Ext.toastWarn(message.msg);
								else
									Ext.toastInfo('当前合同' + button.text + '操作完成!');
								grid.refreshAll();
							},
							failure : function(response) {
								window.alert(button.title + '失败!');
							}
						});
			} else {
				var selected = grid.getFirstSelectedRecord();
				if (selected) {
					var k = button.additionName;
					var mm = (k.indexOf('Late') == -1 ? '-' : '')
							+ k.charAt(k.length - 1);
					Ext.Ajax.request({
								url : 'agreementplan/moveplanmonth.do',
								params : {
									agreementId : agreementId,
									planId : selected.get('tf_planId'),
									moveMonth : mm
								},
								success : function(response) {
									var message = Ext.decode(response.responseText);
									if (message.success == false)
										Ext.toastWarn(message.msg);
									else
										Ext.toastInfo('付款月份' + button.text + '操作完成!');
									grid.refreshAll();
								},
								failure : function(response) {
									window.alert(button.title + '失败!');
								}
							});
				}
			}
		}
	},

	restartTomcat : function(button) {
		Ext.Ajax.request({
					url : 'restarttomcat.do'
				});
	},

	downloadBackupFile : function(button) {
		var grid = button.up('modulegrid');
		var selected = grid.getFirstSelectedRecord();
		if (selected) {
			window.location.href = "systembackup/download.do?id="
					+ selected.get('tf_systembackupId');
		}
	},

	downloadUploadedFile : function(button) {
		var grid = button.up('modulegrid');
		var selected = grid.getFirstSelectedRecord();
		if (selected) {
			if (selected.get('tf_hasfiledata') == true)
				window.location.href = "systemoperatelog/download.do?id="
						+ selected.get('tf_systemlogId');
			else
				Ext.toastWarn('当前选择的日志记录没有文件信息！');
		}
	},

	downloadAdditionFile : function(button) {
		var grid = button.up('modulegrid');
		var selected = grid.getFirstSelectedRecord();
		if (selected) {
			if (selected.get('tf_filename'))
				window.location.href = 'attachment/download.do?id='
						+ selected.get('tf_attachmentId');
			else
				Ext.toastWarn('此附件记录尚未上传附件文件!');
		}
	},

	resetPassword : function(button) {
		var grid = button.up('modulegrid');
		var selected = grid.getFirstSelectedRecord();
		if (selected) {
			Ext.MessageBox.confirm('确定重置', '确定要重置用户『' + selected.get('tf_userName')
							+ '』的密码吗?', function(btn) {
						if (btn == 'yes') {
							Ext.Ajax.request({
										url : 'user/resetpassword.do',
										params : {
											userId : selected.get('tf_userId')
										},
										success : function(response) {
											Ext.toastInfo('用户『' + selected.get('tf_userName')
													+ '』的密码已重置为123456，请通知其尽快修改!');
										},
										failure : function(response) {
											window.alert('重置用户密码保存失败!');
										}
									});
						}
					});
		}

	},

	executeWithSelectAndWindow : function(button) {
		var grid = button.up('modulegrid');
		var selected = grid.getFirstSelectedRecord();
		if (selected) {
			Ext.create('app.module.additionFunction.' + button.additionName, {
						buttonText : button.text,
						selectRecord : selected
					}).show();
		}
	},

	refreshFields : function(button) {
		var grid = button.up('modulegrid');
		var moduleId = grid.getParentOrNavigateValue('_Module');
		if (!moduleId)
			Ext.toastWarn('请在导航列表中选择一个模块，再执行此操作!');
		else {
			Ext.Ajax.request({
				scope : this,
				url : 'systemframe/refreshfields.do',
				params : {
					moduleId : moduleId
				},
				success : function(response) {
					grid.refreshAll();
					Ext.toastInfo('模块字段刷新,加入了 ' + response.responseText + '个字段!');
				},
				failure : function() {
					window.alert(text + '保存失败!');
				}
			});

		}
	},

	addModule : function(button) {
		Ext.MessageBox.prompt('增加模块', '请输入要增加模块的类名称:', function(btn, text) {
					if (btn == 'ok') {
						Ext.Ajax.request({
									scope : this,
									url : 'systemframe/addmodule.do',
									params : {
										moduleName : text
									},
									success : function(response) {
										if (response.responseText)
											Ext.MessageBox.show({
														title : '导入失败',
														msg : '导入模块失败<br/><br/>' + response.responseText,
														buttons : Ext.MessageBox.OK,
														icon : Ext.MessageBox.ERROR
													});

										else {
											button.up('modulegrid').refreshAll();
											Ext.toastInfo('模块:' + text
													+ '的定义和grid,form定义已经到加系统中!');
										}
									},
									failure : function() {
										window.alert(text + '保存失败!');
									}
								});
					}
				});
	}

});