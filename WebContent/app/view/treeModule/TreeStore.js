/**
 * 
 * merge level=41
 * 
 * grid的dataStore,用于根据条件来取得相应的记录
 * 
 * proxy的extraParams中要包括
 * 
 * moduleName : 模块名称
 * 
 * schemeOrder : 当前的列表方案序号
 * 
 * columns : 当前grid的显示字段
 * 
 * query : 全局筛选的值，在所有的当前grid的显示字段之中筛选
 * 
 * page,start,limit,sort
 * 
 * navigates : 导航树的筛选值 包括 导航的名称，选中的模块，,是一个数组
 * 
 * ----moduleName: 筛选条件的模块名称
 * 
 * ----primarykey: 模块的主键，一般的条件都加在主键之上
 * 
 * ----fieldtitle: 字段的名称
 * 
 * ----equalsValue: 筛选的主键值，或者自基本字段的字段值
 * 
 * ----isCodeLevel : 如果是阶梯的module,需要like
 * 
 * parentModuleName : 父模块
 * 
 * parentModuleId : 父模块的id
 * 
 * operateType : 当前模块的操作属性，是default还是审核，审批，多条选择，单条选择，等等
 */

Ext.define('app.view.treeModule.TreeStore', {
			extend : 'Ext.data.TreeStore',

			modulePanel : null,
			remoteSort : true,
			autoLoad : true,
			autoSync : false,


			constructor : function(param) {

				this.callParent(arguments);
			}
		});
