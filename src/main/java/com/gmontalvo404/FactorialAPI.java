package com.gmontalvo404;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/factorial")
public class FactorialAPI {

    private final FactorialService factorialService;

    public FactorialAPI(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Long getFactorial(@QueryParam("number") Long number) {
        return factorialService.getFactorialWithCache(number);
    }
}
