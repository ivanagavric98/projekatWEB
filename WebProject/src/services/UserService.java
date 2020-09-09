package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.UserDAO;
import model.User;

@Path("users")
public class UserService {

	@Context
	ServletContext ctx;
	
	public UserService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("userDAO") == null) {
			String p = ctx.getRealPath("")+"/data";   
			ctx.setAttribute("userDAO", new UserDAO(p));
		}
	}
	
//	localhost:8080/PocetniRest/user/marija@gmail.com
	@GET
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.getUser(username);
	}
	
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User editPersonalData(@PathParam("username") String username,
						 User newUserData,	
						 @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.editPersonalData(username, newUserData);
	}
	//-------------------------------------------------
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> searchUserByUsername(@PathParam("username") String username, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		Collection<User> ret = userDao.searchUserByUsername(username);
		System.out.println("pretraga pocela");
		
		return ret;
	
	}
}
