package com.ney.financetrackerapi.domain.entities;

import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.enums.CategoryType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class CategoryEntity{

        @EmbeddedId
        @NotNull(message = "Id is mandatory")
        CategoryId id;

        @NotEmpty(message = "Name is mandatory")
        String name;

        @NotEmpty(message = "Type is mandatory")
        CategoryType type;

        public CategoryEntity(String name, CategoryType type) {
            Assert.notNull(name, "Name is mandatory");
            Assert.notNull(type, "Name is mandatory");

            this.id = new CategoryId();
            this.name = name;
            this.type = type;
        }
}
