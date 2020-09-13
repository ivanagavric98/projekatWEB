package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumeration.Status;
import model.Apartment;
import model.Comment;
import model.ReservationPeriod;

public class ApartmentDAO {
	private static Map<Long, Apartment> apartments = new HashMap<>();
	private String contextPath;
	
	public ApartmentDAO() {
	}

	public ApartmentDAO(String contextPath) throws NoSuchAlgorithmException, IOException {
		this.contextPath = contextPath;
		apartments = loadApartments(contextPath);
	}

	
	public Apartment addApartment(Apartment apartment) throws NoSuchAlgorithmException, IOException {
		if(apartments.containsKey(apartment.getId())) {
			return null;
		}
		
	    long id = apartments.size() + 1;
	    
		
		Apartment a = new Apartment(
					id,
					apartment.getType(),
					apartment.getRoomsNumber(),
					apartment.getGuestsNumber(),
					apartment.getLocation(),
					apartment.getHost(),
					apartment.getPricePerNight(),
					apartment.getCheckInTime(),
					apartment.getCheckOutTime(),
					Status.INACTIVE,
					true
				);
		apartments.put(a.getId(), a);
		
		saveApartments(contextPath);
		return a;
	}

	
	public Collection<Apartment> findAll() {
			return apartments.values();
		}
//---------------------------
	//pretraga po id
	public  static Apartment searchApById(Long id) {
		
		for(Apartment a : apartments.values()) {
			if(a.getId() == id) {
				return a;	
			}
		}
		
		return null;
	}
	
//	public Apartment findById(Long id) {
//		for(Long ap : apartments.keySet()) {
//			if(ap.getId() == id) {
//				return ap;
//			}
//		}
//	}
//	
//	
//	
	public Collection<Apartment> findAllHostApartment(String host){
		ArrayList<Apartment> apartmentList = new ArrayList<Apartment>();
		
		for(Apartment apartment : apartments.values()) {
			if(apartment.getStatus().equals(Status.ACTIVE) && apartment.getHost().equals(host)) {
				apartmentList.add(apartment);
			}
		}
		return apartmentList;
	}
	
	
	//----------------------------------------
		//pretraga po i i host (za domacina)
	public Collection<Apartment> searchApByIdAndHost(String id,String host ) {
		List <Apartment> apartmentsList=new ArrayList<>();
		
		for(Apartment a : apartments.values()) {
			if((a.getStatus().equals(Status.INACTIVE)) && a.getId() == Long.parseLong(id) && a.getHost().equals(host)) {
				System.out.println(a.getId());
				apartmentsList.add(a);
				}
		
			}
		
		return apartmentsList;
}
	
	public Apartment editApartmanData(Long id, Apartment newApartmentData) throws NoSuchAlgorithmException, IOException {
		Apartment apartment = apartments.get(id);
		if(apartment == null) {
			return null;
		}
		Apartment newApartment = createNewApartment(newApartmentData, id, apartment.getStatus());
		saveApartments(contextPath);
		
		return newApartment;
	}
	
	private Apartment createNewApartment(Apartment newApartmentData, Long id, Status status) {
		Apartment newApartment = new Apartment();
		
		
		//for (Long a : newApartmentData.getComments()) {
		//	newApartment.setComments(a);
		//}
		
		newApartment.setId(id);
		newApartment.setType(newApartmentData.getType());
		newApartment.setRoomsNumber(newApartmentData.getRoomsNumber());
		newApartment.setGuestsNumber(newApartmentData.getGuestsNumber());
		newApartment.setLocation(newApartmentData.getLocation());
		newApartment.setHost(newApartmentData.getHost());
		//newApartment.setPhotos(newApartmentData.getPhotos());
		newApartment.setPricePerNight(newApartmentData.getPricePerNight());
		newApartment.setCheckInTime(newApartmentData.getCheckInTime());
		newApartment.setCheckOutTime(newApartmentData.getCheckOutTime());
		newApartment.setStatus(newApartmentData.getStatus());
		newApartment.setActive(newApartmentData.isActive());
		
		apartments.put(newApartmentData.getId(), newApartment);
		
		return newApartment;
	}
	
