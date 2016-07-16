package com.jfok.cfcmms.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import ognl.OgnlException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.core.util.ExcelExport;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleExcelRecordAdd;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.Office2PDF;
import com.jfok.cfcmms.share.TreeValueText;
import com.jfok.cfcmms.util.UploadFileBean;
import com.jfok.cfcmms.share.ValueText;
import com.jfok.cfcmms.share.module.DataDeleteResponseInfo;
import com.jfok.cfcmms.share.module.DataFetchResponseInfo;
import com.jfok.cfcmms.share.module.DataInsertResponseInfo;
import com.jfok.cfcmms.share.module.DataUpdateResponseInfo;
import com.jfok.cfcmms.share.service.IModuleService;
import com.jfok.cfcmms.share.SortParameter;
import com.jfok.cfcmms.util.TypeChange;

import com.jfok.cfcmms.service.ExcelReportService;
import com.jfok.cfcmms.service.ModuleService;
import com.jfok.cfcmms.service.PrintRecordService;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.service.UploadExcelService;

@Controller
@RequestMapping(value = "/module")
public class ModuleController implements IModuleService {

  @Resource
  private SystemBaseDAO systemBaseDAO;

  @Resource
  private ModuleService moduleService;

  @Resource
  private PrintRecordService printRecordService;

  @Resource
  private UploadExcelService uploadExcelService;

  @Resource
  private ModuleDAO moduleDAO;

  private static final Log log = LogFactory.getLog(ModuleController.class);

  @RequestMapping(value = "/getChildModuleDetail.do", method = RequestMethod.GET)
  public @ResponseBody Object getChildModuleDetail(String moduleName, String id,
      String childModuleName, String childFieldName, HttpServletRequest request) {

    return moduleDAO.getChildModuleDetail(moduleName, id, childModuleName, childFieldName, request);

  }

  /**
   * 在新增和修改模块时，取得一个模块的某个字段的distinct 的数据值
   */
  @RequestMapping(value = "/getModuleFieldComboData.do", method = RequestMethod.GET)
  public @ResponseBody List<ValueText> getModuleFieldComboData(String moduleName, String fieldName,
      Boolean allowParentValue, HttpServletRequest request) {

    return moduleDAO.getDistinctFieldWithComboData(moduleName, fieldName, null, request);
  }

  /**
   * 在新增和修改模块时，该记录的manytoone字段需要取得数据
   */
  @RequestMapping(value = "/getModuleComboData.do", method = RequestMethod.GET)
  public @ResponseBody List<ValueText> getModuleComboData(String moduleName, String query,
      HttpServletRequest request) {
    if (query == null || query.length() == 0)
      return moduleDAO.getModuleWithComboData(moduleName, null, request);
    else {
      _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
      SqlModuleFilter queryFilter = new SqlModuleFilter();
      queryFilter.setModuleId(module.getTf_moduleId());
      queryFilter.setModuleName(moduleName);
      queryFilter.setTableAsName(module.getTableAsName());
      queryFilter.setEqualsMethod(SqlModuleFilter.DIRECT_METHOD);
      queryFilter.setEqualsValue(module.getTableAsName() + "." + module.getTf_nameFields()
          + " like " + "'%" + query.replaceAll("'", "") + "%'");
      List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();
      filters.add(queryFilter);
      return moduleDAO.getModuleWithComboDataWithQuery(moduleName, filters, request);
    }

  }

  /**
   * 在新增和修改模块时，该记录的manytoone字段,并且是分级的，需要取得数据
   */
  @RequestMapping(value = "/getModuleTreeData.do", method = RequestMethod.GET)
  public @ResponseBody List<TreeValueText> getModuleTreeData(String moduleName,
      Boolean allowParentValue, HttpServletRequest request) {

    return moduleDAO.getModuleWithTreeData(moduleName,
        allowParentValue == null ? false : allowParentValue, null, request);
  }

