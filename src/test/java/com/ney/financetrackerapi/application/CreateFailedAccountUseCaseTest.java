package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CreateFailedAccountUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository repository;

    @Test
    public void should_create_failedAccount() throws Exception {
        String invalidRequest = """
                {
                  "name": "",
                  "type": "TEST_TYPE",
                  "initialBalance": -1
                }
                """;

        mockMvc.perform(post("/finance/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest());

        assertThat(repository.count()).isZero();
    }
}
