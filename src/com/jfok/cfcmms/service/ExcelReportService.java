package com.jfok.cfcmms.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ognl.OgnlException;

import net.sf.json.JSONObject;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.core.module.SqlModuleFilter;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleExcelReport;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.Office2PDF;


public class ExcelReportService {

	private SystemBaseDAO systemBaseDAO = (SystemBaseDAO) SystemInfoService
			.getBean(SystemBaseDAO.class);

	private ModuleService moduleService = (ModuleService) SystemInfoService
			.getBean(ModuleService.class);

	private WritableWorkbook workbook;
	private Workbook wb;
	private WritableSheet sheet;

	private List<?> resultList;

	private int cols;
	private int rows;

	/**
	 * 取得一个模块的月度报表， 格式根据 {=ＸＸＸＸ名称} 来定位行，与行里的所有数据进行比较， {tf_xm01} {tf_xm02}
	 * 
	 * @param request
	 * @param response
	 * @param moduleId
	 * @param id
	 * @param year
	 * @param month
	 * @throws IOException
	 * @throws SQLException
	 */
	public void genExcelReportMonth(HttpServletRequest request, HttpServletResponse response,
			String moduleId, int id, Integer year, Integer month) throws IOException, SQLException {

		_Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
		_ModuleExcelReport report = (_ModuleExcelReport) systemBaseDAO.findById(
				_ModuleExcelReport.class, id);

		List<SqlModuleFilter> filters = new ArrayList<SqlModuleFilter>();
		SqlModuleFilter filter = new SqlModuleFilter();
		filter.setTableAsName(module.getTableAsName());
		filter.setModuleId(module.getTf_moduleId());
		filter.setModuleName(module.getTf_moduleName());
		filter.setPrimarykey(module.getTf_yearField());
		filter.setEqualsValue("" + year);
		filters.add(filter);
		filter = new SqlModuleFilter();
		filter.setTableAsName(module.getTableAsName());
		filter.setModuleId(module.getTf_moduleId());
		filter.setModuleName(module.getTf_moduleName());
		filter.setPrimarykey(module.getTf_monthField());
		filter.setEqualsValue("" + month);
		filters.add(filter);

		resultList = (List<?>) moduleService.fetchDataInner(module.getTf_moduleName(), 0, 65535, null,
				null, null, filters, null, null, request).getMatchingObjects();

		String downloadfilename = report.getTf_name() + "(" + year + "年"
				+ (month >= 10 ? month : "0" + month) + "月).xls";

		OutputStream os = generateExcelReport(request, response, module, report.getTf_filedata()
				.getBinaryStream(), year, month);
		if (os != null)
			CommonFunction.download(os, downloadfilename, response);

	}

	/**
	 * 根据模块excel 表 ，添充并 下载单条记录的excel
	 * 
	 * @param request
	 * @param response
	 * @param moduleId
	 * @param parseInt
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 * @throws OgnlException
	 */

	String downloadfilename;
	OutputStream os;

