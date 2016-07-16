package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.core.report.GroupFieldDefine;
import com.jfok.cfcmms.hibernate.system.authority._ModuleApproveUser;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldHiddenRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserFieldReadonlyRoleDetail;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleAddition;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleAdditionField;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.report._ReportGroup;
import com.jfok.cfcmms.hibernate.system.setting._Systeminfo;
import com.jfok.cfcmms.hibernate.system.setting._Systemset;
import com.jfok.cfcmms.hibernate.system.viewSetting._Menu;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridNavigate;
import com.jfok.cfcmms.logic._ModuleAdditionFieldLogic;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.ApproveListTypeEnum;
import com.jfok.cfcmms.share.FieldType;
import com.jfok.cfcmms.share.info.ApplicationInfo;
import com.jfok.cfcmms.share.info.RoleInfo;
import com.jfok.cfcmms.share.info.ServiceInfo;
import com.jfok.cfcmms.share.info.SystemInfo;
import com.jfok.cfcmms.share.info.UserInfo;
import com.jfok.cfcmms.share.module.ModuleConstants;
import com.jfok.cfcmms.share.service.ISystemAndLoginInfoService;


@Service
public class SystemAndLoginInfoService implements ISystemAndLoginInfoService {

	private static final Log log = LogFactory.getLog(SystemAndLoginInfoService.class);
	private static boolean isRefreshAll = false;

	private static SystemBaseDAO systemBaseDAO = null;

	private static ModuleDAO moduleDAO = null;

	private static List<_Module> modules = null;

	private static ApproveService approveService = null;

