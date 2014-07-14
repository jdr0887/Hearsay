package org.renci.hearsay.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
    @Path("/findByAccession/{accession}")
    public ReferenceSequence findByAccession(@PathParam("accession") String accession);

    @POST
    @Path("/")
    public Long save(ReferenceSequence referenceSequence);

}
