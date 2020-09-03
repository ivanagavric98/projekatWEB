package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import model.User;

public class UserDAO {
	
	private HashMap<String, User> users = new HashMap<String, User>();
	
	public UserDAO() {
		
	}
	
	
	public UserDAO(String contextPath) {
		loadUsers(contextPath);
	}


	public Collection<User> findAll() {
		return users.values();
	}

	
	public User findUser(String id) {
		return users.containsKey(id) ? users.get(id) : null;
	}
	
	public User save(User user) {
		Integer maxId = -1;
		for (String id : users.keySet()) {
			int idNum = Integer.parseInt(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		user.setUsername(maxId.toString());
		users.put(user.getUsername(), user);
		return user;
	}

	private void loadUsers(String contextPath) {
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, username = "", password = "",  firstName = "", lastName = "", gender = "",
					role = "", rentalApartments = "", rentedApartments = "", reservations = "";
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					firstName = st.nextToken().trim();
					lastName = st.nextToken().trim();
					gender = st.nextToken().trim();
					role = st.nextToken().trim();
					
				}
				users.put(username, new User());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
		}
	}

