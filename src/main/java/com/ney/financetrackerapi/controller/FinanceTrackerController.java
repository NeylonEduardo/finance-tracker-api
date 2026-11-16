package com.ney.financetrackerapi.controller;

import com.ney.financetrackerapi.application.*;
import com.ney.financetrackerapi.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
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
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;

    public FinanceTrackerController(CreateAccountUseCase createAccountUseCase, GetAllAccountsUseCase getAllAccountsUseCase, CreateCategoryUseCase createCategoryUseCase, GetAllCategoriesUseCase getAllCategoriesUseCase, CreateTransactionUseCase createTransactionUseCase, GetAllTransactionsUseCase getAllTransactionsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAllAccountsUseCase = getAllAccountsUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
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
    public ResponseEntity<List<AccountOutput>> getAllAccounts() {
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

    @GetMapping("/transactions")
    public ResponseEntity<PageResponse<TransactionOutput>> getAllTransaction(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(PageResponse.from(getAllTransactionsUseCase.execute(pageable)));
    }
}
