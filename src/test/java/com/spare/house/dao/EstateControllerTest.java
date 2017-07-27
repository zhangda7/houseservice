package com.spare.house.dao;

import com.spare.house.controller.EstateController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dada on 2017/7/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EstateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testListByNameNoParam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/v1/estate/listByDistrict").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testListByDistrict() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/v1/estate/listByDistrict?district=123&startPage=1&pageCount=100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":200,\"msg\":null,\"data\":\"null\",\"size\":0}")));
    }

    @Test
    public void testListByNameHasValue() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/v1/estate/listByName?estateName=绿地").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(content().string(equalTo("{\"code\":200,\"msg\":null,\"data\":\"null\",\"size\":0}")));
    }

}
