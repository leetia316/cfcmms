/**
 * 
 * merge level=30
 * 
 * 设置一个模块记录筛选角色的模块记录的筛选值，可以是多选的，如果是分级的，那么就按分级的方式筛选
 * 
 */
Ext
		.define(
				'app.module.additionFunction.SetDataFilterRoleDetail',
				{
					extend : 'Ext.window.Window',

					// height : '70%',
					width : 700,
					layout : 'border',
					modal : true,
					buttonText : null,
					selectRecord : null,
					tbar : [
							{
								text : '重新选择',
								icon : 'images/button/clear.png',
								listeners : {
									click : function(button) {
										button.up('window').down('datafilterrolerecordtree')
												.clearAllChecked();
										button.up('window').syncSelected();
									}
								}
							},
							'-',
							{
								text : '全部选择',
								icon : 'images/button/selectall.png',
								listeners : {
									click : function(button) {
										button.up('window').down('datafilterrolerecordtree')
												.selectAllChecked();
										button.up('window').syncSelected();
									}
								}

							},
							'-',
							{
								text : '关闭退出',
								icon : 'images/button/return.png',
								listeners : {
									click : function(button) {
										button.up('window').close();
									}
								}
							},
							{
								text : '确定返回',
								icon : 'images/button/accept.png',
								listeners : {
									click : function(button) {
										var window = button.up('window');
										var selected = window.down('treepanel#selected');
										var values = [];
										var texts = [];
										selected.getRootNode().eachChild(function(node) {
											values.push(node.data.value);
											texts.push(node.data.text);
										});
										window.selectRecord.set('tf_recordIds', values.join(','));
										window.selectRecord.set('tf_recordNames', texts
												.join('<br/>'));
										window.selectRecord.save();
										window.close();
									}
								}
							} ],
					initComponent : function() {
						this.height = document.body.clientHeight * 0.7;
						this.title = this.buttonText + '『角色：'
								+ this.selectRecord.get('_t9038___tf_name') + '』 『模块：'
								+ this.selectRecord.get('_t990102___tf_title') + '』 ';
						this.bbar = Ext.create('Ext.ux.statusbar.StatusBar', {
							items : [ {
								xtype : 'label',
								itemId : 'count',
								text : '未选中记录'
							} ]
						});
						this.items = [ {
							title : '可选择的列表',
							region : 'center',
							width : '350',
							xtype : 'datafilterrolerecordtree',
							moduleId : this.selectRecord.get('_t990102___tf_moduleId'),
							roleId : this.selectRecord.get('_t9038___tf_filterRoleId'),
							selectedValues : this.selectRecord.get('tf_recordIds')
						}, {
							itemId : 'selected',
							region : 'east',
							xtype : 'treepanel',
							root : {
								text : '已选择的记录',
								expanded : true
							},
							width : 300,
							title : '选中列表',
							split : true,
							collapsible : true
						} ];
						this.callParent(arguments);
					},
					// 同步选中列表和可选择列表中的数据
					syncSelected : function() {

						Ext.log('同步选中列表和可选择列表中的数据......');

						var count = 0;
						var tree = this.down('datafilterrolerecordtree');
						var selected = this.down('treepanel#selected');
												
						selected.getRootNode().removeAll();

						tree
								.getRootNode()
								.cascadeBy(
										function(node) {
											// root的 parentNode 为null,
											// 第一级结点的parentNode.data.text='Root'
											if (node.data.checked == true
													&& !!node.parentNode
													&& (node.parentNode.data.text == 'Root' || node.parentNode.data.checked == false)) {
												count++;
												selected.getRootNode().appendChild({
													value : node.data.fieldvalue,
													text : node.data.text,
													leaf : true
												});
											}
										});
						this.down('label#count').setText(
								(count ? '已选中 ' + count + ' 个' : '未选中')
										+ this.selectRecord.get('_t990102___tf_title') + ' ');

					}
				});

/**
 * 
 */

