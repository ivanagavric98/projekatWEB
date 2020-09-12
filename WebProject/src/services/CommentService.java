package services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ApartmentDAO;
import dao.CommentDAO;
import dao.UserDAO;
import model.Apartment;
import model.Comment;
import model.User;

@Path("comment")
public class CommentService {

	@Context
	ServletContext ctx;
	
	public CommentService() {
		
	}
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, IOException {
	
		if (ctx.getAttribute("commentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("commentDAO", new CommentDAO(contextPath));
		}else if(ctx.getAttribute("apartmentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("apartmentDAO", new ApartmentDAO(contextPath));
		}
	}
	
	@GET
	@Path("/findAllComments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Comment> findAll(@Context HttpServletRequest request){
		CommentDAO commentsDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		return commentsDAO.findAllComments();		
	
	}
	/*
	@POST
	@Path("/createComment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response comments(Comment comment, 		Apartment apartment, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		CommentDAO commentsDAO = (CommentDAO) ctx.getAttribute("commentDAO");
		System.out.println("successfully added");
		
		User guest = (User) request.getSession().getAttribute("user");
		comment.setGuest(guest.getUsername());
		ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		
		
		Comment successfulAdd = commentsDAO.addNewComment(comment);		
		
		apartmentDAO.addNewComment(apartment, successfulAdd);
		if(successfulAdd != null) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).entity("Apartment is already exist.").build();
		}
		
	}
	
	*/
	
	
}
