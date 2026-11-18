package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.CategoryOutput;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetAllCategoriesUseCase {
    private final CategoryRepository repository;

    public GetAllCategoriesUseCase(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryOutput> execute(String sortBy) {
        Comparator<CategoryEntity> comparator = switch (sortBy.toUpperCase()) {
            case "NAME" -> Comparator.comparing(CategoryEntity::getName);
            case "TYPE" -> Comparator.comparing(CategoryEntity::getType);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return repository.findAll()
                .stream()
                .sorted(comparator)
                .map(CategoryOutput::from)
                .toList();
    }
}
