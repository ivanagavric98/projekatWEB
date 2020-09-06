package services;

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

import model.User;
import dao.UserDAO;

@Path("")
public class LogInService {
	
	@Context
	ServletContext ctx;
	
	public LogInService() {
	}
	
	
	@PostConstruct
	public void init() {

		if (ctx.getAttribute("userDAO") == null) {
			String contextPath = ctx.getRealPath("");  
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
	
	@POST
	@Path("/registration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNewUser(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		System.out.println("registration started");
		boolean successfulRegistration = userDao.registerUser(user);
		
		if(successfulRegistration) {
			return Response.status(200).build();
		}else {
			return Response.status(400).entity("Username or password is already used.").build();
			
		}
	}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		
		User logged = userDao.findUser(user.getUsername(), user.getPassword());
		
		if (logged != null) {
			request.getSession().setAttribute("user", logged);
			return Response.status(200).build();
		}
		
		return Response.status(400).entity("Username or password is incorrect.").build();
	}
	
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
}