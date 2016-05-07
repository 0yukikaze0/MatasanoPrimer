package com.ashfaq.ciphers.xor;

import com.ashfaq.decode.Decoder;
import com.ashfaq.encode.Encoder;

/**
 * Fixed length XOR cipher implementation
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         04-May-2016 : 11:28:28 pm
 * 
 */
public class FixedXORCipher {

	public FixedXORCipher(){}
	
	/**
	 * Takes equal sized message and key HEX strings and returns XOR
	 * ciphered string
	 * @param hexString
	 * @param key
	 * @return
	 */
	public String encryptHexString(String hexString, String key){
		
		/*---------------------------------------------------------*
		 * Lengths of message and key should be equal
		 *---------------------------------------------------------*/
		if(hexString.length() != key.length()){
			return null;
		}
		/*---------------------------------------------------------*/
		
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
		for (int i = 0; i < messageBinary.length; i++) {
			
			String messageBlock = messageBinary[i];
			String keyBlock 	= keyBinary[i];
			
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
	 * Takes equal sized message and key ASCII string and returns XOR
	 * ciphered string
	 * @param asciiString
	 * @param key
	 * @return
	 */
	public String encryptASCIIString(String asciiString, String key){
		
		/*---------------------------------------------------------*
		 * Lengths of message and key should be equal
		 *---------------------------------------------------------*/
		if(asciiString.length() != key.length()){
			return null;
		}
		/*---------------------------------------------------------*/
		
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
		for (int i = 0; i < messageBinary.length; i++) {
			
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
