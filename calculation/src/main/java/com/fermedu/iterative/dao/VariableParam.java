package com.fermedu.iterative.dao;

import lombok.Data;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-17 14:58
 * @Author: JustThink
 * @Description: 未知数数据对象，待求数
 * @Include:
 **/
@Data
public class VariableParam implements ParamInterface{

    private String paramName;

    private Object paramValue;

    public VariableParam(String paramName, Object paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }
}