	public boolean deleteApartment(Long id) throws NoSuchAlgorithmException, IOException {
			
		Apartment apartment = apartments.get(id);
		boolean delete_fleg = false;
		
		if(apartments.containsKey(id)) {
			apartment.setActive(false);
			delete_fleg = true;
			saveApartments(contextPath);
		}
		return delete_fleg;
	}
			
	//ucitavanje liste korisnika iz fajla
	public HashMap<Long,Apartment> loadApartments(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File apartmentFile = new File(contextPath + "/apartments.json");
	    boolean success = apartmentFile.createNewFile();
	    if (success) {
	       mapper.writeValue(apartmentFile, apartments);
	    }
	    return mapper.readValue(apartmentFile, new TypeReference<HashMap<Long,Apartment>>() {});
	}

	//upisivanje u novi fajl 
		public void saveApartments(String contextPath) throws IOException, NoSuchAlgorithmException {
		    ObjectMapper mapper = new ObjectMapper();
		    File apartmentFile = new File(contextPath + "/apartments.json");
		    apartmentFile.createNewFile();
		    mapper.writeValue(apartmentFile, apartments);
		}
		

		public Collection<Apartment> getSort(String par) {
			ArrayList<Apartment> apartmentsToSort=new ArrayList<Apartment>(apartments.values());
		    Comparator<Apartment> compare = (Apartment o1, Apartment o2) -> Double.compare(o1.getRoomsNumber(), o2.getRoomsNumber());
		    Collections.sort(apartmentsToSort,compare);
		    
			
		   
			if(par.equals("asc"))  {
				 Collections.sort(apartmentsToSort,compare);
			}
			else if(par.equals("desc")){
				Collections.sort(apartmentsToSort,compare.reversed());

			}
			
			System.out.println(Arrays.toString(apartmentsToSort.toArray()));
		   
			return apartmentsToSort;
		}
//----------------------------------------------------------------------------------------
		//sortiranje apartmana po broju gostiju
		public Collection<Apartment> getSortByGuestsNumber(String par) {
			ArrayList<Apartment>apartmentsToSort= new ArrayList<Apartment>(apartments.values());

		    Comparator<Apartment> compare = (Apartment o1, Apartment o2) -> Double.compare(o1.getGuestsNumber(), o2.getGuestsNumber());
		    Collections.sort(apartmentsToSort,compare);
		   
			if(par.equals("asc"))  {
				 Collections.sort(apartmentsToSort,compare);
			}
			else if(par.equals("desc")){
				Collections.sort(apartmentsToSort,compare.reversed());

			}
			
			System.out.println(Arrays.toString(apartmentsToSort.toArray()));
		   
			return apartmentsToSort;
		}
//---------------------------------------------------------------------------------------------
		//sortiranje po cijeni nocenja
		public Collection<Apartment> getSortByPricePerNight(String par) {
			ArrayList<Apartment>apartmentsToSort= new ArrayList<Apartment>(apartments.values());

		    Comparator<Apartment> compare = (Apartment o1, Apartment o2) -> Double.compare(o1.getPricePerNight(), o2.getPricePerNight());
		    Collections.sort(apartmentsToSort,compare);
		   
			if(par.equals("asc"))  {
				 Collections.sort(apartmentsToSort,compare);
			}
			else if(par.equals("desc")){
				Collections.sort(apartmentsToSort,compare.reversed());

			}
			
			System.out.println(Arrays.toString(apartmentsToSort.toArray()));
		   
			return apartmentsToSort;
		}
//------------------------------------------------------------------------------
		//filtracija apartmana po tipu

