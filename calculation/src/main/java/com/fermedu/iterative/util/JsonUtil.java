package com.fermedu.iterative.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-18 18:06
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
