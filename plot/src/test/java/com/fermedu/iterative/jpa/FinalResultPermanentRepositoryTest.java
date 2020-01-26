package com.fermedu.iterative.jpa;

import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-26 17:28
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class FinalResultPermanentRepositoryTest {

    @Autowired
    private FinalResultPermanentRepository repository;

    @Test
    void findByYname() {
        List<FinalResultPermanentEntity> results = repository.findByYnameOrderByCoefficientDesc("1");
        System.out.println(JsonUtil.toJson(results));

    }
}