package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.setting._NumberGroup;
import com.jfok.cfcmms.hibernate.system.setting._NumberGroupDetail;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleGridNavigate;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.ValueText;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.util.TypeChange;

@Service
/**
 * 取得模块的控制树的值
 * 
 * @author jfok
 *
 */
public class NavigateTreeService {

	@Resource
	private ModuleDAO moduleDAO;

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ApproveService approveService;

	/**
	 * gridFilterData 是传进来当前模块的约束，有的话 ，也加在tree 里面
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<TreeNodeRecord> getTreeRecords(String moduleName, String title, String navigatePath,
		Boolean isBaseField, Boolean cascading, Boolean isContainNullRecord,
		SqlModuleFilter parentFilter, _NumberGroup numberGroup, String type, Boolean reverseOrder,
		HttpServletRequest request) {
	// System.out.println(gridFilterData.getParentModuleFilter());

	GridFilterData gridFilterData = new GridFilterData();

	// 父模块约束的加入
	gridFilterData.setParentModuleFilter(parentFilter);

	String[] parentModuleNames = navigatePath.split("--");
	List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();

	_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

	if (navigatePath.equals("approvetype"))
		return approveService.getApproveTypeTree(moduleName, title, parentFilter,
			isContainNullRecord, request);

	_ModuleField field = module.getModuleFieldByFieldName(navigatePath);
	// 如果是某个字段中的所有值，不是manytoone 的要加入导航树中 ， 只有单个字段的才加mode ,type ,以后再考虑层级的
	if (field != null && field.isBaseField()) {
		_ModuleGridNavigate parentsPath = new _ModuleGridNavigate(navigatePath, title, numberGroup,
			type, true);
		parentsPath.setTf_reverseOrder(reverseOrder);
		return getBaseFieldTreeRecords(moduleName, parentsPath, cascading, isContainNullRecord,
			gridFilterData, request);
	}

	if (cascading) {
		// tree 层叠排列
		String firstParentName = parentModuleNames[0];
		// 去掉了最上一级的 parent 的路径
		String subParentsPath = null;
		if (parentModuleNames.length > 1) {
		subParentsPath = parentModuleNames[1];
		for (int i = 2; i < parentModuleNames.length; i++)
			subParentsPath = subParentsPath + "--" + parentModuleNames[i];
		}
		_Module parentModule = SystemAndLoginInfoService.getModuleWithName(firstParentName);
		// if (parentModule.getParents().size() == 0) {
		List<ValueText> records = moduleDAO.getModuleWithComboData(firstParentName, null, request);
		TreeNodeRecord root = new TreeNodeRecord(firstParentName, parentModule.getTableAsName(),
			parentModule.getTf_title(), null, null, null, null);
		root.setLeaf(false);
		root.setExpanded(true);
		result.add(root);

		Map<String, Integer> groupCount = moduleDAO.getParentModuleGroupByRecord(moduleName,
			parentModule.getTableAsName() + "." + parentModule.getTf_primaryKey(), null,
			gridFilterData, null, request);
		Integer sum = 0;
		List<TreeNodeRecord> allRecords = addRecordToTree(parentModule, records, groupCount);
		for (TreeNodeRecord record : allRecords)
		sum += record.getCount();
		root.setCount(sum);
		root.getChildren().addAll(allRecords);
		// for (TreeNodeRecord record : allRecords) {
		// record.setLeaf(false);
		// record.getChildren().addAll(
		diGui_addRecordWithParentFilter(allRecords, moduleName, subParentsPath, firstParentName,
			null, isContainNullRecord, gridFilterData, request);

		// }
		if (!isContainNullRecord)
		deleteNullRecord(result);
		// 如果只有一个子元素，便如 部门－－ 开发区，把部门那一级拿掉
		if (result.size() > 0 && root.getChildren().size() == 1 && parentModuleNames.length > 1) {
		result.add(root.getChildren().get(0));
		result.remove(root);
		}

		// 如果子节点下面没有最后一个节点的数据，那么就不用显示了，例如合同的导航里，如果下面没有标段，那就连工程也不要显示了。
		if (parentModuleNames.length > 1) {
		String lastPath = parentModuleNames[parentModuleNames.length - 1];
		// 搜索每个节点的所有子节点下面是否有最后一个模块的值

		for (TreeNodeRecord record : result) {
			checkLastLevelModuleData(null, record, lastPath);
		}

		}

	} else {
		// 每一级都并排
		for (String mName : parentModuleNames) {
		_Module parentModule = SystemAndLoginInfoService.getModuleWithName(mName);
		List<ValueText> records = moduleDAO.getModuleWithComboData(mName, null, request);
		Map<String, Integer> groupCount = moduleDAO.getParentModuleGroupByRecord(moduleName,
			parentModule.getTableAsName() + "." + parentModule.getTf_primaryKey(), null,
			gridFilterData, null, request);

		TreeNodeRecord root = new TreeNodeRecord(mName, parentModule.getTableAsName(),
			parentModule.getTf_title(), null, null, null, null);
		root.setLeaf(false);
		result.add(root);

		Integer sum = 0;
		List<TreeNodeRecord> allRecords = addRecordToTree(parentModule, records, groupCount);
		for (TreeNodeRecord record : allRecords)
			sum += record.getCount();
		root.setCount(sum);

		if (!isContainNullRecord)
			deleteNullRecord(allRecords);

		root.getChildren().addAll(allRecords);
		}
	}

	checkLeaf(result);

	return result;
	}

	// 检查每一个子级下面是否包含了lastModuleName,如果不包含，就删除
	public void checkLastLevelModuleData(TreeNodeRecord parent, TreeNodeRecord record,
		String lastModuleName) {

	Boolean have = isHaveLastLevelModuleData(record, lastModuleName);

	if (have)
		; // record.setText(record.getText() + "--有");
	else {
		parent.getChildren().remove(record);
		return;
	}

	for (int i = record.getChildren().size() - 1; i >= 0; i--) {
		TreeNodeRecord subRecord = record.getChildren().get(i);
		checkLastLevelModuleData(record, subRecord, lastModuleName);
	}

	}

	/**
	 * 检查当前节点下面有没有最末级的数据，如果没有，那么就把这一个节点删除
	 */

