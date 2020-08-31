package model;

import java.util.ArrayList;
import enumeration.Gender;
import enumeration.Role;

public class User {
		String username;	//jedinstveno je pa ovde nam ne treba id	
		String password;	
		String firstName;
		String lastName;
		Gender gender;
		Role role;
		ArrayList<String> rentalApartments;
		ArrayList<String> rentedApartments;
		ArrayList<String> reservations;
}