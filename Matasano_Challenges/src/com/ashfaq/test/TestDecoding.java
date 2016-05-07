package com.ashfaq.test;

import com.ashfaq.decode.Decoder;

public class TestDecoding {

	public static void main(String[] args) {
		
		String hexString = "4173686661712041686d6564";
		String asciiString = Decoder.instance().hexToAscii(hexString);
		
		System.out.println(asciiString);
	}
	
}
