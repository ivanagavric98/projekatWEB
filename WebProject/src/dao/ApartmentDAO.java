package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.research.ws.wadl.Request;

import enumeration.ApartmentType;
import enumeration.Status;
import model.Address;
import model.Amenities;
import model.Apartment;
import model.Location;
import model.User;

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

	

		


}
