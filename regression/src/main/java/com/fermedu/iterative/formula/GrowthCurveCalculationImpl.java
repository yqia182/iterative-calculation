package com.fermedu.iterative.formula;

import com.fermedu.iterative.mathutil.RegressionMath;
import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.FastMath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 17:26
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class GrowthCurveCalculationImpl implements GrowthCurveCalculation {

    private final double ln10 = FastMath.log(FastMath.E, 10d);

    /** sample data series */
    private SampleData sampleData;

    /**
     * Based on the formula given, calculate once
     * It calculates only one set of sample data. xValueList to yValueList
     * It returns a FormulaTrait Object, which has the coefficient R^2 result.
     */
    private List<Double> calculateSeriesPerGompertzFormula(FormulaTrait formulaTrait) {
        /** init */
        List<Double> yPredictionList = new ArrayList<>();
        double coefficient = 0d; // coefficient of determination . R^2 or r^2 . for determining the power of the interpretability of the model
        final double lag = formulaTrait.getLagTime();// lag time
        final double mumax = formulaTrait.getRate();// max rate
        final double maxOD = formulaTrait.getMaxOD(); // maxOD
        final double minOD = formulaTrait.getMinOD(); // minOD
        /** end initiation */

        /** calculation begins */
        for (double time : this.sampleData.getXValueList()) {
            double yCalculated = this.calculateOncePerGompertzFormula(lag, mumax, maxOD, minOD, time);
            yPredictionList.add(yCalculated);
        }
        /** calculation ends */

        return yPredictionList;

    }

    /** calculation R^2 */
    private double calculateCoefficient(List<Double> yPredictedList) {
        final double[] yActualArray = this.sampleData.getYValueList().stream().mapToDouble(Double::doubleValue).toArray();
        final double[] yPredictedArray = yPredictedList.stream().mapToDouble(Double::doubleValue).toArray();
        final double coefficientOfDetermination = new RegressionMath(yActualArray, yPredictedArray).coefficientOfDetermination();
        return coefficientOfDetermination;
    }


    /** calculate the dependant variable (prediction of OD observation value based on the model) */
    private double calculateOncePerGompertzFormula(double lag, double mumax, double maxOD, double minOD, double time) {
        /** calculation begins */
        final double maxOD_minus_minOD = maxOD - minOD; // maxOD - minOD
        final double maxOD_minus_minOD_times_log10_plus_1 = maxOD_minus_minOD * ln10 + 1; // ((maxOD - minOD)*log10)+1
        final double mumax_times_e1_times_lag_minus_time = mumax * Math.E * (lag - time); // μmax*e(1)*(lag-t)
        final double exponential_result_1 = mumax_times_e1_times_lag_minus_time / maxOD_minus_minOD_times_log10_plus_1; // μmax*e(1)*(lag-t)/((maxOD - minOD)*log10)+1
        final double negative_e_exponential = - Math.pow(Math.E, exponential_result_1); // -e^(μmax*e(1)*(lag-t)/((maxOD - minOD)*log10)+1)
        final double e_exponential = Math.pow(Math.E, negative_e_exponential); // e^(-e^(μmax*e(1)*(lag-t)/((maxOD - minOD)*log10)+1))
        final double result = minOD + maxOD_minus_minOD * e_exponential;// mindOD + (maxOD - minOD) * e^(-e^(μmax*e(1)*(lag-t)/((maxOD - minOD)*log10)+1))
        return result;
        /** calculation ends */
    }



    /**
     * 执行计算的主方法 . 对于一个样品
     * for one sample (one replicate), each set contains a xValueList and a yValueList
     * @return
     */
    @Override
    public FormulaTrait calculateOneSampleSet(FormulaTrait formulaTrait,SampleData sampleData) {
        this.sampleData = sampleData;
        List<Double> yPredictionList = this.calculateSeriesPerGompertzFormula(formulaTrait);
        double coefficient = this.calculateCoefficient(yPredictionList);
        formulaTrait.setCoefficient(coefficient);
        return formulaTrait;

    }

    /***
    * @Description 接收formula trait 和 sample data ， 按照模型公式计算y的预测值。
    * @Params * @param formulaTrait
* @param sampleData
    * @Return com.fermedu.iterative.dao.SampleData
    **/
    @Override
    public SampleData predictFromSampleData(FormulaTrait formulaTrait, SampleData sampleData) {
        this.sampleData = sampleData;
        List<Double> yPredictionList = this.calculateSeriesPerGompertzFormula(formulaTrait);
        /** overwrite Y prediction values after calculation */
        sampleData.setYValueList(yPredictionList);

        return sampleData;
    }
}
