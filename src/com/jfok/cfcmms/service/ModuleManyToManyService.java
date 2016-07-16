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
import com.jfok.cfcmms.core.module.SqlGenerator;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.share.TreeNodeRecord;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.share.ValueText;
import com.jfok.cfcmms.util.ActionResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ModuleManyToManyService {

  @Resource
  private SystemBaseDAO systemBaseDAO;

  @Resource
  private ModuleDAO moduleDAO;

  @Resource
  private ModuleService moduleService;

  /**
   * 
   * @param request
   * @param moduleName
   *          当前模块名称
   * @param id
   *          当前记录id
   * @param manyToManyModuleName
   *          manyToMany的模块名称
   * @param linkModuleName
   *          中间模块名称
   * @return 返回所有manyToManyModuleName的记录数据，并把当前记录已有的manyToMany值的checked置为true
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public List<TreeNodeRecordChecked> getManyToManyDetail(HttpServletRequest request,
      String moduleName, String id, String manyToManyModuleName, String linkModuleName) {
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    _Module manyToManyModule = SystemAndLoginInfoService.getModuleWithName(manyToManyModuleName);

    List<TreeNodeRecord> result = new ArrayList<TreeNodeRecord>();

    // 首先读取manyToManyModuleName中的所有权限可视范围之内的数据
    List<ValueText> allTreeItems = moduleDAO.getModuleWithComboDataWithQuery(manyToManyModuleName,
        null, request);
    for (ValueText vt : allTreeItems) {
      TreeNodeRecordChecked record = new TreeNodeRecordChecked();
      record.setFieldvalue(vt.getValue());
      record.setText(vt.getText());
      record.setLeaf(true);
      result.add(record);
    }
    // 在linkModuleName中读取当前id的manyToMany的值,在数据可视涠之内
    List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();

    SqlModuleFilter moduleIdFilter = new SqlModuleFilter();
    moduleIdFilter.setModuleName(module.getTf_moduleName());
    moduleIdFilter.setTableAsName(module.getTableAsName());
    moduleIdFilter.setPrimarykey(module.getTf_primaryKey());
    moduleIdFilter.setEqualsValue(id);
    filters.add(moduleIdFilter);

    SqlGenerator generator = new SqlGenerator(linkModuleName, request);
    generator.setModuleFilters(filters);
    JSONArray dataArray = moduleDAO.getData(generator, -1, 0);

    // 生成TreeNodeRecordChecked,并加入checked标志
    for (int i = 0; i < dataArray.size(); i++) {
      String manytomanyid = dataArray.getJSONObject(i).getString(
          manyToManyModule.getTableAsName() + "___" + manyToManyModule.getTf_primaryKey());
      for (TreeNodeRecord record : result) {
        if (record.getFieldvalue().equals(manytomanyid))
          ((TreeNodeRecordChecked) record).setChecked(true);
      }

    }

    // 返回结果

    List<TreeNodeRecordChecked> root = new ArrayList<TreeNodeRecordChecked>();
    TreeNodeRecordChecked rootrecord = new TreeNodeRecordChecked();
    rootrecord.setText(manyToManyModule.getTf_title());
    rootrecord.setChildren(result);
    rootrecord.setExpanded(true);
    root.add(rootrecord);
    return root;
  }

  public ActionResult setManyToManyDetail(HttpServletRequest request, String moduleName, String id,
      String manyToManyModuleName, String linkModuleName, String[] selected) {

    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    _Module manyToManyModule = SystemAndLoginInfoService.getModuleWithName(manyToManyModuleName);
    _Module linkedModule = SystemAndLoginInfoService.getModuleWithName(linkModuleName);
    // 在linkModuleName中读取当前id的manyToMany的值,在数据可视涠之内
    List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();

    SqlModuleFilter moduleIdFilter = new SqlModuleFilter();
    moduleIdFilter.setModuleName(module.getTf_moduleName());
    moduleIdFilter.setTableAsName(module.getTableAsName());
    moduleIdFilter.setPrimarykey(module.getTf_primaryKey());
    moduleIdFilter.setEqualsValue(id);
    filters.add(moduleIdFilter);
    SqlGenerator generator = new SqlGenerator(linkModuleName, request);
    generator.setModuleFilters(filters);
    JSONArray dataArray = moduleDAO.getData(generator, -1, 0);

    // 如果原来有，现在selected里面没有了，那么就要删除了
    for (int i = 0; i < dataArray.size(); i++) {
      String manytomanyid = dataArray.getJSONObject(i).getString(
          manyToManyModule.getTableAsName() + "___" + manyToManyModule.getTf_primaryKey());
      boolean isfound = false;
      for (String selectedid : selected) {
        if (manytomanyid.equals(selectedid)) {
          isfound = true;
          break;
        }
      }
      if (!isfound) {
        // 需要删除这个manyTomany，调用系统Service的remove，会判断能否删除的逻辑，会记入日志
        // 尚未做出错处理
        moduleService.remove(linkModuleName,
            dataArray.getJSONObject(i).getString(linkedModule.getTf_primaryKey()), request);
      }
    }
    // 如果原来没有，现在selected里面有了，那么就要增加进去
    for (String selectedid : selected) {
      if (selectedid.length() > 0) {
        boolean isfound = false;
        for (int i = 0; i < dataArray.size(); i++) {
          String manytomanyid = dataArray.getJSONObject(i).getString(
              manyToManyModule.getTableAsName() + "___" + manyToManyModule.getTf_primaryKey());
          if (manytomanyid.equals(selectedid)) {
            isfound = true;
            break;
          }
        }
        if (!isfound) {
          JSONObject object = new JSONObject();
          object.put(
              manyToManyModule.getTableAsName() + "___" + manyToManyModule.getTf_primaryKey(),
              selectedid);
          object.put(module.getTableAsName() + "___" + module.getTf_primaryKey(), id);
          // 需要新增这个manyTomany,调用系统Service的add ,会判断是否能新增等逻辑，会记入日志
          // 尚未做出错处理
          moduleService.add(linkModuleName, object.toString(), request);
        }
      }
    }
    ActionResult result = new ActionResult();
    return result;
  }
}
