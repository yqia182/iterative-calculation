package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.MethodStep;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-14 15:39
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface CalculationService {
    void doCalculate(List<MethodStep> methodStepList);
}
