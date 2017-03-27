package com.kamontat.utilities;

import org.jsoup.nodes.Element;

/**
 * html conversion by {@link org.jsoup.Jsoup} library
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 27/Mar/2017 - 11:49 PM
 */
public class HtmlUtil {
	public static Element toHtmlByHtml(String html) {
		return new Element("html").append(html);
	}
	
	public static Element toHtmlByPlain(String plainText) {
		return new Element("html").text(plainText);
	}
	
	public static Element coverTag(String html, String tagName) {
		return new Element(tagName).append(html);
	}
	
	public static Element coverTag(Element html, String tagName) {
		return new Element(tagName).append(html.toString());
	}
}
