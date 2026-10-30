package com.ney.financetrackerapi.domain.entities;

import com.ney.financetrackerapi.enums.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Profile("test")
class AccountEntityTest {

    private final List<AccountEntity> repository = new ArrayList<>();

    @Test
    void should_createAccount(){
        AccountEntity entity = (new AccountEntity(
                "Neylon Eduardo",
                AccountType.CASH,
                new BigDecimal(2000)
        ));

        repository.add(entity);

        assertThat(entity.name).isEqualTo("Neylon Eduardo");
        assertThat(entity.type).isEqualTo(AccountType.CASH);
        assertThat(entity.initialBalance).isEqualTo(new BigDecimal(2000));

        assertThat(repository.size()).isEqualTo(1);
    }

}