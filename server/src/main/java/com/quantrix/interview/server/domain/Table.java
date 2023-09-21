package com.quantrix.interview.server.domain;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Table
{
    private final String name;

    @JsonCreator
    public Table(@JsonProperty("name") String name)
    {
        this.name = Validate.notBlank(name);
    }

    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return name.equals(table.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
