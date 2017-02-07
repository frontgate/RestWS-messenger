package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemo {
	@GET
	@Path("annotations")
	public String getInjectObject(@MatrixParam("param") String matParam,
								  @HeaderParam("headerParam") String headerParam,
								  @CookieParam("name") String name){
		
		return "Matrix Param : " + matParam +"  Header param :" + headerParam + "  Cookie param : " + name;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers){
		String uri = uriInfo.getAbsolutePath().toString();
		String headersInfo = headers.getMediaType().toString();
		return "URI : " + uri +"  Header Info : "+headersInfo;
	}
}