	public SystemAndLoginInfoService() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)

	public ApplicationInfo getApplicationInfo(HttpServletRequest request) {
		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
		// 系统的属性改变了以后，只有在客户端刷新了以后才生效
		if (isRefreshAll) {
			modules = null;
			// 清除系统设置和系统信息的缓存
			SystemInfoService.refreshAll();
			// 清除所有的附加字段的缓存
			_ModuleAdditionFieldLogic.clearAllMap();
			isRefreshAll = false;
		}
		
		_Systeminfo systeminfo = SystemInfoService.getSysteminfo();
		_Systemset systemset = SystemInfoService.getSystemset();

		ApplicationInfo result = new ApplicationInfo();

		result.setTf_additionFileMaxMB(systeminfo.getTf_additionFileMaxMB());
		result.setTf_previewExts(systeminfo.getTf_previewExts());
		
		// 以上内容暂时为自定义的，以后会改为从数据库和登录信息中读取。
		SystemInfo systemInfo = new SystemInfo();
		systemInfo.setTf_systemName(systeminfo.getTf_systemName());
		systemInfo.setTf_systemVersion(systeminfo.getTf_systemVersion());
		result.setSystemInfo(systemInfo);

		UserInfo userInfo = new UserInfo();
		userInfo.setTf_userdwmc(systemset.getTf_userdwmc());
		userInfo.setTf_userStartdate(systemset.getTf_userStartdate());
		userInfo.setTf_userName(userSession.getUserName());
		userInfo.setTf_loginName(userSession.getLoginName());
		userInfo.setTf_userId(userSession.getUserId());
		userInfo.setTf_departmentId(userSession.getDepartmentId());
		userInfo.setTf_departmentName(userSession.getDepartment());
		result.setUserInfo(userInfo);

		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setTf_serviceDepartment(systemset.getTf_serviceDepartment());
		serviceInfo.setTf_serviceMen(systemset.getTf_serviceMen());
		serviceInfo.setTf_serviceTelnumber(systemset.getTf_serviceTelnumber());
		serviceInfo.setTf_serviceFaxnumber(systemset.getTf_serviceFaxnumber());
		serviceInfo.setTf_serviceQQ(systemset.getTf_serviceQQ());
		serviceInfo.setTf_serviceEmail(systemset.getTf_serviceEmail());
		serviceInfo.setTf_serviceHomepage(systemset.getTf_serviceHomepage());
		serviceInfo.setTf_copyrightInfo(systeminfo.getTf_copyrightInfo());
		serviceInfo.setTf_copyrightOwner(systeminfo.getTf_copyrightOwner());

		result.setServiceInfo(serviceInfo);

		
		// 加入菜单分组
		result.setMenus((List<_Menu>) getSystemBaseDAO().findByPropertyAllSort(_Menu.class, "tf_orderId",
				"ASC", "tf_pid", null, "tf_orderId", "ASC"));

		for (_Menu menu : result.getMenus()) {
			menu.toString();
		}

		// 加入所有的权限设置
		result.setRoleInfo(getRoleInfo(request));

		
		
		// 没有显示权限的模块,并且是系统模块都不进去了
		result.setModules(new HashSet<_Module>());
		for (_Module module : getModules()) {
			if (!module.getTf_isSystem())
				result.getModules().add(module);
		}
		for (_UserRoleDetail roledetail : result.getRoleInfo().getTf_userRoleDetails()) {
			if (roledetail.getTf_allowBrowse() > 0)
				result.getModules().add(getModuleWithId(roledetail.getTf_moduleId()));
			// 如果该模块有父模块，那么也将父模块的信息加入
		}
		
		
		
		return result;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public synchronized RoleInfo getRoleInfo(HttpServletRequest request) {

		UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());

		RoleInfo info = new RoleInfo();

		info.setTf_userRoleDetails((List<_UserRoleDetail>) systemBaseDAO.findByProperty(
				_UserRoleDetail.class, "tf_userId", userSession.getUserId()));
		info.setTf_ReportGroups((List<_ReportGroup>) getSystemBaseDAO().findAll(_ReportGroup.class));

		info.setTf_userFieldHiddenRoleDetails((List<_UserFieldHiddenRoleDetail>) systemBaseDAO
				.findByProperty(_UserFieldHiddenRoleDetail.class, "tf_userId", userSession.getUserId()));

		info.setTf_userFieldReadonlyRoleDetails((List<_UserFieldReadonlyRoleDetail>) systemBaseDAO
				.findByProperty(_UserFieldReadonlyRoleDetail.class, "tf_userId", userSession.getUserId()));

		// 将每个模块具有权限的附加功能加进去
		for (_UserRoleDetail d : info.getTf_userRoleDetails())
			d.setUserRoleAdditions((List<_UserRoleAddition>) systemBaseDAO.findByString(
					_UserRoleAddition.class,
					"tf_moduleId='" + d.getTf_moduleId() + "' and tf_userId=" + d.getTf_userId()));

		userSession
				.setModuleApproveSqlFilters(new HashMap<String, Map<ApproveListTypeEnum, List<SqlModuleFilter>>>());
		// 加入操作员可以审批模块的第几级
		List<_ModuleApproveUser> users = (List<_ModuleApproveUser>) systemBaseDAO.findByProperty(
				_ModuleApproveUser.class, "tf_User.tf_userId", userSession.getUserId());
		for (_ModuleApproveUser user : users) {
			String moduleId = user.getTf_ModuleApprove().getTf_Module().getTf_moduleId();
			// 查找此操作员是否有此模块的审批权限，有再查找是第几级可以审批
			for (_UserRoleDetail d : info.getTf_userRoleDetails()) {
				if (d.getTf_moduleId().equals(moduleId) && d.getTf_allowApprove() > 0) {
					for (_UserRoleDetail userRoleDetail : info.getTf_userRoleDetails())
						if (userRoleDetail.getTf_moduleId().equals(moduleId))
							if (userRoleDetail.getTf_allowApprove() > 0) {
								// 此操作用户在该模块中是第几审批的
								userRoleDetail.setTf_approveOrder(user.getTf_ModuleApprove().getTf_order());
								userRoleDetail.setTf_approveLevel(user.getTf_ModuleApprove().getTf_level());
								_Module m = getModuleWithId(moduleId);
								userSession.getModuleApproveSqlFilters().put(
										m.getTf_moduleName(),
										getApproveService().genModuleApproveSqlFilter(m,
												user.getTf_ModuleApprove().getTf_order(), userSession.getUserName()));
							}
				}
			}
		}
		userSession.setTf_userRoleDetails(info.getTf_userRoleDetails());
		userSession.setTf_userFieldHiddenRoleDetails(info.getTf_userFieldHiddenRoleDetails());
		return info;

	}

	/**
	 * 取得一个模块的所有定义的数据
	 * 
	 * @param request
	 * @param moduleName
	 * @return
	 */
	public _Module getModuleInfo(HttpServletRequest request, String moduleName) {
		_Module result = getModuleWithName(moduleName);
		if (result == null)
			result = getModuleWithId(moduleName);
		if (result == null)
			result = getModuleWithAsName(moduleName);
		return result;
	}

	/**
	 * 客户端需要取得一个模块的字段定义时
	 * 
	 * @param request
	 * @param moduleId
	 * @param moduleName
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public _Module getModuleDefine(HttpServletRequest request, String moduleId, String moduleName) {

		if (moduleId != null)
			return getModuleWithId(moduleId);
		else
			return getModuleWithAsName(moduleName);
	}

	/**
	 * 在需要模块定义的时候，自动生成模块
	 * 
	 * 这个要改掉，以后改成全部要重新取得，不可以，改了要太慢
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public static synchronized List<_Module> getModules() {
		if (modules == null) {
			modules = (List<_Module>) getSystemBaseDAO().findAll(_Module.class);
			// 下面加入字段，列表，以有 form
			for (_Module module : modules)
				module.loadAllOneToMany();

			setModuleParentAndChild(modules);
			setModuleGroupFieldDefine(modules);

			// 将附加字段的信息添完整
			for (_Module module : modules) {

				// 加入自定义的grid 导航
				module.setModuleGridNavigates((List<_ModuleGridNavigate>) systemBaseDAO.findByString(
						_ModuleGridNavigate.class, "tf_moduleId='" + module.getTf_moduleId()
								+ "' and tf_enabled = 1 order by tf_order"));
				module.getModuleGridNavigates().addAll(module.getAllNavigatePaths());

				//
				for (_ModuleAdditionField field : module.getTf_moduleAdditionFields()) {
					_ModuleField f = getModuleFieldWithId(field.getTf_fieldId());
					// if (f == null)
					// continue;
					field.setTargetModuleName(f.getTf_Module().getTf_moduleName());
					_Module m = f.getTf_Module();
					switch (field.getAggregationType()) {

					case COUNT:

						field.setTf_fieldName(ModuleConstants.COUNTAHEAD + m.getTf_moduleName());
						field.setTf_title(m.shortnameOrTitle() + "个数");
						field.setTf_fieldType(FieldType.Integer.getValue());
						field.setTf_allowSummary(true);

						break;
					case SUM:

						field.setTf_fieldName(ModuleConstants.SUMAHEAD + m.getTableAsName()
								+ ModuleConstants.SEPARATOR + f.getTf_fieldName());
						field.setTf_title(m.shortnameOrTitle() + "--" + f.getTf_title() + "小计");
						field.setTf_fieldType(f.getTf_fieldType());
						field.setTf_allowSummary(true);

						break;
					default:
						// 如果是oneToone 不管是parent 还是child 都不用sum,直接用
						field.setTf_fieldName(ModuleConstants.PARENTAHEAD + m.getTableAsName()
								+ ModuleConstants.SEPARATOR + f.getTf_fieldName());
						field.setTf_title(m.shortnameOrTitle() + "--" + f.getTf_title());
						field.setTf_fieldType(f.getTf_fieldType());

						// 如果是one to one ,那么可汇总属性跟着字段走
						if (module.isModuleParentOneToOne(m.getTf_moduleName())
								|| module.isModuleChildOneToOne(m.getTf_moduleName())) {
							field.setTf_allowSummary(f.getTf_allowSummary());
							field.setTf_title(f.getTf_title());
						} else
							field.setTf_allowSummary(false);
						break;
					}
				}
			}
		}
		return modules;
	}

	private static void setModuleGroupFieldDefine(List<_Module> modules) {
		for (_Module module : modules) {
			module.setGroupFieldDefines(new ArrayList<GroupFieldDefine>());
			for (_ModuleField field : module.getTf_fields()) {
				if (field.getTf_allowGroup()) {
					GroupFieldDefine d = new GroupFieldDefine();
					d.setModuleName(module.getTf_moduleName());
					d.setModuleTitle(module.getTf_title());
					d.setFieldId(field.getTf_fieldId());
					d.setFieldTitle(field.getTf_title());
					d.setBaseField(field.isBaseField());
					module.getGroupFieldDefines().add(d);
				}
			}
		}

		Map<_Module, ArrayList<GroupFieldDefine>> groups = new HashMap<_Module, ArrayList<GroupFieldDefine>>();

		for (_Module module : modules) {
			ArrayList<GroupFieldDefine> define = new ArrayList<GroupFieldDefine>();
			define.addAll(module.getGroupFieldDefines());
			groups.put(module, define);

			for (_Module pm : module.getAllParentsList()) {
				define.addAll(pm.getGroupFieldDefines());
			}

		}

		for (_Module module : modules) {
			module.setGroupFieldDefines(groups.get(module));
		}

	}

	// 生成每个模块的id name map ，可以被子模块用来选择
	// private Map<String, Map<String, String>> getModuleIdNameMap(List<_Module>
	// modules,
	// HttpServletRequest request) {
	//
	// Map<String, Map<String, String>> moduleidmaps = new HashMap<String,
	// Map<String, String>>();
	// for (_Module module : modules)
	// if (module.getTf_needIdNameMap())
	// moduleidmaps.put(module.getTf_moduleName(),
	// getModuleDAO().getModuleWithTreeData(module.getTf_moduleName(), null,
	// request));
	// return moduleidmaps;
	// }

	/**
	 * 根据字段序号查找一个字段，并且返回该字段，在调用参数中还返回该 module
	 * 
	 * @param fieldid
	 * @param returnModule
	 * @return
	 */
	public static _ModuleField getModuleFieldWithId(Integer fieldid) {
		for (_Module module : getModules())
			for (_ModuleField field : module.getTf_fields())
				if (field.getTf_fieldId().equals(fieldid)) {
					field.setTf_Module(module);
					return field;
				}
		return null;
	}

	// 根据模块id号取得模块定义
	public static _Module getModuleWithId(String id) {
		for (_Module module : getModules())
			if (module.getTf_moduleId().equals(id))
				return module;
		return null;
	}

	// 根据模块 name 号取得模块定义
	public static _Module getModuleWithName(String name) {
		for (_Module module : getModules())
			if (module.getTf_moduleName().equals(name))
				return module;
		return null;
	}

	// 根据模块 as name 号取得模块定义
	public static _Module getModuleWithAsName(String name) {
		for (_Module module : getModules())
			if (module.getTableAsName().equals(name))
				return module;
		return null;
	}

	/**
	 * 将各个模块的父模块和子模块设置好
	 */
	private static void setModuleParentAndChild(List<_Module> modules) {
		for (_Module module : modules) {
			// 对于每一个字段，查找其字段类型是另一个Module名，如果是的话，那么此为其父类
			for (_ModuleField field : module.getTf_fields()) {
				String fieldType = field.getTf_fieldType();
				for (_Module parentmodule : modules)
					// 检查每一个模块，是否有此模块的父模块
					if (fieldType.equals(parentmodule.getTf_moduleName())) {

						if (field.isManyToOne() || field.isOneToOne()) {

							// 如果是one to one 那么，也把其加入到 par里面去

							module.getParents().add(parentmodule);
							log.debug(module.getTf_title() + "找到父模块:" + parentmodule.getTf_title());
							parentmodule.getChilds().add(module);
							parentmodule.getChildNames().add(module.getTf_moduleName());
						}
						;

						if (field.isOneToOne()) {

							module.getParentOneToOnes().add(parentmodule);
							log.debug(module.getTf_title() + "找到onetoone模块:" + parentmodule.getTf_title());
							parentmodule.getChildOneToOnes().add(module);
							parentmodule.getChildNames().add(module.getTf_moduleName());
						}
					}
			}
		}

		// 将所有的可以加入navigate的模块的模块加好
		for (_Module module : modules) {
			// 对于每一个字段，查找其字段类型是另一个Module名，如果是的话，那么此为其父类
			for (_ModuleField field : module.getTf_fields()) {
				String fieldType = field.getTf_fieldType();
				for (_Module parentmodule : modules)
					// 检查每一个模块，是否有此模块的父模块,并且要将此字段设置为可以显示在导航树
					if (field.getTf_showNavigatorTree() && fieldType.equals(parentmodule.getTf_moduleName())) {
						module.getNavigateParents().add(parentmodule);
					}
			}
		}

	}

	public static boolean isRefreshAll() {
		return isRefreshAll;
	}

	public static void setRefreshAll(boolean isRefreshAll) {
		SystemAndLoginInfoService.isRefreshAll = isRefreshAll;
	}

	public static ApproveService getApproveService() {
		if (approveService == null)
			setApproveService((ApproveService) SystemInfoService.getBean(ApproveService.class));
		return approveService;
	}

	public static void setApproveService(ApproveService approveService) {
		SystemAndLoginInfoService.approveService = approveService;
	}

	public static SystemBaseDAO getSystemBaseDAO() {
		if (systemBaseDAO == null)
			setSystemBaseDAO((SystemBaseDAO) SystemInfoService.getBean(SystemBaseDAO.class));
		return systemBaseDAO;
	}

	public static void setSystemBaseDAO(SystemBaseDAO systemBaseDAO) {
		SystemAndLoginInfoService.systemBaseDAO = systemBaseDAO;
	}

	public static ModuleDAO getModuleDAO() {
		if (moduleDAO == null)
			setModuleDAO((ModuleDAO) SystemInfoService.getBean(ModuleDAO.class));
		return moduleDAO;
	}

	public static void setModuleDAO(ModuleDAO moduleDAO) {
		SystemAndLoginInfoService.moduleDAO = moduleDAO;
	}

}
