package com.ney.financetrackerapi.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.UUID;

@Embeddable
public record CategoryId(UUID id) {
    public CategoryId {
        Assert.notNull(id, "Id not must be null");
    }

    public CategoryId() {
        this(UUID.randomUUID());
    }
}
