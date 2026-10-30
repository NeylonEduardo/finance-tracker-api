package com.ney.financetrackerapi.domain.entities;

import com.ney.financetrackerapi.domain.AccountId;
import com.ney.financetrackerapi.enums.AccountType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class AccountEntity{

        @EmbeddedId
        @NotNull(message = "Id is mandatory")
        AccountId id;

        @NotEmpty(message = "Name is mandatory")
        String name;

        @NotEmpty(message = "Type is mandatory")
        AccountType type;

        @Min(value = 1, message = "Balance be must 1 or more")
        @NotEmpty(message = "Initial balance is mandatory")
        BigDecimal initialBalance;

        public AccountEntity(String name, AccountType type, BigDecimal initialBalance){
            Assert.notNull(name, "Name is mandatory");
            Assert.notNull(type, "Name is mandatory");
            Assert.notNull(initialBalance, "Name is mandatory");

            this.id = new AccountId();
            this.name= name;
            this.type = type;
            this.initialBalance = initialBalance;
        }

}


