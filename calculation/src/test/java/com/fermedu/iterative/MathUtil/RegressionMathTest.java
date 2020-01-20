package com.fermedu.iterative.MathUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 23:29
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RegressionMathTest {

    private final static double[] yActualArray = {1d, 1.4d, 3d};
    private final static double[] yPredictedArray = {1d, 2d, 3d};

    private final static RegressionMath regressionMath = new RegressionMath(yActualArray, yPredictedArray);

    @Test
    public void coefficientOfDeterminationTest() {
        double result = regressionMath.coefficientOfDetermination();
        System.out.println(result);
        Assert.assertTrue(Math.abs(result - 1d) < 0.101d);
    }
}