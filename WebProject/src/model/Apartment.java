package model;

import java.time.LocalDate;
import java.util.ArrayList;

import enumeration.ApartmentType;
import enumeration.Status;

public class Apartment {
	public long id;		//jedinstveni identifikator
	public ApartmentType type;
	public int roomsNumber;
	public int guestsNumber;
	public Location location;
	public ArrayList<LocalDate> rentalDates;
	public ArrayList<LocalDate> availableDates;
	public String host;
	public String comments;
	public String photos;
	public double pricePerNight;
	public int checkInTime;
	public int checkOutTime;
	public Status status;
	public ArrayList<Long> amenities;
	public ArrayList<Long> reservations;
	public boolean active;
	
	public Apartment() {
		active = true;
	}
	
	public Apartment(long id, ApartmentType type, int roomsNumber, int guestsNumber, Location location,
			ArrayList<LocalDate> rentalDates, ArrayList<LocalDate> availableDates, String host, String comments, String photos,
			double pricePerNight, int checkInTime, int checkOutTime, Status status, ArrayList<Long> amenities,
			ArrayList<Long> reservations, boolean active) {
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
		this.status = status;
		this.amenities = amenities;
		this.reservations = reservations;
		this.active = active;
	}

	public Apartment(long id, ApartmentType type, int roomsNumber, int guestsNumber, Location location,
			String host, double pricePerNight, int checkInTime, int checkOutTime, Status status, boolean active) {
		super();
		this.id = id;
		this.type = type;
		this.roomsNumber = roomsNumber;
		this.guestsNumber = guestsNumber;
		this.location = location;
		this.host = host;
		this.pricePerNight = pricePerNight;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.status = status;
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	public ArrayList<LocalDate> getRentalDates() {
		return rentalDates;
	}
	public void setRentalDates(ArrayList<LocalDate> rentalDates) {
		this.rentalDates = rentalDates;
	}
	public ArrayList<LocalDate> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(ArrayList<LocalDate> availableDates) {
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
	public Status getStatus() {
		return Status.INACTIVE;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public ArrayList<Long> getAmenities() {
		return amenities;
	}
	public void setAmenities(ArrayList<Long> amenities) {
		this.amenities = amenities;
	}
	public ArrayList<Long> getReservations() {
		return reservations;
	}
	public void setReservations(ArrayList<Long> reservations) {
		this.reservations = reservations;
	}
	
	
}
