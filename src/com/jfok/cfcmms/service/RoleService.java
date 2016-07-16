package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.authority._Popedom;
import com.jfok.cfcmms.hibernate.system.authority._Role;
import com.jfok.cfcmms.hibernate.system.authority._RoleModuleAddition;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleAddition;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleAdditionFunction;
import com.jfok.cfcmms.hibernate.system.module._ModuleGroup;
import com.jfok.cfcmms.share.RoleAdditionFunction;
import com.jfok.cfcmms.share.RolePopedom;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.TreeValueText;
import com.jfok.cfcmms.share.ValueText;


@Service
@Transactional
public class RoleService {

	public RoleService() {

	}

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModuleDAO moduleDAO;

	// @Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<_ModuleGroup> getRolePopedoms(String roleId) {
		List<_ModuleGroup> result = (List<_ModuleGroup>) systemBaseDAO.findAll(_ModuleGroup.class);
		List<_Popedom> popedoms = (List<_Popedom>) systemBaseDAO.findByProperty(_Popedom.class,
				"tf_roleId", roleId);
		// 所有的模块分组
		for (_ModuleGroup group : result) {
			group.setPopedoms(new ArrayList<RolePopedom>());
			// 模块分组下的所有模块
			List<_Module> modules = (List<_Module>) systemBaseDAO.findByPropertyAllSort(_Module.class,
					"tf_moduleId", "asc", "tf_ModuleGroup.tf_moduleGroupId", group.getTf_moduleGroupId(),
					null, null);

			for (_Module module : modules) {
				if (!module.getTf_isEnable())
					continue;
				// 模块的权限
				RolePopedom rp = new RolePopedom(module);
				_Popedom popedom = getPopedom(popedoms, module.getTf_moduleId());
				rp.SetSavedPopedom(popedom);

				// 加入模块的附加的功能按钮的权限
				List<_ModuleAdditionFunction> functions = (List<_ModuleAdditionFunction>) systemBaseDAO
						.findByPropertyAllSort(_ModuleAdditionFunction.class, "tf_moduleAdditionFunctionId",
								"asc", "tf_Module.tf_moduleId", module.getTf_moduleId(), null, null);
				for (int i = functions.size() - 1; i >= 0; i--)
					if (functions.get(i).getTf_hasEnable() == false)
						functions.remove(i);

				if (functions.size() > 0) {
					// 查找附加功能是否已被选中
					List<_RoleModuleAddition> additions = (List<_RoleModuleAddition>) systemBaseDAO
							.findByProperty(_RoleModuleAddition.class, "tf_Role.tf_roleId", roleId);
					rp.setAdditionFunctions(new ArrayList<RoleAdditionFunction>());
					for (_ModuleAdditionFunction function : functions) {
						RoleAdditionFunction f = new RoleAdditionFunction();
						f.setId(function.getTf_moduleAdditionFunctionId());
						f.setTitle(function.getTf_title());
						for (_RoleModuleAddition a : additions)
							if (a.getTf_ModuleAdditionFunction().getTf_moduleAdditionFunctionId()
									.equals(f.getId())) {
								f.setChecked(true);
								break;
							}
						rp.getAdditionFunctions().add(f);
					}
				}
				group.getPopedoms().add(rp);
			}
		}
		return result;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	// @Override
	@SuppressWarnings("unchecked")
	public Boolean setRolePopedoms(String roleId, RolePopedom rolePopedoms[], String additionids) {

		List<_Popedom> popedoms = (List<_Popedom>) systemBaseDAO.findByProperty(_Popedom.class,
				"tf_roleId", roleId);
		for (_Popedom popedom : popedoms) {
			systemBaseDAO.delete(popedom);
		}

		List<_RoleModuleAddition> functions = (List<_RoleModuleAddition>) systemBaseDAO.findByProperty(
				_RoleModuleAddition.class, "tf_Role.tf_roleId", roleId);
		for (_RoleModuleAddition function : functions) {
			systemBaseDAO.delete(function);
		}

		systemBaseDAO.getHibernateTemplate().flush();

		for (RolePopedom rolePopedom : rolePopedoms) {

			_Popedom rp = new _Popedom();
			rp.setTf_roleId(roleId);
			rp.setTf_moduleId(rolePopedom.getTf_moduleId());
			rp.setTf_allowBrowse(rolePopedom.getTf_allowBrowse() == null ? 0 : rolePopedom
					.getTf_allowBrowse() ? 1 : 0);
			rp.setTf_allowInsert(rolePopedom.getTf_allowInsert() == null ? 0 : rolePopedom
					.getTf_allowInsert() ? 1 : 0);
			rp.setTf_allowEdit(rolePopedom.getTf_allowEdit() == null ? 0
					: rolePopedom.getTf_allowEdit() ? 1 : 0);
			rp.setTf_allowDelete(rolePopedom.getTf_allowDelete() == null ? 0 : rolePopedom
					.getTf_allowDelete() ? 1 : 0);
			rp.setTf_allowExec(rolePopedom.getTf_allowExec() == null ? 0
					: rolePopedom.getTf_allowExec() ? 1 : 0);
			rp.setTf_allowAuditing(rolePopedom.getTf_allowAuditing() == null ? 0 : rolePopedom
					.getTf_allowAuditing() ? 1 : 0);
			rp.setTf_allowApprove(rolePopedom.getTf_allowApprove() == null ? 0 : rolePopedom
					.getTf_allowApprove() ? 1 : 0);

			rp.setTf_allowEditDirect(rolePopedom.getTf_allowEditDirect() == null ? 0 : rolePopedom
					.getTf_allowEditDirect() ? 1 : 0);

			rp.setTf_allowPayment(rolePopedom.getTf_allowPayment() == null ? 0 : rolePopedom
					.getTf_allowPayment() ? 1 : 0);

			rp.setTf_attachmentBrowse(rolePopedom.getTf_attachmentBrowse() == null ? 0 : rolePopedom
					.getTf_attachmentBrowse() ? 1 : 0);
			rp.setTf_attachmentInsert(rolePopedom.getTf_attachmentBrowse() == null ? 0 : rolePopedom
					.getTf_attachmentInsert() ? 1 : 0);
			rp.setTf_attachmentEdit(rolePopedom.getTf_attachmentBrowse() == null ? 0 : rolePopedom
					.getTf_attachmentEdit() ? 1 : 0);
			rp.setTf_attachmentDelete(rolePopedom.getTf_attachmentBrowse() == null ? 0 : rolePopedom
					.getTf_attachmentDelete() ? 1 : 0);

			systemBaseDAO.save(rp);
		}

		// 附加功能的保存
		if (additionids != null && additionids.length() > 0) {
			String ids[] = additionids.split(",");
			for (String id : ids) {
				_RoleModuleAddition addition = new _RoleModuleAddition();
				addition.setTf_Role(new _Role(roleId));
				addition.setTf_ModuleAdditionFunction(new _ModuleAdditionFunction(Integer.parseInt(id)));
				systemBaseDAO.save(addition);
			}
		}

		return true;
	}

