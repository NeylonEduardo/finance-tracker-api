package com.ney.financetrackerapi.model;

import com.ney.financetrackerapi.domain.TransactionId;
import com.ney.financetrackerapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {

    @EmbeddedId
    private TransactionId id;

    private String description;

    private BigDecimal amount;

    private TransactionType type;

    private LocalDate date;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    })
    private AccountEntity account;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    })
    private CategoryEntity category;

    public TransactionEntity(String description, BigDecimal amount, TransactionType type, LocalDate date, AccountEntity account, CategoryEntity category) {
        Assert.notNull(description, "Description must not be null");
        Assert.notNull(amount, "Description must not be null");
        Assert.notNull(type, "Description must not be null");
        Assert.notNull(date, "Description must not be null");

        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.account = account;
        this.category = category;
    }
}
