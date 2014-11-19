package org.renci.hearsay.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.GeneConditionAssertion;

@Path("/GeneConditionAssertionService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GeneConditionAssertionService {

    @GET
    @Path("/findById/{id}")
    public GeneConditionAssertion findById(@PathParam("id") Long id);

    @POST
    @Path("/")
    public Long save(GeneConditionAssertion geneConditionAssertion);

}
