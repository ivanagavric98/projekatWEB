package model;

public class Comment {
	public String id; 		//jedinstveni identifikator
	public String guest;
	public String apartment;
	public String text;
	public String ratingOfApartments;
	
	public Comment() {}
	
	public Comment(String id, String guest, String apartment, String text, String ratingOfApartments) {
		super();
		this.id = id;
		this.guest = guest;
		this.apartment = apartment;
		this.text = text;
		this.ratingOfApartments = ratingOfApartments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
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
