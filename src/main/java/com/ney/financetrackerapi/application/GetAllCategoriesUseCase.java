package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.CategoryOutput;
import com.ney.financetrackerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCategoriesUseCase {
    CategoryRepository repository;

    public GetAllCategoriesUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryOutput> execute() {
        return repository.findAll()
                .stream()
                .map(CategoryOutput::from)
                .toList();
    }
}
