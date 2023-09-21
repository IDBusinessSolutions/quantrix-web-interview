package com.quantrix.interview.server.domain;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuSection
{
    private final String title;

    private final List<MenuItem> items;

    @JsonCreator
    public MenuSection(
        @JsonProperty("title") String title,
        @JsonProperty("items") List<MenuItem> items
    )
    {
        this.title = Validate.notBlank(title);
        this.items = Validate.notEmpty(items);
    }

    @JsonProperty("title")
    public String getTitle()
    {
        return title;
    }

    @JsonProperty("items")
    public List<MenuItem> getItems()
    {
        return items;
    }
}
