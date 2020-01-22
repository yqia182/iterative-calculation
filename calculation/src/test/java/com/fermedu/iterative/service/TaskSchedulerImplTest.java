package com.fermedu.iterative.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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
    private TaskScheduler taskScheduler;

    @Test
    void runAllSamples() {
        taskScheduler.runAllSamples();
    }
}