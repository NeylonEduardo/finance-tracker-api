package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.dto.TransactionOutput;
import com.ney.financetrackerapi.dto.TransactionRequest;
import com.ney.financetrackerapi.exception.AccountNotFoundException;
import com.ney.financetrackerapi.exception.CategoryNotFoundException;
import com.ney.financetrackerapi.exception.IncompatibleCategoryException;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.model.CategoryEntity;
import com.ney.financetrackerapi.model.TransactionEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import com.ney.financetrackerapi.repository.CategoryRepository;
import com.ney.financetrackerapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    public CreateTransactionUseCase(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository,
            CategoryRepository categoryRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public TransactionOutput execute(TransactionRequest request) {

        AccountId accountId = new AccountId(request.accountId());
        CategoryId categoryId = new CategoryId(request.categoryId());

        AccountEntity account = accountRepository
                .findById(accountId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account not found"));

        CategoryEntity category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found"));

        if (request.type() != category.getType()) {
            throw new IncompatibleCategoryException("Category type is incompatible with transaction");
        }

        TransactionEntity entity = new TransactionEntity(
                request.description(),
                request.amount(),
                request.type(),
                LocalDate.now(),
                account,
                category
        );

        TransactionEntity saved = transactionRepository.save(entity);

        return TransactionOutput.from(saved);
    }
}