/**
 * Project Name:gggw-demo-back Maven Webapp
 * File Name:RegexUtil.java
 * Package Name:com.gggw.util
 * Date:2016-6-28下午2:57:56
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:RegexUtil <br/>
 * Function: 正则匹配. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-28 下午2:57:56 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RegexUtil {
	
	/**
	 * checkEmail:(验证Email). <br/>
	 */
	public static boolean checkEmail(String email) {   
	       String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{2,4})(\\]?)$";   
	       return Pattern.matches(regex, email);   
	}  
	
	/**
	 * getNumber:(从字符串中获取数字). <br/>
	 * Matcher.matches() / Matcher.lookingAt() / Matcher.find() 
	 * Mathcer.start() / Matcher.end() / Matcher.group() 
	 */
	public static String getNumber(String str) {
		String num = "";
		Pattern p = Pattern.compile("[0-9\\.]+");
		Matcher m = p.matcher(str);
		while (m.find()) {                     
			num = num + m.group() +";";
		}
		return num;
	}
	
	public static void main(String[] args) {
		System.out.println(getNumber("adfdasf688asdf879asfd"));
	}
}

