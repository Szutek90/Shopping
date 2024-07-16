package com.app.service.extension;

import com.app.model.client.Client;
import com.app.model.preference.Preference;
import com.app.model.product.Product;
import com.app.service.ShoppingService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingServiceInitializer implements TestInstancePostProcessor {
    public static ShoppingService service;

    public ShoppingServiceInitializer() {
        var preferences = new ArrayList<>(List.of(
                new Preference(1, "KOSMETYKI"),
                new Preference(2, "MOTORYZACJA"),
                new Preference(3, "PIECZYWO")));
        var products = new HashMap<Product, Integer>() {
            {
                put(new Product(1, "PERFUMY", BigDecimal.valueOf(129), 1), 1);
                put(new Product(2, "PASTA_DO_ZEBOW", BigDecimal.valueOf(8), 1), 1);
                put(new Product(3, "WAHACZ", BigDecimal.valueOf(210), 2), 2);
                put(new Product(4, "CHLEB", BigDecimal.valueOf(3), 3), 1);

            }
        };
        var clients = new ArrayList<>(List.of(
                new Client(1, "Jan", "Kowal", 20, BigDecimal.valueOf(200),
                        "1,2"),
                new Client(2, "Simba", "Nowak", 3, BigDecimal.valueOf(1800),
                        "1,3"),
                new Client(3, "Lili", "Syberyjska", 8, BigDecimal.valueOf(89050),
                        "2")

        ));
        service = new ShoppingService(preferences, products, clients);
    }

    @Override
    public void postProcessTestInstance(Object o, ExtensionContext extensionContext) throws Exception {

    }
}