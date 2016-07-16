package com.jfok.cfcmms.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;

/**
 * 对有主键是分级安排的模块的处理logic,在模块新增，修改，删除主键时检查有效性
 * 
 * @author jiangfeng
 * 
 */

@Service
public class CodeLevelModuleLogic {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	/**
	 * 在某个模块新增了一条记录时，检查此记录的id，是否符合规范
	 * 
	 * @param module
	 * @param keyid
	 * @return
	 */
	public String addCodeLevelModuleKey(_Module module, String keyid) {

		if (!isCodeLengthRight(module.getTf_codeLevel(), keyid))
			return "代码的长度不符合级次规范:" + module.getTf_codeLevel();
		else // 查找是否有上级代码了
		if (keyid.length() != getThisLevellen(module.getTf_codeLevel(), 0)) // 如果不是0顶级长度
		{
			int parentl = getKeyLevel(module.getTf_codeLevel(), keyid.length()) - 1;
			String parentkey = keyid.substring(0, getcodeLevellen(module.getTf_codeLevel(), parentl));
			if (systemBaseDAO.findById(
					ModuleServiceFunction.getModuleBeanClass(module.getTf_moduleName()), parentkey) == null)
				return "代码:[" + keyid + "]未找到其父代码[" + parentkey + "]的记录值!";
		}
		return null;
	}

	public String deleteCodeLevelModuleKey(_Module module, String keyid) {
		List<?> result = systemBaseDAO.findByLikeProperty(module.getTf_moduleName(),
				module.getTf_primaryKey(), keyid + "%");
		String name = null;
		if (result.size() > 1) {
			try {
				name = CommonFunction.getProperty(result.get(0), module.getTf_nameFields()).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return module.getTf_title() + ":【" + name + "】下有" + String.valueOf(result.size() - 1)
					+ "条子记录，请先删除所有子记录!";
		} else
			return null;
	}

	/**
	 * 主键更换的时候，先判断能否删，再判断能否加
	 * 
	 * @param module
	 * @param oldkeyid
	 * @param newkeyid
	 * @return
	 */
	public String replaceCodeLevelModuleKey(_Module module, String oldkeyid, String newkeyid) {

		String result = deleteCodeLevelModuleKey(module, oldkeyid);
		if (result != null)
			return result;
		return addCodeLevelModuleKey(module, newkeyid);

	}

	/**
	 * 检查一个keyid 是否符合 code level
	 * 
	 * @param codeLevel
	 * @param keyid
	 * @return true, 符合长度，false 不符合长度要求
	 */
	private boolean isCodeLengthRight(String codeLevel, String keyid) {
		if (keyid == null)
			return false;
		int l = keyid.length();
		for (int i = 0; i < getCodeLevelNum(codeLevel); i++)
			if (l == getcodeLevellen(codeLevel, i))
				return true;
		return false;
	}

	/**
	 * 取得一个长度为num 是第几级
	 * 
	 * @param codeLevel
	 * @param num
	 * @return
	 */
	private int getKeyLevel(String codeLevel, int num) {
		for (int i = 0; i < getCodeLevelNum(codeLevel); i++)
			if (num == getcodeLevellen(codeLevel, i))
				return i;
		return -1;
	}

	// 返回code level 共有几级
	public int getCodeLevelNum(String codeLevel) {

		return codeLevel.split(",").length;

	}

	// 返回某一级别的code code level 长度
	public int getThisLevellen(String codeLevel, int level) {
		return Integer.parseInt(codeLevel.split(",")[level]);
	}

	// 返回某一级别的code code level 总长度
	public int getcodeLevellen(String codeLevel, int level) {
		String[] levels = codeLevel.split(",");
		int result = 0;
		for (int i = 0; i <= level; i++)
			result += Integer.parseInt(levels[i]);
		return result;
	}

}
