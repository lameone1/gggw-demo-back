package com.gggw.service.counter;

import com.gggw.util.PropertiesUtils;
/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2016/8/17<br>
 */
public abstract class AbstractKospService {
    private static final String kospRunMode = PropertiesUtils.get("kosp.run.mode", "");

    protected  Boolean isRunMode(){
        return !kospRunMode.equals("debug");
    }
}
