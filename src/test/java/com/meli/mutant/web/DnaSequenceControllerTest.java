package com.meli.mutant.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DnaSequenceControllerTest {
    private MockMvc mockMvc;

    private WebApplicationContext context;
    public DnaSequenceControllerTest(WebApplicationContext context) {
        this.context = context;
    }
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void method_post_response_ok_if_is_mutant() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATGCGA\",\"CCCCTA\",\"TTATGT\",\"CAGTGC\",\"CACCTC\",\"AGAATG\"]}");

        mockMvc.perform(request).andExpect(status().isOk());

    }

    @Test
    public void method_post_response_forbidden_if_is_human() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CCTCTA\",\"TTATGT\",\"CAGTGC\",\"CACCTC\",\"AGAATG\"]}");

        mockMvc.perform(request).andExpect(status().isForbidden());

    }



}