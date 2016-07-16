package com.jfok.cfcmms.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.zip.Deflater;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jfok.cfcmms.DAO.SystemBaseDAO;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment_FileData;
import com.jfok.cfcmms.hibernate.system.attachment._Attachment_ImagePreview;
import com.jfok.cfcmms.hibernate.system.module._Module;
import com.jfok.cfcmms.service.AttachmentService;
import com.jfok.cfcmms.service.SystemAndLoginInfoService;
import com.jfok.cfcmms.share.FileTypeVSMimeType;
import com.jfok.cfcmms.util.CommonFunction;
import com.jfok.cfcmms.util.FileUploadBean;

/**
 * 附件下载和预览文件显示的Service
 * 
 * @author jiangfeng 2013.06.28
 * 
 */

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

	@Resource
	private SystemBaseDAO systemBaseDAO;

	@Resource
	private AttachmentService attachmentService;

	@RequestMapping("uploadnew.do")
	public @ResponseBody Object uploadnew(FileUploadBean uploaditem, BindingResult bindingResult,
		HttpServletRequest request) throws IOException {
	// 写入记录信息
	_Attachment attachment = attachmentService.uploadnew(uploaditem, bindingResult, request);
	MultipartFile file = uploaditem.getFile();
	// 如果有文件，再写入文件，及缩略图
	if (file != null && file.getOriginalFilename().length() > 0) {
		attachmentService.uploadnewFile(file, attachment.getTf_attachmentId());
	}
	return "{success : true}";
	}

	/**
	 * 重新上传一个附件，把原来的覆盖掉
	 * 
	 * @param uploaditem
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("uploadnewattachment.do")
	public @ResponseBody Object uploadnewattachment(FileUploadBean uploaditem,
		BindingResult bindingResult, HttpServletRequest request) throws IOException {
	MultipartFile file = uploaditem.getFile();
	// 如果有文件，再写入文件，及缩略图
	if (file != null && file.getOriginalFilename().length() > 0) {
		attachmentService.uploadnewFile(file, uploaditem.getTf_attachmentId());
	}
	return "{success : true}";
	}

	/**
	 * 下载预览的小图标，如果没有预览小图标，则下载文件图标
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/preview.do")
	public void getPreviewImage(String id, HttpServletRequest request, HttpServletResponse response)
		throws IOException {

	_Attachment_ImagePreview imagePreview = (_Attachment_ImagePreview) systemBaseDAO
		.findById(_Attachment_ImagePreview.class, id);
	// System.out.println("下载预览文件：" + imagePreview.getTf_filename());
	if (imagePreview == null || imagePreview.getTf_filename() == null)
		return;
	else if (imagePreview.getTf_imagePreview() != null) {
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "max-age=" + 600);
		try {
		response.addHeader("Content-Length", "" + imagePreview.getTf_imagePreview().length());
		} catch (SQLException e) {
		e.printStackTrace();
		}
		Blob blob = imagePreview.getTf_imagePreview();
		writeBlobToResponse(blob, response);
	} else {
		CommonFunction.downloadFilePreviewIcon(
			request.getSession().getServletContext().getRealPath("images/attachment/") + "/",
			imagePreview.getTf_filename(), response);
	}
	}

	/**
	 * 取得模块某条记录的第一个图片附件，用于在鼠标移到附件数字上以后可以快速显示
	 * 
	 * @param moduleName
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/firstimagepreview.do")
	public void getModuleRecordFirstImagePreview(String moduleId, String id,
		HttpServletRequest request, HttpServletResponse response) throws IOException {
	@SuppressWarnings("unchecked")
	List<_Attachment> attachments = (List<_Attachment>) systemBaseDAO
		.findByPropertyWithOtherCondition(_Attachment.class, "tf_moduleId", moduleId,
			"tf_moduleIdvalue=" + id);

	for (_Attachment attachment : attachments) {
		// 是否图片文件
		if (attachment.getTf_filesize() != null && attachment.getTf_filesize() > 0
			&& attachment.getTf_imgheight() != null && attachment.getTf_imgheight() > 0) {
		getPreviewImage(attachment.getTf_attachmentId().toString(), request, response);
		return;
		}
	}

	}

	/**
	 * 取得模块某条记录的第一个图片附件，用于在鼠标移到附件数字上以后可以快速显示
	 * 
	 * @param moduleName
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/firstimage.do")
	public void getModuleRecordFirstImage(String moduleId, String id, HttpServletRequest request,
		HttpServletResponse response) throws IOException {
	@SuppressWarnings("unchecked")
	List<_Attachment> attachments = (List<_Attachment>) systemBaseDAO
		.findByPropertyWithOtherCondition(_Attachment.class, "tf_moduleId", moduleId,
			"tf_moduleIdvalue=" + id);

	for (_Attachment attachment : attachments) {
		// 是否图片文件
		if (attachment.getTf_filesize() != null && attachment.getTf_filesize() > 0
			&& attachment.getTf_imgheight() != null && attachment.getTf_imgheight() > 0) {
		getAttachment(attachment.getTf_attachmentId().toString(), response);
		return;
		}
	}

	}

	/**
	 * 文件下载
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("download.do")
	public void download(String id, HttpServletResponse response) throws IOException {

	_Attachment_FileData fileData = (_Attachment_FileData) systemBaseDAO
		.findById(_Attachment_FileData.class, id);
	if (fileData.getTf_filedata() == null) {
		downloadfilenotfound(response);
		return;
	}
	response.addHeader("Content-Disposition", "attachment" + ";filename="
		+ new String(fileData.getTf_filename().getBytes("gb2312"), "iso8859-1"));
	try {
		response.addHeader("Content-Length", "" + fileData.getTf_filedata().length());
	} catch (SQLException e) {
		e.printStackTrace();
	}
	response.setContentType("application/octet-stream");
	Blob blob = fileData.getTf_filedata();
	writeBlobToResponse(blob, response);

	}

	/**
	 * 打包压缩下载模块记录的所有附件
	 * 
	 * @param moduleId
	 * @param id
	 * @param text
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("downloadall.do")
	public void downloadall(String moduleId, String id, String text, HttpServletResponse response)
		throws IOException {

	_Module module = SystemAndLoginInfoService.getModuleWithId(moduleId);
	@SuppressWarnings("unchecked")
	List<_Attachment_FileData> attachments = ((List<_Attachment_FileData>) systemBaseDAO
		.findByPropertyWithOtherCondition(_Attachment_FileData.class, _Attachment.MODULEID,
			moduleId, _Attachment.MODULEKEYID + "=" + id));
	OutputStream os = new ByteArrayOutputStream();
	InputStream input = null;
	ZipOutputStream zipOut = new ZipOutputStream(os);
	zipOut.setEncoding("GBK");
	zipOut.setLevel(Deflater.BEST_COMPRESSION);
	zipOut.setMethod(ZipOutputStream.DEFLATED);
	zipOut.setComment("这是模块" + module.getTf_title() + "中" + text + "的所有附件的压缩文件");
	for (_Attachment_FileData attachment : attachments) {
		if (attachment.getTf_filename() != null && attachment.getTf_filedata() != null) {
		try {
			input = attachment.getTf_filedata().getBinaryStream();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ZipEntry zipEntry = new ZipEntry(attachment.getTf_filename());
		zipEntry.setComment(attachment.getTf_name());
		zipOut.putNextEntry(zipEntry);
		int readed = 0;
		byte[] cash = new byte[2048];
		while ((readed = input.read(cash)) > 0)
			zipOut.write(cash, 0, readed);
		input.close();
		}
	}
	zipOut.close();
	CommonFunction.download(os, module.getTf_title() + "--" + text + "附件" + ".zip", response);
	return;
	}

	@RequestMapping("getattachment.do/{id}")
	public void getAttachmentwithid(@PathVariable String id, HttpServletResponse response)
		throws IOException {
	getAttachment(id, response);
	}

	/**
	 * 在网页里打开原始的上传文件
	 * 
	 * @param id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("getattachment.do")
	public void getAttachment(String id, HttpServletResponse response) throws IOException {

	// 下载上传的原始文件
	_Attachment_FileData fileData = (_Attachment_FileData) systemBaseDAO
		.findById(_Attachment_FileData.class, id);

	if (fileData.getTf_filedata() == null) {
		inlinefilenotfound(response);
		return;
	}
	response.setHeader("Cache-Control", "max-age=" + 600);
	response.addHeader("Content-Disposition", "inline");

	if (fileData.getTf_pdfdata() == null) {

		try {
		response.addHeader("Content-Length", "" + fileData.getTf_filedata().length());
		} catch (SQLException e) {
		e.printStackTrace();
		}
		String mimetype = FileTypeVSMimeType.getMimeType(fileData.getTf_filename());
		if (mimetype == null)
		response.setContentType("application/octet-stream;charset=gb2312");
		else
		response.setContentType(mimetype + ";charset=gb2312");
		Blob blob = fileData.getTf_filedata();
		writeBlobToResponse(blob, response);
	} else { // doc xls 的pdf 预览
		try {
		response.addHeader("Content-Length", "" + fileData.getTf_pdfdata().length());
		} catch (SQLException e) {
		e.printStackTrace();
		}
		String mimetype = FileTypeVSMimeType.getMimeType(".pdf");
		if (mimetype == null)
		response.setContentType("application/octet-stream;charset=gb2312");
		else
		response.setContentType(mimetype + ";charset=gb2312");
		Blob blob = fileData.getTf_pdfdata();
		writeBlobToResponse(blob, response);
	}

	}

	public void inlinefilenotfound(HttpServletResponse response) throws IOException {
	byte[] buffer = "下载文件失败:没有找到要预览的文件!".getBytes();
	long l = buffer.length;
	response.reset();
	response.addHeader("Content-Disposition", "inline");
	response.addHeader("Content-Length", "" + l);
	OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	response.setContentType("text/plain");
	toClient.write(buffer);
	toClient.flush();
	toClient.close();
	}

	public void downloadfilenotfound(HttpServletResponse response) throws IOException {

	byte[] buffer = "下载文件失败:没有找到要下载的文件!".getBytes();
	long l = buffer.length;
	String fn = "下载的文件未找到.txt";
	response.reset();
	response.addHeader("Content-Disposition",
		"attachment;filename=" + new String(fn.getBytes("gb2312"), "iso8859-1"));
	response.addHeader("Content-Length", "" + l);
	OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	response.setContentType("application/octet-stream");
	toClient.write(buffer);
	toClient.flush();
	toClient.close();
	}

	private void writeBlobToResponse(Blob blob, HttpServletResponse response) throws IOException {
	InputStream br = null;
	try {
		br = blob.getBinaryStream();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	OutputStream out = response.getOutputStream();
	try {
		byte[] buffer = new byte[1024 * 10];
		int len = 0;
		while ((len = br.read(buffer)) > 0) {
		out.write(buffer, 0, len);
		out.flush();
		}
	} catch (Exception e) {
	} finally {
		br.close();
		out.close();
	}
	}

}
