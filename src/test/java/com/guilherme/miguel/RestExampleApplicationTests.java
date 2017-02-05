package com.guilherme.miguel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestExampleApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getUserTest() throws Exception {
        mockMvc.perform(get("/user/{id}", 20))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(20))
                .andExpect(jsonPath("$.name").value("Miguel"))
                .andExpect(jsonPath("$.contact.address").value("Lisbon"))
                .andExpect(jsonPath("$.contact.phone").value("999999999"));
    }

    @Test
    public void getNonExistingUserTest() throws Exception {
        mockMvc.perform(get("/user/{id}", 30))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.errorCode").value("404 - Not Found"))
                .andExpect(jsonPath("$.errorMessage").value("User 30 not found"));
    }

}
