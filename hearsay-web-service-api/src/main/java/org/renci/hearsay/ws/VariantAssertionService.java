package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.VariantAssertion;

@Path("/VariantAssertionService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface VariantAssertionService {

    @GET
    @Path("/findByGenomicVariantId/{id}")
    public List<VariantAssertion> findByGenomicVariantId(@PathParam("id") Long id);

}
