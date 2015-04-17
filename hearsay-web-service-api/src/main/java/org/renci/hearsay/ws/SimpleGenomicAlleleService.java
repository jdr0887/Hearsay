package org.renci.hearsay.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.SimpleGenomicAllele;

@Path("/SimpleGenomicAlleleService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SimpleGenomicAlleleService {

    @GET
    @Path("/findById/{id}")
    public SimpleGenomicAllele findById(@PathParam("id") Long id);

}
