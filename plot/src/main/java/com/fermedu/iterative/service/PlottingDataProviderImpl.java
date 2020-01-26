package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import com.fermedu.iterative.formula.GrowthCurveCalculation;
import com.fermedu.iterative.jpa.FinalResultPermanentRepository;
import com.fermedu.iterative.persistence.MysqlConnector;
import com.fermedu.iterative.persistence.SampleDataArranger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private GrowthCurveCalculation growthCurveCalculation;

    /** calculate a set of predicted value by formula trait */
    private SampleData predictSampleDataByFormula(FormulaTrait formulaTrait,SampleData sampleData) {
        final SampleData predictedSampleData = growthCurveCalculation.predictFromSampleData(formulaTrait, sampleData);
        return predictedSampleData;
    }

    @Override
    public SampleData getObservedData(String sampleName) {
        SampleData sampleData = csvData.readOneSampleDataSeriesByName(sampleName);
        return sampleData;
    }

    @Override
    public SampleData getPredictedData(String sampleName) {
        /** get formula trait. select for the highest coef */

        List<FinalResultPermanentEntity> entityList = finalResultRepository.findByYnameOrderByCoefficientDesc(sampleName);
        FinalResultPermanentEntity bestCoefficientEntity = entityList.get(0);
        FormulaTrait formulaTrait = new FormulaTrait();
        BeanUtils.copyProperties(bestCoefficientEntity, formulaTrait);
        /** get observed data, from the xSeries and the formula trait */
        final SampleData observedSampleData = this.getObservedData(sampleName);

        /** combine, save into a sampleData object */
        final SampleData predictedSampleData = this.predictSampleDataByFormula(formulaTrait, observedSampleData);

        return predictedSampleData;
    }


    @Override
    public List<String> getSampleNames() {
        final List<String> csvSampleNames = csvData.readSampleNameList();
        if (csvSampleNames != null && csvSampleNames.size() > 0) {
            return csvSampleNames;
        }

        return null;
    }


}
