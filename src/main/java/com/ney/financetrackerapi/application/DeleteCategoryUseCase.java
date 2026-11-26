package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.exception.CategoryNotFoundException;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void execute(CategoryId id) {
        CategoryEntity deleteCategory = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CategoryNotFoundException("Category not found")
                );

        categoryRepository.delete(deleteCategory);
    }
}
