package org.renci.hearsay.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.renci.hearsay.dao.model.Identifier;

@Path("/IdentifierService/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public interface IdentifierService {

    @GET
    @Path("/findById/{id}")
    public Identifier findById(@PathParam("id") Long id);

    @GET
    @Path("/findBySystem/{system}")
    public List<Identifier> findBySystem(@PathParam("system") String system);

    @GET
    @Path("/findBySystemAndValue/{system}/{value}")
    public Identifier findBySystemAndValue(@PathParam("system") String system, @PathParam("value") String value);

}
