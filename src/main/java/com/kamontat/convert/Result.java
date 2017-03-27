package com.kamontat.convert;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * This class use for Convertor result ONLY
 *
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:55 PM
 */
public class Result {
	private String resultString;
	private InputStream inputStream;
	
	/**
	 * Constructor Package Private Only
	 *
	 * @param resultString
	 * 		string
	 * @param stream
	 * 		input stream
	 */
	Result(String resultString, InputStream stream) {
		this.resultString = resultString;
		inputStream = stream;
	}
	
	/**
	 * get string
	 *
	 * @return {@link String}
	 */
	public String toString() {
		return resultString;
	}
	
	/**
	 * get input stream
	 *
	 * @return {@link InputStream}
	 */
	public InputStream toInputStream() {
		return inputStream;
	}
	
	/**
	 * change string to {@link Result}
	 *
	 * @param s
	 * 		string
	 * @return Result
	 */
	public static Result toResult(String s) {
		return new Result(s, new ByteArrayInputStream(s.getBytes()));
	}
	
	/**
	 * change input stream to {@link Result}
	 *
	 * @param stream
	 * 		inputStream
	 * @return Result
	 */
	public static Result toResult(InputStream stream) {
		BufferedReader r = new BufferedReader(new InputStreamReader(stream));
		Optional<String> optional = r.lines().reduce((s, s2) -> s.concat("\n").concat(s2));
		String s = optional.orElse("");
		return new Result(s, stream);
	}
}
