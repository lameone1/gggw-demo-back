package com.gggw.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.gggw.util.FastJsonUtil;
import com.gggw.util.MapUtil;

/**
 * 功能说明: 中台服务结果集<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2015年7月17日<br>
 */
public class SisapResult implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(SisapResult.class);

	public static final String SUCCESS = "0";
	private String error_no;
	private String error_info;
	private Map<String, String> resultMap;// 用于非结果集返回
	private List<Map<String, String>> resultList;// 用于结果集返回

	public SisapResult() {
		resultMap = new HashMap<String, String>();
		resultList = new ArrayList<Map<String, String>>();
	}

	public SisapResult(String error_no, String error_info) {
		this.error_no = error_no;
		this.error_info = error_info;
		resultMap = new HashMap<String, String>();
		resultList = new ArrayList<Map<String, String>>();
	}

	public SisapResult(String error_no, String error_info, Map<String, String> resultMap, List<Map<String, String>> resultList) {
		this.error_no = error_no;
		this.error_info = error_info;
		this.resultMap = resultMap != null ? resultMap : new HashMap<String, String>();
		this.resultList = resultList != null ? resultList : new ArrayList<Map<String, String>>();
	}

	/**
	 * 解析json格式的结果串为对象
	 * @param jsonStr
	 */
	public SisapResult(String jsonStr) {
		JSONObject json = JSONObject.parseObject(jsonStr);
		this.setError_no(json.getString("error_no"));
		this.setError_info(json.getString("error_info"));
		resultList = new ArrayList<Map<String, String>>();
		if (json.containsKey("resultList") && StringUtils.isNoneBlank(json.getString("resultList"))) {
			this.setResultList(FastJsonUtil.parseObject(json.getString("resultList"), resultList.getClass()));
		}
		resultMap = new HashMap<String, String>();
		if (json.containsKey("resultMap") && StringUtils.isNoneBlank(json.getString("resultMap"))) {
			this.setResultMap(FastJsonUtil.parseObject(json.getString("resultMap"), resultMap.getClass()));
		}
		String[] headers = new String[] {"error_no", "error_info", "resultList", "resultMap"};
		for (Entry<String, Object> entry : json.entrySet()) {
			if (!ArrayUtils.contains(headers, entry.getKey())) {
				resultMap.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
	}

	public String getError_no() {
		return error_no;
	}

	public void setError_no(String error_no) {
		this.error_no = error_no;
	}

	public String getError_info() {
		return error_info;
	}

	public void setError_info(String error_info) {
		this.error_info = error_info;
	}

	public void setResultList(List<Map<String, String>> resultList) {
		this.resultList = resultList;
	}

	public boolean isSuccess() {
		return StringUtils.equals(error_no, "0");
	}

	public List<Map<String, String>> getResultList() {
		if (isSuccess())
			return resultList;
		else
			return null;
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	/**
	 * 以指定对象形式获取单条数据，结果集返回时优先取结果集数据，非结果集返回时取平行数据
	 * @param clazz
	 * @return
	 */
	public <T> T getData(Class<T> clazz) {
		Map<String, String> dataMap = null;
		if (resultList != null && resultList.size() > 0) {
			dataMap = resultList.get(0);
		} else if (resultMap != null && !resultMap.isEmpty()) {
			dataMap = resultMap;
		}
		if (dataMap != null && !dataMap.isEmpty()) {
			return MapUtil.map2Object(clazz, dataMap);
		}
		return null;
	}

	/**
	 * 以指定对象形式获取结果集数据，适用于结果集返回
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getResultList(Class<T> clazz) {
		if (isSuccess()) {
			List<T> resultList = new ArrayList<T>();
			if (getResultList() != null && getResultList().size() > 0) {
				for (Map<String, String> map : getResultList()) {
					resultList.add(MapUtil.map2Object(clazz, map));
				}
			}
			return resultList;
		} else {
			return null;
		}
	}
}
