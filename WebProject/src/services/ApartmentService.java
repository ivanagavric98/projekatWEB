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
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		return apartmentsDAO.findAll();		
	
	}
	

	@POST
	@Path("/addAp")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response apartments(Apartment apartment, @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("successfully added ap");
		
		User host = (User) request.getSession().getAttribute("user");
		
		String host_username = host.getUsername();
		apartment.setHost(host_username);
		
		Apartment successfulAdd = apartmentsDAO.addApartment(apartment);
		System.out.println(apartment);
		
		UserDAO userDAO = (UserDAO) ctx.getAttribute("userDAO");
		userDAO.addHostApartment(host_username, successfulAdd.getId());
		
		if(successfulAdd != null) {
			return Response.status(200).build();	
		}
			return Response.status(400).entity("Apartment is already exist.").build();
		
		
	}

	
	@GET
	@Path("/hostApartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> findActiveHostApartment(@Context HttpServletRequest request){
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		User user = (User) request.getSession(false).getAttribute("user");
		
		return apartmentsDAO.findAllHostApartment(user.getUsername());
	}
	@GET
	@Path("/hostApartmentInactive")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> findActiveHostApartmentInactive(@Context HttpServletRequest request){
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		User user = (User) request.getSession(false).getAttribute("user");
		
		return apartmentsDAO.findAllHostApartmentInactive(user.getUsername());
	}
	
	
	//--------------------------------------------------------------------------------------
		//pretraga apartmana po  id
	@GET
	@Path("/pretragapoId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Apartment searchApById(@PathParam("id") Long id, @Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("pretraga apartmana pocela");

		 return ApartmentDAO.searchApById(id);
	}
	//--------------------------------------------------------------------------------------
			//pretraga apartmana po  id aktivni apartmani
		@GET
		@Path("/pretragapoIdActive/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Apartment pretragapoIdActive(@PathParam("id") Long id, @Context HttpServletRequest request) {
			ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			System.out.println("pretraga apartmana pocela");

			 return ApartmentDAO.pretragapoIdActive(id);
		}
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
	
	//---------------------------------------------------------------------------------
	//filtracija apartmana po tipu
	@GET
	@Path("/{type}/filtracija/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> filtrateApartmentsByType(@PathParam("type") String type,
			@Context HttpServletRequest request) {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		System.out.println("filtracija apartmana pocela");
		System.out.println(apartmentsDAO.filtrateApartmentsByType(type));
		 return apartmentsDAO.filtrateApartmentsByType(type);
	}
	//filtracija apartmana po tipu aktivni
		@GET
		@Path("/{type}/filtracijaActive/")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> filtrateApartmentsByTypeActive(@PathParam("type") String type,
				@Context HttpServletRequest request) {
			ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			System.out.println("filtracija apartmana pocela");
			System.out.println(apartmentsDAO.filtrateApartmentsByType(type));
			 return apartmentsDAO.filtrateApartmentsByTypeActive(type);
		}
	
	
	
	//filtracija apartmana po statusu
		@GET
		@Path("/{status}/filtracija2/")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> filtrateApartmentsByStatus(@PathParam("status") String status,
				@Context HttpServletRequest request) {
			ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			System.out.println("filtracija apartmana pocela");
			 return apartmentsDAO.filtrateApartmentsByStatus(status);
		}
			 
			//filtracija apartmana po statusu aktivni apartmani
				@GET
				@Path("/{status}/filtracija2Active/")
				@Produces(MediaType.APPLICATION_JSON)
				public Collection<Apartment> filtrateApartmentsByStatusActive(@PathParam("status") String status,
						@Context HttpServletRequest request) {
					ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
					System.out.println("filtracija apartmana pocela");
					 return apartmentsDAO.filtrateApartmentsByStatusActive(status);
				}
		
		
	
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
	
	
	@DELETE
	@Path("/{id}/deleteId")
	public Response deleteApartmentHost(@PathParam("id") Long id, @Context HttpServletRequest request)  throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		User host1 = (User) request.getSession().getAttribute("user");
		String host=host1.getUsername();
		boolean succesfulDeleted = apartmentsDAO.deleteApartmentHost(id,host);
		if(succesfulDeleted) {
			return Response.status(200).build();
		}
		return Response.status(400).entity("Id is not existed!").build();
	
	}
	
	

	
	@PUT
	@Path("/{id}/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Apartment edit(@PathParam("id") Long id,
						 Apartment apartment,	
						 @Context HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		return apartmentsDAO.editApartmanData(id,apartment);
	}
	
	
//--------------------------
	//sortiranje apartmana po broju soba r
	@GET
	@Path("/brojSoba/{par}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> getSortApartmentByRoomNumberAsc(@PathParam("par") String par,
						@Context HttpServletRequest request) {
		System.out.println("*****SORTIRANJE PO BROJU SOBA*****");
		ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		Collection<Apartment> ret = apartmentDAO.getSort(par);
		return ret;
	}

	
	//--------------------------
		//sortiranje apartmana po broju soba r aktivni apartmani
		@GET
		@Path("/brojSobaActive/{par}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> getSortApartmentByRoomNumberAscActive(@PathParam("par") String par,
							@Context HttpServletRequest request) {
			System.out.println("*****SORTIRANJE PO BROJU SOBA*****");
			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			Collection<Apartment> ret = apartmentDAO.getSortActive(par);
			return ret;
		}
