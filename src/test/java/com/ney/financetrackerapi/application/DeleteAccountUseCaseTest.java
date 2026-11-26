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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DeleteAccountUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void should_delete_account() throws Exception {
        AccountEntity entity = new AccountEntity(
                "Nubank",
                AccountType.DIGITAL_WALLET,
                BigDecimal.valueOf(500.00)
        );

        accountRepository.save(entity);

        mockMvc.perform(delete("/finance/account/" + entity.getId().id().toString()))
                .andExpect(status().isNoContent());

        assertThat(accountRepository.count()).isZero();
    }
}