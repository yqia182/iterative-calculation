package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.CsvWorksheet;
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
public class DataAccessorCsvImpl implements DataAccessor {

    private List<String> firstIDRow = new ArrayList<>();

    private List<List<Double>> valueRows = new ArrayList<>();

    private <E extends Object> List<E> getRowAsList(int rowIndex, String rowString) {
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

    private <E extends Object> void readToLocalVariable(String csvFilePath) {
        try {
            final File csvFile = new File(csvFilePath);

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
            log.error("File not found. Please check your file path. The file path that you have configured is : {}", csvFilePath);
            log.error("e: {}", e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("File reading failed. Please check your file. The file path that you have configured is : {}", csvFilePath);
            log.error("e: {}", e.toString());
        }
    }


    /***
    * @Description readOneSeries
    * @Params * @param xColumnIndex
    * @param
    * @Return com.fermedu.iterative.persistence.DataSeries
    **/
    @Override
    public CsvWorksheet readCvsWorksheet(String csvFilePath) {
        this.readToLocalVariable(csvFilePath);
        CsvWorksheet csvWorksheet = new CsvWorksheet();
        csvWorksheet.setFirstIDRow(this.firstIDRow);
        csvWorksheet.setValueRows(this.valueRows);
        return csvWorksheet;
    }

    @Override
    public void writeCvsWorksheet(String csvFilePath, List<String> lineList) {
        try {
            File csv = new File(csvFilePath);//CSV文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            //新增一行数据
            for (String eachLine : lineList) {
                bw.newLine();
                bw.write(eachLine);
            }
            bw.close();
        } catch (FileNotFoundException e) {
            //捕获File对象生成时的异常
            e.printStackTrace();
        } catch (IOException e) {
            //捕获BufferedWriter对象关闭时的异常
            e.printStackTrace();
        }

    }


}
