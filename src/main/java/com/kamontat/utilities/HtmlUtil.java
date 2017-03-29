package com.kamontat.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * html conversion by {@link org.jsoup.Jsoup} library
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 27/Mar/2017 - 11:49 PM
 */
public class HtmlUtil {
	public static Elements getHtmlInTag(String html, String tag) {
		return Jsoup.parse(html).child(0).getElementsByTag(tag);
	}
	
	public static String removeTag(String html) {
		return Jsoup.parse(html).child(0).html();
	}
	
	public static String removeTag(Elements html) {
		return html.html();
	}
	
	public static Element toHtmlByHtml(String html) {
		return addTag(html, "html");
	}
	
	public static Element toHtmlByPlain(String plainText) {
		return new Element("html").text(plainText);
	}
	
	public static Element coverTag(String html, String... tagNames) {
		List<String> tags = Arrays.asList(tagNames);
		Collections.reverse(tags);
		Element e = null;
		for (String s : tags) {
			if (e == null) e = addTag(html, s);
			else e = addTag(e, s);
		}
		return e;
	}
	
	public static Element coverTag(Element html, String... tagName) {
		return coverTag(html.toString(), tagName);
	}
	
	private static Element addTag(String s, String tagName) {
		return new Element(tagName).append(s);
	}
	
	private static Element addTag(Element s, String tagName) {
		return new Element(tagName).appendChild(s);
	}
}
