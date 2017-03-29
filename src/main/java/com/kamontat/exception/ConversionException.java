package com.kamontat.exception;

import java.io.IOException;

/**
 * To handle error that occurred when try to convert between html and markdown
 *
 * @author kamontat
 * @version 1.0
 * @since Thu 09/Mar/2017 - 10:17 PM
 */
public class ConversionException extends IOException {
	private String title;
	
	public ConversionException(String title, Exception cause) {
		super(cause.getMessage(), cause.getCause());
		this.title = title;
	}
	
	public ConversionException(String title) {
		super(title);
		this.title = title;
	}
	
	@Override
	public void printStackTrace() {
		System.err.println(title);
		super.printStackTrace();
	}
}
