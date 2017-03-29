package com.kamontat.example;

import com.kamontat.utilities.HtmlUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 28/Mar/2017 - 1:33 AM
 */
public class JsoupUsage {
	public static void main(String[] args) {
		String html = "<p>Error <code>Exception...</code> will appear when you <strong>don'Tag</strong> install <em>JRE</em></p>";
		
		// add html tag (include body and head too)
		Element eFullHtml = HtmlUtil.toHtmlByHtml(html);
		// same
		// Element eFullHtml = HtmlUtil.coverTag(html, "html");
		
		// thing parameter as plain text (so it will convert input to text that can add to html)
		Element eFullPlain = HtmlUtil.toHtmlByPlain(html);
		
		// add multiple tag
		Element eBody = HtmlUtil.coverTag(html, "body", "div");
		
		Elements eCode = HtmlUtil.getHtmlInTag(html, "code");
		String sCode = HtmlUtil.removeTag(eCode);
		
		// output
		System.out.println("-------------------------------");
		System.out.println(eFullHtml);
		/*
		 <html>
		  <head></head>
		  <body>
		   <p>Error <code>Exception...</code> will appear when you <strong>don'Tag</strong> install <em>JRE</em></p>
		  </body>
		 </html>
		 */
		
		System.out.println("-------------------------------");
		System.out.println(eFullPlain);
		/*
		  <html>
		   &lt;p&gt;Error &lt;code&gt;Exception...&lt;/code&gt; will appear when you &lt;strong&gt;don'Tag&lt;/strong&gt; install &lt;em&gt;JRE&lt;/em&gt;&lt;/p&gt;
		  </html>
		 */
		
		System.out.println("-------------------------------");
		System.out.println(eBody);
		/*
		  <body>
		   <div>
		    <p>Error <code>Exception...</code> will appear when you <strong>don'Tag</strong> install <em>JRE</em></p>
		   </div>
		  </body>
		 */
		
		System.out.println("-------------------------------");
		System.out.println(eCode);
		/*
		  <code>Exception...</code>
		 */
		
		System.out.println("-------------------------------");
		System.out.println(sCode);
		/*
		  Exception...
		 */
		
		System.out.println("-------------------------------");
		
		// more than is, you must learn by yourself
	}
}
