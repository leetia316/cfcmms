package com.jfok.cfcmms.logic;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.report._ReportGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._Menu;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;

@Service
public class _MenuLogic extends BaseOperateLogic<_Menu> {

  @Resource
  private SystemBaseDAO systemBaseDAO;

  @Override
  public boolean beforeInsert(_Menu inserted, Map<String, String> errorMessage,
      HttpServletRequest request) {
    /**
     * 如果没有录入title,录入了模块，综合查询分组，或综合查询，那么就加入名称到title
     */
    if (inserted.getTf_title() == null) {
      if (inserted.getTf_Module() != null) {
        _Module module = (_Module) systemBaseDAO.findById(_Module.class,
            inserted.getTf_Module().getTf_moduleId());
        inserted.setTf_title(module.getTf_title());
      } else if (inserted.getTf_ReportGroup() != null) {
        _ReportGroup group = (_ReportGroup) systemBaseDAO.findById(_ReportGroup.class,
            inserted.getTf_ReportGroup().getTf_reportGroupId());
        inserted.setTf_title(group.getTf_title());
      }
    }
    return super.beforeInsert(inserted, errorMessage, request);
  }

  @Override
  public boolean beforeUpdate(ModuleFormOperateType type, _Menu updatedObject, _Menu oldObject,
      Map<String, String> errorMessage, HttpServletRequest request) {

    /**
     * 如果没有录入title,录入了模块，综合查询分组，或综合查询，那么就加入名称到title
     */
    if (updatedObject.getTf_title() == null) {
      if (updatedObject.getTf_Module() != null) {
        _Module module = (_Module) systemBaseDAO.findById(_Module.class,
            updatedObject.getTf_Module().getTf_moduleId());
        updatedObject.setTf_title(module.getTf_title());
      } else if (updatedObject.getTf_ReportGroup() != null) {
        _ReportGroup group = (_ReportGroup) systemBaseDAO.findById(_ReportGroup.class,
            updatedObject.getTf_ReportGroup().getTf_reportGroupId());
        updatedObject.setTf_title(group.getTf_title());
      }
    }
    return super.beforeUpdate(type, updatedObject, oldObject, errorMessage, request);
  }

}
