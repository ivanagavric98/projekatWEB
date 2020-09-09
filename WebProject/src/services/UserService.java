package services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
	public void init() throws NoSuchAlgorithmException, IOException {
		if (ctx.getAttribute("userDAO") == null) {
			String contextPath = ctx.getRealPath("");   
			ctx.setAttribute("userDAO", new UserDAO(contextPath));
		}
	}
	
//	localhost:8080/PocetniRest/user/marija@gmail.com
/*	@GET
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username, @Context HttpServletRequest request) {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.getUser(username);
	}

	*/
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User editPersonalData(@PathParam("username") String username,
						 User newUserData,	
						 @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		UserDAO userDao = (UserDAO) ctx.getAttribute("userDAO");
		return userDao.editPersonalData(username, newUserData);
	}
	//-------------------------------------------------

}
