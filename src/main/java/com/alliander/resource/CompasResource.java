package com.alliander.resource;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alliander.service.BaseXService;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/database")
public class CompasResource {

    /**
     * Hardcoded BaseX choice
     */
    @Inject
    BaseXService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String initial() throws IOException {
        return service.executeCommand("list");
    }

    @DELETE
    @Path("/{database}")
    @Produces(MediaType.TEXT_XML)
    public String dropDatabase(@PathParam String database) throws IOException {
        return service.executeCommand("drop db ".concat(database));
    }

    @PUT
    @Path("/{database}")
    @Produces(MediaType.TEXT_XML)
    public String addDatabase(@PathParam String database, String file) throws IOException {
        return service.executeCommand("create db ".concat(database).concat(" ").concat(file));
    }

    @POST
    @Path("/{database}/query/")
    @Produces(MediaType.TEXT_XML)
    public String query(@PathParam String database, String query) throws IOException {
        return service.executeQuery(database, query);
    }

    @POST
    @Path("/command")
    @Produces(MediaType.TEXT_XML)
    public String command(String command) throws IOException {
        return service.executeCommand(command);
    }
}