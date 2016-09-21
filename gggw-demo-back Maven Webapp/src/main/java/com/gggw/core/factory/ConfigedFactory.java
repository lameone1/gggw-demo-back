package com.gggw.core.factory;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gggw.core.utils.SpringContext;
import com.gggw.util.PropertiesUtils;

/**
 * 功能说明: 配置工厂基类<br>
 * 系统版本: @version 1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2016-9-11 下午1:59:36<br>
 */
public abstract class ConfigedFactory {
	public static Logger logger = LoggerFactory.getLogger(ConfigedFactory.class);
	
	@Autowired
	private SpringContext springContext;
	
	protected abstract String getConfigKey();
	
	protected abstract Map<String, Class> getConfigMapping();
	
	/**
	 * 功能说明: 获取配置, 为空则抛出异常<br>
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-9-11 下午5:02:08<br>
	 */
	protected <T> T getBean() {
		String config = PropertiesUtils.get(getConfigKey());
		T bean = (T)springContext.getBean(getConfigMapping().get(config));
		if (bean == null) {
			logger.error("系统配置项[{}={}],但获取不到相应的实现类,请检查系统配置", getConfigKey(), config);
			throw new NullPointerException("系统配置有误导致空指针异常");
		}
		return bean;
	}
	
	/**
	 * 默认配置为空,取默认值
	 * @author hufeng
	 * @param <T>
	 * @param defaultConfigKey
	 * @return
	 */
	protected <T> T getBean(String defaultConfigValue) {
		String config = PropertiesUtils.get(getConfigKey());
		if (StringUtils.isBlank(config)) {
			logger.info("当前系统配置的{}为空,默认赋值为[{}]", getConfigKey(), defaultConfigValue);
			config = defaultConfigValue;
		}

		T bean = (T)springContext.getBean(getConfigMapping().get(config));
		if (bean == null) {
			logger.error("系统配置项[{}={}],但获取不到相应的实现类,请检查系统配置", getConfigKey(), config);
			throw new NullPointerException("系统配置有误导致空指针异常");
		}
		return bean;
	}
	
}
