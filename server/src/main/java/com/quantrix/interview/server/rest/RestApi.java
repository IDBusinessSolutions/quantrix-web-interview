package com.quantrix.interview.server.rest;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.Application;

/**
 * Extends the JAX-RS {@link Application} class to define the resources
 * in our REST API. Registered with the container via web.xml.
 */
@SuppressWarnings("unused")
public class RestApi extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        return Stream.of(
            MenuResource.class,
            TablesCollection.class
        ).collect(Collectors.toSet());
    }
}
