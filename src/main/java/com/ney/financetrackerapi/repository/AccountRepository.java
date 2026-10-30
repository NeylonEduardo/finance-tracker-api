package com.ney.financetrackerapi.repository;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.domain.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, AccountId> {
}
