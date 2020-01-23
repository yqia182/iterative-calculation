package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.persistence.MysqlConnector;
import com.fermedu.iterative.properties.IterativeCalculationParamSuggestionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private TraitRangeAdvisor traitRangeAdvisor;

    /** initial param properties suggestions */
    @Autowired
    private IterativeCalculationParamSuggestionProperties suggestionProperties;

    /***
     * @Description 将this.formulaTraitList 传入traitRangeAdvisor，获取最优拟合的结果list
     * pass this.formulaTraitList  to traitRangeAdvisor in order to have a list of highest coefficient.
     * @Params * @param
     * @Return void
     **/
    private List<FormulaTrait> selectHighCoefficient(List<FormulaTrait> formulaTraitList) {
        List<FormulaTrait> formulaTraitListResult = new ArrayList<>();
        formulaTraitListResult = traitRangeAdvisor.selectBestCoefficient(formulaTraitList);
        return formulaTraitListResult;
    }
    
    /***
    * @Description make this.formulaTraitList to have values
     * 1st, check if this.formulaTraitList has any value
     * 2nd, check if mysql has any value
     * 3rd, initialize values by generating them according to the ranges you assigned
     * result: there will be values ready to read from this.formulaTraitList
    * @Params * @param 
    * @Return void
    **/
    private void initTraitList() {
        if (this.formulaTraitList == null || this.formulaTraitList.size() <= 0) {

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
                final List<FormulaTrait> formulaTraitList = traitRangeAdvisor.generateFormulaListByGivenParamRange(initialFormulaTraitList);

                this.formulaTraitList = formulaTraitList;
                System.out.println("STATUS: No formula trait(param) is found in the memory or MySql(hard drive). Now have successfully generated ".concat(String.valueOf(formulaTraitList.size())).concat(" records.").concat("The records have been deposited into memory"));

            } 
        } else {
            /** this.formulaTraitList already has initial values */
            System.out.println("STATUS: reading formulaTraitList from memory. Now the list size is: ".concat(String.valueOf(this.formulaTraitList.size())));
        }

    }

    /***
     * @Description 选择最优coefficient并且返回。清空this.formulaTraitList
     * select best coefficient list and return. set this.formulaTraitList to new ArrayList.
     * @Params * @param
     * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
     **/
    @Override
    public List<FormulaTrait> loadTraitList() {
        /** make this.formulaTraitList to have values */
        this.initTraitList();

        /** select best coefficient */
        final List<FormulaTrait> selectedFormulaTraitListResult = this.selectHighCoefficient(this.formulaTraitList);

        /** empty current formulaTraitList */
        mysqlConnector.deleteAndSaveAll(selectedFormulaTraitListResult);
        System.out.println("STATUS: MySql flushed. The formulas with higher coefficients. ".concat(String.valueOf(selectedFormulaTraitListResult.size())).concat(" have been saved into memory and MySql(hard drive)."));

        return selectedFormulaTraitListResult;
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
