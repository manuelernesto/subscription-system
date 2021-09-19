package com.adidas.subscriptionservice.controller;

import com.adidas.subscriptionservice.domain.repository.SubscriptionRepository;
import com.adidas.subscriptionservice.model.Newsletter;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.model.dto.SubscriptionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.UUID;

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
    @Autowired
    private SubscriptionRepository repository;

    private final String URL = "/v1/subscribers";

    @Test
    @WithMockUser(username = "manuel", password = "ernesto", roles = {"ADMIN"})
    public void getAllSubscriptionsTest() throws Exception {
        var subscription = new Subscription();
        var newsletter = new Newsletter();
        newsletter.setId(UUID.fromString("36d05deb-aca9-4b94-9c2c-76fd4a6f4530"));

        subscription.setEmail("manuel@test.com");
        subscription.setConsent(true);
        subscription.setDate_of_birth(new Date());
        subscription.setNewsletter(newsletter);

        repository.save(subscription);

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    @WithMockUser(username = "manuel", password = "ernesto", roles = {"ADMIN"})
    public void getSubscriptionWithEmailTest() throws Exception {
        var subscription = new Subscription();
        var newsletter = new Newsletter();
        newsletter.setId(UUID.fromString("36d05deb-aca9-4b94-9c2c-76fd4a6f4530"));

        subscription.setEmail("manuel1@test.com");
        subscription.setConsent(true);
        subscription.setDate_of_birth(new Date());
        subscription.setNewsletter(newsletter);

        repository.save(subscription);

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/manuel1@test.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "manuel", password = "ernesto", roles = {"ADMIN"})
    public void getSubscriptionEmailNotExistsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/manuel11@test.com"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser(username = "manuel", password = "ernesto", roles = {"ADMIN"})
    public void createNewSubscriptionTest() throws Exception {
        var subscription = new SubscriptionDTO();
        subscription.setEmail("manuelsave@test.com");
        subscription.setConsent(true);
        subscription.setDate_of_birth(new Date());
        subscription.setNewsletter_id(UUID.fromString("36d05deb-aca9-4b94-9c2c-76fd4a6f4530"));

        var json = new ObjectMapper().writeValueAsString(subscription);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @WithMockUser(username = "manuel", password = "ernesto", roles = {"ADMIN"})
    public void createNewSubscriptionEmailAlreadyExistsTest() throws Exception {
        var subscription = new SubscriptionDTO();
        subscription.setEmail("manuelsave@test.com");
        subscription.setConsent(true);
        subscription.setDate_of_birth(new Date());
        subscription.setNewsletter_id(UUID.fromString("36d05deb-aca9-4b94-9c2c-76fd4a6f4530"));

        var json = new ObjectMapper().writeValueAsString(subscription);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
