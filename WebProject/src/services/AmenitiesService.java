package services;

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

import dao.AmenitiesDAO;
import model.Amenities;

@Path("/amenities")
public class AmenitiesService {

	@Context
	ServletContext ctx;
	
	public AmenitiesService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("amenitiesDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("amenitiesDAO", new AmenitiesDAO(contextPath));
		}
	}
	
	@POST
	@Path("/addAmenities")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response amenities(Amenities amenitie, @Context HttpServletRequest request) {
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		System.out.println("successfully added");
		boolean successfulAdd = amenitiesDAO.addAmenities(amenitie);
		if(successfulAdd) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).build();
		}
		
	}
	
	@GET
	@Path("/findAllAmenities")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Amenities> findAll(@Context HttpServletRequest request){
		AmenitiesDAO amenitiesDAO = (AmenitiesDAO) ctx.getAttribute("amenitiesDAO");
		return amenitiesDAO.getAmenities();
		
		
	}
	
	
	
	
}
