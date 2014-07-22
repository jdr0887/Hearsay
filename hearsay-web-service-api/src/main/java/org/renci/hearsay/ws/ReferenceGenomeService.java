package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.ReferenceGenome;

@Path("/ReferenceGenomeService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReferenceGenomeService {

    @GET
    @Path("/findById/{id}")
    public ReferenceGenome findById(@PathParam("id") Long id);

    @GET
    @Path("/findByAll")
    public List<ReferenceGenome> findAll();

    @GET
    @Path("/findBySource/{source}")
    public List<ReferenceGenome> findBySource(@PathParam("source") String source);

    @GET
    @Path("/findBySourceAndVersion/{source}/{version}")
    public ReferenceGenome findBySourceAndVersion(@PathParam("source") String source,
            @PathParam("version") String version);

    @POST
    @Path("/")
    public Long save(ReferenceGenome referenceGenome);

}
