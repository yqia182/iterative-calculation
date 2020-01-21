package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 12:41
 * @Author: JustThink
 * @Description: 传入一个FormulaRangeList，通过对coefficient of determination 排序，找出list中拟合最优的部分list，重新组合并且返回。
 * receive a formula range list. By reordering those according to coefficient of determination, find best portion of formula trait list, then return the list.
 * @Include:
 **/
public interface TraitRangeAdvisor {

    List<FormulaTrait> selectBestCoefficient(List<FormulaTrait> formulaTraitList);

    List<FormulaTrait> generateFormulaListByGivenParamRange(List<FormulaTrait> formulaTraitList);

}
