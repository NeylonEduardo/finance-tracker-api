package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.enums.TransactionType;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DeleteCategoryUseCaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void should_delete_category() throws Exception {
        CategoryEntity entity = new CategoryEntity(
                "Shopping at the mall",
                TransactionType.EXPENSE
        );

        categoryRepository.save(entity);

        mockMvc.perform(delete("/finance/category/" + entity.getId().id().toString()))
                .andExpect(status().isNoContent());

        assertThat(categoryRepository.count()).isZero();
    }
}