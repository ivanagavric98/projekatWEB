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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.User;
import dao.UserDAO;
import enumeration.Gender;

@Path("")
public class LogInService {
	
	@Context
	ServletContext ctx;
	
	public LogInService() {
	}
	
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, IOException {

		if (ctx.getAttribute("userDAO") == null) {
			String contextPath = ctx.getRealPath("");  
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	@GET
	@Path("/searchUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> searchUserByUsername(@QueryParam("username") String username,
			@Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		Collection<User> ret = userDao.searchUserByUsername(username);
		System.out.println("pretraga pocela");
		
		return ret;
	
	}
	
	@GET
	@Path("/users/{username}/sort")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> sortUserByUsername(@PathParam("username") String username,
			@Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		Collection<User> ret = userDao.sortUserByUsername(username);
		
		return ret;
	
	}
	
	@POST
	@Path("/registration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNewUser(User user, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		System.out.println("registration started");
		boolean successfulRegistration = userDao.registerUser(user);
		
		if(successfulRegistration) {
			return Response.status(200).build();
		}else {
			return Response.status(400).entity("Username or password is already used.").build();
			
		}
	}
	@GET
	@Path("/filtrateRole/{role}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> filtrateUsersByRole(@PathParam("role") String role,
			@Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		 return userDao.filtrateUsersByRole(role);
	}
	
	@GET
	@Path("/filtrateGender/{gender}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> filtrateUsersByGender(@PathParam("gender") String gender, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
	 return userDao.filtrateUsersByGender(gender);
	 }
	

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> findAll(@Context HttpServletRequest request){
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.findAll();		
	
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user, @Context HttpServletRequest request) {
		System.out.println();
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		
		User logged = userDao.findUser(user.getUsername(), user.getPassword());
		
		if (logged != null) {
			request.getSession().setAttribute("user", logged);
			return Response.status(200).entity(logged).build();
		}
		
		return Response.status(400).entity("Username or password is incorrect.").build();
	}
	
	@PUT
	@Path("/user/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User editPersonalData(@PathParam("username") String username,
						 User newUserData,	
						 @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		System.out.println("editovanje korisnika");
		
		return userDao.editPersonalData(username, newUserData);
	} 
	
	@GET
	@Path("/user/{username}/username")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		
		
		User loginUser = (User) request.getSession(false).getAttribute("user");

		return userDao.getUser(loginUser.getUsername());
		
		} 
	
	@GET
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
}