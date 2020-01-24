package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-24 12:58
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface PercentileSelect {
    List<FormulaTrait> selectBestCoefficient(List<FormulaTrait> formulaTraitList);

}
