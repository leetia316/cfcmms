package com.jfok.cfcmms.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import ognl.Ognl;
import ognl.OgnlException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LobHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.DAO.TreeModuleDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.AjaxRequestException;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.share.SortParameter;
import com.jfok.cfcmms.share.grid.GridFilterData;
import com.jfok.cfcmms.share.module.DataDeleteResponseInfo;
import com.jfok.cfcmms.share.module.DataFetchRequestInfo;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;
import com.jfok.cfcmms.share.module.DataInsertResponseInfo;
import com.jfok.cfcmms.share.module.DataUpdateResponseInfo;
import com.jfok.cfcmms.share.module.ModuleFormOperateType;
import com.jfok.cfcmms.util.TypeChange;
import com.jfok.cfcmms.util.UploadFileBean;
import com.jfok.cfcmms.hibernate.superclass._ApproveAbstract;
import com.jfok.cfcmms.hibernate.superclass._InputInfoAbstract;
import com.jfok.cfcmms.hibernate.system.authority._UserRoleDetail;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.logic.CodeLevelModuleLogic;
import com.jfok.cfcmms.logic.IModuleOperateLogic;
import com.jfok.cfcmms.logic._AttachmentLogic;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;

@Service
public class ModuleService {

  @Resource
  private SystemBaseDAO systemBaseDAO;

  @Resource
  private ModuleDAO moduleDAO;

  @Resource
  private TreeModuleDAO treeModuleDAO;

  @Resource
  private _AttachmentLogic _AttachmentLogic;

  @Resource
  private CodeLevelModuleLogic codeLevelModuleLogic;

  @Resource
  private _ModuleFieldConstraintService moduleFieldConstraintService;

  private static final Log log = LogFactory.getLog(ModuleService.class);
  public static final int STATUS_FAILURE = -1;
  public static final int STATUS_LOGIN_INCORRECT = -5;
  public static final int STATUS_LOGIN_REQUIRED = -7;
  public static final int STATUS_LOGIN_SUCCESS = -8;
  public static final int STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED = -6;
  public static final int STATUS_SERVER_TIMEOUT = -100;
  public static final int STATUS_SUCCESS = 0;
  public static final int STATUS_TRANSPORT_ERROR = -90;
  public static final int STATUS_VALIDATION_ERROR = -4;

  public static final String UPDATEJSONOBJECT = "updateJsonObject";
  public static final String INSERTJSONOBJECT = "insertJsonObject";

