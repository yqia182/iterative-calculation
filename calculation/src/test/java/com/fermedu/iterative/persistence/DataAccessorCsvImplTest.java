package com.fermedu.iterative.persistence;

import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 15:15
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class DataAccessorCsvImplTest {

    @Autowired
    private DataAccessor dataAccessor;

    @Test
    void readOneSeries() {

        Object result = dataAccessor.readCvsWorksheet();
        System.out.println(JsonUtil.toJson(result));
    }
}