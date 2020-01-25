package com.fermedu.iterative.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-25 18:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class PlottingDataProviderImplTest {

    @Autowired
    private PlottingDataProvider plottingDataProvider;

    @Test
    void getObservedData() {

    }

    @Test
    void getPredictedData() {
        plottingDataProvider.getPredictedData("1");

    }

    @Test
    void getSampleNamesFromCsv() {
    }

    @Test
    void getSampleNamesFromMysql() {
    }
}