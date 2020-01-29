package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.dao.SampleData;
import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import com.fermedu.iterative.entity.FormulaTraitEntity;
import com.fermedu.iterative.entity.TraitResultEntity;
import com.fermedu.iterative.jpa.FinalResultPermanentRepository;
import com.fermedu.iterative.jpa.FormulaTraitRepository;
import com.fermedu.iterative.jpa.TraitResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 16:38
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Service
@Slf4j
public class MysqlConnectorImpl implements MysqlConnector {

    @Autowired
    private FormulaTraitRepository formulaTraitRepository;

    @Autowired
    private TraitResultRepository resultRepository;

    @Autowired
    private FinalResultPermanentRepository permanentRepository;

    private FormulaTrait convertFromEntity(FormulaTraitEntity entity) {
        FormulaTrait formulaTrait = new FormulaTrait();
        BeanUtils.copyProperties(entity, formulaTrait);
        return formulaTrait;
    }

    private FormulaTraitEntity convertToEntity(FormulaTrait formulaTrait) {
        FormulaTraitEntity entity = new FormulaTraitEntity();
        BeanUtils.copyProperties(formulaTrait, entity);
        return entity;
    }

    private List<FormulaTrait> convertFromEntityList(List<FormulaTraitEntity> entityList) {
        List<FormulaTrait> formulaTraitList = new ArrayList<>();
        for (FormulaTraitEntity eachEntity : entityList) {
            FormulaTrait formulaTrait = this.convertFromEntity(eachEntity);
            formulaTraitList.add(formulaTrait);
        }
        return formulaTraitList;
    }

    private List<FormulaTraitEntity> convertToEntityList(List<FormulaTrait> formulaTraitList) {
        List<FormulaTraitEntity> entityList= new ArrayList<>();
        for (FormulaTrait each: formulaTraitList) {
            FormulaTraitEntity entity= this.convertToEntity(each);
            entityList.add(entity);
        }
        return entityList;
    }

    @Override
    public List<FormulaTrait> findAll() {
        final List<FormulaTraitEntity> formulaTraitEntityList = formulaTraitRepository.findAll();
        final List<FormulaTrait> formulaTraitList = this.convertFromEntityList(formulaTraitEntityList);
        return formulaTraitList;
    }


    @Override
    public void saveOne(FormulaTrait formulaTrait) {
        FormulaTraitEntity entity = this.convertToEntity(formulaTrait);
        formulaTraitRepository.save(entity);
    }

    @Override
    public void saveResultForOneSample(SampleData sampleData, int loop, FormulaTrait formulaTrait) {
        TraitResultEntity traitResultEntity = new TraitResultEntity();
        BeanUtils.copyProperties(sampleData, traitResultEntity);
        BeanUtils.copyProperties(formulaTrait, traitResultEntity);

        traitResultEntity.setCalLoop(loop);

        final TraitResultEntity resultEntity = resultRepository.save(traitResultEntity);
    }

    @Override
    public void deleteAllTrait() {

        formulaTraitRepository.deleteAll();
    }

    @Override
    public void deleteAllTempResult() {
        resultRepository.deleteAll();

    }

    @Override
    public void logFinalResultsAndDeleteTempResult(SampleData sampleData, int resultSize) {

        List<TraitResultEntity> resultEntities = resultRepository.findAll();
        List<TraitResultEntity> sortedList = resultEntities.stream().sorted(Comparator.comparingDouble(TraitResultEntity::getCoefficient).reversed()).collect(Collectors.toList());

        List<FinalResultPermanentEntity> permanentEntityList = new ArrayList<>();
        for (int i = 0; i < resultSize; i++) {
            FinalResultPermanentEntity eachFinalResult = new FinalResultPermanentEntity();
            BeanUtils.copyProperties(sortedList.get(i), eachFinalResult);
            permanentEntityList.add(eachFinalResult);
        }
        permanentRepository.saveAll(permanentEntityList);
    }

    /***
     * @Description find the highest coef for each sample . combine the values into a list
     * @Params * @param 
     * @Return java.util.List<com.fermedu.iterative.entity.FinalResultPermanentEntity>
     **/
    @Override
    public List<FinalResultPermanentEntity> findAllFinalResultWithHighestCoef() {

        List<FinalResultPermanentEntity> all = permanentRepository.findAllOrderByCoefficientDesc();

        Map<String, FinalResultPermanentEntity> resultMap = new HashMap<>();
        /** for each yname , get all items under this yname */
        for (FinalResultPermanentEntity each : all) {
            resultMap.put(each.getYname(), each);
        }

        List<FinalResultPermanentEntity> resultList = new ArrayList<>();

        resultMap.forEach((key, value) -> resultList.add(value));

        return resultList;
    }
}
