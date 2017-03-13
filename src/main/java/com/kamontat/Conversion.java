package com.kamontat;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:35 PM
 */
public abstract class Conversion {
	public enum Type {
		MD2HTML,
		HTML2MD;
	}
	
	public static Conversion by(Type type) {
		if (type == Type.MD2HTML) {
			return new Md2Html();
		} else {
			return new Html2Md();
		}
	}
	
	public abstract Result convertString(String string) throws IOException;
	
	public abstract Result convertFile(File file) throws IOException;
	
	public abstract Result convertUrl(URL url) throws IOException;
}
