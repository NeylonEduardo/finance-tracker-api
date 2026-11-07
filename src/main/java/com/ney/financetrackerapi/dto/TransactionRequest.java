package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.enums.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionRequest(

        @NotBlank(message = "Description cannot be blank")
        String description,

        @NotNull(message = "Amount is mandatory")
        @DecimalMin(
                value = "0.00",
                inclusive = false,
                message = "Amount must be greater than zero"
        )
        BigDecimal amount,

        @NotNull(message = "Type is mandatory")
        TransactionType type,

        @NotNull(message = "Account id is mandatory")
        UUID accountId,

        @NotNull(message = "Category id is mandatory")
        UUID categoryId

) {
}
