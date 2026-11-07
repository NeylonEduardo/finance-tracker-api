package com.ney.financetrackerapi.model;

import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity {

    @EmbeddedId
    private CategoryId id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public CategoryEntity(String name, TransactionType type) {
        Assert.notNull(name, "Name is mandatory");
        Assert.notNull(type, "Type is mandatory");

        this.id = new CategoryId();
        this.name = name;
        this.type = type;
    }
}
