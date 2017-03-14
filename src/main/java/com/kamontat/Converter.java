package com.kamontat;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:35 PM
 */
public abstract class Converter {
	public enum Type {
		MD2HTML,
		HTML2MD;
	}
	
	public static Converter by(Type type) {
		if (type == Type.MD2HTML) {
			return new Md2Html();
		} else {
			return new Html2Md();
		}
	}
	
	public <T extends Converter> T to(Class<T> tClass) {
		return tClass.cast(this);
	}
	
	public abstract Result convert(String string) throws IOException;
	
	public abstract Result convert(File file) throws IOException;
	
	public abstract Result convert(URL url) throws IOException;
}