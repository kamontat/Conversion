package com.kamontat.example;

import com.kamontat.convert.Converter;
import com.kamontat.convert.Html2Md;
import com.kamontat.convert.Result;
import com.kamontat.constance.Protocol;
import com.kamontat.utilities.FilesUtil;
import com.kamontat.utilities.URLManager;
import com.overzealous.remark.Remark;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * HTML to Markdown
 *
 * @author kamontat
 * @version 1.0
 * @since Tue 14/Mar/2017 - 12:35 PM
 */
public class ConvH2M {
	private static Converter c = Converter.by(Converter.Type.HTML2MD);
	
	public static void main(String[] args) throws IOException {
		// file
		File f = FilesUtil.getFileFromRoot("src", "resource", "test_file.html");
		// url
		URL url = URLManager.getUrl(Protocol.HTTPS, "www.youtube.com").getUrl();
		// string
		String md = "<p><h1>Hello world</h1> <i>this<i> is my <b>name<b>. What <code>Code<code>?</p>";
		
		String s = c.convert(f).toString();
		InputStream stream = c.convert(url).toInputStream();
		Result r = c.convert(md);
		
		/* ------------------- Advance User ------------------- */
		Remark remark = c.to(Html2Md.class).remark;
		// remark.<METHOD>
		// learn more: http://remark.overzealous.com/javadoc/index.html
	}
}
