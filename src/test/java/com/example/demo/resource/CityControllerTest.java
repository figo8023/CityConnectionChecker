package com.example.demo.resource;

import com.example.demo.CityConnectionCheckerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * File: CityControllerTest
 * Author: Figo
 * Date: 2018/4/29
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityConnectionCheckerApplication.class)
@WebAppConfiguration
public class CityControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void match() throws Exception {
        mockMvc.perform(get("/connected?origin=New York&destination=Edison"))
                .andExpect(status().isOk())
                .andExpect(content().string("yes"));
    }

    @Test
    public void notMatch() throws Exception {
        mockMvc.perform(get("/connected?origin=Albany&destination=Edison"))
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
    }

    @Test
    public void originIsEmpty() throws Exception {
        mockMvc.perform(get("/connected?origin=&destination=Edison"))
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
    }

    @Test
    public void destinationIsEmpty() throws Exception {
        mockMvc.perform(get("/connected?origin=New York&destination="))
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
    }

    @Test
    public void UnexpectedInput() throws Exception {
        mockMvc.perform(get("/connected?origin=123&destination=##"))
                .andExpect(status().isOk())
                .andExpect(content().string("no"));
    }
}
