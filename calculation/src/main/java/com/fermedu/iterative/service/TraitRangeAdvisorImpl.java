package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 13:27
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class TraitRangeAdvisorImpl implements TraitRangeAdvisor {

    private List<FormulaTrait> reverseCoefficientOrder(List<FormulaTrait> formulaTraitList) {

        Collections.sort(formulaTraitList);


        return formulaTraitList;
    }

    private List<FormulaTrait> selectPercentile(List<FormulaTrait> formulaTraitListReversed, double percent) {
        List<FormulaTrait> formulaTraitListResult = new ArrayList<>();
        for (int index = 0; index < formulaTraitListReversed.size() * percent; index++) {
            formulaTraitListResult.add(formulaTraitListReversed.get(index));
        }

        return formulaTraitListResult;
    }

    @Override
    public List<FormulaTrait> selectBestCoefficient(List<FormulaTrait> formulaTraitList) {
        final List<FormulaTrait> initialFormulaTraitList = formulaTraitList;
        final List<FormulaTrait> formulaTraitListReversed = this.reverseCoefficientOrder(initialFormulaTraitList);
        final List<FormulaTrait> formulaTraitListSelected = this.selectPercentile(formulaTraitListReversed, 0.3d);
        return formulaTraitListSelected;
    }
}
