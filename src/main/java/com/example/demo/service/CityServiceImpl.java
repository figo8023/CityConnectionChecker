package com.example.demo.service;

import com.example.demo.model.CitiesMap;
import com.example.demo.model.City;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * File: CityServiceImpl
 * Author: Yafei Zheng
 * Date: 2018/4/29
 */

@Service("cityService")
public class CityServiceImpl implements CityService{
    @Override
    public boolean checkIfConnected(String city1, String city2) {
        Map<String, City> citiesMap = CitiesMap.getCitiesMap();
        return !(citiesMap.get(city1) == null || citiesMap.get(city2) == null) && citiesMap.get(city1).getId().equals(citiesMap.get(city2).getId());
    }
}
