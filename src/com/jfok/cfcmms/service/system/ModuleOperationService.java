package com.jfok.cfcmms.service.system;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import ognl.Ognl;
import ognl.OgnlException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridSchemeGroupField;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;

@Service
public class ModuleOperationService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult saveGridColumnWidth(String param) {
		String s[] = param.split(",");
		for (String column : s) {
			String id_width[] = column.split(":");
			try {
				_ModuleGridSchemeGroupField field = (_ModuleGridSchemeGroupField) systemBaseDAO.findById(
						_ModuleGridSchemeGroupField.class, id_width[0]);
				if (field != null && (field.getTf_columnWidth() == null || field.getTf_columnWidth() != -1)) {
					Integer width = Integer.parseInt(id_width[1]) + 4;
					width = width - width % 5;
					field.setTf_columnWidth(width);
					systemBaseDAO.attachDirty(field, null);
				}
			} catch (Exception e) {
			}
		}
		return new ActionResult();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult saveGridColumnOrder(String param) {
		String s[] = param.split(",");
		int order;
		Map<Integer, Integer> orders = new HashMap<Integer, Integer>();
		for (String id : s) {
			_ModuleGridSchemeGroupField field = (_ModuleGridSchemeGroupField) systemBaseDAO.findById(
					_ModuleGridSchemeGroupField.class, id);
			Integer groupid = field.getTf_ModuleGridSchemeGroup().getTf_gridGroupId();
			if (orders.containsKey(groupid))
				order = orders.get(groupid);
			else {
				order = 10;
				orders.put(groupid, order);
			}
			field.setTf_gridFieldOrder(order);
			order += 10;
			orders.put(groupid, order);
			systemBaseDAO.attachDirty(field, null);
		}
		return new ActionResult();
	}

	/**
	 * 保存记录顺序号，顺序号可以改变的记录，可以拖放改变位置
	 * 
	 * @param moduleName
	 * @param param
	 * @return
	 * @throws OgnlException
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ActionResult saveRecordOrder(String moduleName, String param) throws OgnlException {
		String ids[] = param.split(",");
		int order = 10;
		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		for (String id : ids) {
			Object object = systemBaseDAO.findById(beanClass, id);
			Ognl.setValue(module.getTf_orderField(), object, order);
			systemBaseDAO.attachDirty(object, null);
			order += 10;
		}
		return new ActionResult();
	}

}
