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

        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("","1");
        FormulaTrait formulaTraitResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait,sampleData);
        System.out.println(JsonUtil.toJson(formulaTraitResult));
    }


    @Test
    void demoSample() {
//        "",     "1","RS",              "lag",            "mumax",         "eff"
//        "S288CA",0,-0.0036577398336581,4.79754629364529,0.140945883387029,0.727719018232605

        FormulaTrait formulaTrait = new FormulaTrait();
        formulaTrait.setLagTime(4.79754629364529 * 24);
        formulaTrait.setMaxOD(0.678d);
        formulaTrait.setMinOD(0.0d);
        formulaTrait.setRate(0.140945883387029 / 24);

//        formulaTrait.setLagTime(4.79754629364529);
//        formulaTrait.setMaxOD(0.678d);
//        formulaTrait.setMinOD(0.0d);
//        formulaTrait.setRate(0.140945883387029);

        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("D:\\Desktop\\15degcurves.csv","S288CA");
        FormulaTrait formulaTraitResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait,sampleData);
        System.out.println(JsonUtil.toJson(formulaTraitResult));
    }

    @Test
    void demoSampleS288CB() {
//        "",     "1","RS",              "lag",            "mumax",         "eff"
//        "S288CA",0,-0.0036577398336581,4.79754629364529,0.140945883387029,0.727719018232605

        FormulaTrait formulaTrait = new FormulaTrait();
        formulaTrait.setLagTime(4.504909874 * 24);
        formulaTrait.setMaxOD(0.678d);
        formulaTrait.setMinOD(0.0d);
        formulaTrait.setRate(0.139264493/ 24);


        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("D:\\Desktop\\15degcurves.csv","S288CB");
        FormulaTrait formulaTraitResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait,sampleData);
        System.out.println(JsonUtil.toJson(formulaTraitResult));
    }

    @Test
    void calculateOncePerGompertzFormulaTest() {
        GrowthCurveCalculationImpl growthCurveCalculation = new GrowthCurveCalculationImpl();
        final double lag = 668.1152615;
        final double rate = 0.005393962;
        final double minOD = 0.029394609;
        final double maxOD = 1.628621484;

        final double time = lag * 2;


        double yvalue = growthCurveCalculation.calculateOncePerGompertzFormula(lag, rate, maxOD, minOD, time);
        System.out.println(yvalue);
        double maxTimes = maxOD * 0.06599;
        System.out.println(maxTimes);

    }
}