	/**
	 * 下载 添好数据以后下载 excel report
	 * 
	 * @param request
	 * @param response
	 * @param moduleId
	 * @param reportId
	 * @param id
	 * @throws IOException
	 * @throws SQLException
	 * @throws OgnlException
	 */
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void genRecordExcelReportMonth(HttpServletRequest request, HttpServletResponse response,
			String moduleId, int reportId, String id) throws IOException, SQLException, OgnlException {

		genRecordExcelReportOutPutStream(request, response, moduleId, reportId, id);

		if (os != null)
			CommonFunction.download(os, downloadfilename, response);

	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void genRecordExcelReportToPDF(HttpServletRequest request, HttpServletResponse response,
			String moduleId, int reportId, String id) throws IOException, SQLException, OgnlException {

		genRecordExcelReportOutPutStream(request, response, moduleId, reportId, id);
		downloadfilename = downloadfilename.replaceAll("xls", "pdf");

		if (os != null) {
			OutputStream os1 = Office2PDF.office2PDF("xls", new ByteArrayInputStream(
					((ByteArrayOutputStream) os).toByteArray()));

			CommonFunction.downloadAndOpenPDF(os1, downloadfilename, response);
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void genRecordExcelReportOutPutStream(HttpServletRequest request,
			HttpServletResponse response, String moduleId, int reportId, String id) throws IOException,
			SQLException, OgnlException {

		_Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
		_ModuleExcelReport report = (_ModuleExcelReport) systemBaseDAO.findById(
				_ModuleExcelReport.class, reportId);
		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(module.getTf_moduleName());
		Object record = systemBaseDAO.findById(beanClass, id);
		os = new ByteArrayOutputStream();
		if (!getWorkBook(report.getTf_filedata().getBinaryStream(), os, response))
			return;

		sheet = workbook.getSheet(0);
		cols = sheet.getColumns(); // 共有多少列
		rows = sheet.getRows(); // 共有多少行
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// System.out.println(sheet.getCell(j, i).getContents());
				String content = sheet.getCell(j, i).getContents();
				if (content != null && content.indexOf("{") >= 0) {
					content = content.replaceFirst("\\{", "").replaceAll("\\}", "");
					Object value = PrintRecordService.translateToValue(content, record);
					if (value == null)
						replaceCell("", j, i);
					else
						replaceCell(value, j, i);
				}
			}
		}

		try {
			workbook.write();
			workbook.close();
			wb.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		downloadfilename = report.getTf_name() + ".xls";

	}

	// 根据保存在字段中的excel的输入流，打开一个excel
	public boolean getWorkBook(InputStream is, OutputStream os, HttpServletResponse response)
			throws IOException {
		WorkbookSettings settings = new WorkbookSettings();
		settings.setWriteAccess(null);
		try {
			wb = Workbook.getWorkbook(is);
			workbook = Workbook.createWorkbook(os, wb, settings);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				workbook.write();
				workbook.close();
				wb.close();
			} catch (WriteException e1) {
				e.printStackTrace();
			}
			CommonFunction.downloadExceptionString(response, "下载报表时出错啦,打开文件查看原因.txt",
					"报表模板文件不能被Excel解释程序打开,请确定文件的完整性," + "或者用Excel2003以下的程序打开后再重新保存后，再上传!");
			return false;
		}
		return true;
	}

	public OutputStream generateExcelReport(HttpServletRequest request, HttpServletResponse response,
			_Module module, InputStream is, int y, int m) throws IOException {
		// 用这个settings可以防止 workbook.write 时报错

		OutputStream os = new ByteArrayOutputStream();

		if (!getWorkBook(is, os, response))
			return null;

		sheet = workbook.getSheet(0);
		cols = sheet.getColumns(); // 共有多少列
		rows = sheet.getRows(); // 共有多少行
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// System.out.println(sheet.getCell(j, i).getContents());
				String content = sheet.getCell(j, i).getContents();
				if (content != null && content.indexOf("{") >= 0)
					translate(content, j, i, y, m);
			}
		}
		for (int i = 0; i < rows; i++) {
			translateRows(i);
		}
		try {
			workbook.write();
			workbook.close();
			wb.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return os;
	}

	/**
	 * 转换一行，先找到该行的{=xXXXX},来确定记录值
	 * 
	 * @param i
	 */
	private void translateRows(int i) {
		// 先看看有没有 {=} 这样的
		for (int j = 0; j < cols; j++) {
			String content = sheet.getCell(j, i).getContents();
			if (content != null && content.indexOf("{=") >= 0) {
				content = content.replaceFirst("\\{=", "").replaceAll("\\}", "");
				replaceCell(content, j, i);
				JSONObject record = findContentFromRecords(content);
				replaceContentValueFromRecords(i, record);
			}
		}
	}

