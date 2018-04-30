package com.example.demo.resource;

import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * File: CitiesMap
 * Author: Yafei Zheng
 * Date: 2018/4/29
 * Description: CityController declares the "/connected" endpoint to check if two cities are connected.
 */

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @RequestMapping("/connected")
    public String checkIfConnected(@RequestParam(value = "origin") String origin, @RequestParam(value = "destination") String destination) {
        return cityService.checkIfConnected(origin, destination) ? "yes" : "no";
    }
}