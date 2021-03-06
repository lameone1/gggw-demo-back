/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:MyExceptionResolver.java
 * Package Name:com.gggw.resolver
 * Date:2016-6-24下午12:26:21
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.gggw.util.Logger;

/**
 * ClassName:MyExceptionResolver <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-24 下午12:26:21 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MyExceptionResolver implements HandlerExceptionResolver{
	protected Logger logger = Logger.getLogger(this.getClass());

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//记录错误日志。
		logger.error("Catch Exception: ",ex);
		ex.printStackTrace();		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}

