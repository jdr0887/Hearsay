package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.HearsayDAOException;
import org.renci.hearsay.dao.model.Alignment;

@Path("/AlignmentService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface AlignmentService {

    @GET
    @Path("/findById/{id}")
    public Alignment findById(@PathParam("id") Long id);

    @GET
    @Path("/findByReferenceSequenceId/{referenceSequenceId}")
    public List<Alignment> findByReferenceSequenceId(@PathParam("referenceSequenceId") Long referenceSequenceId)
            throws HearsayDAOException;

}
