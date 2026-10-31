package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.enums.AccountType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountRequest(

        @NotEmpty(message = "Name is mandatory")
        String name,

        @NotNull(message = "Type is mandatory")
        AccountType type,

        @NotNull(message = "Initial balance is mandatory")
        @DecimalMin(value = "0.00", message = "Initial balance cannot be nagative")
        BigDecimal initialBalance
) {
}