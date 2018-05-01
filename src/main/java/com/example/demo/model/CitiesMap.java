package com.example.demo.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * File: CitiesMap
 * Author: Yafei Zheng
 * Date: 2018/4/29
 * Description: CitiesMap.getCitiesMap() will return a synchronized (thread-safe) map in order to guarantee serial access.
 */

public class CitiesMap {
    private static Map<String, City> citiesMap = null;

    private CitiesMap() {}

    public static Map<String, City> getCitiesMap() {
        if (citiesMap == null) {
            citiesMap = Collections.synchronizedMap(new HashMap<>());
        }
        return citiesMap;
    }

    public static void setCitiesMap(Map<String, City> citiesMap) {
        CitiesMap.citiesMap = citiesMap;
    }
}
