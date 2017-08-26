package com.yunpan.manager.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @ClassName: CookieUtill
 * @Description: cookie工具类
 * @author duanjigui
 * @date 2017年1月10日 下午5:32:18
 *
 */
public class CookieUtill {
	
	public static void setCookie(HttpServletResponse response,String key,String value,int seconds){
		Cookie cookie=new Cookie(key, value);
		cookie.setMaxAge(seconds);
		//cookie.setDomain(".yunpan.com"); //设置域名为.yunpan.com的访问路径才能设置
		cookie.setDomain("localhost");
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	
	public static String getCookie(HttpServletRequest request,String key){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
}
