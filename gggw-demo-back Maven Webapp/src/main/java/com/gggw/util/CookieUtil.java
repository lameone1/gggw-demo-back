package com.gggw.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;


/**
 * ClassName:CookieUtil <br/>
 * Function: TODO excute cookie. <br/>
 * Date:     2016-10-11 下午5:32:14 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CookieUtil {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	public static final String COOKIE_GGGW_SESSION_ID = "GGGW_SESSION_ID";
	public static final String GGGW_USER_SESSION_ID = "USER_SESSION_ID";
	private static Map<String, Integer> cookies = null;
	
	/**
	 * 用来存储cookies存活时间, 单位:天
	 */
	static {
		cookies = new HashMap<String, Integer>();
		cookies.put(GGGW_USER_SESSION_ID, 2);
	}
	
	/**
	 * 功能说明: 设置cookie<br>
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-11 下午9:18:17<br>
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, Boolean isRemmerber) {
		try {
			if (null == value) {
				value = "";
			}
			StringBuffer cookieContent = new StringBuffer();
			/**cookie Id */
			cookieContent.append(name);
			cookieContent.append("=");
			cookieContent.append(URLEncoder.encode(value, "UTF-8"));		//为了支持中文
			cookieContent.append(";");
			/**cookie path */
			cookieContent.append("Path=/;");
			/**cookie domain */
			cookieContent.append("Domain=");
			cookieContent.append(PropertiesUtils.get("gggw.cookie.domain"));
			cookieContent.append(";");
			/**cookie Max-Age */
			if (cookies.containsKey(name)) {
				if (cookies.get(name) > 0 && isRemmerber) {
					cookieContent.append("Max-Age=");
					cookieContent.append(cookies.get(name) * 24 * 3600);
					cookieContent.append(";");
				}
			}
			/**防止客户端js 读取cookie值 */
			cookieContent.append("HTTPOnly");
			response.addHeader("Set-Cookie", cookieContent.toString());
			logger.info("Set-Cookie-->  " + cookieContent.toString());
		} catch (Exception e) {
			logger.error("CookieUtil setCookie is error!", e);
		}
	} 
	
	public static void setCookie2(HttpServletResponse response, String name, String value, Boolean isRemmerber) {
		try {
			if (null == value) {
				value = "";
			}
			/**cookie Id */
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			/**
			 * cookie path 
			 * 		warning: 默认为根目录
			 * 				 也可设置/sys/  表示在www.baidu.com/sys 下面有效
			 */
			cookie.setPath("/");
			/**  cookie domain 
			 *      warning: 这里domain不能设置为"" 
			 *      		 127.0.0.1也不行
			 *      		  要么默认,要么填写域名  www.baidu.com 
			 *              
			 */
			cookie.setDomain(PropertiesUtils.get("gggw.cookie.domain"));
			/**cookie Max-Age */
			if (cookies.containsKey(name)) {
				if (cookies.get(name) > 0 && isRemmerber) {
					cookie.setMaxAge(cookies.get(name) * 24 * 3600);
				}
			}
			/**防止客户端js 读取cookie值      Only support from Servlet 3+ */
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		} catch (Exception e) {
			logger.error("CookieUtil setCookie2 is error!", e);
		}	
	}
	
	/**
	 * 功能说明: 获取cookie<br>
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-11 下午10:00:42<br>
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		try {
			Cookie[] requestCookies = request.getCookies();
			if (requestCookies != null) {
				for (Cookie cookie : requestCookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("CookieUtil getCookie is error!", e);
			return null;
		}
		return null;
	}
	
	/**
	 * 功能说明: 清除cookie<br>
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-11 下午10:04:25<br>
	 */
	public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (StringUtils.equals(cookieName, cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					// cookie.setValue(null)会被读取出来为"null"，所以这里要设置为空字符串
					cookie.setValue("");
					response.addCookie(cookie);
				}
			}
		}
	}
}