Ext.define('app.module.additionFunction.SetDataFilterRoleDetail.Tree', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.datafilterrolerecordtree',
	requires : [ 'app.lib.TreeSearchField' ],
	rootVisible : false,

	mixins : {
		treeFilter : 'app.lib.TreeFilter' // 混合tree内部筛选的filter
	},

	config : {
		maxlevel : 2, // 当前tree共有多少级
		level : 1
	// 展开的当前级数,按下展开一级后，会一级一级的展开
	},

	tools : [ {
		type : 'expand',
		tooltip : '展开一级',
		listeners : {
			click : function(tool) {
				tool.up('datafilterrolerecordtree').expandToNextLevel();
			}
		}

	}, {
		type : 'collapse',
		tooltip : '全部折叠',
		listeners : {

			click : function(tool) {
				tool.up('datafilterrolerecordtree').collapseAll();
				tool.up('datafilterrolerecordtree').setLevel(1);
			}
		}

	}

	],

	listeners : {

		checkchange : function(node, checked) {
			setChildChecked(node, checked);
			setParentChecked(node, checked);
			node.getOwnerTree().up('window').syncSelected();
		},

		load : function(store, node, records) {
			this.getView().refresh(); // 刷新一下view 不然后面的 记录数出不来
			this.calcMaxLevel(this.getRootNode()); // 计算node最深的级数
			this.setLevel(1);
			this.up('window').syncSelected();
		},

		afterrender : function(tree) {
			tree.getHeader().insert(1, {
				xtype : 'treesearchfield',
				emptyText : '输入筛选值',
				labelAlign : 'right',
				fieldLabel : '筛选条件',
				labelAlign : 'right',
				labelWidth : 60,
				width : '50%',
				treePanel : tree
			});
		}
	},

	initComponent : function() {
		var me = this;
		Ext.applyIf(me, this.config); // 将部分初始值加进来
		this.store = Ext.create(
				'app.module.additionFunction.SetDataFilterRoleDetail.TreeStore', {
					moduleId : this.moduleId,
					roleId : this.roleId,
					ownerTree : me,

					proxy : {
						type : 'ajax',
						url : 'role/fetchmodulerecordtree.do',
						extraParams : {
							moduleId : this.moduleId,
							roleId : this.roleId
						}
					}

				});

		this.callParent(arguments);
	},

	clearAllChecked : function() {
		setChildChecked(this.getRootNode(), false);
	},

	selectAllChecked : function() {
		setChildChecked(this.getRootNode(), true);
	},

	// * 在数据加载进来后，计算node最大的深度

	calcMaxLevel : function(node) {
		if (node.getDepth() > this.getMaxlevel())
			this.setMaxlevel(node.getDepth());
		for ( var i in node.childNodes)
			this.calcMaxLevel(node.childNodes[i]);
	},

	// * 展开至下一级

	expandToNextLevel : function() {
		if (this.level < this.maxlevel)
			this.expandToLevel(this.getRootNode(), this.level);
		this.level += 1;
		if (this.level >= this.maxlevel)
			this.level = 1;
	},

	// * 展开至指定级数

	expandToLevel : function(node, tolevel) {
		if (node.getDepth() <= tolevel)
			node.expand();
		for ( var i in node.childNodes)
			this.expandToLevel(node.childNodes[i], tolevel);
	}

})

Ext.define('app.module.additionFunction.SetDataFilterRoleDetail.TreeStore', {
	extend : 'Ext.data.TreeStore',

	autoLoad : true,

	constructor : function() {
		this.proxy = {
			type : 'ajax',
			url : 'role/fetchmodulerecordtree.do',
			extraParams : arguments[0]
		}
		this.callParent(arguments);

	},

	listeners : {

		load : function(store, records, successful) {
			var v = store.ownerTree.selectedValues
			if (typeof store.ownerTree.selectedValues == 'string')
				var v = store.ownerTree.selectedValues.split(',');
			for ( var i in records) {
				var node = records[i];
				node.cascadeBy(function(e) {
					// 如果当前node 的id 已经选中了，那么就要打勾
					if (v.indexOf(e.data.fieldvalue) != -1) {
						e.data.checked = true;
						store.setChildChecked(e, true);
					}
				})
			}
			;

			// 如果上层的打勾了，那么所有的子node 也要打勾

			for ( var i in records.childNodes) {
				var node = records.childNodes[i];
				node.cascadeBy(function(e) {
					if (e.data.checked)

						// 如果当前node 的id 已经选中了，那么就要打勾
						if (v.indexOf(e.data.fieldvalue) != -1) {
							e.data.checked = true;
						}
				})
			}
			;

		}
	},

	setChildChecked : function(node, checked) {
		if (node.hasChildNodes()) {
			node.eachChild(function(child) {
				setChildChecked(child, checked);
			})
		}
	}

})