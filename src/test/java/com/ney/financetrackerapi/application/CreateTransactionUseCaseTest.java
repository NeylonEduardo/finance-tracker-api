package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.TransactionRequest;
import com.ney.financetrackerapi.enums.AccountType;
import com.ney.financetrackerapi.enums.TransactionType;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import com.ney.financetrackerapi.repository.CategoryRepository;
import com.ney.financetrackerapi.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CreateTransactionUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void before() {
        transactionRepository.deleteAll();
    }

    @Test
    public void should_create_transaction() throws Exception {

        AccountEntity accountEntity = new AccountEntity(
                "Nubank",
                AccountType.CASH,
                new BigDecimal("4000")
        );

        CategoryEntity categoryEntity = new CategoryEntity(
                "Groceries",
                TransactionType.EXPENSE
        );

        accountRepository.save(accountEntity);
        categoryRepository.save(categoryEntity);

        UUID accountId = accountEntity.getId().id();
        UUID categoryId = categoryEntity.getId().id();

        TransactionRequest request = new TransactionRequest(
                "Monthly grocery shopping",
                new BigDecimal("350.00"),
                TransactionType.EXPENSE,
                accountId,
                categoryId
        );

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/finance/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.description")
                        .value("Monthly grocery shopping"))
                .andExpect(jsonPath("$.amount").value(350.00))
                .andExpect(jsonPath("$.type").value("EXPENSE"))
                .andExpect(jsonPath("$.accountId").value(accountId.toString()))
                .andExpect(jsonPath("$.categoryId").value(categoryId.toString()));

        assertThat(transactionRepository.count()).isEqualTo(1);
    }

    @AfterEach
    void after() {
        transactionRepository.deleteAll();
    }
}