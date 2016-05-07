package com.ashfaq.encode;

import java.util.Hashtable;
import java.util.Map;

/**
 * Encoding tables
 * @author Ashfaq Ahmed S : https://github.com/0yukikaze0
 *
 *         04-May-2016 : 9:29:25 pm
 * 
 */
public class EncodeRefs {

	/*---------------------------------------------------------*
	 * ASCII Character table, with extended characters 128-255
	 *---------------------------------------------------------*/
	public static String[] asciiChars = {"NUL","SOH","STX","ETX","EOT","ENQ","ACK","BEL","BS","HT","LF","VT","FF","CR","SO","SI","DLE","DC1","DC2","DC3","DC4","NAK","SYN","ETB","CAN","EM","SUB","ESC","FS","GS","RS","US"," ","!","\"","#","$","%","&","'","(",")","*","+",",","-",".","/","0","1","2","3","4","5","6","7","8","9",":",";","<","=",">","?","@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","[","\\","]","^","_","`","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","{","|","}","~","	","€"," ","‚","ƒ","„","…","†","‡","ˆ","‰","Š","‹","Œ"," ","Ž"," "," ","‘","’","“","”","•","–","—","˜","™","š","›","œ"," ","ž","Ÿ"," ","¡","¢","£","¤","¥","¦","§","¨","©","ª","«","¬","	","®","¯","°","±","²","³","´","µ","¶","·","¸","¹","º","»","¼","½","¾","¿","À","Á","Â","Ã","Ä","Å","Æ","Ç","È","É","Ê","Ë","Ì","Í","Î","Ï","Ð","Ñ","Ò","Ó","Ô","Õ","Ö","×","Ø","Ù","Ú","Û","Ü","Ý","Þ","ß","à","á","â","ã","ä","å","æ","ç","è","é","ê","ë","ì","í","î","ï","ð","ñ","ò","ó","ô","õ","ö","÷","ø","ù","ú","û","ü","ý","þ","ÿ"};
	/*---------------------------------------------------------*/
	
	/*---------------------------------------------------------*
	 * HEX character to BINARY mapping
	 *---------------------------------------------------------*/
	public static Map<Character, String> hexToBinaryMapping;
	/*---------------------------------------------------------*/
	
	/*---------------------------------------------------------*
	 * Base 64 character table
	 * '=' padding character is added at index 64
	 *---------------------------------------------------------*/
	public static String[] base64Chars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "/", "="};
	/*---------------------------------------------------------*/
	
	static{
		
		/*---------------------------------------------------------*
		 * Populate hex to binary mapping
		 *---------------------------------------------------------*/
		hexToBinaryMapping = new Hashtable<Character, String>();
		hexToBinaryMapping.put('0',"0000");
		hexToBinaryMapping.put('1',"0001");
		hexToBinaryMapping.put('2',"0010");
		hexToBinaryMapping.put('3',"0011");
		hexToBinaryMapping.put('4',"0100");
		hexToBinaryMapping.put('5',"0101");
		hexToBinaryMapping.put('6',"0110");
		hexToBinaryMapping.put('7',"0111");
		hexToBinaryMapping.put('8',"1000");
		hexToBinaryMapping.put('9',"1001");
		hexToBinaryMapping.put('A',"1010");
		hexToBinaryMapping.put('B',"1011");
		hexToBinaryMapping.put('C',"1100");
		hexToBinaryMapping.put('D',"1101");
		hexToBinaryMapping.put('E',"1110");
		hexToBinaryMapping.put('F',"1111");
		hexToBinaryMapping.put('a',"1010");
		hexToBinaryMapping.put('b',"1011");
		hexToBinaryMapping.put('c',"1100");
		hexToBinaryMapping.put('d',"1101");
		hexToBinaryMapping.put('e',"1110");
		hexToBinaryMapping.put('f',"1111");
		/*---------------------------------------------------------*/
		
	}
	
}
