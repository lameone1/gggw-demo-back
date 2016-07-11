/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:SystemPropertyBasedPlaceholderConfigurer.java
 * Package Name:com.gggw.util
 * Date:2016-6-25下午1:11:00
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * ClassName:SystemPropertyBasedPlaceholderConfigurer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-25 下午1:11:00 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class SystemPropertyBasedPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String[] fileLocations;

	private static Logger logger = Logger.getLogger(SystemPropertyBasedPlaceholderConfigurer.class);

	/**
	 * Injected property. Defines file location relative to base property folder
	 */
	public void setFileLocation(String fileLocation) {
		this.fileLocations = new String[] { fileLocation };
	}

	/**
	 * Injected property. Defines file locations relative to base property
	 * folder
	 */
	public void setFileLocations(String[] fileLocations) {
		this.fileLocations = fileLocations;
	}

	
	@Override
	public void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		 if (fileLocations == null) {
			 throw new IllegalArgumentException("Property 'fileLocations' cannot be null");
		 }
		 String password = props.getProperty("db.password");
		 if (password != null)
		    {
		      props.setProperty("db.password", AESUtil.decrypt(password));
		    }
		 
		 internalSetLocations();
		 super.processProperties(beanFactory, props);
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		 if (fileLocations == null) {
			 throw new IllegalArgumentException("Property 'fileLocations' cannot be null");
		 }
		 internalSetLocations();
		 super.postProcessBeanFactory(beanFactory);
	}
	
	protected void internalSetLocations() {
		List<Resource> list = new ArrayList<Resource>();
		for (String fileLocation : fileLocations) {
			try {
				list.add(new FileSystemResource(EnvironmentUtils.getFileAbsolutePath(fileLocation)));
			} catch (Exception e) {
				logger.error("读取配置错误"+e.toString(), e);
			}
		}
		super.setLocations(list.toArray(new Resource[list.size()]));
	}
}

