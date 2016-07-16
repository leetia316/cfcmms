package com.jfok.cfcmms.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Office2PDF {

	/**
	 * 将office 文件转换成pdf文件
	 * @param fileext   office文件后缀名 xls , doc , ppt ,xlsx , docx , pptx 
	 * @param input     输入流
	 * @return
	 */
	public static OutputStream office2PDF(String fileext, InputStream input) {
		ByteArrayOutputStream pdfos = new ByteArrayOutputStream();
		OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
		try {
			connection.connect();
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
		DocumentFormat pdfFormat = formatReg.getFormatByFileExtension("pdf");
		DocumentFormat docFormat = formatReg.getFormatByFileExtension(fileext);
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(input, docFormat, pdfos, pdfFormat);
		connection.disconnect();
		return pdfos;
	}
}
