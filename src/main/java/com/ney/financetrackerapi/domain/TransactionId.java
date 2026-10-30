package com.ney.financetrackerapi.domain;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;

import java.util.UUID;

@Embeddable
public record TransactionId(UUID id) {
    public TransactionId {
        Assert.notNull(id, "Id not must be null");
    }

    public TransactionId() {
        this(UUID.randomUUID());
    }
}
