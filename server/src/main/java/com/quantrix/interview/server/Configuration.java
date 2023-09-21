package com.quantrix.interview.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quantrix.interview.server.domain.Menu;
import com.quantrix.interview.server.domain.Table;

/**
 * Loads application configuration from JSON files to initialize the menu and list
 * of tables.
 *
 * In a real application, we'd have something more involved like a database, but
 * we've simplified this piece for this exercise.
 *
 * You don't need to know the implementation details of this class for the
 * assessment.
 */
public enum Configuration
{
    INSTANCE;

    private final Menu menu;
    private final List<Table> tables;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    Configuration()
    {
        this.menu = readJson("menu", Menu.class);
        this.tables = readJson("tables", new TypeReference<List<String>>(){})
                        .stream()
                        .map(Table::new)
                        .collect(Collectors.toList());
    }

    public Menu getMenu()
    {
        return menu;
    }

    public List<Table> getTables()
    {
        return tables;
    }

    private <T> T readJson(String fileName, Class<T> outputType)
    {
        return readJson(fileName, (mapper, input) -> mapper.readValue(input, outputType));
    }

    private <T> T readJson(String fileName, TypeReference<T> outputType)
    {
        return readJson(fileName, (mapper, input) -> mapper.readValue(input, outputType));
    }

    interface Reader<T>
    {
        T read(ObjectMapper mapper, InputStream stream) throws IOException;
    }

    private <T> T readJson(String fileName, Reader<T> reader)
    {
        try (InputStream is = Configuration.class.getResourceAsStream(String.format("/configuration/%s.json", fileName)))
        {
            if (is == null)
                throw new RuntimeException("Configuration file not found: " + fileName);

            return reader.read(OBJECT_MAPPER, is);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Exception reading configuration", e);
        }
    }
}
