package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.sun.tools.javac.jvm.Gen;

import enumeration.Gender;
import enumeration.Role;
import model.User;

public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	
	public UserDAO() {
	}

	public UserDAO(String contextPath) {
		loadUsers(contextPath);
	}
	
	public User find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	public Collection<User> findAll() {
		return users.values();
	}
	

	private void loadUsers(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String firstName = st.nextToken().trim();
					String lastName = st.nextToken().trim();
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					String gender = st.nextToken().trim();
					String role = st.nextToken().trim();
					
					users.put(username, new User
							(firstName, lastName, username, password, getGender(gender), getRole(role)));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
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
		User registerUser = find(user.getUsername(), user.getPassword());
		if(registerUser != null) {
			return false;
		}
		
		createUser(user);
		// TODO upisati ga u fajl
		writeUserFile(user);
		return true;
	}

	private void writeUserFile(User user) {
		
		
	}

	private void createUser(User user) {
		users.put(user.getUsername(), user);
	}
}