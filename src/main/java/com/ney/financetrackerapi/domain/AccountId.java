package com.ney.financetrackerapi.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.UUID;

@Embeddable
public record AccountId(UUID id) {
    public AccountId {
        Assert.notNull(id, "Id not must be null");
    }

    public AccountId() {
        this(UUID.randomUUID());
    }
}
