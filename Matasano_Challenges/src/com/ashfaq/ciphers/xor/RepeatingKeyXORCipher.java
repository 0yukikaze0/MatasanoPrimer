package com.ashfaq.ciphers.xor;

import com.ashfaq.decode.Decoder;
import com.ashfaq.encode.Encoder;

/**
 * Repeating key XOR cipher implementation
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         08-May-2016 : 2:39:05 am
 * 
 */
public class RepeatingKeyXORCipher {

	public RepeatingKeyXORCipher(){}
	
	/**
	 * Takes HEX message string and HEX key String. Returns XOR
	 * ciphered string
	 * @param hexString
	 * @param key
	 * @return
	 */
	public String encryptHexString(String hexString, String key){
		
		/*---------------------------------------------------------*
		 * Convert message and key to binary
		 *---------------------------------------------------------*/
		String[] messageBinary 	= Encoder.instance().hexToBinaryArray(hexString);
		String[] keyBinary		= Encoder.instance().hexToBinaryArray(key);
		/*---------------------------------------------------------*/
		
		StringBuilder cipherBinary = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Apply XOR operation
		 *---------------------------------------------------------*/
		for (int i = 0, keyBlockIndex = 0; i < messageBinary.length; i++, keyBlockIndex++) {
			
			if(keyBlockIndex == keyBinary.length){
				keyBlockIndex = 0;
			}
			
			String messageBlock = messageBinary[i];
			String keyBlock 	= keyBinary[keyBlockIndex];
			
			String xorBlock = "";
			
			for (int idx = 0; idx < messageBlock.length(); idx++) {
				int result = messageBlock.charAt(idx) ^ keyBlock.charAt(idx);
				xorBlock = xorBlock + String.valueOf(result);
			}
			
			cipherBinary.append(xorBlock);
			
		}
		/*---------------------------------------------------------*/
		
		String cipherText = Decoder.instance().binaryToHex(cipherBinary.toString());
		
		return cipherText;
	}
	
	/**
	 * Takes ASCII message string and ASCII key string. Returns XOR
	 * ciphered string
	 * @param asciiString
	 * @param key
	 * @return
	 */
	public String encryptASCIIString(String asciiString, String key){
		
		/*---------------------------------------------------------*
		 * Convert message and key to binary
		 *---------------------------------------------------------*/
		String[] messageBinary 	= Encoder.instance().asciiToBinaryArray(asciiString.getBytes());
		String[] keyBinary		= Encoder.instance().asciiToBinaryArray(key.getBytes());
		/*---------------------------------------------------------*/
		
		StringBuilder cipherBinary = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Apply XOR operation
		 *---------------------------------------------------------*/
		for (int i = 0, keyBlockIndex = 0; i < messageBinary.length; i++, keyBlockIndex++) {
			
			if(keyBlockIndex == keyBinary.length){
				keyBlockIndex = 0;
			}
			
			String messageBlock = messageBinary[i];
			String keyBlock 	= keyBinary[i];
			
			String xorBlock = "";
			
			for (int idx = 0; idx < messageBlock.length(); idx++) {
				int result = messageBlock.charAt(i) ^ keyBlock.charAt(i);
				xorBlock = xorBlock + String.valueOf(result);
			}
			
			cipherBinary.append(xorBlock);
			
		}
		/*---------------------------------------------------------*/
		
		String cipherText = Decoder.instance().binaryToAscii(cipherBinary.toString());
		
		return cipherText;

	}

	
}
