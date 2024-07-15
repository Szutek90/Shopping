package com.app.model.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public interface ClientMapper {
    Function<Client, BigDecimal> toMoney = client -> client.money;
    Function<Client, List<Integer>> toPreferences = client -> client.preferences;
}
