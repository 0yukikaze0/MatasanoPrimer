package com.ashfaq.decode;

import java.util.Hashtable;
import java.util.Map;

/**
 * Decoding Tables
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         05-May-2016 : 12:01:46 am
 * 
 */
public class DecodeRefs {
	
	/*---------------------------------------------------------*
	 * Binary to HEX character
	 *---------------------------------------------------------*/
	public static Map<String, Character> binaryToHexMapping;
	/*---------------------------------------------------------*/

	static{
		
		/*---------------------------------------------------------*
		 * Populate Binary to HEX mapping
		 *---------------------------------------------------------*/
		binaryToHexMapping = new Hashtable<String, Character>();
		binaryToHexMapping.put("0000",'0');
		binaryToHexMapping.put("0001",'1');
		binaryToHexMapping.put("0010",'2');
		binaryToHexMapping.put("0011",'3');
		binaryToHexMapping.put("0100",'4');
		binaryToHexMapping.put("0101",'5');
		binaryToHexMapping.put("0110",'6');
		binaryToHexMapping.put("0111",'7');
		binaryToHexMapping.put("1000",'8');
		binaryToHexMapping.put("1001",'9');
		binaryToHexMapping.put("1010",'A');
		binaryToHexMapping.put("1011",'B');
		binaryToHexMapping.put("1100",'C');
		binaryToHexMapping.put("1101",'D');
		binaryToHexMapping.put("1110",'E');
		binaryToHexMapping.put("1111",'F');
		/*---------------------------------------------------------*/
		
	}
}
