package com.fermedu.iterative.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 16:34
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
public class FormulaTraitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double lagTime; // lag time

    private double rate; // maximum growth rate - μ max

    private double minOD; // minOD

    private double maxOD; // maxOD

    private double coefficient;
}
