package com.main;

import com.kamontat.convertion.Conversion;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		String markdown = "I *don't* know, Who **are** you? But `I` Can't see.";
		String html = "<p>I <em>don't</em> know, Who <strong>are</strong> you? But <code>I</code> Can't see.</p>";
		
		String h = Conversion.by(Conversion.Type.MD2HTML).convertString(markdown).toString();
		System.out.println(h);
		
		String md = Conversion.by(Conversion.Type.HTML2MD).convertString(html).toString();
		
		System.out.println(html);
		System.out.println(h);
		
		System.out.println(markdown);
		System.out.println(md);
	}
}
