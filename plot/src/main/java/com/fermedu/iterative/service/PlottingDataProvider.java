package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;

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

    FormulaTrait getFormulaTrait(String sampleName);

    List<String> getSampleNames();

}
