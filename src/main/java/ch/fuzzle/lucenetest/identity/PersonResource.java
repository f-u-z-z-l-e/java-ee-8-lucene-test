package ch.fuzzle.lucenetest.identity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Stateless
@Path("person")
public class PersonResource {
    @Context
    private UriInfo uriInfo;
    @Inject
    private PersonRepository repository;

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int id) {
        Person person = repository.readPerson(id);

        if (person == null) {
            return Response.status(NOT_FOUND).build();
        }

        return Response.ok(person).build();
    }

    @POST
    @Path("/")
    @Consumes(APPLICATION_JSON)
    public Response createPerson(Person person) {
        Integer personId = repository.createPerson(person);
        URI location = UriBuilder.fromUri(uriInfo.getRequestUri()).path("/{personId}").build(personId);
        return Response.created(location).build();
    }
}
