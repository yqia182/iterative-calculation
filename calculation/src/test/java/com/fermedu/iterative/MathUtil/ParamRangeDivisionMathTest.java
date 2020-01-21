package com.fermedu.iterative.MathUtil;

import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 00:42
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class ParamRangeDivisionMathTest {

    @Test
    void divideRangeFurtherToArray() {
        double[] testArray = new double[]{1.1,1.2,1.3,123.1,5412.1,123.1,0.1};

        double[] resultArray = ParamRangeDivisionMath.divideRangeFurtherToArray(testArray, 0.2);

        System.out.println(JsonUtil.toJson(resultArray));

    }
}