	public boolean isHaveLastLevelModuleData(TreeNodeRecord record, String lastModuleName) {

	// 如果是子节点，那么判断是否是最末级的模块
	if (record.hasChildren().equals(false)) {
		if (record.getModuleName().equals(lastModuleName))
		return true;
		else
		return false;
	} else
		for (TreeNodeRecord subRecord : record.getChildren()) {
		if (isHaveLastLevelModuleData(subRecord, lastModuleName))
			// 如果不是子节点的话，判断这个子节点下面是不是有当前的末级科目的值
			return true;
		}
	return false;
	}

	/**
	 * 递归加入treenode record 中的下级模块的数据
	 * 
	 * @param records
	 * @param countModuleName
	 * @param parentsPath
	 * @param parentModuleName
	 * @param parentIdvalue
	 * @param isContainNullRecord
	 * @param gridFilterData
	 * @param request
	 */
	public void diGui_addRecordWithParentFilter(List<TreeNodeRecord> records,
		String countModuleName, String parentsPath, String parentModuleName, String parentIdvalue,
		Boolean isContainNullRecord, GridFilterData gridFilterData, HttpServletRequest request) {
	for (TreeNodeRecord record : records) {
		record.setLeaf(false);
		if (record.getChildren() != null && record.getChildren().size() > 0)
		diGui_addRecordWithParentFilter(record.getChildren(), countModuleName, parentsPath,
			parentModuleName, record.getFieldvalue(), isContainNullRecord, gridFilterData, request);
		record.getChildren()
			.addAll(getTreeRecordsWithParentFilter(countModuleName, parentsPath, parentModuleName,
				record.getFieldvalue(), isContainNullRecord, gridFilterData, request));

	}
	}

