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

import dao.ApartmentDAO;

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
	
	@GET
	@Path("/findAllApartments")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> findAll(@Context HttpServletRequest request){
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		return apartmentsDAO.findAll();		
	
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
	/*
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> searchApById(@PathParam("id") String id, @Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("pretraga apartmana pocela");

		 return apartmentsDAO.searchApById(id);
		
	
	}
	/*
	@DELETE
	@Path("/")
	public Response deleteApartment(@QueryParam("id") Long id, @Context HttpServletRequest request)  throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");

		boolean succesfulDeleted = apartmentsDAO.deleteApartment(id);
		if(succesfulDeleted) {
			return Response.status(200).build();
		}
		return Response.status(400).entity("Id is not existed!").build();
	
	}
*/
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Apartment edit(@PathParam("id") Long id,
						 Apartment apartment,	
						 @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		return apartmentsDAO.editApartmanData(id,apartment);
	}
	
	/*
//--------------------------
	//sortiranje apartmana po broju soba
	@GET
	@Path("/{par}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> getSortApartmentByRoomNumber(@PathParam("par") String par,
						@Context HttpServletRequest request) {
		System.out.println("*****SORTIRANJE PO BROJU SOBA*****");
		ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		Collection<Apartment> ret = apartmentDAO.getSort(par);
		return ret;
	}
	*/
//------------------------------------
	//sortiranje apartmana po broju gostiju
	
	@GET
	@Path("/{par}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> getSortApartmentByGuestNumber(@PathParam("par") String par,
						@Context HttpServletRequest request) {
		System.out.println("*****SORTIRANJE PO BROJU GOSTIJU*****");
		ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		Collection<Apartment> ret = apartmentDAO.getSortByGuestsNumber(par);
		return ret;
	}
	
}
