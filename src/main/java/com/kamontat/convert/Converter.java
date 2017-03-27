package com.kamontat.convert;

import com.kamontat.exception.ConversionException;

import java.io.File;
import java.net.URL;

/**
 * Converter class to convert between <b>html</b> and <b>markdown</b> <br>
 * create by {@link Converter#by(Type)}
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:35 PM
 */
public abstract class Converter {
	public enum Type {
		/**
		 * <b>md</b> to <b>html</b>
		 */
		MD2HTML,
		/**
		 * <b>html</b> to <b>md</b>
		 */
		HTML2MD;
	}
	
	/**
	 * get converter by {@link Type}
	 *
	 * @param type
	 * 		convert type
	 * @return Converter belong by that type
	 */
	public static Converter by(Type type) {
		if (type == Type.MD2HTML) {
			return new Md2Html();
		} else {
			return new Html2Md();
		}
	}
	
	/**
	 * change Converter to Subclass of it's <br>
	 * Use by <code>converter.to(Converter.class);</code>
	 *
	 * @param tClass
	 * 		Subclass of Converter
	 * @param <T>
	 * 		return class
	 * @return object that class <b>T</b>
	 */
	public <T extends Converter> T to(Class<T> tClass) {
		return tClass.cast(this);
	}
	
	/**
	 * convert {@link String} parameter to {@link Result}
	 *
	 * @param string
	 * 		input string
	 * @return output result
	 * @throws ConversionException
	 * 		Conversion error occurred
	 */
	public abstract Result convert(String string) throws ConversionException;
	
	/**
	 * convert {@link File} parameter to {@link Result}
	 *
	 * @param file
	 * 		input file
	 * @return output result
	 * @throws ConversionException
	 * 		Conversion error occurred
	 */
	public abstract Result convert(File file) throws ConversionException;
	
	/**
	 * convert {@link URL} parameter to {@link Result}
	 *
	 * @param url
	 * 		input url
	 * @return output result
	 * @throws ConversionException
	 * 		Conversion error occurred
	 */
	public abstract Result convert(URL url) throws ConversionException;
}