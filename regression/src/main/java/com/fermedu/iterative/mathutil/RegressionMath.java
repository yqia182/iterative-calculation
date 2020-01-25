package com.fermedu.iterative.mathutil;

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

    private final double totalSumOfSquares;

    private final double sumOfSquaresOfResiduals;

    public RegressionMath(double[] yActualArray, double[] yPredictedArray) {
        this.yActualArray = yActualArray;
        this.yPredictedArray = yPredictedArray;
        final double meanOfActualArray = this.meanOfActualArray(yActualArray);
        this.totalSumOfSquares = this.totalSumOfSquares(yActualArray, meanOfActualArray);
        this.sumOfSquaresOfResiduals = this.sumOfSquaresOfResiduals(yActualArray, yPredictedArray);
    }

    /**
     * only called from the constructor
     */
    private double meanOfActualArray(double[] yActualArray) {
        final double mean = (new Sum().evaluate(yActualArray)) / (yActualArray.length);
        return mean;
    }

    /**
     * only called from the constructor
     */
    private double totalSumOfSquares(double[] yActualArray, double meanOfActualArray) {
        double totalSumOfSquares = 0d;
        for (double observed : this.yActualArray) {
            totalSumOfSquares += Math.pow((observed - meanOfActualArray), 2);
        }

        return totalSumOfSquares;
    }

    /**
     * only called from the constructor
     */
    private double sumOfSquaresOfResiduals(double[] yActualArray, double[] yPredictedArray) {
        double sumOfSquaresOfResiduals = 0d;

        for (int index = 0; index < this.yPredictedArray.length; index++) {
            sumOfSquaresOfResiduals += Math.pow(this.yActualArray[index] - this.yPredictedArray[index], 2);
        }
        return sumOfSquaresOfResiduals;
    }

    public double coefficientOfDetermination() {

        final double coefficientOfDetermination = 1 - (this.sumOfSquaresOfResiduals / this.totalSumOfSquares);
        return coefficientOfDetermination;
    }
}
