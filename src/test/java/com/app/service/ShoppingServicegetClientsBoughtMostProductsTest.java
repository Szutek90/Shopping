package com.app.service;

import com.app.model.client.Client;
import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServicegetClientsBoughtMostProductsTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    void test(){
        var salesStatement = service.makeShopping();
        Assertions.assertThat(service.getClientsBoughtMostProducts(salesStatement)).isEqualTo(
                List.of(new Client(3, "Lili", "Syberyjska", 8,
                        BigDecimal.valueOf(88630),"2"),
                        new Client(1, "Jan", "Kowal", 20, BigDecimal.valueOf(63),
                                "1,2"))
        );
    }
}
