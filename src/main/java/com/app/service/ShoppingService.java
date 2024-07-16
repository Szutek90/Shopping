package com.app.service;

import com.app.model.client.Client;
import com.app.model.client.ClientMapper;
import com.app.model.preference.Preference;
import com.app.model.product.Product;
import com.app.model.product.ProductMapper;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@ToString
public class ShoppingService {
    private final Map<Integer, Preference> preferences;
    private final Map<Product, Integer> products;
    private final List<Client> clients;

    public ShoppingService(List<Preference> preferences, Map<Product, Integer> products, List<Client> clients) {
        this.preferences = preferences.stream()
                .collect(Collectors.toMap(Preference::id, preference -> preference));
        this.products = products;
        this.clients = clients;
    }

    public Map<Client, List<Product>> makeShopping() {
        var clientPurchases = new HashMap<Client, List<Product>>();
        for (var client : clients) {
            clientPurchases.put(client, new ArrayList<>());
            var clientMoney = ClientMapper.toMoney.apply(client);
            for (var clientPreference : ClientMapper.toPreferences.apply(client)) {
                var productsByPreference = getProductsByPreference(
                        preferences.get(clientPreference), clientMoney);
                while (!productsByPreference.isEmpty()) {
                    var currentClientMoney = ClientMapper.toMoney.apply(client);
                    var product = productsByPreference.firstEntry().getKey();
                    if (ProductMapper.toPrice.apply(product).compareTo(currentClientMoney) <= 0 &&
                            products.get(product) > 0) {
                        clientPurchases.get(client).add(product);
                        productsByPreference.put(product, productsByPreference.get(product) - 1);
                        client.decrementMoney(ProductMapper.toPrice.apply(product));
                        products.compute(product, (k, v) -> v == null ? null : v - 1);
                        if (productsByPreference.get(product) == 0) {
                            productsByPreference.remove(product);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return clientPurchases;
    }

    private SortedMap<Product, Integer> getProductsByPreference(Preference preference, BigDecimal clientMoney) {
        return products.entrySet().stream()
                .filter(prod -> prod.getKey().hasTheSameCategory(preference.id()))
                .filter(prod -> prod.getValue() > 0)
                .filter(prod -> ProductMapper.toPrice.apply(prod.getKey()).compareTo(clientMoney) <= 0)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        () -> new TreeMap<>(Comparator.comparing(ProductMapper.toPrice))
                ));
    }

    public List<Client> getClientsBoughtMostTotalValues(Map<Client, List<Product>> salesStatement) {
        return salesStatement.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getValue().stream().map(ProductMapper.toPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(IllegalStateException::new);

    }

    public List<Client> getClientsBoughtMostProducts(Map<Client, List<Product>> salesStatement) {
        return salesStatement.entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getValue().size(),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(IllegalStateException::new);
    }

    public Map<Product, Integer> statementOfSelectedProducts(Map<Client, List<Product>> salesStatement) {
        return salesStatement.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.groupingBy(product -> product,
                        Collectors.summingInt(product -> 1)));
    }

    public List<Product> getMostFrequentlyChosenProduct(Map<Client, List<Product>> salesStatement) {
        return statementOfSelectedProducts(salesStatement).entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(IllegalStateException::new);
    }

    public List<Product> getLeastFrequentlyChosenProduct(Map<Client, List<Product>> salesStatement) {
        return statementOfSelectedProducts(salesStatement).entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseThrow(IllegalStateException::new);
    }

    public Map<Integer, List<String>> getPopularByCategories(Map<Client, List<Product>> salesStatement) {
        return salesStatement.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.groupingBy(ProductMapper.toCategoryId,
                        Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(e -> preferences.get(e.getKey()).name(),
                                Collectors.toList())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e->e.getKey().intValue(),
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));
    }
}
