package org.koushik.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.koushik.javabrains.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		System.out.println(exception.toString());
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), "www.javabrains.in", 404);
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
