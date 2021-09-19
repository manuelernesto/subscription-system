package com.adidas.subscriptionservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 19/09/21 14:19
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String URL = "/v1/subscribers";

//    @Test
//    public void getAllSubscriptionsTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(URL))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());


//    }


    @Test
    public void getSubscriptionWithEmailTest() {

    }

    @Test
    public void getSubscriptionEmailNotExistsTest() {

    }


    @Test
    public void createNewSubscriptionTest() {

    }


    @Test
    public void createNewSubscriptionEmailAlreadyExistsTest() {

    }
}
