package com.fermedu.iterative.jpa;

import com.fermedu.iterative.entity.FormulaTraitEntity;
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
 * @Create: 2020-01-22 18:11
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class FormulaTraitRepositoryTest {

    @Autowired
    private FormulaTraitRepository repository;

    @Test
    void testOnce() {
        List<FormulaTraitEntity> entityList = repository.findAll();
        System.out.println(JsonUtil.toJson(entityList));

    }

    @Test
    void saveAllTest() {
        FormulaTraitEntity formulaTrait1 = new FormulaTraitEntity();
        FormulaTraitEntity formulaTrait2 = new FormulaTraitEntity();

        List<FormulaTraitEntity> formulaTraitList = Arrays.asList(formulaTrait1, formulaTrait2);
        formulaTraitList.stream().forEach(entity -> entity.setLagTime(2));
        repository.saveAll(formulaTraitList);

    }

}