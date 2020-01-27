package com.fermedu.iterative.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-27 13:47
 * @Author: JustThink
 * @Description: index page. control panel
 * @Include:
 **/
@Controller
@Slf4j
@RequestMapping(value = {"/","/iterative"})
public class IndexController {

    @GetMapping(value = {"/index","/"})
    public ModelAndView index(Map<String, Object> map) {
        map.put("test", "test");
        return new ModelAndView("page/index",map);
    }

}
