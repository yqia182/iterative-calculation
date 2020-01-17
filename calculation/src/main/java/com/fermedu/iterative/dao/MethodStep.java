package com.fermedu.iterative.dao;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-17 14:26
 * @Author: JustThink
 * @Description: 每一步的方法
 * @Include:
 **/
@Data
public class MethodStep {

    private Object componentA;

    private Object componentB;

    private Method stepMethod;

    private Object stepResult;

    public MethodStep(Object componentA, Object componentB, Method stepMethod, Object stepResult) {
        this.componentA = componentA;
        this.componentB = componentB;
        this.stepMethod = stepMethod;
        this.stepResult = stepResult;
    }
}
