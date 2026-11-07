package com.ney.financetrackerapi.controller;

import com.ney.financetrackerapi.application.*;
import com.ney.financetrackerapi.dto.*;
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
    CreateCategoryUseCase createCategoryUseCase;
    CreateTransactionUseCase createTransactionUseCase;
    GetAllAccountsUseCase getAllAccountsUseCase;
    GetAllCategoriesUseCase getAllCategoriesUseCase;

    public FinanceTrackerController(CreateAccountUseCase createAccountUseCase, GetAllAccountsUseCase getAllAccountsUseCase, CreateCategoryUseCase createCategoryUseCase, GetAllCategoriesUseCase getAllCategoriesUseCase, CreateTransactionUseCase createTransactionUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAllAccountsUseCase = getAllAccountsUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping("/account")
    public ResponseEntity<AccountOutput> createAccount(@RequestBody @Valid AccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccountUseCase.execute(request));
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryOutput> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createCategoryUseCase.execute(request));
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionOutput> createTransaction(@RequestBody @Valid TransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createTransactionUseCase.execute(request));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountOutput>> getAll() {
        return ResponseEntity
                .ok()
                .body(getAllAccountsUseCase.execute());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryOutput>> getAllCategories() {
        return ResponseEntity
                .ok()
                .body(getAllCategoriesUseCase.execute());
    }
}
