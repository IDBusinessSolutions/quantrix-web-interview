package com.quantrix.interview.server.domain;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItem
{
    private final String name;

    @JsonCreator
    public MenuItem(@JsonProperty("name") String name)
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
        MenuItem menuItem = (MenuItem) o;
        return name.equals(menuItem.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name);
    }
}
