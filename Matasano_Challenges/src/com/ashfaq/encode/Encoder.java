package com.ashfaq.encode;

/**
 * Singleton encoder
 * Note : Rough draft code, unoptimized, heavy usage of strings!
 * Encodes available
 * 		|
 * 		+- ASCII	-->	BINARY
 * 		+- HEX		-->	BINARY
 * 		+- ASCII	-->	BASE64
 * 		+- HEX		-->	BASE64
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         04-May-2016 : 8:26:11 pm
 * 
 */
public class Encoder {
	
	private static Encoder instance;
	
	private Encoder(){}
	
	public static Encoder instance(){
		
		if(instance == null){
			instance = new Encoder();
		}
		
		return instance;
	}
	
	/**
	 * Takes a byte array and returns an array of binary representations
	 * @param bytes
	 * @return
	 */
	public synchronized String[] asciiToBinaryArray(byte[] bytes){
		
		String[] binaries = new String[bytes.length];
				
		for (int i = 0; i < bytes.length; i++) {
			
			binaries[i] = padLeft(Integer.toBinaryString(bytes[i]),8);
			
		}
		
		return binaries;
		
	}
	
	/**
	 * Takes a hexadecimal string and returns an array of its binary representations
	 * @param hexString
	 * @return
	 */
	public synchronized String[] hexToBinaryArray(String hexString){
		
		/*---------------------------------------------------------*
		 * Sanity check to see if the hex string is complete
		 * Length of a hex representation is always even
		 *---------------------------------------------------------*/
		if(hexString.length()%2 != 0){
			return null;
		}
		/*---------------------------------------------------------*/
		
		/*---------------------------------------------------------*
		 * Read two characters at a time and build an 8-bit binary 
		 * representation
		 *---------------------------------------------------------*/
		String[] binaries = new String[hexString.length()/2];
		for (int i = 0, idx = 0; i < hexString.length(); i = i + 2, idx++) {
			
			char left 	= hexString.charAt(i);
			char right 	= hexString.charAt(i+1);
			
			binaries[idx] = EncodeRefs.hexToBinaryMapping.get(left) + EncodeRefs.hexToBinaryMapping.get(right);
		}
		/*---------------------------------------------------------*/
		
		return binaries;
	}
	
	/**
	 * Takes a ASCII string and returns its base64 encoded representations
	 * @param asciiString
	 * @return
	 */
	public synchronized String asciiToBase64(String asciiString){
		
		/*---------------------------------------------------------*
		 * Get binary representation of the ASCII
		 *---------------------------------------------------------*/
		String[] binaries = asciiToBinaryArray(asciiString.getBytes());
		/*---------------------------------------------------------*/
		
		StringBuilder base64Rep = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Build 24-bit binary blocks and send them to encoder
		 *---------------------------------------------------------*/
		
			/*---------------------------------------------------------*
			 * In case there exists a block in the end which is less 
			 * than 24 bits, prune it. This block will be operated on 
			 * separately
			 *---------------------------------------------------------*/
			int oddIndexCount 	= binaries.length % 3;
			int logicalEnd 		= binaries.length - oddIndexCount;
			/*---------------------------------------------------------*/
		
		for (int i = 0; i < logicalEnd; i = i+3){
			base64Rep.append(blockToBase64( binaries[i] + binaries[i+1] + binaries[i+2]));
		}
		
			/*---------------------------------------------------------*
			 * Handling odd block
			 *---------------------------------------------------------*/
			if(oddIndexCount > 0){
				String oddBlock = "";
				for(int i=binaries.length - oddIndexCount ; i<binaries.length ; i++){
					oddBlock = oddBlock + binaries[i];
				}
				
				base64Rep.append(blockToBase64(oddBlock));
			}
			/*---------------------------------------------------------*/
		/*---------------------------------------------------------*/
		
		return base64Rep.toString();
	}
	