		public Collection<Apartment> filtrateApartmentsByType(String type) {
			List<Apartment>filtratedApartments=new ArrayList<Apartment>();
			if(type.equals("prazan_string")) {
				return apartments.values();
			}
			for(Apartment a : apartments.values()) {
				if( a.getType().toString().toLowerCase().contains(type.toLowerCase())) {
					filtratedApartments.add(a);
					}			
				}
			return filtratedApartments;
		}
	//-------------------------------------------------------------------------
		//filtracija apartmana po statusu
		public Collection<Apartment> filtrateApartmentsByStatus(String status) {
			List<Apartment>filtratedApartments=new ArrayList<Apartment>();
			for(Apartment a : apartments.values()) {
				if( a.getStatus().toString().equals(status)) {
					filtratedApartments.add(a);
					}			
				}
			return filtratedApartments;
		}
//-----------------------------------------------------------------------------
		//pretraga apartmana po lokaciji
		public Collection<Apartment> searchApartmentsByLocation(String city) {
			List<Apartment>resultApartmnts=new ArrayList<Apartment>();
			if(city.equals("prazan_string")) {
				return apartments.values() ;
			}
			
			for(Apartment a : apartments.values()) {
				if( a.isActive() && a.getLocation().address.city.toString().toLowerCase().contains(city.toLowerCase())) {
						resultApartmnts.add(a);
					}			
				}
			return resultApartmnts;
		}
//---------------------------------------------------------------------------------
		//filtriranje apartmana po opsegu cijene
		public Collection<Apartment> searchApartmentsByPriceAmount(String from, String to) {
			List<Apartment>resultApartmnts=new ArrayList<Apartment>();
			for(Apartment a : apartments.values()) {
				if( a.isActive() && a.getPricePerNight()>=Double.parseDouble(from) && a.getPricePerNight()<=Double.parseDouble(to)) {
					resultApartmnts.add(a);
					}			
				}
			return resultApartmnts;
		}
//-----------------------------------------------------------------------------------
		//filtriranje apartmana po opsegu broja soba
		public Collection<Apartment> searchApartmentsByRoomNumber(String from, String to) {
			List<Apartment>resultApartmnts=new ArrayList<Apartment>();
			for(Apartment a : apartments.values()) {
				if( a.isActive() && a.getRoomsNumber()>=Integer.parseInt(from) && a.getRoomsNumber()<=Integer.parseInt(to)) {
					resultApartmnts.add(a);
					}			
				}
			return resultApartmnts;
		}
//-----------------------------------------------------------------------------------
				//filtriranje apartmana po broju gostiju(do koliko gostiju)
		public Collection<Apartment> searchApartmentsByMaxGuestNumber( String to) {
					List<Apartment>resultApartmnts=new ArrayList<Apartment>();
					for(Apartment a : apartments.values()) {
						if( a.isActive() && a.getGuestsNumber()<=Integer.parseInt(to)) {
							resultApartmnts.add(a);
							}			
						}
					return resultApartmnts;
				}

		public Collection<Apartment> combinedSearch(String city, String fromPrice, String toPrice, String fromNumRoom,
				String toNumRoom, String guestNumber) {
			List<Apartment>locationSearch=(List<Apartment>) searchApartmentsByLocation(city);
			List<Apartment>priceSearch=(List<Apartment>) searchApartmentsByPriceAmount(fromPrice,toPrice);
			List<Apartment>roomNumSearc=(List<Apartment>) searchApartmentsByRoomNumber(fromNumRoom,toNumRoom);
			List<Apartment>guestNumberSearch =(List<Apartment>) searchApartmentsByMaxGuestNumber(guestNumber);
			List<Apartment>resultApartmnts=new ArrayList<Apartment>();


			for(Apartment a : apartments.values()) {
				if( a.isActive() && locationSearch.contains(a) && priceSearch.contains(a) && roomNumSearc.contains(a) && guestNumberSearch.contains(a)) {
					resultApartmnts.add(a);
					}			
				}
			return null;
		}

		public void addNewComment(Apartment apartment, Comment comment) throws NoSuchAlgorithmException, IOException {
			Apartment ap = apartments.get(apartment.getId());
			
			//ap.setComments(comment.getId());
			saveApartments(contextPath);
			
		}

