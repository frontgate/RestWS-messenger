package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Creating sub-resource of message resource
 * @author mohossai
 *
 */

@Path("/")
public class CommentResource {
	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("messageId") String messageId, @PathParam("commentId") long commentId){
		String.valueOf(commentId);
		return "comment sub-resource : "+messageId+" CommentId : " +commentId;
	}

}
