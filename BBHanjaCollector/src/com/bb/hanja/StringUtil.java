package com.bb.hanja;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StringUtil {
	
	/**
	 * 정렬
	 * 
	 * @param str
	 * @return
	 */
	public static String getSortedText(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		
		ArrayList<String> strList = new ArrayList<String>();
		
		String oneMark = "";
		int len = str.length();
		for (int i=0; i<len; i++) {
			oneMark = str.substring(i, i+1);
			if (oneMark.equals(" ") || oneMark.equals("\r") || oneMark.equals("\n") || oneMark.equals("\t")) {
				continue;
			}
			
			strList.add(oneMark);
		}
		
		Collections.sort(strList);
		
		StringBuffer buff = new StringBuffer();
		len = strList.size();
		for (int i=0; i<len; i++) {
			if (buff.length() > 0) {
				buff.append("\n");
			}
			buff.append(strList.get(i));
		}
		return buff.toString();
	}
	
	/**
	 * 중복제거. 한 글자 단위로 개행.
	 * 
	 * @param str
	 * @return
	 */
	public static String getDuplRemovedText(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		StringBuffer buff = new StringBuffer();
		
		String oneMark = "";
		int len = str.length();
		for (int i=0; i<len; i++) {
			oneMark = str.substring(i, i+1);
			if (oneMark.equals(" ") || oneMark.equals("\r") || oneMark.equals("\n") || oneMark.equals("\t")) {
				continue;
			}
			
			if (map.get(oneMark) == null) {
				map.put(oneMark, 1);
				
				if (buff.length() > 0) {
					buff.append("\n");
				}
				buff.append(oneMark);
			}
		}
		
		return buff.toString();
	}
}