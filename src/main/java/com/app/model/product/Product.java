package com.app.model.product;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Product {
    private final int id;
    private final String name;
    final BigDecimal price;
    final int categoryId;

    public static Map<Product, Integer> parse(String line) {
        var splitted = line.split(";");
        var map = new HashMap<Product, Integer>();
        map.put(new Product(Integer.parseInt(splitted[0]), splitted[1],
                        new BigDecimal(splitted[3]), Integer.parseInt(splitted[4])),
                Integer.parseInt(splitted[2]));
        return map;
    }

    public boolean hasTheSameCategory(int categoryId) {
        return this.categoryId == categoryId;
    }

}
