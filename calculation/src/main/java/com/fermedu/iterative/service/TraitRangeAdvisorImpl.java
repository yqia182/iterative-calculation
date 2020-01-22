package com.fermedu.iterative.service;

import com.fermedu.iterative.MathUtil.ParamRangeDivisionMath;
import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.properties.IterativeCalculationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        final List<FormulaTrait> initialFormulaTraitList = formulaTraitList;
        final List<FormulaTrait> formulaTraitListReversed = this.reverseCoefficientOrder(initialFormulaTraitList);
        final List<FormulaTrait> formulaTraitListSelected = this.selectPercentile(formulaTraitListReversed);
        return formulaTraitListSelected;
    }

    /***
     * @Description for the received formulaTraitList (as it has been selected top xx percent of best coefficient)
     * this method will figure out the range of each parameter incl. lag, rate, etc,
     * and divide the range into xx aliquots (e.g. 100)
     * and return as a formula trait list.
     * @Params * @param formulaTraitList
     * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
     **/
    @Override
    public List<FormulaTrait> generateFormulaListByGivenParamRange(List<FormulaTrait> selectedFormulaTraitList) {
        /** how small each divided range will be
         * If granularity is 0.1d, each parameter,
         * e.g. lag will be divided into 1/0.1 +1 = 6 suggested new params */
        final double divisionGranularity = calculationProperties.getRangeDividerGranularity();
        // 算出每个参数的范围.
        // figure out the total range (pool), from which N aliquots of the parameter are suggested
        // 把每个参数等分
        // from the pool, based on the granularity, divide the pool into multiple aliquots

        final double[] lagArray = selectedFormulaTraitList.stream().map(formulaTrait -> formulaTrait.getLagTime()).collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();
        final double[] rateArray = selectedFormulaTraitList.stream().map(formulaTrait -> formulaTrait.getRate()).collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();
        final double[] minODArray = selectedFormulaTraitList.stream().map(formulaTrait -> formulaTrait.getMinOD()).collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();
        final double[] maxODArray = selectedFormulaTraitList.stream().map(formulaTrait -> formulaTrait.getMaxOD()).collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();

        final double[] lagFurtherDividedArray = ParamRangeDivisionMath.divideRangeFurtherToArray(lagArray,divisionGranularity);
        final double[] rateFurtherDividedArray = ParamRangeDivisionMath.divideRangeFurtherToArray(rateArray,divisionGranularity);
        final double[] minODFurtherDividedArray = ParamRangeDivisionMath.divideRangeFurtherToArray(minODArray,divisionGranularity);
        final double[] maxODFurtherDividedArray = ParamRangeDivisionMath.divideRangeFurtherToArray(maxODArray, divisionGranularity);

        // 把等分结果重新排列组合成 N^4 个结果，组成list返回
        // combine all aliquots from every parameters into a list
        List<FormulaTrait> formulaTraitResultList = new ArrayList<>();
        for (double lag : lagFurtherDividedArray) {
            for (double rate : rateFurtherDividedArray) {
                for (double minOD : minODFurtherDividedArray) {
                    for (double maxOD : maxODFurtherDividedArray) {
                        final FormulaTrait formulaTrait = new FormulaTrait(lag, rate, minOD, maxOD);
                        formulaTraitResultList.add(formulaTrait);
                    }
                }
            }
        }

        return formulaTraitResultList;
    }
}
