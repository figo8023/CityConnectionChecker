package com.example.demo;

import com.example.demo.model.CitiesMap;
import com.example.demo.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * File: CityServiceImpl
 * Author: Yafei Zheng
 * Date: 2018/4/29
 */
@SpringBootApplication
public class CityConnectionCheckerApplication {

    private static final Logger logger = LoggerFactory.getLogger(CityConnectionCheckerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CityConnectionCheckerApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup() {
        return (args) -> {
            List<String> result = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader()
                    .getResourceAsStream("city.txt")))
                    .lines().collect(Collectors.toList());
            Map<String, City> citiesMap = CitiesMap.getCitiesMap();
            // initialize the citiesMap, here each city's id will be itself, other cities read from pair will be store
            // in a set so that no duplicate city stored.
            for (String pairs : result) {
                String[] pair = pairs.split(",");
                putDataToCitiesMap(citiesMap, pair[0], pair[1]);
                putDataToCitiesMap(citiesMap, pair[1], pair[0]);
            }
            modifyCitiesMap(citiesMap);
            logger.info("The sample data has been generated");
        };
    }

    //using bfs, if the city is not visited, update all related cities' id to current id.
    private void modifyCitiesMap(Map<String, City> citiesMap) {
        Set<String> visited = new HashSet<>();
        citiesMap.entrySet().forEach(entry -> {
            if (visited.add(entry.getKey())) {
                Queue<String> queue = new LinkedList<>();
                String curId = entry.getKey();
                queue.offer(curId);
                while (!queue.isEmpty()) {
                    String curCity = queue.poll();
                    //update each city's id to current id.
                    citiesMap.get(curCity).setId(curId);
                    citiesMap.get(curCity).getConnectedCities().forEach(city -> {
                        //if this city is not visited, put it in queue.
                        if (visited.add(city)) {
                            queue.offer(city);
                        }
                    });
                }
            }
        });
    }

    private void putDataToCitiesMap(Map<String, City> citiesMap, String city1, String city2) {
        if (!citiesMap.containsKey(city1)) {
            citiesMap.put(city1, new City(city1, new HashSet<>(Collections.singletonList(city2))));
        } else {
            citiesMap.get(city1).getConnectedCities().add(city2);
        }
    }
}
