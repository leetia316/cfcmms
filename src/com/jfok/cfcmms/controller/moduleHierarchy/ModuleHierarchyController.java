package com.jfok.cfcmms.controller.moduleHierarchy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.ModuleHierarchyService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/modulehierarchy")

public class ModuleHierarchyController {

  @Resource
  private ModuleHierarchyService moduleHierarchyService;

  @RequestMapping("/allmodule.do")
  public @ResponseBody List<JSONObject> getAllModule() {

    return moduleHierarchyService.allModulesInfo();

  }

  @RequestMapping("/allmodulehierarchy.do")
  public @ResponseBody List<JSONObject> getAllModuleHierarchy() {

    return moduleHierarchyService.allModulesHierarchyInfo();

  }
}
