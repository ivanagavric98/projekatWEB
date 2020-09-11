package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import enumeration.Gender;
import enumeration.Role;
import model.Amenities;
import model.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private String contextPath;
	
	public UserDAO() {
	}

	public UserDAO(String contextPath) throws NoSuchAlgorithmException, IOException {
		this.contextPath = contextPath;
		users = loadUsers(contextPath);
	
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

	public boolean registerUser(User user) throws NoSuchAlgorithmException, IOException {
		User registerUser = findUser(user.getUsername(), user.getPassword());
		if(registerUser != null) {
			return false;
		}
		
		user.setRole(Role.GUEST);
		users.put(user.getUsername(), user);
		saveUsers(contextPath);
		return true;
	}
	

	
	public User getUser(String username) {
		
		return users.get(username);
	}

	public User editPersonalData(String username, User newUserData) throws NoSuchAlgorithmException, IOException {
		User user = users.get(username);
		if(user == null) {
			return null;
		}
		User newUser = createNewUser(newUserData, username, user.getRole());
		saveUsers(contextPath);
		
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
	
	public Collection<User> searchUserByUsername(String username) {
		List <User> listUsers=new ArrayList<>();
		for(User u : users.values()) {
			if(u.active && u.getUsername().equals(username)) {
				listUsers.add(u);
				}
		
			}
		return listUsers;
		
		
	}
		
	//-------------------------------------------------------------------
		//filtriranje korisnika po ulozi(administrator)
	public Collection<User> filtrateUsersByRole(String role) {
		List<User>filtratedUsers=new ArrayList<User>();
		for(User u : users.values()) {
			if( u.getRole().toString().equals(role)) {
				filtratedUsers.add(u);
				}			
			}
		return filtratedUsers;
	}
	
	//filtriranje korisnika po polu
	public Collection<User> filtrateUsersByGender(String gender) {
		List<User>filtratedUsers=new ArrayList<User>();
		for(User u : users.values()) {
			if( u.getGender().toString().equals(gender)) {
				filtratedUsers.add(u);
				}			
			}
		return filtratedUsers;
	}
	
	
	
	//ucitavanje liste korisnika iz fajla
		public HashMap<String, User> loadUsers(String contextPath) throws IOException, NoSuchAlgorithmException {
		    ObjectMapper mapper = new ObjectMapper();
		    File usersFile = new File(contextPath + "/users.json");
		    boolean success = usersFile.createNewFile();
		    if (success) {
		       mapper.writeValue(usersFile, users);
		    }
		    return mapper.readValue(usersFile, new TypeReference<HashMap<String,User>>() {});
		}

		//upisivanje u novi fajl 
			public void saveUsers(String contextPath) throws IOException, NoSuchAlgorithmException {
			    ObjectMapper mapper = new ObjectMapper();
			    File usersFile = new File(contextPath + "/users.json");
			    usersFile.createNewFile();
			    mapper.writeValue(usersFile, users);
			}

}