package com.ney.financetrackerapi.repository;

import com.ney.financetrackerapi.domain.TransactionId;
import com.ney.financetrackerapi.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, TransactionId> {
}
