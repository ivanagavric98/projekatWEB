package dao;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enumeration.Status;
import model.Apartment;

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

	public Collection<Apartment> searchApById(String id) {
			List <Apartment> apartmentsList=new ArrayList<>();
			
			for(Apartment a : apartments.values()) {
				if((a.getStatus().equals(Status.INACTIVE)) && a.getId() == Long.parseLong(id)) {
					System.out.println(a.getId());
					apartmentsList.add(a);
					}
			
				}
			
			return apartmentsList;
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
	/*
	public Apartment editApartmentData(Apartment newApartment){
		Apartment old_Apartment=null;
		boolean isUnique=true;
		for(Apartment a:apartments.values()) {
			if(newApartment.getId()==a.getId()) {
				isUnique=false;
			}
		}
		for(Apartment a :apartments.values()) {
			if(newApartment.getId() == a.getId()) {
				old_Apartment = a;
				break;
			}	
		}
		
		if(old_Apartment == null) {
			return null;
		}
		
		if(isUnique) {
			old_Apartment.setType(newApartment.getType());
			old_Apartment.setRoomsNumber(newApartment.getRoomsNumber());
			old_Apartment.setGuestsNumber(newApartment.getGuestsNumber());
			old_Apartment.setLocation(newApartment.getLocation());
			old_Apartment.setHost(newApartment.getHost());
			old_Apartment.setComments(newApartment.getComments());
			old_Apartment.setPhotos(newApartment.getPhotos());
			old_Apartment.setPricePerNight(newApartment.getPricePerNight());
			old_Apartment.setCheckInTime(newApartment.getCheckInTime());
			old_Apartment.setCheckOutTime(newApartment.getCheckOutTime());
			old_Apartment.setStatus(newApartment.getStatus());
			old_Apartment.setActive(newApartment.isActive());

			apartments.put(old_Apartment.getId(), old_Apartment);
			System.out.println("mijenjanje apartmana");
			return old_Apartment;
		}
		
		return null;
	}
	*/
	
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
		newApartment.setId(id);
		newApartment.setType(newApartmentData.getType());
		newApartment.setRoomsNumber(newApartmentData.getRoomsNumber());
		newApartment.setGuestsNumber(newApartmentData.getGuestsNumber());
		newApartment.setLocation(newApartmentData.getLocation());
		newApartment.setHost(newApartmentData.getHost());
		newApartment.setComments(newApartmentData.getComments());
		newApartment.setPhotos(newApartmentData.getPhotos());
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
		//----------------------------------------------------------------------------------------
				//sortiranje apartmana po broju soba
		public Collection<Apartment> getSort(String par) {
			List<Apartment> apartmentsToSort=(List<Apartment>) findAll();
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
			for(Apartment a : apartments.values()) {
				if( a.getType().toString().equals(type)) {
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

		public Collection<Apartment> searchApartmentsByLocation(String city) {
			List<Apartment>resultApartmnts=new ArrayList<Apartment>();
			for(Apartment a : apartments.values()) {
				if( a.isActive() && a.getLocation().address.city.toString().equals(city)) {
					resultApartmnts.add(a);
					}			
				}
			return resultApartmnts;
		}

		
}
