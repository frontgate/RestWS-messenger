package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/secured")
public class SecuredResources {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSecuredResources(){
		return "This is Secured Resources >>> !!!";
	}
}