	private _Popedom getPopedom(List<_Popedom> popedoms, String moduleId) {
		for (_Popedom popedom : popedoms) {
			if (popedom.getTf_moduleId().equals(moduleId))
				return popedom;
		}
		return null;
	}

	private _UserRoleDetail getUserPopedom(List<_UserRoleDetail> popedoms, String moduleId) {
		for (_UserRoleDetail popedom : popedoms) {
			if (popedom.getTf_moduleId().equals(moduleId))
				return popedom;
		}
		return null;
	}

	/**
	 * 取得某个用户的所有的权限设置，返回，不能修改
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<_ModuleGroup> getUserPopedoms(Integer userId) {

		List<_ModuleGroup> result = (List<_ModuleGroup>) systemBaseDAO.findAll(_ModuleGroup.class);
		List<_UserRoleDetail> popedoms = (List<_UserRoleDetail>) systemBaseDAO.findByProperty(
				_UserRoleDetail.class, "tf_userId", userId);

		// 所有的模块分组
		for (_ModuleGroup group : result) {
			group.setPopedoms(new ArrayList<RolePopedom>());
			// 模块分组下的所有模块
			List<_Module> modules = (List<_Module>) systemBaseDAO.findByPropertyAllSort(_Module.class,
					"tf_moduleId", "asc", "tf_ModuleGroup.tf_moduleGroupId", group.getTf_moduleGroupId(),
					null, null);

			for (_Module module : modules) {
				if (!module.getTf_isEnable())
					continue;
				// 模块的权限

				RolePopedom rp = new RolePopedom(module);
				_UserRoleDetail popedom = getUserPopedom(popedoms, module.getTf_moduleId());
				if (popedom == null || popedom.getTf_allowBrowse() == 0)
					continue;
				rp.SetSavedPopedom(popedom);

				// 加入模块的附加的功能按钮的权限
				List<_ModuleAdditionFunction> functions = (List<_ModuleAdditionFunction>) systemBaseDAO
						.findByPropertyAllSort(_ModuleAdditionFunction.class, "tf_moduleAdditionFunctionId",
								"asc", "tf_Module.tf_moduleId", module.getTf_moduleId(), null, null);
				for (int i = functions.size() - 1; i >= 0; i--)
					if (functions.get(i).getTf_hasEnable() == false)
						functions.remove(i);

				if (functions.size() > 0) {
					// 查找附加功能是否已被选中
					// List<_RoleModuleAddition> additions = (List<_RoleModuleAddition>)
					// systemBaseDAO
					// .findByProperty(_RoleModuleAddition.class, "tf_Role.tf_roleId",
					// roleId);

					List<_UserRoleAddition> additions = (List<_UserRoleAddition>) systemBaseDAO.findByString(
							_UserRoleAddition.class, " tf_userId = " + userId.toString());

					rp.setAdditionFunctions(new ArrayList<RoleAdditionFunction>());
					for (_ModuleAdditionFunction function : functions) {
						RoleAdditionFunction f = new RoleAdditionFunction();
						f.setId(function.getTf_moduleAdditionFunctionId());
						f.setTitle(function.getTf_title());
						for (_UserRoleAddition a : additions)
							if (a.getTf_moduleAdditionFunctionId().toString().equals(f.getId().toString())) {
								f.setChecked(true);
								break;
							}
						rp.getAdditionFunctions().add(f);
					}
				}

				group.getPopedoms().add(rp);
			}
		}
		for (int i = result.size() - 1; i >= 0; i--)
			if (result.get(i).getPopedoms().size() == 0)
				result.remove(i);
		return result;
	}

	public List<TreeNodeRecordChecked> getModuleTreeData(String moduleId, Integer roleId,
			HttpServletRequest request) {

		_Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
		List<TreeNodeRecordChecked> results = new ArrayList<TreeNodeRecordChecked>();
		if (module.getTf_codeLevel() == null || module.getTf_codeLevel().length() == 0) {
			List<ValueText> values = moduleDAO.getModuleWithComboData(module.getTf_moduleName(), null, request);
			for (ValueText value : values) {
				TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
						module.getTableAsName(), value.getText(), module.getTf_primaryKey(), value.getValue(),
						null, module.isCodeLevel());
				record.setLeaf(true);
				results.add(record);
			}
		} else {
			List<TreeValueText> values = moduleDAO.getModuleWithTreeData(module.getTf_moduleName(), true, null, request);
			for (TreeValueText value : values) {
				TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
						module.getTableAsName(), value.getText(), module.getTf_primaryKey(), value.getValue(),
						null, module.isCodeLevel());
				record.setLeaf(true);
				results.add(record);
				addSub(value.getChildren(), record, module);
			}
		}
		return results;
	}

	// 将子级item加入
	private void addSub(List<TreeValueText> subvalues, TreeNodeRecordChecked p, _Module module) {
		if (subvalues != null && subvalues.size() > 0) {
			p.setLeaf(false);
			for (TreeValueText value : subvalues) {
				TreeNodeRecordChecked record = new TreeNodeRecordChecked(module.getTf_moduleName(),
						module.getTableAsName(), value.getText(), module.getTf_primaryKey(), value.getValue(),
						null, module.isCodeLevel());
				p.getChildren().add(record);
				addSub(value.getChildren(), record, module);
			}
		}
	}
}
