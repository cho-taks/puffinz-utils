package com.puffinz.utils.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ObjectUtil {
	
	public boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public boolean isEmpty(int i) {
		return i == 0;
	}
	
	public boolean isEmpty(double i) {
		return i == 0.0;
	}

	public boolean isEmpty(Object obj) {
		return obj == null;
	}

	public boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	public boolean isNotEmpty(int i) {
		return !isEmpty(i);
	}
	
	public boolean isNotEmpty(double i) {
		return !isEmpty(i);
	}

	public Object ifEmpty(Object obj) {
		if(obj == null) obj = 0;
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> beanToMap(Object obj) {
		Gson gson = new Gson();
		String str = gson.toJson(obj);
		Map<String, Object> map = gson.fromJson(str, HashMap.class);
		return map;
	}

	@SuppressWarnings("unchecked")
	public String listToString(List list, char delim) {
		return listToString(list, delim, null);
	}
	
	public String listToString(List<String> list, char delim, String quot) {
		
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
	
	public String ArrayToString(Object[] objs, char delim) {
		return StringUtils.join(objs, delim);
	}
	
	public boolean isDouble(String str) {
		
		boolean result = true;
		try {
			Double.parseDouble(str);
		} catch(Exception e) {
			result = false;
		}
		return result;
	}
}
