package org.koushik.javabrains.messenger.filter;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecuredFilter implements ContainerRequestFilter{
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	public static final String AUTHORIZATION_URL_PATH = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		
		if(requestContext.getUriInfo().getPath().contains(AUTHORIZATION_URL_PATH)){
		
			if(authHeader != null && authHeader.size() >0){
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String userPass = Base64.decodeAsString(authToken);
				StringTokenizer tokens = new StringTokenizer(userPass, ":");
				String userName = tokens.nextToken();
				String password = tokens.nextToken();
				
				System.out.println("User Name : "+ userName + " and Password : "+password);
				
				if("user".equals(userName) && "password".equals(password) ){
					return;
				}
			}
		}		
		
		Response unauthorisedResponse = Response.status(Status.UNAUTHORIZED).entity("Unauthorized request ..").build();
		requestContext.abortWith(unauthorisedResponse);
	}

}
