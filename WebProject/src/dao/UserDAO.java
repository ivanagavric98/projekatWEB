package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import enumeration.Gender;
import enumeration.Role;
import model.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private String contextPath;
	
	public UserDAO() {
	}

	public UserDAO(String contextPath) {
		this.contextPath = contextPath;
		loadUsers();
	
	}
	
	public User findUser(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	
		private Gender getGender(String gender) {
		switch (gender) {
			case "MALE":
				return Gender.MALE;
			case "FEMALE":
				return Gender.FEMALE;
		}
		return null;
	}

	public Collection<User> findAll() {
		return users.values();
	}
	

	private Role getRole(String role) {
		switch (role) {
		case "HOST":
			return Role.HOST;
		case "GUEST":
			return Role.GUEST;
		default:
			return Role.ADMINISTRATOR;
		}
	}

	public boolean registerUser(User user) {
		User registerUser = findUser(user.getUsername(), user.getPassword());
		if(registerUser != null) {
			return false;
		}
		
		user.setRole(Role.GUEST);
		users.put(user.getUsername(), user);
		saveUsers();
		return true;
	}
	

	
	public User getUser(String username) {
		return users.get(username);
	}

	public User editPersonalData(String username, User newUserData) {
		User user = users.get(username);
		if(user == null) {
			return null;
		}
		User newUser = createNewUser(newUserData, username, user.getRole());
		saveUsers();
		
		return newUser;
	}

	private User createNewUser(User newUserData, String username, Role role) {
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(newUserData.getPassword());
		newUser.setFirstName(newUserData.getFirstName());
		newUser.setLastName(newUserData.getLastName());
		newUser.setRole(role);
		newUser.setGender(newUserData.getGender());
		
		users.put(newUser.getUsername(), newUser);
		
		return newUser;
	}
	
	//metoda za ucitavanje korisnika iz json datoteke
	@SuppressWarnings("unchecked")
	public void loadUsers() {
		System.out.println("load users");
		
		JSONParser parser = new JSONParser(); 
		
		try {
			Object obj = parser.parse(new FileReader(contextPath + "/users.json"));
			JSONArray jsonArray = (JSONArray) obj; 
			System.out.println(jsonArray);
			
			Iterator<JSONObject> iterator = jsonArray.iterator();
			
			while (iterator.hasNext()) {
				JSONObject jsonObject = iterator.next();
				User user = new User();
			
				user.setUsername((String) jsonObject.get("username"));
				user.setPassword((String) jsonObject.get("password"));
				user.setFirstName((String) jsonObject.get("firstName"));
				user.setLastName((String) jsonObject.get("lastName"));
				user.setGender(getGender((String) jsonObject.get("gender")));
				user.setActive((boolean) jsonObject.get("active"));
				user.setRole(getRole((String)jsonObject.get("role")));
				
				if(user.getRole().equals("HOST")) {
					JSONArray apartments = (JSONArray)jsonObject.get("rentalApartments");
					if(!apartments.isEmpty()) {
						ArrayList<Long> rentalApartments = new ArrayList<>();
						for(int i=0; i<apartments.size(); i++) {
							rentalApartments.add((Long) apartments.get(i));
						}
						user.setRentalApartments(rentalApartments);
					}
				}else if(user.getRole().equals("GUEST")) {
					JSONArray rentedApartmentsJSON = (JSONArray)jsonObject.get("rentedApartments");
					if(!rentedApartmentsJSON.isEmpty()) {
						ArrayList<Long> rentedApartments = new ArrayList<>();
						for(int i=0; i<rentedApartmentsJSON.size(); i++) {
							rentedApartments.add((Long) rentedApartmentsJSON.get(i));
						}
						user.setRentalApartments(rentedApartments);
					}
					JSONArray reservationsJSON = (JSONArray)jsonObject.get("reservations");
					if(!reservationsJSON.isEmpty()) {
						ArrayList<Long> reservations = new ArrayList<>();
						for(int i=0; i<reservationsJSON.size(); i++) {
							reservations.add((Long) reservationsJSON.get(i));
						}
						user.setRentalApartments(reservations);
					}
				}	
				
				users.put(user.getUsername(), user);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	 }
	
	//metoda za cuvanje korisnika u json datoteku
	@SuppressWarnings({ "unchecked", "resource" })
	public void saveUsers() {
		 JSONArray listUsers = new JSONArray();
		 
		 for (String username : users.keySet()) {
		    	User user = users.get(username);
		    	
		    	JSONObject obj = new JSONObject();
		        
		    	obj.put("username",user.getUsername());
		    	obj.put("password",user.getPassword());
		    	obj.put("firstName",user.getFirstName());
		    	obj.put("lastName",user.getLastName());
		    	obj.put("gender", user.getGender().toString());
		    	obj.put("active",user.isActive());
		    	obj.put("role",user.getRole().toString());
		  
		    	JSONArray rentedApartments = new JSONArray();
		    	JSONArray rentalApartments = new JSONArray();
		    	JSONArray reservationList = new JSONArray();
		   
		    	if(user.getRole().equals(Role.GUEST)) {
		    		for(Long apartment_id: user.getRentedApartments()) {
		    			rentedApartments.add(apartment_id);
		    		}
		    		for(Long reservation_id: user.getReservations()) {
		    			reservationList.add(reservation_id);
		    		}
		    	} else if (user.getRole().equals(Role.HOST)) {
		    		for(Long host_apartment_id: user.getRentalApartments()) {
		    			rentalApartments.add(host_apartment_id);
		    		}
		    	}
		        obj.put("rentedApartments",rentedApartments);	 
		        obj.put("rentalApartments",rentalApartments);	
		        obj.put("reservations",rentedApartments);	
		    	
		    	listUsers.add(obj);
		   }
		   try{
			   
				FileWriter fw = new FileWriter(contextPath + "/users.json"); 
				fw.write(listUsers.toJSONString());
				fw.flush();
			}catch(Exception e) {
				e.printStackTrace();
			}   
	}
	
}