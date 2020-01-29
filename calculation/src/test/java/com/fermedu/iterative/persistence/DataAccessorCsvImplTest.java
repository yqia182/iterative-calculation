package com.fermedu.iterative.persistence;

import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import com.fermedu.iterative.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-19 15:15
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
class DataAccessorCsvImplTest {

    @Autowired
    private DataAccessor dataAccessor;

    @Autowired
    private MysqlConnector mysqlConnector;


    private List<String> contructCsvLinesFromList(List<FinalResultPermanentEntity> objectList) {
        List<String> resultList = new ArrayList<>();

        final String firstLine = "yname,lag_time,max_rate,min_od,max_od,coef";
        resultList.add(firstLine);

        for (FinalResultPermanentEntity each : objectList) {
            String eachString = new String();
            eachString=eachString.concat(each.getYname());
            eachString=eachString.concat(",");
            eachString=eachString.concat(String.valueOf(each.getLagTime()));
            eachString=eachString.concat(",");
            eachString=eachString.concat(String.valueOf(each.getRate()));
            eachString=eachString.concat(",");
            eachString=eachString.concat(String.valueOf(each.getMinOD()));
            eachString=eachString.concat(",");
            eachString=eachString.concat(String.valueOf(each.getMaxOD()));
            eachString=eachString.concat(",");
            eachString=eachString.concat(String.valueOf(each.getCoefficient()));

            resultList.add(eachString);
        }

        return resultList;

    }

    @Test
    void readOneSeries() {

        Object result = dataAccessor.readCvsWorksheet("");
        System.out.println(JsonUtil.toJson(result));
    }



    @Test
    void writeAllSampleResultsToCsv() {


        final String csvFilePath = "D:\\Desktop\\result.csv";

        List<FinalResultPermanentEntity> resultList = mysqlConnector.findAllFinalResultWithHighestCoef();

        List<String> strings = this.contructCsvLinesFromList(resultList);

        dataAccessor.writeCvsWorksheet(csvFilePath, strings);
    }
}