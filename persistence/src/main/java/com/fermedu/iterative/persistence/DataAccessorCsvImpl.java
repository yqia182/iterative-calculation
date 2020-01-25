package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.CsvWorksheet;
import com.fermedu.iterative.dao.DataSeries;
import com.fermedu.iterative.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 14:00
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class DataAccessorCsvImpl<E extends Object> implements DataAccessor {

    private final static String CSV_FILE_PATH = "D:\\Desktop\\1.csv";

    private List<String> firstIDRow = new ArrayList<>();

    private List<List<Double>> valueRows = new ArrayList<>();

    private DataSeries dataSeries;

    private List<E> getRowAsList(int rowIndex, String rowString) {
        if (rowIndex == 0) {

            /** first row, which contains sample ids */

            this.firstIDRow = Arrays.asList(rowString.split(","));
            return (List<E>) this.firstIDRow;
        } else {
            List<Double> eachValueRow = new ArrayList<>();

            List<String> eachRowStringList= Arrays.asList(rowString.split(","));

            eachRowStringList.forEach(eachString->eachValueRow.add(Double.valueOf(eachString)));

            this.valueRows.add(eachValueRow);

            return (List<E>) eachValueRow;
        }

    }

    private void readToLocalVariable() {
        try {
            final File csvFile = new File(CSV_FILE_PATH);

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));

            String eachRow;
            int rowIndex = 0;
            while ((eachRow = reader.readLine()) != null) {
                List<E> eachRowList = this.getRowAsList(rowIndex, eachRow);
                log.trace(JsonUtil.toJson(eachRowList));
                rowIndex += 1;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("File not found. Please check your file path. The file path that you have configured is : {}", CSV_FILE_PATH);
            log.error("e: {}", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("File reading failed. Please check your file. The file path that you have configured is : {}", CSV_FILE_PATH);
            log.error("e: {}", e.toString());
        }
    }


    /***
    * @Description constructor . initialize the data into rows
    * @Params * @param
    * @Return
    **/
    public DataAccessorCsvImpl() {
        if (this.firstIDRow!=null && this.firstIDRow.size()>0 && this.valueRows != null && this.valueRows.size() > 0) {
        } else {
            log.info("Data arranged in rows is NOT found from the local memory. Now attempting to read csv file and initialize the data preparation.");
            this.readToLocalVariable();
        }
    }

    /***
    * @Description readOneSeries
    * @Params * @param xColumnIndex
    * @param
    * @Return com.fermedu.iterative.persistence.DataSeries
    **/
    @Override
    public CsvWorksheet readCvsWorksheet() {

        CsvWorksheet csvWorksheet = new CsvWorksheet();
        csvWorksheet.setFirstIDRow(this.firstIDRow);
        csvWorksheet.setValueRows(this.valueRows);
        return csvWorksheet;
    }
}
