package com.main;

import com.kamontat.Converter;
import com.utilities.FilesUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		File file = FilesUtil.getFile("src", "resource");
		
		List<File> htmlFiles = FilesUtil.getAllFiles(file.getAbsolutePath(), "html");
		
		String md = Converter.by(Converter.Type.HTML2MD).convert(htmlFiles.get(0)).toString();
		
		String h = Converter.by(Converter.Type.MD2HTML).convert(md).toString();
		
		String s = FilesUtil.readAll(file.getAbsolutePath(), null);
		System.out.println(s);
	}
}