package com.fermedu.iterative.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 14:12
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class LeastSquareMethodTest {

    private static void testLeastSquareMethodFromApache() {

        final WeightedObservedPoints obs = new WeightedObservedPoints();
        obs.add(-3, 4);
        obs.add(-2, 2);
        obs.add(-1, 3);
        obs.add(0, 0);
        obs.add(1, -1);
        obs.add(2, -2);
        obs.add(3, -5);

        // Instantiate a third-degree polynomial fitter.
        final PolynomialCurveFitter fitter = PolynomialCurveFitter.create(3);

        // Retrieve fitted parameters (coefficients of the polynomial function).
        final double[] coeff = fitter.fit(obs.toList());
        for (double c : coeff) {
            System.out.println(c);
        }

    }
}