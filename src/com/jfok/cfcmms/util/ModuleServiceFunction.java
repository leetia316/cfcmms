package com.jfok.cfcmms.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;

import ognl.Ognl;
import ognl.OgnlException;

public class ModuleServiceFunction {

	private static final String HIBERNATEPACKAGEPATH_ROOT = "com.jfok.cfcmms.hibernate";

	private static List<String> beanDirs = null;

	// 所有的 hibernate class在第一次查找的时候，都加入到此map中
	private static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

	public static List<String> getBeanDirs() {
		if (beanDirs == null) {
			beanDirs = new ArrayList<String>();
			beanDirs.add(HIBERNATEPACKAGEPATH_ROOT);
			List<String> subPackages = getPackageName(HIBERNATEPACKAGEPATH_ROOT);
			for (String s : subPackages)
				beanDirs.add(HIBERNATEPACKAGEPATH_ROOT + "." + s);
		}
		return beanDirs;
	}

	public static Class<?> getModuleBeanClass(String moduleName) {

		Class<?> moduleClass = classMap.get(moduleName);
		if (moduleClass != null)
			return moduleClass;
		for (String dir : getBeanDirs()) {
			try {
				moduleClass = Class.forName(dir + "." + moduleName);
				classMap.put(moduleName, moduleClass);
				break;
			} catch (ClassNotFoundException e) {
			}
		}
		if (moduleClass == null)
			System.out.println("未找到：" + moduleName + "的定义文件");
		return moduleClass;
	}

	/**
	 * 根据包名，取得该包下的下一级目录的名字，没有考虑递归
	 * 
	 * @param packageName
	 * @return
	 */
	private static List<String> getPackageName(String packageName) {
		List<String> packages = new ArrayList<String>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", File.separator); // "/"
		URL url = loader.getResource(packagePath);
		if (url != null) {
			String type = url.getProtocol();
			if (type.equals("file")) {
				File file = new File(url.getPath().replace("%20", " ")); // 如果是window系统，空格用%20来表示的
				File[] childFiles = file.listFiles();
				for (File childFile : childFiles) {
					if (childFile.isDirectory()) {
						int c = childFile.getPath().lastIndexOf(File.separator);
						String p = childFile.getPath().substring(c + 1);
						packages.add(p);
						getSubPackageName(packages, childFile.getPath(), p + ".");
					}
				}
			}
		}
//		for (String type1 : packages)
//			System.out.println("packages:" + type1);

		return packages;
	}

	/**
	 * 检查某个包下面是否还有子包存在，如果有的话，也加入到检测bean的目录之中，这是一个递归的过程
	 * 
	 * @param packages
	 * @param dirname
	 * @return
	 */
	private static void getSubPackageName(List<String> packages, String dirname, String parent) {

		File file = new File(dirname.replace("%20", " ")); // 如果是window系统，空格用%20来表示的
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				int c = childFile.getPath().lastIndexOf(File.separator);
				String p = parent + childFile.getPath().substring(c + 1);
				packages.add(p);
				getSubPackageName(packages, childFile.getPath(), p);
			}
		}

	}

	// ognl 转换成Date 有问题，暂时用这个解决
	public static void setValueToRecord(String key, Object record, Object object)
			throws OgnlException {
		try {
			// System.out.println("改之前：" + record);
			Ognl.setValue(key, record, ModuleServiceFunction.isNull(object));
			// System.out.println("改之后：" + record);

		} catch (OgnlException e) {
			// e.getReason().getMessage()
			// Unable to convert type java.lang.String of 2012-12-12 to type of
			// java.util.Date
			// e.printStackTrace();
			if (e.getReason() != null && e.getReason().getMessage() != null
					&& e.getReason().getMessage().endsWith("Date"))
				Ognl.setValue(key, record, TypeChange.StringToDate(object));
			else {
				e.printStackTrace();
				// throw e;
			}
		}
	}

	// 删除记录时，判断是不是被外键约束阻止了
	public static String addPK_ConstraintMessage(String e, String moduleName) {
		Pattern pattern = Pattern.compile("FK_" + "[A-z|_|0-9]*_" + moduleName);
		Matcher matcher = pattern.matcher(e);
		String finder = null;
		while (matcher.find()) {
			finder = e
					.substring(matcher.start() + 3, matcher.end() - moduleName.length() - 1);
			break;
		}
		// DELETE 语句与 REFERENCE
		// 约束"FK_Project_Global"冲突。该冲突发生于数据库"pm"，表"dbo.Project", column
		// 'tf_globalId'。
		if (finder != null) {
			_Module module = SystemAndLoginInfoService.getModuleWithName(finder);
			if (module != null)
				return "与本记录相关联的『" + module.getTf_title() + "』数据没有全部清空";
		}
		return null;
	}

	// 根据出错信息，判断是不是索引键重复
	public static boolean addIX_UniqueMessage(String cause,
			Map<String, String> errorMessage, _Module module) {

		Pattern pattern = Pattern.compile("IX_" + module.getTf_moduleName() + "_[A-z|_|0-9]*");
		Matcher matcher = pattern.matcher(cause);
		String finder = null;
		while (matcher.find()) {
			finder = cause.substring(matcher.start(), matcher.end());
			break;
		}
		// 不能在具有唯一索引 'IX_Project_tf_code' 的对象 'dbo.Project' 中插入重复键的行。
		// 找到错误信息，如果有 IX_(moduleName)_ ,那么找到后面的字段，再对应字段名称，
		// 不能在具有唯一性的(模块，字段)中插入重复键值。
		if (finder != null) {
			finder = finder.replace("IX_" + module.getTf_moduleName() + "_", "");
			_ModuleField moduleField = module.getModuleFieldByFieldName(finder);
			if (moduleField != null) {
				errorMessage.put(moduleField.getTf_fieldName(), "不能在具有唯一性的字段『" + moduleField.getTf_title()
						+ "』中插入重复值。");
				return true;
			}
		}
		return false;
	}

	// 根据出错信息，将主键重复，或者 值为空的 错误提示加到 map中
	public static void addExceptionCauseToErrorMessage(String cause,
			Map<String, String> errorMessage, String idFieldName) {
		// 错误信息1:Column 'tf_int' cannot be null
		// 错误信息2:Duplicate entry '80' for key 'PRIMARY'
		if (cause.toLowerCase().indexOf("primary") != -1)
			errorMessage.put(idFieldName, "插入记录的主键值与数据库中原有的值重复!");
		else
			errorMessage.put("error", cause);
	}

	public static Object isNull(Object object) {
		if (object == null || object.toString().toLowerCase().equals("null"))
			return null;
		else if (object.toString().toLowerCase().equals("false"))
			return false;
		else
			return object;
	}
}
