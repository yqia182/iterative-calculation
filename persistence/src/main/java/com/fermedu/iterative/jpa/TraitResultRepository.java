package com.fermedu.iterative.jpa;

import com.fermedu.iterative.entity.TraitResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-23 16:39
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface TraitResultRepository extends JpaRepository<TraitResultEntity, Long> {

    TraitResultEntity save(TraitResultEntity traitResultEntity);

    List<TraitResultEntity> findAll();

    void deleteAll();



}
