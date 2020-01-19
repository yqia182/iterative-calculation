package com.fermedu.iterative.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 16:16
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Data
public class CsvWorksheet {
    private List<String> firstIDRow;
    private List<List<Double>> valueRows;
}
