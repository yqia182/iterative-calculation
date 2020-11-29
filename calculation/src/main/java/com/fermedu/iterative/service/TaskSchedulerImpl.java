package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.formula.GrowthCurveCalculation;
import com.fermedu.iterative.persistence.MysqlConnector;
import com.fermedu.iterative.persistence.ResultHolder;
import com.fermedu.iterative.persistence.SampleDataArranger;
import com.fermedu.iterative.properties.IterativePathProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-20 18:17
 * @Author: JustThink
 * @Description: scheduler which has the power of getting sample data, optimizing params, doing calculation, etc.
 * @Include:
 **/
@Service
@Slf4j
public class TaskSchedulerImpl implements TaskScheduler {

    /**
     * load and save a formula trait list
     */
    @Autowired
    private TraitRangeCollector traitRangeCollector;

    @Autowired
    private SampleDataArranger sampleDataArranger;

    @Autowired
    private GrowthCurveCalculation growthCurveCalculation;

    @Autowired
    private ResultHolder resultHolder;

    @Autowired
    private MysqlConnector mysqlConnector;

    @Autowired
    private IterativePathProperties pathProperties;

    /**
     * load a formula trait list
     */
    private List<FormulaTrait> formulaTraitLoader(int loop) {
        return traitRangeCollector.loadTraitList(loop);
    }

    /**
     * load all sample names
     */
    private List<String> sampleDataArranger() {
        return sampleDataArranger.readSampleNameList(pathProperties.getObservedDataCsvFilePath());
    }


    /**
     * save the results of one sample to mysql database
     * return nothing
     */
    private boolean saveResultForOneSample(SampleData sampleData, int loop) {

        /** save to mysqlConnector */
        final boolean ifCoefSatisfied = resultHolder.saveResultForOneSample(sampleData, loop);
        return ifCoefSatisfied;
    }


    private void logFinalResultsAndDeleteTempResult(SampleData sampleData, int resultSize) {
        mysqlConnector.logFinalResultsAndDeleteTempResult(sampleData, resultSize);

        /** empty two forms for the next sample calculation */
        mysqlConnector.deleteAllTempResult();
        mysqlConnector.deleteAllTrait();

    }

    /***
     * @Description runOneSample loop the given times. For each time,
     * there is a formula trait(which has lag, rate, maxOD and minOD).
     * Then put the formula trait and sample data together,
     * so as to calculate the coefficient for this sample data& formula trait.
     * @Params * @param sampleData
     * @Return void
     **/
    @Override
    public void runOneSample(SampleData sampleData) {
        FormulaTrait formulaTraitResult = new FormulaTrait();
        /** loop for the given times */
        for (int calLoop = 0; calLoop <= 200; calLoop++) {
            System.out.println("STATUS: sample code: ".concat(sampleData.getYname()).concat(" (as on data sheet)"));
            System.out.println("STATUS: Now the ".concat(String.valueOf(calLoop)).concat(" loop is on."));

            /** load a formula list, which has been optimized */
            List<FormulaTrait> formulaTraitList = this.formulaTraitLoader(calLoop);

            for (FormulaTrait formulaTrait : formulaTraitList) {

                /** calculate all coefficient, and save those to the list */
                formulaTraitResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait, sampleData);

                /** save the list to somewhere */
                traitRangeCollector.saveToTraitList(formulaTraitResult);
            }

            /** after the loop ,record the result list to database */
            final boolean ifCoefSatisfied = this.saveResultForOneSample(sampleData, calLoop);
            if (ifCoefSatisfied) {
                break;
            }

        }

        this.logFinalResultsAndDeleteTempResult(sampleData,10);


    }




    /***
     * @Description loop all samples
     * @Params * @param
     * @Return void
     **/
    @Async("asyncExecutor")
    @Override
    public void runAllSamples() {
        /** loop for the number of the samples */
        List<String> sampleNames = this.sampleDataArranger();
        for (String sampleName : sampleNames) {
            SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName(pathProperties.getObservedDataCsvFilePath(), sampleName);
            this.runOneSample(sampleData);
        }
    }
}
