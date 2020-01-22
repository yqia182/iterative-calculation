package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;

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

    void deleteAll();

    void saveAll(List<FormulaTrait> formulaTraitList);

    void saveOne(FormulaTrait formulaTrait);
}
