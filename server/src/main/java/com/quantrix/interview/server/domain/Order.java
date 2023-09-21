package com.quantrix.interview.server.domain;

import static java.util.Collections.unmodifiableList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order
{
    private final Table table;

    private final List<MenuItem> items;

    public Order(Table table)
    {
        this.table = Validate.notNull(table);
        this.items = new CopyOnWriteArrayList<>();
    }

    @JsonProperty("table")
    public Table getTable()
    {
        return table;
    }

    public void addItem(MenuItem item)
    {
        Validate.notNull(item);
        items.add(item);
    }

    @JsonProperty("items")
    public List<MenuItem> getItems()
    {
        return unmodifiableList(items);
    }
}
