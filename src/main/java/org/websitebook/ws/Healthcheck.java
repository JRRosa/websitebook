package org.websitebook.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/healthcheck")
@Produces(MediaType.TEXT_PLAIN)
public class Healthcheck {
	
	@GET
	public String ping() {
		return "Working and Running";
	}
}
