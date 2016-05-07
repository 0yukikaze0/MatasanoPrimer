package com.ashfaq.decode;

import com.ashfaq.encode.EncodeRefs;

/**
 * Singleton decoder
 * Decodes available
 * 		|
 * 		+- BINARY	-->	HEX
 * 		+- BINARY 	--> ASCII
 * 		+- HEX		--> ASCII
 * 
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         05-May-2016 : 12:03:25 am
 * 
 */
public class Decoder {

	private static Decoder instance;
	
	private Decoder(){}
	
	public static Decoder instance(){
		if(instance == null){
			instance = new Decoder();
		}
		return instance;
	}
	
	/**
	 * Takes a binary string and converts into an
	 * HEX representation
	 * @param binaryString
	 * @return
	 */
	public String binaryToHex(String binaryString){
		
		StringBuilder hexString = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Read the binary string in 4-bit blocks
		 *---------------------------------------------------------*/
		for (int i = 0; i < binaryString.length(); i = i + 4) {
		
			String binaryBlock = binaryString.substring(i,i+4);
			
			hexString.append(DecodeRefs.binaryToHexMapping.get(binaryBlock));
		}
		/*---------------------------------------------------------*/
		
		return hexString.toString();
		
	}
	
	/**
	 * Takes a binary string and builds its ASCII representation
	 * a ASCII string
	 * @param binaries
	 * @return
	 */
	public String binaryToAscii(String binaryString){
		
		StringBuilder asciiString = new StringBuilder();
		
		for (int i = 0; i < binaryString.length(); i = i + 8) {
			
			String binaryBlock = binaryString.substring(i,i+8);
			
			asciiString.append(EncodeRefs.asciiChars[calculateASCIIIndex(binaryBlock)]);
			
		}
		
		return asciiString.toString();
		
	}
	
	/**
	 * Takes a HEX string and converts to ASCII
	 * @param hexString
	 * @return
	 */
	public String hexToAscii(String hexString){
		
		/*---------------------------------------------------------*
		 * Sanity check : See if length is even. if not even, we 
		 * have an incomplete string
		 *---------------------------------------------------------*/
		if(hexString.length() % 2 != 0){
			return null;
		}
		/*---------------------------------------------------------*/
		
		StringBuilder asciiString = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Build 8-bit representations from hex characters
		 *---------------------------------------------------------*/
		for(int i=0;i<hexString.length();i = i+2){
			
			asciiString.append(EncodeRefs.asciiChars[
			                                         calculateASCIIIndex(	EncodeRefs.hexToBinaryMapping.get(hexString.charAt(i)) 
			                                        		 			+ 	EncodeRefs.hexToBinaryMapping.get(hexString.charAt(i+1)))]);
			
		}
		/*---------------------------------------------------------*/
		
		return asciiString.toString();
	}
	
	/**
	 * Takes an 8-bit binary section and calculates ASCII index
	 * @param section
	 * @return
	 */
	private synchronized int calculateASCIIIndex(String section){
		
		int index = 0;
		
		for (int bitPosition = 1, i = section.length()-1; i >= 0; bitPosition = bitPosition * 2, i--) {
			
			int current = Integer.parseInt(String.valueOf(section.charAt(i)));
			index = index + (current * bitPosition);
			
		}
		
		return index;
		
	}
	
}