//------------------------------------
	//sortiranje apartmana po broju gostiju
	
	@GET
	@Path("/brojGostiju/{par}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Apartment> getSortApartmentByGuestNumber(@PathParam("par") String par,
						@Context HttpServletRequest request) {
		System.out.println("*****SORTIRANJE PO BROJU GOSTIJU*****");
		ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
		Collection<Apartment> ret = apartmentDAO.getSortByGuestsNumber(par);
		return ret;
	}
	
	
	//sortiranje apartmana po broju gostiju aktivni
	
		@GET
		@Path("/brojGostijuActive/{par}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> getSortApartmentByGuestNumberActive(@PathParam("par") String par,
							@Context HttpServletRequest request) {
			System.out.println("*****SORTIRANJE PO BROJU GOSTIJU*****");
			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			Collection<Apartment> ret = apartmentDAO.getSortByGuestsNumberActive(par);
			return ret;
		}
	//------------------------------------
		//sortiranje apartmana po cijeni

		@GET
		@Path("/cijena/{par}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> getSortByPricePerNight(@PathParam("par") String par,
							@Context HttpServletRequest request) {
			System.out.println("*****SORTIRANJE PO CIJENI NOCENJA*****");
			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			Collection<Apartment> ret = apartmentDAO.getSortByPricePerNight(par);
			return ret;
		}
		
		//------------------------------------
		//sortiranje apartmana po cijeni aktivni

		@GET
		@Path("/cijenaActive/{par}")
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Apartment> getSortByPricePerNightActive(@PathParam("par") String par,
							@Context HttpServletRequest request) {
			System.out.println("*****SORTIRANJE PO CIJENI NOCENJA*****");
			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
			Collection<Apartment> ret = apartmentDAO.getSortByPricePerNightActive(par);
			return ret;
		}
		//------------------------------------
//		//sortiranje apartmana po lokaciji
//
//		@GET
//		@Path("/lokacija")
//		@Produces(MediaType.APPLICATION_JSON)
//		public Collection<Apartment> getSortByLocation(
//							@Context HttpServletRequest request) {
//			System.out.println("*****SORTIRANJE PO LOKACIJI*****");
//			ApartmentDAO apartmentDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
//			Collection<Apartment> ret = apartmentDAO.getSortByLocation();
//			return ret;
//		}
	
	
	//---------------------------------------------------------------------------
//	//pretrazivanje apartmana po lokaciji(grad)
			@GET
			@Path("/{city}/filtracija1/")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByLocation(@PathParam("city") String city,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po lokaciji");
				 return apartmentsDAO.searchApartmentsByLocation(city);
			}
			
			//---------------------------------------------------------------------------
//			//pretrazivanje apartmana po lokaciji(grad) aktivni apartmani
					@GET
					@Path("/{city}/filtracija1Active/")
					@Produces(MediaType.APPLICATION_JSON)
					public Collection<Apartment> searchApartmentsByLocationActive(@PathParam("city") String city,
							@Context HttpServletRequest request) {
						ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
						System.out.println("pretrazivanje apartmana po lokaciji");
						 return apartmentsDAO.searchApartmentsByLocationActive(city);
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
	
			
				
			
*/
			//--------------------------------------------------------------------------------
			//pretraga po maks broju gostiju
			@GET
			@Path("/{maxNumberGuest}/filtracija3/")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByMaxGuestNumber(@PathParam("maxNumberGuest") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByMaxGuestNumber(to);
			}
			//pretraga po maks broju gostiju aktivni
			@GET
			@Path("/{maxNumberGuest}/filtracija3Active/")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByMaxGuestNumberActive(@PathParam("maxNumberGuest") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByMaxGuestNumberActive(to);
			}
			//-----------------------------------------------------------------------------------
			//-------------------------------------------------------------------------------
//			//pretgara apartmana po broju soba(od-do)
			@GET
			@Path("/{from}/{to}/filtracija4/")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByRoomNumber(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByRoomNumber(from,to);
			}
			
			//pretgara apartmana po broju soba(od-do) aktivni apartmani
			@GET
			@Path("/{from}/{to}/filtracija4Active/")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByRoomNumberActive(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu broja soba");
				 return apartmentsDAO.searchApartmentsByRoomNumberActive(from,to);
			}
			
//			//-------------------------------------------------------------------------------
//					//pretgara apartmana po cijeni (u rasponu od -do)
				@GET
				@Path("/{from}/{to}/filtracija5")
				@Produces(MediaType.APPLICATION_JSON)
				public Collection<Apartment> searchApartmentsByPriceAmount(@PathParam("from") String from,
						@PathParam("to") String to,
						@Context HttpServletRequest request) {
					ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
					System.out.println("pretrazivanje apartmana po opsegu cijene");
				 return apartmentsDAO.searchApartmentsByPriceAmount(from,to);
 			}
				//-------------------------------------------------------------------------------
//				//pretgara apartmana po cijeni (u rasponu od -do) aktivni apartmani
			@GET
			@Path("/{from}/{to}/filtracija5Active")
			@Produces(MediaType.APPLICATION_JSON)
			public Collection<Apartment> searchApartmentsByPriceAmountActive(@PathParam("from") String from,
					@PathParam("to") String to,
					@Context HttpServletRequest request) {
				ApartmentDAO apartmentsDAO = (ApartmentDAO) ctx.getAttribute("apartmentDAO");
				System.out.println("pretrazivanje apartmana po opsegu cijene");
			 return apartmentsDAO.searchApartmentsByPriceAmountActive(from,to);
			}
				

}

