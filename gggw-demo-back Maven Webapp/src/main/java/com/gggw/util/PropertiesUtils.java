/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:PropertiesUtils.java
 * Package Name:com.gggw.util
 * Date:2016-6-25上午10:11:15
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * ClassName:PropertiesUtils <br/>
 * Function: 配置文件读取. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-25 上午10:11:15 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class PropertiesUtils {
	
	private static Logger logger = Logger.getLogger(PropertiesUtils.class);
	
	private static Properties prop;
	private static List<String> fileList;
	public static String[] pwdNameList = {"db.password"};
	
	public static void clear() {
		prop = null;
	}
	
	/**
	 * TODO(读取配置).<br/>
	 */
	public static Properties getProp() {
		if (prop == null) {
			prop = new Properties();

			if (fileList != null) {
				for (String filename : fileList) {
					InputStream configIs = null;
					try {
						configIs = new FileInputStream(EnvironmentUtils.getFileAbsolutePath(filename));
						prop.load(configIs);

					} catch (IOException e) {
						logger.error("读取配置文件错误" + e.toString(), e);
					} finally {
						if (configIs != null) {
							try {
								configIs.close();
							} catch (IOException e) {
								logger.error("关闭文件流错误", e);
							}
						}
					}
				}
			}

		}
		return prop;
	}
	
	public static String get(String key) {
		String value = "";
		if (getProp().containsKey(key)) value = getProp().getProperty(key);
		if(value.equals("NULL") || value.equals("null") || (value.startsWith("${") && value.endsWith("}"))){
			value = "";
		}
		if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
			return AESUtil.decrypt(value);
		}
		return value;
	}

	public static String get(String key, String defaultValue) {
		String value = getProp().getProperty(key, defaultValue);

		if(value.toLowerCase().equals("null") || null==value || StringUtils.isBlank(value)
				|| (value.startsWith("${") && value.endsWith("}"))){
			value = defaultValue;
		}
		if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
			return AESUtil.decrypt(value);
		}
		return value;
	}

	public static int getInt(String key, int defaultValue) {
		String value = get(key, String.valueOf(defaultValue));
		if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
			return Integer.parseInt(AESUtil.decrypt(value));
		}
		return Integer.parseInt(value);
	}

	public static String getWithFormat(String key, String ... vals) {
		String msg = "";
		String format = getProp().getProperty(key);
		if (!StringUtils.isBlank(format)) {
			MessageFormat mf = new MessageFormat(format);
			msg = mf.format(vals);
		}
		return msg;
	}

	public static String getWithFormat(String key, Map<String, String> params) {
		String str = get(key, "");
		String regex = "\\$\\{([^)]*?)\\}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			key = m.group(1);
			String value = params.get(key);
			str = str.replace("${" + key + "}", StringUtils.defaultIfEmpty(value, ""));
		}
		return str;
	}

	public static Integer get(String key, Integer def) {
		try {
			Integer res = def;
			System.out.println("key="+key);
			String value = getProp().getProperty(key);
			if(null==value||StringUtils.isBlank(value)){
				value = String.valueOf(def);
			}
			
			if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
				return Integer.parseInt(AESUtil.decrypt(value));
			}
			if (getProp().containsKey(key)){
				if(value.equals("NULL") || value.equals("null") 
						|| (value.startsWith("${") && value.endsWith("}"))){
					value = res.toString();
				}
				res = Integer.parseInt(value);
			}
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return def;
		}

	}

	public static Long get(String key, Long def) {
		try {
			Long res = def;
			String value = getProp().getProperty(key);
			if(null==value || StringUtils.isBlank(value)){
				value = String.valueOf(def);
			}
			if(isPassword(key,value)&&value.matches("^[A-F0-9]+$") && value.length()%16==0){
				return Long.parseLong(AESUtil.decrypt(value));
			}
			if (getProp().containsKey(key)) res = Long.parseLong(value);
			return res;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return def;
		}

	}

	public static void set(String key, String value) {
		getProp().put(key, value);
	}

	public void setFileList(List<String> fileList) {
		PropertiesUtils.fileList = fileList;
	}
	
	public static boolean isPassword(String key , String value){
		boolean flag = false;
		boolean flag2 = false;
		if(key!=null&&value!=null&&!"null".equals(value)){
			key = key.replace(".","_");
			for(String str:pwdNameList){
				if(str.equals(key.trim())){
					flag = true;
				}
			}
			if(key.trim().contains("password")&&!"db_password".equals(key.trim())){
				flag2 = true;
			}
		}
		return flag || flag2;
	}

}

