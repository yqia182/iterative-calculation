package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void saveAll() {
    }

    @Test
    void saveOne() {
    }
}