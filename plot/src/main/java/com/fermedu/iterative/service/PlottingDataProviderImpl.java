package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import com.fermedu.iterative.jpa.FinalResultPermanentRepository;
import com.fermedu.iterative.persistence.DataAccessor;
import com.fermedu.iterative.persistence.MysqlConnector;
import com.fermedu.iterative.persistence.SampleDataArranger;
import com.fermedu.iterative.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.by;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-25 16:32
 * @Author: JustThink
 * @Description: provide observed data array and predicted data array
 * * @Include:
 **/
@Service
@Slf4j
public class PlottingDataProviderImpl implements PlottingDataProvider {
    @Autowired
    private MysqlConnector mysqlConnector;

    @Autowired
    private SampleDataArranger csvData;

    @Autowired
    private FinalResultPermanentRepository finalResultRepository;

    private void predictSampleDataByFormula() {


    }

    @Override
    public SampleData getObservedData(String sampleName) {
        SampleData sampleData = csvData.readOneSampleDataSeriesByName(sampleName);
        return sampleData;
    }

    @Override
    public SampleData getPredictedData(String sampleName) {
        //todo
        /** get formula trait. select for the highest coef */

        List<FinalResultPermanentEntity> entityList = finalResultRepository.findAllByYNameAndOrderByCoefficientDesc(sampleName);

        FinalResultPermanentEntity bestCoefficientEntity = entityList.get(0);

        System.out.println(JsonUtil.toJson(bestCoefficientEntity));
        /** get observed data, from the xSeries and the formula trait */


        /** calculate the predicted ySeries */

        /** combine, save into a sampleData object */

        this.predictSampleDataByFormula();
        return null;
    }



    @Override
    public List<String> getSampleNamesFromCsv() {
        return null;
    }

    @Override
    public List<String> getSampleNamesFromMysql() {
        return null;
    }
}
