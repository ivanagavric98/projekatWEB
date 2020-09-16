package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Amenities;
import model.Apartment;
import model.User;

public class AmenitiesDAO {

	private static Map<Long, Amenities> amenities = new HashMap<>();
	private String contextPath;

	public AmenitiesDAO() {
	}

	public AmenitiesDAO(String contextPath) throws NoSuchAlgorithmException, IOException {
		this.contextPath = contextPath;
		amenities = loadAmenities(contextPath);
	}

	// dodavanje sadrzaja u apartman
	public boolean addAmenities(Amenities amenitie) throws NoSuchAlgorithmException, IOException {
		boolean isUnique = true;
		for (Amenities a : amenities.values()) {
			if (amenitie.getName().equals(a.getName())) {
				isUnique = false;
			}
		}

		if (isUnique) {
			amenitie.setId(amenities.size() + 1);
			amenities.put(amenitie.getId(), amenitie);
			saveAmenities(contextPath);
			return true;
		}
		return false;
	}
	public static Amenities findById(Long id) {
		for(Amenities a : amenities.values()) {
			if(a.getId() == id) {
				return a;	
			}
		}
		
		return null;
	}
	public Amenities editAmenitie(long id, Amenities amenitie) {
		Amenities old = amenities.get(id);
		
		boolean isUnique = true;
		for (Amenities a : amenities.values()) {
			if (amenitie.getName().equals(a.getName())) {
				isUnique = false;
			}
		}
		if(!isUnique) {
			return null;
		}
		old.setName(amenitie.getName());
		
		return old;

	}

	// brisanje sadrzaja apartmana
	public boolean deleteAmenitie(String name) throws NoSuchAlgorithmException, IOException {
		for (Amenities amenitie : amenities.values()) {
			if (amenitie.getName().equals(name) && amenitie.isActive()) {
				amenitie.setActive(false);
				saveAmenities(contextPath);
				return true;
			}
		}
		return false;

	}

	public Collection<Amenities> getAmenities() {
		return amenities.values();
	}

	//ucitavanje liste korisnika iz fajla
	public HashMap<Long,Amenities> loadAmenities(String contextPath) throws IOException, NoSuchAlgorithmException {
	    ObjectMapper mapper = new ObjectMapper();
	    File amenitiesFile = new File(contextPath + "/amenities.json");
	    boolean success = amenitiesFile.createNewFile();
	    if (success) {
	       mapper.writeValue(amenitiesFile, amenities);
	    }
	    return mapper.readValue(amenitiesFile, new TypeReference<HashMap<Long,Amenities>>() {});
	}

	//upisivanje u novi fajl 
		public void saveAmenities(String contextPath) throws IOException, NoSuchAlgorithmException {
		    ObjectMapper mapper = new ObjectMapper();
		    File amenitiesFile = new File(contextPath + "/amenities.json");
		    amenitiesFile.createNewFile();
		    mapper.writeValue(amenitiesFile, amenities);
		}

}
