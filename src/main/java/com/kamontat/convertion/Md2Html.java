package com.kamontat.convertion;


import com.utilities.URLUtil;
import com.utilities.FilesUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Wed 08/Mar/2017 - 10:52 PM
 */
public class Md2Html extends Conversion {
	private static final String GITHUB_API = "api.github.com/";
	private static final String MARKDOWN_CONVERTER_API = "markdown/raw";
	
	Md2Html() {
	}
	
	private Result convert(String rawMarkdown) throws IOException {
		String link = GITHUB_API + MARKDOWN_CONVERTER_API;
		HttpsURLConnection connection = URLUtil.getUrl(URLUtil.Protocol.HTTPS, link).getHttpsConnection();
		
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "text/plain");
		connection.setRequestProperty("Content-Length", Integer.toString(rawMarkdown.length()));
		connection.setDoOutput(true);
		connection.getOutputStream().write(rawMarkdown.getBytes("UTF8"));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String s = B2S(reader);
		
		return new Result(s, reader);
	}
	
	/**
	 * convert buffer reader to string.
	 *
	 * @param r
	 * 		buffer reader
	 * @return string
	 */
	private String B2S(BufferedReader r) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = r.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException ignore) {
			sb.append("$(Error)");
		}
		
		return sb.toString();
	}
	
	@Override
	public Result convertString(String string) throws IOException {
		return convert(string);
	}
	
	@Override
	public Result convertFile(File file) throws IOException {
		if (file == null) throw new IOException("no file");
		
		return convert(FilesUtil.readAll(file));
	}
	
	@Override
	public Result convertUrl(URL url) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
		return convert(B2S(r));
	}
}
