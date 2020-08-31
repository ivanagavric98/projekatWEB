package model;

import enumeration.ReservationStatus;

public class Reservation {
	String id;		//jedinstveni identifikator
	Apartment bookedApartment;
	Long startDate;
	int nightsNumber;
	double totalPrice;
	String reservationMessage;
	String guest;
	ReservationStatus reservationStatus;
}
