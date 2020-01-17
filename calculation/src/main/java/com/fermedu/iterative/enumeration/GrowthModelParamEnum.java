package com.fermedu.iterative.enumeration;

import com.fermedu.iterative.dao.VariableParam;

import java.math.BigDecimal;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-17 15:11
 * @Author: JustThink
 * @Description: 生长模型未知数枚举
 * @Include:
 **/
public enum GrowthModelParamEnum {
    MAX_GROWTH_RATE(new VariableParam("Maximum Growth Rate", BigDecimal.valueOf())),
    ;


    private VariableParam variableParam;

    public GrowthModelParamEnum(VariableParam variableParam) {
        this.variableParam = variableParam;
    }
}
