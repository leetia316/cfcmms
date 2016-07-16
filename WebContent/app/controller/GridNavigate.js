/**
 * 
 *  merge level=80
 * 
 * 导航总控面板的事件控制
 */

Ext.define('app.controller.GridNavigate', {
			extend : 'Ext.app.Controller',

			init : function() {

				this.control({

							/**
							 * 导航面板中设置按钮下的菜单事件
							 */
							'navigatesettingmenu menuitem#showintab' : {
								click : function(item) {
									var tool = item.parentMenu.parentMenu.navigate
											.down('header[tag=modulenavigate] tool[type=pin]');
									tool.fireEvent('click', tool);
								}
							},

							'navigatesettingmenu menuitem#showinacce' : {
								click : function(item) {
									var tool = item.parentMenu.parentMenu.navigate
											.down('header[tag=modulenavigate] tool[type=unpin]');
									tool.fireEvent('click', tool);
								}
							},

							'navigatesettingmenu menuitem#clearAllFilter' : {
								click : function(item) {
									item.ownerCt.navigate.clearNavigateValues();
								}
							},

							'navigatesettingmenu menuitem#refresh' : {
								click : function(item) {
									this.refreshNavigateTree(item.ownerCt.navigate);
								}
							},

							'navigatesettingmenu menucheckitem#display0record' : {
								checkchange : function(item, checked) {
									Ext.each(item.ownerCt.navigate.query('navigatetree'),
											function(tree) {
												tree.setIsContainNullRecord(checked);
											});
								}
							},

							'navigatesettingmenu menucheckitem#allselected' : {
								checkchange : function(item, checked) {
									var tool = item.ownerCt.navigate.down('tool[type='
											+ (checked ? 'plus' : 'minus') + ']');
									tool.fireEvent('click', tool);
								}
							},

							'navigatesettingmenu menuitem#dockinleft' : {
								checkchange : function(item, checked) {
									if (checked) {
										var me = item.parentMenu.parentMenu.navigate;
										me.setBorderRegion('west');
										var tab = me.down('tabpanel');
										if (tab) {
											me.tabPosition = 'left';
											me.setNavigateMode('tab');
										}
									}
								}
							},

							'navigatesettingmenu menuitem#dockinright' : {
								checkchange : function(item, checked) {
									if (checked) {
										var me = item.parentMenu.parentMenu.navigate;
										me.setBorderRegion('east');
										var tab = me.down('tabpanel');
										if (tab) {
											me.tabPosition = 'right';
											me.setNavigateMode('tab');
										}
									}
								}
							},
							/**
							 * 整个导航面板的事件
							 */

							'modulenavigate' : {
								collapse : function(p) {
									if (p.up('modulepanel').down('recorddetail').collapsed) {
										p.up('modulepanel').down('modulegrid')
												.setShowMaximize(false);
									}
								},
								expand : function(p) {
									p.up('modulepanel').down('modulegrid').setShowMaximize(true);
								},

								afterrender : function(p) {				//可能这个比grid先渲染
									setTimeout(function() {
												p.up('modulepanel').down('modulegrid')
														.setShowMaximize(true);
											}, 100)
								}

							},
							'modulenavigate tool[type=gear]' : {
								click : function(tool, e, opts) {
									var menu = tool.up('modulenavigate').getSettingMenu();
									menu.show();
									menu.setXY([
											Ext.Array.min([e.browserEvent.clientX,
													document.body.clientWidth - 200]),
											e.browserEvent.clientY]);
								}
							},

							'modulenavigate tool[type=refresh]' : {
								click : function(tool) {
									this.refreshNavigateTree(tool.up('modulenavigate'));
								}
							},

							'modulenavigate header[tag=modulenavigate] tool[type=unpin]' : {
								click : function(tool) {
									tool.setVisible(false);
									tool.ownerCt.down('tool[type=pin]').setVisible(true);
									tool.up('modulenavigate').setNavigateMode('accordion');
									tool.up('modulenavigate').getSettingMenu()
											.down('menuitem#showintab').setChecked(false, true);
									tool.up('modulenavigate').getSettingMenu()
											.down('menuitem#showinacce').setChecked(true, true);
								}
							},
							'modulenavigate header[tag=modulenavigate] tool[type=pin]' : {
								click : function(tool) {
									tool.setVisible(false);
									tool.ownerCt.down('tool[type=unpin]').setVisible(true);
									tool.up('modulenavigate').setNavigateMode('tab');

									tool.up('modulenavigate').getSettingMenu()
											.down('menuitem#showintab').setChecked(true, true);
									tool.up('modulenavigate').getSettingMenu()
											.down('menuitem#showinacce').setChecked(false, true);

								}
							},

							'modulenavigate tool[type=plus]' : {
								click : function(tool) {
									this.setAllSelected(tool, true);
								}
							},
							'modulenavigate tool[type=minus]' : {
								click : function(tool) {
									this.setAllSelected(tool, false);
								}
							}

						})
			},

			/**
			 * 设置是否所有条件都有效
			 */

			setAllSelected : function(tool, value) {
				tool.setVisible(false);
				Ext.toastInfo(tool.tooltip);
				tool.ownerCt.down('tool[type=' + (value ? 'minus' : 'plus') + ']')
						.setVisible(true);
				tool.ownerCt.up('modulenavigate').setAllSelected(value);

				tool.up('modulenavigate').getSettingMenu().down('menuitem#allselected')
						.setChecked(value, true);

			},

			/**
			 * 刷新所有导航数据
			 */

			refreshNavigateTree : function(navigate) {
				Ext.each(navigate.query('navigatetree'), function(tree) {
							tree.store.reload();
						})
			}

		});