package com.fermedu.iterative.service;

import com.fermedu.iterative.dao.FormulaTrait;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private TraitRangeAdvisor traitRangeAdvisor;

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
     * @Description 选择最优coefficient并且返回。清空this.formulaTraitList
     * select best coefficient list and return. set this.formulaTraitList to new ArrayList.
     * @Params * @param
     * @Return java.util.List<com.fermedu.iterative.dao.FormulaTrait>
     **/
    @Override
    public List<FormulaTrait> loadTraitList() {

        this.initTraitList();

        final List<FormulaTrait> formulaTraitListResult = this.selectHighCoefficient(this.formulaTraitList);

        /** empty current this.formulaTraitList */
        this.formulaTraitList = new ArrayList<>();
        return formulaTraitListResult;
    }

    /***
    * @Description if this.formulaTraitListResult is not present,
     * initialize the this.formulaTraitListResult for the first time
     * and set the generated list to this.formulaTraitListResult
    * @Params * @param 
    * @Return void
    **/
    private void initTraitList() {
        if (this.formulaTraitList == null || this.formulaTraitList.size() <= 0) {
            /** not yet initialized the this.formulaTraitList */

        } else {

        }

    }

    @Override
    public List<FormulaTrait> saveToTraitList(FormulaTrait formulaTrait) {
        this.formulaTraitList.add(formulaTrait);


        return this.formulaTraitList;
    }
}
