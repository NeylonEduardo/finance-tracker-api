package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.AccountOutput;
import com.ney.financetrackerapi.dto.AccountRequest;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountUseCase {
    AccountRepository repository;

    public CreateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccountOutput execute(AccountRequest request) {
        AccountEntity entity = new AccountEntity(
                request.name(),
                request.type(),
                request.initialBalance()
        );

        repository.save(entity);

        return AccountOutput.from(entity);
    }
}
