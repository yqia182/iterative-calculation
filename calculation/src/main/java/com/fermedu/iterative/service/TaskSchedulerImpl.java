package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 18:17
 * @Author: JustThink
 * @Description: scheduler which has the power of getting sample data, optimizing params, doing calculation, etc.
 * @Include:
 **/
@Service
@Slf4j
public class TaskSchedulerImpl implements TaskScheduler {
    private FormulaLoadingService formulaLoadingService;

    private FormulaTrait formulaTraitLoader() {
        return formulaLoadingService.load();
    }

    @Override
    public void run() {

    }
}
