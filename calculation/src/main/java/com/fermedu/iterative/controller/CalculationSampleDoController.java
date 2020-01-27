package com.fermedu.iterative.controller;

import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.persistence.SampleDataArranger;
import com.fermedu.iterative.properties.IterativePathProperties;
import com.fermedu.iterative.service.TaskScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-27 23:53
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RestController
@Slf4j
@RequestMapping(value = {"/calculation/do/sample"})
public class CalculationSampleDoController {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private SampleDataArranger sampleDataArranger;

    @Autowired
    private IterativePathProperties pathProperties;

    @GetMapping(value = {"/all"})
    public void sampleAll() {
        taskScheduler.runAllSamples();
    }

    @GetMapping(value = {"/one"})
    public void sampleOne(@RequestParam(value = "sampleName",defaultValue = "1") String sampleId) {
        String observedDataCsvFilePath = pathProperties.getObservedDataCsvFilePath();
        SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName(observedDataCsvFilePath, sampleId);
        taskScheduler.runOneSample(sampleData);
    }
}
