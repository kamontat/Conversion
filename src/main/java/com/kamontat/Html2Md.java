package com.kamontat;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;
import com.utilities.FilesUtil;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 11:15 PM
 */
public class Html2Md extends Converter {
	/**
	 * for advanced user.
	 */
	public Remark remark;
	
	Html2Md() {
		remark = new Remark();
	}
	
	public Html2Md(Options option) {
		remark = new Remark(option);
	}
	
	@Override
	public Result convert(String string) throws IOException {
		return Result.toResult(remark.convert(string));
	}
	
	@Override
	public Result convert(File file) throws IOException {
		String s = FilesUtil.getExtension(file.getName());
		if (s.contains("html")) return convert(FilesUtil.readAll(file));
		return Result.toResult(remark.convert(file));
	}
	
	@Override
	public Result convert(URL url) throws IOException {
		Connection connection = HttpConnection.connect(url);
		Document doc = connection.get();
		return Result.toResult(remark.convert(doc));
	}
}
