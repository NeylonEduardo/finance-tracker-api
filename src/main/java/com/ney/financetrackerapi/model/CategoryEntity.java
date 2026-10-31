package com.ney.financetrackerapi.model;

import com.ney.financetrackerapi.domain.CategoryId;
import com.ney.financetrackerapi.enums.CategoryType;
import jakarta.persistence.*;
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
        private CategoryId id;

        @NotEmpty(message = "Name is mandatory")
        private String name;

        @NotEmpty(message = "Type is mandatory")
        @Enumerated(EnumType.STRING)
        @Column(nullable = false, unique = true)
        private CategoryType type;

        public CategoryEntity(String name, CategoryType type) {
            Assert.notNull(name, "Name is mandatory");
            Assert.notNull(type, "Type is mandatory");

            this.id = new CategoryId();
            this.name = name;
            this.type = type;
        }
}
