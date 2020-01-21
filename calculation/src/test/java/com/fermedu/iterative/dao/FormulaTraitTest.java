package com.fermedu.iterative.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 14:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class FormulaTraitTest {

    @Test
    void compareTo() {
        FormulaTrait formulaTrait1 = new FormulaTrait();
        FormulaTrait formulaTrait2 = new FormulaTrait();

        formulaTrait1.setCoefficient(3d);
        formulaTrait2.setCoefficient(3d);

        int result = formulaTrait1.compareTo(formulaTrait2);

        System.out.println("formulaTrait1.compareTo(formulaTrait2), result:".concat(String.valueOf(result)));
        Assert.assertEquals(0, result);

    }
}