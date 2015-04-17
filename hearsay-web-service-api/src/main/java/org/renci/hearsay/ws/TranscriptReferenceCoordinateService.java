package org.renci.hearsay.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.TranscriptReferenceCoordinate;

@Path("/TranscriptReferenceCoordinateService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TranscriptReferenceCoordinateService {

    @GET
    @Path("/findById/{id}")
    public TranscriptReferenceCoordinate findById(@PathParam("id") Long id);

}
