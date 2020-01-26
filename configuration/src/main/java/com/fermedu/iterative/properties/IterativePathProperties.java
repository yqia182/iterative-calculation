package com.fermedu.iterative.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-26 20:56
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@ConfigurationProperties(prefix = "iterative.filepath")
@Component
@Data
public class IterativePathProperties {

    private String observedDataCsvFilePath = "D:\\Desktop\\1.csv";
}
