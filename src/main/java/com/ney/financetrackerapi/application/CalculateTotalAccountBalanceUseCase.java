package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.dto.AccountBalanceOutput;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculateTotalAccountBalanceUseCase {

    private final AccountRepository repository;

    public CalculateTotalAccountBalanceUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public AccountBalanceOutput execute(AccountId id) {
        AccountEntity entity = repository.getReferenceById(id);

        return AccountBalanceOutput.from(entity);
    }
}
