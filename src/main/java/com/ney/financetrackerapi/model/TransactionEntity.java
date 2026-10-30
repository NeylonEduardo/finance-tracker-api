package com.ney.financetrackerapi.model;

import com.ney.financetrackerapi.domain.TransactionId;
import com.ney.financetrackerapi.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {

    @EmbeddedId
    @NotNull(message = "Id is mandatory")
    private TransactionId id;

    @NotEmpty(message = "Description is mandatory")
    private String description;

    @NotEmpty(message = "Amount is mandatory")
    @Min(value = 1, message = "Amount must be 1 or more")
    private BigDecimal amount;

    @NotEmpty(message = "Type is mandatory")
    private TransactionType type;

    @NotEmpty(message = "Date is mandatory")
    private LocalDateTime date;

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

    public TransactionEntity(String description, BigDecimal amount, TransactionType type, LocalDateTime date) {
        Assert.notNull(description, "Description must not be null");
        Assert.notNull(amount, "Description must not be null");
        Assert.notNull(type, "Description must not be null");
        Assert.notNull(date, "Description must not be null");

        this.id = new TransactionId();
        this.amount = amount;
        this.type = type;
        this.date = date;
    }
}
