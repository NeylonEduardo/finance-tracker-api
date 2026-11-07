package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.model.TransactionEntity;

import java.math.BigDecimal;

public record TransactionOutput(
        String id,
        String description,
        BigDecimal amount,
        String type,
        String date,
        String accountId,
        String categoryId
) {

    public static TransactionOutput from(TransactionEntity entity) {
        return new TransactionOutput(
                entity.getId().id().toString(),
                entity.getDescription(),
                entity.getAmount(),
                entity.getType().name(),
                entity.getDate().toString(),
                entity.getAccount().getId().id().toString(),
                entity.getCategory().getId().id().toString()
        );
    }
}
