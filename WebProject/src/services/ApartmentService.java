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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.AmenitiesDAO;
import dao.ApartmentDAO;
import model.Amenities;
import model.Apartment;

@Path("apartment")
public class ApartmentService {

	@Context
	ServletContext ctx;
	
	public ApartmentService() {
		
	}
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, IOException {
	
		if (ctx.getAttribute("apartmentDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("apartmentDAO", new ApartmentDAO(contextPath));
		}
		
	}
	
	@POST
	@Path("/addApartment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response apartments(Apartment apartment, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("successfully added");
		
		String host_username = (String) request.getSession().getAttribute("users");
		apartment.setHost(host_username);
		Apartment successfulAdd = apartmentsDAO.addApartment(apartment);
		
		if(successfulAdd != null) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).entity("Apartment is already exist.").build();
		}
		
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> searchApById(@PathParam("id") long id, @Context HttpServletRequest request) {
		ApartmentDAO apDao = (ApartmentDAO) ctx.getAttribute("apDAO");
		Collection<Apartment> ret = ApartmentDAO.searchApById(id);
		System.out.println("pretraga apartmana pocela");
		
		return ret;
	
	}
	
}
