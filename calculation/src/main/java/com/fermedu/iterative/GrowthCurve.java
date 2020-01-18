package com.fermedu.iterative;

import com.fermedu.iterative.util.Jsonutil;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 17:26
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public class GrowthCurve {

    /** The series of the independent variable to fit in the formula */
    private double[] xList;
    /** The series of the dependent variable to fit in the formula */
    private double[] yList;

    private double[] coefficient;


    public GrowthCurve(double[] xList, double[] yList) {
        this.xList = xList;
        this.yList = yList;
    }

    /** Based on the formula given, calculate once */
    private void calculateOncePerFormula() {
        System.out.println(Jsonutil.toJson(this.xList));
        System.out.println(Jsonutil.toJson(this.yList));
    }

    /** 执行计算的主方法 */
    public void run() {
        this.calculateOncePerFormula();

    }
}
