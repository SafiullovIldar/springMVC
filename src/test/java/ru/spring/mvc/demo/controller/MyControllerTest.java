package ru.spring.mvc.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.spring.mvc.demo.config.Config;
import ru.spring.mvc.demo.config.WebAppConfig;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Config.class, WebAppConfig.class})
public class MyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void sendMessage() throws Exception {
        mockMvc.perform(post("/message")
                .param("integer", "45")
                .content("Pasha"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("integer", 45))
                .andExpect(model().attribute("string", "Pasha"))
                .andDo(print());
    }

    @Test
    public void givenWac_whenServletContext_thenItNotNull() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
    }
}