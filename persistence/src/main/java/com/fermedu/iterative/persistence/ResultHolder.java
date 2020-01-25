package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.SampleData;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-23 16:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface ResultHolder {

    boolean saveResultForOneSample(SampleData sampleData, int loop);
}