	// 找到行
	private JSONObject findContentFromRecords(String content) {
		for (Object object : resultList) {
			JSONObject jsonObject = (JSONObject) object;
			for (Object o : jsonObject.values()) {
				if (o.toString().equals(content))
					return jsonObject;
			}
		}
		return null;
	}

	// 替换里面的具体值
	private void replaceContentValueFromRecords(int i, JSONObject jsonObject) {
		for (int j = 0; j < cols; j++) {
			String content = sheet.getCell(j, i).getContents();
			if (content != null && content.indexOf("{") >= 0) {
				String fieldname = content.replaceFirst("\\{", "").replaceAll("\\}", "");
				Object value = null;
				if (jsonObject != null)
					value = jsonObject.get(fieldname);
				if (value == null)
					replaceCell("", j, i);
				else {
					replaceCell(value, j, i);
				}
			}
		}
	}

	/**
	 * 转换的总控，包括年月，等以后再加其他的
	 * 
	 * @param text
	 * @param j
	 * @param i
	 * @param y
	 * @param m
	 */
	private void translate(String text, int j, int i, int y, int m) {
		String tran = translateYearMonth(text, y, m);
		if (!text.equals(tran)) {
			replaceCell(tran, j, i);
		}

	}

	/**
	 * 把单元格的值改变，格式仍然用原来的
	 * 
	 * @param text
	 * @param j
	 * @param i
	 */
	private void replaceCell(Object text, int j, int i) {
		// if (text != null)
		// System.out.println(text.getClass().getSimpleName());
		if (text == null)
			replaceCell("", j, i);
		else if (text instanceof Double)
			replaceCell((Double) text, j, i);
		else if (text instanceof BigDecimal)
			replaceCell(((BigDecimal) text).doubleValue(), j, i);
		else if (text instanceof Integer)
			replaceCell((Integer) text, j, i);
		else if (text instanceof Date)
			replaceCell((Date) text, j, i);
		else if (text instanceof Timestamp)
			replaceCell((Timestamp) text, j, i);
		else
			replaceCell(text.toString(), j, i);
		// System.out.println(text.getClass().getSimpleName());
	}

	/**
	 * 把单元格的值改变，格式仍然用原来的
	 * 
	 * @param text
	 * @param j
	 * @param i
	 */
	private void replaceCell(String text, int j, int i) {
		try {
			WritableCell cell = sheet.getWritableCell(j, i);
			CellFormat cf = cell.getCellFormat();
			Label newcell = new Label(j, i, text);
			newcell.setCellFormat(cf);
			sheet.addCell(newcell);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把单元格的值改变，格式仍然用原来的
	 * 
	 * @param text
	 * @param j
	 * @param i
	 */
	private void replaceCell(Double text, int j, int i) {
		try {
			WritableCell cell = sheet.getWritableCell(j, i);
			CellFormat cf = cell.getCellFormat();
			jxl.write.Number newcell = new jxl.write.Number(j, i, text);
			newcell.setCellFormat(cf);
			sheet.addCell(newcell);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private void replaceCell(Integer text, int j, int i) {
		try {
			WritableCell cell = sheet.getWritableCell(j, i);
			CellFormat cf = cell.getCellFormat();
			jxl.write.Number newcell = new jxl.write.Number(j, i, text);
			newcell.setCellFormat(cf);
			sheet.addCell(newcell);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private void replaceCell(Date text, int j, int i) {
		try {
			WritableCell cell = sheet.getWritableCell(j, i);
			CellFormat cf = cell.getCellFormat();
			jxl.write.DateTime newcell = new jxl.write.DateTime(j, i, text);
			newcell.setCellFormat(cf);
			sheet.addCell(newcell);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	private String translateYearMonth(String text, int y, int m) {
		String result = text;
		if (text == null)
			return null;
		if (result.indexOf("{year}") >= 0) {
			result = result.replaceAll("\\{year\\}", "" + y);
		}
		if (result.indexOf("{month}") >= 0) {
			result = result.replaceAll("\\{month\\}", "" + m);
		}
		return result;
	}

}
