package com.ney.financetrackerapi.controller;

import com.ney.financetrackerapi.application.*;
import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/finance")
public class FinanceTrackerController {
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final CalculateTotalAccountBalanceUseCase calculateTotalAccountBalanceUseCase;

    public FinanceTrackerController(CreateAccountUseCase createAccountUseCase, GetAllAccountsUseCase getAllAccountsUseCase, CreateCategoryUseCase createCategoryUseCase, GetAllCategoriesUseCase getAllCategoriesUseCase, CreateTransactionUseCase createTransactionUseCase, GetAllTransactionsUseCase getAllTransactionsUseCase, CalculateTotalAccountBalanceUseCase calculateTotalAccountBalanceUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAllAccountsUseCase = getAllAccountsUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.createTransactionUseCase = createTransactionUseCase;
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
        this.calculateTotalAccountBalanceUseCase = calculateTotalAccountBalanceUseCase;
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
    public ResponseEntity<List<AccountOutput>> getAllAccounts(@RequestParam String sortBy) {
        return ResponseEntity
                .ok()
                .body(getAllAccountsUseCase.execute(sortBy));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryOutput>> getAllCategories(@RequestParam(defaultValue = "NAME") String sortBy) {
        return ResponseEntity
                .ok()
                .body(getAllCategoriesUseCase.execute(sortBy));
    }

    @GetMapping("/transactions")
    public ResponseEntity<PageResponse<TransactionOutput>> getAllTransaction(Pageable pageable, @RequestParam(defaultValue = "description") String sortBy) {
        return ResponseEntity
                .ok()
                .body(PageResponse.from(getAllTransactionsUseCase.execute(pageable, sortBy)));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<AccountBalanceOutput> calculateAccountBalance(@PathVariable UUID id) {
        return ResponseEntity.ok(
                calculateTotalAccountBalanceUseCase.execute(new AccountId(id))
        );
    }
}
