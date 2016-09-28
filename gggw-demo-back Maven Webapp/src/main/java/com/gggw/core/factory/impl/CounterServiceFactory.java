package com.gggw.core.factory.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gggw.core.factory.ConfigedFactory;
import com.gggw.core.factory.ICounterService;
import com.gggw.core.utils.SpringContext;
import com.gggw.result.SisapResult;
import com.gggw.service.counter.AbstractHsIfsService;
import com.gggw.service.counter.AbstractKospService;
import com.gggw.service.counter.AbstractLiveBosService;
import com.gggw.service.counter.Counter;
import com.gggw.util.PropertiesUtils;

/**
 * 功能说明: 第三方服务工场(根据不同的第三方服务创建不同的处理类)<br>
 * 			    备注：
 * 					1.这里针对相同功能的第三方服务，比如柜台。如果不同功能，比如auth认证，则需要多建一个工厂。
 * 						private CounterServiceFactory counterFactory;
 * 						private AuthLoginFactory authLoginFactory; 
 * 					2.如果第三方服务功能单一，建议直接使用以下创建工厂的方式:
 * 					     这样的方式就不需要重写getBean方法，但是需要创建每个详细的实现类，比如configMapping.put("tfzq_token", TfzqTokenLoginServiceImpl.class);// 天风token服务器，具体逻辑放在实现类中
 * 						public class AuthLoginFactory extends ConfigedFactory<IAuthLoginService> implements IAuthLoginService
 * 						@Override
 *						public SisapResult authLogin(LoginForm form) {
 *							return getBean().authLogin(form);
 *						}
 * 系统版本: @version 1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2016-9-11 下午5:04:30<br>
 */
@SuppressWarnings("all") 
@Service
public class CounterServiceFactory extends ConfigedFactory<ICounterService> {

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
	
	/**
	 *  这里多加个第三方服务就要改这里的代码
	 *  Any better way ?
	 *  
	 *  In my opinion：
	 *  	1.name使用类名首字母小写的形式
	 *  	2.同时去实现一个接口Counter
	 *  	3.通过springContext.getBeansOfType(Counter);获取到相应类
	 *  	4.遍历相应类放入相应类类型
	 *  Result:
	 *  	1.因为抽象类没有自动注入（@Service） 所以无法使用springContext  
	 *  	2.如果自己写的springContext.getBeansOfType(Counter),只能获取最底层的子类，无法获取到抽象类
	 *  	3.如果同spring ApplicationContext的getBeansOfType,会获取所有实现Counter接口的类
	 *  	4.如果写个工具类获取所有实现Counter的接口(这里是所有,不太合适)	 -- ClassUtil
	 */
	@Override
	protected Map<String, Class> getConfigMapping() {
		if (null == configMapping) {
			configMapping = new HashMap<String, Class>();
			configMapping.put(CONFIG_KEY_HS, AbstractHsIfsService.class);
			configMapping.put(CONFIG_KEY_KD, AbstractKospService.class);
			configMapping.put(COFNIG_KEY_LB, AbstractLiveBosService.class);
		}
		return configMapping;
	}

	public SisapResult excute(String userName, Map<String, Object> params, Class<? extends ICounterService> clazz) {
		ICounterService counter = getBean(clazz);
		if (counter == null) {
			logger.error("未获取到{}的实现类,请检查系统配置项[{}]", clazz.getName(), CONFIG_KEY);
		}
		return counter.excute(userName, params);
	}
	
	public ICounterService getBean(Class<? extends ICounterService> clazz) {
		String configValue = PropertiesUtils.get(CONFIG_KEY);//"oosKospTellerCounter";
		if (StringUtils.isBlank(configValue)) {
			logger.info("当前配置项的{}为空,默认将加载对接恒生的实现", CONFIG_KEY);
			configValue = CONFIG_KEY_HS;
		}
		
		/**
		 * why Class<ICounterService>  (abstract factory)  		   --2016.09.01
		 * 
		 * 这里在编译器只是提示警告，生成字节码的时候无泛型，故也不会报错。   --2016.09.27
		 * In my opinion：
		 * 			Class configClass = getConfigMapping().get(configValue);
		 */
		Class<ICounterService> configClass = getConfigMapping().get(configValue);
		Map<String, ICounterService> map = springContext.getBeansOfType(configClass);
		for (Map.Entry<String, ICounterService> entry : map.entrySet()) {
			ICounterService bean = entry.getValue();
			//入参  判断类类型
			if (clazz.isAssignableFrom(bean.getClass())) {
				return bean;
			}
		}
		return null;
	}
}
