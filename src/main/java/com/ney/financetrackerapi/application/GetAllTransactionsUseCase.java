package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.TransactionOutput;
import com.ney.financetrackerapi.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetAllTransactionsUseCase {
    TransactionRepository repository;

    public GetAllTransactionsUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<TransactionOutput> execute(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TransactionOutput::from);
    }
}