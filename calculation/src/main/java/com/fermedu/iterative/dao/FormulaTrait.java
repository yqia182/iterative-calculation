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
public class FormulaTrait implements Comparable<FormulaTrait>{

    private double lagTime; // lag time

    private double rate; // maximum growth rate - μ max

    private double maxOD; // maxOD

    private double minOD; // minOD

    private double coefficient;

    /**
     * step for ordering, based on the level of coefficient
     * this method will put higher coefficient at better priority.
     * */
    @Override
    public int compareTo(FormulaTrait formulaTraitParam) {
        final Double thisCoefficient = new Double(this.coefficient);
        final Double coefficientParam = new Double(formulaTraitParam.getCoefficient());
        return coefficientParam.compareTo(thisCoefficient);
    }

}
