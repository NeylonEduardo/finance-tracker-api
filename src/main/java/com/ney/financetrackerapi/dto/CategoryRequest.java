package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.enums.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(

        @NotEmpty(message = "Name is mandatory")
        String name,

        @NotNull(message = "Type is mandatory")
        TransactionType type
) {
}
