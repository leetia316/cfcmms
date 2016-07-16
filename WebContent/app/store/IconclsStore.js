/**
 * 存放FontAwesome 等字体文件的css选择项，可以直接供 iconCls的字段下拉选择
 */
Ext.define('app.store.IconclsStore', {
	extend : 'Ext.data.Store',

	fields : [ 'name' ],

	proxy : {
		type : 'ajax',
		url : 'extradata/iconcls.json',
		reader : {
			type : 'json'
		}
	}
	

})