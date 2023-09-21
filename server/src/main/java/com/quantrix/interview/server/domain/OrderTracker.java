package com.quantrix.interview.server.domain;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.Validate;

public enum OrderTracker
{
    INSTANCE;

    private final Map<Table, Order> ordersByTable;

    OrderTracker()
    {
        ordersByTable = new ConcurrentHashMap<>();
    }

    public Optional<Order> getOrder(Table table)
    {
        Validate.notNull(table);

        return Optional.ofNullable(ordersByTable.get(table));
    }

    public Order addToOrder(Table table, MenuItem item)
    {
        // TODO: implement this method

        return new Order(table); // remove this default value
    }
}
