package com.ney.financetrackerapi.model;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity {

    @EmbeddedId
    private AccountId id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private AccountType type;

    private BigDecimal initialBalance;

    public AccountEntity(String name, AccountType type, BigDecimal initialBalance) {
        Assert.notNull(name, "Name is mandatory");
        Assert.notNull(type, "Name is mandatory");
        Assert.notNull(initialBalance, "Name is mandatory");

        this.id = new AccountId();
        this.name = name;
        this.type = type;
        this.initialBalance = initialBalance;
    }
}