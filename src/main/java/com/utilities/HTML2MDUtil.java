package com.utilities;

import com.kamontat.convertion.Conversion;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 10:26 PM
 */
public class HTML2MDUtil {
	public static void htmlToJekyllMd(String htmlPath, String mdPath, String charset) {
		try {
			List<File> fileList = FilesUtil.getAllFiles(htmlPath, "html");
			for (File file : fileList) {
				String mdName = file.getAbsolutePath().replace(htmlPath, mdPath).replace("html", "md");
				String hmPath = mdName.substring(0, mdName.lastIndexOf("/")) + "/";
				String separator = System.getProperty("line.separator");
				String head = "---" + separator + "layout: post" + separator + "title: \"" + file.getName() + "\"" + separator + "description: \"" + file.getName() + "\"" + separator + "category: pages\"" + separator + "tags: [blog]\"" + separator + "--- " + separator + "{% include JB/setup %}" + separator + separator;
				FilesUtil.isExist(hmPath);
				String parsedText = Conversion.by(Conversion.Type.HTML2MD).convertFile(file).toString();
				Calendar calendar = Calendar.getInstance();
				String dateName = DateUtil.dateToShortString(calendar.getTime());
				String newName = dateName + "-" + hmPath.replace(mdPath, "").replace("/", "-") + "-" + file.getName();
				String mmName = (hmPath + newName.replace("html", "md")).replaceAll("\\s*", "");
				FilesUtil.newFile(mmName, head + parsedText, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void htmlToHexoMd(String htmlPath, String mdPath, String charset) {
		try {
			List<File> fileList = FilesUtil.getAllFiles(htmlPath, "html");
			for (File file : fileList) {
				String mdName = file.getAbsolutePath().replace(htmlPath, mdPath).replace("html", "md");
				String hmPath = mdName.substring(0, mdName.lastIndexOf("/")) + "/";
				String separator = System.getProperty("line.separator");
				String[] strings = hmPath.replace(mdPath, "").split("/");
				Calendar calendar = Calendar.getInstance();
				String dateName = DateUtil.dateToShortString(calendar.getTime());
				String dateString = DateUtil.dateToLongString(calendar.getTime());
				StringBuilder blog = new StringBuilder();
				StringBuilder categories = new StringBuilder();
				Map<String, String> stringMap = new TreeMap<String, String>();
				for (String value : strings) {
					stringMap.put(value, value);
				}
				for (String tag : stringMap.keySet()) {
					blog.append(" - ").append(tag).append(separator);
				}
				categories.append(strings[0]);
				String head = "---" + separator + "layout: post" + separator + "title: \"" + file.getName().replace(".html", "").split("-")[0] + "\"" + separator + "date: " + dateString + separator + "categories: " + categories + separator + "tags: " + separator + blog.toString() + "--- " + separator + separator;
				FilesUtil.isExist(hmPath);
				String parsedText = Conversion.by(Conversion.Type.HTML2MD).convertFile(file).toString();
				String newName = dateName + "-" + hmPath.replace(mdPath, "").replace("/", "-") + "-" + file.getName();
				String mmName = (hmPath + newName.replace("html", "md")).replaceAll("\\s*", "");
				FilesUtil.newFile(mmName, head + parsedText, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
