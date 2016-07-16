/**
 * 
 * 系统的主页面。本系统是一个单一页面的架构，所有的子页面都是包含在此控件之下
 * 
 * 主页面采用MVVM的架构模式
 * 
 */
Ext.define('app.view.main.Main', {
	extend : 'Ext.container.Viewport',

	// 当前控件的xtype类型，在子控件中可以使用 sub.up('app-main') 来找到它
	xtype : 'app-main',

	// 这里需要的文件可以在此控件加载之后再加载,可理解为异步加载
	requires : [ 'app.view.main.MainController', 'app.view.main.MainModel',
			'app.view.main.region.Bottom', 'app.view.main.region.Center',
			'app.view.main.region.Top', 'app.view.main.widget.ShowHeaderToolbar',
			'app.view.main.menu.MainMenuToolbar', 'app.view.main.region.Left'],

	// 这里需要的文件必须在此控件加载之前加载好,可理解为同步加载
	uses : [],

	id : 'appviewport',

	// 当前控件和其子控件的控制器，也就是事件处理的控制器。
	controller : 'main',

	// 当前控件和其子控件的视图模型，里面有控制界面如何显示的参数。
	viewModel : {
		type : 'main'
	},

	// 隐藏或显示顶部或底部区域的控件
	showOrHiddenToolbar : null,

	layout : {
		type : 'border' // 系统的主页面的布局,这个布局的items里必须包含一个center区域
	},

	listeners : {

		/*
		 * MVVM中设置事件的方法，等价于下面的方法。 resize : function(container) {
		 * container.getController().onMainResize(); }
		 */
		resize : 'onMainResize'

	},

	// 系统主页面里面的控件分布，主要包括顶部和底部的信息面版，左边的菜单面版，中间的模块信息显示区域
	items : [ {
		xtype : 'maintop',
		title : '信息面版，左边的菜单面版，中间的模块信息显示区域',
		region : 'north' // 把它放在最顶上
	}, {
		xtype : 'mainmenutoolbar',
		region : 'north', // 把他放在maintop的下面
		hidden : true, // 默认隐藏
		bind : {
			hidden : '{!isToolbarMenu}' // 如果不是标准菜单就隐藏
		}
	}, {
		xtype : 'mainbottom',
		region : 'south' // 把它放在最底下
	}, {
		xtype : 'mainmenuregion',
		reference : 'mainmenuregion',
		region : 'west', // 左边面板
		width : 220,
		collapsible : true,
		split : true,
		hidden : true, // 系统默认是显示此树状菜单。这里改成true也可以，你就能看到界面显示好后，再显示菜单的过程
		bind : {
			hidden : '{!isTreeMenu}'
		}
	}, {
		region : 'center', // 中间的显示面版，是一个tabPanel.
		xtype : 'maincenter',
		reference : 'maincenter'
	} ],

	initComponent : function() {
		// Ext中显示log的方法，在用sencha cmd编译的产品发布文件中，会自动删除这些log语句。
		console.log('main view initComponent');

		// 在app的命名空间里加入此控件，在其他地方可以直接使用此控件
		app.viewport = this;

		this.showOrHiddenToolbar = Ext.widget('showheadertoolbar');

		this.callParent(arguments); // 调用父类的初始化方法
	}

});
