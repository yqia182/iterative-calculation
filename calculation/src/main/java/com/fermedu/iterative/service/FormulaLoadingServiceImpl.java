package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 13:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class FormulaLoadingServiceImpl implements FormulaLoadingService {

    private FormulaTrait formulaTrait;

    private void initFormula() {

        // TODO : change
        this.formulaTrait.setLagTime(200d);
        this.formulaTrait.setRate(5d);

    }

    @Override
    public FormulaTrait load() {
        this.initFormula();
        return this.formulaTrait;
    }
}
