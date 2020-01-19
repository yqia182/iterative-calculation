package com.fermedu.iterative.formula;

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

    private double[] coefficient;


    /** Based on the formula given, calculate once */
    private void calculateOncePerFormula() {
        System.out.println(JsonUtil.toJson(this.sampleData.getXValueList()));
        System.out.println(JsonUtil.toJson(this.sampleData.getYValueList()));
    }

    /** 执行计算的主方法 */
    @Override
    public void run(SampleData sampleData) {
        this.sampleData = sampleData;
        this.calculateOncePerFormula();

    }
}
