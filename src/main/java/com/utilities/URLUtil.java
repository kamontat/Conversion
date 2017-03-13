package com.utilities;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Wed 08/Mar/2017 - 10:30 PM
 */
public class URLUtil {
	public enum Protocol {
		HTTP,
		HTTPS;
		
		public static Protocol getProtocol(URL url) {
			return url.getProtocol().contains(HTTPS.name().toLowerCase()) ? HTTPS: HTTP;
		}
		
		@Override
		public String toString() {
			return this.name().toLowerCase(Locale.ENGLISH);
		}
	}
	
	private Protocol protocol;
	private URL url;
	
	private URLUtil(Protocol protocol, String link) throws IOException {
		this.protocol = protocol;
		// add protocol
		link = (protocol == Protocol.HTTP ? "http://": "https://") + link;
		
		try {
			url = new URL(link.toLowerCase(Locale.ENGLISH));
		} catch (MalformedURLException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}
	}
	
	private URLUtil(URL url) {
		this.protocol = Protocol.getProtocol(url);
		this.url = url;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public Protocol getProtocol() {
		return protocol;
	}
	
	
	public static URLUtil getUrl(Protocol protocol, String link) throws IOException {
		return new URLUtil(protocol, link.toLowerCase(Locale.ENGLISH));
	}
	
	public static URLUtil getUrl(URL url) {
		return new URLUtil(url);
	}
	
	public HttpsURLConnection getHttpsConnection() throws IOException {
		return (HttpsURLConnection) url.openConnection();
	}
	
	
	public HttpURLConnection getHttpConnection() throws IOException {
		return (HttpURLConnection) url.openConnection();
	}
	
	public URLConnection getConnection() throws IOException {
		return url.openConnection();
	}
	
	public String getContent() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line).append("\n");
		}
		return builder.toString();
	}
	
	public int getURLSize() throws IOException {
		if (url == null) return 0;
		return getConnection().getContentLength();
	}
	
	public String getURLFilename() {
		String filename = url.getPath();
		int k = filename.lastIndexOf("/");
		if (k == filename.length() - 1) return "";
		if (k >= 0) filename = filename.substring(k + 1);
		return filename;
	}
	
	public void printHeader() throws IOException {
		Map header = getConnection().getHeaderFields();
		for (Object key : header.keySet()) {
			System.out.printf("%s: %s\n", key, header.get(key));
		}
	}
}
