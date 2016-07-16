/**
 * 
 * merge level=80
 * 
 * 附件的控制器
 */
Ext.define('app.controller.Attachment', {
	extend : 'Ext.app.Controller',

	requires : [ 'app.attachment.AttachmentNavigate',
			'app.attachment.AttachmentTabPanel', 'app.attachment.AttachmentView',
			'app.attachment.ImagePreviewPanel' ],

	canPreviewExt : [ '.pdf', '.swf', '.txt', '.htm', '.html', '.xml', '.sql',
			'.doc', '.xls' ],
	// '.mp3', '.mp4', '.mpe', '.mpg', '.mpeg','.wav', '.avi'
	// .doc docx xls xlsx ppt pptx

	init : function() {

		this.control({

			// 保存新增附件的记录,并上传文件
			'baseform button#uploadnewattachment' : {
				click : function(button) {
					console.log(button.up('form').data);
					Ext.create('app.module.window.UploadNewAttachment', {
						module : button.up('form').module,
						model : button.up('form').data
					}).show();

				}
			},

			'attachmentview' : {
				scope : this,

				itemdblclick : function(view, record, item) {
					var panel = view.up('tabpanel');
					var downloadbutton = panel.down('button#download');
					downloadbutton.fireEvent('click', downloadbutton);
				},

				selectionchange : function(view, selected) {
					var panel = view.view.up('tabpanel');
					panel.down('#attachmentpreview').setTitle('附件文件预览');

					var filepanel = panel.down('#attachmentfile');
					var imagepanel = panel.down('imagepreviewpanel');

					filepanel.setVisible(false);
					imagepanel.setVisible(false);
					panel.down('displayform').getForm().resetToNull();
					if (selected.length != 0) {
						var model = selected[0];
						panel.down('displayform').setData(model);
						var fn = model.get('tf_filename');
						panel.down('#attachmentpreview').setTitle(
								'附件文件预览' + '『' + model.get('tf_name') + (fn ? ' -- ' + fn : '')
										+ '』');
						if (model.get('tf_filename')) {
							if (model.get('tf_imgheight')) {
								imagepanel.setVisible(true);
								imagepanel.setImage("attachment/getattachment.do?id="
										+ model.get('tf_attachmentId'), model.get('tf_imgwidth'),
										model.get('tf_imgheight'));
							} else {
								var canpre = false;
								Ext.each(app.viewport.getViewModel().get('tf_previewExts'),
										function(ext) {
											canpre = canpre
													|| model.get('tf_filename').toLowerCase().slice(
															-ext.length - 1) == '.' + ext;
										});
								if (canpre) {
									filepanel.setVisible(true);

									// 这里默认是用pdf 预览，如果还有其他的要预览，要改改
									filepanel.el.dom.src = 'pdf/web/viewer.html?file='
											+ getContextPath()
											+ '/attachment/getattachment.do%3Fid%3d'
											+ +model.get('tf_attachmentId');
									// filepanel.el.dom.src = "attachment/getattachment.do?id="
									// + model.get('tf_attachmentId');
								}
							}
						} else {
							var remark = model.get('tf_remark');
							// 如果备注里是以http://开头，则表示为一个链接
							if (remark && remark.indexOf('http') == 0) {
								filepanel.setVisible(true);
								filepanel.el.dom.src = remark;
							}
						}
					}
				}
			},

			'attachmentnavigate tool[type=refresh]' : {
				click : function(button) {
					button.up('attachmentnavigate').store.reload();
				}
			},

			'attachmentnavigate button#download' : {
				click : function(button) {
					var view = button.up('attachmentnavigate').down('attachmentview');
					if (view.getSelectionModel().selected.items.length == 0)
						Ext.toastWarn("请先选择一个附件,然后再执行此操作！");
					else {
						var model = view.getSelectionModel().selected.items[0];
						if (model.get('tf_filename'))
							window.location.href = 'attachment/download.do?id='
									+ model.get('tf_attachmentId');
						else
							Ext.toastWarn("当前选中的附件尚未上传附件文件！");
					}
				}
			},

			'attachmentnavigate button#downloadall' : {
				click : function(button) {
					var view = button.up('attachmentnavigate').down('attachmentview');
					var pf = Ext.decode(view.store.extraParams.parentFilter);
					window.location.href = 'attachment/downloadall.do?moduleId='
							+ pf.moduleId + '&id=' + pf.equalsValue + '&text=' + (pf.text);
				}
			}
		})
	}
})