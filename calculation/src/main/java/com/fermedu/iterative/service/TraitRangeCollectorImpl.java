package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.persistence.MysqlConnector;
import com.fermedu.iterative.properties.IterativeCalculationParamSuggestionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 13:17
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class TraitRangeCollectorImpl implements TraitRangeCollector {


    private List<FormulaTrait> formulaTraitList;

    @Autowired
    private MysqlConnector mysqlConnector;
    
    @Autowired
    private TraitRangeAdvisorStepLengthExpandImpl traitRangeAdvisor;

    /** initial param properties suggestions */
    @Autowired
    private IterativeCalculationParamSuggestionProperties suggestionProperties;


    @Autowired
    private PercentileSelect percentileSelect;


    /***
    * @Description make this.formulaTraitList to have values
     * 1st, check if this.formulaTraitList has any value
     * 2nd, check if mysql has any value
     * 3rd, initialize values by generating them according to the ranges you assigned
     * result: there will be values ready to read from this.formulaTraitList
    * @Params * @param 
    * @Return void
    **/
    private void initTraitList(int loop) {


            List<FormulaTrait> mysqlList = mysqlConnector.findAll();
            if (mysqlList != null && mysqlList.size() > 0) {
                this.formulaTraitList = mysqlList;
                System.out.println("STATUS: successfully loaded formula trait params from MySql(hard drive). MySql has given ".concat(String.valueOf(mysqlList.size())).concat(" records."));
            } else {
                /** not yet initialized the this.formulaTraitList */
                final FormulaTrait formulaTraitMin = new FormulaTrait(
                        suggestionProperties.getLagRangeMin(),
                        suggestionProperties.getRateRangeMin(),
                        suggestionProperties.getMinODRangeMin(),
                        suggestionProperties.getMaxODRangeMin());
                final FormulaTrait formulaTraitMax = new FormulaTrait(
                        suggestionProperties.getLagRangeMax(),
                        suggestionProperties.getRateRangeMax(),
                        suggestionProperties.getMinODRangeMax(),
                        suggestionProperties.getMaxODRangeMax());

                final List<FormulaTrait> initialFormulaTraitList = Arrays.asList(formulaTraitMin, formulaTraitMax);
                final List<FormulaTrait> formulaTraitList = traitRangeAdvisor.generateFormulaListByGivenParamRange(initialFormulaTraitList, loop);

                this.formulaTraitList = formulaTraitList;
                System.out.println("STATUS: No formula trait(param) is found in the memory or MySql(hard drive). Now have successfully generated ".concat(String.valueOf(formulaTraitList.size())).concat(" records.").concat("The records have been deposited into memory"));

            } 


    }







    /***
     * @Description 选择最优coefficient并且返回。清空this.formulaTraitList
     * select best coefficient list and return. set this.formulaTraitList to new ArrayList.
     * @Params * @param
     * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
     **/
    @Override
    public List<FormulaTrait> loadTraitList(int loop) {
        /** make this.formulaTraitList to have values */
        this.initTraitList(loop);


        /** select best coefficient. but not for the 1st time */
        final List<FormulaTrait> selectedFormulaTraitListResult = percentileSelect.selectBestCoefficient(this.formulaTraitList);


        /**
         * based on the selected percentile range,
         * generate a new range
         * */
        this.formulaTraitList = traitRangeAdvisor.generateFormulaListByGivenParamRange(selectedFormulaTraitListResult, loop);




        /** empty current formulaTraitList */
        mysqlConnector.deleteAll();
        System.out.println("STATUS: MySql flushed.");

        System.out.println("STATUS: ".concat(String.valueOf(this.formulaTraitList.size())).concat(" formulas have been loaded to memory for calculation."));

        return this.formulaTraitList;
    }


    /***
    * @Description save one formulaTrait to mysql
    * @Params * @param formulaTrait
    * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
    **/
    @Override
    public List<FormulaTrait> saveToTraitList(FormulaTrait formulaTrait) {
        mysqlConnector.saveOne(formulaTrait);

        final List<FormulaTrait> formulaTraitList = mysqlConnector.findAll();

        return formulaTraitList;
    }
}
