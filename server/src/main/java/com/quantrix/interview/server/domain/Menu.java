package com.quantrix.interview.server.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Menu
{
    private final List<MenuSection> sections;

    @JsonCreator
    public Menu(@JsonProperty("sections") List<MenuSection> sections)
    {
        this.sections = sections;
    }

    @JsonProperty("sections")
    public List<MenuSection> getSections()
    {
        return sections;
    }
}
