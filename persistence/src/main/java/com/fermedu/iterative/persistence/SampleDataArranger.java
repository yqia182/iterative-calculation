package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.SampleData;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 16:23
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface SampleDataArranger {
    List<String> readSampleNameList();

    SampleData readOneSampleDataSeriesByName(String yName);

}
