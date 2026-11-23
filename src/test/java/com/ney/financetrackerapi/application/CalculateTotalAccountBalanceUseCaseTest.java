package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.enums.AccountType;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CalculateTotalAccountBalanceUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void should_return_accountBalance() throws Exception {
        AccountEntity entity = new AccountEntity(
                "Banco do Brasil",
                AccountType.DIGITAL_WALLET,
                BigDecimal.valueOf(500.00)
        );

        long repositoryTotalEntity = accountRepository.count();

        accountRepository.save(entity);

        assertThat(accountRepository.count()).isEqualTo(repositoryTotalEntity + 1);

        mockMvc.perform(get("/finance/balance/" + entity.getId().id().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId().id().toString()))
                .andExpect(jsonPath("$.balance").value(500.00));
    }
}