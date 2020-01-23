package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-23 16:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface ResultHolder {

    void saveResultForOneSample(SampleData sampleData, int loop, List<FormulaTrait> formulaTraitList);
}
