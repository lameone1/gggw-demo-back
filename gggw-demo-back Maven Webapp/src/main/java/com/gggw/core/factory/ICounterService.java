package com.gggw.core.factory;

import java.util.Map;

import com.gggw.result.SisapResult;

public interface ICounterService {

	public SisapResult excute(String userName, Map<String, Object> params);
}
