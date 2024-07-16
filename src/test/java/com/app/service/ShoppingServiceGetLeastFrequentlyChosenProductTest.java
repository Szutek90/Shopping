package com.app.service;

import com.app.model.product.Product;
import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServiceGetLeastFrequentlyChosenProductTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    void test() {
        var salesStatement = service.makeShopping();
        var expectedResult = new ArrayList<>(List.of(
                new Product(1, "PERFUMY", BigDecimal.valueOf(129), 1),
                new Product(2, "PASTA_DO_ZEBOW", BigDecimal.valueOf(8), 1),
                new Product(4, "CHLEB", BigDecimal.valueOf(3), 3)));
        Assertions.assertThat(service.getLeastFrequentlyChosenProduct(salesStatement))
                .containsExactlyInAnyOrderElementsOf(expectedResult);
    }
}