  // 返回json数据，要在这里加 application/json
  // produces = "application/json;text/plain;charset=UTF-8"
  // @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Map<String, Object> fetchData(String moduleName, Integer start, Integer limit, String sort,
      String query, String columns, String navigates, String parentFilter,
      HttpServletRequest request) {
    DataFetchResponseInfo response = fetchDataInner(moduleName, start, limit, sort, query, columns,
        navigates, parentFilter, (SqlModuleFilter) null, request);
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("records", response.getMatchingObjects());
    result.put("totalCount", response.getTotalRows());
    return result;
  }

  /**
   * 内部的取得数据的函数，start=-1 ,取得所有的数据
   * 
   * @param moduleName
   * @param start
   * @param limit
   * @param sort
   * @param query
   * @param columns
   * @param navigates
   * @param parentFilter
   * @param additionFilter
   *          //附加的过滤条件
   * @param request
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public DataFetchResponseInfo fetchDataInner(String moduleName, Integer start, Integer limit,
      String sort, String query, String columns, String navigates, String parentFilter,
      SqlModuleFilter additionFilter, HttpServletRequest request) {

    SortParameter sorts[] = SortParameter.changeToSortParameters(sort);
    List<SqlModuleFilter> navigateFilters = changeToNavigateFilters(navigates);
    SqlModuleFilter pFilter = null;
    if (parentFilter != null && parentFilter.length() > 1) {
      JSONObject jo = JSONObject.fromObject(parentFilter);
      pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
    }
    return fetchDataInner(moduleName, start, limit, sorts, query, columns, navigateFilters, pFilter,
        additionFilter, request);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public DataFetchResponseInfo fetchDataInner(String moduleName, Integer start, Integer limit,
      String sort, String query, String columns, String navigates, String parentFilter,
      List<SqlModuleFilter> additionFilters, HttpServletRequest request) {

    SortParameter sorts[] = SortParameter.changeToSortParameters(sort);
    List<SqlModuleFilter> navigateFilters = changeToNavigateFilters(navigates);
    navigateFilters.addAll(additionFilters);
    SqlModuleFilter pFilter = null;
    if (parentFilter != null && parentFilter.length() > 1) {
      JSONObject jo = JSONObject.fromObject(parentFilter);
      pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
    }
    return fetchDataInner(moduleName, start, limit, sorts, query, columns, navigateFilters, pFilter,
        null, request);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public DataFetchResponseInfo fetchDataInner(String moduleName, Integer start, Integer limit,
      SortParameter sorts[], String query, String columns, List<SqlModuleFilter> navigateFilters,
      SqlModuleFilter pFilter, SqlModuleFilter additionFilter, HttpServletRequest request) {

    // // 对于有审批的导航，需要加入另外的值
    // SqlModuleFilter approveFilter = null;
    // if (navigateFilters != null)
    // for (SqlModuleFilter filter : navigateFilters) {
    // if (filter.getPrimarykey().equals("approvetype")) {
    // approveFilter = filter;
    // break;
    // }
    // }
    // if (approveFilter != null) {
    // navigateFilters.remove(approveFilter);
    // UserSession userSession =
    // SessionManage.getInstance().getUserSession(request.getSession());
    // ApproveListTypeEnum typeEnum =
    // ApproveListTypeEnum.valueOf(approveFilter.getEqualsValue());
    // navigateFilters
    // .addAll(userSession.getModuleApproveSqlFilters().get(moduleName).get(typeEnum));
    // }

    // System.out.println(moduleName + "," + start + "," + limit + "," + sort +
    // "," + query + ","
    // + columns + "," + navigates);
    DataFetchRequestInfo dsRequest = new DataFetchRequestInfo();
    dsRequest.setModuleName(moduleName);
    dsRequest.setStartRow(start);
    dsRequest.setEndRow(start + limit - 1);
    dsRequest.setSorts(sorts);
    dsRequest.setNavigateFilters(navigateFilters);

    if (additionFilter != null) {
      if (dsRequest.getNavigateFilters() == null)
        dsRequest.setNavigateFilters(new ArrayList<SqlModuleFilter>());
      dsRequest.getNavigateFilters().add(additionFilter);
    }
    GridFilterData gridFilterData = new GridFilterData();
    // 父模块约束的加入
    if (pFilter != null) {
      // 如果你模块约束是 可审批的
      // if (pFilter.getPrimarykey().equals("approvetype")) {
      // UserSession userSession =
      // SessionManage.getInstance().getUserSession(request.getSession());
      // navigateFilters.addAll(userSession.getModuleApproveSqlFilters().get(moduleName)
      // .get(ApproveListTypeEnum.我可以审批的));
      // } else
      gridFilterData.setParentModuleFilter(pFilter);
    }
    gridFilterData.setSearchText(query);
    if (columns != null)
      gridFilterData.setGridColumnNames(columns.split(","));
    DataFetchResponseInfo response = moduleDAO.getModuleData(moduleName, dsRequest, gridFilterData,
        request);
    return response;
  }

  /**
   * 取得一个树状模块的所有树记录
   * 
   * @param moduleName
   * @param sorts
   * @param query
   * @param columns
   * @param request
   * @return
   */

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public JSONObject fetchTreeDataInner(String moduleName, SortParameter sorts[], String query,
      String columns, HttpServletRequest request) {

    DataFetchRequestInfo dsRequest = new DataFetchRequestInfo();
    dsRequest.setModuleName(moduleName);
    dsRequest.setSorts(sorts);
    GridFilterData gridFilterData = new GridFilterData();
    gridFilterData.setSearchText(query);
    if (columns != null)
      gridFilterData.setGridColumnNames(columns.split(","));
    // 树状结构的有一个id , 有一个 pid ,先加入根节点，再找到 pid 为null 的作为第一级子节点，然后一级级的往下加 children
    // []
    JSONObject response = null;
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    if (module.getTf_parentKey() != null && module.getTf_parentKey().length() > 0)
      // 设置了 parentkey
      response = treeModuleDAO.getTreeModuleDataWithPid(module, dsRequest, gridFilterData, request);
    else
      response = treeModuleDAO.getTreeModuleDataWithLevel(module, dsRequest, gridFilterData,
          request);
    return response;
  }

  @SuppressWarnings("unchecked")
  // @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Object getRecordNewDefault(String moduleName, String parentFilter, String navigates,
      HttpServletRequest request) {

    Map<String, Object> result = new HashMap<String, Object>();
    List<SqlModuleFilter> navigateFilters = changeToNavigateFilters(navigates);

    // 父模块约束的加入
    GridFilterData gridFilterData = new GridFilterData();

    // 导航值的加入，某些模块的初始值，需要使用到导航值
    request.setAttribute("navigate", navigateFilters);

    // gridFilterData.setNavigateTreeSelected(navigateTreeSelected)
    if (parentFilter != null && parentFilter.length() > 1) {
      JSONObject jo = JSONObject.fromObject(parentFilter);
      SqlModuleFilter pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
      gridFilterData.setParentModuleFilter(pFilter);
    }

    UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
    // 如果此模块有inputmen ,那么加入inputmen inputdate 的缺省值
    if (SystemAndLoginInfoService.getModuleWithName(moduleName)
        .getModuleFieldByFieldName(_InputInfoAbstract.INPUTMEN) != null) {
      result.put(_InputInfoAbstract.INPUTMEN, userSession.getUserName());
      result.put(_InputInfoAbstract.INPUTDATE, TypeChange.DateToString(new Date()));
    }
    IModuleOperateLogic<Object> moduleOperateLogic = (IModuleOperateLogic<Object>) SystemInfoService
        .getBean(moduleName + "Logic");
    if (moduleOperateLogic != null) {
      Map<String, Object> newDefaultValue = moduleOperateLogic.getNewDefultValue(request,
          gridFilterData);
      if (newDefaultValue != null)
        result.putAll(newDefaultValue);
    }
    return result;
  }

  // @Override
  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Object getRecordById(String moduleName, String id, HttpServletRequest request) {
    log.debug("根据主键取得模块的一条记录:" + moduleName + "," + id);
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("totalCount", 1);
    List<Object> records = new ArrayList<Object>();
    try {
      records.add(moduleDAO.getModuleRecord(moduleName, id, request).toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    result.put("records", records);
    log.debug("getRecordById返回值：" + result.toString());
    return result;
  }

  @SuppressWarnings("unchecked")
  // @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public DataInsertResponseInfo add(String moduleName, String inserted,
      HttpServletRequest request) {

    log.debug("数据insert:" + moduleName + ":" + inserted);

    JSONObject updateJsonObject = JSONObject.fromObject(inserted);
    request.setAttribute(INSERTJSONOBJECT, updateJsonObject);

    DataInsertResponseInfo result = new DataInsertResponseInfo();
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    //  检查id 是否是codelevel,如果是，则要进行长度和父节点的检查
    if (module.getTf_codeLevel() != null) {
      String mess = codeLevelModuleLogic.addCodeLevelModuleKey(module,
          updateJsonObject.getString(module.getTf_primaryKey()));
      if (mess != null) {
        result.getErrorMessage().put(module.getTf_primaryKey(), mess);
        result.setResultCode(STATUS_VALIDATION_ERROR);
        return result;
      }
    }

    Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
    try {
      Object record = Class.forName(beanClass.getName()).newInstance();
      moduleDAO.updateValueToBean(moduleName, record, updateJsonObject);
      try {
        CommonFunction.setPropertyToSuperClass(record, _InputInfoAbstract.INPUTMEN,
            SessionManage.getInstance().getUserSession(request.getSession()).getUserName());
        CommonFunction.setPropertyToSuperClass(record, _InputInfoAbstract.INPUTDATE, new Date());
      } catch (Exception e) {
      }
      // 如果具有审核权限，那么在新增的时候，加入 正在审核，以及没有锁住审核
      // if (record instanceof ApproveAbstract) {
      // ((ApproveAbstract)
      // record).setTf_shResult(ApproveAbstract.APPROVE_EXEC);
      // ((ApproveAbstract) record).setTf_shLocked(false);
      // }

      // 插入数据之前做数据的字段间的约束检查
      if (!moduleFieldConstraintService.moduleFieldConstraintValid(module, record,
          result.getErrorMessage())) {
        result.setResultCode(STATUS_VALIDATION_ERROR);
        return result;
      }

      // 插入数据之前去检查逻辑性
      IModuleOperateLogic<Object> moduleOperateLogic = (IModuleOperateLogic<Object>) SystemInfoService
          .getBean(moduleName + "Logic");
      if (moduleOperateLogic != null
          && !moduleOperateLogic.beforeInsert(record, result.getErrorMessage(), request)) {
        for (String error : result.getErrorMessage().keySet())
          log.debug(
              "数据新增失败：" + moduleName + ":" + error + ":" + result.getErrorMessage().get(error));
        result.setResultCode(STATUS_VALIDATION_ERROR);
        return result;
      }

      systemBaseDAO.save(record);
      // systemBaseDAO.getHibernateTemplate().evict(record)
      // 写入数据了以后，可能会有计算字段等 信息，重新读取
      record = systemBaseDAO.findById(beanClass, Ognl.getValue(module.getTf_primaryKey(), record));
      // System.out.println("idkey : " +
      // Ognl.getValue(module.getTf_primaryKey(), record));
      // 写入日志
      moduleDAO.saveOperateLog(request, module,
          Ognl.getValue(module.getTf_primaryKey(), record).toString(),
          moduleDAO.getRecordNameValue(module, record), "新增",
          updateJsonObject.toString().length() > 10000 ? "有上传的图片文件，未保存json串"
              : updateJsonObject.toString());

      // 插入数据之后去做相关逻辑性的操作,既然已经写入数据库了，就不应该会产生错误
      systemBaseDAO.getSessionFactory().getCurrentSession().flush();
      try {
        if (moduleOperateLogic != null) {
          // 加上这一条，不然在处理after insert 的时候会有问题，没有得到select 的最新数据
          moduleOperateLogic.afterInsert(record, request);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      // 判断是否是combo中的值进行进修改了
      // ComboValueGeneratorService.hasComboValueNeedRefresh(dsRequest.getModuleId());
      // 判断是不是系统模块参数的类进行了修改，如果是将系统参数清空，下次刷新，调用新的
      // systemInfoGeneratorService.isModuleControlModified(record);
      // systemBaseDAO.getHibernateTemplate().flush();

      result.setResultCode(STATUS_SUCCESS);
      // 此处由于事务还没有提交，不可以进行数据的读取，将modulename 和 key 返回到控制层去读取
      // result.getRecords().add(
      // moduleDAO.getModuleRecord(moduleName,
      // Ognl.getValue(module.getTf_primaryKey(), record).toString(),
      // request).toString());
      result.setModuleName(moduleName);
      result.setKey(Ognl.getValue(module.getTf_primaryKey(), record).toString());
    } catch (DataAccessException e) {
      e.printStackTrace();
      // 这个出错是不会执行到的，这是没有加事务的出错 检查，
      ModuleServiceFunction.addExceptionCauseToErrorMessage(e.getRootCause().getMessage(),
          result.getErrorMessage(), module.getTf_primaryKey());
      result.setResultCode(STATUS_VALIDATION_ERROR);
    } catch (Exception e) {
      e.printStackTrace();
      result.getErrorMessage().put("error", e.getMessage());
      result.setResultCode(STATUS_FAILURE);
    }
    log.debug("insert返回值：" + result.toString());

    return result;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public DataUpdateResponseInfo changeRecordId(String moduleName, String id, String oldid) {
    DataUpdateResponseInfo result = new DataUpdateResponseInfo();
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    // 如果主键被修改了，那么先执行sql语句修改主键，此条如果正确执行，返回结果后，grid中会多一条出来,需要刷新grid。
    //  检查id 是否是codelevel,如果是，对其进行变更处理
    if (module.getTf_codeLevel() != null) {
      String mess = codeLevelModuleLogic.replaceCodeLevelModuleKey(module, oldid, id);
      if (mess != null) {
        result.getErrorMessage().put(module.getTf_primaryKey(), mess);
        result.setResultCode(STATUS_VALIDATION_ERROR);
        return result;
      }
    }

    Session session = systemBaseDAO.getSessionFactory().getCurrentSession();
    // try {
    Query query = session
        .createSQLQuery("update " + moduleName + " set " + module.getTf_primaryKey()
            + " = :newvalue where " + module.getTf_primaryKey() + "=:oldvalue");
    query.setParameter("oldvalue", oldid);
    query.setParameter("newvalue", id);
    query.executeUpdate();
    // } catch (Exception e) {
    // e.printStackTrace();
    // result.getErrorMessage().put(module.getTf_primaryKey(), "主键值重复");
    // result.setResultCode(STATUS_VALIDATION_ERROR);
    // return result;
    // } finally {
    // //session.close();
    // }
    // 如果有附件，要把附件都跟过去
    if (module.getTf_hasAttachment())
      _AttachmentLogic.changeAttachmentIdkey(module.getTf_moduleId(), oldid, id);
    return result;
  }

  @SuppressWarnings("unchecked")
  // @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public DataUpdateResponseInfo update(String moduleName, String id, String operType,
      String updated, HttpServletRequest request) {
    log.debug("数据update:" + moduleName + "," + id + "," + updated);
    JSONObject updateJsonObject = JSONObject.fromObject(updated);
    request.setAttribute(UPDATEJSONOBJECT, updateJsonObject);
    DataUpdateResponseInfo result = new DataUpdateResponseInfo();
    Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    _UserRoleDetail roleDetail = SessionManage.getInstance().getUserSession(request.getSession())
        .getUserRoleDetails(module.getTf_moduleId());
    if (operType == null)
      operType = ModuleFormOperateType.EDIT.getValue();
    try {
      // 保存数据之前老的值
      Object oldRecord = systemBaseDAO.findById(beanClass, id);
      // 使oldRecord 处于游离状态
      systemBaseDAO.getHibernateTemplate().evict(oldRecord);
      Object record = systemBaseDAO.findById(beanClass, id);

      // 如果是审批，判断是否此记录可以审批，有时候审批的条件会变掉 ，要在审批之前判断
      if (operType.equals(ModuleFormOperateType.APPROVE.getValue())) {
        _ApproveAbstract aobject = (_ApproveAbstract) oldRecord;
        if (!aobject.meCanApprove(roleDetail.getTf_approveOrder(), roleDetail.getTf_approveLevel(),
            module.getTf_moduleApproves())) {
          result.getErrorMessage().put(module.getTf_nameFields(), "当前记录在服务器中不允许审批，请刷新数据查看最新数据！");
          result.setResultCode(STATUS_VALIDATION_ERROR);
          return result;
        }
      }

      moduleDAO.updateValueToBean(moduleName, record, updateJsonObject);

      // 如果是审批的操作,要看看是不是对审批结果有作用
      if (operType.equals(ModuleFormOperateType.APPROVE.getValue())) {
        ((_ApproveAbstract) record).adjustResultInfo(roleDetail.getTf_approveOrder(),
            module.getTf_moduleApproves().size());
      }
      // 如果是取消审批的操作
      if (operType.equals(ModuleFormOperateType.CANCELAPPROVE.getValue())) {
        _ApproveAbstract aobject = (_ApproveAbstract) record;
        aobject.clearResultInfo();
      }

      // 修改数据之前做数据的字段间的约束检查
      if (!moduleFieldConstraintService.moduleFieldConstraintValid(module, record,
          result.getErrorMessage())) {
        result.setResultCode(STATUS_VALIDATION_ERROR);
        systemBaseDAO.getHibernateTemplate().evict(record);
        return result;
      }

      // 修改数据之前去检查逻辑性
      IModuleOperateLogic<Object> moduleOperateLogic = (IModuleOperateLogic<Object>) SystemInfoService
          .getBean(moduleName + "Logic");
      if (moduleOperateLogic != null
          && !moduleOperateLogic.beforeUpdate(ModuleFormOperateType.OperateTypeGen(operType),
              record, oldRecord, result.getErrorMessage(), request)) {
        for (String error : result.getErrorMessage().keySet())
          log.debug(
              "数据更新失败：" + moduleName + ":" + error + ":" + result.getErrorMessage().get(error));
        result.setResultCode(STATUS_VALIDATION_ERROR);
        systemBaseDAO.getHibernateTemplate().evict(record);
        return result;
      }
      systemBaseDAO.attachDirty(record, null);
      record = systemBaseDAO.findById(beanClass, id);

      // 写入日志
      moduleDAO.saveOperateLog(request, module, id, moduleDAO.getRecordNameValue(module, record),
          operType, updateJsonObject.toString().length() > 10000 ? "有上传的图片文件，未保存json串"
              : updateJsonObject.toString());

      // 保存数据之后去做相关逻辑性的操作,既然已经写入数据库了，就不应该会产生错误
      systemBaseDAO.getSessionFactory().getCurrentSession().flush();
      try {
        if (moduleOperateLogic != null)
          moduleOperateLogic.afterUpdate(ModuleFormOperateType.OperateTypeGen(operType), record,
              oldRecord, request);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 判断是否是combo中的值进行进修改了
      // ComboValueGeneratorService.hasComboValueNeedRefresh(dsRequest.getModuleName());
      // // 判断是不是系统模块参数的类进行了修改，如果是将系统参数清空，下次刷新，调用新的
      // systemInfoGeneratorService.isModuleControlModified(record);

      result.setResultCode(STATUS_SUCCESS);

      // result.getRecords().add(
      // moduleDAO.getModuleRecord(moduleName,
      // Ognl.getValue(module.getTf_primaryKey(), record).toString(),
      // request).toString());
    } catch (DataAccessException e) {
      e.printStackTrace();
      ModuleServiceFunction.addExceptionCauseToErrorMessage(e.getRootCause().getMessage(),
          result.getErrorMessage(), module.getTf_primaryKey());
      result.setResultCode(STATUS_VALIDATION_ERROR);
    } catch (ConstraintViolationException e) { // 这个是数据库改成 mysql
                                               // 之后加入的，不然的话唯一索引冲突不能拦截得到
      result = new DataUpdateResponseInfo();
      // 如果不是索引冲突，那么加入原来的处理。
      if (ModuleServiceFunction.addIX_UniqueMessage(e.getCause().getMessage(),
          result.getErrorMessage(), module) == false)
        ModuleServiceFunction.addExceptionCauseToErrorMessage(e.getCause().getMessage(),
            result.getErrorMessage(), module.getTf_primaryKey());

      result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
    } catch (Exception e) {
      e.printStackTrace();

      // System.out.println(e.getRootCause().getMessage());
      result.getErrorMessage().put("error", e.getMessage());
      result.setResultCode(STATUS_FAILURE);
    }
    log.debug("update返回值：" + result.toString());
    return result;

  }

  // @Override
  @SuppressWarnings("unchecked")
  @Transactional(propagation = Propagation.REQUIRED)
  public DataDeleteResponseInfo remove(String moduleName, String id, HttpServletRequest request) {
    log.debug("数据delete:模块" + moduleName + ",主键" + id);
    DataDeleteResponseInfo result = new DataDeleteResponseInfo();

    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    //  检查id 是否是codelevel,如果是，则要进行长度和父节点的检查
    if (module.getTf_codeLevel() != null) {
      String mess = codeLevelModuleLogic.deleteCodeLevelModuleKey(module, id);
      if (mess != null) {
        result.setResultMessage(-1, mess);
        return result;
      }
    }

    Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
    try {
      // 检查是否有附件
      if (_AttachmentLogic.getAttachmentCount(module.getTf_moduleId(), id) > 0)
        throw new AjaxRequestException(STATUS_VALIDATION_ERROR, "本记录有附件信息，请先删除所有附件！", request);

      Object record = systemBaseDAO.findById(beanClass, id);
      // 删除数据之前去检查逻辑性
      IModuleOperateLogic<Object> moduleOperateLogic = (IModuleOperateLogic<Object>) SystemInfoService
          .getBean(moduleName + "Logic");
      if (moduleOperateLogic != null
          && !moduleOperateLogic.beforeDelete(record, result.getErrorMessageList(), request)) {
        log.debug("数据删除失败：" + moduleName + ":" + result.getMessage());
        throw new AjaxRequestException(STATUS_VALIDATION_ERROR, result.getMessage(), request);
      }

      systemBaseDAO.delete(record);

      // 写入日志
      moduleDAO.saveOperateLog(request, module, id, moduleDAO.getRecordNameValue(module, record),
          "删除", null);

      // 插入数据之后去做相关逻辑性的操作,既然已经写入数据库了，就不应该会产生错误

      systemBaseDAO.getSessionFactory().getCurrentSession().flush();
      try {
        if (moduleOperateLogic != null) {
          moduleOperateLogic.afterDelete(record, request);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      // 判断是否是combo中的值进行进修改了
      // ComboValueGeneratorService.hasComboValueNeedRefresh(dsRequest.getModuleId());
      // 判断是不是系统模块参数的类进行了修改，如果是将系统参数清空，下次刷新，调用新的
      // systemInfoGeneratorService.isModuleControlModified(record);

      result.setResultCode(STATUS_SUCCESS);
    } catch (DataIntegrityViolationException e) {
      // 这个出错是不会执行到的，这是没有加事务的出错 检查，
      result.setResultMessage(-1, "请检查与本记录相关联的其他数据是否全部清空！");
      e.printStackTrace();
    } catch (DataAccessException e) {
      result = new DataDeleteResponseInfo();

      String errormessage = ModuleServiceFunction
          .addPK_ConstraintMessage(e.getRootCause().getMessage(), moduleName);
      result.setResultMessage(-1,
          errormessage != null ? errormessage : "请检查与本记录相关联的其他数据是否全部清空！<br/>");
    } catch (ConstraintViolationException e) {
      result = new DataDeleteResponseInfo();
      String errormessage = ModuleServiceFunction.addPK_ConstraintMessage(e.getCause().getMessage(),
          moduleName);
      result.setResultMessage(-1,
          errormessage != null ? errormessage : "请检查与本记录相关联的其他数据是否全部清空！<br/>");
    }

    catch (Exception e) {
      result.setResultMessage(-1, e.getMessage());
      e.printStackTrace();
    }
    log.debug("delete返回值：" + result.toString());
    return result;
  }

  /**
   * 根据字符串返回grid导航的数据，生成一个数组
   * 
   * @param str
   * @return
   */
  private List<SqlModuleFilter> changeToNavigateFilters(String str) {
    List<SqlModuleFilter> result = new ArrayList<SqlModuleFilter>();
    if (str != null && str.length() > 5) {
      JsonConfig config = new JsonConfig();
      config.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
      config.setRootClass(SqlModuleFilter.class);
      SqlModuleFilter[] navigateFilters = (SqlModuleFilter[]) JSONSerializer
          .toJava(JSONArray.fromObject(str), config);
      // System.out.println(navigateFilters[0]);
      for (SqlModuleFilter f : navigateFilters)
        result.add(f);
    }
    return result;

  }

  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public void downloadRecordExcelReport(HttpServletRequest request, HttpServletResponse response,
      String moduleId, String excelReportId, String id)
          throws NumberFormatException, IOException, SQLException, OgnlException {
    new ExcelReportService().genRecordExcelReportMonth(request, response, moduleId,
        Integer.parseInt(excelReportId), id);
  }

  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public void downloadRecordExcelReportToPDF(HttpServletRequest request,
      HttpServletResponse response, String moduleId, String excelReportId, String id)
          throws NumberFormatException, IOException, SQLException, OgnlException {
    new ExcelReportService().genRecordExcelReportToPDF(request, response, moduleId,
        Integer.parseInt(excelReportId), id);
  }

  // @Override
  public void exportRecordExcel(String moduleName, String id, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    // TODO Auto-generated method stub

  }

  /**
   * 用户在form中选择了一个上传的图片以后，需要把图像的内容再返回给客户端，使其可以生成一个临时的图像，显示在img中
   * 
   * @param uploadExcelBean
   * @param bindingResult
   * @param request
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public ActionResult uploadImageFileAndReturn(UploadFileBean uploadExcelBean,
      BindingResult bindingResult, HttpServletRequest request) {
    ActionResult result = new ActionResult();
    InputStream is;
    try {
      is = uploadExcelBean.getFile().getInputStream();
      byte[] buffer = new byte[(int) uploadExcelBean.getFile().getSize()];
      is.read(buffer, 0, (int) uploadExcelBean.getFile().getSize());
      JSONObject image = new JSONObject();
      image.put("value", buffer);
      result.setMsg(image.get("value"));
    } catch (IOException e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setMsg("上传的文件接收失败，可能是文件太大");
    }

    return result;
  }

  /**
   * 上传模块一条记录的文件
   * 
   * @param uploadExcelBean
   * @param bindingResult
   * @param request
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public ActionResult uploadModuleFile(UploadFileBean uploadExcelBean, BindingResult bindingResult,
      HttpServletRequest request) {
    ActionResult result = new ActionResult();
    _Module module = SystemAndLoginInfoService.getModuleWithId(uploadExcelBean.getModuleId());
    Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(module.getTf_moduleName());
    Object record = systemBaseDAO.findById(beanClass, uploadExcelBean.getId());

    try {
      InputStream is = uploadExcelBean.getFile().getInputStream();
      LobHelper lobHelper = systemBaseDAO.getSessionFactory().getCurrentSession().getLobHelper();
      Blob blob = lobHelper.createBlob(is, is.available());
      Ognl.setValue(module.getTf_fileField(), record, blob);
      try {
        Ognl.setValue("tf_filename", record, uploadExcelBean.getFile().getOriginalFilename());
      } catch (Exception e) {
      }
      try {
        Ognl.setValue("tf_filesize", record, uploadExcelBean.getFile().getSize());
      } catch (Exception e) {
      }
      UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
      try {
        Ognl.setValue("tf_author", record, userSession.getUserName());

      } catch (Exception e) {
      }
      try {
        Ognl.setValue("tf_uploadDate", record, new Date());
      } catch (Exception e) {
      }

      if (uploadExcelBean.getFile().getSize() == 0)
        Ognl.setValue(module.getTf_fileField(), record, null);

      systemBaseDAO.save(record);
    } catch (OgnlException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
      result.setSuccess(false);
      result.setMsg(e.getMessage());
    }

    return result;
  }

}
