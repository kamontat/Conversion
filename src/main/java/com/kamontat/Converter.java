package com.kamontat;

import com.exception.ConversionException;

import java.io.File;
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
	
	public abstract Result convert(String string) throws ConversionException;
	
	public abstract Result convert(File file) throws ConversionException;
	
	public abstract Result convert(URL url) throws ConversionException;
}