  // 返回json数据，要在这里加 application/json
  // produces = "application/json;text/plain;charset=UTF-8"
  @Override
  @RequestMapping(value = "/fetchdata.do", method = RequestMethod.GET)
  public @ResponseBody Map<String, Object> fetchData(String moduleName, Integer start,
      Integer limit, String sort, String query, String columns, String navigates,
      String parentFilter, HttpServletRequest request) {
    DataFetchResponseInfo response = moduleService.fetchDataInner(moduleName, start, limit, sort,
        query, columns, navigates, parentFilter, (SqlModuleFilter) null, request);
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("records", response.getMatchingObjects());
    result.put("totalCount", response.getTotalRows());
    return result;
  }

  // 返回json数据，要在这里加 application/json
  // produces = "application/json;text/plain;charset=UTF-8"

  @RequestMapping(value = "/fetchtreedata.do/{id}", method = RequestMethod.GET)
  public @ResponseBody JSONObject fetchTreeData(String moduleName, String sort, String query,
      String columns, String navigates, String parentFilter, HttpServletRequest request) {
    JSONObject result = moduleService.fetchTreeDataInner(moduleName,
        SortParameter.changeToSortParameters(sort), query, columns, request);

    return result;
  }

  @RequestMapping(value = "/getnewdefault.do", method = RequestMethod.POST)
  @Override
  public @ResponseBody Object getRecordNewDefault(String moduleName, String parentFilter,
      String navigates, HttpServletRequest request) {

    return moduleService.getRecordNewDefault(moduleName, parentFilter, navigates, request);

  }

  @Override
  @RequestMapping(value = "/fetchdata.do/{id}", method = RequestMethod.GET)
  public @ResponseBody Object getRecordById(String moduleName, @PathVariable("id") String id,
      HttpServletRequest request) {
    log.debug("根据主键取得模块的一条记录:" + moduleName + "," + id);
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("totalCount", 1);
    List<Object> records = new ArrayList<Object>();
    try {
      records.add(moduleDAO.getModuleRecord(moduleName, id, request));
    } catch (Exception e) {
      e.printStackTrace();
    }
    result.put("records", records);
    log.debug("getRecordById返回值：" + result.toString());
    return result;
  }

  @RequestMapping(value = "/create.do", method = RequestMethod.POST)
  public @ResponseBody DataInsertResponseInfo addWithNoPrimaryKey(String moduleName,
      @RequestBody String inserted, HttpServletRequest request) {

    return add(moduleName, inserted, request);
  }

  @Override
  @RequestMapping(value = "/create.do/{id}", method = RequestMethod.POST)
  public @ResponseBody DataInsertResponseInfo add(String moduleName, @RequestBody String inserted,
      HttpServletRequest request) {
    DataInsertResponseInfo result = null;
    try {
      result = moduleService.add(moduleName, inserted, request);
      if (result.getKey() != null) // 如果是空的话，那么就没有保存，错误原因已经在errorMessage里了
        result.getRecords()
            .add(moduleDAO.getModuleRecord(moduleName, result.getKey(), request).toString());
    } catch (DataAccessException e) {
      e.printStackTrace();
      if (result == null)
        result = new DataInsertResponseInfo();
      _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
      // 如果不是索引冲突，那么加入原来的处理。
      if (ModuleServiceFunction.addIX_UniqueMessage(e.getRootCause().getMessage(),
          result.getErrorMessage(), module) == false)
        ModuleServiceFunction.addExceptionCauseToErrorMessage(e.getRootCause().getMessage(),
            result.getErrorMessage(), module.getTf_primaryKey());
      result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
    } catch (Exception e) {
      e.printStackTrace();
      if (result == null)
        result = new DataInsertResponseInfo();
      result.getErrorMessage().put("error", e.getMessage());
      result.setResultCode(ModuleService.STATUS_FAILURE);
    }
    return result;
  }

