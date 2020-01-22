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
 * @Create: 2020-01-22 15:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class IterativeCalculationParamSuggestionPropertiesTest {


    @Autowired
    private IterativeCalculationParamSuggestionProperties properties;

    @Test
    void testProperties() {
        System.out.println(JsonUtil.toJson(properties));

    }

}