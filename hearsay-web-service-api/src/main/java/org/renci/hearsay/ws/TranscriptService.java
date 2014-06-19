package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.Transcript;

@Path("/TranscriptService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TranscriptService {

    @GET
    @Path("/findAll")
    public List<Transcript> findAll();

    @GET
    @Path("/findById/{id}")
    public Transcript findById(@PathParam("id") Long id);

    @GET
    @Path("/findByGeneId/{geneId}")
    public List<Transcript> findByGeneId(@PathParam("geneId") Long geneId);

    @GET
    @Path("/findByGeneName/{geneName}")
    public List<Transcript> findByGeneName(@PathParam("geneName") String geneName);

    @POST
    @Path("/")
    public Long save(Transcript transcript);

}
