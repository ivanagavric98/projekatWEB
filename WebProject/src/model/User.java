package model;

import java.util.ArrayList;
import enumeration.Gender;
import enumeration.Role;

public class User {
		public String username;	//jedinstveno je pa ovde nam ne treba id	
		public String password;	
		public String firstName;
		public String lastName;
		public Gender gender;
		public Role role;
		public ArrayList<Long> rentalApartments;
		public ArrayList<Long> rentedApartments;
		public ArrayList<Long> reservations;
		public boolean active;
		
		public User() {
			active = true;
			rentalApartments = new ArrayList();
			rentedApartments = new ArrayList();
			reservations = new ArrayList();
		}
		
		public User(String username, String password, String firstName, String lastName, Gender gender, Role role) {
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.role = role;
			this.active = true;
			this.rentalApartments = new ArrayList();
			this.rentedApartments = new ArrayList();
			this.reservations = new ArrayList();
		}
		
		public User(String username, String password, String firstName, String lastName, Gender gender, Role role,
				ArrayList<Long> rentalApartments, ArrayList<Long> rentedApartments,
				ArrayList<Long> reservations, boolean active) {
			super();
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.role = role;
			this.rentalApartments = rentalApartments;
			this.rentedApartments = rentedApartments;
			this.reservations = reservations;
			this.active = active;
		}
		
		public void setActive(boolean active) {
			this.active = active;
		}
		
		public boolean isActive() {
			return active;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		public ArrayList<Long> getRentalApartments() {
			return rentalApartments;
		}
		public void setRentalApartments(ArrayList<Long> rentalApartments) {
			this.rentalApartments = rentalApartments;
		}
		public ArrayList<Long> getRentedApartments() {
			return rentedApartments;
		}
		public void setRentedApartments(ArrayList<Long> rentedApartments) {
			this.rentedApartments = rentedApartments;
		}
		public ArrayList<Long> getReservations() {
			return reservations;
		}
		public void setReservations(ArrayList<Long> reservations) {
			this.reservations = reservations;
		}
		
		

}