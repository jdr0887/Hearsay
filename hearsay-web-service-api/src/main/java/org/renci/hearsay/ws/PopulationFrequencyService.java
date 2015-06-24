package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.PopulationFrequency;

@Path("/PopulationFrequencyService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PopulationFrequencyService {

    @GET
    @Path("/findById/{id}")
    public PopulationFrequency findById(@PathParam("id") Long id);

    @GET
    @Path("/findBySourceAndVersion/{source}/{version}")
    public List<PopulationFrequency> findBySourceAndVersion(@PathParam("source") String source,
            @PathParam("version") String version);

}
