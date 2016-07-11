package com.gggw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * ClassName: LoginHandlerInterceptor <br/>
 * Function: 校验用户是否登录拦截器. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016-6-24 下午2:33:19 <br/>
 *
 * @author cgw
 * @version 
 * @since JDK 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("LoginHandlerInterceptor  preHandle()===========登录拦截");
		
		return true;
	}
	
	
	/** 
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("LoginHandlerInterceptor  postHandle()===========");
	}
	
	
	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		System.out.println("LoginHandlerInterceptor  afterCompletion()===========");
		
	}
}
