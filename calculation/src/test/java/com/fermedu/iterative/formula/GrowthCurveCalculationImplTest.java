package com.fermedu.iterative.formula;

import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.persistence.SampleDataArranger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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
    void run() {

        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("1");
        growthCurveCalculation.run(sampleData);
    }
}