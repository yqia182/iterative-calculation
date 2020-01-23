package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 18:10
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class MysqlConnectorImplTest {

    @Autowired
    private MysqlConnector mysqlConnector;

    @Test
    void findAll() {
        List<FormulaTrait> all = mysqlConnector.findAll();
        System.out.println(all);
    }

    @Test
    void deleteAll() {
        mysqlConnector.deleteAll();
        List<FormulaTrait> all = mysqlConnector.findAll();
        System.out.println(all);
    }

    @Test
    void saveAll() {
        FormulaTrait formulaTrait1 = new FormulaTrait(100, 0.01, 0, 1.6);
        FormulaTrait formulaTrait2 = new FormulaTrait(100, 0.02, 0, 1.6);
        FormulaTrait formulaTrait3 = new FormulaTrait(100, 0.03, 0, 1.6);
        FormulaTrait formulaTrait4 = new FormulaTrait(100, 0.04, 0, 1.6);
        FormulaTrait formulaTrait5 = new FormulaTrait(100, 0.05, 0, 1.6);
        List<FormulaTrait> formulaTraitList = Arrays.asList(formulaTrait1, formulaTrait2, formulaTrait3, formulaTrait4
                , formulaTrait5);

        List<FormulaTrait> resultList = mysqlConnector.saveAll(formulaTraitList);
        System.out.println(JsonUtil.toJson(resultList));
    }

    @Test
    void saveOne() {
        FormulaTrait formulaTrait1 = new FormulaTrait(100, 0.01, 0, 1.6);
        mysqlConnector.saveOne(formulaTrait1);

    }
}