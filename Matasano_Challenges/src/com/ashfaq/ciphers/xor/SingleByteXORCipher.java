package com.ashfaq.ciphers.xor;

import com.ashfaq.decode.Decoder;
import com.ashfaq.encode.Encoder;

/**
 *
 * Single byte XOR cipher implementation
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         08-May-2016 : 2:13:01 am
 * 
 */
public class SingleByteXORCipher {

	public SingleByteXORCipher(){}
	
	/**
	 * Takes HEX message string and hex character key. Returns XOR
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
		for (int i = 0; i < messageBinary.length; i++) {
			
			String messageBlock = messageBinary[i];
			
			String xorBlock = "";
			
			for (int idx = 0; idx < messageBlock.length(); idx++) {
				int result = messageBlock.charAt(idx) ^ keyBinary[0].charAt(idx);
				xorBlock = xorBlock + String.valueOf(result);
			}
			
			cipherBinary.append(xorBlock);
			
		}
		/*---------------------------------------------------------*/
		
		String cipherText = Decoder.instance().binaryToHex(cipherBinary.toString());
		
		return cipherText;
	}
	
	/**
	 * Takes ASCII message string and single character key. Returns XOR
	 * ciphered string
	 * @param asciiString
	 * @param key
	 * @return
	 */
	public String encryptASCIIString(String asciiString, char key){
		
		/*---------------------------------------------------------*
		 * Convert message and key to binary
		 *---------------------------------------------------------*/
		String[] messageBinary 	= Encoder.instance().asciiToBinaryArray(asciiString.getBytes());
		String[] keyBinary		= Encoder.instance().asciiToBinaryArray(String.valueOf(key).getBytes());
		/*---------------------------------------------------------*/
		
		StringBuilder cipherBinary = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Apply XOR operation
		 *---------------------------------------------------------*/
		for (int i = 0; i < messageBinary.length; i++) {
			
			String messageBlock = messageBinary[i];
			
			String xorBlock = "";
			
			for (int idx = 0; idx < messageBlock.length(); idx++) {
				int result = messageBlock.charAt(i) ^ keyBinary[0].charAt(i);
				xorBlock = xorBlock + String.valueOf(result);
			}
			
			cipherBinary.append(xorBlock);
			
		}
		/*---------------------------------------------------------*/
		
		String cipherText = Decoder.instance().binaryToAscii(cipherBinary.toString());
		
		return cipherText;

	}

	
}
