package com.jfok.cfcmms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.hibernate.system.module._Module;

import net.sf.json.JSONObject;

/**
 * 
 * 模块之间层次关系的Service,用于提供gojs的显示模块的总结构图，和每一个模块的图。
 * 
 * @author jiangfeng
 *
 */

@Service
public class ModuleHierarchyService {

  /**
   * 返回所有的业务模块
   * @return
   */
  public List<JSONObject> allModulesInfo() {
    List<JSONObject> result = new ArrayList<JSONObject>();
    for (_Module module : SystemAndLoginInfoService.getModules()) {
      if (!module.getTf_moduleName().startsWith("_")) {
        JSONObject m = new JSONObject();
        m.put("moduleName", module.getTf_moduleName());
        m.put("key", module.getTf_moduleName());
        m.put("title", module.getTf_title());
        m.put("moduleId", module.getTf_moduleId());
        result.add(m);
      }
    }
    return result;
  }

  /**
   * 返回所有的业务模块之间的关联关系
   * @return
   */
  public List<JSONObject> allModulesHierarchyInfo() {
    List<JSONObject> result = new ArrayList<JSONObject>();
    for (_Module module : SystemAndLoginInfoService.getModules()) {
      if (!module.getTf_moduleName().startsWith("_"))
        for (_Module pm : module.getParents()) {
          JSONObject m = new JSONObject();
          m.put("from", pm.getTf_moduleName());
          m.put("to", module.getTf_moduleName());
          result.add(m);
        }
    }
    return result;
  }
}
