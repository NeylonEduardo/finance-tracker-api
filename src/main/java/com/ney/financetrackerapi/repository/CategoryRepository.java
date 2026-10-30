package com.ney.financetrackerapi.repository;

import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, CategoryId> {
}
