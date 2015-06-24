package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.Feature;

@Path("/FeatureService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FeatureService {

    @GET
    @Path("/findById/{id}")
    public Feature findById(@PathParam("id") Long id);

    @GET
    @Path("/findByReferenceSequenceId/{referenceSequenceId}")
    public List<Feature> findByReferenceSequenceId(@PathParam("referenceSequenceId") Long referenceSequenceId);

}