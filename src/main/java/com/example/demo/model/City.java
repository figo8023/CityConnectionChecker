package com.example.demo.model;

import java.util.Set;

/**
 * File: City
 * Author: Yafei Zheng
 * Date: 2018/4/29
 * Description: id is the identifier for the city that if two cities have the same id, that means they are connected.
 * connectedCities is used to store the info reading from "city.txt" , showing which city is connected to this city.
 */

public class City {
    private String id;
    private Set<String> connectedCities;

    public City(String id, Set<String> connectedCities) {
        this.id = id;
        this.connectedCities = connectedCities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getConnectedCities() {
        return connectedCities;
    }

    public void setConnectedCities(Set<String> connectedCities) {
        this.connectedCities = connectedCities;
    }
}
