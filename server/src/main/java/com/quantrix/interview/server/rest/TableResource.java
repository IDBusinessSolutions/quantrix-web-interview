package com.quantrix.interview.server.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.Validate;

import com.quantrix.interview.server.domain.Order;
import com.quantrix.interview.server.domain.OrderTracker;
import com.quantrix.interview.server.domain.Table;

public class TableResource
{
    private final Table table;

    public TableResource(Table table)
    {
        this.table = Validate.notNull(table);
    }

    @GET
    @Path("order")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder()
    {
        return OrderTracker.INSTANCE.getOrder(table).orElseThrow(() -> new NotFoundException("No such table"));
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("order")
    public Order addToOrder(String itemName)
    {
        // TODO: implement this method

        return new Order(table); // remove this default value
    }
}