	// 把result中的所有node,的leaf排列一下，有childern的改为false
	private void checkLeaf(List<TreeNodeRecord> records) {
	for (TreeNodeRecord record : records)
		if (record.hasChildren()) {
		record.setLeaf(false);
		checkLeaf(record.getChildren());
		} else
		record.setLeaf(true);

	}

	// 如果不要空值的，那么把所有的空值的记录全删掉
	private void deleteNullRecord(List<TreeNodeRecord> records) {
	if (records != null)
		for (int i = records.size() - 1; i >= 0; i--) {
		TreeNodeRecord record = records.get(i);
		if (record.getCount() == 0)
			records.remove(record);
		else
			deleteNullRecord(record.getChildren());
		}
	}

	/**
	 * 
	 * @param countModuleName
	 *          是哪一个模块的树状列表
	 * @param parentsPath
	 * @param parentModuleName
	 * @param parentIdvalue
	 * @param parentNodeId
	 * @param isContainNullRecord
	 * @param gridFilterData
	 * @param result
	 */
	// 根据父模块的一个键值，来取得子模块的值，并且以此父模块的id作为parent
	public List<TreeNodeRecord> getTreeRecordsWithParentFilter(String countModuleName,
		String parentsPath, String parentModuleName, String parentIdvalue,
		Boolean isContainNullRecord, GridFilterData gridFilterData, HttpServletRequest request) {
	if (parentsPath == null)
		return new ArrayList<TreeNodeRecord>();
	String[] parentModuleNames = parentsPath.split("--");
	String moduleName = parentModuleNames[0];
	// 去掉了最上一级的 parent 的路径
	String subParentsPath = null;
	if (parentModuleNames.length > 1) {
		subParentsPath = parentModuleNames[1];
		for (int i = 2; i < parentModuleNames.length; i++)
		subParentsPath = subParentsPath + "--" + parentModuleNames[i];
	}
	_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
	_Module parentModule = SystemAndLoginInfoService.getModuleWithName(parentModuleName);
	// 生成module 查询时所限制的主键
	SqlModuleFilter filter = new SqlModuleFilter();
	filter.setModuleName(parentModuleName);
	filter.setTableAsName(parentModule.getTableAsName());
	filter.setPrimarykey(parentModule.getTf_primaryKey());
	filter.setNameField(parentModule.getTf_nameFields());
	filter.setEqualsValue(parentIdvalue);
	// filter.setIsCodeLevel(parentModule.getTf_codeLevel() != null);
	List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();
	filters.add(filter);
	List<ValueText> records = moduleDAO.getModuleWithComboData(moduleName, filters, request);

	Map<String, Integer> groupCount = moduleDAO.getParentModuleGroupByRecord(countModuleName,
		module.getTableAsName() + "." + module.getTf_primaryKey(), filter, gridFilterData, null,
		request);

	List<TreeNodeRecord> allRecords = addRecordToTree(module, records, groupCount);

	// for (TreeNodeRecord record : allRecords) {
	// record.setLeaf(false);
	// record.getChildren().addAll(
	// getTreeRecordsWithParentFilter(countModuleName, subParentsPath,
	// moduleName,
	// record.getFieldvalue(), isContainNullRecord, gridFilterData, request));
	// }

	diGui_addRecordWithParentFilter(allRecords, countModuleName, subParentsPath, moduleName, null,
		isContainNullRecord, gridFilterData, request);

	return allRecords;

	}

	private List<TreeNodeRecord> addRecordToTree(_Module module, List<ValueText> records,
		Map<String, Integer> groupCount) {

	List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();
	TreeNodeRecord nullrecord = getNullTreeNode(groupCount, module.getTf_moduleName(),
		module.getTableAsName(), module.getTableAsName(), module.getTf_primaryKey(), "未定义111");
	if (nullrecord != null)
		result.add(nullrecord);
	for (ValueText idname : records) {
		String id = idname.getValue();
		String name = idname.getText();
		Integer c = groupCount.get(id);
		TreeNodeRecord record = new TreeNodeRecord(module.getTf_moduleName(),
			module.getTableAsName(), name, module.getTf_primaryKey(), id, null,
			module.isCodeLevel());
		record.setIcon(module.getTf_moduleName());
		record.setCount(c);
		record.setFieldtitle(module.getTf_title());

		result.add(record);
	}
	// 层级排列的,把他加到最适合的一个位置
	if (module.getTf_codeLevel() != null)
		treeNodetoLevel(result);

	return result;
	}