  @RequestMapping(value = "/update.do/{id}", method = RequestMethod.PUT)
  @Override
  public @ResponseBody DataUpdateResponseInfo update(String moduleName,
      @PathVariable("id") String id, String oldid, String operType, @RequestBody String updated,
      HttpServletRequest request) {

    DataUpdateResponseInfo result = null;
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    // 如果主键值修改了，那么先进行主键的修改
    if (oldid != null && (!oldid.equals(id))) {
      try {
        result = moduleService.changeRecordId(moduleName, id, oldid);
      } catch (ConstraintViolationException e) {
        e.printStackTrace();
        result = new DataUpdateResponseInfo();
        if (e.getCause().toString().toLowerCase().indexOf("primary") >= 0)
          result.getErrorMessage().put(module.getTf_primaryKey(), "修改过后的主键与原有的主键值重复！");
        else
          result.getErrorMessage().put(module.getTf_primaryKey(), "当前主键与子模块有约束关系，不可以修改！");
        result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
      }
      if (!result.getResultCode().equals(0))
        return result;
    }
    // 修改记录
    try {
      result = moduleService.update(moduleName, id, operType, updated, request);
      result.getRecords().add(moduleDAO.getModuleRecord(moduleName, id, request).toString());
    } catch (DataAccessException e) {
      result = new DataUpdateResponseInfo();

      // 如果不是索引冲突，那么加入原来的处理。
      if (ModuleServiceFunction.addIX_UniqueMessage(e.getRootCause().getMessage(),
          result.getErrorMessage(), module) == false)
        ModuleServiceFunction.addExceptionCauseToErrorMessage(e.getRootCause().getMessage(),
            result.getErrorMessage(), module.getTf_primaryKey());

      result.setResultCode(ModuleService.STATUS_VALIDATION_ERROR);
    } catch (Exception e) {
      e.printStackTrace();
      result = new DataUpdateResponseInfo();
      result.getErrorMessage().put("error", e.getMessage());
      result.setResultCode(ModuleService.STATUS_FAILURE);
    }
    return result;
  }

  @Override
  @RequestMapping(value = "/remove.do/{id}", method = RequestMethod.DELETE)
  public @ResponseBody DataDeleteResponseInfo remove(String moduleName, @PathVariable String id,
      HttpServletRequest request) {
    DataDeleteResponseInfo result = null;
    try {
      result = moduleService.remove(moduleName, id, request);
    } catch (DataAccessException e) {
      result = new DataDeleteResponseInfo();

      String errormessage = ModuleServiceFunction
          .addPK_ConstraintMessage(e.getRootCause().getMessage(), moduleName);

      result.setResultMessage(-1,
          errormessage != null ? errormessage : "请检查与本记录相关联的其他数据是否全部清空！<br/>"); // +
      // e.getRootCause().getMessage()
    } catch (Exception e) {
      result = new DataDeleteResponseInfo();
      result.setResultMessage(-1, e.getMessage());
    }
    return result;
  }

  @RequestMapping(value = "/removerecords.do")
  public @ResponseBody DataDeleteResponseInfo removeRecords(String moduleName, String ids,
      String titles, HttpServletRequest request) {
    DataDeleteResponseInfo result = null;
    String[] idarray = ids.split(",");
    String[] titlearray = titles.split("~~");

    result = new DataDeleteResponseInfo();

    for (int i = 0; i < idarray.length; i++) {
      try {
        DataDeleteResponseInfo recordDeleteResult = moduleService.remove(moduleName, idarray[i],
            request);
        if (recordDeleteResult.getResultCode() == 0)
          result.getOkMessageList().add(titlearray[i]);
        else {
          if (recordDeleteResult.getErrorMessageList().size() > 0)
            result.getErrorMessageList()
                .add("【" + titlearray[i] + "】" + recordDeleteResult.getErrorMessageList().get(0));
          else
            result.getErrorMessageList().add("【" + titlearray[i] + "】" + "未知错误！");
        }
      } catch (DataAccessException e) {
        String errormessage = ModuleServiceFunction
            .addPK_ConstraintMessage(e.getRootCause().getMessage(), moduleName);

        result.getErrorMessageList()
            .add("【" + titlearray[i] + "】" + (errormessage != null ? errormessage : "关联的数据未清空！"));
      } catch (Exception e) {
        result.getErrorMessageList().add("【" + titlearray[i] + "】" + e.getMessage());
      }
    }
    result.setResultCode(result.getErrorMessageList().size());
    return result;
  }

