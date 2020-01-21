package com.fermedu.iterative.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-21 16:26
 * @Author: JustThink
 * @Description: map the properties to this pojo object
 * @Include:
 **/
@ConfigurationProperties(prefix = "iterative")
@Component
@Data
public class IterativeCalculationProperties {

    private double coefficientSelectivePercentile = 0.25d;

    private double rangeDividerGranularity = 0.10d;

}
