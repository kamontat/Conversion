package com.kamontat;


import com.utilities.FilesUtil;
import com.utilities.URLUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.IOException;
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
	public Result convert(String string) throws IOException {
		String link = GITHUB_API + MARKDOWN_CONVERTER_API;
		HttpsURLConnection connection = URLUtil.getUrl(URLUtil.Protocol.HTTPS, link).getHttpsConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setRequestProperty("Content-Length", Integer.toString(string.length()));
		connection.setDoOutput(true);
		connection.getOutputStream().write(string.getBytes("UTF8"));
		
		return Result.toResult(connection.getInputStream());
	}
	
	@Override
	public Result convert(File file) throws IOException {
		if (file == null) throw new IOException("no file");
		return convert(FilesUtil.readAll(file));
	}
	
	@Override
	public Result convert(URL url) throws IOException {
		if (url == null) throw new IOException("no url link");
		String s = Result.toResult(url.openStream()).toString();
		return convert(s);
	}
}
