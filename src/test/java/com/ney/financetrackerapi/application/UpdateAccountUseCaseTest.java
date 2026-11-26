package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.enums.AccountType;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UpdateAccountUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void should_update_account() throws Exception {
        AccountEntity entity = new AccountEntity(
                "PayPal",
                AccountType.DIGITAL_WALLET,
                BigDecimal.valueOf(500.00)
        );

        long accountQuantityAfterSave = accountRepository.count();

        accountRepository.save(entity);

        String updateRequest = """
                {
                    "name": "Bradesco",
                    "type": "CREDIT_CARD"
                }
                """;

        mockMvc.perform(put("/finance/account/" + entity.getId().id().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bradesco"))
                .andExpect(jsonPath("$.type").value("CREDIT_CARD"));

        assertThat(accountRepository.count()).isEqualTo(accountQuantityAfterSave + 1);
    }
}