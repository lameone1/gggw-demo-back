/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:EnvironmentUtils.java
 * Package Name:com.gggw.util
 * Date:2016-6-25上午10:19:28
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ClassName:EnvironmentUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-25 上午10:19:28 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class EnvironmentUtils {
	
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(EnvironmentUtils.class);	
	private static final String CLASSPATH_PREFIX = "classpath:";
	
	/**
	 * 获取系统运行期配置文件路径
	 * @return
	 */
	public static String getRuntimeConfigPath() {
		String path = System.getenv().get("RUNTIME_CONFIG_ROOT");
		if (StringUtils.isBlank(path)) {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win") >= 0) {
				path = "E:"+File.separator+"runtime_config";
			}
			else {
				path = "/home/config/runtime_config";
			}
		}
		return path;
	}
	
	/**
	 * 获取WEB-INF Class 目录地址
	 * @return
	 */
	public static String getWebClassPath() {
		return Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	}
	
	public static String getWebRootPath(){
		String webClassPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		File file = new File(webClassPath);
		return file.getParentFile().getParentFile().getAbsolutePath();
	}
	
	
	public static String getFileAbsolutePath(String filename) throws FileNotFoundException {
		if (filename.startsWith(CLASSPATH_PREFIX)) {
			filename = filename.substring(CLASSPATH_PREFIX.length());
		}
		if (filename.startsWith("/")) {
			filename = filename.substring(1);
			String fileUrl = EnvironmentUtils.getRuntimeConfigPath()+File.separator+filename; 
			File file = new File(fileUrl);
			if (file.exists()) {
				return fileUrl;
			}
			fileUrl = getWebClassPath()+filename;
			file = new File(fileUrl);
			if (file.exists()) {
				return fileUrl;
			}else{
				throw new FileNotFoundException(filename+" Not Found In System ClassPath ...");
			}
		}else {
			String configRoot = EnvironmentUtils.getRuntimeConfigPath();
			List<File> list = new ArrayList<File>();
			findFile(new File(configRoot), filename, list);
			if (list.isEmpty()) {
				String fileUrl = getWebClassPath()+filename;
				File file = new File(fileUrl);
				if (file.exists()) {
					return fileUrl;
				}else{
					throw new FileNotFoundException(filename+" Not Found In System ClassPath ...");
				}
			}else{
				return list.get(0).getAbsolutePath();
			}
		}
		
	}
	
	private static void findFile(File file, String filename, List<File> list) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File subFile : files) {
				findFile(subFile, filename, list);
			}
		}else{
			if (file.getAbsolutePath().contains(filename)) {
				list.add(file);
			}
		}
	}
	
}