	/**
	 * 将tree 根据id 的分组情况，来自动排列成树状,将当前节点加到最适合的位置之下
	 * 
	 * 从下往上，把结点放在离自己最近的一个父接点上
	 * 
	 * @param allRecords
	 */
	private void treeNodetoLevel(List<TreeNodeRecord> allRecords) {
	List<TreeNodeRecord> deleted = new ArrayList<TreeNodeRecord>();
	for (int i = allRecords.size() - 1; i > 0; i--) {
		// 对每一个编码，找到离其最近的上一级编码，把自己放在他的下面
		TreeNodeRecord record = allRecords.get(i);
		for (int j = i - 1; j >= 0; j--) {
		TreeNodeRecord p = allRecords.get(j);
		if (record.getFieldvalue().startsWith(p.getFieldvalue())) {
			p.getChildren().add(0, record);
			p.setCount(p.getCount() + record.getCount());
			p.setLeaf(false);
			deleted.add(record);
			break;
		}
		}
	}
	allRecords.removeAll(deleted);
	}

	/**
	 * 模块的属性中是否有null值，如果有的话，把null-- 未定义加到tree navigate中去
	 * 
	 * @param groupCount
	 * @param result
	 * @param moduleName
	 * @param asName
	 * @return
	 */
	private TreeNodeRecord getNullTreeNode(Map<String, Integer> groupCount, String moduleName,
		String asName, String parentNodeId, String primaryKey, String fieldtitle) {
	Integer c = groupCount.get(null);
	if (c == null)
		return null;
	else {
		TreeNodeRecord record = new TreeNodeRecord(moduleName, asName, "未定义", primaryKey, "null",
			null, null);
		record.setFieldtitle(fieldtitle);
		record.setCount(c);
		// parent.getChildren().add(record);
		return record;
	}
	}

	/**
	 * 根据模块数值字段，以及数值字段分组的描述，对数值字段进行分组，加入到导航树中
	 * 
	 * @param moduleName
	 * @param parentsPath
	 * @param gridFilterData
	 * @param request
	 * @return
	 */
	public List<TreeNodeRecord> getNumberFieldGroupRecord(String moduleName,
		_ModuleGridNavigate parentsPath, GridFilterData gridFilterData,
		HttpServletRequest request) {
	List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();
	_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

	@SuppressWarnings("unchecked")
	List<_NumberGroupDetail> nDetails = (List<_NumberGroupDetail>) systemBaseDAO
		.findByPropertyAllSort(_NumberGroupDetail.class, "tf_order", "ASC",
			"tf_NumberGroup.tf_numberGroupId",
			parentsPath.getTf_NumberGroup().getTf_numberGroupId(), null, null);

	TreeNodeRecord root = new TreeNodeRecord(moduleName, module.getTableAsName(),
		parentsPath.getTf_text(), null, null, null, null);
	root.setLeaf(false);
	result.add(root);
	int sum = 0;
	// String field = String.format("isnull( %s.%s ,0)",
	// module.getTableAsName(),
	// parentsPath.getTf_fields());

	String field = String.format("(case when %s.%s is null then 0 else %s.%s end)",
		module.getTableAsName(), parentsPath.getTf_fields(), module.getTableAsName(),
		parentsPath.getTf_fields());

	// case when _t701010.numberOfPeople is null then 0 else
	// _t701010.numberOfPeople end

	String fieldfilter = "";
	for (_NumberGroupDetail detail : nDetails) {

		List<SqlModuleFilter> sqlModuleFilters = new ArrayList<SqlModuleFilter>();
		// 加入父模块信息
		if (gridFilterData.getParentModuleFilter() != null)
		sqlModuleFilters.add(gridFilterData.getParentModuleFilter());
		if (detail.getTf_condition1() != null) {
		SqlModuleFilter filter = new SqlModuleFilter();
		filter.setModuleName(moduleName);
		filter.setTableAsName(module.getTableAsName());
		filter.setEqualsMethod(SqlModuleFilter.DIRECT_FIELDANDVALUE);
		filter.setEqualsValue(detail.getTf_condition1());
		filter.setPrimarykey(field);
		sqlModuleFilters.add(filter);
		fieldfilter = field + " " + detail.getTf_condition1();
		}
		if (detail.getTf_condition2() != null) {
		SqlModuleFilter filter = new SqlModuleFilter();
		filter.setModuleName(moduleName);
		filter.setTableAsName(module.getTableAsName());
		filter.setEqualsMethod(SqlModuleFilter.DIRECT_FIELDANDVALUE);
		filter.setEqualsValue(detail.getTf_condition2());
		filter.setPrimarykey(field);
		sqlModuleFilters.add(filter);
		if (fieldfilter.length() > 0)
			fieldfilter = fieldfilter + " and ";
		fieldfilter = fieldfilter + field + " " + detail.getTf_condition2();
		}

		TreeNodeRecord record = new TreeNodeRecord(module.getTf_moduleName(),
			module.getTableAsName(), detail.getTf_name(), parentsPath.getTf_fields(), fieldfilter,
			SqlModuleFilter.DIRECT_METHOD, null);
		record.setFieldtitle(parentsPath.getTf_text());
		record.setCount(moduleDAO.getModuleReccWithFilter(moduleName, sqlModuleFilters, request));
		sum += record.getCount();
		root.getChildren().add(record);
	}
	root.setCount(sum);
	return result;
	}

