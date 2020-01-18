package com.fermedu.iterative;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 17:56
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class GrowthCurveTest {

    private final static double[] X_LIST = new double[]{0.5, 1.0, 1.5, 2.0, 2.5, 3.0};

    private final static double[] Y_LIST = new double[]{1.75, 2.45, 3.81, 4.8, 7.0, 8.6};

    @Test
    void run() {
        GrowthCurve calculationTest = new GrowthCurve(this.X_LIST, this.Y_LIST);
        calculationTest.run();

    }
}