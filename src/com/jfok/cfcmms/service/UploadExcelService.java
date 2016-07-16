package com.jfok.cfcmms.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.jfok.cfcmms.DAO.ModuleDAO;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.controller.ModuleController;
import com.jfok.cfcmms.hibernate.system.log._SystemOperateLog;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.module._ModuleField;
import com.jfok.cfcmms.hibernate.system.viewSetting._ModuleExcelRecordAdd;
import com.jfok.cfcmms.share.module.DataInsertResponseInfo;
import com.jfok.cfcmms.share.module.ModuleConstants;
import com.jfok.cfcmms.util.ActionResult;
import com.jfok.cfcmms.util.TypeChange;
import com.jfok.cfcmms.util.UploadFileBean;


@Service
/**
 * 用来处理上传上来的 excel 用于修改过新增
 * @author jiangfeng
 *
 */
public class UploadExcelService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private ModuleDAO moduleDAO;

	@Resource
	private ModuleService moduleService;

	public Object uploadnew(UploadFileBean uploadExcelBean, BindingResult bindingResult,
			ModuleController moduleController, HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		ActionResult result = new ActionResult();
		// 用这个settings可以防止 workbook.write 时报错
		WorkbookSettings settings = new WorkbookSettings();
		settings.setWriteAccess(null);
		Workbook wb = null;
		WritableWorkbook workbook = null;
		OutputStream os = new ByteArrayOutputStream();
		try {
			wb = Workbook.getWorkbook(uploadExcelBean.getFile().getInputStream());
			workbook = Workbook.createWorkbook(os, wb, settings);
			// wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("上传的文件不能被Excel解释程序打开,请确定文件的完整性," + "或者用Excel2003以下的程序打开后再重新保存后，再执行上传操作!");
			try {
				workbook.write();
				workbook.close();
				wb.close();
			} catch (WriteException e1) {
				e.printStackTrace();
			}
			return result;
		}

		WritableSheet sheet = workbook.getSheet(0);

		// System.out.println("sheet Columns :" + sheet.getColumns());
		// System.out.println("sheet Rows :" + sheet.getRows());

		// 找出每一条记录，然后分别导入
		_Module module = SystemAndLoginInfoService.getModuleWithId(uploadExcelBean.getModuleId());
		List<_ModuleField> canInsertFields = new ArrayList<_ModuleField>();
		for (_ModuleField field : module.getTf_fields()) {
			if (field.getTf_allowInsertExcel())
				canInsertFields.add(field);
		}
		int nameRow = 3;
		// 判断excel表里的各个字段的名称是不是原来的，有可能在下载过excel表以后，允许导出下载的字段又有过变化了
		for (int i = 0; i < canInsertFields.size(); i++) {
			_ModuleField field = canInsertFields.get(i);
			String content = sheet.getCell(i, nameRow).getContents();
			if (content == null || !content.equals(field.getTf_fieldName())) {
				result.setSuccess(false);
				result.setMsg("上传的Excel文件中的字段与当前能用Excel导入的字段的设置不相符，" + "请重新下载用于新增的Excel表，填入数据后重新上传。");
				try {
					workbook.write();
					workbook.close();
					wb.close();
				} catch (WriteException e) {
					e.printStackTrace();
				}
				return result;
			}
		}

		sb.append("上传导入时间:" + TypeChange.DateToString(new Date()) + ";\r\n");
		int firstRow = 5;
		int importok = 0;
		sb.append("共有记录：" + (sheet.getRows() - firstRow) + " 条;\r\n");
		for (int i = firstRow; i < sheet.getRows(); i++) {
			// System.out.println("开始新的一行了－－－－－－－－－");
			JSONObject jsonObject = new JSONObject();
			// 对于每一个字段，判断一下类型，以及是 否是 manytoone
			// sheet.getCell(j, i).getContents() 没有空值，无值的就是 空串
			String recordResult = "";
			for (int j = 0; j < canInsertFields.size(); j++) {
				String r = setJSONValue(jsonObject, canInsertFields.get(j), sheet.getCell(j, i)
						.getContents());
				if (r != null)
					recordResult += r;
			}
			// System.out.println(jsonObject.toString());
			if (recordResult.length() > 0) {
				try {
					sheet.addCell(new Label(canInsertFields.size(), i, "--不能导入--:" + recordResult));
				} catch (Exception e) {
				}
			} else {
				DataInsertResponseInfo addresult = moduleController.add(module.getTf_moduleName(),
						jsonObject.toString(), request);
				// 将操作结果返回到最后一列
				recordResult = addresult.getMessage().replaceAll("<br/>", ";");
				importok += (recordResult.length() == 0 ? 1 : 0);
				try {
					sheet.addCell(new Label(canInsertFields.size(), i, recordResult.length() == 0 ? "--已导入--"
							: "--不能导入--:" + recordResult));
				} catch (Exception e) {
				}
				// System.out.println("导入加入结果:" + addresult.getMessage());
			}
		}
		sb.append("成功导入：" + importok + " 条记录;\r\n");
		sb.append("导入失败：" + (sheet.getRows() - firstRow - importok) + " 条记录;\r\n");
		try {
			WritableCell cell = sheet.getWritableCell(0, 2);
			CellFormat cf = cell.getCellFormat();
			Label newcell = new Label(0, 2, sb.toString());
			newcell.setCellFormat(cf);
			sheet.addCell(newcell);
			workbook.write();
			workbook.close();
			wb.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}

		_SystemOperateLog log = moduleDAO.saveUploadExcelOperateLog(module, "上传Excel批量新增",
				uploadExcelBean.getFile().getOriginalFilename(), os, "remark", request);
		result.setTag(log.getTf_systemlogId().toString());
		result.setMsg(sb.toString().replaceAll(";", ";<br/>"));
		return result;
	}

	public String setJSONValue(JSONObject jsonObject, _ModuleField field, String value) {
		// System.out.println("字段：" + field.getTf_title() + "," +
		// field.getTf_fieldType() + ",值:" + value);
		if (value.length() == 0) {
			jsonObject.put(field.getTf_fieldName(), null);
			if (field.getTf_isRequired())
				return field.getTf_title() + " 是必填项;";
			else
				return null;
		}
		if (field.isManyToOne()) {
			// 如果这是一个父模块的字段，1.先检查是否是主键，2.检查是否是fieldnames,3.检查是否只有一个符合条件的like,
			// 如果都没有，则不转入
			_Module pmodule = SystemAndLoginInfoService.getModuleWithName(field.getTf_fieldType());
			Object pid = moduleDAO.getBeanIdWithIdOrName(pmodule, value);
			if (pid == null) {
				return field.getTf_title() + " 没有找到和此值相对应的父模块记录;";
			} else {
				jsonObject.put(
						pmodule.getTableAsName() + ModuleConstants.SEPARATOR + pmodule.getTf_primaryKey(), pid);
			}
		} else {
			String type = field.getTf_fieldType().toLowerCase();
			if (type.equals("integer")) {
				jsonObject.put(field.getTf_fieldName(), TypeChange.StringtoInteger(value));
			} else if (type.equals("double") || type.equals("float") || type.equals("money")) {
				jsonObject.put(field.getTf_fieldName(), TypeChange.StringtoDouble(value));
			} else if (type.equals("boolean")) {
				jsonObject.put(field.getTf_fieldName(), TypeChange.StringtoBoolean(value));
			} else if (type.equals("date")) {
				jsonObject.put(field.getTf_fieldName(),
						TypeChange.DateToString(TypeChange.StringToDate(value)));
			} else
				jsonObject.put(field.getTf_fieldName(), value);
		}
		return null;
	}

	// 上传excel 新增一条记录，读取对应信息，然后保存写入

	@SuppressWarnings({ "unchecked" })
	public Object uploadnewRecord(UploadFileBean uploadExcelBean, BindingResult bindingResult,
			ModuleController moduleController, HttpServletRequest request) throws IOException {

		ActionResult result = new ActionResult();
		Workbook wb = null;
		WritableWorkbook workbook = null;
		OutputStream os = new ByteArrayOutputStream();
		try {
			wb = Workbook.getWorkbook(uploadExcelBean.getFile().getInputStream());
			workbook = Workbook.createWorkbook(os, wb);
			// wb.close();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("上传的文件不能被Excel解释程序打开,请确定文件的完整性," + "或者用Excel2003以下的程序打开后再重新保存后，再执行上传操作!");
			try {
				workbook.write();
				workbook.close();
				wb.close();
			} catch (WriteException e1) {
				e.printStackTrace();
			}
			return result;
		}

		WritableSheet sheet = workbook.getSheet(0);

		// System.out.println("sheet Columns :" + sheet.getColumns());
		// System.out.println("sheet Rows :" + sheet.getRows());

		// 找出每一条记录，然后分别导入
		_Module module = SystemAndLoginInfoService.getModuleWithId(uploadExcelBean.getModuleId());

		_ModuleExcelRecordAdd recordAdd = (_ModuleExcelRecordAdd) systemBaseDAO.findById(
				_ModuleExcelRecordAdd.class, uploadExcelBean.getId());

		List<FieldCellRelation> cellRelations = new ArrayList<FieldCellRelation>();
		for (String s : recordAdd.getTf_relation().replaceAll("\r", "").replaceAll("\n", "").split(";")) {
			if (s != null && s.length() > 0)
				cellRelations.add(new FieldCellRelation(s, module));
		}
		JSONObject jsonObject = new JSONObject();

		// 去取得新增模块时的初始值
		Map<String, Object> defaults = (Map<String, Object>) moduleService.getRecordNewDefault(
				module.getTf_moduleName(), null, null, request);
		jsonObject.putAll(defaults);

		// 对于每一个字段，判断一下类型，以及是 否是 manytoone
		// sheet.getCell(j, i).getContents() 没有空值，无值的就是 空串

		String recordResult = "";
		for (int j = 0; j < cellRelations.size(); j++) {
			FieldCellRelation fc = cellRelations.get(j);
			String r = setJSONValue(jsonObject, fc.getModuleField(),
					sheet.getCell(fc.getCol(), fc.getRow()).getContents());
			if (r != null)
				recordResult += r;
		}
		// System.out.println(jsonObject.toString());
		if (recordResult.length() > 0) { // 出错了，不能导入
			result.setMsg("导入失败：" + recordResult);
			result.setSuccess(false);
		} else {
			DataInsertResponseInfo addresult = moduleController.add(module.getTf_moduleName(),
					jsonObject.toString(), request);
			// 将操作结果返回到最后一列s
			recordResult = addresult.getMessage().replaceAll("<br/>", ";");
			if (recordResult.length() != 0) {
				result.setMsg("导入失败：" + recordResult);
				result.setSuccess(false);
			}
		}
		try {
			workbook.close();
			wb.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		_SystemOperateLog log = moduleDAO.saveUploadExcelOperateLog(module, "上传Excel新增一条",
				uploadExcelBean.getFile().getOriginalFilename(),
				uploadExcelBean.getFile().getInputStream(), "remark", request);
		result.setTag(log.getTf_systemlogId().toString());
		return result;
	}
}

class FieldCellRelation {
	private _ModuleField moduleField;
	private String fieldname;
	private int col;
	private int row;

	public FieldCellRelation(String string, _Module module) {
		String[] part = string.split("=");
		if (part.length == 2) {
			//System.out.println(part[0] + "---" + part[1]);
			fieldname = part[0];
			moduleField = module.getModuleFieldByFieldName(fieldname);
			String v = part[1].toUpperCase();
			col = (int) (v.charAt(0)) - (int) 'A';
			row = Integer.parseInt(v.substring(1)) - 1;
		}
		//System.out.println(this);
	}

	@Override
	public String toString() {
		return "FieldCellRelation [fieldname=" + fieldname + ", col=" + col + ", row=" + row + "]";
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public _ModuleField getModuleField() {
		return moduleField;
	}

	public void setModuleField(_ModuleField moduleField) {
		this.moduleField = moduleField;
	}

}
