package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.properties.IterativeCalculationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-24 12:58
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class PercentileSelectImpl implements PercentileSelect {

    @Autowired
    private IterativeCalculationProperties calculationProperties;

    private List<FormulaTrait> reverseCoefficientOrder(List<FormulaTrait> formulaTraitList) {

        Collections.sort(formulaTraitList);


        return formulaTraitList;
    }

    private List<FormulaTrait> selectPercentile(List<FormulaTrait> formulaTraitListReversed) {
        List<FormulaTrait> formulaTraitListResult = new ArrayList<>();
        double percent = calculationProperties.getCoefficientSelectivePercentile();
        for (int index = 0; index < formulaTraitListReversed.size() * percent; index++) {
            formulaTraitListResult.add(formulaTraitListReversed.get(index));
        }

        return formulaTraitListResult;
    }

    /***
     * @Description select a percentile formula trait list according to the level of coefficient
     * only the list with relatively highest coefficient will be picked out.
     * @Params * @param formulaTraitList
     * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
     **/
    @Override
    public List<FormulaTrait> selectBestCoefficient(List<FormulaTrait> formulaTraitList) {
        final double maxCoefficient = formulaTraitList.stream().max(Comparator.comparingDouble(FormulaTrait::getCoefficient)).orElse(new FormulaTrait()).getCoefficient();
        if (maxCoefficient < 0.0001) {
            /** current max coefficient is zero, now it is initialization stage. */
            System.out.println("STATUS: Current max coefficient is zero. Now it is initialization stage. The coefficient list is not being selected for top coefficients.");
            return formulaTraitList;
        }

        final List<FormulaTrait> initialFormulaTraitList = formulaTraitList;
        final List<FormulaTrait> formulaTraitListReversed = this.reverseCoefficientOrder(initialFormulaTraitList);
        final List<FormulaTrait> formulaTraitListSelected = this.selectPercentile(formulaTraitListReversed);

        System.out.println("STATUS: The formulas have been selected for higher coefficients. ");

        return formulaTraitListSelected;
    }
}
