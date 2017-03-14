package com.kamontat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since Mon 13/Mar/2017 - 8:55 PM
 */
public class Result {
	private String resultString;
	private InputStream inputStream;
	
	Result(String resultString, InputStream stream) {
		this.resultString = resultString;
		inputStream = stream;
	}
	
	public String toString() {
		return resultString;
	}
	
	public InputStream toInputStream() {
		return inputStream;
	}
	
	public static Result toResult(String s) {
		return new Result(s, new ByteArrayInputStream(s.getBytes()));
	}
	
	public static Result toResult(InputStream stream) {
		BufferedReader r = new BufferedReader(new InputStreamReader(stream));
		Optional<String> optional = r.lines().reduce((s, s2) -> s.concat("\n").concat(s2));
		String s = optional.orElse("");
		return new Result(s, stream);
	}
}
