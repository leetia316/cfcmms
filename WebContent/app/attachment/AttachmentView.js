/**
 * merge level=50
 * 
 * 附件预览
 */
Ext.define('app.attachment.AttachmentView', {
			extend : 'Ext.view.View',
			alias : 'widget.attachmentview',
			baseCls : 'images-view',
			tpl : new Ext.XTemplate(
					'<tpl for=".">',
					'<div class="thumb-wrap" id="_preview_{tf_attachmentId}" >',
					'<div class="thumb"><img src="',

					'<tpl if="tf_filename">',
					'attachment/preview.do?id={tf_attachmentId}',
					'<tpl else>',
					'images/attachment/no.png',
					'</tpl>',
					'" data-qtip="{tf_name}<br/>{_t9502___tf_name}<br/>' +
					
					'<tpl if="tf_filename"> {tf_filename}', '<tpl else>&nbsp;',
					'</tpl>',
					
					'"/></div>',
					'<span>',

					'<a onclick="javascript:__smr(\'_Attachment\',\'',
					'{tf_attachmentId}', '\');return false;" href="#">', '{tf_name}</a>',
					'</br><tpl if="tf_filename"> {tf_filename}', '<tpl else>&nbsp;',
					'</tpl>　</span>',

					'</div>', '</tpl>', '<div class="x-clear"></div>'),

			// plugins : [Ext.create('Ext.ux.DataView.DragSelector', {})],

			trackOver : true,
			overItemCls : 'x-item-over',
			itemSelector : 'div.thumb-wrap',
			autoScroll : true
		});