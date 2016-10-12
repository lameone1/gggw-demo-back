/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:LoginController.java
 * Package Name:com.gggw.controller.system
 * Date:2016-6-24下午2:35:29
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.controller.system;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gggw.util.CookieUtil;
import com.gggw.util.FastJsonUtil;
import com.gggw.util.PageData;
import com.gggw.util.jedis.RedisClientUtil;
import com.gggw.controller.base.BaseController;
import com.gggw.core.factory.impl.CounterServiceFactory;
import com.gggw.service.counter.service.CounterService0002;
import com.gggw.service.system.SysUserService;
import com.gggw.system.service.IImageCodeService;

/**
 * ClassName:LoginController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-24 下午2:35:29 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

@Controller
public class LoginController extends BaseController{
	
	/**
	 * @Resource  
	 * 			默认使用 byName装配，也可使用 byType（@Resource(type="ISserService")） 
	 * 			等效于
	 * 				 @Autowired
	 *				 @Qualifier("sysUserService")
	 *			属于 JSR 250 (@Resource @PostConstruct @PreDestroy)
	 *
	 * @Autowired
	 * 			使用byType装配
	 * 		WARNNING： 当多个实现类实现统一接口时，抛出 "NoSuchBeanDefinitionException"异常
	 * 					
	 * 			
	 */
	@Resource(name="sysUserService")
	private SysUserService sysUserService;
	
	@Autowired
	private CounterServiceFactory counterFactory;
	@Autowired
	private IImageCodeService verifyCodeService;
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="login_login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = sysUserService.findByUserNo(pd);
		//System.out.println(getRequestPostStr(request));
		map.put("error_no", "0");
		counterFactory.excute("cccgw", null, CounterService0002.class);
		
		CookieUtil.setCookie(response, CookieUtil.COOKIE_GGGW_SESSION_ID, UUID.randomUUID().toString(), true);
		
		return FastJsonUtil.toJSONString(pd);
	}
	
	/**
	 * 测试redis
	 */
	@RequestMapping(value="redisTest")
	@ResponseBody
	public Object redisTest(HttpServletRequest request)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		RedisClientUtil.set(pd.getString("regist_user_email"), pd.getString("regist_user_no"));
		map.put("regist_user_no", RedisClientUtil.get("502269006@qq.com"));
		return FastJsonUtil.toJSONString(map);
	}
	
	/**
	 * 测试cookie
	 */
	@RequestMapping(value="cookieTest")
	@ResponseBody
	public Object cookieTest(HttpServletRequest request)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String cookieString = CookieUtil.getCookie(request, CookieUtil.COOKIE_GGGW_SESSION_ID);
		map.put("cookie_string", cookieString);
		return FastJsonUtil.toJSONString(map);
	}
	
	/**
	 * 校验验证码
	 */
	@RequestMapping(value="checkCookie")
	@ResponseBody
	public Object checkCookie(HttpServletRequest request)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (verifyCodeService.checkVerifycode(pd.getString("verify_code"), CookieUtil.getCookie(request, CookieUtil.COOKIE_GGGW_SESSION_ID))) {
			map.put("result_info", "正确");
		} else {
			map.put("result_info", "错误");
		}
		
		
		return FastJsonUtil.toJSONString(map);
	}
	
	
	
	
	
	//=========================================  tool Functions  start  ===========================================//
	
	/**       
     * 描述:获取 post 请求内容 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public static String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
    }  

    /**       
     * 描述:获取 post 请求的 byte[] 数组 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException       
     */  
    public static byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,  
                    contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    }  
    
	//=========================================  tool Functions  end  ===========================================//
}

