package com.fermedu.iterative.formula;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 17:26
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class GrowthCurveCalculationImpl implements GrowthCurveCalculation {

    /** The series of the independent variable to fit in the formula */
    /**
     * The series of the dependent variable to fit in the formula
     */
    private SampleData sampleData;

    private FormulaTrait formulaTrait;


    /** Based on the formula given, calculate once */
    private void calculateOncePerFormula(double xOne, double yOne) {
        double yCalculated = 0d;


    }

    /** 执行计算的主方法 */
    @Override
    public void run(SampleData sampleData) {
        this.sampleData = sampleData;
        double xOne = 0d;
        double yOne = 0d;
        for (int xIndex = 0; xIndex < this.sampleData.getXValueList().size(); xIndex++) {
            xOne = this.sampleData.getXValueList().get(xIndex);
            yOne = this.sampleData.getYValueList().get(xIndex);
        }
        this.calculateOncePerFormula(xOne, yOne);

    }
}
