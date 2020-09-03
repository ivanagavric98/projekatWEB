package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/users")
public class UserService {
	
	@Context
	ServletContext ctx;	//koristimo ga jer postoje neki udaljeni objekti kojima treba da pristupimo

}
