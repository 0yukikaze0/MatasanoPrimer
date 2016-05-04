package com.ashfaq.test;

import com.ashfaq.encode.Encoder;

public class TestEncoding {

	public static void main(String[] args) {
		
		Encoder encoder = Encoder.instance();
		
		/*---------------------------------------------------------*
		 * Encoding ASCII string to base64
		 *---------------------------------------------------------*/
		String asciiString = "Ashfaq";
		String asciiBase64 = encoder.asciiToBase64(asciiString);
		/*---------------------------------------------------------*/
		
		/*---------------------------------------------------------*
		 * Encoding HEX string to base64
		 *---------------------------------------------------------*/
		String hexString = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
		String hexBase64 = encoder.hexToBase64(hexString);
		/*---------------------------------------------------------*/
	}
	
}
