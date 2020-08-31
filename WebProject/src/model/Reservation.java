package model;

import enumeration.ReservationStatus;

public class Reservation {
	public String id;		//jedinstveni identifikator
	public Apartment bookedApartment;
	public Long startDate;
	public int nightsNumber;
	public double totalPrice;
	public String reservationMessage;
	public String guest;
	public ReservationStatus reservationStatus;
	
	public Reservation() {}
	
	public Reservation(String id, Apartment bookedApartment, Long startDate, int nightsNumber, double totalPrice,
			String reservationMessage, String guest, ReservationStatus reservationStatus) {
		super();
		this.id = id;
		this.bookedApartment = bookedApartment;
		this.startDate = startDate;
		this.nightsNumber = nightsNumber;
		this.totalPrice = totalPrice;
		this.reservationMessage = reservationMessage;
		this.guest = guest;
		this.reservationStatus = reservationStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Apartment getBookedApartment() {
		return bookedApartment;
	}
	public void setBookedApartment(Apartment bookedApartment) {
		this.bookedApartment = bookedApartment;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
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
