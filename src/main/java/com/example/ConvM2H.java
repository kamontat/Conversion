package com.example;

import com.kamontat.Converter;
import com.kamontat.Result;
import com.utilities.FilesUtil;
import com.utilities.URLUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Tue 14/Mar/2017 - 12:30 PM
 */
public class ConvM2H {
	private static Converter c = Converter.by(Converter.Type.MD2HTML);
	
	public static void main(String[] args) throws IOException {
		// file
		File f = FilesUtil.getFile("src", "resource", "test_file.md");
		// url
		URL url = URLUtil.getUrl(URLUtil.Protocol.HTTPS, "raw.githubusercontent.com/kamontat/CheckIDNumber/master/Readme.md").getUrl();
		// string
		String md = "Error `Exception...` will appear when you **don't** install *JRE*";
		
		String s = c.convert(f).toString();
		InputStream stream = c.convert(url).toInputStream();
		Result r = c.convert(md);
	}
}