	/**
	 * 取得某表一个字段的tree , 这些字段不是manytoone 字段，例如性别字段，可取得男，女，这样的汉字字段，也可以是boolean字段等
	 * 
	 * @param moduleName
	 * @param parentsPath
	 * @param treeModeEnum
	 * @param isContainNullRecord
	 * @param gridFilterData
	 * @return
	 */
	public List<TreeNodeRecord> getBaseFieldTreeRecords(String moduleName,
		_ModuleGridNavigate parentsPath, Boolean cascading, Boolean isContainNullRecord,
		GridFilterData gridFilterData, HttpServletRequest request) {
	List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();

	_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
	// Map<String, String> records = moduleDAO.getModuleWithTreeData(mName,
	// null,
	// getThreadLocalRequest());
	// 判断是否是日期字段，如果是的话，将结果集放到年月中去
	Boolean isDate = false;
	String fields = module.getTableAsName() + "." + parentsPath.getTf_fields();
	_ModuleField moduleField = module.getModuleFieldByFieldName(parentsPath.getTf_fields());
	if (moduleField != null) {
		if (moduleField.isDateField()) {
		isDate = true;
		//fields = String.format("CONVERT(char(10), %s , 23)", fields);   //sqlserver
		
		fields = String.format("DATE_FORMAT( %s , '%%Y-%%m-%%d')", fields);

		} else if ((moduleField.isNumberField() || moduleField.getTf_fieldType().equals("Percent"))
			&& parentsPath.getTf_NumberGroup() != null) {
		// 数值字段分类汇总
		return getNumberFieldGroupRecord(moduleName, parentsPath, gridFilterData, request);
		}

	}

	Map<String, Integer> groupCount = moduleDAO.getParentModuleGroupByRecord(moduleName, fields,
		null, gridFilterData, parentsPath.getTf_reverseOrder(), request);

	String rootid = module.getTableAsName() + "_" + parentsPath.getTf_fields();
	TreeNodeRecord root = new TreeNodeRecord(moduleName, module.getTableAsName(),
		parentsPath.getTf_text(), null, null, null, null);
	root.setLeaf(false);
	result.add(root);

	int sum = 0;
	TreeNodeRecord nullrecord = getNullTreeNode(groupCount, module.getTf_moduleName(),
		module.getTableAsName(), rootid, parentsPath.getTf_fields(), parentsPath.getTf_text());
	if (nullrecord != null) {
		root.getChildren().add(nullrecord);
		sum = nullrecord.getCount();
	}
	Iterator<String> ids = groupCount.keySet().iterator();
	while (ids.hasNext()) {
		String id = (String) ids.next();
		if (id == null)
		continue;
		Integer c = groupCount.get(id);
		TreeNodeRecord record = new TreeNodeRecord(module.getTf_moduleName(),
			module.getTableAsName(), id, parentsPath.getTf_fields(), id, null,
			module.isCodeLevel());

		// 如果是审核情况分组的，将0，1改为未审核和已审核
		if (parentsPath.getTf_fields().equals("tf_auditinged")) {
		if (record.getText().equals("1"))
			record.setText("已审核");
		else
			record.setText("未审核");
		}
		record.setFieldtitle(parentsPath.getTf_text());
		sum += c;
		record.setCount(c);
		root.getChildren().add(record);
	}
	root.setCount(sum);
	// Integer sum = 0;
	// List<TreeNodeRecord> allRecords = addRecordToTree(parentModule, records,
	// groupCount,
	// parentModule.getTableAsName());
	// for (TreeNodeRecord record : allRecords)
	// sum += record.getCount();
	// root.setCount(sum);
	// result.addAll(allRecords);
	if (isDate)
		dateResultAddYearMonth(root, parentsPath.getTf_NumberGroup() == null ? null
			: parentsPath.getTf_NumberGroup().getTf_name());
	checkLeaf(result);

	return result;
	}

