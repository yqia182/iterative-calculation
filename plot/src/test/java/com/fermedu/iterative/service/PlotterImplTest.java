package com.fermedu.iterative.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-26 17:03
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class PlotterImplTest {

    @Autowired
    private Plotter plotter;

    @Test
    void plotBothObservedPredicted() {
        String sampleName = new String("1");
        plotter.plotBothObservedPredicted(sampleName);

    }
}