package com.kamontat.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * html conversion by {@link org.jsoup.Jsoup} library <br>
 * All method is <b>static</b> method
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 27/Mar/2017 - 11:49 PM
 */
public class HtmlUtil {
	private static Document.OutputSettings setting;
	
	/**
	 * setting new output.
	 *
	 * @param setting
	 * 		{@link Document.OutputSettings}
	 * @see Document.OutputSettings
	 */
	public static void setSetting(Document.OutputSettings setting) {
		HtmlUtil.setting = setting;
	}
	
	/**
	 * get html tag include tag too <br>
	 * <pre>{@code
	 * input (html): <div><code>Hello world</code>!</div>
	 * input (tag): code
	 *
	 * output: <code>Hello world</code>
	 *     }</pre>
	 * so you can remove the input tag by using {@link #removeTag(String)}
	 *
	 * @param html
	 * 		searching html
	 * @param tag
	 * 		tagName (learn more {@link Tag#valueOf(String)})
	 * @return {@link Elements} (which contains of html string inside tag parameter)
	 * @see HtmlUtil#removeTag(String)
	 */
	public static Elements getHtmlInTag(String html, String tag) {
		return Jsoup.parse(html).child(0).getElementsByTag(tag);
	}
	
	/**
	 * remove top tag and return as string <br>
	 * <pre>{@code
	 *  input: <div>I am <code>Java</code> programmer</div>
	 *  output: I am <code>Java</code> programmer
	 * }</pre>
	 *
	 * @param html
	 * 		input html
	 * @return string that removed top tag
	 */
	public static String removeTag(String html) {
		return Jsoup.parse(html).child(0).html();
	}
	
	/**
	 * remove top tag and return as string
	 *
	 * @param html
	 * 		input Elements (easy get from {@link #getHtmlInTag(String, String)})
	 * @return string that removed top tag
	 * @see #removeTag(String)
	 */
	public static String removeTag(Elements html) {
		return html.html();
	}
	
	/**
	 * insert html tag in the top <br>
	 * <b>Beware</b>: It's include head and body tag too
	 * <pre>{@code
	 * input: <div>I am <code>Java</code> programmer</div>
	 * output:
	 * <html>
	 *      <head></head>
	 *      <body>
	 *          <div>I am <code>Java</code> programmer</div>
	 *      </body>
	 * </html>
	 * }</pre>
	 *
	 * @param html
	 * 		input html
	 * @return input with html, body and head tag
	 */
	public static Element toHtmlByHtml(String html) {
		return new Element("html").append(html);
	}
	
	/**
	 * same work with {@link #toHtmlByHtml(String)} <b>but</b> think input parameter as plain text (so meaning if there have charactor that cannot convert to html it's will change to other) <br>
	 * Example:
	 * <pre>{@code
	 * input: <div>I am <code>Java</code> programmer</div>
	 * output:
	 * <html>
	 *      <head></head>
	 *      <body>
	 *          &lt;div&gt;I am &lt;code&gt;Java&lt;/code&gt; programmer&lt;/div&gt;
	 *      </body>
	 * </html>
	 *  }</pre>
	 *
	 * @param plainText
	 * 		plain text
	 * @return input with html, body and head tag
	 */
	public static Element toHtmlByPlain(String plainText) {
		Element html = new Element("html");
		Element head = new Element("head");
		Element body = new Element("body").text(plainText);
		return html.prependChild(body).prependChild(head);
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
		return parse(s).body().tagName(tagName);
	}
	
	private static Element addTag(Element e, String tagName) {
		return parse(e.toString()).body().tagName(tagName);
	}
	
	public static Document parse(String html) {
		Document document = Jsoup.parse(html);
		if (setting != null) return document.outputSettings(setting);
		return document;
	}
	
	public static void main(String[] args) {
		addTag("asdf", "html");
	}
}
