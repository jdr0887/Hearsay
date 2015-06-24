package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.Alignment;

@Path("/AlignmentService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AlignmentService {

    @GET
    @Path("/findByReferenceSequenceId/{id}")
    public List<Alignment> findByReferenceSequenceId(@PathParam("id") Long id);

    @GET
    @Path("/findById/{id}")
    public Alignment findById(@PathParam("id") Long id);

}
