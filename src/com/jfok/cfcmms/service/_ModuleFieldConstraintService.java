package com.jfok.cfcmms.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ognl.Ognl;
import ognl.OgnlException;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleFieldConstraint;


@Service
/**
 * 模块字段平衡关系的服务，用来判断新增或修改的的数据是否满足平衡关系
 * @author jiangfeng
 *
 */
public class _ModuleFieldConstraintService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@SuppressWarnings("unchecked")
	/**
	 * 检查模块的新增或修改的记录有无平衡关系
	 * 
	 * @param module        模块的定义bean
	 * @param record				要新增或修改的记录bean
	 * @param errorMessage  出错信息写入此处
	 * @return true   			无错误
	 * 				 false				有错误
	 */
	public Boolean moduleFieldConstraintValid(_Module module, Object record,
			Map<String, String> errorMessage) {

		if (module.getModuleFieldConstraints() == null) {
			module.setModuleFieldConstraints((List<_ModuleFieldConstraint>) systemBaseDAO
					.findByPropertyAllSort(_ModuleFieldConstraint.class, "tf_order", "asc",
							"tf_Module.tf_moduleId", module.getTf_moduleId(), null, null));
		}
		if (module.getModuleFieldConstraints().size() > 0) {
			int c = 0;
			for (_ModuleFieldConstraint fc : module.getModuleFieldConstraints()) {
				if (fc.getTf_isEnable()) {
					try {
						if (((Boolean) Ognl.getValue(fc.getTf_express(), record)) == false) {
							c++;
							errorMessage.put(fc.getTf_errorMessageField(), fc.getTf_errorMessage());
						}
					} catch (OgnlException e) {
						// 表达式公式写错了
						e.printStackTrace();
					}
				}
			}
			return c == 0;
		} else
			return true;
	}

}
