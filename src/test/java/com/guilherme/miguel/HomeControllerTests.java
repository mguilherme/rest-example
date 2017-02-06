package com.guilherme.miguel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.miguel.controller.ErrorResponseBody;
import com.guilherme.miguel.domain.User;
import com.guilherme.miguel.domain.User.Contact;
import com.guilherme.miguel.exception.UserNotFoundException;
import com.guilherme.miguel.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test Examples with expect jsonPath and asserts with Objects, this example shows both for testing purposes.
 * <p>
 * We wouldn't need to mock {@link UserService} but if we have any form of persistence
 * this should be the way to deal with.
 *
 * @author Miguel Guilherme
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void getUserTest() throws Exception {
        Long id = 20L;
        given(userService.getUser(id))
                .willReturn(new User(id, "John Doe", new Contact("London", "111111111")));

        MvcResult result = mockMvc.perform(get("/user/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.contact.address").value("London"))
                .andExpect(jsonPath("$.contact.phone").value("111111111"))
                .andDo(print()).andReturn();

        User user = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        assertThat(user.getId(), is(equalTo(id)));
        assertThat(user.getName(), is(equalTo("John Doe")));
        assertThat(user.getContact().getAddress(), is(equalTo("London")));
        assertThat(user.getContact().getPhone(), is(equalTo("111111111")));
    }

    @Test
    public void getNonExistingUserTest() throws Exception {
        Long id = 30L;
        given(userService.getUser(anyLong()))
                .willThrow(new UserNotFoundException(format("User %s not found", id), id));

        MvcResult result = mockMvc.perform(get("/user/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.errorCode").value("404 - Not Found"))
                .andExpect(jsonPath("$.errorMessage").value("User 30 not found"))
                .andDo(print()).andReturn();

        ErrorResponseBody errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponseBody.class);

        assertThat(errorResponse.getErrorCode(), is(equalTo("404 - Not Found")));
        assertThat(errorResponse.getErrorMessage(), is(equalTo("User 30 not found")));
    }

}
