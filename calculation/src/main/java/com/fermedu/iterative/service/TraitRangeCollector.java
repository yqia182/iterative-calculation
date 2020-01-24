package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 12:26
 * @Author: JustThink
 * @Description: 提供/保存参数的范围。Provide/save params range
 * @Include:
 **/
public interface TraitRangeCollector {
    List<FormulaTrait> loadTraitList(int loop);

    List<FormulaTrait> saveToTraitList(FormulaTrait formulaTrait);

}
