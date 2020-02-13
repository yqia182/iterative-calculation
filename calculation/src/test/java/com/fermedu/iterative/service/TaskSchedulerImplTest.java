package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.persistence.SampleDataArranger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 14:41
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class TaskSchedulerImplTest {

    @Autowired
    private SampleDataArranger sampleDataArranger;

    @Autowired
    private TaskScheduler taskScheduler;

    @Test
    void runOneSample() {
//        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("1");
        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName("D:\\Desktop\\1.csv","53");
        taskScheduler.runOneSample(sampleData);
    }

    @Test
    void runAllSamples() {
        taskScheduler.runAllSamples();
    }
}