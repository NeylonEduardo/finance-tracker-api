package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.model.AccountEntity;

import java.math.BigDecimal;

public record AccountBalanceOutput(
        String id,
        BigDecimal balance
) {

    public static AccountBalanceOutput from(AccountEntity entity) {
        return new AccountBalanceOutput(
                entity.getId().id().toString(),
                entity.getInitialBalance()
        );
    }
}