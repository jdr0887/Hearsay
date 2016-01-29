package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.ContextualAllele;

@Path("/ContextualAlleleService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ContextualAlleleService {

    @GET
    @Path("/findById/{id}")
    public ContextualAllele findById(@PathParam("id") Long id);

    @GET
    @Path("/findByName/{name}")
    public List<ContextualAllele> findByName(@PathParam("name") String name);

}
