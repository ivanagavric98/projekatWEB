package model;

import java.util.ArrayList;

import enumeration.ApartmentType;

public class Apartment {
	String id;		//jedinstveni identifikator
	ApartmentType type;
	int roomsNumber;
	int guestsNumber;
	Location location;
	ArrayList<Long> rentalDates;
	ArrayList<Long> availableDates;
	String host;
	String comments;
	String photos;
	double pricePerNight;
	int checkInTime;
	int checkOutTime;
	ArrayList<String> amenities;
	ArrayList<String> reservations;
}
