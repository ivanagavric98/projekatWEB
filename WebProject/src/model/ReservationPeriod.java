package model;

import java.time.LocalDate;

public class ReservationPeriod {
	
	private long id;
	private LocalDate dateFrom;
	private int numberOfNights;
	
	public ReservationPeriod() {
	}

	public ReservationPeriod(long id, LocalDate dateFrom, int numberOfNights) {
		super();
		this.id = id;
		this.dateFrom = dateFrom;
		this.numberOfNights = numberOfNights;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public int getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(int numberOfNights) {
		this.numberOfNights = numberOfNights;
	}

}
