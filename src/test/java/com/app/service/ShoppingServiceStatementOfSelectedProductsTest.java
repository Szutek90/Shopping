package com.app.service;

import com.app.model.product.Product;
import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Map;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServiceStatementOfSelectedProductsTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    void test(){
        var salesStatement = service.makeShopping();
        var expectedMap = Map.of(
                new Product(3, "WAHACZ", BigDecimal.valueOf(210), 2),2,
                new Product(4, "CHLEB", BigDecimal.valueOf(3), 3),1,
                new Product(2, "PASTA_DO_ZEBOW", BigDecimal.valueOf(8), 1),1,
                new Product(1, "PERFUMY", BigDecimal.valueOf(129), 1), 1
        );
        Assertions.assertThat(service.statementOfSelectedProducts(salesStatement))
                .containsExactlyInAnyOrderEntriesOf(expectedMap);
    }
}
