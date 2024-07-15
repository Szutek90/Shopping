package com.app.model.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class ProductTest {

    @Test
    @DisplayName("When parsing")
    void test() {
        Assertions.assertThat(Product.parse("1;PERFUMY;2;129;1")).isEqualTo(
                Map.of(new Product(1, "PERFUMY", BigDecimal.valueOf(129), 1),
                        2));
    }
}
