package com.thebook.thebookapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScriptureController.class)
public class ScriptureControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_testEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/scripture/test"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("Hello from the-book-api"));
    }
}
