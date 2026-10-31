package com.ney.financetrackerapi.controller;

import com.ney.financetrackerapi.application.CreateAccountUseCase;
import com.ney.financetrackerapi.application.GetAllAccountsUseCase;
import com.ney.financetrackerapi.dto.AccountOutput;
import com.ney.financetrackerapi.dto.AccountRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/finance")
public class FinanceTrackerController {
    CreateAccountUseCase createAccountUseCase;
    GetAllAccountsUseCase getAllAccountsUseCase;

    public FinanceTrackerController(CreateAccountUseCase createAccountUseCase, GetAllAccountsUseCase getAllAccountsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAllAccountsUseCase = getAllAccountsUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountOutput> create(@RequestBody @Valid AccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccountUseCase.execute(request));
    }

    @GetMapping
    public ResponseEntity<List<AccountOutput>> getAll() {
        return ResponseEntity
                .ok()
                .body(getAllAccountsUseCase.execute());
    }
}
