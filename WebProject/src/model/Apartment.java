package model;

import java.util.ArrayList;

import enumeration.ApartmentType;

public class Apartment {
	public String id;		//jedinstveni identifikator
	public ApartmentType type;
	public int roomsNumber;
	public int guestsNumber;
	public Location location;
	public ArrayList<Long> rentalDates;
	public ArrayList<Long> availableDates;
	public String host;
	public String comments;
	public String photos;
	public double pricePerNight;
	public int checkInTime;
	public int checkOutTime;
	public ArrayList<String> amenities;
	public ArrayList<String> reservations;
	
	public Apartment() {}
	
	public Apartment(String id, ApartmentType type, int roomsNumber, int guestsNumber, Location location,
			ArrayList<Long> rentalDates, ArrayList<Long> availableDates, String host, String comments, String photos,
			double pricePerNight, int checkInTime, int checkOutTime, ArrayList<String> amenities,
			ArrayList<String> reservations) {
		super();
		this.id = id;
		this.type = type;
		this.roomsNumber = roomsNumber;
		this.guestsNumber = guestsNumber;
		this.location = location;
		this.rentalDates = rentalDates;
		this.availableDates = availableDates;
		this.host = host;
		this.comments = comments;
		this.photos = photos;
		this.pricePerNight = pricePerNight;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.amenities = amenities;
		this.reservations = reservations;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ApartmentType getType() {
		return type;
	}
	public void setType(ApartmentType type) {
		this.type = type;
	}
	public int getRoomsNumber() {
		return roomsNumber;
	}
	public void setRoomsNumber(int roomsNumber) {
		this.roomsNumber = roomsNumber;
	}
	public int getGuestsNumber() {
		return guestsNumber;
	}
	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public ArrayList<Long> getRentalDates() {
		return rentalDates;
	}
	public void setRentalDates(ArrayList<Long> rentalDates) {
		this.rentalDates = rentalDates;
	}
	public ArrayList<Long> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(ArrayList<Long> availableDates) {
		this.availableDates = availableDates;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public int getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(int checkInTime) {
		this.checkInTime = checkInTime;
	}
	public int getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(int checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public ArrayList<String> getAmenities() {
		return amenities;
	}
	public void setAmenities(ArrayList<String> amenities) {
		this.amenities = amenities;
	}
	public ArrayList<String> getReservations() {
		return reservations;
	}
	public void setReservations(ArrayList<String> reservations) {
		this.reservations = reservations;
	}
	
	
}