	/**
	 * 对于日期型的字段，把结果放到年月之中去
	 * 
	 * @param root
	 */
	private void dateResultAddYearMonth(TreeNodeRecord root, String mode) {
	if (mode == null || mode.equals(""))
		mode = "年月";
	List<TreeNodeRecord> years = new ArrayList<TreeNodeRecord>();
	List<TreeNodeRecord> months = new ArrayList<TreeNodeRecord>();
	List<TreeNodeRecord> removefromroot = new ArrayList<TreeNodeRecord>();
	Calendar cal = Calendar.getInstance();
	for (TreeNodeRecord record : root.getChildren()) {
		Date date = TypeChange.StringToDate(record.getFieldvalue());
		if (date != null) {
		cal.setTime(date);
		TreeNodeRecord year = addYearToList(years, cal.get(Calendar.YEAR), record);
		if (mode.equals("年月"))
			addYearMonthToList(year.getChildren(), cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, record);
		else if (mode.equals("年季"))
			addYearSeasonToList(year.getChildren(), cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, record);
		else if (mode.equals("年月日"))
			addYearMonthDayToList(year.getChildren(), cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), record);
		removefromroot.add(record);
		}
	}
	root.getChildren().removeAll(removefromroot);
	root.getChildren().addAll(years);
	root.getChildren().addAll(months);

	}

	/**
	 * 把一个值加入到list中，如果没有的话
	 * 
	 * @param list
	 * @param str
	 */
	private TreeNodeRecord addYearToList(List<TreeNodeRecord> list, Integer year,
		TreeNodeRecord day) {
	for (TreeNodeRecord y : list) {
		if (y.getFieldvalue().equals(year.toString())) {
		y.setCount(y.getCount() + day.getCount());
		return y;
		}
	}
	TreeNodeRecord yearRecord = new TreeNodeRecord(day.getModuleName(), day.getTableAsName(),
		year + "年", day.getFieldname(), year.toString(), SqlModuleFilter.EQUALSMETHOD_YEAR, false);
	yearRecord.setFieldtitle(day.getFieldtitle());
	yearRecord.setLeaf(false);
	yearRecord.setCount(day.getCount());
	list.add(yearRecord);
	return yearRecord;
	}

	private void addYearSeasonToList(List<TreeNodeRecord> list, Integer year, Integer month,
		TreeNodeRecord day) {
	String ys = year.toString() + "-" + ((month - 1) / 3 + 1);
	for (TreeNodeRecord y : list) {
		if (y.getFieldvalue().equals(ys)) {
		y.setCount(y.getCount() + day.getCount());
		return;
		}
	}
	TreeNodeRecord yearmonthRecord = new TreeNodeRecord(day.getModuleName(), day.getTableAsName(),
		year.toString() + "年 第" + ((month - 1) / 3 + 1) + "季", day.getFieldname(), ys,
		SqlModuleFilter.EQUALSMETHOD_YEARSEASON, false);
	yearmonthRecord.setFieldtitle(day.getFieldtitle());
	yearmonthRecord.setCount(day.getCount());
	// yearmonthRecord.getChildren().add(day);
	list.add(yearmonthRecord);

	}

	private void addYearMonthToList(List<TreeNodeRecord> list, Integer year, Integer month,
		TreeNodeRecord dayrecord) {
	String ym = year.toString() + "-" + (month >= 10 ? "" : "0") + month.toString();
	for (TreeNodeRecord y : list) {
		if (y.getFieldvalue().equals(ym)) {
		y.setCount(y.getCount() + dayrecord.getCount());
		return;
		}
	}
	TreeNodeRecord yearmonthRecord = new TreeNodeRecord(dayrecord.getModuleName(),
		dayrecord.getTableAsName(),
		year.toString() + "年" + (month >= 10 ? "" : "0") + month.toString() + "月",
		dayrecord.getFieldname(), ym, SqlModuleFilter.EQUALSMETHOD_YEARMONTH, false);
	yearmonthRecord.setFieldtitle(dayrecord.getFieldtitle());
	yearmonthRecord.setCount(dayrecord.getCount());
	// yearmonthRecord.getChildren().add(day);
	list.add(yearmonthRecord);

	}

	// 年 月 日
	private void addYearMonthDayToList(List<TreeNodeRecord> list, Integer year, Integer month,
		Integer day, TreeNodeRecord dayrecord) {
	String ym = year.toString() + "-" + (month >= 10 ? "" : "0") + month.toString();
	for (TreeNodeRecord y : list) {
		if (y.getFieldvalue().equals(ym)) {
		y.setCount(y.getCount() + dayrecord.getCount());

		// 这是因为有时候会有 2012-01-01 1:00:00 和 2012-01-01 5:28:09 会产生二个日期
		// 这个问题解决掉了，但是这里放着也行
		TreeNodeRecord newDay = dayRecord(dayrecord, year, month, day);
		// boolean found = false;
		// for (TreeNodeRecord day1 : y.getChildren())
		// if (day1.getFieldvalue().equals(newDay.getFieldvalue())) {
		// found = true;
		// day1.setCount(day1.getCount() + newDay.getCount());
		// break;
		// }
		// if (!found)
		y.getChildren().add(newDay);

		return;
		}
	}
	TreeNodeRecord yearmonthRecord = new TreeNodeRecord(dayrecord.getModuleName(),
		dayrecord.getTableAsName(),
		year.toString() + "年" + (month >= 10 ? "" : "0") + month.toString() + "月",
		dayrecord.getFieldname(), ym, SqlModuleFilter.EQUALSMETHOD_YEARMONTH, false);
	yearmonthRecord.setFieldtitle(dayrecord.getFieldtitle());
	yearmonthRecord.setCount(dayrecord.getCount());
	yearmonthRecord.getChildren().add(dayRecord(dayrecord, year, month, day));
	list.add(yearmonthRecord);
	}

	private TreeNodeRecord dayRecord(TreeNodeRecord dayrecord, Integer year, Integer month,
		Integer day) {
	String str = year.toString() + "-" + (month >= 10 ? "" : "0") + month.toString() + "-"
		+ (day >= 10 ? "" : "0") + day.toString();
	TreeNodeRecord result = new TreeNodeRecord(dayrecord.getModuleName(),
		dayrecord.getTableAsName(), str, dayrecord.getFieldname(), str,
		SqlModuleFilter.EQUALSMETHOD_YMD, false);
	result.setFieldtitle(dayrecord.getFieldtitle());
	result.setCount(dayrecord.getCount());
	return result;
	}
}

class IdAndName {
	public String id;
	public String name;

	public IdAndName(String id, String name) {
	this.id = id;
	this.name = name;
	}
}