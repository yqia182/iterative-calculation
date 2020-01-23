package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
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

    /***
    * @Description receives a sampledata and the formulatraitlist,
     * which has the coefficients calculated after iterative loops.
     * So the formulaTraitList here is seen to have the finally optimized
     * formulaTrait (with the best coefficient)
     *
     * Only needs to select the best out of the list and save.
    * @Params * @param sampleData
* @param formulaTraitList
    * @Return void
    **/
    @Override
    public void saveResultForOneSample(SampleData sampleData, int loop, List<FormulaTrait> formulaTraitList) {
        FormulaTrait bestCoefficientResult = formulaTraitList.stream().max(Comparator.comparingDouble(FormulaTrait::getCoefficient)).orElse(new FormulaTrait());
        if (Math.abs(bestCoefficientResult.getCoefficient() - 0.0d) < 0.001d) {
            /** the best coefficient is very close to 0 */
            System.out.println("Warning ResultHolderImpl saveResultForOneSample might have failed finding the max coefficient,".concat(
                    "current formulaTraitList is : ").concat(JsonUtil.toJson(formulaTraitList)));

            log.error("Warning ResultHolderImpl saveResultForOneSample might have failed finding the max coefficient,".concat(
                    "current formulaTraitList is : ").concat(JsonUtil.toJson(formulaTraitList)));
        }

        mysqlConnector.saveResultForOneSample(sampleData, loop, bestCoefficientResult);
    }
}
