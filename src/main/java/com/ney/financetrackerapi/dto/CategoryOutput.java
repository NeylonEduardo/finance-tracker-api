package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.model.CategoryEntity;

public record CategoryOutput(
        String id,
        String name,
        String type) {

    public static CategoryOutput from(CategoryEntity entity) {
        return new CategoryOutput(
                entity.getId().id().toString(),
                entity.getName(),
                entity.getType().name()
        );
    }
}
