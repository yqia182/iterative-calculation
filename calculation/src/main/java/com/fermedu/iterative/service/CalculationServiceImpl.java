package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.MethodStep;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-14 15:39
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
public class CalculationServiceImpl implements CalculationService {
    @Override
    public void doCalculate(List<MethodStep> methodStepList) {
        for (MethodStep methodStep : methodStepList) {
            Object eachResult = 
        }
    }
}
