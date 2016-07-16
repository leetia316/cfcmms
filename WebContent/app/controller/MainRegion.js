/**
 * 
 * merge level=80
 * 
 * 主工作区的控制器
 */

Ext.define('app.controller.MainRegion', {
			extend : 'Ext.app.Controller',
			models : [],
			stores : [],
			//views : [ 'region.HomepagePanel'],

			reports : new Ext.util.MixedCollection(),

			init : function() {
				this.control({

				});
			},
			/**
			 * 根据父模块的信息，加入一个模块
			 * 
			 * @param {}
			 *          childModuleName
			 * @param {}
			 *          pModuleName
			 * @param {}
			 *          pId
			 * @param {}
			 *          pName
			 * @param {}
			 *          param
			 */
			addParentFilterModule : function(childModuleName, pModuleName, pId,
					pName, param) {
				var view = app.viewport.down('maincenter');
				var tabItemId = childModuleName + '_pf_tab';
				var tab = view.down('panel#' + tabItemId);
				var module = app.modules.getModule(childModuleName);
				if (tab && childModuleName == '_Attachment') {
					view.remove(tab, false);
					tab = null;
				}
				var m = module.getPanelWithParentModule(tabItemId, pModuleName, pId,
						pName, param);
				if (m == null)
					return;

				if (!tab) {
					tab = view.add(m);
				}
				tab.setTitle(module.tf_title + '『' + pName + '』');
				// console.log(param);
				if (!param || (param && !param.notFocus))
					view.setActiveTab(tab);
				return tab;
			},

			// 加入一个筛选条件的模块
			addFilterModule : function(moduleName, parentFilter) {
				var view = app.viewport.down('maincenter');
				var tabItemId = moduleName + '_filter_tab';
				var tab = view.down('panel#' + tabItemId);
				var module = app.modules.getModule(moduleName);
				if (tab && moduleName == '_Attachment') {
					view.remove(tab, false);
					tab = null;
				}
				var m = module.getPanelWithParentFilter(tabItemId, parentFilter);
				if (m == null)
					return;
				if (!tab) {
					tab = view.add(m);
				}
				tab.setTitle(module.tf_title + '『' + parentFilter.text + '』');
				view.setActiveTab(tab);
			},

			// 加入一个可支付的模块，限定条件为我可支付
			addModuleToPayout : function(moduleName) {
				var view = app.viewport.down('maincenter');
				var tabItemId = moduleName + '_payout';
				var tab = view.down('panel#' + tabItemId);
				var module = app.modules.getModule(moduleName);
				var m = module.getModulePanelToPayout(tabItemId);
				if (m == null)
					return;
				if (!tab) {
					tab = view.add(m);
				}
				tab.setTitle(module.tf_title + '『可支付』');
				view.setActiveTab(tab);
			},

			// 加入一个可审核的模块，限定条件为我可审核
			addModuleToAuditing : function(moduleName) {
				var view = app.viewport.down('maincenter');
				var tabItemId = moduleName + '_auditing';
				var tab = view.down('panel#' + tabItemId);
				var module = app.modules.getModule(moduleName);
				var m = module.getModulePanelToAuditing(tabItemId);
				if (m == null)
					return;
				if (!tab) {
					tab = view.add(m);
				}
				tab.setTitle(module.tf_title + '『可审核』');
				view.setActiveTab(tab);
			},

			// 加入一个可审批的模块，限定条件为我可审批
			addModuleToApprove : function(moduleName) {
				var view = app.viewport.down('maincenter');
				var tabItemId = moduleName + '_approve';
				var tab = view.down('panel#' + tabItemId);
				var module = app.modules.getModule(moduleName);

				var m = module.getModulePanelToApprove(tabItemId);
				if (m == null)
					return;
				if (!tab) {
					tab = view.add(m);
				}
				tab.setTitle(module.tf_title + '『可审批』');
				view.setActiveTab(tab);
			},

			/**
			 * 在主tabPanel中增加一个模块，如果该模块已有，则转到该模块
			 */
			addModuleToMainRegion : function(moduleName) {
				if (!moduleName)
					return;
				// console.log('add module name to mainregion: ' + moduleName);
				this.isModuleInMainRegion(moduleName);
			},

			/**
			 * 在主tabPanel中增加一个综合查询模块
			 */
			addReportToMainRegion : function(reportGroup) {
				if (!reportGroup)
					return;
				var view = app.viewport.down('maincenter');
				var tabItemId = 'report_' + reportGroup.reportGroupId + '_tab';
				var tab = view.down('panel#' + tabItemId);
				if (!tab) {
					var m = this.reports.get(reportGroup.reportGroupId);
					if (!m) {
						var m = Ext.create('app.report.MainReport', {
									reportGroup : reportGroup,
									itemId : tabItemId,
									closable : true
								});
						this.reports.add(reportGroup.reportGroupId , m);
					}
					tab = view.add(m);
				}
				view.setActiveTab(tab);

			},

			/**
			 * 检查是否有模块已经加入tabpanel中了，如果没有，就加入
			 */
			isModuleInMainRegion : function(moduleName) {
				if (!moduleName || moduleName == 'undefined')
					return;
				var view = app.viewport.down('maincenter');
				var tabItemId = moduleName + '_tab';
				var tab = view.down('panel#' + tabItemId);
				if (!tab) {
					var m = app.modules.getModule(moduleName).getModulePanel(tabItemId);
					if (m != null)
						tab = view.add(m);
				}
				view.setActiveTab(tab);
			}

		});