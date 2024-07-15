package com.app.model.client;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@ToString
@EqualsAndHashCode
public class Client {
    private final int id;
    private final String name;
    private final String surname;
    private final int age;
    BigDecimal money;
    final List<Integer> preferences;

    public Client(int id, String name, String surname, int age, BigDecimal money, String preferences) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.money = money;
        this.preferences = new LinkedList<>(
                Arrays.stream(preferences.split(",")).map(Integer::parseInt).toList()
        );
    }

    public void decrementMoney(BigDecimal shoppingPrice) {
        money = money.subtract(shoppingPrice);
    }

    public static Client parse(String line) {
        var splitted = line.split(";");
        return new Client(Integer.parseInt(splitted[0]), splitted[1], splitted[2],
                Integer.parseInt(splitted[3]), new BigDecimal(splitted[4]), splitted[5]);
    }
}
