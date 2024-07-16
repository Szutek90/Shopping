package com.app.service;

import com.app.model.product.Product;
import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServiceGetMostFrequentlyChosenProductTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    void test(){
        var salesStatement = service.makeShopping();
        Assertions.assertThat(service.getMostFrequentlyChosenProduct(salesStatement)).isEqualTo(
                List.of(new Product(3, "WAHACZ", BigDecimal.valueOf(210), 2))
        );
    }
}
