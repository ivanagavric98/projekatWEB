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
import dao.UserDAO;
import model.Apartment;
import model.User;


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
		
		User host = (User) request.getSession().getAttribute("user");
		String host_username = host.getUsername();
		
		apartment.setHost(host_username);
		Apartment successfulAdd = apartmentsDAO.addApartment(apartment);
		
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		userDAO.addHostApartment(host_username, successfulAdd.getId());
		
		if(successfulAdd != null) {
			return Response.status(200).build();	
		}else {
			return Response.status(400).entity("Apartment is already exist.").build();
		}
		
	}

	/*	
	@GET
	@Path("/hostApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> findActiveHostApartment(@Context HttpServletRequest request){
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		User user = (User) request.getSession(false).getAttribute("user");
		
		return apartmentsDAO.findAllHostApartment(user.getUsername());
	}
	*/
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
	/*
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
	*/
	/*
	//---------------------------------------------------------------------------------
	//filtracija apartmana po tipu
	@GET
	@Path("/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> filtrateApartmentsByType(@PathParam("type") String type,
			@Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("filtracija apartmana pocela");
		System.out.println(apartmentsDAO.filtrateApartmentsByType(type));
		 return apartmentsDAO.filtrateApartmentsByType(type);
	}
	
	*/
	/*
	//filtracija apartmana po statusu
		@GET
		@Path("/{status}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> filtrateApartmentsByStatus(@PathParam("status") String status,
				@Context HttpServletRequest request) {
			ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			System.out.println("filtracija apartmana pocela");
			 return apartmentsDAO.filtrateApartmentsByStatus(status);
		}
		*/
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
	/*
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
	*/
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
	
//------------------------------------
	//sortiranje apartmana po broju gostiju
	/*
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
	*/
	//------------------------------------
		//sortiranje apartmana po broju gostiju
/*
		@GET
		@Path("/{par}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> getSortByPricePerNight(@PathParam("par") String par,
							@Context HttpServletRequest request) {
			System.out.println("*****SORTIRANJE PO CIJENI NOCENJA*****");
			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			Collection<Apartment> ret = apartmentDAO.getSortByPricePerNight(par);
			return ret;
		}
	
	
	//---------------------------------------------------------------------------
	//pretrazivanje apartmana po lokaciji(grad)
			@GET
			@Path("/{city}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByLocation(@PathParam("city") String city,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po lokaciji");
				 return apartmentsDAO.searchApartmentsByLocation(city);
			}
			
	//-------------------------------------------------------------------------------
			//pretgara apartmana po cijeni (u rasponu od -do)
			@GET
			@Path("/{from}/{to}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByPriceAmount(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu cijene");
				 return apartmentsDAO.searchApartmentsByPriceAmount(from,to);
			}
			//-------------------------------------------------------------------------------
			//pretgara apartmana po broju soba(od-do)
			@GET
			@Path("/{from}/{to}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByRoomNumber(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByRoomNumber(from,to);
			}
			
			//-------------------------------------------------------------------------------
			//kombinovana pretraga apatrmana--ne radi provjeriti
			@GET
			@Path("/{city}/{fromPrice}/{toPrice}/{fromNumRoom}/{toNumRoom}/{guestNumber}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> combinedSearch(@QueryParam("city") String city,
					@QueryParam("fromPrice") String fromPrice,
					@QueryParam("toPrice") String toPrice,
					@QueryParam("fromNumRoom") String fromNumRoom,
					@QueryParam("toNumRoom") String toNumRoom,
					@PathParam("guestNumber") String guestNumber,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("kombinovana pretraga");
				 return apartmentsDAO.combinedSearch(city,fromPrice,toPrice,fromNumRoom,toNumRoom,guestNumber);
			}
	
			/*
			//-------------------------------------------------------------------------------
			//pretgara apartmana po broju datumu (u rasponu od-do)
			@GET
			@Path("/{from}/{to}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByDatePeriod(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana datumu od-do");
				 return apartmentsDAO.searchApartmentsByDatePeriod(from,to);
			}
	
			
						//--------------------------------------------------------------------------------
			//pretraga po maks broju gostiju
			@GET
			@Path("/{to}")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByMaxGuestNumber(@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByMaxGuestNumber(to);
			}
			
			//-----------------------------------------------------------------------------------
			
			
*/
}

