package model;

public class Comment {
	public long id; 		//jedinstveni identifikator
	public String guest;
	public Long apartment;
	public String text;
	public String ratingOfApartments;
	public boolean active;
	
	public Comment() {
		active = true;
	}
	
	public Comment(long id, String guest, Long apartment, String text, String ratingOfApartments) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.ratingOfApartments = ratingOfApartments;
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
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public Long getApartment() {
		return apartment;
	}
	public void setApartment(Long apartment) {
		this.apartment = apartment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getRatingOfApartments() {
		return ratingOfApartments;
	}
	public void setRatingOfApartments(String ratingOfApartments) {
		this.ratingOfApartments = ratingOfApartments;
	}
	
	
}
