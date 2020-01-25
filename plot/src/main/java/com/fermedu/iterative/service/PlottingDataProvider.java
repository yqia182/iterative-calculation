package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.entity.FinalResultPermanentEntity;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-25 16:31
 * @Author: JustThink
 * @Description: provide observed data array and predicted data array
 * @Include:
 **/
public interface PlottingDataProvider {
    SampleData getObservedData(String sampleName);

    SampleData getPredictedData(String sampleName);

    List<String> getSampleNamesFromCsv();

    List<String> getSampleNamesFromMysql();
}
