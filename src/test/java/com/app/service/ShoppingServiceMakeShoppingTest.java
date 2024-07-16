package com.app.service;

import com.app.model.client.Client;
import com.app.model.product.Product;
import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServiceMakeShoppingTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    @DisplayName("When making shopping")
    void test() {
        var expected = new HashMap<Client, List<Product>>() {
            {
                put(new Client(3, "Lili", "Syberyjska", 8, BigDecimal.valueOf(88630),
                        "2"), List.of(
                        new Product(3, "WAHACZ", BigDecimal.valueOf(210), 2),
                        new Product(3, "WAHACZ", BigDecimal.valueOf(210), 2)
                ));
                put(new Client(1, "Jan", "Kowal", 20, BigDecimal.valueOf(63),
                        "1,2"), List.of(
                        new Product(2, "PASTA_DO_ZEBOW", BigDecimal.valueOf(8), 1),
                        new Product(1, "PERFUMY", BigDecimal.valueOf(129), 1)
                ));
                put(new Client(2, "Simba", "Nowak", 3, BigDecimal.valueOf(1797),
                        "1,3"), List.of(
                        new Product(4, "CHLEB", BigDecimal.valueOf(3), 3)
                ));
            }
        };
        Assertions.assertThat(service.makeShopping()).isEqualTo(expected);
    }
}
