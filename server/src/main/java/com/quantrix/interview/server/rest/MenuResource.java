package com.quantrix.interview.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.quantrix.interview.server.Configuration;
import com.quantrix.interview.server.domain.Menu;

@Path("menu")
public class MenuResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Menu get()
    {
        return Configuration.INSTANCE.getMenu();
    }
}
