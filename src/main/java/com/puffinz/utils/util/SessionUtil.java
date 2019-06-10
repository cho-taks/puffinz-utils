package com.puffinz.utils.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SessionUtil {
	
	public Object getAttribute(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);
	}
	
	public void setAttribute(HttpServletRequest request, String key, Object value) {
		request.getSession().setAttribute(key, value);
	}
	
	public void removeAttribute(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);
	}

}
