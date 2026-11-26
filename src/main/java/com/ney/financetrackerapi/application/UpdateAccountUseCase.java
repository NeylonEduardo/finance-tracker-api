package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.dto.AccountOutput;
import com.ney.financetrackerapi.dto.UpdateAccountRequest;
import com.ney.financetrackerapi.exception.AccountNotFoundException;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAccountUseCase {

    private final AccountRepository repository;

    public UpdateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountOutput execute(AccountId id, UpdateAccountRequest request) {
        AccountEntity account = repository.findById(id)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account not found")
                );

        account.setName(request.name());
        account.setType(request.type());

        repository.save(account);

        return AccountOutput.from(account);
    }
}
