package com.fermedu.iterative.dao;

import lombok.Data;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 12:08
 * @Author: JustThink
 * @Description: All constants in the formula, such as lag time, rate, log e etc.
 * @Include:
 **/
@Data
public class FormulaTrait {

    private double lagTime;

    private double rate;



    private double coefficient;
}