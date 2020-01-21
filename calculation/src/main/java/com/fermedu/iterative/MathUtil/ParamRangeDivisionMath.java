package com.fermedu.iterative.MathUtil;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 18:48
 * @Author: JustThink
 * @Description: 把参数范围（已经经过百分比优选）等分成若干份
 * selected param range (selected according to a percentage) is then further divided into multiple aliquots
 * @Include:
 **/
public class ParamRangeDivisionMath {

    public static double[] divideRangeFurtherToArray(double[] paramArray, double granularity) {
        // 找到最大值和最小值. find the max and min values
        final double maxInArray = StatUtils.max(paramArray);
        final double minInArray = StatUtils.min(paramArray);
        final double extremeDiff = maxInArray - minInArray;
        final double stepLength = extremeDiff / (1/granularity);
        List<Double> resultList = new ArrayList<>();
        for (int i = 0; i <= 1 / granularity; i++) {
            double eachDouble = minInArray + stepLength * i;
            resultList.add(eachDouble);
        }

        final double[] resultArray = resultList.stream().mapToDouble(Double::doubleValue).toArray();

        return resultArray;
    }
}
