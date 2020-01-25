package com.fermedu.iterative.formula;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 17:44
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface GrowthCurveCalculation {
    FormulaTrait calculateOneSampleSet(FormulaTrait formulaTrait, SampleData sampleData);

    SampleData predictFromSampleData(FormulaTrait formulaTrait, SampleData sampleData);


}
