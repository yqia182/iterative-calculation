package com.fermedu.iterative.jpa;

import com.fermedu.iterative.entity.FinalResultPermanentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-24 22:29
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface FinalResultPermanentRepository extends JpaRepository<FinalResultPermanentEntity, Long> {

    <S extends FinalResultPermanentEntity> List<S> saveAll(Iterable<S> iterable);



    List<FinalResultPermanentEntity> findByyNameOrderByCoefficientDesc(String yName);
}


