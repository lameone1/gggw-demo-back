/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:LoginController.java
 * Package Name:com.gggw.controller.system
 * Date:2016-6-24下午2:35:29
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gggw.util.FastJsonUtil;
import com.gggw.util.PageData;
import com.gggw.controller.base.BaseController;
import com.gggw.service.system.SysUserService;

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
	
	@Resource(name="sysUserService")
	private SysUserService sysUserService;
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login_login")
	@ResponseBody
	public Object login(HttpServletRequest request)throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = sysUserService.findByUserNo(pd);		
		return FastJsonUtil.toJSONString(pd);
	}
	

}

