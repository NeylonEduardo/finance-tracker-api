package com.ney.financetrackerapi.dto;

import com.ney.financetrackerapi.enums.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateAccountRequest(

        @NotEmpty(message = "Name is mandatory")
        String name,

        @NotNull(message = "Type is mandatory")
        AccountType type
) {
}
