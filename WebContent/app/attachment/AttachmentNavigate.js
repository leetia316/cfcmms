/**
 * merge level=50
 * 
 * 附件的缩略图显示区域，上面显示所有附件的缩略图以及一些操作按钮
 * 
 */

Ext.define('app.attachment.AttachmentNavigate', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.attachmentnavigate',
			title : '附件缩略图',
			region : 'west',
			width : 330,
			layout : 'fit',
			split : true,
			collapsible : true,
			tools : [{
				type : 'refresh',
				tooltip : '刷新数据'
			}		// , {
					// type : 'print'
					// }, {
					// type : 'gear',
					// tooltip : '模块设置',
					// scope : this,
					// listeners : {}
					// }
			],

			initComponent : function() {

				this.tbar = {
					layout : {
						overflowHandler : 'Menu'
					},
					items : [{
								text : '下载',
								itemId : 'download',
								icon : 'images/button/download.png'
							}, {
								text : '全部下载',
								itemId : 'downloadall',
								tooltip : '将全部文件附件压缩成.zip文件后下载',
								icon : 'images/button/download.png'
							}, '-', '筛选', {
								width : 80,
								xtype : 'searchfield',
								store : this.store
							}]
				};
				this.items = [{
							xtype : 'attachmentview',
							store : this.store
						}]

				this.callParent(arguments);
			}

		});
