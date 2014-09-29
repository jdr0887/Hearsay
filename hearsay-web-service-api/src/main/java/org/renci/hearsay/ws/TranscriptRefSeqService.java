package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.TranscriptRefSeq;

@Path("/TranscriptRefSeqService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TranscriptRefSeqService {

    @GET
    @Path("/findAll")
    public List<TranscriptRefSeq> findAll();

    @GET
    @Path("/findById/{id}")
    public TranscriptRefSeq findById(@PathParam("id") Long id);

    @GET
    @Path("/findByAccession/{accession}")
    public List<TranscriptRefSeq> findByAccession(@PathParam("accession") String accession);

    @GET
    @Path("/findByGeneId/{geneId}")
    public List<TranscriptRefSeq> findByGeneId(@PathParam("geneId") Long geneId);

    @GET
    @Path("/findByGeneName/{geneName}")
    public List<TranscriptRefSeq> findByGeneName(@PathParam("geneName") String geneName);

    @POST
    @Path("/")
    public Long save(TranscriptRefSeq transcriptRefSeq);

}
