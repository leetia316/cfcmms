package com.jfok.cfcmms.service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import ognl.Ognl;
import ognl.OgnlException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.hibernate.system.viewSetting._PrintSchemeGroup;
import com.jfok.cfcmms.hibernate.system.viewSetting._PrintSchemeGroupCell;
import com.jfok.cfcmms.util.ModuleServiceFunction;
import com.jfok.cfcmms.util.TypeChange;

@Service
public class PrintRecordService {

	@Resource
	private UserService userService;

	@Resource
	private SystemBaseDAO systemBaseDAO;

	// 根据grid中列出的字段导出当前记录

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public OutputStream GenRecordExcel(HttpServletRequest request, String moduleName,
			String schemeId, String id) {

		_Module module = SystemAndLoginInfoService.getModuleWithName(moduleName);

		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		Object record = systemBaseDAO.findById(beanClass, id);

		List<_PrintSchemeGroup> schemeGroups = (List<_PrintSchemeGroup>) systemBaseDAO
				.findByPropertyAllSort(_PrintSchemeGroup.class, "tf_printGroupOrder", "asc",
						"tf_PrintScheme.tf_printSchemeId", Integer.parseInt(schemeId), null, null);

		WritableWorkbook workbook = null;
		OutputStream os = new ByteArrayOutputStream();
		try {
			workbook = Workbook.createWorkbook(os); // .createWorkbook(new
																							// File(filename));
			workbook.setColourRGB(Colour.LIGHT_TURQUOISE2, 0xF8, 0xFF, 0xEF);
			workbook.setColourRGB(Colour.PALE_BLUE, 0xbb, 0xdd, 0xff);
			WritableSheet sheet = workbook.createSheet(module.getTf_title(), 0);
			Integer nowRow = 0; // 列

			String widths[] = schemeGroups.get(0).getTf_widths().split(",");
			for (int i = 0; i < widths.length; i++)
				sheet.setColumnView(i, Integer.parseInt(widths[i]) / 8);

			for (_PrintSchemeGroup group : schemeGroups) {

				List<_PrintSchemeGroupCell> schemeGroupCells = (List<_PrintSchemeGroupCell>) systemBaseDAO
						.findByPropertyAllSort(_PrintSchemeGroupCell.class, "tf_order", "asc",
								"tf_PrintSchemeGroup.tf_printSchemeGroupId", group.getTf_printSchemeGroupId(),
								null, null);
				// 去掉disabled 的
				for (int i = schemeGroupCells.size() - 1; i >= 0; i--)
					if (schemeGroupCells.get(i).getTf_disabled())
						schemeGroupCells.remove(i);
				int r = group.genCellRowAndCol(schemeGroupCells, 0);

				for (_PrintSchemeGroupCell cell : schemeGroupCells) {
					if (cell.getTf_height() != null && cell.getTf_height() > 0)
						sheet.setRowView(cell.getRownumber() + nowRow, cell.getTf_height() * 10);

					if (cell.colspan() > 1 || cell.rowspan() > 1)
						sheet.mergeCells(cell.getColnumber(), cell.getRownumber() + nowRow, cell.getColnumber()
								+ cell.colspan() - 1, cell.getRownumber() + nowRow + cell.rowspan() - 1);

					WritableFont textFont = new WritableFont(WritableFont.createFont("宋体"), 10,
							WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
					WritableCellFormat cellFormat = null;
					Object cellObject = null;
					// 返回的值
					Map<String, Object> value = getCellValue(cell.cellText(), record);
					Object firstExpressValue = value.get("firstvalue");
					if (firstExpressValue != null) {
						if ((firstExpressValue instanceof Date || firstExpressValue instanceof java.sql.Date)
								&& value.get("value").toString().length() == 10) {
							cellFormat = new WritableCellFormat(textFont, new DateFormat("yyyy-mm-dd"));
							cellObject = new jxl.write.DateTime(cell.getColnumber(),
									cell.getRownumber() + nowRow, (Date) firstExpressValue, cellFormat);
						}

						if ((firstExpressValue instanceof Double || firstExpressValue instanceof Float)
								&& TypeChange.StringtoDouble(value.get("value").toString()) != 0) {
							cellFormat = new WritableCellFormat(textFont, new NumberFormat("#,##0.00;-#,##0.00;",
									NumberFormat.COMPLEX_FORMAT));
							cellObject = new jxl.write.Number(cell.getColnumber(), cell.getRownumber() + nowRow,
									(Double) firstExpressValue, cellFormat);
						}

					}

					if (cellFormat == null)
						cellFormat = new WritableCellFormat(textFont);
					cellFormat.setBorder(group.getTf_border() > 0 ? Border.ALL : Border.NONE,
							BorderLineStyle.THIN); // 线条
					cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
					if (cell.getTf_valign() != null) {
						if (cell.getTf_valign().equals("top"))
							cellFormat.setVerticalAlignment(VerticalAlignment.TOP); //
						if (cell.getTf_valign().equals("bottom"))
							cellFormat.setVerticalAlignment(VerticalAlignment.BOTTOM);
					}
					cellFormat.setAlignment(Alignment.LEFT); // 水平对齐
					if (cell.getTf_align() != null) {
						if (cell.getTf_align().equals("middle"))
							cellFormat.setAlignment(Alignment.CENTRE);
						if (cell.getTf_align().equals("right"))
							cellFormat.setAlignment(Alignment.RIGHT);
					}

					if (!cell.containFormula()) {
						WritableFont BoldFont = new WritableFont(WritableFont.createFont("宋体"), 10,
								WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.DARK_BLUE);
						cellFormat.setFont(BoldFont);

					}
					if (cell.getTf_cssStyle() != null && cell.getTf_cssStyle().equals("headtitle")) {
						WritableFont HeadFont = new WritableFont(WritableFont.createFont("黑体"), 16,
								WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.DARK_BLUE);
						cellFormat.setFont(HeadFont);
					}

					cellFormat.setBackground(Colour.LIGHT_TURQUOISE2);
					cellFormat.setWrap(true); // 是否换行

					if (cellObject == null)
						cellObject = new Label(cell.getColnumber(), cell.getRownumber() + nowRow, value.get(
								"value").toString(), cellFormat);
					sheet.addCell((WritableCell) cellObject);

				}
				nowRow += r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.write();
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return os;
	}

	public Map<String, Object> getCellValue(String text, Object record) {

		// 更改里面的值
		// .*? 加？表示非贪婪模式
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher(text);

		StringBuffer resultBuffer = new StringBuffer();
		Object firstExpressValue = null;
		Object expressValue = null;
		int i = 0;
		while (matcher.find()) {
			// 花括号内的原字符串
			String expressText = text.substring(matcher.start() + 1, matcher.end() - 1);
			boolean isDaXie = expressText.indexOf(DAXIE) != -1;
			// 做过操作以后的值
			String express = expressText;
			if (isDaXie)
				express = expressText.replace(DAXIE, "");

			try {
				expressValue = translateToValue(express, record);
				if (i == 0) {
					firstExpressValue = expressValue;
				}
				i++;
				if (isDaXie) {
					matcher.appendReplacement(resultBuffer, TypeChange.moneyFormatToUpper(expressValue));
				} else
					matcher.appendReplacement(resultBuffer, (valueToString(expressValue)));
			} catch (OgnlException e) {
				e.printStackTrace();
			}
		}
		matcher.appendTail(resultBuffer);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("value", resultBuffer.toString());
		result.put("firstvalue", firstExpressValue);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String genPrintHtml(String moduleName, String id, String schemeId,
			HttpServletRequest request) {

		Class<?> beanClass = ModuleServiceFunction.getModuleBeanClass(moduleName);
		Object record = systemBaseDAO.findById(beanClass, id);

		List<_PrintSchemeGroup> schemeGroups = (List<_PrintSchemeGroup>) systemBaseDAO
				.findByPropertyAllSort(_PrintSchemeGroup.class, "tf_printGroupOrder", "asc",
						"tf_PrintScheme.tf_printSchemeId", Integer.parseInt(schemeId), null, null);
		StringBuilder result = new StringBuilder();

		for (_PrintSchemeGroup group : schemeGroups) {

			List<_PrintSchemeGroupCell> schemeGroupCells = (List<_PrintSchemeGroupCell>) systemBaseDAO
					.findByPropertyAllSort(_PrintSchemeGroupCell.class, "tf_order", "asc",
							"tf_PrintSchemeGroup.tf_printSchemeGroupId", group.getTf_printSchemeGroupId(), null,
							null);
			// 去掉disabled 的
			for (int i = schemeGroupCells.size() - 1; i >= 0; i--)
				if (schemeGroupCells.get(i).getTf_disabled())
					schemeGroupCells.remove(i);

			result.append(group.genHtml(group, schemeGroupCells));

		}

		// 更改里面的值
		// .*? 加？表示非贪婪模式
		Pattern pattern = Pattern.compile("\\{.*?\\}");
		Matcher matcher = pattern.matcher(result);

		StringBuffer resultBuffer = new StringBuffer();
		while (matcher.find()) {
			// 花括号内的原字符串
			String expressText = result.substring(matcher.start() + 1, matcher.end() - 1);
			boolean isDaXie = expressText.indexOf(DAXIE) != -1;
			boolean isSignPhoto = expressText.indexOf(SIGNPHOTO) != -1;
			// 做过操作以后的值
			String express = expressText;
			if (isDaXie)
				express = expressText.replace(DAXIE, "");
			if (isSignPhoto)
				express = expressText.replace(SIGNPHOTO, "");
			try {
				Object expressValue = translateToValue(express, record);
				if (isDaXie) {
					matcher.appendReplacement(resultBuffer, TypeChange.moneyFormatToUpper(expressValue));
				} else if (isSignPhoto)
					matcher.appendReplacement(resultBuffer,
							userService.getSignPhotoSrc(expressValue == null ? "" : expressValue.toString()));
				else
					matcher.appendReplacement(resultBuffer, changeSpaceEnter(valueToString(expressValue)));
			} catch (OgnlException e) {
				e.printStackTrace();
			}
		}
		matcher.appendTail(resultBuffer);
		return resultBuffer.toString();
	}

	public static Object translateToValue(String express, Object record) throws OgnlException {
		Object result = null;

		try {
			result = ognlExpressLevel(express, record);
		} catch (Exception e) {
			try {
				result = ognlExpressLevel(express, SystemInfoService.getSystemset());
			} catch (Exception e1) {
				try {
					result = ognlExpressLevel(express, SystemInfoService.getSysteminfo());
				} catch (Exception e2) {
					try {
						result = ognlExpressLevel(express, SystemInfoService.getSystemsetAddition());
					} catch (Exception e3) {
						return express + "未解析";
					}
				}
			}
		}
		return result;
	}

	// 一级一级的解释，如果有一级为空，那么不要解释下一级了
	// 如 Department.tf_name,
	public static Object ognlExpressLevel(final String express, final Object record)
			throws OgnlException {
		String[] exps = express.split("\\.");
		String exp = "";
		for (int i = 0; i < exps.length; i++) {
			exp += (i == 0 ? "" : ".") + exps[i];
			Object value = Ognl.getValue(exp, record);
			if (value == null)
				return null;
		}
		return Ognl.getValue(express, record);
	}

	// 将空格的回车换成html 符号
	public String changeSpaceEnter(Object value) {
		if (value == null)
			return "";
		String result = value.toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>")
				.replaceAll(" ", "&nbsp;");
		return result;

	}

	public String valueToString(Object value) {
		if (value == null)
			return "";
		if (value instanceof Date) {
			return TypeChange.DateToString((Date) value);
		}
		if (value instanceof Double) {
			return TypeChange.DoubletoString((Double) value);
		}
		return value.toString();

	}

	public static final String DAXIE = "(大写)";
	public static final String SIGNPHOTO = "(签名)";

}
