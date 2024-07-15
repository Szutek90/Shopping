package com.app.file_operator;

import com.app.file_operator.impl.ClientFileOperator;
import com.app.model.client.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

class ClientFileOperatorTest {

    @Test
    @DisplayName("When loading Clients")
    void test() {
        var operator = new ClientFileOperator();
        Assertions.assertThat(operator.load("src/test/resources/clients.csv"))
                .containsExactlyInAnyOrderElementsOf(List.of(
                        new Client(1, "Jan", "Kowal", 20, BigDecimal.valueOf(200),
                                "1,2,4,7,8,9"),
                        new Client(2, "Simba", "Nowak", 3, BigDecimal.valueOf(1800),
                                "4,5,6")));
    }
}
