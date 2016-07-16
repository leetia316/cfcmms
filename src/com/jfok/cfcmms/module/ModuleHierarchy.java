package com.jfok.cfcmms.module;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 模块层次体系结构
 * 
 * @author jiangfeng
 *
 */
@SuppressWarnings("serial")
public class ModuleHierarchy implements Serializable {
  
  private String moduleName;    // 模块名称
  private String asName;        // as Name
  
  private Integer level;        // 级数
  
  private String namePath;      // 名称的路径
  private String path;          // as 里面的路径
  
  private List<ModuleHierarchy> parents;
  

  public ModuleHierarchy(){
    
  }


  public String getModuleName() {
    return moduleName;
  }


  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }


  public String getAsName() {
    return asName;
  }


  public void setAsName(String asName) {
    this.asName = asName;
  }


  public Integer getLevel() {
    return level;
  }


  public void setLevel(Integer level) {
    this.level = level;
  }


  public String getNamePath() {
    return namePath;
  }


  public void setNamePath(String namePath) {
    this.namePath = namePath;
  }


  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public List<ModuleHierarchy> getParents() {
    return parents;
  }


  public void setParents(List<ModuleHierarchy> parents) {
    this.parents = parents;
  }
  
}
