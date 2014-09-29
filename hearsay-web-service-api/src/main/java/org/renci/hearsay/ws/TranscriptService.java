package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.TranscriptSequence;

@Path("/TranscriptService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TranscriptService {

    @GET
    @Path("/findAll")
    public List<TranscriptSequence> findAll();

    @GET
    @Path("/findById/{id}")
    public TranscriptSequence findById(@PathParam("id") Long id);

    @GET
    @Path("/findByAccession/{accession}")
    public List<TranscriptSequence> findByAccession(@PathParam("accession") String accession);

    @GET
    @Path("/findByGeneId/{geneId}")
    public List<TranscriptSequence> findByGeneId(@PathParam("geneId") Long geneId);

    @GET
    @Path("/findByGeneName/{geneName}")
    public List<TranscriptSequence> findByGeneName(@PathParam("geneName") String geneName);

    @POST
    @Path("/")
    public Long save(TranscriptSequence transcript);

}
