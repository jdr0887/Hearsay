package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.ReferenceSequence;

@Path("/ReferenceSequenceService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReferenceSequenceService {

    @GET
    @Path("/findById/{id}")
    public ReferenceSequence findById(@PathParam("id") Long id);

    @GET
    @Path("/findByGeneId/{geneId}")
    public List<ReferenceSequence> findByGeneId(@PathParam("geneId") Long geneId);

    @GET
    @Path("/findByIdentifierValue/{value}")
    public List<ReferenceSequence> findByIdentifierValue(@PathParam("value") String value);

}
