package com.fermedu.iterative.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-22 15:42
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@ConfigurationProperties(prefix = "iterative.param.suggestion")
@Component
@Data
public class IterativeCalculationParamSuggestionProperties {

    private double lagRangeMin;
    private double lagRangeMax;

    private double rateRangeMin;
    private double rateRangeMax;

    private double minODRangeMin;
    private double minODRangeMax;

    private double maxODRangeMin;
    private double maxODRangeMax;

}
