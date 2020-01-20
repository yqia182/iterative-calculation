package com.fermedu.iterative.MathUtil;

import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 17:36
 * @Author: JustThink
 * @Description: regression related calculation
 * @Include:
 **/
public class RegressionMath {

    private final double[] yActualArray;

    private final double[] yPredictedArray;

    public RegressionMath(double[] yActualArray, double[] yPredictedArray) {
        this.yActualArray = yActualArray;
        this.yPredictedArray = yPredictedArray;
    }

    private double meanOfActualArray() {
        final double meanOfActualArray = new Sum().evaluate(this.yActualArray);
        return meanOfActualArray;
    }

    private double totalSumOfSquares() {
        final double totalSumOfSquares = new SumOfSquares().evaluate(this.yActualArray);
        return totalSumOfSquares;
    }

    private double regressionSumOfSquares(double meanOfActualArray) {
        double regressionSumOfSquares = 0d;

        for (double yPredicted : this.yPredictedArray) {

            regressionSumOfSquares += Math.pow(yPredicted - meanOfActualArray,2);
        }

        return regressionSumOfSquares;

    }

    private double sumOfSquaresOfResiduals() {
        double sumOfSquaresOfResiduals = 0d;
        for (int index = 0; index < this.yPredictedArray.length; index++) {
            sumOfSquaresOfResiduals += Math.pow(this.yActualArray[index] - this.yPredictedArray[index], 2);
        }
        return sumOfSquaresOfResiduals;
    }

    public double coefficientOfDetermination() {
//        final double meanOfActualArray = this.meanOfActualArray(); // mean of the observed data
        final double totalSumOfSquares = this.totalSumOfSquares();

//        final double regressionSumOfSquares = this.regressionSumOfSquares(meanOfActualArray);
        final double sumOfSquaresOfResiduals = this.sumOfSquaresOfResiduals();

        final double coefficientOfDetermination = 1 - (sumOfSquaresOfResiduals / totalSumOfSquares);
        return coefficientOfDetermination;
    }
}
