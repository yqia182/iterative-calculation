package com.fermedu.iterative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-25 16:29
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@SpringBootApplication
@EnableJpaAuditing
public class PlotApplication {

    public static void main(String[] args) {

        SpringApplication.run(PlotApplication.class, args);
    }
}
