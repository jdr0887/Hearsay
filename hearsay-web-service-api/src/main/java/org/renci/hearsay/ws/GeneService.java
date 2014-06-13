package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.Gene;

@Path("/GeneService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GeneService {

    @GET
    @Path("/findAll")
    public List<Gene> findAll();

    @GET
    @Path("/findById/{id}")
    public Gene findById(@PathParam("id") Long id);

    @POST
    @Path("/")
    public Long save(Gene gene);

}
