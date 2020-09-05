package model;

import java.time.LocalDate;

import enumeration.ReservationStatus;

public class Reservation {
	public long id;		//jedinstveni identifikator
	public Apartment bookedApartment;
	public LocalDate startDate;
	public int nightsNumber;
	public double totalPrice;
	public String reservationMessage;
	public String guest;
	public ReservationStatus reservationStatus;
	public boolean active;
	
	public Reservation() {
		nightsNumber = 1; 
		active = true;
	}
	
	public Reservation(long id, Apartment bookedApartment, LocalDate startDate, int nightsNumber, double totalPrice,
			String reservationMessage, String guest, ReservationStatus reservationStatus, boolean active) {
		super();
		this.id = id;
		this.bookedApartment = bookedApartment;
		this.startDate = startDate;
		this.nightsNumber = nightsNumber;
		this.totalPrice = totalPrice;
		this.reservationMessage = reservationMessage;
		this.guest = guest;
		this.reservationStatus = reservationStatus;
		this.active = active;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Apartment getBookedApartment() {
		return bookedApartment;
	}
	public void setBookedApartment(Apartment bookedApartment) {
		this.bookedApartment = bookedApartment;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public int getNightsNumber() {
		return nightsNumber;
	}
	public void setNightsNumber(int nightsNumber) {
		this.nightsNumber = nightsNumber;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getReservationMessage() {
		return reservationMessage;
	}
	public void setReservationMessage(String reservationMessage) {
		this.reservationMessage = reservationMessage;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
	
}
