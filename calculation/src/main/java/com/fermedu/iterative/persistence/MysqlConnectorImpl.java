package com.fermedu.iterative.persistence;

import com.fermedu.iterative.dao.FormulaTrait;
import com.fermedu.iterative.entity.FormulaTraitEntity;
import com.fermedu.iterative.jpa.FormulaTraitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void deleteAll() {
        formulaTraitRepository.deleteAll();
    }

    @Override
    public List<FormulaTrait> saveAll(List<FormulaTrait> formulaTraitList) {
        List<FormulaTraitEntity> entities = this.convertToEntityList(formulaTraitList);
        List<FormulaTraitEntity> entityResultList= formulaTraitRepository.saveAll(entities);
        List<FormulaTrait> resultList = this.convertFromEntityList(entityResultList);
        return resultList;
    }

    @Override
    public void saveOne(FormulaTrait formulaTrait) {
        FormulaTraitEntity entity = this.convertToEntity(formulaTrait);
        formulaTraitRepository.save(entity);
    }
}