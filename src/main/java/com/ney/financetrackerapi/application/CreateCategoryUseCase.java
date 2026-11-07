package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.CategoryOutput;
import com.ney.financetrackerapi.dto.CategoryRequest;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCategoryUseCase {
    CategoryRepository repository;

    public CreateCategoryUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CategoryOutput execute(CategoryRequest request) {
        CategoryEntity entity = new CategoryEntity(
                request.name(),
                request.type()
        );

        repository.save(entity);

        return CategoryOutput.from(entity);
    }
}