  @Override
  @RequestMapping(value = "/exportgrid.do")
  public void exportDataExecl(String moduleName, Integer schemeOrder, String sort, String group,
      String query, String columns, String navigates, String parentFilter,
      HttpServletRequest request, HttpServletResponse response) throws IOException {

    List<?> resultList = (List<?>) moduleService
        .fetchData(moduleName, 0, 65535, sort, query, columns, navigates, parentFilter, request)
        .get("records");
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    String fn = module.getTf_title() + "列表--" + CommonFunction.fu_GenXH() + ".xls";
    List<SqlModuleFilter> navigateFilters = TypeChange.changeToNavigateFilters(navigates);

    // 父模块约束的加入
    if (parentFilter != null && parentFilter.length() > 1) {
      JSONObject jo = JSONObject.fromObject(parentFilter);
      SqlModuleFilter pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
      navigateFilters.add(0, pFilter);
    }

    List<String> conditionList = new ArrayList<String>();
    // 加入树形导航的选择
    if (navigateFilters != null)
      for (SqlModuleFilter filter : navigateFilters)
        conditionList.add(filter.getFieldtitle() + ":" + filter.getText());
    // 全局的筛选文字
    if (query != null && query.length() > 0)
      conditionList.add("列表筛选值：" + query);

    // for (String fieldname : gridFilterData.getGridColumnNames()) {
    // _ModuleField mf = module.getModuleFieldByFieldName(fieldname);
    // if (mf == null)
    // continue;
    // 这里如果有每个字段的筛选，则要加入
    // String filterString =
    // gridFilterData.getEachFieldFilter().get(fieldname);
    // // 如果字段筛选值是combo 的，那么取得那个combo 的 name 值
    // if (mf.haveIdKeyComboSource())
    // filterString =
    // comboValueGeneratorService.getValueWithNameAndId(mf.getTf_comboSource(),
    // dsRequest.getEachFieldFilters().get(fieldname));
    // conditionList.add(mf.getTf_caption() + "筛选值：" + filterString);
    // }

    // 如果有上级编码，将上级的名字和字段名一并写入，递归上去

    SortParameter[] groups = TypeChange.changeToSortParameters(group);
    if (groups != null)
      group = groups[0].getProperty();

    OutputStream os = new ExcelExport(false).GenExcel(request, module, conditionList, resultList,
        group, schemeOrder);
    CommonFunction.download(os, fn, response);
  }

  /**
   * 将grid 转为excel ,再转为 pdf , 然后再传回去
   * 
   * @param moduleName
   * @param schemeOrder
   * @param sort
   * @param group
   * @param query
   * @param columns
   * @param navigates
   * @param parentFilter
   * @param request
   * @param response
   * @throws IOException
   */
  @RequestMapping(value = "/exportgridtoprint.do")
  public void exportDataExeclToPDFToPrint(String moduleName, Integer schemeOrder, String sort,
      String group, String query, String columns, String navigates, String parentFilter,
      HttpServletRequest request, HttpServletResponse response) throws IOException {

    List<?> resultList = (List<?>) moduleService
        .fetchData(moduleName, 0, 65535, sort, query, columns, navigates, parentFilter, request)
        .get("records");
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    String fn = module.getTf_title() + "列表--" + CommonFunction.fu_GenXH() + ".pdf";
    List<SqlModuleFilter> navigateFilters = TypeChange.changeToNavigateFilters(navigates);

    // 父模块约束的加入
    if (parentFilter != null && parentFilter.length() > 1) {
      JSONObject jo = JSONObject.fromObject(parentFilter);
      SqlModuleFilter pFilter = (SqlModuleFilter) JSONObject.toBean(jo, SqlModuleFilter.class);
      navigateFilters.add(0, pFilter);
    }

    List<String> conditionList = new ArrayList<String>();
    // 加入树形导航的选择
    if (navigateFilters != null)
      for (SqlModuleFilter filter : navigateFilters)
        conditionList.add(filter.getFieldtitle() + ":" + filter.getText());
    // 全局的筛选文字
    if (query != null && query.length() > 0)
      conditionList.add("列表筛选值：" + query);

    // for (String fieldname : gridFilterData.getGridColumnNames()) {
    // _ModuleField mf = module.getModuleFieldByFieldName(fieldname);
    // if (mf == null)
    // continue;
    // 这里如果有每个字段的筛选，则要加入
    // String filterString =
    // gridFilterData.getEachFieldFilter().get(fieldname);
    // // 如果字段筛选值是combo 的，那么取得那个combo 的 name 值
    // if (mf.haveIdKeyComboSource())
    // filterString =
    // comboValueGeneratorService.getValueWithNameAndId(mf.getTf_comboSource(),
    // dsRequest.getEachFieldFilters().get(fieldname));
    // conditionList.add(mf.getTf_caption() + "筛选值：" + filterString);
    // }

    // 如果有上级编码，将上级的名字和字段名一并写入，递归上去

    SortParameter[] groups = TypeChange.changeToSortParameters(group);
    if (groups != null)
      group = groups[0].getProperty();

    OutputStream os = new ExcelExport(true).GenExcel(request, module, conditionList, resultList,
        group, schemeOrder);

    OutputStream os1 = Office2PDF.office2PDF("xls",
        new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray()));

