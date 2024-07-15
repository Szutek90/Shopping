package com.app.model.product;

import java.math.BigDecimal;
import java.util.function.Function;

public interface ProductMapper {
    Function<Product, BigDecimal> toPrice = product -> product.price;
    Function<Product, Integer> toCategoryId = product -> product.categoryId;
}
