package com.fermedu.iterative.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-27 23:20
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
@Controller
@Slf4j
@RequestMapping(value = {"/calculation/sample"})
public class CalculatedSampleController {

    @GetMapping(value = {"/all"})
    public ModelAndView sampleAll(Map<String, Object> map) {

        return new ModelAndView("page/sampleAll", map);
    }

    @GetMapping(value = {"/one"})
    public ModelAndView sampleOne(Map<String, Object> map) {

        return new ModelAndView("page/sampleOne", map);
    }
}
