package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.TranscriptInterval;

@Path("/TranscriptIntervalService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TranscriptIntervalService {

    @GET
    @Path("/findByTranscriptId/{id}")
    public List<TranscriptInterval> findByTranscriptId(@PathParam("id") Long id);

    @GET
    @Path("/findById/{id}")
    public TranscriptInterval findById(@PathParam("id") Long id);

    @POST
    @Path("/")
    public Long save(TranscriptInterval transcriptInterval);

}
