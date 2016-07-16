/**
 * merge level=50
 * 
 * 当grid中选择了一条记录，单击子模块按钮，如果是显示一个窗口，就显示这一个了
 * 
 */

Ext.define('app.module.window.SubModuleWindow', {

			extend : 'Ext.window.Window',
			alias : 'widget.submodulewindow',

			layout : 'fit',
			maximizable : true,
			height : 600,
			width : 800,
			shadowOffset : 20,
			modal : true,
			closeAction : 'destroy',

			pModuleName : null,
			pModuleTitle : null,
			pId : null,
			pName : null,
			param : null,

			initComponent : function() {

			//	childModuleName, pModuleName, pId,	pName, param

				var tabItemId = this.childModuleName + '_pf_win';
				var module = app.modules.getModule(this.childModuleName);

				var m = module.getNewPanelWithParentModule(tabItemId, this.pModuleName, this.pId,
						this.pName, this.param , true);    //true表示生成一个新的
				m.border = false;
				m.frame = false;
				this.icon = m.icon;
				this.title = m.title;
				
				delete m.icon;
				delete m.title;
				delete m.closable ;
						
				this.items = [m];
					
				this.callParent(arguments);
			}

		})
