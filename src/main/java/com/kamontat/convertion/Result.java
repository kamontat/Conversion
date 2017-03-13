package com.kamontat.convertion;

import java.io.BufferedReader;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:55 PM
 */
public class Result {
	private String resultString;
	private BufferedReader resultBuffer;
	
	public Result(String resultString, BufferedReader resultBuffer) {
		this.resultString = resultString;
		this.resultBuffer = resultBuffer;
	}
	
	public String toString() {
		return resultString;
	}
	
	public BufferedReader toBuffer() {
		return resultBuffer;
	}
}
