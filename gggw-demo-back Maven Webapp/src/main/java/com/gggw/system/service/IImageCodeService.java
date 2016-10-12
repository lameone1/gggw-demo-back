package com.gggw.system.service;

import java.awt.image.BufferedImage;

/**
 * ClassName:IImageCodeService <br/>
 * Function: TODO 验证码处理接口 <br/>
 * Date:     2016-10-12 上午9:22:58 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface IImageCodeService {
	/**
	 * 根据验证码生成图片
	 * @param verifyCode
	 * @return
	 */
	public BufferedImage createVerifyCodeImage(String verifyCode);
	
	/**
	 * 生成随机字符串
	 * @return 随机字符串
	 */
	public String getRandString();

	/**
	 * 缓存生成的随机验证码
	 * @param verifyCode
	 * @param sessionId
	 */
	public void saveValidateCode(String verifyCode, String sessionId);

	/**
	 * 验证填写的验证码是否正确
	 * @param verifyCode
	 * @param sessionId
	 * @return
	 */
	public boolean checkVerifycode(String verifyCode, String sessionId);
}

