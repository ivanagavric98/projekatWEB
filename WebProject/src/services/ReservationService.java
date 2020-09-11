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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import dao.ApartmentDAO;
import dao.ReservationDAO;
import model.Apartment;
import model.Reservation;
import model.User;


@Path("reservation")
public class ReservationService {

	@Context
	ServletContext ctx;
	
	public ReservationService() {
		
	}
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, IOException {
	
		if (ctx.getAttribute("reservationDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("reservationDAO", new ReservationDAO(contextPath));
		}
		
	}
	
	@GET
	@Path("/findAllReservations")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Reservation> findAll(@Context HttpServletRequest request){
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
		return reservationDAO.findAll();		
	
	}

	@GET
	@Path("/findGuestReservations")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Reservation> findGuestRes(@Context HttpServletRequest request){
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
		User user = (User) request.getSession(false).getAttribute("user");
		
		return reservationDAO.findGuestReservation(user.getUsername());		
	
	}
	
	@GET
	@Path("/findHostReservations")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Reservation> findHostRes(@Context HttpServletRequest request){
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
		User user = (User) request.getSession(false).getAttribute("user");
		
		return reservationDAO.findHostReservation(user.getUsername());		
	
	}
	
	
	/*
	@POST
	@Path("/addReservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservations(Reservation reservation, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ReservationDAO reservationDAO = (ReservationDAO) ctx.getAttribute("reservationDAO");
		System.out.println("successfully added");
		
	
		
		Reservation successfulAdd = reservationDAO.addReservation(reservation, ld, 0, available);
		
		if(successfulAdd != null) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).entity("Apartment is already exist.").build();
		}
		
	}
	
	/*
	//--------------------------------------------------------------------------------------
		//pretraga apartmana po  id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> searchApById(@PathParam("id") String id, @Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("pretraga apartmana pocela");

		 return apartmentsDAO.searchApById(id);
	}
	*/
	//----------------------------------------------------------------------------
	//pretraga apartmana po  id+hostid
	@GET
	@Path("/{id}/{host}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> searchApByIdAndHost(@QueryParam("id") String id,
			@QueryParam("host") String host, @Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("pretraga apartmana pocela");

		 return apartmentsDAO.searchApByIdAndHost(id,host);
	}
	

}
