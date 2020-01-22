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

    @Test
    void generateFormulaListByGivenParamRange() {
        List<FormulaTrait> selectedFormulaTraitList = new ArrayList<>();

        FormulaTrait formulaTrait1 = new FormulaTrait(100, 0.1, 0.1231, 2.12);
        FormulaTrait formulaTrait2 = new FormulaTrait(104, 0.211, 0.01231, 2.123);
        FormulaTrait formulaTrait3 = new FormulaTrait(111, 0.121, 0.13321, 2.97);
        FormulaTrait formulaTrait4 = new FormulaTrait(112, 0.11, 0.08751, 2.1231);
        FormulaTrait formulaTrait5 = new FormulaTrait(132, 0.31, 0.121, 2.12);

        selectedFormulaTraitList.add(formulaTrait1);
        selectedFormulaTraitList.add(formulaTrait2);
        selectedFormulaTraitList.add(formulaTrait3);
        selectedFormulaTraitList.add(formulaTrait4);
        selectedFormulaTraitList.add(formulaTrait5);

        final List<FormulaTrait> formulaTraitResultList = traitRangeAdvisor.generateFormulaListByGivenParamRange(selectedFormulaTraitList);

        for (int i = 0; i <= 100; i++) {
            System.out.println(formulaTraitResultList.get(i));

        }


        System.out.println(formulaTraitResultList.size());
    }
}