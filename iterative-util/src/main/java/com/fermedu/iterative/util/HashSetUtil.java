package com.fermedu.iterative.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Program: iterative-calculation
 * @Create: 2020-01-29 12:52
 * @Author: JustThink
 * @Description:
 * @Include:
 **/
public class HashSetUtil {

    public static  <T extends Object> List<T> removeReplicationFromList(List<T> list) {
        Set<T> hashSet = new HashSet<T>();
        hashSet.addAll(list);
        list.clear();
        list.addAll(hashSet);
        return list;
    }
}
