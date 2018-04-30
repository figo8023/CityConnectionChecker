package com.example.demo.service;

/**
 * File: CityServiceImpl
 * Author: Yafei Zheng
 * Date: 2018/4/29
 * Description: CityService declares the checkIfConnected method to check if two cities are connected.
 */

public interface CityService {
    boolean checkIfConnected(String city1, String city2);
}
