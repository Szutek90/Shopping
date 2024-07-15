package com.app.file_operator;

import com.app.file_operator.impl.ProductFileOperator;
import com.app.model.product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

class ProductFileOperatorTest {
    @Test
    @DisplayName("When loading Products")
    void test(){
        var operator = new ProductFileOperator();
        Assertions.assertThat(operator.load("src/test/resources/products.csv"))
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        new Product(1,"PERFUMY", BigDecimal.valueOf(129),1),2,
                        new Product(2,"NOKIA", BigDecimal.valueOf(1500),4),0
                ));
    }
}
