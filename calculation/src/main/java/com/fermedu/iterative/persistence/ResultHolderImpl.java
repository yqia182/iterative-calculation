package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.properties.IterativeCalculationProperties;
import com.fermedu.iterative.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-23 16:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class ResultHolderImpl implements ResultHolder {
    @Autowired
    private MysqlConnector mysqlConnector;


    @Autowired
    private IterativeCalculationProperties calculationProperties;

    /***
     * @Description receives a sampledata and the formulatraitlist,
     * which has the coefficients calculated after iterative loops.
     * So the formulaTraitList here is seen to have the finally optimized
     * formulaTrait (with the best coefficient)
     *
     * Only needs to select the best out of the list and save.
     * @Params * @param sampleData
     * @param
     * @Return void
     **/
    @Override
    public boolean saveResultForOneSample(SampleData sampleData, int loop) {

        final List<FormulaTrait> allFormulaTrait = mysqlConnector.findAll();


        FormulaTrait bestCoefficientResult = allFormulaTrait.stream().max(Comparator.comparingDouble(FormulaTrait::getCoefficient)).orElse(new FormulaTrait());

        if (Math.abs(bestCoefficientResult.getCoefficient() - 0.0d) < 0.001d) {
            /** the best coefficient is very close to 0 */
            System.out.println("Warning ResultHolderImpl saveResultForOneSample might have failed finding the max coefficient,".concat(
                    "current formulaTraitList is : ").concat(JsonUtil.toJson(allFormulaTrait)));

            log.error("Warning ResultHolderImpl saveResultForOneSample might have failed finding the max coefficient,".concat(
                    "current formulaTraitList is : ").concat(JsonUtil.toJson(allFormulaTrait)));
        }

        mysqlConnector.saveResultForOneSample(sampleData, loop, bestCoefficientResult);

        return this.checkIfCoefSatisfied(bestCoefficientResult);
    }

    private boolean checkIfCoefSatisfied(FormulaTrait bestCoefficientResult) {
        final double threshold = calculationProperties.getSatisfiedCoefThreshold();
        if (bestCoefficientResult.getCoefficient() >= threshold) {
            System.out.println("STATUS: current formula:".concat(JsonUtil.toJson(bestCoefficientResult).concat("\n has reach satisfication level. Preparing to finalize current run.")));
            return true;
        }
        return false;
    }
}
