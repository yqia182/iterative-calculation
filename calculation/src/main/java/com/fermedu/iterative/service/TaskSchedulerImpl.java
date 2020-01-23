package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.formula.GrowthCurveCalculation;
import com.fermedu.iterative.persistence.ResultHolder;
import com.fermedu.iterative.persistence.SampleDataArranger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * load a formula trait list
     */
    private List<FormulaTrait> formulaTraitLoader() {
        return traitRangeCollector.loadTraitList();
    }

    /**
     * load all sample names
     */
    private List<String> sampleDataArranger() {
        return sampleDataArranger.readSampleNameList();
    }


    /**
     * save the results of one sample to mysql database
     * return nothing
     */
    private boolean saveResultForOneSample(SampleData sampleData, int loop, List<FormulaTrait> formulaTraitList) {

        /** save to mysqlConnector */
        final boolean ifCoefSatisfied = resultHolder.saveResultForOneSample(sampleData, loop, formulaTraitList);
        return ifCoefSatisfied;
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
        /** loop for the given times */
        for (int calLoop = 0; calLoop <= 1000; calLoop++) {
            System.out.println("STATUS: Now running sample: ".concat(sampleData.getYName()).concat(" (Name on data sheet column)"));
            System.out.println("STATUS: Now the ".concat(String.valueOf(calLoop)).concat(" loop is on."));

            /** load a formula list, which has been optimized */
            List<FormulaTrait> formulaTraitList = this.formulaTraitLoader();

            for (FormulaTrait formulaTrait : formulaTraitList) {

                /** calculate all coefficient, and save those to the list */
                final FormulaTrait eachCoefficientResult = growthCurveCalculation.calculateOneSampleSet(formulaTrait, sampleData);                /** save the list to somewhere */

                /** save the list to somewhere */
                traitRangeCollector.saveToTraitList(eachCoefficientResult);
            }

            /** after the loop ,record the result list to database */
            final boolean ifCoefSatisfied = this.saveResultForOneSample(sampleData, calLoop, this.formulaTraitLoader());
            if (ifCoefSatisfied) {
                break;
            }

        }


    }


    /***
     * @Description loop all samples
     * @Params * @param
     * @Return void
     **/
    @Override
    public void runAllSamples() {
        /** loop for the number of the samples */
        List<String> sampleNames = this.sampleDataArranger();
        for (String sampleName : sampleNames) {
            SampleData sampleData = sampleDataArranger.readOneSampleDataSeriesByName(sampleName);
            this.runOneSample(sampleData);
        }
    }
}
