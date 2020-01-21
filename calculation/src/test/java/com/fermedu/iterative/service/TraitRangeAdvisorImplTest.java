package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 14:55
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class TraitRangeAdvisorImplTest {


    @Autowired
    private TraitRangeAdvisor traitRangeAdvisor;


    @Test
    void selectBestCoefficient() {
        List<FormulaTrait> formulaTraitList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            FormulaTrait formulaTrait = new FormulaTrait();
            formulaTrait.setCoefficient(i);
            formulaTraitList.add(formulaTrait);
        }

        System.out.println(formulaTraitList);

        List<FormulaTrait> formulaTraitListResult = traitRangeAdvisor.selectBestCoefficient(formulaTraitList);

        System.out.println(formulaTraitListResult);


    }
}