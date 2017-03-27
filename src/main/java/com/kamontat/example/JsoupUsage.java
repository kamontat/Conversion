package com.kamontat.example;

import com.kamontat.utilities.HtmlUtil;
import org.jsoup.nodes.Element;

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 28/Mar/2017 - 1:33 AM
 */
public class JsoupUsage {
	public static void main(String[] args) {
		String html = "<p>Error <code>Exception...</code> will appear when you <strong>don'Tag</strong> install <em>JRE</em></p>";
		Element e1 = HtmlUtil.toHtmlByHtml(html);
		Element e2 = HtmlUtil.toHtmlByPlain(html);
		Element e3 = HtmlUtil.coverTag(html, "html");
		Element e4 = HtmlUtil.coverTag(e1, "body");
		
		System.out.println("-------------------------------");
		System.out.println(e1);
		System.out.println("-------------------------------");
		System.out.println(e2);
		System.out.println("-------------------------------");
		System.out.println(e3);
		System.out.println("-------------------------------");
		System.out.println(e4);
		System.out.println("-------------------------------");
	}
}
