package com.ney.financetrackerapi.application;

import com.ney.financetrackerapi.dto.TransactionOutput;
import com.ney.financetrackerapi.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GetAllTransactionsUseCase {
    private final TransactionRepository repository;

    public GetAllTransactionsUseCase(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<TransactionOutput> execute(Pageable pageable, String sortBy) {
        String sortField = switch (sortBy.toUpperCase()) {
            case "DESCRIPTION" -> "description";
            case "AMOUNT" -> "amount";
            case "TYPE" -> "type";
            case "DATE" -> "date";

            default -> throw new IllegalStateException("Unexpected value: " + sortBy);
        };

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(sortField)
        );

        return repository.findAll(sortedPageable)
                .map(TransactionOutput::from);
    }
}