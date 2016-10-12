package com.gggw.controller.system;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gggw.system.service.IImageCodeService;
import com.gggw.util.CookieUtil;

/**
 * ClassName:VerificationCodeAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-12 上午9:51:12 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
public class VerifyCodeAction {
	@Autowired
	private IImageCodeService verifyCodeService;
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(VerifyCodeAction.class);
	
	@RequestMapping("imageCode.img")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
		String verifyCode="";
		try {
			// 生成随机验证码内容   
			verifyCode= verifyCodeService.getRandString();
			String sessionId=CookieUtil.getCookie(request, CookieUtil.COOKIE_GGGW_SESSION_ID);
			// 存入redis，以便集群服务器均可获取，3分钟过期
			verifyCodeService.saveValidateCode(verifyCode, sessionId);
			BufferedImage bufferedImage = verifyCodeService.createVerifyCodeImage(verifyCode);
			response.setContentType("image/png");  
		    OutputStream os = response.getOutputStream();  
		    ImageIO.write(bufferedImage, "png", os);
		    os.close();
		} catch (Exception e) {
			logger.error("imageCode.img is error!", e);
		}
	}
}

