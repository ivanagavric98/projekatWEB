package services;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.AmenitiesDAO;
import model.Amenities;

@Path("/amenities")
public class AmenitiesService {

	@Context
	ServletContext ctx;
	
	public AmenitiesService() {
	}
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, IOException {
		if (ctx.getAttribute("amenitiesDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("amenitiesDAO", new AmenitiesDAO(contextPath));
		}
	}
	
	@POST
	@Path("/addAmenities")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response amenities(Amenities amenitie, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		System.out.println("successfully added");
		
		boolean successfulAdd = amenitiesDAO.addAmenities(amenitie);
		if(successfulAdd) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).entity("Amenitie is already exist.").build();
		}
		
	}
	
	@GET
	@Path("/findAllAmenities")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Amenities> findAll(@Context HttpServletRequest request){
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		return amenitiesDAO.getAmenities();
		
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Amenities edit(@PathParam("id") Long id,
						 Amenities amenitie,	
						 @Context HttpServletRequest request) {
		AmenitiesDAO amenitiesDao = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		return amenitiesDao.editAmenitie(id, amenitie);
	}
	
	
	
	@PUT
	@Path("delete/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteByName(@PathParam("name") String name, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		System.out.println(name);

		boolean succesfulDeleted = amenitiesDAO.deleteAmenitie(name);
		if(succesfulDeleted) {
			return Response.status(200).build();
		}
		return Response.status(400).entity("Error deleting!").build();
	
	}
	
	
}
