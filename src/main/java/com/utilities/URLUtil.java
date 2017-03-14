package com.utilities;

import com.annotation.DebugTools;

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
 * URL Utility
 *
 * @author kamontat
 * @version 1.0
 * @since Wed 08/Mar/2017 - 10:30 PM
 */
public class URLUtil {
	/**
	 * protocol for website link
	 */
	public enum Protocol {
		HTTP,
		HTTPS;
		
		/**
		 * get protocol from link
		 *
		 * @param url
		 * 		link
		 * @return protocol
		 */
		public static Protocol getProtocol(URL url) {
			return url.getProtocol().contains(HTTPS.name().toLowerCase()) ? HTTPS: HTTP;
		}
		
		@Override
		public String toString() {
			return this.name().toLowerCase(Locale.ENGLISH);
		}
	}
	
	/**
	 * web protocol
	 */
	private Protocol protocol;
	/**
	 * web url/link
	 */
	private URL url;
	
	/**
	 * get url utility
	 *
	 * @param protocol
	 * 		web protocol
	 * @param link
	 * 		web link (without <b>http://</b> or <b>https://</b>)
	 * @return url utility
	 * @throws IOException
	 * 		Protocol Error, I/O error occurred
	 * @see URLUtil
	 */
	public static URLUtil getUrl(Protocol protocol, String link) throws IOException {
		return new URLUtil(protocol, link);
	}
	
	/**
	 * get url utility
	 *
	 * @param url
	 * 		the url link
	 * @return url utility
	 * @see URLUtil
	 */
	public static URLUtil getUrl(URL url) {
		return new URLUtil(url);
	}
	
	/**
	 * get url utility
	 *
	 * @param link
	 * 		the link
	 * @return url utility
	 * @throws IOException
	 * 		Protocol Error, I/O error occurred
	 * @see URLUtil
	 */
	public static URLUtil getUrl(String link) throws IOException {
		try {
			return new URLUtil(new URL(link));
		} catch (MalformedURLException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Constructor Private
	 *
	 * @param protocol
	 * 		web protocol
	 * @param link
	 * 		web link
	 * @throws IOException
	 * 		protocol error, I/O error occurred
	 */
	private URLUtil(Protocol protocol, String link) throws IOException {
		this.protocol = protocol;
		// add protocol
		link = (protocol == Protocol.HTTP ? "http://": "https://") + link;
		
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Constructor Private
	 *
	 * @param url
	 * 		web url
	 */
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
	
	/**
	 * get https connection (For <b>HTTPS</b> protocol ONLY)
	 *
	 * @return {@link HttpsURLConnection}
	 * @throws IOException
	 * 		isn't https protocol, I/O error occurred
	 */
	public HttpsURLConnection getHttpsConnection() throws IOException {
		try {
			return (HttpsURLConnection) url.openConnection();
		} catch (ClassCastException e) {
			throw new IOException("Protocol isn't HTTPS");
		}
	}
	
	/**
	 * get http connection (For <b>HTTP</b> protocol ONLY)
	 *
	 * @return {@link HttpURLConnection}
	 * @throws IOException
	 * 		isn't http protocol, I/O error occurred
	 */
	public HttpURLConnection getHttpConnection() throws IOException {
		try {
			return (HttpURLConnection) url.openConnection();
		} catch (ClassCastException e) {
			throw new IOException("Protocol isn't HTTP");
		}
	}
	
	/**
	 * get connection
	 *
	 * @return url connection
	 * @throws IOException
	 * 		I/O error occurred
	 * @see URL#openConnection()
	 */
	public URLConnection getConnection() throws IOException {
		return url.openConnection();
	}
	
	/**
	 * get all content inside url
	 *
	 * @return content
	 * @throws IOException
	 * 		because of {@link #getConnection()}
	 */
	public String getContent() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getConnection().getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line).append("\n");
		}
		return builder.toString();
	}
	
	/**
	 * get content length/size
	 *
	 * @return size of content
	 * @throws IOException
	 * 		because of {@link #getConnection()}
	 */
	public int getURLSize() throws IOException {
		if (url == null) return 0;
		return getConnection().getContentLength();
	}
	
	/**
	 * get file name <br>
	 * Example: https://xyz.com/re/as/fe/test.txt <br>
	 * the return will be "test.txt"
	 *
	 * @return file name
	 */
	public String getURLFilename() {
		String filename = url.getPath();
		int k = filename.lastIndexOf("/");
		if (k == filename.length() - 1) return "";
		if (k >= 0) filename = filename.substring(k + 1);
		return filename;
	}
	
	/**
	 * print all header to (DEBUG TOOL)
	 *
	 * @throws IOException
	 * 		cannot get header
	 */
	@DebugTools
	public void printHeader() throws IOException {
		Map header = getConnection().getHeaderFields();
		for (Object key : header.keySet()) {
			System.out.printf("%s: %s\n", key, header.get(key));
		}
	}
}