	/**
	 * Takes a HEX string and returns a base64 encoded representation
	 * @param hexString
	 * @return
	 */
	public synchronized String hexToBase64(String hexString){
		
		/*---------------------------------------------------------*
		 * Get binary representation of this HEX string
		 *---------------------------------------------------------*/
		String[] binaries = hexToBinaryArray(hexString);
		/*---------------------------------------------------------*/
		
		StringBuilder base64Rep = new StringBuilder();
		
		/*---------------------------------------------------------*
		 * Build 24-bit binary blocks and send them to encoder
		 *---------------------------------------------------------*/
		
			/*---------------------------------------------------------*
			 * In case there is any block in the end that is less than 24
			 * bits, prune it. This block will be operated outside the 
			 * next loop
			 *---------------------------------------------------------*/
			int oddIndexCount 	= binaries.length % 3;
			int logicalEnd 		= binaries.length - oddIndexCount;
			/*---------------------------------------------------------*/
		
		for (int i = 0; i < logicalEnd; i = i+3){
			base64Rep.append(blockToBase64( binaries[i] + binaries[i+1] + binaries[i+2]));
		}
		
		if(oddIndexCount > 0){
			String oddBlock = "";
			for(int i=binaries.length - oddIndexCount ; i<binaries.length ; i++){
				oddBlock = oddBlock + binaries[i];
			}
			
			base64Rep.append(blockToBase64(oddBlock));
		}
		/*---------------------------------------------------------*/
		
		return base64Rep.toString();
		
	}
	
	/**
	 * Base64 encoder
	 * 	|
	 * 	+- Takes a 24bit binary block as input
	 *  +- Splits into four 6-bit sections
	 *  	+- Resolves to a base64 index
	 *  
	 *  				+-------------------------------------------------+
	 *  24 Bit Block 	| 0 0 0 1 0 1 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 1 1 0 |
	 *  				+------------+-----------+-----------+------------+
	 *  6 Bit Sections 	| 0 0 0 1 0 1|0 0 1 0 0 0|0 0 1 0 0 0|0 0 0 1 1 0 |
	 *  				+------------+-----------+-----------+------------+
	 *  Base 64 Index	|	   5     |     8     |     8     |     6      |
	 *  				+------------+-----------+-----------+------------+
	 *  
	 * @param binaryBlock
	 * @return
	 */
	private synchronized String blockToBase64(String binaryBlock){
		
		/*---------------------------------------------------------*
		 * Split 24 bit binary block into 4 6-bit sections
		 * For missing bits, pad with 0 on right
		 *---------------------------------------------------------*/
		String section1 = "";
		String section2 = "";
		String section3 = "";
		String section4 = "";
		
		if(binaryBlock.length() == 24){
			section1 = binaryBlock.substring(0,6);
			section2 = binaryBlock.substring(6,12);
			section3 = binaryBlock.substring(12,18);
			section4 = binaryBlock.substring(18,24);
		} else if(binaryBlock.length() >= 18 && binaryBlock.length() < 24){
			section1 = binaryBlock.substring(0,6);
			section2 = binaryBlock.substring(6,12);
			section3 = binaryBlock.substring(12,18);
			section4 = binaryBlock.substring(18);
			section4 = padRight(section4, 6);
		} else if(binaryBlock.length() >= 12 && binaryBlock.length() < 18){
			
			section1 = binaryBlock.substring(0,6);
			section2 = binaryBlock.substring(6,12);
			section3 = binaryBlock.substring(12);
			section3 = padRight(section3, 6);
			section4 = "PAD";
		} else {
			section1 = binaryBlock.substring(0,6);
			section2 = binaryBlock.substring(6);
			section2 = padRight(section2, 6);
			section3 = "PAD";
			section4 = "PAD";
		}
		/*---------------------------------------------------------*/
		
		String base64Block = 		EncodeRefs.base64Chars[calculateBase64Index(section1)] 
								+	EncodeRefs.base64Chars[calculateBase64Index(section2)]
								+	EncodeRefs.base64Chars[calculateBase64Index(section3)]
								+	EncodeRefs.base64Chars[calculateBase64Index(section4)];
		
		return base64Block;
		
	}
	
	/**
	 * Takes a 6-bit binary section and calculates base64 index
	 * @param section
	 * @return
	 */
	private synchronized int calculateBase64Index(String section){
		
		if("PAD".equals(section)){
			return 64;
		}
		
		int index = 0;
		
		for (int bitPosition = 1, i = section.length()-1; i >= 0; bitPosition = bitPosition * 2, i--) {
			
			int current = Integer.parseInt(String.valueOf(section.charAt(i)));
			index = index + (current * bitPosition);
			
		}
		
		return index;
		
	}
	
	private String padLeft(String inputBlock, int bitLength){
		
		StringBuilder padChars = new StringBuilder();
		
		for (int i = 0; i < (bitLength - inputBlock.length()); i++) {
			padChars.append("0");
		}
		
		return padChars + inputBlock;
	}
	
	private String padRight(String inputBlock, int bitLength){
		
		StringBuilder padChars = new StringBuilder();
		
		for (int i = 0; i < (bitLength - inputBlock.length()); i++) {
			padChars.append("0");
		}
		
		return inputBlock + padChars;
	}

}
