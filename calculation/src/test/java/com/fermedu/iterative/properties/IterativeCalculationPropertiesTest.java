package com.fermedu.iterative.properties;

import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 16:34
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class IterativeCalculationPropertiesTest {

    @Autowired
    private IterativeCalculationProperties properties;

    @Test
    void getCoefficientPercentile() {
        System.out.println(properties);
        System.out.println(properties.getCoefficientSelectivePercentile());
    }

    @Test
    void getRangeDividerGranularity() {
        System.out.println(JsonUtil.toJson(properties));
        System.out.println(properties.getRangeDividerGranularity());

    }
}