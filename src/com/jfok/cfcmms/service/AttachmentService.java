package com.jfok.cfcmms.service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.LobHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment_FileData;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment_ImagePreview;
import com.jfok.cfcmms.hibernate.system.attachment._AttachmentReduceMode;
import com.jfok.cfcmms.util.FileUploadBean;
import com.jfok.cfcmms.login.SessionManage;
import com.jfok.cfcmms.login.UserSession;
import com.jfok.cfcmms.share.FileTypeVSMimeType;

/**
 * 附件下载和预览文件显示的Service
 * 
 * @author jiangfeng 2013.06.28
 * 
 */

@Service
public class AttachmentService {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public _Attachment uploadnew(FileUploadBean uploaditem, BindingResult bindingResult,
		HttpServletRequest request) throws IOException {
	Map<String, Object> result = new HashMap<String, Object>();

	if (bindingResult.hasErrors()) {
		for (ObjectError error : bindingResult.getAllErrors()) {
		// 有二个错误信息，是上传日期和录入日期，传进来的是字符串，不能自动转成 Date
		// System.err.println("Error: " + error.getCode() + " - " +
		// error.getDefaultMessage());
		result.put(error.getCode(), error.getDefaultMessage());
		}
	}
	// 下划线开头的字段不能自动注入，日期也注入不对，以后再处理了，现在这里可以正确执行了
	uploaditem.set_t9502___tf_typeId(request.getParameter("_t9502___tf_typeId"));
	uploaditem
		.set_t9503___tf_fileTypeId(request.getParameter("_t9503___tf_fileTypeId"));
	uploaditem.set_t9506___tf_fieldId(request.getParameter("_t9506___tf_fieldId"));

	uploaditem.set_t9504___tf_reduceModeId(request.getParameter("_t9504___tf_reduceModeId"));
	UserSession userSession = SessionManage.getInstance().getUserSession(request.getSession());
	_Attachment Attachment = new _Attachment();
	MultipartFile file = uploaditem.getFile();
	if (file != null && file.getOriginalFilename().length() > 0) {
		Attachment.setTf_AttachmentFileType(uploaditem.getTf_AttachmentFileType());
		Attachment.setTf_AttachmentReduceMode(uploaditem.getTf_AttachmentReduceMode());
	}
	Attachment.setTf_AttachmentType(uploaditem.getTf_AttachmentType());
	Attachment.setTf_AttachmentOnField(uploaditem.getTf_AttachmentOnField());
	Attachment.setTf_moduleId(uploaditem.getTf_moduleId());
	Attachment.setTf_moduleIdvalue(uploaditem.getTf_moduleIdvalue());
	Attachment.setTf_name(uploaditem.getTf_name());
	Attachment.setTf_order(uploaditem.getTf_order());
	Attachment.setTf_remark(uploaditem.getTf_remark());
	Attachment.setTf_inputmen(userSession.getUserName());
	Attachment.setTf_inputdate(new Date());
	systemBaseDAO.save(Attachment);
	return Attachment;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object uploadnewFile(MultipartFile file, Integer id) throws IOException {

	_Attachment Attachment = (_Attachment) systemBaseDAO.findById(_Attachment.class, id);
	Attachment.setTf_filename(file.getOriginalFilename());
	Attachment.setTf_filesize((int) file.getSize());
	Attachment.setTf_filelastupdate(new Date());
	Attachment.setTf_imgheight(null);
	Attachment.setTf_imgwidth(null);

	_Attachment_FileData Attachment_FileData = (_Attachment_FileData) systemBaseDAO
		.findById(_Attachment_FileData.class, Attachment.getTf_attachmentId());
	_Attachment_ImagePreview Attachment_ImagePreview = (_Attachment_ImagePreview) systemBaseDAO
		.findById(_Attachment_ImagePreview.class, Attachment.getTf_attachmentId());

	// 取得压缩后的缩略图的stream
	ByteArrayOutputStream compressOK = null;
	Image image = null;
	if (FileTypeVSMimeType.isImageFile(Attachment.getTf_filename())) {
		image = ImageIO.read(file.getInputStream());
		compressOK = CompressImage(file.getInputStream(), Attachment, image);
	}
	// 如果是图形文件，写入缩略图
	LobHelper lobHelper = systemBaseDAO.getSessionFactory().getCurrentSession().getLobHelper();

	if (compressOK != null) {
		Attachment_ImagePreview.setTf_imagePreview(lobHelper.createBlob(compressOK.toByteArray()));
		// Hibernate.createBlob(new
		// ByteArrayInputStream(compressOK.toByteArray())));
		// 根据图形文件的压缩设置，保存原始图形
		if (saveImageWithReduceMode(Attachment, Attachment_FileData, file, image))
		;
		else
		// 如果没有压缩成功，还是保存原图
		// Attachment_FileData.setTf_filedata(Hibernate.createBlob(file.getInputStream()));  //hibernate 3
		Attachment_FileData.setTf_filedata(
			lobHelper.createBlob(file.getInputStream(), file.getInputStream().available()));
	} else {
		Attachment_ImagePreview.setTf_imagePreview(null);
		Attachment.setTf_AttachmentReduceMode(null);
		Attachment_FileData.setTf_filedata(
			lobHelper.createBlob(file.getInputStream(), file.getInputStream().available()));
	}

	// 如果是doc ,docx 文件，那么生成一个 pdf 的放在tf_pdfdata ,预览的时候调用pdf
	String fileext = null;
	String fn = Attachment.getTf_filename().toLowerCase();
	if (fn.endsWith(".doc"))
		fileext = "doc";
	if (fn.endsWith(".docx"))
		fileext = "docx";
	if (fn.endsWith(".xls"))
		fileext = "xls";
	if (fn.endsWith(".xlsx"))
		fileext = "xlsx";
	if (fn.endsWith(".ppt"))
		fileext = "ppt";
	if (fn.endsWith(".pptx"))
		fileext = "pptx";

	ByteArrayOutputStream pdfos = new ByteArrayOutputStream();
	Attachment_FileData.setTf_pdfdata(null);
	if (fileext != null) {
		try {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
		connection.connect();

		DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
		DocumentFormat pdfFormat = formatReg.getFormatByFileExtension("pdf");

		DocumentFormat docFormat = formatReg.getFormatByFileExtension(fileext);

		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		// 输入流，转换的类型，输出流，输出类型
		converter.convert(file.getInputStream(), docFormat, pdfos, pdfFormat);
		connection.disconnect();

		Blob blob = lobHelper.createBlob(pdfos.toByteArray());

		Attachment_FileData.setTf_pdfdata(blob);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	systemBaseDAO.attachDirty(Attachment_FileData, null);

	systemBaseDAO.attachDirty(Attachment, null);
	systemBaseDAO.attachDirty(Attachment_ImagePreview, null);

	return "{success:true}";
	}

	private boolean saveImageWithReduceMode(_Attachment Attachment, _Attachment_FileData fileData,
		MultipartFile file, Image image) throws IOException {
	_AttachmentReduceMode mode = Attachment.getTf_AttachmentReduceMode();
	if (mode != null) {
		if (mode.getTf_maxValue() != null && mode.getTf_maxValue() > 1) {
		// 对图像的宽高比，按照最大的进行
		return CompressImageWithMaxValue(Attachment, fileData, mode.getTf_maxValue(), image);
		} else if (mode.getTf_recudeTo() != null && mode.getTf_recudeTo() > 1) {
		return CompressImageWithReduce(Attachment, fileData, mode.getTf_recudeTo(), image);
		}
	}
	return false;
	}

	private boolean CompressImageWithMaxValue(_Attachment Attachment, _Attachment_FileData fileData,
		Integer maxValue, Image image) {
	try {

		int width = image.getWidth(null);
		int height = image.getHeight(null);
		if (width > maxValue || height > maxValue) {
		if (width > height) {
			height = new Double(1.00 * height * maxValue / width).intValue();
			width = maxValue;
		} else {
			width = new Double(1.00 * width * maxValue / height).intValue();
			height = maxValue;
		}
		saveToDbf(image, Attachment, fileData, width, height);
		return true;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}

	private boolean CompressImageWithReduce(_Attachment Attachment, _Attachment_FileData fileData,
		Integer recudeTo, Image image) {
	try {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		if (width < 800 && height < 800) // 二个都小于800，不要压缩了
		return false;
		double r = Math.sqrt(recudeTo);
		width = new Double(1.00 * width / r).intValue();
		height = new Double(1.00 * height / r).intValue();
		saveToDbf(image, Attachment, fileData, width, height);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}

	public void saveToDbf(Image image, _Attachment Attachment, _Attachment_FileData fileData, int width,
		int height) throws IOException {
	Image simage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g2d = bufferedImage.getGraphics();
	g2d.drawImage(simage, 0, 0, null);
	g2d.dispose();
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	ImageIO.write(bufferedImage, "JPEG", os);

	LobHelper helper = systemBaseDAO.getSessionFactory().getCurrentSession().getLobHelper();
	InputStream is = new ByteArrayInputStream(os.toByteArray());
	Blob blob = helper.createBlob(is, is.available());

	fileData.setTf_filedata(blob);
	Attachment.setTf_imgwidth(width);
	Attachment.setTf_imgheight(height);
	Attachment.setTf_filesize(os.size());
	}

	private int MAXXY = 128;

	public ByteArrayOutputStream CompressImage(InputStream is, _Attachment Attachment, Image image) {
	ByteArrayOutputStream os = new ByteArrayOutputStream();
	try {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		Attachment.setTf_imgwidth(width);
		Attachment.setTf_imgheight(height);
		int c_w = MAXXY;
		int c_h = MAXXY * height / width;
		if (height > width) {
		c_h = MAXXY;
		c_w = MAXXY * width / height;
		}
		BufferedImage bufferedImage = new BufferedImage(MAXXY, MAXXY, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(MAXXY, MAXXY,
			Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = bufferedImage.createGraphics();
		g2d.drawImage(image, (MAXXY - c_w) / 2, (MAXXY - c_h) / 2, c_w, c_h, null);
		g2d.setColor(Color.GREEN);
		g2d.drawRect(0, 0, MAXXY - 1, MAXXY - 1);
		g2d.dispose();
		ImageIO.write(bufferedImage, "png", os);

	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	return os;
	}

}
