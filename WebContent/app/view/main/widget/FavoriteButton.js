
Ext.define('app.view.main.widget.FavoriteButton',{

	extend : 'app.ux.ButtonTransparent',
	alias : 'widget.favoritebutton',
	text : '收藏',
	
	iconCls :'fa fa-star',
	
		listeners : {

			render : function(button) {
				// 可以使Grid中选中的记录拖到到此按钮上来进行删除
				
				button.dropZone = new Ext.dd.DropZone(button.getEl(), {
					// 此处的ddGroup需要与Grid中设置的一致
					ddGroup : 'DD_Province',// + button.module.tf_moduleName,

					// 这个函数没弄明白是啥意思,没有还不行
					getTargetFromEvent : function(e) {
						console.log(e);
						return e.getTarget('');
					},
					
//	      On entry into a target node, highlight that node.
	        onNodeEnter : function(target, dd, e, data){
	        	console.log('node enter');
	           // Ext.fly(target).addCls('hospital-target-hover');
	        },
					
					// 用户拖动选中的记录经过了此按钮
					onNodeOver : function(target, dd, e, data) {
						console.log(target);
						return Ext.dd.DropZone.prototype.dropAllowed;
					},
					// 用户放开了鼠标键，删除记录
					onNodeDrop : function(target, dd, e, data) {
						button.fireEvent('click', button); // 执行删除按钮的click事件
					}
				})
			}
		}
	
})

