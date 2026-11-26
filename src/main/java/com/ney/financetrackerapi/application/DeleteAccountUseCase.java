package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.exception.AccountNotFoundException;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public DeleteAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void execute(AccountId id) {
        AccountEntity deleteAccount = accountRepository.findById(id)
                .orElseThrow(
                        () -> new AccountNotFoundException("Account not found")
                );

        accountRepository.delete(deleteAccount);
    }
}
