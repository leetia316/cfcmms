package com.jfok.cfcmms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.service.ModuleManyToManyService;
import com.jfok.cfcmms.share.TreeNodeRecordChecked;
import com.jfok.cfcmms.util.ActionResult;

/**
 * 用来管理模块的ManyToMany数据的读取和修改的保存
 * 
 * @author jiangfeng
 *
 *         2016-01-11
 *
 */
@Controller
@RequestMapping("/modulemanytomany")
public class ModuleManyToManyController {

  @Resource
  private ModuleManyToManyService moduleManyToManyService;

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
  @RequestMapping("/getmanytomanydetail.do")
  public @ResponseBody List<TreeNodeRecordChecked> genManyToManyDetail(HttpServletRequest request,
      String moduleName, String id, String manyToManyModuleName, String linkModuleName) {
    return moduleManyToManyService.getManyToManyDetail(request, moduleName, id,
        manyToManyModuleName, linkModuleName);

  }

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
   * @param selected
   *          所有选中的值，以逗号分隔
   * @return 返回所有manyToManyModuleName的记录数据，并把当前记录已有的manyToMany值的checked置为true
   */

  @RequestMapping("/setmanytomanydetail.do")
  public @ResponseBody ActionResult setManyToManyDetail(HttpServletRequest request,
      String moduleName, String id, String manyToManyModuleName, String linkModuleName,
      String selected) {
    return moduleManyToManyService.setManyToManyDetail(request, moduleName, id,
        manyToManyModuleName, linkModuleName, selected.split(","));

  }

}
