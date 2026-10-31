package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.model.AccountEntity;

import java.math.BigDecimal;

public record AccountOutput(
        String id,
        String name,
        String type,
        BigDecimal initialBalance
) {
    public static AccountOutput from(AccountEntity entity) {
        return new AccountOutput(
                entity.getId().id().toString(),
                entity.getName(),
                entity.getType().name(),
                entity.getInitialBalance()
        );
    }
}