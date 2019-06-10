package com.puffinz.utils.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtil {
	
    public String getCookie(HttpServletRequest request, String name)
    {
		try {
			Cookie[] cookies = request.getCookies();
			if(cookies == null) {
				//log.info("cookies : not found");
				return null;
			}
				
			for(Cookie cookie : cookies ) {
				//log.info("cookie : {}, {}, {}", new Object[] { cookie.getDomain(), cookie.getName(), cookie.getValue() });
				if (cookie != null && name.equals(cookie.getName())) {
					return cookie.getValue();
				}		
			}
		}
		catch(Exception e) {
			log.error("Exception :: {}", e);
		}
		
		return null;  
    }
    
    public void setCookie(HttpServletResponse response, String name, String value, int agesec) {
		
    	Cookie cookie = new Cookie(name, value);
    	String domain = "adpuffin.com";

		if(agesec < 0) { 
			cookie.setMaxAge(0);
		} else if(agesec == 0) {
			//
		} else {
			cookie.setMaxAge(agesec);
		}

		if("localhost".equals(domain)) {
			cookie.setDomain(domain);
		} else {
			cookie.setDomain("." + domain);
		}
		
		cookie.setPath("/");
		
		log.error("cookie : {}|{}|{}|{}", new Object[]{domain, name, value, agesec});

		response.addCookie(cookie); 
    }

}
