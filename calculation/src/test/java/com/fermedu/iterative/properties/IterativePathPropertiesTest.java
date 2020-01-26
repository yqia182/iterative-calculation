package com.fermedu.iterative.properties;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-26 21:28
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class IterativePathPropertiesTest {

    @Autowired
    private IterativePathProperties pathProperties;

    @Test
    void getObservedDataCsvFilePath() {
        String result = pathProperties.getObservedDataCsvFilePath();
        System.out.println(result);

    }
}