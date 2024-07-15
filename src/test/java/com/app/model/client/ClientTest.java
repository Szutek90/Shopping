package com.app.model.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ClientTest {

    @Test
    @DisplayName("When parsing line")
    void test(){
        Assertions.assertThat(Client.parse("1;Jan;Kowal;20;200;1,2,4,7,8,9")).isEqualTo(
                new Client(1, "Jan", "Kowal", 20, BigDecimal.valueOf(200),
                        "1,2,4,7,8,9")
        );
    }
}
