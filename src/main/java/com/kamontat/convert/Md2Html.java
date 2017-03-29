package com.kamontat.convert;


import com.kamontat.exception.ConversionException;
import com.kamontat.constance.ContentType;
import com.kamontat.constance.Headers;
import com.kamontat.constance.Protocol;
import com.kamontat.constance.RequestMethod;
import com.kamontat.utilities.FilesUtil;
import com.kamontat.utilities.RequestProp;
import com.kamontat.utilities.URLManager;
import com.kamontat.utilities.HtmlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.kamontat.constance.ContentType.PLAIN;
import static com.kamontat.constance.Type.TEXT;

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
	
	/**
	 * This will convert markdown syntax to html syntax. <br>
	 * {@inheritDoc}
	 *
	 * @param string
	 * 		{@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws ConversionException
	 * 		{@inheritDoc}
	 * @implNote This method will convert to fully html string (include <b>html</b>, <b>body</b> and <b>head</b> tag), your can remove them by {@link HtmlUtil} (the html utilities by {@link Jsoup})
	 * @see HtmlUtil
	 */
	@Override
	public Result convert(String string) throws ConversionException {
		String link = GITHUB_API + MARKDOWN_CONVERTER_API;
		
		RequestMethod m = RequestMethod.POST; // post method
		RequestProp p1 = new RequestProp(Headers.Content_Type, ContentType.get(TEXT, PLAIN)); // with plain text type
		RequestProp p2 = new RequestProp(Headers.Content_Length, String.valueOf(string.length())); // length of string
		
		InputStream s = URLManager.getUrl(Protocol.HTTPS, link).getSpecifyInputFromConnection(m, string, p1, p2);
		try {
			Document d = Jsoup.parse(s, FilesUtil.DEFAULT_ENCODING, "");
			return new Result(d.toString(), s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Result.toResult(s);
	}
	
	/**
	 * convert file to string and using {@link #convert(String)}
	 *
	 * @param file
	 * 		{@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws ConversionException
	 * 		{@inheritDoc}
	 * @see HtmlUtil
	 */
	@Override
	public Result convert(File file) throws ConversionException {
		if (file == null) throw new ConversionException("File not found");
		String s = FilesUtil.getExtension(file.getName());
		if (s.contains("md")) return convert(FilesUtil.readAll(file));
		else throw new ConversionException("File extension unacceptable");
	}
	
	/**
	 * convert url to string and using {@link #convert(String)}
	 *
	 * @param url
	 * 		{@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws ConversionException {@inheritDoc}
	 * @see HtmlUtil
	 */
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
