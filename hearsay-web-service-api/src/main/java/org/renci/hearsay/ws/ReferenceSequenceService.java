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
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface ReferenceSequenceService {

    @GET
    @Path("/findById/{id}")
    public ReferenceSequence findById(@PathParam("id") Long id);

    @GET
    @Path("/findByGeneId/{geneId}")
    public List<ReferenceSequence> findByGeneId(@PathParam("geneId") Long geneId);

    @GET
    @Path("/findByIdentifierSystemAndValue/{system}/{value}")
    public List<ReferenceSequence> findByIdentifierSystemAndValue(@PathParam("system") String system, @PathParam("value") String value);

    @GET
    @Path("/findByIdentifierSystem/{system}")
    public List<ReferenceSequence> findByIdentifierSystem(@PathParam("system") String system);

}
