package com.puffinz.utils.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class ObjectUtil {
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isEmpty(int i) {
		return i == 0;
	}
	
	public static boolean isEmpty(double i) {
		return i == 0.0;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public static boolean isNotEmpty(int i) {
		return !isEmpty(i);
	}
	
	public static boolean isNotEmpty(double i) {
		return !isEmpty(i);
	}

	public static Object ifEmpty(Object obj) {
		if(obj == null) obj = 0;
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object obj) {
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		Map<String, Object> map = gson.fromJson(str, HashMap.class);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static String listToString(List list, char delim) {
		return listToString(list, delim, null);
	}
	
	public static String listToString(List<String> list, char delim, String quot) {
		
		if(isNotEmpty(quot)) {
			List<String> strs = new ArrayList<String>();
			for(String str : list) {
				strs.add(quot+ str + quot);
			}
			list.clear();
			list.addAll(strs);
		}
		return ArrayToString(list.toArray(), delim);
	}
	
	public static String ArrayToString(Object[] objs, char delim) {
		return StringUtils.join(objs, delim);
	}
	
	public static boolean isDouble(String str) {
		
		boolean result = true;
		try {
			Double.parseDouble(str);
		} catch(Exception e) {
			result = false;
		}
		return result;
	}
}
