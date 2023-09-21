package com.quantrix.interview.server.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.quantrix.interview.server.Configuration;
import com.quantrix.interview.server.domain.Table;

@Path("tables")
public class TablesCollection
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Table> getTables()
    {
        return Configuration.INSTANCE.getTables();
    }

    @Path("{tableName}")
    public TableResource getTable(@PathParam("tableName") String tableName)
    {
        return Configuration.INSTANCE.getTables()
                                     .stream()
                                     .filter((t) -> t.getName().equals(tableName))
                                     .findFirst()
                                     .map(TableResource::new)
                                     .orElseThrow(() -> new NotFoundException("No such table"));
    }
}
