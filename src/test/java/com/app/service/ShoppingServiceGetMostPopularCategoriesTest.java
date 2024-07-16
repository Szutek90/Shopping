package com.app.service;

import com.app.service.extension.ShoppingServiceInitializer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;

@ExtendWith(ShoppingServiceInitializer.class)
class ShoppingServiceGetMostPopularCategoriesTest {
    static ShoppingService service;

    @BeforeAll
    static void beforeAll() {
        service = ShoppingServiceInitializer.service;
    }

    @Test
    void test() {
        var salesStatement = service.makeShopping();
        var expectedResult = Map.of(
                1,List.of("PIECZYWO"),
                2, List.of("KOSMETYKI", "MOTORYZACJA")
        );
        Assertions.assertThat(service.getPopularByCategories(salesStatement))
                .containsExactlyInAnyOrderEntriesOf(expectedResult);
    }
}
