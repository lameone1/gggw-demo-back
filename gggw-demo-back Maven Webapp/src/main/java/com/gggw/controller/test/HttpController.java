/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:HttpController.java
 * Package Name:com.gggw.controller
 * Date:2016-6-24下午12:34:42
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.controller.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:HttpController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-24 下午12:34:42 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
public class HttpController {
	
	@RequestMapping(value="/testDzh")
	@ResponseBody
	public Object login(HttpServletRequest request)throws Exception{
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("operatorTime", "20160517162504");
		parameter.put("ext", "mf-gXVrLHoOoaS-PbKpD7qYt5OVUT2AmOZxdIdJBJ*is7mpoFOVxCyp-dpo-n5yor4r8riV2mQGeiDbaYJVuTPRQ8PkaB26B-2pdaECkgjGEuxFonJ36cZyRtCisrYojt*mdGlebAv1IE9fEEFuqIQ..");
		parameter.put("currentStep", "3");
		parameter.put("version", "1.0");
		parameter.put("inputCharset", "1");
		parameter.put("signType", "1");
		parameter.put("traderNo", "10200");
		parameter.put("sourceNo", "128");
		parameter.put("clientId", "600007");
		parameter.put("osn", "32345010200320160517165904522");
		parameter.put("customerCode", "32345010200");
		parameter.put("mobile", "13245566677");
		
		
		URL url = new URL("http://uniformkh.cairenhui.com/record/insertRecord.json");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);

		conn.setInstanceFollowRedirects(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.connect();

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
		out.write(convertKeyValueFormat(parameter));
		out.flush();
		out.close(); // flush and close

		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
		StringBuilder result = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}

		reader.close();
		conn.disconnect();
		System.out.println(result.toString());
		return result.toString();
	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private static String convertKeyValueFormat(Map<String, String>  paramsMap) {
        Iterator itResult = paramsMap.entrySet().iterator();
        StringBuilder result = new StringBuilder();
        while(itResult.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) itResult.next();
            if (result.length() != 0) {
                result.append("&");
            }
            result.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return result.toString();
    }
}

