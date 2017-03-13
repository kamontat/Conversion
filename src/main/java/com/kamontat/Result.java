package com.kamontat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:55 PM
 */
public class Result {
	private String resultString;
	private BufferedReader resultBuffer;
	
	Result(String resultString, BufferedReader resultBuffer) {
		this.resultString = resultString;
		this.resultBuffer = resultBuffer;
	}
	
	public String toString() {
		return resultString;
	}
	
	public BufferedReader toBuffer() {
		return resultBuffer;
	}
	
	public static Result toResult(String s) {
		return new Result(s, new BufferedReader(new InputStreamReader(new ByteArrayInputStream(s.getBytes()))));
	}
}
