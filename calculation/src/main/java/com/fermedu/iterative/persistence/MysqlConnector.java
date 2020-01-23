package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 16:37
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface MysqlConnector {

    List<FormulaTrait> findAll();

    List<FormulaTrait> deleteAndSaveAll(List<FormulaTrait> formulaTraitList);

    void saveOne(FormulaTrait formulaTrait);

    void saveResultForOneSample(SampleData sampleData, int loop, FormulaTrait formulaTrait);

}
