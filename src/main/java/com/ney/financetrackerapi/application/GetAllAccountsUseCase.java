package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.AccountOutput;
import com.ney.financetrackerapi.model.AccountEntity;
import com.ney.financetrackerapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetAllAccountsUseCase {
    AccountRepository repository;

    public GetAllAccountsUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public List<AccountOutput> execute(String sortBy) {
        Comparator<AccountEntity> comparator = switch (sortBy.toUpperCase()) {
            case "NAME" -> Comparator.comparing(AccountEntity::getName);
            case "TYPE" -> Comparator.comparing(AccountEntity::getType);
            case "BALANCE" -> Comparator.comparing(AccountEntity::getInitialBalance);

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        return repository.findAll()
                .stream()
                .sorted(comparator)
                .map(AccountOutput::from)
                .toList();
    }
}