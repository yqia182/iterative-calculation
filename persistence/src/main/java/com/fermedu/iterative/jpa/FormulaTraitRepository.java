package com.fermedu.iterative.jpa;

import com.fermedu.iterative.entity.FormulaTraitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 16:33
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public interface FormulaTraitRepository extends JpaRepository<FormulaTraitEntity,Long> {

    List<FormulaTraitEntity> findAll();

    void deleteAll();

    FormulaTraitEntity save(FormulaTraitEntity formulaTrait);

    <S extends FormulaTraitEntity> List<S> saveAll(Iterable<S> iterable);
}
