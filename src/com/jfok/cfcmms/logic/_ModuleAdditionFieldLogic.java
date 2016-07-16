package com.jfok.cfcmms.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleAdditionField;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;


@Service
public class _ModuleAdditionFieldLogic extends BaseOperateLogic<_ModuleAdditionField> {

	private static final Log log = LogFactory.getLog(_ModuleAdditionFieldLogic.class);

	@Resource
	private SystemBaseDAO systemBaseDAO;

	// 存放所有父模块的附加字段
	private static Map<String, Map<String, List<_ModuleField>>> moduleAdditionParentFields = new HashMap<String, Map<String, List<_ModuleField>>>();

	// 存放所有子模块的count
	private static Map<String, List<String>> moduleAdditionChildCounts = new HashMap<String, List<String>>();

	// 存放所有子模块的附加sum字段
	private static Map<String, Map<String, List<_ModuleField>>> moduleAdditionChildSumFields = new HashMap<String, Map<String, List<_ModuleField>>>();

	public static void clearAllMap() {
		moduleAdditionParentFields.clear();
		moduleAdditionChildCounts.clear();
		moduleAdditionChildSumFields.clear();
	}

	/**
	 * 根据模块名称取得所有的父模块附加字段的定义
	 * 
	 * @param moduleName
	 *          要取得附加字段的模块名称
	 */
	@SuppressWarnings("unchecked")
	public synchronized Map<String, List<_ModuleField>> getModuleAdditionParentFields(
			String moduleName) {
		if (moduleAdditionParentFields.containsKey(moduleName))
			return moduleAdditionParentFields.get(moduleName);
		Map<String, List<_ModuleField>> pmodulefields = new HashMap<String, List<_ModuleField>>();
		moduleAdditionParentFields.put(moduleName, pmodulefields);

		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		List<_ModuleAdditionField> fields = (List<_ModuleAdditionField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleAdditionField.class, "tf_moduleId",
						module.getTf_moduleId(), " tf_aggregate = 'normal' ");

		for (_ModuleAdditionField field : fields) {
			_ModuleField pfield = (_ModuleField) systemBaseDAO.findById(_ModuleField.class,
					field.getTf_fieldId());
			if (!pmodulefields.containsKey(pfield.getTf_Module().getTf_moduleName()))
				pmodulefields.put(pfield.getTf_Module().getTf_moduleName(),
						new ArrayList<_ModuleField>());
			pmodulefields.get(pfield.getTf_Module().getTf_moduleName()).add(pfield);
			log.debug("加入了parent field:" + pfield.getTf_Module().getTf_moduleName() + ","
					+ pfield.getTf_title());
		}

		return pmodulefields;
	}


	/**
	 * 根据模块名称取得所有的子模块的 sum 字段
	 * 
	 * @param moduleName
	 *          要取得附加字段的模块名称
	 */
	@SuppressWarnings("unchecked")
	public synchronized Map<String, List<_ModuleField>> getModuleAdditionSumFields(
			String moduleName) {
		if (moduleAdditionChildSumFields.containsKey(moduleName))
			return moduleAdditionChildSumFields.get(moduleName);
		Map<String, List<_ModuleField>> pmodulefields = new HashMap<String, List<_ModuleField>>();
		moduleAdditionChildSumFields.put(moduleName, pmodulefields);

		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		List<_ModuleAdditionField> fields = (List<_ModuleAdditionField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleAdditionField.class, "tf_moduleId",
						module.getTf_moduleId(), " tf_aggregate = 'sum' ");

		for (_ModuleAdditionField field : fields) {
			_ModuleField cfield = (_ModuleField) systemBaseDAO.findById(_ModuleField.class,
					field.getTf_fieldId());
			if (!pmodulefields.containsKey(cfield.getTf_Module().getTf_moduleName()))
				pmodulefields.put(cfield.getTf_Module().getTf_moduleName(),
						new ArrayList<_ModuleField>());
			pmodulefields.get(cfield.getTf_Module().getTf_moduleName()).add(cfield);
			log.debug("加入了child sum field:" + cfield.getTf_Module().getTf_moduleName() + ","
					+ cfield.getTf_title());
		}
		return pmodulefields;
	}

	
	
	
	
	/**
	 * 根据模块名称取得所有的子模块的聚合 count
	 * 
	 * @param moduleName
	 *          要取得附加字段的模块名称
	 */
	@SuppressWarnings("unchecked")
	public synchronized List<String> getModuleAdditionCountModule(String moduleName) {
		if (moduleAdditionChildCounts.containsKey(moduleName))
			return moduleAdditionChildCounts.get(moduleName);
		List<String> pmodulefields = new ArrayList<String>();
		moduleAdditionChildCounts.put(moduleName, pmodulefields);
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
		List<_ModuleAdditionField> fields = (List<_ModuleAdditionField>) systemBaseDAO
				.findByPropertyWithOtherCondition(_ModuleAdditionField.class, "tf_moduleId",
						module.getTf_moduleId(), " tf_aggregate = 'count' ");
		for (_ModuleAdditionField field : fields) {
			_ModuleField pfield = (_ModuleField) systemBaseDAO.findById(_ModuleField.class,
					field.getTf_fieldId());
			pmodulefields.add(pfield.getTf_Module().getTf_moduleName());
			log.debug("加入了Child count module:" + pfield.getTf_Module().getTf_moduleName());
		}
		return pmodulefields;
	}

}
