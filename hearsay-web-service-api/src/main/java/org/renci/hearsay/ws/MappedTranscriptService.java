package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.MappedTranscript;

@Path("/MappedTranscriptService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface MappedTranscriptService {

    @GET
    @Path("/findByTranscriptId/{id}")
    public List<MappedTranscript> findByTranscriptId(@PathParam("id") Long id);

    @GET
    @Path("/findByTranscriptAccession/{accession}")
    public List<MappedTranscript> findByTranscriptAccession(@PathParam("accession") String accession);

    @GET
    @Path("/findByGeneName/{name}")
    public List<MappedTranscript> findByGeneName(@PathParam("name") String name);

    @GET
    @Path("/findById/{id}")
    public MappedTranscript findById(@PathParam("id") Long id);

    @POST
    @Path("/")
    public Long save(MappedTranscript mappedTranscript);

}
