/**
 * Project Name:CPBBack Maven Webapp
 * File Name:StartFilter.java
 * Package Name:com.gggw.filter
 * Date:2016-6-23上午9:28:09
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/
/**
 * Project Name:CPBBack Maven Webapp
 * File Name:StartFilter.java
 * Package Name:com.gggw.filter
 * Date:2016-6-23上午9:28:09
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
 */

package com.gggw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gggw.controller.base.BaseController;

/**
 * ClassName:StartFilter <br/>
 * Function: tomcat等 启动之前执行. <br/>
 * Reason:	 过滤. <br/>
 * Date:     2016-6-23 上午9:28:09 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StartFilter extends BaseController implements Filter {
		
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("StartFilter init()=============");
		
	}
	
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("StartFilter doFilter()=========== before ======chain.doFilter(req, res)");
		
		chain.doFilter(req, res); // 调用下一过滤器
		
		System.out.println("StartFilter doFilter()=========== after ======chain.doFilter(req, res)");
		
	}

	
	public void destroy() {
		
		System.out.println("StartFilter destroy()===========");
		
	}

}

