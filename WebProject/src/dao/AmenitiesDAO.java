package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import model.Amenities;
import model.User;

public class AmenitiesDAO {

	private Map<Long, Amenities> amenities = new HashMap<>();
	private String contextPath;
	
	public AmenitiesDAO() {
	}

	public AmenitiesDAO(String contextPath) {
		this.contextPath = contextPath;
		loadAmenities();
	}
	
	// dodavanje sadrzaja u apartman
	public boolean addAmenities(Amenities amenitie) {
		boolean isUnique = true;
		for(Amenities a : amenities.values()) {
			if(amenitie.getName().equals(a.getName())) {
				isUnique = false;
			}
		}
		
		if(isUnique) {
			amenitie.setId(amenities.size() + 1);
			amenities.put(amenitie.getId(), amenitie);
			saveAmenities();
			return true;
		}
		return false;
	}
	
	public boolean deleteAmenitie(String name) {
		for(Amenities amenitie : amenities.values()) {
			if(amenitie.getName().equals(name) && amenitie.isActive()) {
				amenitie.setActive(false);
				saveAmenities();
				return true;
			}
		}
		return false;
		
	}
	
	
	public Collection<Amenities> getAmenities(){
		return amenities.values();
	}
	

	@SuppressWarnings("resource")
	public void saveAmenities() {
		 System.out.println("save amenities");
		 JSONArray listAmenities = new JSONArray();
		 
		 for (Long id : amenities.keySet()) {
		    	Amenities amenitie = amenities.get(id);
		    	JSONObject obj = new JSONObject();
		        obj.put("name",amenitie.getName());
		    	obj.put("id",amenitie.getId());  		    	
		    	listAmenities.add(obj);
		   }
		   try{
				System.out.println(contextPath + "/amenities.json");		
				FileWriter fw = new FileWriter(contextPath + "/amenities.json"); 
				fw.write(listAmenities.toJSONString());
				fw.flush();
			}catch(Exception e) {
				e.printStackTrace();
			}   
	}
	
	public void loadAmenities()  {
		System.out.println("load amenities");
		JSONParser parser = new JSONParser(); 
		
		try {
			Object obj = parser.parse(new FileReader(contextPath));
			
			JSONArray jsonArray = (JSONArray) obj;
			System.out.println(jsonArray);
			
			Iterator<JSONObject> iterator = jsonArray.iterator();

			while (iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				Amenities amenitie = new Amenities();
				amenitie.setId(((Long)jsonObject.get("id")).intValue());
				amenitie.setName((String) jsonObject.get("name"));
				
				
				amenities.put(amenitie.getId(), amenitie);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
	
	
}
