package com.ashfaq.test;

import com.ashfaq.ciphers.xor.FixedXORCipher;

public class TestXORCipher {

	public static void main(String[] args) {
		
		String hexMessage 	= "1c0111001f010100061a024b53535009181c";
		String hexKey		= "686974207468652062756c6c277320657965";
		
		FixedXORCipher xorCipher = new FixedXORCipher();
		
		String cipherText = xorCipher.encryptHexString(hexMessage, hexKey);
		
	}
	
}
