package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.SimpleAllele;

@Path("/SimpleAlleleService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface SimpleAlleleService {

    @GET
    @Path("/findById/{id}")
    public SimpleAllele findById(@PathParam("id") Long id);

    @GET
    @Path("/findByName/{name}")
    public List<SimpleAllele> findByName(@PathParam("name") String name);

}