    CommonFunction.downloadAndOpenPDF(os1, fn, response);
  }

  @RequestMapping(value = "/exportrecord.do")
  @Override
  public void exportRecordExcel(String moduleName, String id, String title, String schemeId,
      HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 没有设置的导出方案，则导出默认的方案
    _Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);
    String fn = module.getTf_title() + "-" + title + "-" + CommonFunction.fu_GenXH() + ".xls";
    if (schemeId == null || schemeId.equals("null")) {
      JSONObject record = moduleDAO.getModuleRecord(moduleName, id, request);
      OutputStream os = new ExcelExport(false).GenRecordExcel(request, module, null, record, null);
      CommonFunction.download(os, fn, response);
    } else {
      OutputStream os = printRecordService.GenRecordExcel(request, moduleName, schemeId, id);
      CommonFunction.download(os, fn, response);
    }
  }

  /**
   * 导出用于新增的Excel表格
   * 
   * @param moduleId
   * @param request
   * @param response
   * @throws IOException
   */
  @RequestMapping(value = "/downloadinsertexcel.do")
  public void downloadInsertExcel(String moduleId, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    _Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
    String fn = module.getTf_title() + "(新增导入表)" + CommonFunction.fu_GenXH() + ".xls";
    OutputStream os = new ExcelExport(false).GenInsertExcel(request, module);
    CommonFunction.download(os, fn, response);
  }

  /**
   * 导出用于新增一条记录的 的Excel表格文件，是由管理人员上传的，可以由其他人员下载，添好后再上传新增
   */
  @RequestMapping(value = "/downloadinsertexcelrecord.do")
  public void downloadInsertExcel(String moduleId, String methodId, HttpServletRequest request,
      HttpServletResponse response) throws IOException, SQLException {

    _ModuleExcelRecordAdd record = (_ModuleExcelRecordAdd) systemBaseDAO
        .findById(_ModuleExcelRecordAdd.class, Integer.parseInt(methodId));
    if (record.getTf_filedata() == null || record.getTf_filedata().length() == 0)
      CommonFunction.downloadfilenotfound(response);
    else
      CommonFunction.download(record.getTf_filedata().getBinaryStream(), record.getTf_filename(),
          "attachment", response);
  }

  // 上传excel 加入一批记录
  @RequestMapping("uploadexcelinsert.do")
  public @ResponseBody Object uploadnew(UploadFileBean uploadExcelBean, BindingResult bindingResult,
      HttpServletRequest request) throws IOException {
    // 写入记录信息
    // System.out.println("moduleid:" + uploadExcelBean.getModuleId());
    // System.out.println("file:" + uploadExcelBean.getFile().getSize());
    // System.out.println("file:" + uploadExcelBean.getFile().getName());
    // System.out.println("file:" +
    // uploadExcelBean.getFile().getOriginalFilename());
    // System.out.println("file:" + uploadExcelBean.getFile().getContentType());

    return uploadExcelService.uploadnew(uploadExcelBean, bindingResult, this, request);

  }

  // 上传excel 加入一条记录
  @RequestMapping("uploadexcelinsertrecord.do")
  public @ResponseBody Object uploadnewRecord(UploadFileBean uploadExcelBean,
      BindingResult bindingResult, HttpServletRequest request) throws IOException {
    // 写入记录信息
    // System.out.println("moduleid:" + uploadExcelBean.getModuleId());
    // System.out.println("id:" + uploadExcelBean.getId());
    // System.out.println("file:" + uploadExcelBean.getFile().getSize());
    // System.out.println("file:" + uploadExcelBean.getFile().getName());
    // System.out.println("file:" +
    // uploadExcelBean.getFile().getOriginalFilename());
    // System.out.println("file:" + uploadExcelBean.getFile().getContentType());

    return uploadExcelService.uploadnewRecord(uploadExcelBean, bindingResult, this, request);

  }

  /**
   * 用户在form中选择了一个上传的图片以后，需要把图像的内容再返回给客户端，使其可以生成一个临时的图像，显示在img中
   * 
   * @param uploadExcelBean
   * @param bindingResult
   * @param request
   * @return
   * @throws IOException
   */
  @RequestMapping(value = "uploadimagefileandreturn.do")
  public ResponseEntity<Map<String, Object>> uploadImageFileAndReturn(
      UploadFileBean uploadExcelBean, BindingResult bindingResult, HttpServletRequest request)
          throws IOException {
    Map<String, Object> map = new HashMap<String, Object>();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    ActionResult ar = moduleService.uploadImageFileAndReturn(uploadExcelBean, bindingResult,
        request);
    map.put("success", ar.getSuccess());
    map.put("msg", ar.getMsg());
    return new ResponseEntity<Map<String, Object>>(map, headers, HttpStatus.OK);

  }

  @RequestMapping("uploadmodulefile.do")
  public ResponseEntity<Map<String, Object>> uploadModuleFile(UploadFileBean uploadExcelBean,
      BindingResult bindingResult, HttpServletRequest request) throws IOException {

    Map<String, Object> map = new HashMap<String, Object>();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.TEXT_PLAIN);
    ActionResult ar = moduleService.uploadModuleFile(uploadExcelBean, bindingResult, request);
    map.put("success", ar.getSuccess());
    map.put("msg", ar.getMsg());
    return new ResponseEntity<Map<String, Object>>(map, headers, HttpStatus.OK);

  }

  /**
   * 下载一个模块的 excel report 月报
   * 
   * @param moduleId
   * @param excelReportId
   * @param year
   * @param month
   * @throws SQLException
   * @throws IOException
   * @throws NumberFormatException
   */
  @RequestMapping("downloadexcelreportmonth.do")
  public void downloadExcelReportMonth(HttpServletRequest request, HttpServletResponse response,
      String moduleId, String excelReportId, Integer year, Integer month)
          throws NumberFormatException, IOException, SQLException {
    new ExcelReportService().genExcelReportMonth(request, response, moduleId,
        Integer.parseInt(excelReportId), year, month);
  }

  @RequestMapping("downloadrecordexcelreport.do")
  public void downloadRecordExcelReport(HttpServletRequest request, HttpServletResponse response,
      String moduleId, String excelReportId, String id)
          throws NumberFormatException, IOException, SQLException, OgnlException {
    moduleService.downloadRecordExcelReport(request, response, moduleId, excelReportId, id);

  }

  @RequestMapping("downloadrecordexcelreportPDF.do")
  public void downloadRecordExcelReportToPDF(HttpServletRequest request,
      HttpServletResponse response, String moduleId, String excelReportId, String id)
          throws NumberFormatException, IOException, SQLException, OgnlException {
    moduleService.downloadRecordExcelReportToPDF(request, response, moduleId, excelReportId, id);

  }

  @RequestMapping(value = "/printrecord.do", produces = "text/plain;charset=UTF-8")
  @Override
  public @ResponseBody String printRecordExcel(String moduleName, String id, String schemeId,
      HttpServletRequest request, HttpServletResponse response) throws IOException {

    return printRecordService.genPrintHtml(moduleName, id, schemeId, request);

  }

}
