package com.fermedu.iterative.formula;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.persistence.SampleDataArranger;
import com.fermedu.iterative.util.JsonUtil;
import org.apache.commons.math3.util.FastMath;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 17:47
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class GrowthCurveCalculationImplTest {

    @Autowired
    @Qualifier(value = "growthCurveCalculationImpl")
    private GrowthCurveCalculation growthCurveCalculation;

    @Autowired
    private SampleDataArranger sampleDataArranger;

    @Test
    void tempOnce() {
        double result = FastMath.log(FastMath.E,10d);
        System.out.println(result);
    }

    @Test
    void run() {
        FormulaTrait formulaTrait = new FormulaTrait();
        formulaTrait.setLagTime(100d);
        formulaTrait.setMaxOD(1.6d);
        formulaTrait.setMinOD(0.0d);
        formulaTrait.setRate(0.00153333333d);

        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("1");
        FormulaTrait formulaTraitResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait,sampleData);
        System.out.println(JsonUtil.toJson(formulaTraitResult));
    }
}