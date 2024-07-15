package com.app.file_operator.impl;

import com.app.file_operator.FileMapOperator;
import com.app.model.product.Product;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ProductFileOperator implements FileMapOperator<Product> {

    @Override
    public Map<Product, Integer> load(String filename) {
        try(var lines = Files.lines(Paths.get(filename))){
            var result = new HashMap<Product, Integer>();
            for(var line: lines.toList()){
                result.putAll(Product.parse(line));
            }
            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