		public Collection<Apartment> getSortByLocation() {
			ArrayList<Apartment>apartmentsToSort= new ArrayList<Apartment>(apartments.values());
			
			return null;
		}
		
//		
//		public void saveApartments() {
//			JSONArray apartmentList = new JSONArray();
//			
//			for(Long apartmentId : apartments.keySet()) {
//				Apartment ap = apartments.get(apartmentId);
//				JSONObject obj_ap = new JSONObject();
//				
////				obj_ap.put("id", ap.getId());
//				obj_ap.put("type", ap.getType());
//				obj_ap.put("roomsNumber", ap.getRoomsNumber());
//				obj_ap.put("guestsNumber", ap.getGuestsNumber());
//
//				JSONObject loc = new JSONObject();
//				if(ap.getLocation() != null) {
//					loc.put("longitude", ap.getLocation().getLongitude());
//					loc.put("latitude", ap.getLocation().getLatitude());
//
//					JSONObject address = new JSONObject();
//					if(ap.getLocation().getAddress() != null) {
////						address.put("id", ap.getLocation().getAddress().getId());
//						address.put("street", ap.getLocation().getAddress().getStreet());
//						address.put("number", ap.getLocation().getAddress().getNumber());
//						address.put("city", ap.getLocation().getAddress().getCity());
//						address.put("postalCode", ap.getLocation().getAddress().getPostalCode());
//					}
//					
//					loc.put("address", address);
//					System.out.println(contextPath+"/apartmentss.json");
//
//				}
//				
//				JSONArray rentalDate = new JSONArray();
//				if(!ap.getRentalDates().isEmpty()) {
//					for(LocalDate rDates : ap.getRentalDates()) {
//						rentalDate.add(rDates);
//					}
//				}
//				obj_ap.put("rentalDates", rentalDate);
//
//				
//				JSONArray busyDate = new JSONArray();
//				if(!ap.getBusyDates().isEmpty()) {
//					for(ReservationPeriod bDates : ap.getBusyDates()) {
//						 busyDate.add(bDates);
//					}
//				}
//				obj_ap.put("busyDates", busyDate);
//
//				JSONArray comment = new JSONArray();/*
//				if(!ap.getComments().isEmpty()) {
//					for(Long comm : ap.getComments()) {
//						 comment.add(comm);
//					}
//				}*/
//				/*obj_ap.put("comments", comment);
//
//				JSONArray photo = new JSONArray();
//				if(!ap.getPhotos().isEmpty()) {
//					for(Long ph : ap.getPhotos()) {
//						 photo.add(ph);
//					}
//				}
//				obj_ap.put("photos", photo);
//*/
//				
//				obj_ap.put("pricePerNight", ap.getPricePerNight());
//				obj_ap.put("checkInTime", ap.getCheckInTime());
//				obj_ap.put("checkOutTime", ap.getCheckOutTime());
//				obj_ap.put("status", ap.getStatus());
//				obj_ap.put("active", true);
//				
//				
//				JSONArray amenties = new JSONArray();
//				if(!ap.getAmenities().isEmpty()) {
//					for(Long am : ap.getAmenities()) {
//						amenties.add(am);
//					}
//				}
//				
//				obj_ap.put("amenties", amenties);
//
//				JSONArray reservations = new JSONArray();
//				if(!ap.getReservations().isEmpty()) {
//					for(Long rs : ap.getReservations()) {
//						reservations.add(rs);
//					}
//				}
//				
//				obj_ap.put("reservations", reservations);
//				
//				apartmentList.add(obj_ap);
//							
//			}try {
//					System.out.println(contextPath + "/apartments.json");		
//					FileWriter fw = new FileWriter(contextPath+"/apartments.json"); 
//					fw.write(apartmentList.toJSONString());
//					fw.flush();
//			}catch(Exception e) {
//					e.printStackTrace();
//		    }   
//			
//		}
//
		
}
