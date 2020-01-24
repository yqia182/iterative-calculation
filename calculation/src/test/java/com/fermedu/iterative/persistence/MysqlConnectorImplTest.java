package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    void deleteAllTrait() {

        mysqlConnector.deleteAllTrait();
    }

    @Test
    void saveOne() {
        FormulaTrait formulaTrait1 = new FormulaTrait(100, 0.01, 0, 1.6);
        mysqlConnector.saveOne(formulaTrait1);

    }

    @Test
    void saveResultTest() {
        SampleData sampleData = new SampleData();
        sampleData.setYName("1231");
        FormulaTrait formulaTrait = new FormulaTrait(100d, 0.01d, 0d, 1.5d);

        mysqlConnector.saveResultForOneSample(sampleData,0, formulaTrait);

    }

    @Test
    void logFinalResultsAndDeleteTempResult() {
        SampleData sampleData = new SampleData();

        mysqlConnector.logFinalResultsAndDeleteTempResult(sampleData,10);
    }
}