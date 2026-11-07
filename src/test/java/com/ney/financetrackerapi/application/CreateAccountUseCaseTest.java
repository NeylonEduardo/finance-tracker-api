package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateAccountUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void before() {
        repository.deleteAll();
    }

    @Test
    void should_create_account() throws Exception {
        String requestBody = """
                {
                  "name": "Inter",
                  "type": "CREDIT_CARD",
                  "initialBalance": 2500.00
                }
                """;

        mockMvc.perform(post("/finance/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Inter"))
                .andExpect(jsonPath("$.type").value("CREDIT_CARD"))
                .andExpect(jsonPath("$.initialBalance").value(2500.00));

        assertThat(repository.count()).isEqualTo(1);
    }
}