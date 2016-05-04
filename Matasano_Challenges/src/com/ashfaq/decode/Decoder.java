package com.ashfaq.decode;

/**
 * Singleton decoder
 * Decodes available
 * 		|
 * 		+- BINARY	-->	HEX
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
	
}
