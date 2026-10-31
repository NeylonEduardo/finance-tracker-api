package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.AccountOutput;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAccountsUseCase {
    AccountRepository repository;

    public GetAllAccountsUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountOutput> execute() {
        return repository.findAll()
                .stream()
                .map(AccountOutput::from)
                .toList();
    }
}