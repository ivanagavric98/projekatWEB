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

	private Map<Long, Amenities> amenities = new HashMap<>();
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

	public Amenities editAmenitie(Amenities amenitie) {
		
		Amenities old_Amenitie = null;
		boolean isUnique = true;
		for (Amenities a : amenities.values()) {
			if (amenitie.getName().equals(a.getName())) {
				isUnique = false;
			}
		}

		
		for(Amenities a : amenities.values()) {
			if(amenitie.getId() == a.getId()) {
				old_Amenitie = a;
				break;
			}	
		}
		
		if(old_Amenitie == null) {
			return null;
		}
		
		if(isUnique) {
			old_Amenitie.setName(amenitie.getName());
			amenities.put(old_Amenitie.getId(), old_Amenitie);
			return old_Amenitie;
		}
		
		return null;
		
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
