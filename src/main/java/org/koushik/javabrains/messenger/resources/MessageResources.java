package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.bean.MessageUtilBean;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
public class MessageResources {
	
	MessageService msgService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageUtilBean mBean){
		if(mBean.getYear() > 0){
			return msgService.getAllMessagesForYear(mBean.getYear());
		}
		
		if(mBean.getStart() >= 0 && mBean.getSize() >=0){
			return msgService.getAllMessagesPaginated(mBean.getStart(), mBean.getSize());
		}
		return msgService.getAllMessages();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessageById(@PathParam ("id") long id) throws DataNotFoundException{
		return msgService.getMessage(id);
	}
	
	@POST
	//@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessages(@Context UriInfo uriInfo,Message message) {
		Message msg =  msgService.addMessage(message);
		/*return Response.status(Status.CREATED)
		.entity(msg)
		.build();*/ // For sending exact status 201 CREATED
		
		//Sending the right status and the new url of the resource created
		
		String newId = String.valueOf(msg.getId());
		
		//URI uri = new URI(uriInfo.getAbsolutePath()+newId); // This is also bad design
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
		.entity(msg)
		.build();
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message modifyMessages(Message message){
		return msgService.updateMessage(message);
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message removeMessage(@PathParam("id") long id){
		return msgService.removeMessage(id);
		
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getComments(){
		return new CommentResource();
	}

}
