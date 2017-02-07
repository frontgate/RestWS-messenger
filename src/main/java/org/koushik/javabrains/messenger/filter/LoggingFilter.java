package org.koushik.javabrains.messenger.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter,ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("Request Filter : "+requestContext.getHeaders());
		
	}

	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		System.out.println("Response Filter : " + responseContext.getHeaders());
		
	}

}
