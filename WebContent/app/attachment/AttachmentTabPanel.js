/**
 * merge level=50
 * 
 * 附件文件预览界面
 */
 
Ext.define('app.attachment.AttachmentTabPanel', {
			extend : 'Ext.tab.Panel',
			alias : 'widget.attachmenttabpanel',
			requires : ['app.module.form.DisplayForm'],
			tabPosition : 'bottom',
			initComponent : function() {
				this.items = [{
							layout : 'border',
							title : '附件操作',
							icon : 'images/module/_Attachment.png',
							items : this.grid_detail
						}, {
							layout : 'border',
							title : '附件显示',
							icon : 'images/button/display.png',
							items : [{
										title : '附件文件预览',
										itemId : 'attachmentpreview',
										xtype : 'panel',
										region : 'center',
										layout : 'fit',
										items : [{
													xtype : 'component',
													itemId : 'attachmentfile',
													hidden : true,
													autoEl : {
														tag : 'iframe'
													}
												}, {
													xtype : 'imagepreviewpanel',
													hidden : true
												}, Ext.create('app.module.form.DisplayForm', {
															module : app.modules.getModule('_Attachment'),
															disableSetWindowTitle : true
														})]
									}, {
										xtype : 'attachmentnavigate',
										store : this.store
									}]
						}];
				this.listeners = {
					scope : this,
					afterrender : function() {
						this.setActiveTab(1);
						this.setActiveTab(0);
						if (this.up('modulepanel').param
								&& this.up('modulepanel').param.showAdditionView)
							this.setActiveTab(1);
						this.down('displayform toolbar').setVisible(false);
					}
				};
				this.callParent(arguments);
			}
		})