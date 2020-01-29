package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.CsvWorksheet;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 13:57
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface DataAccessor {
    CsvWorksheet readCvsWorksheet(String csvFilePath);

    void writeCvsWorksheet(String csvFilePath, List<String> lineList);



}
