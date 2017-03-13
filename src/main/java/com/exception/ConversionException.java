package com.exception;

import java.io.IOException;

/**
 * @author kamontat
 * @version 1.0
 * @since Thu 09/Mar/2017 - 10:17 PM
 */
public class ConversionException extends IOException {
	public ConversionException(String title, Exception cause) {
		super(title + ": " + cause.getMessage(), cause.getCause());
	}
	
	public ConversionException(String title) {
		super(title);
	}
}
