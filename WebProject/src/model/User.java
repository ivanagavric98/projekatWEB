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
		public ArrayList<String> rentalApartments;
		public ArrayList<String> rentedApartments;
		public ArrayList<String> reservations;
		
		public User() {}
		
		public User(String username, String password, String firstName, String lastName, Gender gender, Role role,
				ArrayList<String> rentalApartments, ArrayList<String> rentedApartments,
				ArrayList<String> reservations) {
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
		public ArrayList<String> getRentalApartments() {
			return rentalApartments;
		}
		public void setRentalApartments(ArrayList<String> rentalApartments) {
			this.rentalApartments = rentalApartments;
		}
		public ArrayList<String> getRentedApartments() {
			return rentedApartments;
		}
		public void setRentedApartments(ArrayList<String> rentedApartments) {
			this.rentedApartments = rentedApartments;
		}
		public ArrayList<String> getReservations() {
			return reservations;
		}
		public void setReservations(ArrayList<String> reservations) {
			this.reservations = reservations;
		}
		
		

}