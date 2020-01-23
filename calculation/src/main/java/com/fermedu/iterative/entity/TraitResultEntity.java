package com.fermedu.iterative.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-23 16:08
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class TraitResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * sample name
     * which is the column name for the sample on the sample collection sheet
     */
    private String yName;
    
    /** loop of the iterative cal */
    private int loop;

    private double lagTime; // lag time

    private double rate; // maximum growth rate - μ max

    private double minOD; // minOD

    private double maxOD; // maxOD

    private double coefficient;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
