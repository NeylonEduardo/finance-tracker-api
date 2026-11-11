package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.enums.AccountType;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GetAllAccountsUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository repository;

    @AfterEach
    void after() {
        repository.deleteAll();
    }

    @Test
    void should_have_list_account() throws Exception {
        AccountEntity entity = repository.save(
                new AccountEntity
                        (
                                "Nubank",
                                AccountType.CHECKING_ACCOUNT,
                                new BigDecimal("3000.00")
                        )
        );

        UUID id = entity.getId().id();

        mockMvc.perform(get("/finance/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].name").value("Nubank"))
                .andExpect(jsonPath("$[0].type").value("CHECKING_ACCOUNT"))
                .andExpect(jsonPath("$[0].initialBalance").value(3000.00));
    }

    @BeforeEach
    void before() {
        repository.deleteAll();
    }
}