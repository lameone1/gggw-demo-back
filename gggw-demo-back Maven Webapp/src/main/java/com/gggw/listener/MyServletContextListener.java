/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:MyServletContextListener.java
 * Package Name:com.gggw.listener
 * Date:2016-6-24下午6:34:19
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ClassName:MyServletContextListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-24 下午6:34:19 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MyServletContextListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("MyServletContextListener  contextInitialized=============");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		System.out.println("MyServletContextListener  contextDestroyed=============");
		
	}
	
}

