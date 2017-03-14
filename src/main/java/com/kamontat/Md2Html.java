package com.kamontat;


import com.exception.ConversionException;
import com.utilities.FilesUtil;
import com.utilities.URLUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Wed 08/Mar/2017 - 10:52 PM
 */
public class Md2Html extends Converter {
	private static final String GITHUB_API = "api.github.com/";
	private static final String MARKDOWN_CONVERTER_API = "markdown/raw";
	
	Md2Html() {
	}
	
	@Override
	public Result convert(String string) throws ConversionException {
		String link = GITHUB_API + MARKDOWN_CONVERTER_API;
		HttpsURLConnection connection = null;
		try {
			connection = URLUtil.getUrl(URLUtil.Protocol.HTTPS, link).getHttpsConnection();
		} catch (IOException e) {
			throw new ConversionException("Protocol not found", e);
		}
		
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			throw new ConversionException("Protocol error", e);
		}
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setRequestProperty("Content-Length", Integer.toString(string.length()));
		connection.setDoOutput(true);
		try {
			connection.getOutputStream().write(string.getBytes(FilesUtil.DEFAULT_ENCODING));
		} catch (IOException e) {
			throw new ConversionException("I/O occurred when getting output stream", e);
		}
		
		try {
			return Result.toResult(connection.getInputStream());
		} catch (IOException e) {
			throw new ConversionException("I/O occurred when getting input stream", e);
		}
	}
	
	@Override
	public Result convert(File file) throws ConversionException {
		if (file == null) throw new ConversionException("File not found");
		String s = FilesUtil.getExtension(file.getName());
		if (s.contains("md")) return convert(FilesUtil.readAll(file));
		else throw new ConversionException("File extension unacceptable");
	}
	
	@Override
	public Result convert(URL url) throws ConversionException {
		if (url == null) throw new ConversionException("URL link not found");
		try {
			String s = Result.toResult(url.openStream()).toString();
			return convert(s);
		} catch (IOException e) {
			throw new ConversionException("I/O occurred when getting input stream");
		}
	}
}
