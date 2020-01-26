package com.fermedu.iterative.dao;

import lombok.Data;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 16:24
 * @Author: JustThink
 * @Description: Every sample data for mapping a real sample data series.
 * @Include:
 **/
@Data
public class SampleData {
    private String xname;

    private List<Double> xValueList;

    private String yname;

    private List<Double> yValueList;
}
