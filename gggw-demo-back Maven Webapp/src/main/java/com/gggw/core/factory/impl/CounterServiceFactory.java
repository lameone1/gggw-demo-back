package com.gggw.core.factory.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gggw.core.factory.ConfigedFactory;
import com.gggw.core.factory.ICounterService;
import com.gggw.core.utils.SpringContext;
import com.gggw.result.SisapResult;
import com.gggw.service.counter.AbstractHsIfsService;
import com.gggw.service.counter.AbstractKospService;
import com.gggw.service.counter.AbstractLiveBosService;
import com.gggw.util.PropertiesUtils;

/**
 * 功能说明: 第三方服务工场<br>
 * 系统版本: @version 1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2016-9-11 下午5:04:30<br>
 */
public class CounterServiceFactory extends ConfigedFactory {

	private Logger logger = LoggerFactory.getLogger(CounterServiceFactory.class);
	
	@Autowired
	private SpringContext springContext;
	
	public static final String CONFIG_KEY_HS = "oosHSTellerCounter";
	public static final String CONFIG_KEY_KD = "oosKospTellerCounter";
	public static final String COFNIG_KEY_LB = "oosLiveBosTellerCounter";
	
	public static final String CONFIG_KEY = "interface.oos.counter.teller.bean.ref";
	
	private Map<String, Class> configMapping;
	
	@Override
	protected String getConfigKey() {
		return CONFIG_KEY;
	}
	
	//这里多加个第三方就要多加一次。有没有其他办法。
	@Override
	protected Map<String, Class> getConfigMapping() {
		if (null == configMapping) {
			configMapping = new HashMap<String, Class>();
			configMapping.put(CONFIG_KEY_HS, AbstractHsIfsService.class);
			configMapping.put(CONFIG_KEY_KD, AbstractKospService.class);
			configMapping.put(COFNIG_KEY_LB, AbstractLiveBosService.class);
		}
		return null;
	}

	public SisapResult excute(String userName, Map<String, Object> params, Class<? extends ICounterService> clazz) {
		ICounterService counter = getBean(clazz);
		if (counter == null) {
			logger.error("未获取到{}的实现类,请检查系统配置项[{}]", clazz.getName(), CONFIG_KEY);
		}
		return counter.excute(userName, params);
	}
	
	public ICounterService getBean(Class<? extends ICounterService> clazz) {
		String configValue = PropertiesUtils.get(CONFIG_KEY);
		if (StringUtils.isBlank(configValue)) {
			logger.info("当前配置项的{}为空,默认将加载对接恒生的实现", CONFIG_KEY);
			configValue = CONFIG_KEY_HS;
		}
		
		/**
		 * why ICounterService  (abstract factory)  	--2016.09.01
		 * 这里在编译器只是提示警告，生成字节码的时候无泛型，故也不会报错。 --2016.09.27
		 * In my opinion：
		 * 			Class configClass = getConfigMapping().get(configValue);
		 */
		Class<ICounterService> configClass = getConfigMapping().get(configValue);
		Map<String, ICounterService> map = springContext.getBeansOfType(configClass);
		for (Map.Entry<String, ICounterService> entry : map.entrySet()) {
			ICounterService bean = entry.getValue();
			//入参
			if (clazz.isAssignableFrom(bean.getClass())) {
				return bean;
			}
		}
		return null;
	}
}
