package com.meli.mutant.web;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.persistence.entity.Stat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatControllerTest {
    private MockMvc mockMvc;

    private WebApplicationContext context;

    public StatControllerTest(WebApplicationContext context) {
        this.context = context;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void method_get_stats_response_ok() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/stats");
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void method_get_response_with_count_human_equal_zero_and_count_mutant_equal_zero()  {
        StatDomain statDomain = new StatDomain(0,0);
        Assertions.assertEquals(0, statDomain.getCountHumanDna());
        Assertions.assertEquals(0, statDomain.getCountMutantDna());
        Assertions.assertEquals(0, statDomain.getRatioStat());
    }

    @Test
    void method_get_response_with_count_human_equal_40_and_count_mutant_equal_10() {
        StatDomain statDomain = new StatDomain(40,10);
        Assertions.assertEquals(40, statDomain.getCountMutantDna());
        Assertions.assertEquals(10, statDomain.getCountHumanDna());
        Assertions.assertEquals(0.4, statDomain.getRatioStat());
    }

}