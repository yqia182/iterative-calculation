package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 18:16
 * @Author: JustThink
 * @Description: scheduler which has the power of getting sample data, optimizing params, doing calculation, etc.
 * @Include:
 **/
public interface TaskScheduler {
    void runOneSample(SampleData sampleData);

    void runAllSamples();
}
